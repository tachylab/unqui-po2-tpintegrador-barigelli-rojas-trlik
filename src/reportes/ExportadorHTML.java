package reportes;

public class ExportadorHTML implements ExportadorReporte {

    @Override
    public String exportar(Reporte reporte) {

        StringBuilder resultado = new StringBuilder();

        resultado.append("<html>");
        resultado.append("<head>");
        resultado.append("<meta charset=\"UTF-8\">");
        resultado.append("<title>Reporte</title>");
        resultado.append("</head>");
        resultado.append("<body>");
        resultado.append("<h1>Reporte</h1>");
        resultado.append("<ul>");

        for (String dato : reporte.getDatos()) {
            resultado.append("<li>")
                    .append(dato)
                    .append("</li>");
        }

        resultado.append("</ul>");
        resultado.append("</body>");
        resultado.append("</html>");

        return resultado.toString();
    }
}

