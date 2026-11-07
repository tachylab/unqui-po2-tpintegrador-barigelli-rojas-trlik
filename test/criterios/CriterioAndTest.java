package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioAndTest {

    CriterioAnd criterioAnd;
    CriterioBusqueda izquierdo;
    CriterioBusqueda derecho;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        izquierdo = mock(CriterioBusqueda.class);
        derecho = mock(CriterioBusqueda.class);
        item = mock(ItemCatalogo.class);
        criterioAnd = new CriterioAnd(izquierdo, derecho);
    }

    //Testing
    @Test
    public void ambosVerdaderosEsVerdaderoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(true);
        when(derecho.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertTrue(criterioAnd.evaluar(item));
        //Verify (Mockito)
        verify(izquierdo).evaluar(item);
        verify(derecho).evaluar(item);
    }

    @Test
    public void izquierdoFalsoEsFalsoYNoEvaluaElDerechoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(false);
        //Verify (Assert)
        assertFalse(criterioAnd.evaluar(item));
        //Verify (Mockito)
        verify(izquierdo).evaluar(item);
        verify(derecho, never()).evaluar(any());
    }

    @Test
    public void derechoFalsoEsFalsoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(true);
        when(derecho.evaluar(item)).thenReturn(false);
        //Verify (Assert)
        assertFalse(criterioAnd.evaluar(item));
        //Verify (Mockito)
        verify(izquierdo).evaluar(item);
        verify(derecho).evaluar(item);
    }

    @Test
    public void ambosFalsosEsFalsoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(false);
        when(derecho.evaluar(item)).thenReturn(false);
        //Verify (Assert)
        assertFalse(criterioAnd.evaluar(item));
    }
}
