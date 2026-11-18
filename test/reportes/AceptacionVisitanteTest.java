package reportes;

import org.junit.jupiter.api.Test;
import serviciosYPaquetes.ServicioIndividual;
import serviciosYPaquetes.PaqueteTuristico;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

public class AceptacionVisitanteTest {

    @Test
    public void servicioIndividualAceptaVisitanteTest() {
        ServicioIndividual servicio = new ServicioIndividual(
            "1", "Hotel", "Proveedor A", "Alojamiento",
            10, LocalDate.of(2026, 2, 1),
            LocalDate.of(2026, 2, 5), 100.0
        );

        VisitanteReportes visitante = mock(VisitanteReportes.class);

        servicio.aceptarVisistante(visitante);

        verify(visitante).visitar(servicio);
    }

    @Test
    public void paqueteTuristicoAceptaVisitanteTest() {
        PaqueteTuristico paquete = new PaqueteTuristico(
            "2", "Paquete", "Proveedor B", "Turismo",
            10, LocalDate.of(2026, 3, 1),
            LocalDate.of(2026, 3, 5), 10.0
        );

        VisitanteReportes visitante = mock(VisitanteReportes.class);

        paquete.aceptarVisistante(visitante);

        verify(visitante).visitar(paquete);
    }
}