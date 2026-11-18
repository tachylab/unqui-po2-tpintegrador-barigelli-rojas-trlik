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

public class ReporteServiciosMasReservadosTest {

    ReporteServiciosMasReservados reporte;
    ServicioIndividual servicio;
    PaqueteTuristico paquete;
    Reserva reserva1;
    Reserva reserva2;

    @BeforeEach
    public void setUp() {
        reserva1 = mock(Reserva.class);
        reserva2 = mock(Reserva.class);

        reporte = new ReporteServiciosMasReservados(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 12, 31),
            List.of(reserva1, reserva2)
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
            10.0
        );

        when(reserva1.getItems()).thenReturn(List.of(servicio));
        when(reserva2.getItems()).thenReturn(List.of(servicio));

        when(reserva1.getPreciosAplicados()).thenReturn(
            java.util.Map.of(servicio, 80.0)
        );

        when(reserva2.getPreciosAplicados()).thenReturn(
            java.util.Map.of(servicio, 100.0)
        );
    }

    @Test
    public void testVisitarServicioIndividual() {
        reporte.visitar(servicio);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Hotel"));
        assertTrue(reporte.getDatos().get(0).contains("Reservas: 2"));
        assertTrue(reporte.getDatos().get(0).contains("Precio promedio: 90.0"));
    }

    @Test
    public void testVisitarPaqueteTuristicoSinReservas() {
        reporte.visitar(paquete);

        assertTrue(reporte.getDatos().isEmpty());
    }

    @Test
    public void testIgnorarServicioFueraDelPeriodo() {
        ServicioIndividual fueraDelPeriodo = new ServicioIndividual(
            "3", "Hotel", "Proveedor C", "Alojamiento",
            10,
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
            10,
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 1, 5),
            100.0
        );

        reporte.visitar(fueraDelPeriodo);

        assertTrue(reporte.getDatos().isEmpty());
    }

    @Test
    public void testPrecioNoRegistradoSeConsideraCero() {
        when(reserva1.getItems()).thenReturn(List.of(servicio));
        when(reserva2.getItems()).thenReturn(List.of());

        when(reserva1.getPreciosAplicados()).thenReturn(java.util.Map.of());

        reporte.visitar(servicio);

        assertEquals(1, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Precio promedio: 0.0"));
    }

    @Test
    public void testOrdenarPorCantidadDeReservas() {
        ServicioIndividual otroServicio = new ServicioIndividual(
            "5", "Excursion", "Proveedor B", "Turismo",
            10,
            LocalDate.of(2026, 4, 1),
            LocalDate.of(2026, 4, 5),
            50.0
        );

        when(reserva1.getItems()).thenReturn(List.of(servicio, otroServicio));
        when(reserva2.getItems()).thenReturn(List.of(servicio));

        when(reserva1.getPreciosAplicados()).thenReturn(
            java.util.Map.of(servicio, 80.0, otroServicio, 50.0)
        );

        when(reserva2.getPreciosAplicados()).thenReturn(
            java.util.Map.of(servicio, 100.0)
        );

        reporte.visitar(otroServicio);
        reporte.visitar(servicio);

        assertEquals(2, reporte.getDatos().size());
        assertTrue(reporte.getDatos().get(0).contains("Hotel"));
        assertTrue(reporte.getDatos().get(1).contains("Excursion"));
    }
}
