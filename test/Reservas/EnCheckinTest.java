package Reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serviciosYPaquetes.ItemCatalogo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnCheckinTest {

    private EnCheckin estado;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estado = new EnCheckin();
        reservaMock = mock(Reserva.class);
    }

    @Test
    void iniciarReservaCambiaAEnCurso() {
        estado.iniciar(reservaMock);
        verify(reservaMock).setEstado(isA(EnCurso.class));
    }

    @Test
    void cancelarReservaEnCheckin() {
        estado.cancelar(reservaMock);
        verify(reservaMock).liberarCupos();
        verify(reservaMock).reembolsarServiciosYTasas();
        verify(reservaMock).setEstado(isA(Cancelada.class));
    }

    @Test
    void siFallaElReembolsoNoCambiaACancelada() {
        doThrow(new OperacionInvalidaException("Error al reembolsar")).when(reservaMock).reembolsarServiciosYTasas();
        assertThrows(OperacionInvalidaException.class, () -> estado.cancelar(reservaMock));
        verify(reservaMock, never()).setEstado(any(EstadoDeReserva.class));
    }

    @Test
    void noPermiteConfirmar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.confirmar(reservaMock));
    }

    @Test
    void noPermiteHacerCheckinDosVeces() {
        assertThrows(OperacionInvalidaException.class, () -> estado.hacerCheckin(reservaMock));
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
    void noPermiteFinalizarSinIniciar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.finalizar(reservaMock));
    }

}