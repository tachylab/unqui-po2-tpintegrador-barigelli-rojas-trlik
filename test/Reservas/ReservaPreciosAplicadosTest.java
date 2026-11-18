package Reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;
import serviciosYPaquetes.ItemCatalogo;
import serviciosYPaquetes.ServicioIndividual;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaPreciosAplicadosTest {

    Reserva reserva;
    ServicioIndividual servicio;

    @BeforeEach
    public void setUp() {
        reserva = new Reserva();

        servicio = new ServicioIndividual(
            "1", "Hotel", "Proveedor A", "Alojamiento",
            10,
            LocalDate.of(2026, 2, 1),
            LocalDate.of(2026, 2, 5),
            100.0
        );
    }

    @Test
    public void registrarPrecioAplicadoTest() {
        reserva.registrarPrecioAplicado(servicio, 150.0);

        assertEquals(150.0, reserva.getPreciosAplicados().get(servicio));
    }

    @Test
    public void obtenerPreciosAplicadosVaciosTest() {
        assertTrue(reserva.getPreciosAplicados().isEmpty());
    }

    @Test
    public void obtenerPreciosAplicadosDevuelveCopiaInmodificableTest() {
        reserva.registrarPrecioAplicado(servicio, 150.0);

        Map<ItemCatalogo, Double> precios = reserva.getPreciosAplicados();

        assertEquals(150.0, precios.get(servicio));
        assertThrows(UnsupportedOperationException.class,
            () -> precios.put(servicio, 200.0));
    }
}

