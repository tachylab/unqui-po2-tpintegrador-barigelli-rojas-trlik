package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PorCategoriaTest {

    PorCategoria porCategoria;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        porCategoria = new PorCategoria("Aventura");
        item = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void itemConLaMismaCategoriaCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.getCategoria()).thenReturn("Aventura");
        //Verify (Assert)
        assertTrue(porCategoria.evaluar(item));
        //Verify (Mockito)
        verify(item).getCategoria();
    }

    @Test
    public void itemConOtraCategoriaNoCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.getCategoria()).thenReturn("Relax");
        //Verify (Assert)
        assertFalse(porCategoria.evaluar(item));
        //Verify (Mockito)
        verify(item).getCategoria();
    }

    @Test
    public void laComparacionDistingueMayusculasTest() {
        //Stubbing del comportamiento esperado
        when(item.getCategoria()).thenReturn("aventura");
        //Verify (Assert)
        assertFalse(porCategoria.evaluar(item));
    }

    @Test
    public void noConsultaOtrosDatosDelItemTest() {
        //Stubbing del comportamiento esperado
        when(item.getCategoria()).thenReturn("Aventura");
        //Exercise
        porCategoria.evaluar(item);
        //Verify (Mockito)
        verify(item, never()).getNombre();
        verify(item, never()).precioBase();
    }
}
