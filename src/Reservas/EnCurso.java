package Reservas;

public class EnCurso extends EstadoDeReserva {
    @Override
    public void finalizar(Reserva reserva) {
        reserva.setEstado(new Finalizada());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
        //Se reembolsa costo de no se que pero no de las tasas
    }

}
