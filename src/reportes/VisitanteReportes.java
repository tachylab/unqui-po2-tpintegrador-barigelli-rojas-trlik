package reportes;
import serviciosYPaquetes.ServicioIndividual;
import serviciosYPaquetes.PaqueteTuristico;

public interface VisitanteReportes {

    void visitar(ServicioIndividual servicio);

    void visitar(PaqueteTuristico paquete);

}
