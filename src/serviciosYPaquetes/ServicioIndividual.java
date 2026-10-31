package serviciosYPaquetes;

import java.util.ArrayList;
import java.util.List;

public class ServicioIndividual extends ItemCatalogo {

    //Atributos
    private double precioBase;
    private List<AtributoDinamico> atributosDinamicos;

    //Constructores
    //Constructor 1: Inicializar con la lista vacía
    public ServicioIndividual(String id, String nombre, String proveedor, double precioBase) {
        this(id, nombre, proveedor, precioBase, new ArrayList<>());
    }

    //Constructor 2: Inicializar pasando la lista de atributos dinámicos
    public ServicioIndividual(String id, String nombre, String proveedor, double precioBase, List<AtributoDinamico> atributosDinamicos) {
        super(id, nombre, proveedor);
        this.precioBase = precioBase;
        this.atributosDinamicos = atributosDinamicos;
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
}
