package serviciosYPaquetes;

public class TipoTexto implements TipoAtributo{
    @Override
    public boolean esValido(Object valor) {
        return valor instanceof String;
    }
}
