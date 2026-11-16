package Reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import org.mockito.InOrder;
import serviciosYPaquetes.ItemCatalogo;

class ConfirmadaTest {

    private Confirmada estado;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estado = new Confirmada();
        reservaMock = mock(Reserva.class);
    }

    @Test
    void hacerCheckinCambiaElEstadoAEnCheckin() {
        estado.hacerCheckin(reservaMock);
        verify(reservaMock).setEstado(isA(EnCheckin.class));
    }

    @Test
    void cancelarReservaConfirmadaLiberaCupos() {
        estado.cancelar(reservaMock);
        InOrder orden = inOrder(reservaMock);
        orden.verify(reservaMock).liberarCupos();
        orden.verify(reservaMock).setEstado(isA(Cancelada.class));
    }


    @Test
    void siFallaLiberarCuposNoCambiaAEstadoCancelada() {
        doThrow(new OperacionInvalidaException("Error al liberar cupos")).when(reservaMock).liberarCupos();
        assertThrows(OperacionInvalidaException.class, () -> estado.cancelar(reservaMock));
        verify(reservaMock, never()).setEstado(any(EstadoDeReserva.class));
    }


    @Test
    void noPermiteConfirmarDosVeces() {
        assertThrows(OperacionInvalidaException.class, () -> estado.confirmar(reservaMock));
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


    @Test
    void noPermiteIniciarSinCheckin() {
        assertThrows(OperacionInvalidaException.class, () -> estado.iniciar(reservaMock));
    }

    @Test
    void noPermiteFinalizarSinIniciar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.finalizar(reservaMock));
    }

}
