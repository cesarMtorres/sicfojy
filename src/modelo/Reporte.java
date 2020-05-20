package modelo;
import java.sql.DriverManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import java.sql.Connection;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

public class Reporte
{
    private Connection conexion;
    /**
     * @param args argumentos recibidos por la linea de comandos
     */
    public static void main(String[] args) throws Exception
    {
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sicfojy", "postgres", "root");

        JasperReport reporte = (JasperReport) JRLoader.loadObject("report1.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);

        JRHtmlExporter exporter = new JRHtmlExporter();
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
        exporter.exportReport();
    }
}