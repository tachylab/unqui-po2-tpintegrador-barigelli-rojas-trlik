package serviciosYPaquetes;
import java.math.BigDecimal;

public  class TrasladoEstandar implements TipoDeTraslado {
    
    //atributos
    private int cantPasajeros;
    private String direccionDestino;
    
    //constructor
    public TrasladoEstandar(int cantPasajeros, String direccionDestino) {
        this.cantPasajeros = cantPasajeros;
        this.direccionDestino = direccionDestino;
    }

    //accessors
    public BigDecimal calcularCosto() {
      float costo = PlataformaTransporte.estimarTraslado(cantPasajeros, direccionDestino);
      return BigDecimal.valueOf(costo);
    }
}