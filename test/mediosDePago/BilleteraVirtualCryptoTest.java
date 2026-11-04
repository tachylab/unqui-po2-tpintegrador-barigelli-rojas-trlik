package mediosDePago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BilleteraVirtualCryptoTest {

    BilleteraVirtualCrypto billetera;
    BilleteraCryptoAPI api;

    //Set up
    @BeforeEach
    public void setUp() {
        api = mock(BilleteraCryptoAPI.class);
        billetera = new BilleteraVirtualCrypto(api, "usuario", "vendedor");
    }

    //Testing
    @Test
    public void procesarPagoExitosoTest() {
        //Stubbing del comportamiento esperado
        when(api.consultarSaldoSuficiente("usuario", 50.0)).thenReturn(true);
        when(api.bloquearSaldo("usuario", 50.0)).thenReturn(true);
        when(api.acreditarAlVendedor("usuario", "vendedor", 50.0)).thenReturn(true);
        //Exercise
        billetera.procesarPago(50.0);
        //Verify (Mockito)
        InOrder orden = inOrder(api);
        orden.verify(api).consultarSaldoSuficiente("usuario", 50.0);
        orden.verify(api).bloquearSaldo("usuario", 50.0);
        orden.verify(api).acreditarAlVendedor("usuario", "vendedor", 50.0);
        orden.verify(api).enviarNotificacionPush("usuario", "Pago confirmado por un monto de $50.0");
    }

    @Test
    public void procesarPagoExitosoNoGuardaComprobanteTest() {
        //Stubbing del comportamiento esperado
        when(api.consultarSaldoSuficiente(anyString(), anyDouble())).thenReturn(true);
        when(api.bloquearSaldo(anyString(), anyDouble())).thenReturn(true);
        when(api.acreditarAlVendedor(anyString(), anyString(), anyDouble())).thenReturn(true);
        //Exercise
        billetera.procesarPago(50.0);
        //Verify (Assert)
        assertTrue(billetera.comprobantes.isEmpty());
    }

    @Test
    public void procesarPagoConSaldoInsuficienteTest() {
        //Stubbing del comportamiento esperado
        when(api.consultarSaldoSuficiente(anyString(), anyDouble())).thenReturn(false);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> billetera.procesarPago(50.0));
        //Verify (Assert)
        assertEquals("La billetera virtual posee saldo insuficiente", error.getMessage());
        //Verify (Mockito)
        verify(api, never()).bloquearSaldo(anyString(), anyDouble());
        verify(api, never()).acreditarAlVendedor(anyString(), anyString(), anyDouble());
        verify(api, never()).enviarNotificacionPush(anyString(), anyString());
    }

    @Test
    public void procesarPagoSinPoderBloquearSaldoTest() {
        //Stubbing del comportamiento esperado
        when(api.consultarSaldoSuficiente(anyString(), anyDouble())).thenReturn(true);
        when(api.bloquearSaldo(anyString(), anyDouble())).thenReturn(false);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> billetera.procesarPago(50.0));
        //Verify (Assert)
        assertEquals("No se pudo bloquear el saldo", error.getMessage());
        //Verify (Mockito)
        verify(api, never()).acreditarAlVendedor(anyString(), anyString(), anyDouble());
        verify(api, never()).enviarNotificacionPush(anyString(), anyString());
    }

    @Test
    public void procesarPagoSinPoderAcreditarAlVendedorTest() {
        //Stubbing del comportamiento esperado
        when(api.consultarSaldoSuficiente(anyString(), anyDouble())).thenReturn(true);
        when(api.bloquearSaldo(anyString(), anyDouble())).thenReturn(true);
        when(api.acreditarAlVendedor(anyString(), anyString(), anyDouble())).thenReturn(false);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> billetera.procesarPago(50.0));
        //Verify (Assert)
        assertEquals("No se pudo acreditar con el vendedor", error.getMessage());
        //Verify (Mockito)
        verify(api, never()).enviarNotificacionPush(anyString(), anyString());
    }
}
