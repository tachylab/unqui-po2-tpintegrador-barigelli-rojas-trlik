package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AtributoDinamicoTest {

    AtributoDinamico atributoDinamico;
    TipoAtributo tipoAtributo;

    //Set up
    @BeforeEach
    public void setUp() {
        tipoAtributo = mock(TipoAtributo.class);
        atributoDinamico = new AtributoDinamico("Capacidad", true, tipoAtributo, 4);
    }

    //Testing
    @Test
    public void getNombreTest() {
        //Verify (Assert)
        assertEquals("Capacidad", atributoDinamico.getNombre());
    }

    @Test
    public void getEsObligatorioTest() {
        //Verify (Assert)
        assertTrue(atributoDinamico.getEsObligatorio());
    }

    @Test
    public void getTipoAtributoTest() {
        //Verify (Assert)
        assertSame(tipoAtributo, atributoDinamico.getTipoAtributo());
    }

    @Test
    public void getValorTest() {
        //Verify (Assert)
        assertEquals(4, atributoDinamico.getValor());
    }

    @Test
    public void getValorSinValorInicialTest() {
        //Setup
        AtributoDinamico atributoSinValor = new AtributoDinamico("Capacidad", true, tipoAtributo);

        //Verify (Assert)
        assertNull(atributoSinValor.getValor());
    }

    @Test
    public void esValidoConValorDelTipoDeclaradoTest() {
        //Stubbing del comportamiento esperado
        when(tipoAtributo.esValido(4)).thenReturn(true);
        //Verify (Assert)
        assertTrue(atributoDinamico.esValido());
        //Verify (Mockito)
        verify(tipoAtributo).esValido(4);
    }

    @Test
    public void noEsValidoConValorDeOtroTipoTest() {
        //Stubbing del comportamiento esperado
        when(tipoAtributo.esValido(4)).thenReturn(false);
        //Verify (Assert)
        assertFalse(atributoDinamico.esValido());
        //Verify (Mockito)
        verify(tipoAtributo).esValido(4);
    }

    @Test
    public void noEsValidoObligatorioSinValorTest() {
        AtributoDinamico atributoSinValor = new AtributoDinamico("Capacidad", true, tipoAtributo);
        //Stubbing del comportamiento esperado
        when(tipoAtributo.esValido(null)).thenReturn(false);
        //Verify (Assert)
        assertFalse(atributoSinValor.esValido());
    }

    @Test
    public void esValidoOpcionalSinValorTest() {
        AtributoDinamico atributoSinValor = new AtributoDinamico("Capacidad", false, tipoAtributo);
        //Stubbing del comportamiento esperado
        when(tipoAtributo.esValido(null)).thenReturn(false);
        //Verify (Assert)
        assertTrue(atributoSinValor.esValido());
    }
}
