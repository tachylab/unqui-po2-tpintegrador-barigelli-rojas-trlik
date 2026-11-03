package mediosDePago;

import comprobantes.ComprobanteCBU;
import comprobantes.ComprobantePago;

public class TransferenciaBancariaDirecta extends MedioDePago {
    //Atributos
    private TransferenciaAPI api;
    private String cbuOrigen;
    private String cbuDestino;
    private String alias;

    //Constructor
    public TransferenciaBancariaDirecta(TransferenciaAPI api, String cbuOrigen, String cbuDestino, String alias) {
        this.api = api;
        this.cbuOrigen = cbuOrigen;
        this.cbuDestino = cbuDestino;
        this.alias = alias;
    }

    @Override
    protected void validarDatos(double monto) {
        boolean esValido = this.api.validarCBU(this.cbuOrigen, this.alias);
        if (!esValido) {
            throw new RuntimeException("CBU/CVU o alias inválido.");
        }
    }

    @Override
    protected void reservarFondos(double monto) {
        //No aplica, se hace una transferencia directa
    }

    @Override
    protected ComprobantePago ejecutarTransaccion(double monto) {
        String nroOperacion = this.api.transferir(this.cbuOrigen, this.cbuDestino, monto);
        if (nroOperacion == null) {
            throw new RuntimeException("La transferencia no se pudo concretar.");
        }
        return new ComprobanteCBU(monto, this.cbuOrigen, this.cbuDestino, nroOperacion);

    }
}
