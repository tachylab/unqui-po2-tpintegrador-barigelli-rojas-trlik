package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoBooleanoTest {

    TipoBooleano tipoBooleano;

    //Set up
    @BeforeEach
    public void setUp() {
        tipoBooleano = new TipoBooleano();
    }

    //Testing
    @Test
    public void esValidoConTrueTest() {
        //Verify (Assert)
        assertTrue(tipoBooleano.esValido(true));
    }

    @Test
    public void esValidoConFalseTest() {
        //Verify (Assert)
        assertTrue(tipoBooleano.esValido(false));
    }

    @Test
    public void noEsValidoConTextoTest() {
        //Verify (Assert)
        assertFalse(tipoBooleano.esValido("true"));
    }

    @Test
    public void noEsValidoConNullTest() {
        //Verify (Assert)
        assertFalse(tipoBooleano.esValido(null));
    }
}
