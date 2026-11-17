package Reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import serviciosYPaquetes.ItemCatalogo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EnCursoTest {

    private EnCurso estado;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estado = new EnCurso();
        reservaMock = mock(Reserva.class);
    }

    @Test
    void finalizarReservaCambiaAFinalizada() {

        estado.finalizar(reservaMock);

        verify(reservaMock).setEstado(isA(Finalizada.class));
    }

    @Test
    void cancelarReservaEnCurso() {
        estado.cancelar(reservaMock);
        InOrder orden = inOrder(reservaMock);
        orden.verify(reservaMock).reembolsarServiciosNoDisfrutados();
        orden.verify(reservaMock).setEstado(isA(Cancelada.class));
    }

    @Test
    void siFallaElReembolsoNoCambiaACancelada() {
        doThrow(new OperacionInvalidaException("Error al reembolsar")).when(reservaMock).reembolsarServiciosNoDisfrutados();
        assertThrows(OperacionInvalidaException.class, () -> estado.cancelar(reservaMock));
        verify(reservaMock, never()).setEstado(any(EstadoDeReserva.class));
    }


    @Test
    void noPermiteConfirmar() {
        assertThrows(OperacionInvalidaException.class, () -> estado.confirmar(reservaMock));
    }


    @Test
    void noPermiteHacerCheckin() {
        assertThrows(OperacionInvalidaException.class, () -> estado.hacerCheckin(reservaMock));
    }


    @Test
    void noPermiteIniciarDosVeces() {
        assertThrows(OperacionInvalidaException.class, () -> estado.iniciar(reservaMock));
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