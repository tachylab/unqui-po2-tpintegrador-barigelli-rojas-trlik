package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoTextoTest {

    TipoTexto tipoTexto;

    //Set up
    @BeforeEach
    public void setUp() {
        tipoTexto = new TipoTexto();
    }

    //Testing
    @Test
    public void esValidoConTextoTest() {
        //Verify (Assert)
        assertTrue(tipoTexto.esValido("Texto"));
    }

    @Test
    public void esValidoConTextoVacioTest() {
        //Verify (Assert)
        assertTrue(tipoTexto.esValido(""));
    }

    @Test
    public void noEsValidoConNumeroTest() {
        //Verify (Assert)
        assertFalse(tipoTexto.esValido(10));
    }

    @Test
    public void noEsValidoConNullTest() {
        //Verify (Assert)
        assertFalse(tipoTexto.esValido(null));
    }
}
