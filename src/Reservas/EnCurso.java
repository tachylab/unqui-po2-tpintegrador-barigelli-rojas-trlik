package Reservas;

public class EnCurso extends EstadoDeReserva {
    @Override
    public void finalizar(Reserva reserva) {
        reserva.setEstado(new Finalizada());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.reembolsarServiciosNoDisfrutados();
        reserva.setEstado(new Cancelada());
    }

}
