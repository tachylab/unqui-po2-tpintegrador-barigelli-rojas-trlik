package criterios;

import serviciosYPaquetes.ItemCatalogo;

public abstract class CriterioBusqueda {
    public abstract boolean evaluar (ItemCatalogo item);

    public CriterioBusqueda and(CriterioBusqueda otro) {
        return new CriterioAnd(this, otro);
    }

    public CriterioBusqueda or(CriterioBusqueda otro) {
        return new CriterioOr(this, otro);
    }

    public CriterioBusqueda not() {
        return new CriterioNot(this);
    }
}
