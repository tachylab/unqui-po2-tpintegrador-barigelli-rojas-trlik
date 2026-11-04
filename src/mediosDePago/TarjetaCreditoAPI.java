package mediosDePago;

public interface TarjetaCreditoAPI {

    boolean validarTarjeta(String numeroTarjeta, String ccv, String vencimiento);

    boolean preAutorizar(String numeroTarjeta, double monto);

    String debitar(String numeroTarjeta, double monto);
}
