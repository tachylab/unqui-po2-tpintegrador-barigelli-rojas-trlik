package serviciosYPaquetes;

public class AtributoDinamico {
    //Atributos
    private String nombre;
    private boolean esObligatorio;
    private TipoAtributo tipoAtributo;
    private Object valor;


    //Constructores
    //Constructor 1: Inicializar sin un valor
    public AtributoDinamico(String nombre, boolean esObligatorio, TipoAtributo tipoAtributo) {
        this(nombre, esObligatorio, tipoAtributo, null);
    }

    //Constructor 2: Inicializar con un valor
    public AtributoDinamico(String nombre, boolean esObligatorio, TipoAtributo tipoAtributo, Object valor) {
        this.nombre = nombre;
        this.esObligatorio = esObligatorio;
        this.tipoAtributo = tipoAtributo;
        this.valor = valor;
    }

    //Accessors
    public String getNombre() {
        return nombre;
    }

    public boolean getEsObligatorio() {
        return esObligatorio;
    }

    public TipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public Object getValor() {
        return valor;
    }

    //Funciones
    public boolean esValido() {
        return this.validarAtributoObligatorio() && this.validarTipoDeclarado();
    }

    private boolean  validarAtributoObligatorio() {
        return !this.esObligatorio || this.valor != null;
    }

    private boolean validarTipoDeclarado() {
        return this.valor == null || this.tipoAtributo.esValido(this.valor);
    }
}
