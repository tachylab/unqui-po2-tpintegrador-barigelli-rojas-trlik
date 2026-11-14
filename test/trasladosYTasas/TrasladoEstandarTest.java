package trasladosYTasas;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrasladoEstandarTest {

    TrasladoEstandar trasladoEstandar;
    PlataformaTransporte transporte;

    // Set up
    @BeforeEach
    public void setUp() {
        transporte = mock(PlataformaTransporte.class);
        trasladoEstandar = new TrasladoEstandar(transporte, 4, "Av. Siempre Viva 123");
    }

    // Testing
    @Test
    public void calcularCostoTest() {
        // Stubbing del comportamiento esperado
        when(transporte.estimarTraslado(4, "Av. Siempre Viva 123")).thenReturn(1500.50f);

        // Verify (Assert)
        assertEquals(new BigDecimal("1500.5"), trasladoEstandar.calcularCosto());

        // Verify (Mockito)
        verify(transporte).estimarTraslado(4, "Av. Siempre Viva 123");
    }

    @Test
    public void calcularCostoCeroTest() {
        // Stubbing del comportamiento esperado
        when(transporte.estimarTraslado(4, "Av. Siempre Viva 123")).thenReturn(0f);

        // Verify (Assert)
        assertEquals(BigDecimal.valueOf(0.0f), trasladoEstandar.calcularCosto());

        // Verify (Mockito)
        verify(transporte).estimarTraslado(4, "Av. Siempre Viva 123");
    }

    @Test
    public void calcularCostoConValorDecimalTest() {
        // Stubbing del comportamiento esperado
        when(transporte.estimarTraslado(4, "Av. Siempre Viva 123")).thenReturn(2750.75f);

        // Verify (Assert)
        assertEquals(BigDecimal.valueOf(2750.75f), trasladoEstandar.calcularCosto());

        // Verify (Mockito)
        verify(transporte).estimarTraslado(4, "Av. Siempre Viva 123");
    }
}
