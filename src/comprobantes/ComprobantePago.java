package comprobantes;

import java.time.LocalDate;

public abstract class ComprobantePago {
    //Atributo
    private LocalDate fecha;
    private double monto;

    //Constructor
    public ComprobantePago(double monto) {
        this.fecha = LocalDate.now();
        this.monto = monto;
    }

    //Getters
    public LocalDate getFecha() {
        return this.fecha;
    }

    public double getMonto() {
        return this.monto;
    }
}
