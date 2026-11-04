package comprobantes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ComprobanteCBUTest {

    ComprobanteCBU comprobanteCBU;

    //Set up
    @BeforeEach
    public void setUp() {
        comprobanteCBU = new ComprobanteCBU(500.0, "0000003100010000000001", "0000003100020000000002", "OP-123");
    }

    //Testing
    @Test
    public void getMontoTest() {
        //Verify (Assert)
        assertEquals(500.0, comprobanteCBU.getMonto());
    }

    @Test
    public void getFechaTest() {
        //Verify (Assert)
        assertEquals(LocalDate.now(), comprobanteCBU.getFecha());
    }

    @Test
    public void getCbuOrigenTest() {
        //Verify (Assert)
        assertEquals("0000003100010000000001", comprobanteCBU.getCbuOrigen());
    }

    @Test
    public void getCbuDestinoTest() {
        //Verify (Assert)
        assertEquals("0000003100020000000002", comprobanteCBU.getCbuDestino());
    }

    @Test
    public void getNumeroOperacionTest() {
        //Verify (Assert)
        assertEquals("OP-123", comprobanteCBU.getNumeroOperacion());
    }
}
