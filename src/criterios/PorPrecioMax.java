package criterios;

import serviciosYPaquetes.ItemCatalogo;

public class PorPrecioMax extends CriterioBusqueda {
    //Atributo
    private final double precioMax;

    //Constructor
    public PorPrecioMax(double precioMax) {
        this.precioMax = precioMax;
    }

    @Override
    public boolean evaluar(ItemCatalogo item) {
        return item.precioBase() <= precioMax;
    }
}
