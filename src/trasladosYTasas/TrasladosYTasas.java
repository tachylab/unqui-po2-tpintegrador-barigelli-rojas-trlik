package trasladosYTasas;
import java.math.BigDecimal;

public class TrasladosYTasas {
    //atrivutos
    private TipoDeTraslado metodoDeTraslado;
    
    //constructor
    public TrasladosYTasas(TipoDeTraslado metodo) {
        this.metodoDeTraslado = metodo;
    }
    
    //accessors
    public BigDecimal calcularCosto() {
        return metodoDeTraslado.calcularCosto();
    }
    
}

