package criterios;

import serviciosYPaquetes.ItemCatalogo;

public class CriterioNot extends CriterioBusqueda{
    //Atributos
    private CriterioBusqueda criterio;

    //Constructor
    public CriterioNot(CriterioBusqueda otro) {
        this.criterio = otro;
    }

    @Override
    public boolean evaluar(ItemCatalogo item) {
        return !criterio.evaluar(item);
    }
}
