package Reservas;

public class EnCheckin extends EstadoDeReserva {
    @Override
    public void iniciar(Reserva reserva) {
        reserva.setEstado(new EnCurso());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.liberarCupos();
        reserva.reembolsarServiciosYTasas();
        reserva.setEstado(new Cancelada());
    }

}
