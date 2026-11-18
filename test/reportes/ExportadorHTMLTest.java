package reportes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ExportadorHTMLTest {

    ExportadorHTML exportador;
    Reporte reporte;

    @BeforeEach
    public void setUp() {
        exportador = new ExportadorHTML();

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

        assertTrue(resultado.contains("<html>"));
        assertTrue(resultado.contains("<head>"));
        assertTrue(resultado.contains("<meta charset=\"UTF-8\">"));
        assertTrue(resultado.contains("<title>Reporte</title>"));
        assertTrue(resultado.contains("<h1>Reporte</h1>"));
        assertTrue(resultado.contains("<li>Hotel</li>"));
        assertTrue(resultado.contains("<li>Paquete</li>"));
        assertTrue(resultado.contains("</html>"));
    }

    @Test
    public void testExportarReporteSinDatos() {
        String resultado = exportador.exportar(reporte);

        assertTrue(resultado.contains("<ul></ul>"));
        assertTrue(resultado.contains("<html>"));
        assertTrue(resultado.contains("</html>"));
    }
}
