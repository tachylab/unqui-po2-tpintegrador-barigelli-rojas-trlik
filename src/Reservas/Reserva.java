package Reservas;

import serviciosYPaquetes.ItemCatalogo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import serviciosYPaquetes.ServicioIndividual;
import trasladosYTasas.TrasladosYTasas;
import java.math.BigDecimal;
import notificaciones.ObservadorReserva;

public class Reserva {

    //atrivutos
    private EstadoDeReserva estado;
    private List<ItemCatalogo> items;
    private Map<Disponibilidad, Integer> cuposBloqueados = new HashMap<>();
    //atrivuto para traslados
    private TrasladosYTasas traslado;
    private double tasasReserva;
    private List<VoucherReembolso> reembolsos;
    private Map<ServicioIndividual, Integer> serviciosDisfrutados = new HashMap<>();
    private List<ObservadorReserva> observadores = new ArrayList<>();
    private String emailCliente;

    public Reserva() {
        this.estado = new SolicitudPendiente();
        this.items = new ArrayList<>();
        this.reembolsos = new ArrayList<>();
    }

    public void setEstado(EstadoDeReserva nuevoEstado) {
        EstadoDeReserva anterior = this.estado;
        this.estado = nuevoEstado;
        notificarCambioDeEstado(anterior, nuevoEstado);
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

    void quitarItemInternamente(ItemCatalogo item) {
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


    public void registrarReembolso(double monto) {
        VoucherReembolso voucher = new VoucherReembolso(monto);
        reembolsos.add(voucher);
    }

    public List<VoucherReembolso> getReembolsos() {
        return List.copyOf(reembolsos);
    }

    public double precioTotalServicios() {
        return items.stream().mapToDouble(ItemCatalogo::precioFinal).sum();
    }

    public void establecerTasasReserva(double tasas) {
        if (tasas < 0) {
            throw new OperacionInvalidaException("Las tasas de reserva no pueden ser negativas");
        }
        this.tasasReserva = tasas;
    }

    public void reembolsarServiciosYTasas() {
        double monto = precioTotalServicios() + tasasReserva;

        if (monto > 0) {
            registrarReembolso(monto);
        }
    }

    public long cantidadReservadaDe(ServicioIndividual servicio) {
        return items.stream().flatMap(item -> item.obtenerServicios().stream()).filter(s -> s == servicio).count();
    }

    private void verificarQueEsteEnCurso() {
        if (!(estado instanceof EnCurso)) {
            throw new OperacionInvalidaException("Solo se pueden registrar servicios disfrutados en EnCurso");
        }
    }

    public void registrarServicioDisfrutado(ServicioIndividual servicio) {
        verificarQueEsteEnCurso();

        long cantidadReservada = cantidadReservadaDe(servicio);
        int cantidadDisfrutada = serviciosDisfrutados.getOrDefault(servicio, 0);

        if (cantidadDisfrutada >= cantidadReservada) {
            throw new OperacionInvalidaException("No hay unidades pendientes de disfrutar de este servicio");
        }

        serviciosDisfrutados.merge(servicio, 1, Integer::sum);
    }

    public int cantidadDisfrutadaDe(ServicioIndividual servicio) {
        return serviciosDisfrutados.getOrDefault(servicio, 0);
    }


    private Map<ServicioIndividual, Integer> cantidadesReservadas() {
        Map<ServicioIndividual, Integer> cantidades = new HashMap<>();

        for (ItemCatalogo item : items) {
            for (ServicioIndividual servicio : item.obtenerServicios()) {
                cantidades.merge(servicio, 1, Integer::sum);
            }
        }

        return cantidades;
    }


    private Map<ServicioIndividual, Integer> cantidadesNoDisfrutadas() {
        Map<ServicioIndividual, Integer> pendientes = cantidadesReservadas();
        serviciosDisfrutados.forEach((servicio, cantidad) -> pendientes.merge(servicio, -cantidad, Integer::sum));
        return pendientes;
    }

    public double precioServiciosNoDisfrutados() {
        Map<ServicioIndividual, Integer> pendientes = cantidadesNoDisfrutadas();

        return items.stream().mapToDouble(item -> item.precioNoDisfrutado(pendientes)).sum();
    }



    public void reembolsarServiciosNoDisfrutados() {
        double importe = precioServiciosNoDisfrutados();

        if (importe > 0) {
            registrarReembolso(importe);
        }
    }


    public void agregarObservador(ObservadorReserva observador) {
        observadores.add(observador);
    }

    public void quitarObservador(ObservadorReserva observador) {
        observadores.remove(observador);
    }

    private void notificarCambioDeEstado(EstadoDeReserva anterior, EstadoDeReserva nuevo) {
        for (ObservadorReserva observador : observadores) {
            observador.cambioDeEstado(this, anterior, nuevo);
        }
    }

    public void establecerEmailCliente(String email) {
        this.emailCliente = email;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

}
