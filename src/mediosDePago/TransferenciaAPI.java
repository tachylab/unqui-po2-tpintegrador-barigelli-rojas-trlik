package mediosDePago;

public interface TransferenciaAPI {
    boolean validarCBU(String cbuOrigen, String alias);

    String transferir(String cbuOrigen, String cbuDestino, double monto);
}
