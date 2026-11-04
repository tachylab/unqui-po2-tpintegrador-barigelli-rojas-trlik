package mediosDePago;

public interface BilleteraCryptoAPI {
    boolean consultarSaldoSuficiente(String usuario, double monto);

    boolean bloquearSaldo(String usuario, double monto);

    boolean acreditarAlVendedor(String usuario, String vendedor, double monto);

    void enviarNotificacionPush(String usuario, String mensaje);
}
