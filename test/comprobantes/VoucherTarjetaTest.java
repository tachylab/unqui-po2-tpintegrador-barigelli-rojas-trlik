package comprobantes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class VoucherTarjetaTest {

    VoucherTarjeta voucherTarjeta;

    //Set up
    @BeforeEach
    public void setUp() {
        voucherTarjeta = new VoucherTarjeta(300.0, "AUT-999", "4321");
    }

    //Testing
    @Test
    public void getMontoTest() {
        //Verify (Assert)
        assertEquals(300.0, voucherTarjeta.getMonto());
    }

    @Test
    public void getFechaTest() {
        //Verify (Assert)
        assertEquals(LocalDate.now(), voucherTarjeta.getFecha());
    }

    @Test
    public void getCodigoAutorizacionTest() {
        //Verify (Assert)
        assertEquals("AUT-999", voucherTarjeta.getCodigoAutorizacion());
    }

    @Test
    public void getUltimosDigitosTarjetaTest() {
        //Verify (Assert)
        assertEquals("4321", voucherTarjeta.getUltimosDigitosTarjeta());
    }
}
