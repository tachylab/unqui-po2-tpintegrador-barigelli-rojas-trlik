package Reservas;

public class EnCheckin extends EstadoDeReserva {
    @Override
    public void iniciar(Reserva reserva) {
        reserva.setEstado(new EnCurso());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
        //Se libera cupo y se reembolsa tanto el costo como las tasas
    }

}
