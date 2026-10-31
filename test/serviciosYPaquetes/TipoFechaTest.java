package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TipoFechaTest {

    TipoFecha tipoFecha;

    //Set up
    @BeforeEach
    public void setUp() {
        tipoFecha = new TipoFecha();
    }

    //Testing
    @Test
    public void esValidoConFechaTest() {
        //Verify (Assert)
        assertTrue(tipoFecha.esValido(LocalDate.of(2026, 10, 31)));
    }

    @Test
    public void noEsValidoConTextoTest() {
        //Verify (Assert)
        assertFalse(tipoFecha.esValido("2026-10-31"));
    }

    @Test
    public void noEsValidoConNullTest() {
        //Verify (Assert)
        assertFalse(tipoFecha.esValido(null));
    }
}
