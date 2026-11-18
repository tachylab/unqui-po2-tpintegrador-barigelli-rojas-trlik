package reportes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ExportadorTextoTest {

    ExportadorTexto exportador;
    Reporte reporte;

    @BeforeEach
    public void setUp() {
        exportador = new ExportadorTexto();

        reporte = new Reporte(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 12, 31)
        ) {};
    }

    @Test
    public void testExportarReporteConDatos() {
        reporte.getDatos().add("Hotel | Reservas: 2");
        reporte.getDatos().add("Paquete | Reservas: 3");

        String resultado = exportador.exportar(reporte);

        assertEquals(
            "Hotel | Reservas: 2\nPaquete | Reservas: 3\n",
            resultado
        );
    }

    @Test
    public void testExportarReporteSinDatos() {
        String resultado = exportador.exportar(reporte);

        assertEquals("", resultado);
    }

    @Test
    public void testExportarUnSoloDato() {
        reporte.getDatos().add("Hotel");

        assertEquals("Hotel\n", exportador.exportar(reporte));
    }
}

