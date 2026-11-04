package mediosDePago;

import comprobantes.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MedioDePago {
    //Comprobantes
    protected List<ComprobantePago> comprobantes = new ArrayList<>();

    // Template Method
    public void procesarPago(double monto) {
        validarDatos(monto);
        reservarFondos(monto);
        ComprobantePago comprobante = ejecutarTransaccion(monto);
        notificarResultado(monto, comprobante);
    }

    // Abstract Methods
    protected abstract void validarDatos(double monto);

    protected abstract void reservarFondos(double monto);

    protected abstract ComprobantePago ejecutarTransaccion(double monto);

    // Hook Methods
    protected void notificarResultado(double monto, ComprobantePago comprobante) {
        añadirComprobante(comprobante);
    }

    public void añadirComprobante(ComprobantePago comprobante) {
        comprobantes.add(comprobante);
    }
}
