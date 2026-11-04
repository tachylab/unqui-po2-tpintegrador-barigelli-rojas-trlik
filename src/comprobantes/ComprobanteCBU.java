package comprobantes;

public class ComprobanteCBU extends ComprobantePago{
    //Atributos
    private String cbuOrigen;
    private String cbuDestino;
    private String numeroOperacion;

    //Constructor
    public ComprobanteCBU(double monto, String cbuOrigen, String cbuDestino, String numeroOperacion) {
        super(monto);
        this.cbuOrigen = cbuOrigen;
        this.cbuDestino = cbuDestino;
        this.numeroOperacion = numeroOperacion;
    }

    //Getters
    public String getCbuOrigen() {
        return this.cbuOrigen;
    }

    public String getCbuDestino() {
        return this.cbuDestino;
    }

    public String getNumeroOperacion() {
        return this.numeroOperacion;
    }
}
