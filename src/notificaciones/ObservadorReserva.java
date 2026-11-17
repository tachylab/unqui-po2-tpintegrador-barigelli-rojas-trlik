package notificaciones;

import Reservas.Reserva;
import Reservas.EstadoDeReserva;



public interface ObservadorReserva {

    void cambioDeEstado(
            Reserva reserva,
            EstadoDeReserva anterior,
            EstadoDeReserva nuevo
    );
}