package serviciosYPaquetes;

public class TipoBooleano implements TipoAtributo {
    @Override
    public boolean esValido(Object valor) {
        return valor instanceof Boolean;
    }
}
