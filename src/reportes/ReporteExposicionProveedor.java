package reportes;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import serviciosYPaquetes.ItemCatalogo;
import serviciosYPaquetes.ServicioIndividual;
import serviciosYPaquetes.PaqueteTuristico;

public class ReporteExposicionProveedor extends Reporte implements VisitanteReportes {

    private Map<String, Integer> cantidadPorProveedor = new HashMap<>();
    private int totalItems;

    public ReporteExposicionProveedor(
            LocalDate periodoDesde,
            LocalDate periodoHasta) {

        super(periodoDesde, periodoHasta);
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

        String proveedor = item.getProveedor();

        cantidadPorProveedor.merge(proveedor, 1, Integer::sum);
        totalItems++;

        actualizarDatos();
    }

    private void actualizarDatos() {

        datos.clear();

        for (Map.Entry<String, Integer> entrada : cantidadPorProveedor.entrySet()) {

            double porcentaje =
                    (double) entrada.getValue() / totalItems * 100;

            datos.add(
                    entrada.getKey()
                    + " | Porcentaje del catálogo: "
                    + String.format("%.2f", porcentaje) + "%"
            );
        }
    }
}
