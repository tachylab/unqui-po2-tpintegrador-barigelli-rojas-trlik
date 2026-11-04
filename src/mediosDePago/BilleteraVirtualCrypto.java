package mediosDePago;

import comprobantes.ComprobanteNulo;
import comprobantes.ComprobantePago;

public class BilleteraVirtualCrypto extends MedioDePago{
    //Atributos
    private BilleteraCryptoAPI api;
    private String usuario;
    private String vendedor;

    //Constructor
    public BilleteraVirtualCrypto(BilleteraCryptoAPI api,  String usuario, String vendedor) {
        this.api = api;
        this.usuario = usuario;
        this.vendedor = vendedor;
    }


    @Override
    protected void validarDatos(double monto) {
        boolean saldoSuficiente = this.api.consultarSaldoSuficiente(this.usuario, monto);
        if (!saldoSuficiente) {
            throw new RuntimeException("La billetera virtual posee saldo insuficiente");
        }
    }

    @Override
    protected void reservarFondos(double monto) {
        boolean reserva = this.api.bloquearSaldo(this.usuario, monto);
        if (!reserva) {
            throw new RuntimeException("No se pudo bloquear el saldo");
        }
    }

    @Override
    protected ComprobantePago ejecutarTransaccion(double monto) {
        boolean transferenciaExitosa = this.api.acreditarAlVendedor(this.usuario, this.vendedor, monto);
        if (!transferenciaExitosa) {
            throw new RuntimeException("No se pudo acreditar con el vendedor");
        }
        return new ComprobanteNulo(monto);
    }

    @Override
    protected void notificarResultado(double monto, ComprobantePago pago) {
        this.api.enviarNotificacionPush(this.usuario, "Pago confirmado por un monto de $" + monto);
    }

}
