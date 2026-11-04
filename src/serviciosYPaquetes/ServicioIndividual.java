package serviciosYPaquetes;

import java.util.ArrayList;
import java.util.List;
import Reservas.Disponibilidad;

public class ServicioIndividual extends ItemCatalogo {

    //Atributos
    private double precioBase;
    private List<AtributoDinamico> atributosDinamicos;
    private Disponibilidad disponibilidad;
/*
    //Constructores
    //Constructor 1: Inicializar con la lista vacía
    public ServicioIndividual(String id, String nombre, String proveedor, String categoria, double precioBase) {
        this(id, nombre, proveedor, categoria, precioBase, new ArrayList<>());
    }

    //Constructor 2: Inicializar pasando la lista de atributos dinámicos
    public ServicioIndividual(String id, String nombre, String proveedor, String categoria, double precioBase, List<AtributoDinamico> atributosDinamicos) {
        super(id, nombre, proveedor, categoria);
        this.precioBase = precioBase;
        this.atributosDinamicos = atributosDinamicos;
    }*/

    public ServicioIndividual(
            String id,
            String nombre,
            String proveedor,
            double precioBase
    ) {
        this(id, nombre, proveedor, precioBase,
                new ArrayList<>(), new Disponibilidad(1));
    }

    public ServicioIndividual(
            String id,
            String nombre,
            String proveedor,
            double precioBase,
            List<AtributoDinamico> atributosDinamicos
    ) {
        this(id, nombre, proveedor, precioBase,
                atributosDinamicos, new Disponibilidad(1));
    }

    //Constructor 3: Constructor para cupos
    public ServicioIndividual(String id, String nombre, String proveedor, double precioBase, List<AtributoDinamico> atributosDinamicos, Disponibilidad disponibilidad) {
        super(id, nombre, proveedor);
        this.precioBase = precioBase;
        this.atributosDinamicos = atributosDinamicos;
        this.disponibilidad = disponibilidad;
    }

    //Funciones
    @Override
    public double precioBase() {
        return this.precioBase;
    }

    @Override
    public double precioFinal() {
        return this.precioBase;
    }

    @Override
    public boolean esValido() {
        return atributosDinamicos.stream().allMatch(AtributoDinamico::esValido);
    }

    //Cupos por Items
    public void reservarCupo() {
        disponibilidad.reservarCupo();
    }

    public void liberarCupo() {
        disponibilidad.liberarCupo();
    }

    @Override
    public boolean tieneCupoDisponible() {
        return disponibilidad.tieneCupoDisponible();
    }

    @Override
    public List<ServicioIndividual> obtenerServicios() {
        return List.of(this);
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }
}
