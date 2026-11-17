package notificaciones;

import Reservas.Reserva;
import Reservas.EstadoDeReserva;
import Reservas.Finalizada;


public class GeneradorComprobantes implements ObservadorReserva {

    private GeneradorDocumentos generador;

    public GeneradorComprobantes(GeneradorDocumentos generador) {
        this.generador = generador;
    }

    @Override
    public void cambioDeEstado(Reserva reserva, EstadoDeReserva anterior, EstadoDeReserva nuevo) {

        if (nuevo instanceof Finalizada) {
            generador.generarVoucherYFactura(reserva);
        }
    }
}