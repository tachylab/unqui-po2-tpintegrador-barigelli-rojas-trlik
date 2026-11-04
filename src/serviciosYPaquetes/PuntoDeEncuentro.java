package serviciosYPaquetes;
import java.math.BigDecimal;

public class PuntoDeEncuentro implements TipoDeTraslado {
    
    //atributos posibles si quiero saber disponibilidad
    //private String puntoEncuentro;
    //private GestorGuias gestorGuias;
    
    //accessors
    @Override
    public BigDecimal calcularCosto() {
        return BigDecimal.ZERO;
    }
}
