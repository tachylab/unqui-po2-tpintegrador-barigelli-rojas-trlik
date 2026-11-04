package serviciosYPaquetes;
import java.util.List;

public abstract class ItemCatalogo {
    //Atributos
    protected String id;
    protected String nombre;
    protected String proveedor;
    protected String categoria;

    //Constructor
    protected  ItemCatalogo(String id, String nombre, String proveedor, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = categoria;
    }

    //Accessors
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public abstract double precioBase();

    public abstract double precioFinal();

    public abstract boolean esValido();

    //CUPOS
    public abstract boolean tieneCupoDisponible();

    public abstract void reservarCupo();

    public abstract void liberarCupo();

    public abstract List<ServicioIndividual> obtenerServicios();

}
