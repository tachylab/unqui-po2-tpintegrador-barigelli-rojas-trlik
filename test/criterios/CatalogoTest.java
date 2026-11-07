package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class CatalogoTest {

    Catalogo catalogo;
    CriterioBusqueda criterio;
    ItemCatalogo item;
    ItemCatalogo item2;
    ItemCatalogo item3;

    //Set up
    @BeforeEach
    public void setUp() {
        catalogo = new Catalogo();
        criterio = mock(CriterioBusqueda.class);
        item = mock(ItemCatalogo.class);
        item2 = mock(ItemCatalogo.class);
        item3 = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void buscarEnCatalogoVacioTest() {
        //Verify (Assert)
        assertTrue(catalogo.buscar(criterio).isEmpty());
        //Verify (Mockito)
        verifyNoInteractions(criterio);
    }

    @Test
    public void buscarDevuelveSoloLosItemsQueCumplenElCriterioTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        catalogo.agregarItem(item3);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(item)).thenReturn(true);
        when(criterio.evaluar(item2)).thenReturn(false);
        when(criterio.evaluar(item3)).thenReturn(true);
        //Verify (Assert)
        assertEquals(List.of(item, item3), catalogo.buscar(criterio));
        //Verify (Mockito)
        verify(criterio).evaluar(item);
        verify(criterio).evaluar(item2);
        verify(criterio).evaluar(item3);
    }

    @Test
    public void buscarSinCoincidenciasDevuelveListaVaciaTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(any())).thenReturn(false);
        //Verify (Assert)
        assertTrue(catalogo.buscar(criterio).isEmpty());
        //Verify (Mockito)
        verify(criterio, times(2)).evaluar(any());
    }

    @Test
    public void buscarConTodasLasCoincidenciasDevuelveTodosLosItemsTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(any())).thenReturn(true);
        //Verify (Assert)
        assertEquals(List.of(item, item2), catalogo.buscar(criterio));
    }

    @Test
    public void buscarNoModificaElCatalogoTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(item)).thenReturn(true);
        when(criterio.evaluar(item2)).thenReturn(false);
        //Exercise
        catalogo.buscar(criterio);
        CriterioBusqueda todos = mock(CriterioBusqueda.class);
        when(todos.evaluar(any())).thenReturn(true);
        //Verify (Assert)
        assertEquals(List.of(item, item2), catalogo.buscar(todos));
    }
}
