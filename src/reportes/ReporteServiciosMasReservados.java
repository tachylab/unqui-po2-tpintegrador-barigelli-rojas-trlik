package reportes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
//import java.util.Comparator;
import serviciosYPaquetes.ItemCatalogo;
import serviciosYPaquetes.ServicioIndividual;
import serviciosYPaquetes.PaqueteTuristico;
import Reservas.Reserva;

public class ReporteServiciosMasReservados extends Reporte implements VisitanteReportes {

    private List<Reserva> reservas;
    private Map<ItemCatalogo, Integer> cantidadesReservadas = new HashMap<>();
    private Map<ItemCatalogo, Double> preciosTotales = new HashMap<>();

    public ReporteServiciosMasReservados(
            LocalDate periodoDesde,
            LocalDate periodoHasta,
            List<Reserva> reservas) {

        super(periodoDesde, periodoHasta);
        this.reservas = reservas;
    }

    @Override
    public void visitar(ServicioIndividual servicio) {
        procesarItem(servicio);
    }

    @Override
    public void visitar(PaqueteTuristico paquete) {
        procesarItem(paquete);
    }

    private void procesarItem(ItemCatalogo item) {

        if (item.getFechaInicio().isAfter(periodoHasta)
                || item.getFechaFin().isBefore(periodoDesde)) {
            return;
        }

        int cantidad = 0;
        double sumaPrecios = 0;

        for (Reserva reserva : reservas) {
            if (reserva.getItems().contains(item)) {
                cantidad++;

                Double precio = reserva.getPreciosAplicados().get(item);

                if (precio != null) {
                    sumaPrecios += precio;
                }
            }
        }

        if (cantidad > 0) {
            cantidadesReservadas.put(item, cantidad);
            preciosTotales.put(item, sumaPrecios);
        }

        actualizarDatos();
    }

    private void actualizarDatos() {

        datos.clear();

        cantidadesReservadas.entrySet().stream()
                .sorted(Map.Entry.<ItemCatalogo, Integer>comparingByValue()
                        .reversed())
                .forEach(entrada -> {
                    ItemCatalogo item = entrada.getKey();
                    int cantidad = entrada.getValue();

                    double promedio = preciosTotales.get(item) / cantidad;

                    datos.add(
                            item.getNombre()
                            + " | Reservas: " + cantidad
                            + " | Precio promedio: " + promedio
                    );
                });
    }
}
