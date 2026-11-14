package trasladosYTasas;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PuntoDeEncuentroTest {

    PuntoDeEncuentro puntoDeEncuentro;
    GestorGuias gestorGuias;

    // Set up
    @BeforeEach
    public void setUp() {
        gestorGuias = mock(GestorGuias.class);
        puntoDeEncuentro = new PuntoDeEncuentro("Plaza Central", gestorGuias);
    }

    // Testing
    @Test
    public void calcularCostoTest() {
        // Verify (Assert)
        assertEquals(BigDecimal.ZERO, puntoDeEncuentro.calcularCosto());
    }

    @Test
    public void hayDisponibilidadTest() {
        // Stubbing del comportamiento esperado
        when(gestorGuias.hayCupo("Plaza Central")).thenReturn(true);

        // Verify (Assert)
        assertTrue(puntoDeEncuentro.hayDisponibilidad());

        // Verify (Mockito)
        verify(gestorGuias).hayCupo("Plaza Central");
    }

    @Test
    public void noHayDisponibilidadTest() {
        // Stubbing del comportamiento esperado
        when(gestorGuias.hayCupo("Plaza Central")).thenReturn(false);

        // Verify (Assert)
        assertFalse(puntoDeEncuentro.hayDisponibilidad());

        // Verify (Mockito)
        verify(gestorGuias).hayCupo("Plaza Central");
    }
}
