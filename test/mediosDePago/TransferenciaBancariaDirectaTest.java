package mediosDePago;

import comprobantes.ComprobanteCBU;
import comprobantes.ComprobantePago;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransferenciaBancariaDirectaTest {

    TransferenciaBancariaDirecta transferencia;
    TransferenciaAPI api;

    //Set up
    @BeforeEach
    public void setUp() {
        api = mock(TransferenciaAPI.class);
        transferencia = new TransferenciaBancariaDirecta(api, "CBU-ORIGEN", "CBU-DESTINO", "mi.alias");
    }

    //Testing
    @Test
    public void procesarPagoExitosoTest() {
        //Stubbing del comportamiento esperado
        when(api.validarCBU("CBU-ORIGEN", "mi.alias")).thenReturn(true);
        when(api.transferir("CBU-ORIGEN", "CBU-DESTINO", 1000.0)).thenReturn("OP-555");
        //Exercise
        transferencia.procesarPago(1000.0);
        //Verify (Mockito)
        InOrder orden = inOrder(api);
        orden.verify(api).validarCBU("CBU-ORIGEN", "mi.alias");
        orden.verify(api).transferir("CBU-ORIGEN", "CBU-DESTINO", 1000.0);
    }

    @Test
    public void procesarPagoExitosoGeneraUnComprobanteCBUTest() {
        //Stubbing del comportamiento esperado
        when(api.validarCBU(anyString(), anyString())).thenReturn(true);
        when(api.transferir(anyString(), anyString(), anyDouble())).thenReturn("OP-555");
        //Exercise
        transferencia.procesarPago(1000.0);
        //Verify (Assert)
        assertEquals(1, transferencia.comprobantes.size());
        ComprobantePago comprobante = transferencia.comprobantes.get(0);
        ComprobanteCBU comprobanteCBU = (ComprobanteCBU) comprobante;
        assertEquals(1000.0, comprobanteCBU.getMonto());
        assertEquals("CBU-ORIGEN", comprobanteCBU.getCbuOrigen());
        assertEquals("CBU-DESTINO", comprobanteCBU.getCbuDestino());
        assertEquals("OP-555", comprobanteCBU.getNumeroOperacion());
    }

    @Test
    public void procesarPagoConCBUInvalidoTest() {
        //Stubbing del comportamiento esperado
        when(api.validarCBU(anyString(), anyString())).thenReturn(false);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> transferencia.procesarPago(1000.0));
        //Verify (Assert)
        assertEquals("CBU/CVU o alias inválido.", error.getMessage());
        assertTrue(transferencia.comprobantes.isEmpty());
        //Verify (Mockito)
        verify(api, never()).transferir(anyString(), anyString(), anyDouble());
    }

    @Test
    public void procesarPagoConTransferenciaFallidaTest() {
        //Stubbing del comportamiento esperado
        when(api.validarCBU(anyString(), anyString())).thenReturn(true);
        when(api.transferir(anyString(), anyString(), anyDouble())).thenReturn(null);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> transferencia.procesarPago(1000.0));
        //Verify (Assert)
        assertEquals("La transferencia no se pudo concretar.", error.getMessage());
        assertTrue(transferencia.comprobantes.isEmpty());
    }
}
