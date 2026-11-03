package mediosDePago;

import comprobantes.ComprobantePago;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedioDePagoTest {

    MedioDePago medioDePago;
    ComprobantePago comprobante;

    //Set up
    @BeforeEach
    public void setUp() {
        comprobante = mock(ComprobantePago.class);
        //Spy sobre una subclase mínima para poder verificar el Template Method
        medioDePago = spy(new MedioDePago() {
            @Override
            protected void validarDatos(double monto) {}

            @Override
            protected void reservarFondos(double monto) {}

            @Override
            protected ComprobantePago ejecutarTransaccion(double monto) {
                return comprobante;
            }
        });
    }

    //Testing
    @Test
    public void sinComprobantesInicialmenteTest() {
        //Verify (Assert)
        assertTrue(medioDePago.comprobantes.isEmpty());
    }

    @Test
    public void añadirComprobanteTest() {
        //Exercise
        medioDePago.añadirComprobante(comprobante);
        //Verify (Assert)
        assertEquals(1, medioDePago.comprobantes.size());
        assertSame(comprobante, medioDePago.comprobantes.get(0));
    }

    @Test
    public void procesarPagoEjecutaLosPasosEnOrdenTest() {
        //Exercise
        medioDePago.procesarPago(100.0);
        //Verify (Mockito)
        InOrder orden = inOrder(medioDePago);
        orden.verify(medioDePago).validarDatos(100.0);
        orden.verify(medioDePago).reservarFondos(100.0);
        orden.verify(medioDePago).ejecutarTransaccion(100.0);
        orden.verify(medioDePago).notificarResultado(100.0, comprobante);
    }

    @Test
    public void procesarPagoGuardaElComprobanteTest() {
        //Exercise
        medioDePago.procesarPago(100.0);
        //Verify (Assert)
        assertTrue(medioDePago.comprobantes.contains(comprobante));
        //Verify (Mockito)
        verify(medioDePago).añadirComprobante(comprobante);
    }

    @Test
    public void procesarPagoSeCortaSiFallaLaValidacionTest() {
        //Stubbing del comportamiento esperado
        doThrow(new RuntimeException("Datos inválidos")).when(medioDePago).validarDatos(100.0);
        //Verify (Assert)
        assertThrows(RuntimeException.class, () -> medioDePago.procesarPago(100.0));
        assertTrue(medioDePago.comprobantes.isEmpty());
        //Verify (Mockito)
        verify(medioDePago, never()).reservarFondos(anyDouble());
        verify(medioDePago, never()).ejecutarTransaccion(anyDouble());
    }
}
