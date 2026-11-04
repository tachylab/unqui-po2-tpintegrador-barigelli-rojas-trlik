package comprobantes;

public class VoucherTarjeta extends ComprobantePago{
    //Atributos
    private String codigoAutorizacion;
    private String ultimosDigitosTarjeta;

    //Constructor
    public VoucherTarjeta(double monto, String codigoAutorizacion, String ultimosDigitosTarjeta) {
        super(monto);
        this.codigoAutorizacion = codigoAutorizacion;
        this.ultimosDigitosTarjeta = ultimosDigitosTarjeta;
    }

    //Getters
    public String getCodigoAutorizacion() {
        return this.codigoAutorizacion;
    }

    public String getUltimosDigitosTarjeta() {
        return this.ultimosDigitosTarjeta;
    }
}
