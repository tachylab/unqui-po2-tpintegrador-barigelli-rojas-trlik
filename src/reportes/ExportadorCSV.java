package reportes;

public class ExportadorCSV implements ExportadorReporte {

    @Override
    public String exportar(Reporte reporte) {

        StringBuilder resultado = new StringBuilder();

        resultado.append("Datos\n");

        for (String dato : reporte.getDatos()) {
            resultado.append("\"")
                    .append(dato.replace("\"", "\"\""))
                    .append("\"\n");
        }

        return resultado.toString();
    }
}

