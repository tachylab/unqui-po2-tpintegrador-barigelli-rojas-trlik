package notificaciones;

import Reservas.Reserva;
import Reservas.EstadoDeReserva;
import Reservas.Cancelada;

public class ClubViajeros implements ObservadorReserva {

    private MailSender mailSender;

    public ClubViajeros(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void cambioDeEstado(Reserva reserva, EstadoDeReserva anterior, EstadoDeReserva nuevo) {

        if (nuevo instanceof Cancelada) {
            mailSender.enviarMail(reserva.getEmailCliente(), "Club de Viajeros - Descuento", "Lamentamos la cancelación de tu reserva. " + "Te ofrecemos un cupón de descuento del 5% " + "para tu próxima reserva.", null);
        }
    }
}