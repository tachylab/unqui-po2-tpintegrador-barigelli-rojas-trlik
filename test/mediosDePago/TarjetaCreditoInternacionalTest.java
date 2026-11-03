package mediosDePago;

import comprobantes.ComprobantePago;
import comprobantes.VoucherTarjeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TarjetaCreditoInternacionalTest {

    TarjetaCreditoInternacional tarjeta;
    TarjetaCreditoAPI api;

    //Set up
    @BeforeEach
    public void setUp() {
        api = mock(TarjetaCreditoAPI.class);
        tarjeta = new TarjetaCreditoInternacional(api, "4111111111111111", "123", "12/30");
    }

    //Testing
    @Test
    public void procesarPagoExitosoTest() {
        //Stubbing del comportamiento esperado
        when(api.validarTarjeta("4111111111111111", "123", "12/30")).thenReturn(true);
        when(api.preAutorizar("4111111111111111", 200.0)).thenReturn(true);
        when(api.debitar("4111111111111111", 200.0)).thenReturn("AUT-001");
        //Exercise
        tarjeta.procesarPago(200.0);
        //Verify (Mockito)
        InOrder orden = inOrder(api);
        orden.verify(api).validarTarjeta("4111111111111111", "123", "12/30");
        orden.verify(api).preAutorizar("4111111111111111", 200.0);
        orden.verify(api).debitar("4111111111111111", 200.0);
    }

    @Test
    public void procesarPagoExitosoGeneraUnVoucherTest() {
        //Stubbing del comportamiento esperado
        when(api.validarTarjeta(anyString(), anyString(), anyString())).thenReturn(true);
        when(api.preAutorizar(anyString(), anyDouble())).thenReturn(true);
        when(api.debitar(anyString(), anyDouble())).thenReturn("AUT-001");
        //Exercise
        tarjeta.procesarPago(200.0);
        //Verify (Assert)
        assertEquals(1, tarjeta.comprobantes.size());
        ComprobantePago comprobante = tarjeta.comprobantes.get(0);
        VoucherTarjeta voucher = (VoucherTarjeta) comprobante;
        assertEquals(200.0, voucher.getMonto());
        assertEquals("AUT-001", voucher.getCodigoAutorizacion());
        assertEquals("1111", voucher.getUltimosDigitosTarjeta());
    }

    @Test
    public void procesarPagoConTarjetaInvalidaTest() {
        //Stubbing del comportamiento esperado
        when(api.validarTarjeta(anyString(), anyString(), anyString())).thenReturn(false);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> tarjeta.procesarPago(200.0));
        //Verify (Assert)
        assertEquals("La tarjeta de crédito no es valida", error.getMessage());
        assertTrue(tarjeta.comprobantes.isEmpty());
        //Verify (Mockito)
        verify(api, never()).preAutorizar(anyString(), anyDouble());
        verify(api, never()).debitar(anyString(), anyDouble());
    }

    @Test
    public void procesarPagoSinPoderReservarFondosTest() {
        //Stubbing del comportamiento esperado
        when(api.validarTarjeta(anyString(), anyString(), anyString())).thenReturn(true);
        when(api.preAutorizar(anyString(), anyDouble())).thenReturn(false);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> tarjeta.procesarPago(200.0));
        //Verify (Assert)
        assertEquals("No se pudo reservar el monto en la tarjeta", error.getMessage());
        assertTrue(tarjeta.comprobantes.isEmpty());
        //Verify (Mockito)
        verify(api, never()).debitar(anyString(), anyDouble());
    }

    @Test
    public void procesarPagoConDebitoFallidoTest() {
        //Stubbing del comportamiento esperado
        when(api.validarTarjeta(anyString(), anyString(), anyString())).thenReturn(true);
        when(api.preAutorizar(anyString(), anyDouble())).thenReturn(true);
        when(api.debitar(anyString(), anyDouble())).thenReturn(null);
        //Exercise
        RuntimeException error = assertThrows(RuntimeException.class, () -> tarjeta.procesarPago(200.0));
        //Verify (Assert)
        assertEquals("No se pudo realizar el debito", error.getMessage());
        assertTrue(tarjeta.comprobantes.isEmpty());
        //Verify (Mockito)
        verify(api).debitar("4111111111111111", 200.0);
    }
}
