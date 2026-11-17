package Reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serviciosYPaquetes.ItemCatalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FinalizadaTest {

    private Finalizada estado;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estado = new Finalizada();
        reservaMock = mock(Reserva.class);
    }


    @Test
    void noPermiteConfirmar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.confirmar(reservaMock));
    }


    @Test
    void noPermiteCancelar() {
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
    void noPermiteFinalizarDosVeces() {
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