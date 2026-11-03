package serviciosYPaquetes;

import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristico extends ItemCatalogo{
    //Atributos
    private double descuento;
    private List<ItemCatalogo> items;

    //Constructores
    //Constructor 1: Inicializar con la lista vacía
    public PaqueteTuristico(String id, String nombre, String proveedor, String categoria, double descuento) {
        this(id, nombre, proveedor, categoria, descuento, new ArrayList<>());
    }

    //Constructor 2: Inicializar con la lista dada
    public PaqueteTuristico(String id, String nombre, String proveedor, String categoria, double descuento, List<ItemCatalogo> items) {
        super(id, nombre, proveedor, categoria);
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
}
