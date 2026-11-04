package Reservas;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import serviciosYPaquetes.ItemCatalogo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import serviciosYPaquetes.ItemCatalogo;


class ReservaTest {

    private Reserva reserva;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        itemMock = mock(ItemCatalogo.class);
    }

    static Stream<Reserva> reservasNoPendientes() {
        Reserva confirmada = new Reserva();
        confirmada.confirmar();

        Reserva enCheckin = new Reserva();
        enCheckin.confirmar();
        enCheckin.hacerCheckin();

        Reserva enCurso = new Reserva();
        enCurso.confirmar();
        enCurso.hacerCheckin();
        enCurso.iniciar();

        Reserva finalizada = new Reserva();
        finalizada.confirmar();
        finalizada.hacerCheckin();
        finalizada.iniciar();
        finalizada.finalizar();

        Reserva cancelada = new Reserva();
        cancelada.cancelar();

        return Stream.of(
                confirmada,
                enCheckin,
                enCurso,
                finalizada,
                cancelada
        );
    }

    private void llevarReservaAFinalizada() {
        reserva.confirmar();
        reserva.hacerCheckin();
        reserva.iniciar();
        reserva.finalizar();
    }

    private void llevarReservaAEnCurso() {
        reserva.confirmar();
        reserva.hacerCheckin();
        reserva.iniciar();
    }

    private void llevarReservaAEnCheckin() {
        reserva.confirmar();
        reserva.hacerCheckin();
    }


    @Test
    public void unaReservaComienzaPendiente() {
        assertTrue(reserva.getEstado() instanceof SolicitudPendiente);
    }

    @Test
    public void unaReservaPuedeSerPendienteCorrectamente() {
        assertTrue(reserva.getEstado() instanceof SolicitudPendiente);
    }

    @Test
    public void unaReservaPendientePuedeConfirmarse() {
        reserva.confirmar();
        assertTrue(reserva.getEstado() instanceof Confirmada);
    }

    @Test
    public void unaReservaPendientePuedeCancelarse() {
        reserva.cancelar();
        assertTrue(reserva.getEstado() instanceof Cancelada);
    }

    @Test
    public void unaReservaPendienteNoPuedeFinalizarse() {
        assertThrows(OperacionInvalidaException.class, () -> reserva.finalizar());
    }

    @Test
    public void unaReservaPendienteNoPuedeHacerChekin() {
        assertThrows(OperacionInvalidaException.class, () -> reserva.hacerCheckin());
    }

    @Test
    public void unaReservaPendienteNoPuedeIniciarse() {
        assertThrows(OperacionInvalidaException.class, () -> reserva.iniciar());
    }

    @Test
    public void unaReservaPuedeConfirmarseCorrectamente() {
        reserva.confirmar();
        assertTrue(reserva.getEstado() instanceof Confirmada);
    }

    @Test
    public void unaReservaConfirmadaPuedeHacerCheckin() {
        reserva.confirmar();
        reserva.hacerCheckin();
        assertTrue(reserva.getEstado() instanceof EnCheckin);
    }

    @Test
    public void unaReservaConfirmadaPuedeCancelarse() {
        reserva.confirmar();
        reserva.cancelar();
        assertTrue(reserva.getEstado() instanceof Cancelada);
    }

    @Test
    public void unaReservaConfirmadaNoPuedeFinalizarse() {
        reserva.confirmar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.finalizar());
    }

    @Test
    public void unaReservaConfirmadaNoPuedeConfirmarse() {
        reserva.confirmar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.confirmar());
    }

    @Test
    public void unaReservaConfirmadaNoPuedeIniciarse() {
        reserva.confirmar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.iniciar());
    }

    @Test
    public void unaReservaPuedeEntrarEnCheckinCorrectamente() {
        llevarReservaAEnCheckin();
        assertTrue(reserva.getEstado() instanceof EnCheckin);
    }

    @Test
    public void unaReservaEnCheckinPuedeIniciar() {
        llevarReservaAEnCheckin();
        reserva.iniciar();
        assertTrue(reserva.getEstado() instanceof EnCurso);
    }

    @Test
    public void unaReservaEnCheckinPuedeCancelarse() {
        llevarReservaAEnCheckin();
        reserva.cancelar();
        assertTrue(reserva.getEstado() instanceof Cancelada);
    }

    @Test
    public void unaReservaEnCheckinNoPuedeConfirmar() {
        llevarReservaAEnCheckin();
        assertThrows(OperacionInvalidaException.class, () -> reserva.confirmar());
    }

    @Test
    public void unaReservaEnCheckinNoPuedeVolverAHacerCheckin() {
        llevarReservaAEnCheckin();
        assertThrows(OperacionInvalidaException.class, () -> reserva.hacerCheckin());
    }

    @Test
    public void unaReservaEnCheckinNoPuedeFinalizar() {
        llevarReservaAEnCheckin();
        assertThrows(OperacionInvalidaException.class, () -> reserva.finalizar());
    }


    @Test
    public void unaReservaPuedeEntrarEnCursoCorrectamente() {
        llevarReservaAEnCurso();
        assertTrue(reserva.getEstado() instanceof EnCurso);
    }

    @Test
    public void unaReservaEnCursoPuedeFinalizarse() {
        llevarReservaAEnCurso();
        reserva.finalizar();
        assertTrue(reserva.getEstado() instanceof Finalizada);
    }

    @Test
    public void unaReservaEnCursoPuedeCancelarse() {
        llevarReservaAEnCurso();
        reserva.cancelar();
        assertTrue(reserva.getEstado() instanceof Cancelada);
    }

    @Test
    public void unaReservaEnCursoNoPuedeConfirmar() {
        llevarReservaAEnCurso();
        assertThrows(OperacionInvalidaException.class, () -> reserva.confirmar());
    }

    @Test
    public void unaReservaEnCursoNoPuedeIniciar() {
        llevarReservaAEnCurso();
        assertThrows(OperacionInvalidaException.class, () -> reserva.iniciar());
    }

    @Test
    public void unaReservaEnCursoNoPuedeHacerCheckin() {
        llevarReservaAEnCurso();
        assertThrows(OperacionInvalidaException.class, () -> reserva.hacerCheckin());
    }


    @Test
    public void unaReservaPuedeFinalizarCorrectamente() {
        llevarReservaAFinalizada();
        assertTrue(reserva.getEstado() instanceof Finalizada);
    }

    @Test
    public void unaReservaFinalizadaNoPuedeConfirmar() {
        llevarReservaAFinalizada();
        assertThrows(OperacionInvalidaException.class, () -> reserva.confirmar());
    }

    @Test
    public void unaReservaFinalizadaNoPuedeCancelar() {
        llevarReservaAFinalizada();
        assertThrows(OperacionInvalidaException.class, () -> reserva.cancelar());
    }

    @Test
    public void unaReservaFinalizadaNoPuedeHacerCheckin() {
        llevarReservaAFinalizada();
        assertThrows(OperacionInvalidaException.class, () -> reserva.hacerCheckin());
    }

    @Test
    public void unaReservaFinalizadaNoPuedeIniciar() {
        llevarReservaAFinalizada();
        assertThrows(OperacionInvalidaException.class, () -> reserva.iniciar());
    }

    @Test
    public void unaReservaFinalizadaNoPuedeVolverAFinalizar() {
        llevarReservaAFinalizada();
        assertThrows(OperacionInvalidaException.class, () -> reserva.finalizar());
    }



    @Test
    public void unaReservaPuedeCancelarCorrectamente() {
        reserva.cancelar();
        assertTrue(reserva.getEstado() instanceof Cancelada);
    }

    @Test
    public void unaReservaCanceladaNoPuedeVolverACancelar() {
        reserva.cancelar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.cancelar());
    }

    @Test
    public void unaReservaCanceladaNoPuedeHacerCheckin() {
        reserva.cancelar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.hacerCheckin());
    }

    @Test
    public void unaReservaCanceladaNoPuedeIniciar() {
        reserva.cancelar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.iniciar());
    }

    @Test
    public void unaReservaCanceladaNoPuedeFinalizar() {
        reserva.cancelar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.finalizar());
    }

    @Test
    public void unaReservaCanceladaNoPuedeConfirmar() {
        reserva.cancelar();
        assertThrows(OperacionInvalidaException.class, () -> reserva.confirmar());
    }

    @Test
    public void unaReservaPendientePuedeAgregarServicio() {
        reserva.agregarServicio(itemMock);
        assertTrue(reserva.getItems().contains(itemMock));
    }

    @Test
    public void unaReservaPendientePuedeQuitarServicio() {
        reserva.agregarServicio(itemMock);
        reserva.quitarServicio(itemMock);
        assertFalse(reserva.getItems().contains(itemMock));
    }


    @ParameterizedTest
    @MethodSource("reservasNoPendientes")
    void noSePuedeAgregarServicioSiNoEstaPendiente(Reserva reserva) {

        ItemCatalogo itemMock = mock(ItemCatalogo.class);

        assertThrows(
                OperacionInvalidaException.class,
                () -> reserva.agregarServicio(itemMock)
        );
    }


    @ParameterizedTest
    @MethodSource("reservasNoPendientes")
    void noSePuedeQuitarServicioSiNoEstaPendiente(Reserva reserva) {

        ItemCatalogo itemMock = mock(ItemCatalogo.class);

        assertThrows(
                OperacionInvalidaException.class,
                () -> reserva.quitarServicio(itemMock)
        );
    }
}
