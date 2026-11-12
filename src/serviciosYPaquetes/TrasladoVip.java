package serviciosYPaquetes;
import java.math.BigDecimal;

public class TrasladoVip implements TipoDeTraslado {
    
    //atributos
    private LibreriaTrasladoVip vip;
    private int precioTotal;
    
    //constructor
    public TrasladoVip(LibreriaTrasladoVip vip, int precioTotal) {
        this.vip = vip;
        this.precioTotal = precioTotal;
    }
    
    //accessors
    @Override 
    public BigDecimal calcularCosto() {
        float costo = vip.calcularCosto((float) precioTotal);
        return BigDecimal.valueOf(costo);
    }

}