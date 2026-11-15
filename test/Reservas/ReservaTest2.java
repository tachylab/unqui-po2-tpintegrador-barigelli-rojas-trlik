package Reservas;

import java.math.BigDecimal;
import trasladosYTasas.TrasladosYTasas;
import serviciosYPaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaTest2 {

    Reserva reserva;
    Reserva reservaMock;
    ItemCatalogo itemMock;
    Cancelada cancelada;
    Finalizada finalizada;

    // Set up
    @BeforeEach
    public void setUp() {
        reserva = new Reserva();
        reservaMock = mock(Reserva.class);
        itemMock = mock(ItemCatalogo.class);
        cancelada = new Cancelada();
        finalizada = new Finalizada();
    }

    // Testing
    @Test
    public void calcularCostoTrasladoSinTrasladoAsignadoTest() {
        // Verify (Assert)
        assertEquals(BigDecimal.ZERO, reserva.calcularCostoTraslado());
    }

    @Test
    public void calcularCostoTrasladoConTrasladoAsignadoTest() {
        // Setup
        TrasladosYTasas traslado = mock(TrasladosYTasas.class);

        // Stubbing del comportamiento esperado
        BigDecimal costoEsperado = new BigDecimal("3500.50");
        when(traslado.calcularCosto()).thenReturn(costoEsperado);

        reserva.asignarTraslados(traslado);

        // Verify (Assert)
        assertEquals(costoEsperado, reserva.calcularCostoTraslado());

        // Verify (Mockito)
        verify(traslado).calcularCosto();
    }

    // Estado Cancelada
    @Test
    public void canceladaEsUnEstadoDeReservaTest() {
        // Verify (Assert)
        assertInstanceOf(EstadoDeReserva.class, cancelada);
    }

    @Test
    public void canceladaNoPuedeConfirmarTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> cancelada.confirmar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void canceladaNoPuedeCancelarTest() {
        //  (Assert)
        assertThrows(OperacionInvalidaException.class, () -> cancelada.cancelar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void canceladaNoPuedeHacerCheckinTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> cancelada.hacerCheckin(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void canceladaNoPuedeIniciarTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> cancelada.iniciar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void canceladaNoPuedeFinalizarTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> cancelada.finalizar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void canceladaNoPuedeAgregarServicioTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> cancelada.agregarServicio(reservaMock, itemMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void canceladaNoPuedeQuitarServicioTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> cancelada.quitarServicio(reservaMock, itemMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    // Estado Finalizada
    @Test
    public void finalizadaEsUnEstadoDeReservaTest() {
        // Verify (Assert)
        assertInstanceOf(EstadoDeReserva.class, finalizada);
    }

    @Test
    public void finalizadaNoPuedeConfirmarTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> finalizada.confirmar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void finalizadaNoPuedeCancelarTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> finalizada.cancelar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void finalizadaNoPuedeHacerCheckinTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> finalizada.hacerCheckin(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void finalizadaNoPuedeIniciarTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> finalizada.iniciar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void finalizadaNoPuedeFinalizarTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> finalizada.finalizar(reservaMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void finalizadaNoPuedeAgregarServicioTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> finalizada.agregarServicio(reservaMock, itemMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }

    @Test
    public void finalizadaNoPuedeQuitarServicioTest() {
        // Verify (Assert)
        assertThrows(OperacionInvalidaException.class, () -> finalizada.quitarServicio(reservaMock, itemMock));

        // Verify (Mockito)
        verifyNoInteractions(reservaMock);
    }
}
