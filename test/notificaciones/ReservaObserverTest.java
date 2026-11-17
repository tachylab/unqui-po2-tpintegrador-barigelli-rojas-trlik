package notificaciones;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Reservas.Reserva;
import Reservas.Confirmada;
import Reservas.SolicitudPendiente;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaObserverTest {

    private Reserva reserva;
    private ObservadorReserva observadorMock;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        observadorMock = mock(ObservadorReserva.class);

        reserva.agregarObservador(observadorMock);
    }

    @Test
    void notificaCuandoCambiaDeEstado() {
        reserva.setEstado(new Confirmada());
        verify(observadorMock).cambioDeEstado(eq(reserva), isA(SolicitudPendiente.class), isA(Confirmada.class));
    }

    @Test
    void notificaATodosLosObservadores() {
        ObservadorReserva segundoObservador = mock(ObservadorReserva.class);

        reserva.agregarObservador(segundoObservador);
        reserva.setEstado(new Confirmada());

        verify(observadorMock).cambioDeEstado(eq(reserva), isA(SolicitudPendiente.class), isA(Confirmada.class));
        verify(segundoObservador).cambioDeEstado(eq(reserva), isA(SolicitudPendiente.class), isA(Confirmada.class));
    }

    @Test
    void noNotificaAUnObservadorEliminado() {
        reserva.quitarObservador(observadorMock);
        reserva.setEstado(new Confirmada());

        verifyNoInteractions(observadorMock);
    }

    @Test
    void conservaElNuevoEstadoLuegoDeNotificar() {
        reserva.setEstado(new Confirmada());

        assertInstanceOf(Confirmada.class, reserva.getEstado());
    }
}