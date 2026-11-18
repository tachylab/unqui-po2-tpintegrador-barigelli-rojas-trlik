package reportes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ExportadorCSVTest {

    ExportadorCSV exportador;
    Reporte reporte;

    @BeforeEach
    public void setUp() {
        exportador = new ExportadorCSV();

        reporte = new Reporte(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 12, 31)
        ) {};
    }

    @Test
    public void testExportarReporteConDatos() {
        reporte.getDatos().add("Hotel");
        reporte.getDatos().add("Paquete");

        String resultado = exportador.exportar(reporte);

        assertEquals("Datos\n\"Hotel\"\n\"Paquete\"\n", resultado);
    }

    @Test
    public void testExportarReporteSinDatos() {
        assertEquals("Datos\n", exportador.exportar(reporte));
    }

    @Test
    public void testEscaparComillasEnLosDatos() {
        reporte.getDatos().add("Hotel \"Premium\"");

        String resultado = exportador.exportar(reporte);

        assertEquals("Datos\n\"Hotel \"\"Premium\"\"\"\n", resultado);
    }
}

