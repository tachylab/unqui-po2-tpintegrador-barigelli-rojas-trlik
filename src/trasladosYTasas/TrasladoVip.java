package trasladosYTasas;
import java.math.BigDecimal;

public class TrasladoVip implements TipoDeTraslado {
    
    //atributos
    private LibreriaTrasladoVip vip;
    private float precioTotal;
    
    //constructor
    public TrasladoVip(LibreriaTrasladoVip vip, float precioTotal) {
        this.vip = vip;
        this.precioTotal = precioTotal;
    }
    
    //accessors
    @Override 
    public BigDecimal calcularCosto() {
        float costo = vip.calcularCosto(precioTotal);
        return BigDecimal.valueOf(costo);
    }

}
