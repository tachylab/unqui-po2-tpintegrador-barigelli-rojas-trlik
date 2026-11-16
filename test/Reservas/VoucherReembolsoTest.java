package Reservas;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VoucherReembolsoTest {

    @Test
    void unVoucherConservaElMontoDelReembolso() {
        VoucherReembolso voucher = new VoucherReembolso(15000);
        assertEquals(15000, voucher.getMonto());
    }

    @Test
    void noSePuedeCrearUnVoucherConMontoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new VoucherReembolso(-100));
    }

    @Test
    void noSePuedeCrearUnVoucherConMontoCero() {
        assertThrows(IllegalArgumentException.class, () -> new VoucherReembolso(0));
    }

}
