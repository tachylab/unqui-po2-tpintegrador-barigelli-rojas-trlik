package Reservas;
import serviciosYPaquetes.ItemCatalogo;

public class EstadoDeReserva {

    //private List<ElementoCatalogo> items;


    public void confirmar(Reserva reserva) {
        throw new OperacionInvalidaException(
                "No se puede hacer check-in de una reserva pendiente"
        );
    }

    public void cancelar(Reserva reserva) {
        throw new OperacionInvalidaException(
                "No se puede hacer check-in de una reserva pendiente"
        );
    }

    public void hacerCheckin(Reserva reserva) {
        throw new OperacionInvalidaException(
                "No se puede hacer check-in de una reserva pendiente"
        );
    }

    public void iniciar(Reserva reserva) {
        throw new OperacionInvalidaException(
                "No se puede hacer check-in de una reserva pendiente"
        );
    }

    public void finalizar(Reserva reserva) {
        throw new OperacionInvalidaException(
                "No se puede hacer check-in de una reserva pendiente"
        );
    }


    public void agregarServicio(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalidaException(
                "No se pueden agregar servicios en este estado"
        );
    }

    public void quitarServicio(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalidaException(
                "No se pueden quitar servicios en este estado"
        );
    }


}