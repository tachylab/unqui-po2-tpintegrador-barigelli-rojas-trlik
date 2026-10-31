package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ServicioIndividualTest {
    //Setup
    ServicioIndividual servicioIndividual;
    List<AtributoDinamico> atributosDinamicos;
    AtributoDinamico atributoDinamico;
    AtributoDinamico atributoDinamico2;
    AtributoDinamico atributoDinamico3;
    @BeforeEach
    public void setUp() {
        servicioIndividual = new ServicioIndividual("1", "ServicioIndividual", "Proveedor", 120.0);
        atributosDinamicos = new ArrayList<>();
        atributoDinamico = mock(AtributoDinamico.class);
        atributoDinamico2 = mock(AtributoDinamico.class);
        atributoDinamico3 = mock(AtributoDinamico.class);
    }

    //Testing
    @Test
    public void getIdTest() {
        //Verify (Assert)
        assertEquals("1", servicioIndividual.getId());
    }

    @Test
    public void getNombreTest() {
        //Verify (Assert)
        assertEquals("ServicioIndividual", servicioIndividual.getNombre());
    }

    @Test
    public void getProveedorTest() {
        //Verify (Assert)
        assertEquals("Proveedor", servicioIndividual.getProveedor());
    }

    @Test
    public void precioBaseTest() {
        //Verify (Assert)
        assertEquals(120.0, servicioIndividual.precioBase());
    }

    @Test
    public void precioFinalTest() {
        //Verify (Assert)
        assertEquals(120.0, servicioIndividual.precioFinal());
    }

    @Test
    public void esValidoSinAtributosTest() {
        //Verify (Assert)
        assertTrue(servicioIndividual.esValido());
    }

    @Test
    public void esValidoConTodosLosAtributosValidosTest() {
        //Setup
        atributosDinamicos.addAll(List.of(atributoDinamico, atributoDinamico2, atributoDinamico3));
        servicioIndividual = new ServicioIndividual("1", "ServicioIndividual", "Proveedor", 120.0, atributosDinamicos);
        //Stubbing del comportamiento esperado
        when(atributoDinamico.esValido()).thenReturn(true);
        when(atributoDinamico2.esValido()).thenReturn(true);
        when(atributoDinamico3.esValido()).thenReturn(true);
        //Verify (Assert)
        assertTrue(servicioIndividual.esValido());
        //Verify (Mockito)
        verify(atributoDinamico).esValido();
        verify(atributoDinamico2).esValido();
        verify(atributoDinamico3).esValido();
    }

    @Test
    public void noEsValidoConUnAtributoInvalidoTest() {
        //Setup
        atributosDinamicos.addAll(List.of(atributoDinamico, atributoDinamico2, atributoDinamico3));
        servicioIndividual = new ServicioIndividual("1", "ServicioIndividual", "Proveedor", 120.0, atributosDinamicos);
        //Stubbing del comportamiento esperado
        when(atributoDinamico.esValido()).thenReturn(true);
        when(atributoDinamico2.esValido()).thenReturn(false);
        //Verify (Assert)
        assertFalse(servicioIndividual.esValido());
        //Verify (Mockito)
        verify(atributoDinamico).esValido();
        verify(atributoDinamico2).esValido();
    }
}
