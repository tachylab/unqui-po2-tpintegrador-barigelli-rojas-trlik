package criterios;

import serviciosYPaquetes.ItemCatalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import reportes.VisitanteReportes;

import reportes.Reporte;

public class Catalogo {
    //Atributos
    private List<ItemCatalogo> items = new ArrayList<>();

    public void agregarItem(ItemCatalogo item) {
        this.items.add(item);
    }

    public List<ItemCatalogo> buscar(CriterioBusqueda criterio) {
        return this.items.stream()
                        .filter(criterio::evaluar)
                        .collect(Collectors.toList());
    }
    
    public void generarReporte(Reporte reporte) {
        for (ItemCatalogo item : items) {
            item.aceptarVisistante((VisitanteReportes) reporte);
        }
    }
}
