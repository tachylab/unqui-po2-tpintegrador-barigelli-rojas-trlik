public abstract class ItemCatalogo {
    //Atributos
    String id;
    String nombre;
    String proveedor;
    float precioBase;

    //Constructor
    protected  ItemCatalogo(String id, String nombre, String proveedor, float precioBase) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.precioBase = precioBase;
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

    public float getPrecioBase() {
        return precioBase;
    }

    public abstract float precioFinal();

    public abstract boolean esValido();
}
