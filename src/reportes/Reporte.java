package reportes;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public abstract class Reporte {

    protected LocalDate periodoDesde;
    protected LocalDate periodoHasta;
    protected List<String> datos;

    public Reporte(LocalDate periodoDesde, LocalDate periodoHasta) {
        this.periodoDesde = periodoDesde;
        this.periodoHasta = periodoHasta;
        this.datos = new ArrayList<>();
    }

    public List<String> getDatos() {
        return datos;
    }
}
