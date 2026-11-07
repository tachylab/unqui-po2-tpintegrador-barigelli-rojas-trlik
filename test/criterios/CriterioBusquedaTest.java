package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioBusquedaTest {

    CriterioBusqueda criterio;
    CriterioBusqueda otro;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        //Mock de la clase abstracta que ejecuta los métodos concretos (and, or, not)
        criterio = mock(CriterioBusqueda.class, CALLS_REAL_METHODS);
        otro = mock(CriterioBusqueda.class);
        item = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void andCombinaAmbosCriteriosTest() {
        //Stubbing del comportamiento esperado
        doReturn(true).when(criterio).evaluar(item);
        when(otro.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertTrue(criterio.and(otro).evaluar(item));
        //Verify (Mockito)
        verify(criterio).evaluar(item);
        verify(otro).evaluar(item);
    }

    @Test
    public void orCombinaAmbosCriteriosTest() {
        //Stubbing del comportamiento esperado
        doReturn(false).when(criterio).evaluar(item);
        when(otro.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertTrue(criterio.or(otro).evaluar(item));
        //Verify (Mockito)
        verify(criterio).evaluar(item);
        verify(otro).evaluar(item);
    }

    @Test
    public void notNiegaElCriterioTest() {
        //Stubbing del comportamiento esperado
        doReturn(true).when(criterio).evaluar(item);
        //Verify (Assert)
        assertFalse(criterio.not().evaluar(item));
        //Verify (Mockito)
        verify(criterio).evaluar(item);
    }

    @Test
    public void composicionEncadenadaTest() {
        //Setup: (criterio AND otro) OR NOT(criterio)
        CriterioBusqueda compuesto = criterio.and(otro).or(criterio.not());
        //Stubbing del comportamiento esperado
        doReturn(true).when(criterio).evaluar(item);
        when(otro.evaluar(item)).thenReturn(false);
        //Verify (Assert)
        assertFalse(compuesto.evaluar(item));
    }
}
