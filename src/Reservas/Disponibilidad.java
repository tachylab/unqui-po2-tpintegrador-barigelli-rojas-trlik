package Reservas;

public class Disponibilidad {

    private int capacidadMaxima;
    private int cuposDisponibles;

    public Disponibilidad(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.cuposDisponibles = capacidadMaxima;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void reservarCupo() {
        if (cuposDisponibles <= 0) {
            throw new CupoInsuficienteException(
                    "No quedan cupos disponibles"
            );
        }
        cuposDisponibles--;
    }

    public void liberarCupo() {
        cuposDisponibles++;
    }

}
