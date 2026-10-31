package serviciosYPaquetes;

import java.time.LocalDate;

public class TipoFecha implements TipoAtributo {
    @Override
    public boolean esValido(Object valor) {
        return valor instanceof LocalDate;
    }
}
