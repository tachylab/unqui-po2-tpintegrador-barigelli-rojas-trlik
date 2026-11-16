package Reservas;

public class VoucherReembolso {

    private double monto;

    public VoucherReembolso(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del reembolso debe ser positivo");
        }
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

}
