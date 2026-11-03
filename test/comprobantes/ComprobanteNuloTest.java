package comprobantes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ComprobanteNuloTest {

    ComprobanteNulo comprobanteNulo;

    //Set up
    @BeforeEach
    public void setUp() {
        comprobanteNulo = new ComprobanteNulo(100.0);
    }

    //Testing
    @Test
    public void getMontoTest() {
        //Verify (Assert)
        assertEquals(100.0, comprobanteNulo.getMonto());
    }

    @Test
    public void getFechaTest() {
        //Verify (Assert)
        assertEquals(LocalDate.now(), comprobanteNulo.getFecha());
    }
}
