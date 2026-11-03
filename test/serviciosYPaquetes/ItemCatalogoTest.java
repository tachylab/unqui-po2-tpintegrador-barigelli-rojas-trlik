package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemCatalogoTest {

    ItemCatalogo itemCatalogo;

    //Set up
    @BeforeEach
    public void setUp() {
        itemCatalogo = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void getIdTest() {
        //Stubbing del comportamiento esperado
        when((itemCatalogo).getId()).thenReturn("1");
        //Verify (Assert)
        assertEquals("1", itemCatalogo.getId());
        //Verify (Mockito)
        verify(itemCatalogo).getId();
    }

    @Test
    public void getNombreTest() {
        //Stubbing del comportamiento esperado
        when((itemCatalogo).getNombre()).thenReturn("ItemGenérico");
        //Verify (Assert)
        assertEquals("ItemGenérico", itemCatalogo.getNombre());
        //Verify (Mockito)
        verify(itemCatalogo).getNombre();
    }

    @Test
    public void getProveedorTest() {
        //Stubbing del comportamiento esperado
        when((itemCatalogo).getProveedor()).thenReturn("Proveedor");
        //Verify (Assert)
        assertEquals("Proveedor", itemCatalogo.getProveedor());
        //Verify (Mockito)
        verify(itemCatalogo).getProveedor();
    }

    @Test
    public void getCategoriaTest() {
        //Stubbing del comportamiento esperado
        when((itemCatalogo).getCategoria()).thenReturn("Categoria");
        //Verify (Assert)
        assertEquals("Categoria", itemCatalogo.getCategoria());
        //Verify (Mockito)
        verify(itemCatalogo).getCategoria();

    }
}
