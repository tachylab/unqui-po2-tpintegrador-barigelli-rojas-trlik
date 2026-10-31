package serviciosYPaquetes;

public abstract class ItemCatalogo {
    //Atributos
    protected String id;
    protected String nombre;
    protected String proveedor;

    //Constructor
    protected  ItemCatalogo(String id, String nombre, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
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

    public abstract double precioBase();

    public abstract double precioFinal();

    public abstract boolean esValido();
}
