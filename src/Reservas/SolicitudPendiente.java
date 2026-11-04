package Reservas;
import serviciosYPaquetes.ItemCatalogo;

public class SolicitudPendiente extends EstadoDeReserva {

    @Override
    public void confirmar(Reserva reserva) {
        reserva.setEstado(new Confirmada());
        // Bloquear Decremento de Cupos
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
    }

    @Override
    public void agregarServicio(Reserva reserva, ItemCatalogo item) {
        reserva.agregarItemInternamente(item);
    }

    @Override
    public void quitarServicio(Reserva reserva, ItemCatalogo item) {
        reserva.QuitarItemInternamente(item);
    }

}
