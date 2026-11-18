package reportes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import serviciosYPaquetes.PaqueteTuristico;
import serviciosYPaquetes.ServicioIndividual;
import static org.junit.jupiter.api.Assertions.*;

public class ReporteExposicionProveedorTest {

    ReporteExposicionProveedor reporte;
    ServicioIndividual servicio;
    PaqueteTuristico paquete;

    @BeforeEach
    public void setUp() {
        reporte = new ReporteExposicionProveedor(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 12, 31)
        );

        servicio = new ServicioIndividual(
            "1", "Hotel", "Proveedor A", "Alojamiento",
            10,
            LocalDate.of(2026, 2, 1),
            LocalDate.of(2026, 2, 5),
            100.0
        );

        paquete = new PaqueteTuristico(
            "2", "Paquete", "Proveedor B", "Turismo",
            10,
            LocalDate.of(2026, 3, 1),
            LocalDate.of(2026, 3, 5),
            10.0,
            List.of(servicio)
        );
    }

    @Test
    public void testVisitarServicioIndividual() {
        reporte.visitar(servicio);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Proveedor A"));
        assertTrue(reporte.getDatos().get(0).contains("100,00")
                || reporte.getDatos().get(0).contains("100.00"));
    }

    @Test
    public void testVisitarPaqueteTuristico() {
        reporte.visitar(paquete);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Proveedor B"));
    }

    @Test
    public void testCalcularPorcentajeConVariosProveedores() {
        reporte.visitar(servicio);
        reporte.visitar(paquete);

        assertEquals(2, reporte.getDatos().size());

        assertTrue(reporte.getDatos().stream()
            .allMatch(dato -> dato.contains("50")));
    }

    @Test
    public void testAcumularElementosDelMismoProveedor() {
        ServicioIndividual otroServicio = new ServicioIndividual(
            "3", "Excursion", "Proveedor A", "Turismo",
            10,
            LocalDate.of(2026, 4, 1),
            LocalDate.of(2026, 4, 5),
            50.0
        );

        reporte.visitar(servicio);
        reporte.visitar(otroServicio);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Proveedor A"));
        assertTrue(reporte.getDatos().get(0).contains("100"));
    }
}
