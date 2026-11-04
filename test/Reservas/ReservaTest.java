package Reservas;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import serviciosYPaquetes.ItemCatalogo;
import serviciosYPaquetes.ServicioIndividual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
        Reserva confirmada = crearReservaConServicio();
        confirmada.confirmar();

        Reserva enCheckin = crearReservaConServicio();
        enCheckin.confirmar();
        enCheckin.hacerCheckin();

        Reserva enCurso = crearReservaConServicio();
        enCurso.confirmar();
        enCurso.hacerCheckin();
        enCurso.iniciar();

        Reserva finalizada = crearReservaConServicio();
        finalizada.confirmar();
        finalizada.hacerCheckin();
        finalizada.iniciar();
        finalizada.finalizar();

        Reserva cancelada =crearReservaConServicio();
        cancelada.cancelar();

        return Stream.of(
                confirmada,
                enCheckin,
                enCurso,
                finalizada,
                cancelada
        );
    }

    private void llevarReservaAConfirmada(){
        reserva = crearReservaConServicio();
        reserva.confirmar();
    }

    private void llevarReservaAFinalizada() {
        reserva = crearReservaConServicio();
        reserva.confirmar();
        reserva.hacerCheckin();
        reserva.iniciar();
        reserva.finalizar();
    }

    private void llevarReservaAEnCurso() {
        reserva = crearReservaConServicio();
        reserva.confirmar();
        reserva.hacerCheckin();
        reserva.iniciar();
    }

    private void llevarReservaAEnCheckin() {
        reserva = crearReservaConServicio();
        reserva.confirmar();
        reserva.hacerCheckin();
    }

    private static Reserva crearReservaConServicio() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        ServicioIndividual servicioMock = mock(ServicioIndividual.class);

        when(servicioMock.obtenerServicios()).thenReturn(List.of(servicioMock));
        when(servicioMock.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(servicioMock);

        return reserva;
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
        reserva = crearReservaConServicio();
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
        llevarReservaAConfirmada();
        assertTrue(reserva.getEstado() instanceof Confirmada);
    }

    @Test
    public void unaReservaConfirmadaPuedeHacerCheckin() {
        llevarReservaAConfirmada();
        reserva.hacerCheckin();
        assertTrue(reserva.getEstado() instanceof EnCheckin);
    }

    @Test
    public void unaReservaConfirmadaPuedeCancelarse() {
        llevarReservaAConfirmada();
        reserva.cancelar();
        assertTrue(reserva.getEstado() instanceof Cancelada);
    }

    @Test
    public void unaReservaConfirmadaNoPuedeFinalizarse() {
        llevarReservaAConfirmada();
        assertThrows(OperacionInvalidaException.class, () -> reserva.finalizar());
    }

    @Test
    public void unaReservaConfirmadaNoPuedeConfirmarse() {
        llevarReservaAConfirmada();
        assertThrows(OperacionInvalidaException.class, () -> reserva.confirmar());
    }

    @Test
    public void unaReservaConfirmadaNoPuedeIniciarse() {
        llevarReservaAConfirmada();
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
        assertThrows(OperacionInvalidaException.class, () -> reserva.agregarServicio(itemMock));
    }


    @ParameterizedTest
    @MethodSource("reservasNoPendientes")
    void noSePuedeQuitarServicioSiNoEstaPendiente(Reserva reserva) {
        ItemCatalogo itemMock = mock(ItemCatalogo.class);
        assertThrows(OperacionInvalidaException.class, () -> reserva.quitarServicio(itemMock));
    }

    @Test
    void obtenerCantidadesDeServiciosDiferentes() {
        Disponibilidad disponibilidad1 = new Disponibilidad(10);
        Disponibilidad disponibilidad2 = new Disponibilidad(5);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        ServicioIndividual hotel = mock(ServicioIndividual.class);

        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(hotel.obtenerServicios()).thenReturn(List.of(hotel));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidad1);
        when(hotel.getDisponibilidad()).thenReturn(disponibilidad2);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);
        reserva.agregarServicio(hotel);

        Map<Disponibilidad, Integer> cantidades = reserva.obtenerCantidades();
        assertEquals(1, cantidades.get(disponibilidad1));
        assertEquals(1, cantidades.get(disponibilidad2));
    }

    @Test
    void obtenerCantidadesDeServicioRepetido() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);

        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);
        reserva.agregarServicio(vuelo);

        Map<Disponibilidad, Integer> cantidades = reserva.obtenerCantidades();
        assertEquals(2, cantidades.get(disponibilidad));
    }

    @Test
    void obtenerCantidadesDeServiciosQueCompartenDisponibilidad() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        ServicioIndividual vueloEconomico = mock(ServicioIndividual.class);
        ServicioIndividual vueloPremium = mock(ServicioIndividual.class);

        when(vueloEconomico.obtenerServicios()).thenReturn(List.of(vueloEconomico));
        when(vueloPremium.obtenerServicios()).thenReturn(List.of(vueloPremium));
        when(vueloEconomico.getDisponibilidad()).thenReturn(disponibilidad);
        when(vueloPremium.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vueloEconomico);
        reserva.agregarServicio(vueloPremium);

        Map<Disponibilidad, Integer> cantidades = reserva.obtenerCantidades();
        assertEquals(1, cantidades.size());
        assertEquals(2, cantidades.get(disponibilidad));
    }

    @Test
    void bloquearCuposCorrectamente() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);
        reserva.agregarServicio(vuelo);
        reserva.bloquearCupos();

        assertEquals(8, disponibilidad.getCuposDisponibles());
    }

    @Test
    void noBloquearCuposSiSonInsuficientes() {
        Disponibilidad disponibilidadVuelo = new Disponibilidad(10);
        Disponibilidad disponibilidadHotel = new Disponibilidad(0);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        ServicioIndividual hotel = mock(ServicioIndividual.class);

        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(hotel.obtenerServicios()).thenReturn(List.of(hotel));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidadVuelo);
        when(hotel.getDisponibilidad()).thenReturn(disponibilidadHotel);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);
        reserva.agregarServicio(hotel);

        assertThrows(CupoInsuficienteException.class, () -> reserva.bloquearCupos());

        assertEquals(10, disponibilidadVuelo.getCuposDisponibles());
        assertEquals(0, disponibilidadHotel.getCuposDisponibles());
    }


    @Test
    void liberarCuposCorrectamente() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);

        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);
        reserva.agregarServicio(vuelo);

        reserva.bloquearCupos();
        reserva.liberarCupos();

        assertEquals(10, disponibilidad.getCuposDisponibles());
    }

    @Test
    void noPermitirLiberarSinBloquear() {
        Reserva reserva = new Reserva();
        assertThrows(OperacionInvalidaException.class, () -> reserva.liberarCupos());
    }

    @Test
    void noPermitirBloquearReservaVacia() {
        Reserva reserva = new Reserva();
        assertThrows(OperacionInvalidaException.class, () -> reserva.bloquearCupos());
    }

    @Test
    void confirmarReservaBloqueaCupos() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);
        reserva.confirmar();

        assertEquals(9, disponibilidad.getCuposDisponibles());
        assertInstanceOf(Confirmada.class, reserva.getEstado());
    }

    @Test
    void cancelarReservaConfirmadaLiberaCupos() {
        Disponibilidad disponibilidad = new Disponibilidad(10);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);
        reserva.confirmar();
        reserva.cancelar();

        assertEquals(10, disponibilidad.getCuposDisponibles());
        assertInstanceOf(Cancelada.class, reserva.getEstado());
    }

    @Test
    void confirmarSinCuposMantieneReservaPendiente() {
        Disponibilidad disponibilidad = new Disponibilidad(0);
        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(vuelo.getDisponibilidad()).thenReturn(disponibilidad);

        Reserva reserva = new Reserva();
        reserva.agregarServicio(vuelo);

        assertThrows(CupoInsuficienteException.class, () -> reserva.confirmar());

        assertInstanceOf(SolicitudPendiente.class, reserva.getEstado());
    }
}
