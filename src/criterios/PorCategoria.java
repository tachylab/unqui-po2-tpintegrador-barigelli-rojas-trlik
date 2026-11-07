package criterios;

import serviciosYPaquetes.ItemCatalogo;

public class PorCategoria extends CriterioBusqueda {
    private String categoria;

    public PorCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean evaluar(ItemCatalogo item) {
        return item.getCategoria().equals(categoria);
    }
}
