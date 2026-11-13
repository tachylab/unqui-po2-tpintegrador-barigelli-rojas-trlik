package Reservas;

import serviciosYPaquetes.ItemCatalogo;
import java.util.ArrayList;
import java.util.List;
import serviciosYPaquetes.ItemCatalogo;
import java.util.Map;
import java.util.HashMap;
import serviciosYPaquetes.ServicioIndividual;
import serviciosYPaquetes.TrasladosYTasas;
import java.math.BigDecimal;


public class Reserva {

    //atrivutos
    private EstadoDeReserva estado;
    private List<ItemCatalogo> items;
    private Disponibilidad disponibilidad;
    private Map<Disponibilidad, Integer> cuposBloqueados = new HashMap<>();
    //atrivuto para traslados
    private TrasladosYTasas traslado;

    public Reserva() {
        this.estado = new SolicitudPendiente();
        this.items = new ArrayList<>();
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
    
//traslados y tasas
    public void asignarTraslados(TrasladosYTasas traslado) {
        this.traslado = traslado;
    }
    
    public BigDecimal calcularCostoTraslado() {
        if (traslado == null) {
            return BigDecimal.ZERO;
        }
        return traslado.calcularCosto();
    }
//
    
    
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

    public Map<Disponibilidad, Integer> obtenerCantidades() {
        Map<Disponibilidad, Integer> cantidades = new HashMap<>();
        for (ItemCatalogo item : items) {
            for (ServicioIndividual servicio : item.obtenerServicios()) {
                Disponibilidad disponibilidad = servicio.getDisponibilidad();
                cantidades.merge(disponibilidad, 1, Integer::sum);
            }
        }

        return cantidades;
    }


    public void bloquearCupos() {
        verificarQueNoEsteBloqueada();
        Map<Disponibilidad, Integer> cantidades = obtenerCantidades();
        verificarQueSePuedanBloquear(cantidades);
        reservarCantidades(cantidades);
        cuposBloqueados = new HashMap<>(cantidades);
    }

    private void verificarQueNoEsteBloqueada() {
        if (!cuposBloqueados.isEmpty()) {
            throw new OperacionInvalidaException("La reserva ya tiene cupos bloqueados");
        }
    }

    private void verificarQueSePuedanBloquear(
            Map<Disponibilidad, Integer> cantidades) {

        if (cantidades.isEmpty()) {
            throw new OperacionInvalidaException("No se puede confirmar una reserva sin servicios");
        }

        for (Map.Entry<Disponibilidad, Integer> entrada : cantidades.entrySet()) {
            if (!entrada.getKey().tieneCuposDisponibles(entrada.getValue())) {
                throw new CupoInsuficienteException("No hay suficientes cupos");
            }
        }
    }

    private void reservarCantidades(
            Map<Disponibilidad, Integer> cantidades) {

        for (Map.Entry<Disponibilidad, Integer> entrada : cantidades.entrySet()) {
            entrada.getKey().reservarCupos(entrada.getValue());
        }
    }


    public void liberarCupos() {
        verificarQueHayaCuposBloqueados();
        liberarCantidades();
        cuposBloqueados.clear();
    }

    private void verificarQueHayaCuposBloqueados() {
        if (cuposBloqueados.isEmpty()) {
            throw new OperacionInvalidaException("La reserva no tiene cupos bloqueados");
        }
    }

    private void liberarCantidades() {
        for (Map.Entry<Disponibilidad, Integer> entrada : cuposBloqueados.entrySet()) {
            entrada.getKey().liberarCupos(entrada.getValue());
        }
    }
}
