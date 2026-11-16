package Reservas;

import java.math.BigDecimal;
import trasladosYTasas.TrasladosYTasas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaTest2 {

    Reserva reserva;

    // Set up
    @BeforeEach
    public void setUp() {
        reserva = new Reserva();
    }

    // Testing
    @Test
    public void calcularCostoTrasladoSinTrasladoAsignadoTest() {
        // Verify (Assert)
        assertEquals(BigDecimal.ZERO, reserva.calcularCostoTraslado());
    }

    @Test
    public void calcularCostoTrasladoConTrasladoAsignadoTest() {
        // Setup
        TrasladosYTasas traslado = mock(TrasladosYTasas.class);

        // Stubbing del comportamiento esperado
        BigDecimal costoEsperado = new BigDecimal("3500.50");
        when(traslado.calcularCosto()).thenReturn(costoEsperado);

        reserva.asignarTraslados(traslado);

        // Verify (Assert)
        assertEquals(costoEsperado, reserva.calcularCostoTraslado());

        // Verify (Mockito)
        verify(traslado).calcularCosto();
    }
}

