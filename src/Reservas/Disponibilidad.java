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
            throw new CupoInsuficienteException("No quedan cupos disponibles");
        }
        cuposDisponibles--;
    }

    public void liberarCupo() {
        if (cuposDisponibles >= capacidadMaxima) {
            throw new OperacionInvalidaException("No hay cupos reservados para liberar");
        }
        cuposDisponibles++;
    }

    public boolean tieneCupoDisponible() {
        return cuposDisponibles > 0;
    }

    //AGREGADO NUEVO
    public boolean tieneCuposDisponibles(int cantidad) {
        return cantidad > 0 && cuposDisponibles >= cantidad;
    }

    public void reservarCupos(int cantidad) {
        if (!tieneCuposDisponibles(cantidad)) {
            throw new CupoInsuficienteException("No hay suficientes cupos");
        }

        cuposDisponibles -= cantidad;
    }

    public void liberarCupos(int cantidad) {
        if (cantidad <= 0 || cuposDisponibles + cantidad > capacidadMaxima) {
            throw new OperacionInvalidaException("No se puede liberar esa cantidad de cupos");
        }

        cuposDisponibles += cantidad;
    }
}
