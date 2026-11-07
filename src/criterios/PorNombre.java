package criterios;

import serviciosYPaquetes.ItemCatalogo;

public class PorNombre extends CriterioBusqueda {
    private String nombre;

    public PorNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean evaluar(ItemCatalogo item) {
        return item.getNombre().equals(nombre);
    }
}
