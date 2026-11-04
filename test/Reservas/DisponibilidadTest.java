package Reservas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DisponibilidadTest {

    private Disponibilidad disponibilidad;

    @BeforeEach
    void setUp() {
        disponibilidad = new Disponibilidad(10);
    }

    @Test
    void disponibilidadInicial() {
        assertEquals(10, disponibilidad.getCuposDisponibles());
    }

    @Test
    void reservarCupoDescuentaUno() {
        disponibilidad.reservarCupo();
        assertEquals(9, disponibilidad.getCuposDisponibles());
    }

    @Test
    void liberarCupoAumentaUno() {
        disponibilidad.reservarCupo();
        disponibilidad.liberarCupo();
        assertEquals(10, disponibilidad.getCuposDisponibles());
    }

    @Test
    void noSePuedeReservarSinCupos() {
        Disponibilidad sinCupos = new Disponibilidad(0);
        assertThrows(CupoInsuficienteException.class, () -> sinCupos.reservarCupo());
    }

    @Test
    void tieneCupoDisponibleCuandoHayCupos() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        assertTrue(disponibilidad.tieneCupoDisponible());
    }

    @Test
    void noTieneCupoDisponibleCuandoEstaAgotado() {
        Disponibilidad disponibilidad = new Disponibilidad(0);
        assertFalse(disponibilidad.tieneCupoDisponible());
    }

    @Test
    void tieneCuposDisponiblesCuandoAlcanzan() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        assertTrue(disponibilidad.tieneCuposDisponibles(5));
    }

    @Test
    void noTieneCuposDisponiblesCuandoNoAlcanzan() {
        Disponibilidad disponibilidad = new Disponibilidad(3);
        assertFalse(disponibilidad.tieneCuposDisponibles(5));
    }

    @Test
    void noTieneCuposDisponiblesParaCantidadInvalida() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        assertFalse(disponibilidad.tieneCuposDisponibles(0));
        assertFalse(disponibilidad.tieneCuposDisponibles(-2));
    }

    @Test
    void reservarVariosCupos() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        disponibilidad.reservarCupos(4);
        assertEquals(6, disponibilidad.getCuposDisponibles());
    }

    @Test
    void noPermiteReservarMasCuposDeLosDisponibles() {
        Disponibilidad disponibilidad = new Disponibilidad(3);
        assertThrows(CupoInsuficienteException.class, () -> disponibilidad.reservarCupos(5));
        assertEquals(3, disponibilidad.getCuposDisponibles());
    }

    @Test
    void liberarVariosCupos() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        disponibilidad.reservarCupos(4);
        disponibilidad.liberarCupos(4);
        assertEquals(10, disponibilidad.getCuposDisponibles());
    }

    @Test
    void noPermiteLiberarMasCuposDeLosReservados() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        disponibilidad.reservarCupos(2);
        assertThrows(OperacionInvalidaException.class, () -> disponibilidad.liberarCupos(3));
        assertEquals(8, disponibilidad.getCuposDisponibles());
    }
}