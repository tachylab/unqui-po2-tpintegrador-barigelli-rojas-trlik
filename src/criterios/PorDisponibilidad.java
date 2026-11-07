package criterios;

import serviciosYPaquetes.ItemCatalogo;

public class PorDisponibilidad extends CriterioBusqueda {
    @Override
    public boolean evaluar(ItemCatalogo item) {
        return item.tieneCupoDisponible();
    }
}
