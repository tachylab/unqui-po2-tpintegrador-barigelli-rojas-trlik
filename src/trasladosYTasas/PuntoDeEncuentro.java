package trasladosYTasas;
import java.math.BigDecimal;

public class PuntoDeEncuentro implements TipoDeTraslado {
    
    //atributos posibles si quiero saber disponibilidad
    private String puntoEncuentro;
    private GestorGuias gestorGuias;
    
    //constructor
    public PuntoDeEncuentro(String puntoEncuentro, GestorGuias gestorGuias) {
        this.puntoEncuentro = puntoEncuentro;
        this.gestorGuias = gestorGuias;
    }
    
    //accessors
    @Override
    public BigDecimal calcularCosto() {
        return BigDecimal.ZERO;
    }
    
    public boolean hayDisponibilidad() {
        return gestorGuias.hayCupo(puntoEncuentro);
    }
}
