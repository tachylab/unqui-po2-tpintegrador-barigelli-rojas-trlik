package criterios;

import serviciosYPaquetes.ItemCatalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
