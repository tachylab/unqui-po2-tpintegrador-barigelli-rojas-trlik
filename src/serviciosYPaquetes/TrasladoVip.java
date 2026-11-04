package serviciosYPaquetes;
import java.math.BigDecimal;

public class TrasladoVip implements TipoDeTraslado {
    
    //atributos
    private int precioTotal;
    
    //constructor
    public TrasladoVip(int precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    //accessors
    @Override 
    public BigDecimal calcularCosto() {
        float costo = new LibreriaTrasladoVip.calcularCosto((float) precioTotal);
        return BigDecimal.valueOf(costo);
    }

}