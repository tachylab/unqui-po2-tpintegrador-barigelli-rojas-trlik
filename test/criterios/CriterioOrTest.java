package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioOrTest {

    CriterioOr criterioOr;
    CriterioBusqueda izquierdo;
    CriterioBusqueda derecho;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        izquierdo = mock(CriterioBusqueda.class);
        derecho = mock(CriterioBusqueda.class);
        item = mock(ItemCatalogo.class);
        criterioOr = new CriterioOr(izquierdo, derecho);
    }

    //Testing
    @Test
    public void izquierdoVerdaderoEsVerdaderoYNoEvaluaElDerechoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertTrue(criterioOr.evaluar(item));
        //Verify (Mockito)
        verify(izquierdo).evaluar(item);
        verify(derecho, never()).evaluar(any());
    }

    @Test
    public void derechoVerdaderoEsVerdaderoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(false);
        when(derecho.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertTrue(criterioOr.evaluar(item));
        //Verify (Mockito)
        verify(izquierdo).evaluar(item);
        verify(derecho).evaluar(item);
    }

    @Test
    public void ambosVerdaderosEsVerdaderoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(true);
        when(derecho.evaluar(item)).thenReturn(true);
        //Verify (Assert)
        assertTrue(criterioOr.evaluar(item));
    }

    @Test
    public void ambosFalsosEsFalsoTest() {
        //Stubbing del comportamiento esperado
        when(izquierdo.evaluar(item)).thenReturn(false);
        when(derecho.evaluar(item)).thenReturn(false);
        //Verify (Assert)
        assertFalse(criterioOr.evaluar(item));
        //Verify (Mockito)
        verify(izquierdo).evaluar(item);
        verify(derecho).evaluar(item);
    }
}
