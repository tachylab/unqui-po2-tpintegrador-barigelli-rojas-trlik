package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoNumericoTest {

    TipoNumerico tipoNumerico;

    //Set up
    @BeforeEach
    public void setUp() {
        tipoNumerico = new TipoNumerico();
    }

    //Testing
    @Test
    public void esValidoConEnteroTest() {
        //Verify (Assert)
        assertTrue(tipoNumerico.esValido(10));
    }

    @Test
    public void esValidoConDecimalTest() {
        //Verify (Assert)
        assertTrue(tipoNumerico.esValido(10.5));
    }

    @Test
    public void noEsValidoConTextoTest() {
        //Verify (Assert)
        assertFalse(tipoNumerico.esValido("10"));
    }

    @Test
    public void noEsValidoConNullTest() {
        //Verify (Assert)
        assertFalse(tipoNumerico.esValido(null));
    }
}
