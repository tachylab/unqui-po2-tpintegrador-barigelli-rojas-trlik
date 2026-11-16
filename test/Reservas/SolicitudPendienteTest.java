package Reservas;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import org.mockito.InOrder;
import serviciosYPaquetes.ItemCatalogo;




public class SolicitudPendienteTest {

    private SolicitudPendiente estado;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estado = new SolicitudPendiente();
        reservaMock = mock(Reserva.class);
    }

    @Test
    void confirmarBloqueaCuposYCambiaEstado() {
        estado.confirmar(reservaMock);
        InOrder orden = inOrder(reservaMock);
        orden.verify(reservaMock).bloquearCupos();
        orden.verify(reservaMock).setEstado(isA(Confirmada.class));
    }

    @Test
    void siFallaElBloqueoNoCambiaAConfirmada() {
        doThrow(new CupoInsuficienteException("No hay cupos")).when(reservaMock).bloquearCupos();
        assertThrows(CupoInsuficienteException.class, () -> estado.confirmar(reservaMock));
        verify(reservaMock, never()).setEstado(any(EstadoDeReserva.class));
    }

    @Test
    void cancelarCambiaElEstadoACancelada() {
        estado.cancelar(reservaMock);
        verify(reservaMock).setEstado(isA(Cancelada.class));
        verify(reservaMock, never()).liberarCupos();
        verify(reservaMock, never()).reembolsarServiciosYTasas();
        verify(reservaMock, never()).reembolsarServiciosNoDisfrutados();
    }

    @Test
    void agregarServicioPermiteAgregarUnItem() {
        ItemCatalogo itemMock = mock(ItemCatalogo.class);
        estado.agregarServicio(reservaMock, itemMock);
        verify(reservaMock).agregarItemInternamente(itemMock);
    }

    @Test
    void quitarServicioPermiteEliminarUnItem() {
        ItemCatalogo itemMock = mock(ItemCatalogo.class);
        estado.quitarServicio(reservaMock, itemMock);
        verify(reservaMock).quitarItemInternamente(itemMock);
    }


    @Test
    void confirmarReservaPendiente() {
        Reserva reserva = mock(Reserva.class);
        SolicitudPendiente estado = new SolicitudPendiente();
        estado.confirmar(reserva);
        verify(reserva).bloquearCupos();
        verify(reserva).setEstado(isA(Confirmada.class));
    }

    @Test
    void noPermiteIniciarReservaPendiente() {
        Reserva reserva = mock(Reserva.class);
        SolicitudPendiente estado = new SolicitudPendiente();
        assertThrows(OperacionInvalidaException.class, () -> estado.iniciar(reserva));
    }

    @Test
    void cancelarReservaPendiente() {
        estado.cancelar(reservaMock);
        verify(reservaMock).setEstado(isA(Cancelada.class));
        verify(reservaMock, never()).liberarCupos();
    }

    @Test
    void agregarServicioEnReservaPendiente() {
        ItemCatalogo item = mock(ItemCatalogo.class);
        estado.agregarServicio(reservaMock, item);
        verify(reservaMock).agregarItemInternamente(item);
    }

    @Test
    void quitarServicioEnReservaPendiente() {
        ItemCatalogo item = mock(ItemCatalogo.class);
        estado.quitarServicio(reservaMock, item);
        verify(reservaMock).quitarItemInternamente(item);
    }

    @Test
    void noPermiteHacerCheckin() {
        assertThrows(OperacionInvalidaException.class, () -> estado.hacerCheckin(reservaMock));
    }

    @Test
    void noPermiteIniciar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.iniciar(reservaMock));
    }

    @Test
    void noPermiteFinalizar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.finalizar(reservaMock));
    }
}