package trasladosYTasas;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrasladoVipTest {

    TrasladoVip trasladoVip;
    LibreriaTrasladoVip vip;

    // Set up
    @BeforeEach
    public void setUp() {
        vip = mock(LibreriaTrasladoVip.class);
        trasladoVip = new TrasladoVip(vip, 2000f);
    }

    // Testing
    @Test
    public void calcularCostoTest() {
        // Stubbing del comportamiento esperado
        when(vip.calcularCosto(2000f)).thenReturn(2500f);

        // Verify (Assert)
        assertEquals(BigDecimal.valueOf(2500f), trasladoVip.calcularCosto());

        // Verify (Mockito)
        verify(vip).calcularCosto(2000f);
    }

    @Test
    public void calcularCostoCeroTest() {
        // Stubbing del comportamiento esperado
        when(vip.calcularCosto(2000f)).thenReturn(0f);

        // Verify (Assert)
        assertEquals(BigDecimal.valueOf(0.0f), trasladoVip.calcularCosto());

        // Verify (Mockito)
        verify(vip).calcularCosto(2000f);
    }

    @Test
    public void calcularCostoConValorDecimalTest() {
        // Stubbing del comportamiento esperado
        when(vip.calcularCosto(2000f)).thenReturn(2750.75f);

        // Verify (Assert)
        assertEquals(BigDecimal.valueOf(2750.75f), trasladoVip.calcularCosto());

        // Verify (Mockito)
        verify(vip).calcularCosto(2000f);
    }
}

