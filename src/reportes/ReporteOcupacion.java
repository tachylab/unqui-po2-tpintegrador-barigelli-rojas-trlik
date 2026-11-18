package reportes;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import serviciosYPaquetes.ItemCatalogo;
import Reservas.Reserva;
import serviciosYPaquetes.ServicioIndividual;
import serviciosYPaquetes.PaqueteTuristico;

public class ReporteOcupacion extends Reporte implements VisitanteReportes {

    private List<Reserva> reservas;

    private Map<String, Integer> cuposTotales = new HashMap<>();
    private Map<String, Integer> cuposReservados = new HashMap<>();

    public ReporteOcupacion(
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

        String categoria = item.getCategoria();

        cuposTotales.merge(categoria, item.getCupoTotal(), Integer::sum);

        int reservados = 0;

        for (Reserva reserva : reservas) {
            if (reserva.getItems().contains(item)) {
                reservados++;
            }
        }

        cuposReservados.merge(categoria, reservados, Integer::sum);
        actualizarDatos();
    }

    private void actualizarDatos() {

        datos.clear();

        for (String categoria : cuposTotales.keySet()) {
            int total = cuposTotales.get(categoria);
            int reservados = cuposReservados.getOrDefault(categoria, 0);
            int disponibles = total - reservados;

            datos.add(
                    categoria
                    + " | Cupo total: " + total
                    + " | Reservado: " + reservados
                    + " | Disponible: " + disponibles
            );
        }
    }
}

