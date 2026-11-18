package reportes;

public class ExportadorTexto implements ExportadorReporte {

    @Override
    public String exportar(Reporte reporte) {

        StringBuilder resultado = new StringBuilder();

        for (String dato : reporte.getDatos()) {
            resultado.append(dato).append("\n");
        }

        return resultado.toString();
    }
}
