package criterios;

import serviciosYPaquetes.ItemCatalogo;

import java.util.ArrayList;
import java.util.List;

public class CriterioOr extends CriterioBusqueda {
    //Atributos
    private List<CriterioBusqueda> criterios = new ArrayList<>();

    //Constructores
    public CriterioOr(CriterioBusqueda izquierdo,  CriterioBusqueda derecho) {
        criterios.add(izquierdo);
        criterios.add(derecho);
    }


    @Override
    public boolean evaluar(ItemCatalogo item) {
        for (CriterioBusqueda criterioBusqueda : criterios) {
            if (criterioBusqueda.evaluar(item)) {
                return true;
            }
        }
        return false;
    }
}
