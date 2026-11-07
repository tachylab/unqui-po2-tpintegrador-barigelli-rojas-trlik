package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PorNombreTest {

    PorNombre porNombre;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        porNombre = new PorNombre("Bariloche");
        item = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void itemConElMismoNombreCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.getNombre()).thenReturn("Bariloche");
        when(item.getCategoria()).thenReturn("Aventura");
        //Verify (Assert)
        assertTrue(porNombre.evaluar(item));
        //Verify (Mockito)
        verify(item).getNombre();
    }

    @Test
    public void itemConOtroNombreNoCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.getNombre()).thenReturn("Cordoba");
        when(item.getCategoria()).thenReturn("Aventura");
        //Verify (Assert)
        assertFalse(porNombre.evaluar(item));
        //Verify (Mockito)
        verify(item).getNombre();
    }

    @Test
    public void noFiltraPorCategoriaTest() {
        //Stubbing: la categoría coincide con el texto buscado pero el nombre no
        when(item.getNombre()).thenReturn("Cordoba");
        when(item.getCategoria()).thenReturn("Bariloche");
        //Verify (Assert)
        assertFalse(porNombre.evaluar(item));
        //Verify (Mockito)
        verify(item, never()).getCategoria();
    }
}
