package criterios;

import serviciosYPaquetes.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PorDisponibilidadTest {

    PorDisponibilidad porDisponibilidad;
    ItemCatalogo item;

    //Set up
    @BeforeEach
    public void setUp() {
        porDisponibilidad = new PorDisponibilidad();
        item = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void itemConCupoDisponibleCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.tieneCupoDisponible()).thenReturn(true);
        //Verify (Assert)
        assertTrue(porDisponibilidad.evaluar(item));
        //Verify (Mockito)
        verify(item).tieneCupoDisponible();
    }

    @Test
    public void itemSinCupoDisponibleNoCumpleTest() {
        //Stubbing del comportamiento esperado
        when(item.tieneCupoDisponible()).thenReturn(false);
        //Verify (Assert)
        assertFalse(porDisponibilidad.evaluar(item));
        //Verify (Mockito)
        verify(item).tieneCupoDisponible();
    }

    @Test
    public void evaluarNoReservaNiLiberaCuposTest() {
        //Stubbing del comportamiento esperado
        when(item.tieneCupoDisponible()).thenReturn(true);
        //Exercise
        porDisponibilidad.evaluar(item);
        //Verify (Mockito)
        verify(item, never()).reservarCupo();
        verify(item, never()).liberarCupo();
    }
}
