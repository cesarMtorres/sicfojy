/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.ConexionReporte;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.Principal;
/**
 *
 * @author Jose Navarro
 */
public class ctrlReporte {
    ConexionReporte conexion;
    private final String logo= "/Reportes/org.jpg";
    private final String ministerio= "/Reportes/cintillo.png";
    
    public ctrlReporte () {
    }



 /**
    *Genera un reporte general existente en la ruta proporcionada
     * @param ruta_reporte
     * @param titulo
     
    */
    public void generar_reporte(String ruta_reporte, String titulo){
         ConexionReporte cn = new ConexionReporte();
       
       String ruta;
        JasperReport JR;
         Properties su = System.getProperties();
              String SO = su.getProperty("os.name").toUpperCase();
                if(SO.equals("LINUX"))
                    {
                        ruta = "/opt/Reportes/";
                    }
                    else
                        {
                            ruta = "C:\\Users\\Danny\\Documents\\NetBeansProjects\\SICFOJYv3\\src\\vista\\";
                        }
        try {
        Map parametro = new HashMap();
        parametro.put("cintillo", this.getClass().getResourceAsStream("/opt/Reportes/cintillo.jpg"));
        parametro.put("ruta_logo",this.getClass().getResourceAsStream(logo));
        parametro.put("ruta_ministerio",this.getClass().getResourceAsStream(ministerio));
        parametro.put("nombre_usuario", Principal.getUsuario().getText());
        
        JR= (JasperReport) JRLoader.loadObjectFromFile(ruta+ruta_reporte);
        JasperPrint JP= JasperFillManager.fillReport(JR,parametro,cn.conexion());
        JasperViewer jv= new JasperViewer(JP,false);
        jv.setZoomRatio((float) 0.99);
        jv.setTitle(titulo);
        jv.setSize(850, 500);
        jv.setLocation(130,5);
        jv.setResizable(false);
        jv.setVisible(true);
        cn.cierraConexion(); 
    }
        catch(JRException J)
                {
                    JOptionPane.showMessageDialog(null,J);
                }
    }  
    public void generar_reporte_con_campo(String ruta_reporte,String titulo, String codigo)
        {
             ConexionReporte cn = new ConexionReporte();
   
        
         String ruta;
        JasperReport JR;
         Properties su = System.getProperties();
              String SO = su.getProperty("os.name").toUpperCase();
                if(SO.equals("LINUX"))
                    {
                        ruta = "/opt/Reportes/";
                    }
                    else
                        {
                            ruta = "C:\\Users\\Danny\\Documents\\NetBeansProjects\\SICFOJYv3\\src\\vista\\";
                        }
        try {
        Map parametro = new HashMap();
        parametro.put("ruta_logo",this.getClass().getResourceAsStream(logo));
        parametro.put("ruta_ministerio",this.getClass().getResourceAsStream(ministerio));
        parametro.put("nombre_usuario", Principal.getUsuario().getText());
        parametro.put("cedula",codigo);
        parametro.put("SUBREPORT_DIR", ruta);
            System.out.println(ruta+ruta_reporte);
        JR= (JasperReport) JRLoader.loadObjectFromFile(ruta+ruta_reporte);
        JasperPrint JP= JasperFillManager.fillReport(JR,parametro,cn.conexion());
        JasperViewer jv= new JasperViewer(JP,false);
        jv.setZoomRatio((float) 0.99);
        jv.setTitle(titulo);
        jv.setSize(850, 500);
        jv.setLocation(280, 130);
        jv.setResizable(false);
        jv.setVisible(true);
        cn.cierraConexion(); 
    }
        catch(JRException J)
                {
                    JOptionPane.showMessageDialog(null,J);
                }
              
        }
    
   public void generar_reporte_con_campo_fecha(String ruta_reporte,String titulo,Date fecha1,Date fecha2){
             ConexionReporte cn = new ConexionReporte();
        
          //String ruta_ministerio= "/Reportes/LogoIndependecia.png";
          //String ruta_escudo= "/Reportes/EscudoIndependecia.png";
       //String usuarioLocal = Vistas.menuprincipal.usuario.getText();
        
         String ruta;
        JasperReport JR;
         Properties su = System.getProperties();
              String SO = su.getProperty("os.name").toUpperCase();
                if(SO.equals("LINUX"))
                    {
                        ruta = "/opt/terminal";
                    }
                    else
                        {
                            ruta = "C:\\Users\\Danny\\Documents\\NetBeansProjects\\SICFOJYv3\\src\\vista\\";
                        }
        try {
        Map parametro = new HashMap();
        parametro.put("ruta_ministerio",this.getClass().getResourceAsStream(logo));
       // parametro.put("ruta_escudo",this.getClass().getResourceAsStream(escudo));
        parametro.put("fecha1",fecha1);
        parametro.put("fecha2",fecha2);
        System.out.println(fecha1+""+fecha2);
        //parametro.put("nombre_persona", nombre_persona);
        parametro.put("SUBREPORT_DIR", ruta);
            System.out.println(ruta);
        JR= (JasperReport) JRLoader.loadObjectFromFile(ruta+ruta_reporte);
        JasperPrint JP= JasperFillManager.fillReport(JR,parametro,cn.conexion());
        JasperViewer jv= new JasperViewer(JP,false);
        jv.setZoomRatio((float) 0.99);
        jv.setTitle(titulo);
        jv.setSize(850, 500);
        jv.setLocation(280, 130);
        jv.setResizable(false);
        jv.setVisible(true);
        cn.cierraConexion(); 
    }
        catch(JRException J)
                {
                    JOptionPane.showMessageDialog(null,J);
                }
              
        }
}
