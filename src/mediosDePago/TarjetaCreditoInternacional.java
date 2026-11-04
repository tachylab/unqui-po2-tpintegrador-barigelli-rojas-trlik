package mediosDePago;

import comprobantes.ComprobantePago;
import comprobantes.VoucherTarjeta;

public class TarjetaCreditoInternacional extends MedioDePago {
    //Atributos
    private TarjetaCreditoAPI api;
    private String numeroTarjeta;
    private String ccv;
    private String vencimiento;

    //Constructor
    public TarjetaCreditoInternacional(TarjetaCreditoAPI api, String numeroTarjeta, String ccv, String vencimiento) {
        this.api = api;
        this.numeroTarjeta = numeroTarjeta;
        this.ccv = ccv;
        this.vencimiento = vencimiento;
    }

    @Override
    protected void validarDatos(double monto) {
        boolean esValida = this.api.validarTarjeta(this.numeroTarjeta, this.ccv, this.vencimiento);
        if (!esValida) {
            throw new RuntimeException("La tarjeta de crédito no es valida");
        }
    }

    @Override
    protected void reservarFondos(double monto) {
        boolean reserva = this.api.preAutorizar(this.numeroTarjeta, monto);
        if (!reserva) {
            throw new RuntimeException("No se pudo reservar el monto en la tarjeta");
        }
    }

    @Override
    protected ComprobantePago ejecutarTransaccion(double monto) {
        String codigoAutorizacion = this.api.debitar(this.numeroTarjeta, monto);
        if (codigoAutorizacion == null) {
            throw new RuntimeException("No se pudo realizar el debito");
        }
        return new VoucherTarjeta(monto, codigoAutorizacion, this.numeroTarjeta.substring(this.numeroTarjeta.length() - 4));
    }
}
