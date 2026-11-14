package trasladosYTasas;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrasladosYTasasTest {

    TrasladosYTasas traslados;
    TipoDeTraslado tipoDeTraslado;

    // Set up
    @BeforeEach
    public void setUp() {
        tipoDeTraslado = mock(TipoDeTraslado.class);
        traslados = new TrasladosYTasas(tipoDeTraslado);
    }

    // Testing
    @Test
    public void calcularCostoTest() {
        // Stubbing del comportamiento esperado
        BigDecimal costoEsperado = new BigDecimal("1500.50");
        when(tipoDeTraslado.calcularCosto()).thenReturn(costoEsperado);

        // Verify (Assert)
        assertEquals(costoEsperado, traslados.calcularCosto());

        // Verify (Mockito)
        verify(tipoDeTraslado).calcularCosto();
    }

    @Test
    public void calcularCostoCeroTest() {
        // Stubbing del comportamiento esperado
        when(tipoDeTraslado.calcularCosto()).thenReturn(BigDecimal.ZERO);

        // Verify (Assert)
        assertEquals(BigDecimal.ZERO, traslados.calcularCosto());

        // Verify (Mockito)
        verify(tipoDeTraslado).calcularCosto();
    }
}