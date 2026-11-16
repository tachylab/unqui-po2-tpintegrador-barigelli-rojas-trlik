package trasladosYTasas;
import java.math.BigDecimal;

public  class TrasladoEstandar implements TipoDeTraslado {
    
    //atributos
    private PlataformaTransporte transporte;
    private int cantPasajeros;
    private String direccionDestino;
    
    //constructor
    public TrasladoEstandar(PlataformaTransporte transporte, int cantPasajeros, String direccionDestino) {
        this.transporte = transporte;
        this.cantPasajeros = cantPasajeros;
        this.direccionDestino = direccionDestino;
    }

    //accessors
    public BigDecimal calcularCosto() {
      float costo = transporte.estimarTraslado(cantPasajeros, direccionDestino);
      return BigDecimal.valueOf(costo);
    }
}
