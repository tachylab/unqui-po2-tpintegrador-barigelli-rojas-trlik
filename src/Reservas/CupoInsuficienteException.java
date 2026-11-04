package Reservas;

public class CupoInsuficienteException extends RuntimeException{
    public CupoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
