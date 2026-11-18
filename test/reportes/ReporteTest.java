package reportes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ReporteTest {

    Reporte reporte;

    @BeforeEach
    public void setUp() {
        reporte = new Reporte(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 12, 31)
        ) {};
    }

    @Test
    public void testConstructorInicializaDatosVacios() {
        assertNotNull(reporte.getDatos());
        assertTrue(reporte.getDatos().isEmpty());
    }

    @Test
    public void testGetDatosDevuelveLosDatosDelReporte() {
        reporte.getDatos().add("Servicio reservado");

        assertEquals(1, reporte.getDatos().size());
        assertEquals("Servicio reservado", reporte.getDatos().get(0));
    }
}

