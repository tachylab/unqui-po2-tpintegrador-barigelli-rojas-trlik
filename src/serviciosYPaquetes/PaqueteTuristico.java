package serviciosYPaquetes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import reportes.VisitanteReportes;


public class PaqueteTuristico extends ItemCatalogo{
    //Atributos
    private double descuento;
    private List<ItemCatalogo> items;

    //Constructores
    //Constructor 1: Inicializar con la lista vacía
    public PaqueteTuristico(String id, String nombre, String proveedor, String categoria, int cupoTotal, LocalDate fechaInicio, LocalDate fechaFin, double descuento) {
        this(id, nombre, proveedor, categoria, cupoTotal, fechaInicio, fechaFin, descuento, new ArrayList<>());
    }

    //Constructor 2: Inicializar con la lista dada
    public PaqueteTuristico(String id, String nombre, String proveedor, String categoria,int cupoTotal, LocalDate fechaInicio, LocalDate fechaFin, double descuento, List<ItemCatalogo> items) {
        super(id, nombre, proveedor, categoria, cupoTotal, fechaInicio, fechaFin);
        this.descuento = descuento / 100.0;
        this.items = items;
    }

    //Funciones
    @Override
    public double precioBase() {
        return items.stream().mapToDouble(ItemCatalogo::precioBase).sum();
    }

    public double getDescuento() {
        return descuento;
    }

    @Override
    public double precioFinal() {
        return this.precioBase() * (1.0 - this.descuento);
    }

    @Override
    public boolean esValido() {
        return items.stream().allMatch(ItemCatalogo::esValido);
    }

    //Funciones propias del Composite
    void agregarItem(ItemCatalogo item){
        items.add(item);
    }

    void eliminarItem(ItemCatalogo item){
        items.remove(item);
    }

    //Cupos
    @Override
    public boolean tieneCupoDisponible() {
        return !items.isEmpty() && items.stream().allMatch(ItemCatalogo::tieneCupoDisponible);
    }

    @Override
    public void reservarCupo() {
        items.forEach(ItemCatalogo::reservarCupo);
    }

    @Override
    public void liberarCupo() {
        items.forEach(ItemCatalogo::liberarCupo);
    }

    @Override
    public List<ServicioIndividual> obtenerServicios() {
        List<ServicioIndividual> servicios = new ArrayList<>();
        for (ItemCatalogo item : items) {
            servicios.addAll(item.obtenerServicios());
        }

        return servicios;
    }


    @Override
    public double precioNoDisfrutado(Map<ServicioIndividual, Integer> pendientes) {
        double total = items.stream().mapToDouble(item -> item.precioNoDisfrutado(pendientes)).sum();
        return total * (1.0 - descuento);
    }
    
    //Reportes
    @Override 
    public void aceptarVisistante(VisitanteReportes visitante) {
        visitante.visitar(this);
    }
}
