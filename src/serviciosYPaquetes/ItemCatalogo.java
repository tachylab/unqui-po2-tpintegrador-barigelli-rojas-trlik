package serviciosYPaquetes;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import reportes.VisitanteReportes;


public abstract class ItemCatalogo {
    //Atributos
    protected String id;
    protected String nombre;
    protected String proveedor;
    protected String categoria;
    //atributo para reportes
    protected int cupoTotal;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;


    //Constructor
    protected  ItemCatalogo(String id, String nombre, String proveedor, String categoria, int cupoTotal, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.cupoTotal = cupoTotal;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public abstract double precioNoDisfrutado(Map<ServicioIndividual, Integer> pendientes);

    //Reportes
    public abstract void aceptarVisistante(VisitanteReportes visitante);

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    public int getCupoTotal() {
        return cupoTotal;
    }
}
