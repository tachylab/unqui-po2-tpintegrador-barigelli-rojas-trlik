package notificaciones;
import Reservas.Reserva;


public interface GeneradorDocumentos {

    void generarVoucherYFactura(Reserva reserva);
}