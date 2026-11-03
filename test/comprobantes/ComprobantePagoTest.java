package comprobantes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ComprobantePagoTest {

    ComprobantePago comprobante;

    //Set up
    @BeforeEach
    public void setUp() {
        //Se usa una subclase concreta para testear el comportamiento de la clase abstracta
        comprobante = new ComprobanteNulo(250.0);
    }

    //Testing
    @Test
    public void getFechaEsLaFechaDeCreacionTest() {
        //Verify (Assert)
        assertEquals(LocalDate.now(), comprobante.getFecha());
    }
}
