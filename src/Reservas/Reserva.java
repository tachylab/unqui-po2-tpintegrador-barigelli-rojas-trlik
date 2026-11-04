package Reservas;

import serviciosYPaquetes.ItemCatalogo;
import java.util.ArrayList;
import java.util.List;
import serviciosYPaquetes.ItemCatalogo;


public class Reserva {

    private EstadoDeReserva estado;
    private List<ItemCatalogo> items;
    private Disponibilidad disponibilidad;

    public Reserva(Disponibilidad disponibilidad) {
        this.estado = new SolicitudPendiente();
        this.items = new ArrayList<>();
        this.disponibilidad = disponibilidad;
    }

    public void setEstado(EstadoDeReserva nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void confirmar() {
        this.estado.confirmar(this);
    }

    public void cancelar() {
        this.estado.cancelar(this);
    }

    public void finalizar() {
        this.estado.finalizar(this);
    }

    public void hacerCheckin() {
        this.estado.hacerCheckin(this);
    }

    public void iniciar() {
        this.estado.iniciar(this);
    }


    public EstadoDeReserva getEstado() {
        return this.estado;
    }

    public void agregarServicio(ItemCatalogo item) {
        estado.agregarServicio(this, item);
    }

    public void quitarServicio(ItemCatalogo item) {
        estado.quitarServicio(this, item);
    }

    void agregarItemInternamente(ItemCatalogo item) {
        items.add(item);
    }

    void QuitarItemInternamente(ItemCatalogo item) {
        items.remove(item);
    }

    public List<ItemCatalogo> getItems() {
        return List.copyOf(items);
    }
}
