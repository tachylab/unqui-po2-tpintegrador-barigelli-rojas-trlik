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

class ClubViajerosTest {

    private ClubViajeros club;
    private MailSender mailSenderMock;
    private Reserva reserva;

    @BeforeEach
    void setUp() {
        mailSenderMock = mock(MailSender.class);
        club = new ClubViajeros(mailSenderMock);
        reserva = new Reserva();
        reserva.establecerEmailCliente("cliente@gmail.com");
    }

    @Test
    void enviaCuponCuandoSeCancela() {
        club.cambioDeEstado(reserva, new Confirmada(), new Cancelada());

        verify(mailSenderMock).enviarMail(eq("cliente@gmail.com"), eq("Club de Viajeros - Descuento"), contains("5%"), isNull());
    }

    @Test
    void noEnviaCuponCuandoSeConfirma() {
        club.cambioDeEstado(reserva, new SolicitudPendiente(), new Confirmada());

        verifyNoInteractions(mailSenderMock);
    }

    @Test
    void noEnviaCuponCuandoInicia() {
        club.cambioDeEstado(reserva, new EnCheckin(), new EnCurso());

        verifyNoInteractions(mailSenderMock);
    }

    @Test
    void noEnviaCuponCuandoFinaliza() {
        club.cambioDeEstado(reserva, new EnCurso(), new Finalizada());

        verifyNoInteractions(mailSenderMock);
    }

    @Test
    void noEnviaCuponCuandoPasaAEnCheckin() {
        club.cambioDeEstado(reserva, new Confirmada(), new EnCheckin());

        verifyNoInteractions(mailSenderMock);
    }
}