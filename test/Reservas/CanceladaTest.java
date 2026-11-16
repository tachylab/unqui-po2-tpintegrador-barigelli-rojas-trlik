package Reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serviciosYPaquetes.ItemCatalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CanceladaTest {

    private Cancelada estado;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estado = new Cancelada();
        reservaMock = mock(Reserva.class);
    }


    @Test
    void noPermiteConfirmar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.confirmar(reservaMock));
    }

    @Test
    void noPermiteCancelarDosVeces() {
        assertThrows(OperacionInvalidaException.class, () -> estado.cancelar(reservaMock));
    }

    @Test
    void noPermiteHacerCheckin() {
        assertThrows(OperacionInvalidaException.class, () -> estado.hacerCheckin(reservaMock));
    }


    @Test
    void noPermiteIniciar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.iniciar(reservaMock));
    }

    @Test
    void noPermiteFinalizar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.finalizar(reservaMock));
    }


    @Test
    void noPermiteAgregarServicios() {
        ItemCatalogo item = mock(ItemCatalogo.class);
        assertThrows(OperacionInvalidaException.class, () -> estado.agregarServicio(reservaMock, item));
    }


    @Test
    void noPermiteQuitarServicios() {
        ItemCatalogo item = mock(ItemCatalogo.class);
        assertThrows(OperacionInvalidaException.class, () -> estado.quitarServicio(reservaMock, item));
    }



}