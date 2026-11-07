package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioNotTest {

    CriterioNot criterioNot;
    CriterioBusqueda criterio;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        criterio = mock(CriterioBusqueda.class);
        item = mock(ItemCatalogo.class);
        criterioNot = new CriterioNot(criterio);
    }

    //Testing
    @Test
    public void niegaUnCriterioVerdaderoTest() {
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertFalse(criterioNot.evaluar(item));
        //Verify (Mockito)
        verify(criterio).evaluar(item);
    }

    @Test
    public void niegaUnCriterioFalsoTest() {
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(item)).thenReturn(false);
        //Verify (Assert)
        assertTrue(criterioNot.evaluar(item));
        //Verify (Mockito)
        verify(criterio).evaluar(item);
    }

    @Test
    public void dobleNegacionDevuelveElValorOriginalTest() {
        //Setup
        CriterioNot dobleNegacion = new CriterioNot(criterioNot);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertTrue(dobleNegacion.evaluar(item));
    }
}
