package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PorPrecioMaxTest {

    PorPrecioMax porPrecioMax;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        porPrecioMax = new PorPrecioMax(100.0);
        item = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void precioMenorAlMaximoCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.precioBase()).thenReturn(50.0);
        //Verify (Assert)
        assertTrue(porPrecioMax.evaluar(item));
        //Verify (Mockito)
        verify(item).precioBase();
    }

    @Test
    public void precioIgualAlMaximoCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.precioBase()).thenReturn(100.0);
        //Verify (Assert)
        assertTrue(porPrecioMax.evaluar(item));
        //Verify (Mockito)
        verify(item).precioBase();
    }

    @Test
    public void precioMayorAlMaximoNoCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.precioBase()).thenReturn(100.01);
        //Verify (Assert)
        assertFalse(porPrecioMax.evaluar(item));
        //Verify (Mockito)
        verify(item).precioBase();
    }

    @Test
    public void usaElPrecioBaseYNoElFinalTest() {
        //Stubbing del comportamiento esperado
        when(item.precioBase()).thenReturn(80.0);
        //Exercise
        porPrecioMax.evaluar(item);
        //Verify (Mockito)
        verify(item).precioBase();
        verify(item, never()).precioFinal();
    }
}
