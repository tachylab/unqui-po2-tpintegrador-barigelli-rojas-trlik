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

class NotificadorEmailTest {

    private NotificadorEmail notificador;
    private MailSender mailSenderMock;
    private Reserva reserva;

    @BeforeEach
    void setUp() {
        mailSenderMock = mock(MailSender.class);
        notificador = new NotificadorEmail(mailSenderMock);

        reserva = new Reserva();
        reserva.establecerEmailCliente("cliente@gmail.com");
    }

    @Test
    void enviaMailCuandoSeConfirma() {
        notificador.cambioDeEstado(reserva, new SolicitudPendiente(), new Confirmada());

        verify(mailSenderMock).enviarMail(eq("cliente@gmail.com"), eq("Cambio de estado de reserva"), contains("Confirmada"), isNull());
    }

    @Test
    void enviaMailCuandoPasaAEnCurso() {
        notificador.cambioDeEstado(reserva, new EnCheckin(), new EnCurso());

        verify(mailSenderMock).enviarMail(eq("cliente@gmail.com"), eq("Cambio de estado de reserva"), contains("EnCurso"), isNull());
    }

    @Test
    void enviaMailCuandoFinaliza() {
        notificador.cambioDeEstado(reserva, new EnCurso(), new Finalizada());

        verify(mailSenderMock).enviarMail(eq("cliente@gmail.com"), eq("Cambio de estado de reserva"), contains("Finalizada"), isNull());
    }

    @Test
    void noEnviaMailCuandoPasaAEnCheckin() {
        notificador.cambioDeEstado(reserva, new Confirmada(), new EnCheckin());
        verifyNoInteractions(mailSenderMock);
    }

    @Test
    void noEnviaMailCuandoSeCancela() {
        notificador.cambioDeEstado(reserva, new Confirmada(), new Cancelada());
        verifyNoInteractions(mailSenderMock);
    }
}