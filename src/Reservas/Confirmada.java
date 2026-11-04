package Reservas;

public class Confirmada extends EstadoDeReserva{


    @Override
    public void hacerCheckin(Reserva reserva) {
        reserva.setEstado(new EnCheckin());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.liberarCupos();
        reserva.setEstado(new Cancelada());
    }

}
