package notificaciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Reservas.Reserva;
import Reservas.EnCurso;
import Reservas.Confirmada;
import Reservas.Finalizada;
import Reservas.EnCheckin;
import Reservas.Cancelada;
import Reservas.SolicitudPendiente;
import static org.mockito.Mockito.*;

class GeneradorComprobantesTest {

    private GeneradorComprobantes observador;
    private GeneradorDocumentos generadorMock;
    private Reserva reserva;

    @BeforeEach
    void setUp() {generadorMock = mock(GeneradorDocumentos.class);observador = new GeneradorComprobantes(generadorMock);reserva = new Reserva();}

    @Test
    void generaComprobantesCuandoFinaliza() {

        observador.cambioDeEstado(reserva, new EnCurso(), new Finalizada());

        verify(generadorMock).generarVoucherYFactura(reserva);
    }

    @Test
    void noGeneraComprobantesCuandoSeConfirma() {

        observador.cambioDeEstado(reserva, new SolicitudPendiente(), new Confirmada());

        verifyNoInteractions(generadorMock);
    }

    @Test
    void noGeneraComprobantesCuandoSeCancela() {

        observador.cambioDeEstado(reserva, new EnCurso(), new Cancelada());

        verifyNoInteractions(generadorMock);
    }

    @Test
    void noGeneraComprobantesCuandoInicia() {
        observador.cambioDeEstado(reserva, new EnCheckin(), new EnCurso());

        verifyNoInteractions(generadorMock);
    }
}