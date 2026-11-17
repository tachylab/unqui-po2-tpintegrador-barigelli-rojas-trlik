package notificaciones;

import Reservas.Reserva;
import Reservas.EstadoDeReserva;
import Reservas.EnCurso;
import Reservas.Confirmada;
import Reservas.Finalizada;


public class NotificadorEmail implements ObservadorReserva {

    private MailSender mailSender;

    public NotificadorEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void cambioDeEstado(Reserva reserva, EstadoDeReserva anterior, EstadoDeReserva nuevo) {

        if (nuevo instanceof Confirmada || nuevo instanceof EnCurso || nuevo instanceof Finalizada) {
            mailSender.enviarMail(reserva.getEmailCliente(), "Cambio de estado de reserva", "Tu reserva ahora está en estado: " + nuevo.getClass().getSimpleName(), null);
        }
    }
}