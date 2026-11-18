package reportes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import serviciosYPaquetes.ServicioIndividual;
import serviciosYPaquetes.PaqueteTuristico;
import Reservas.Reserva;

public class ReporteOcupacionTest {

    ReporteOcupacion reporte;
    ServicioIndividual servicio;
    PaqueteTuristico paquete;
    Reserva reserva;

    @BeforeEach
    public void setUp() {
        reserva = mock(Reserva.class);

        reporte = new ReporteOcupacion(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 12, 31),
            List.of(reserva)
        );

        servicio = new ServicioIndividual(
            "1", "Hotel", "Proveedor A", "Alojamiento",
            10,
            LocalDate.of(2026, 2, 1),
            LocalDate.of(2026, 2, 5),
            100.0
        );

        paquete = new PaqueteTuristico(
            "2", "Paquete", "Proveedor B", "Alojamiento",
            5,
            LocalDate.of(2026, 3, 1),
            LocalDate.of(2026, 3, 5),
            10.0
        );

        when(reserva.getItems()).thenReturn(List.of(servicio));
    }

    @Test
    public void testVisitarServicioIndividual() {
        reporte.visitar(servicio);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Cupo total: 10"));
        assertTrue(reporte.getDatos().get(0).contains("Reservado: 1"));
        assertTrue(reporte.getDatos().get(0).contains("Disponible: 9"));
    }

    @Test
    public void testVisitarPaqueteTuristico() {
        reporte.visitar(paquete);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Cupo total: 5"));
        assertTrue(reporte.getDatos().get(0).contains("Reservado: 0"));
        assertTrue(reporte.getDatos().get(0).contains("Disponible: 5"));
    }

    @Test
    public void testIgnorarServicioFueraDelPeriodo() {
        ServicioIndividual fueraDelPeriodo = new ServicioIndividual(
            "3", "Hotel", "Proveedor C", "Alojamiento",
            20,
            LocalDate.of(2027, 1, 1),
            LocalDate.of(2027, 1, 5),
            100.0
        );

        reporte.visitar(fueraDelPeriodo);

        assertTrue(reporte.getDatos().isEmpty());
    }

    @Test
    public void testIgnorarServicioAnteriorAlPeriodo() {
        ServicioIndividual fueraDelPeriodo = new ServicioIndividual(
            "4", "Hotel", "Proveedor C", "Alojamiento",
            20,
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 1, 5),
            100.0
        );

        reporte.visitar(fueraDelPeriodo);

        assertTrue(reporte.getDatos().isEmpty());
    }

    @Test
    public void testAcumularCuposDeLaMismaCategoria() {
        ServicioIndividual otroServicio = new ServicioIndividual(
            "5", "Hostel", "Proveedor B", "Alojamiento",
            5,
            LocalDate.of(2026, 4, 1),
            LocalDate.of(2026, 4, 5),
            80.0
        );

        reporte.visitar(servicio);
        reporte.visitar(otroServicio);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Cupo total: 15"));
        assertTrue(reporte.getDatos().get(0).contains("Reservado: 1"));
        assertTrue(reporte.getDatos().get(0).contains("Disponible: 14"));
    }
}

