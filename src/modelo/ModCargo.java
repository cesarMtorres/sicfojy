/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
//import static com.sun.org.apache.xerces.internal.util.XMLChar.trim;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeString.trim;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
/**
 *
 * @author rover
 */
public class ModCargo extends ModeloBaseDatos  {
    ConexionBD conexion  = new ConexionBD();
    String valor="TRUE";
    String valor2="FALSE";
    
    // Datos de Usuario
    private String status;
    private String descrip;
    private String nombre;
    public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    private String id;
    private final String logotipo = "/images/busqueda1.png";
    //private JasperViewer jasperViewer;

    public ModCargo() {
    }


    public ModCargo(String id, String nombre, String descrip) {
        this.descrip = descrip;
        this.nombre = nombre;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
    
    public ModCargo(String nombre, String descrip) {
        this.descrip = descrip;
        this.nombre = nombre;
 
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


 

    public boolean Consultar(String id){
       
            boolean statusConsulta=false;
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.cargo WHERE nombrecar='"+id+"'";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setDescrip(resultadoConsulta.getString("descripcion"));
                    this.setNombre(resultadoConsulta.getString("nombrecar"));
                     this.setStatus(resultadoConsulta.getString("eliminacion"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("estatus: "+getStatus());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarCargo(){
        boolean statusIncluir;
        conexion.conectar();
                               
        String sentenciaSql = "INSERT INTO public.cargo (nombrecar,descripcion,eliminacion) VALUES ('"+nombre+"','"+descrip+"','"+valor+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarCargo(String id){
        boolean statusModificar;
        conexion.conectar();
        System.out.println("modificar cargo");
        String sentenciaSql = "UPDATE public.cargo SET nombrecar='"+nombre+"', descripcion='"+descrip+"' WHERE id='"+id+"';";
        System.out.println("SQL.-"+sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoCargo(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT * FROM public.cargo WHERE eliminacion='TRUE' order by nombrecar asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=2;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Nombre"," Descripcion"};
                     for (int i = 0; i < cantidadColumnas; i++) {
                         tablaModel.addColumn(titulo[i]);
                     }
                     
                     //Creando las filas para el JTable
                     while (resul.next()) {
                         Object[] fila = new Object[cantidadColumnas];
                         for (int i = 0; i < cantidadColumnas; i++) {
                             fila[i]=resul.getObject(i+1);
                             System.out.println(resul.getObject(i+1)+" ");
                         }
                         tablaModel.addRow(fila);
                         
                     }
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(ModCargo.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos
      
          public boolean Eliminar(String id){
        boolean statusEliminar=false;

        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.cargo set eliminacion='"+valor2+"' WHERE id='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        System.out.println("SQL.-"+sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        conexion.cerrar();       
 
        
        return statusEliminar;
    }//fin de incluir 

        public boolean inhabilitar(String cargo) {
        boolean resultado = false;
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String SQL= "UPDATE cargo SET "
                + "eliminacion= 'FALSE'"
                + " WHERE id = '"+cargo+"';";
        
        int ejecutar = conexion.ejecutarActualizacion(SQL);
        
        if(ejecutar!=0) {
            resultado = true;
        } else {
            resultado = false;
        }
        conexion.cerrar();
        return resultado;
    }
    
     public boolean Activar(String cargo){
        boolean statusA;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String tirasql = "UPDATE cargo SET eliminacion ='TRUE' WHERE id='"+cargo+"';";
        System.out.println(tirasql);
        int ejecutado = conexion.ejecutarActualizacion(tirasql);
        
        if(ejecutado!=0){
            statusA= true;
        }else{
            statusA = false;
        }
        
        conexion.cerrar();
        return statusA;
     }
     
          
      public boolean CargarTablaCargo(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT nombrecar,descripcion FROM public.cargo";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaCargo(ptabcargo);
        System.out.println(tiraSQL);
        
//        conn = crearConexion();
        System.out.println(conn);
 //       rs = consultarDatos(conn, tiraSQL);
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);        
        try {
            if (rs!=null) {
                ///existe = rs.next();
                existe = true;
                //if (existe){
                    //rs.first();
                    rsm = rs.getMetaData();
                    ArrayList<Object[]> datos;
                    datos = new ArrayList();
                    while (rs.next()) {
                        Object[] rows = new Object[rsm.getColumnCount()];
                        for (int i = 0; i < rows.length; i++) {
                            rows[i] = rs.getObject(i + 1);   
                        }
                        datos.add(rows);
                        //ptabCargo.setToolTipText("Doble Click para Seleccionar Prtfil");
                    }
                    DefaultTableModel dtm = (DefaultTableModel) ptabcargo.getModel();
                    for (int i = 0; i < datos.size(); i++) {
                        dtm.addRow(datos.get(i));
                    }
                 }
            //} else {
            //    System.out.println("No Existe el Cargo Consultado...");
           // }
        } catch (SQLException ex) {
            Logger.getLogger(ModCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaCargo(JTable ptabcargo) {
        DefaultTableModel temp;
        try {
            temp = (DefaultTableModel) ptabcargo.getModel();
            int a = temp.getRowCount() - 1;
            for (int i = 0; i <= a; i++) {
                temp.removeRow(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
          
          public String[][] consultarListado() {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT * FROM public.cargo WHERE eliminacion='TRUE'";
        System.out.println("consultarListado() "+tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
        
        if(resultadoConsulta == null) return null;
        int i = 0;
        try {
            
           while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][2];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    
                    datos[i][0] = resultadoConsulta.getString("nombrecar");
                    datos[i][1] = resultadoConsulta.getString("descripcion");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
          
          
            public String[][] Filtro (String busqueda){
       
            boolean estatusConsulta=false;
            
            //conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT * FROM public.cargo WHERE nombrecar LIKE '%"+busqueda+"%'";
            System.out.println("desde Filtro() "+ tirasql);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            if(resultadoConsulta == null) return null;
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][2];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    
                    
                    datos[i][0] = resultadoConsulta.getString("nombrecar");
                    datos[i][1] = resultadoConsulta.getString("descripcion");
                   
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
   public ArrayList<String> CargarCargo() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion.conectar();        
        String tiraSQL = "SELECT nombrecar FROM public.cargo WHERE eliminacion='TRUE' order by nombrecar";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        if (rs==null){
            ListPartida.add("SIN CARGO");
        }else{
               try {
            ListPartida.add(("SELECCIONAR"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombrecar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
               
        return ListPartida;
    }
       public boolean imprimirCargo(String pCodCargo) throws JRException, IOException {
        int velim = 0;
        boolean sw = false;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Seguro de Imprimir el Registro?", "Confirmar Impresión", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
           conn = crearConexion();
            try {
                // Obtener ruta de la clase. Cambia MiClase por el nombre de la clase desde donde estas
                String ubica = ModCargo.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                File aux =new File(ubica);
                if (aux.isDirectory())
                    ubica = ubica + "C:\\Users\\Danny\\Documents\\NetBeansProjects\\SICFOJYv3\\src\\Reportes\\cargo.jrxml";
                else
                    ubica = aux.getParent() + "C:\\Users\\Danny\\Documents\\NetBeansProjects\\SICFOJYv3\\src\\Reportes\\cargo.jrxml";
                
                System.out.println(ubica + trim(pCodCargo)+"h");
                reporte = (JasperReport) JRLoader.loadObject(ubica);
                pCodCargo = trim(pCodCargo);
                String pcrit = "";
                if(!"".equals(pCodCargo)) {
                    pcrit = " WHERE cargo.id = '"+ pCodCargo +"'";
                }
                System.out.println(pcrit);
                Map parametro = new HashMap();
                parametro.put("", pcrit);
                parametro.put("", this.getClass().getResourceAsStream(logotipo));
                
                JasperPrint print = JasperFillManager.fillReport(reporte, parametro, conn);
                
                /*JasperViewer jviewer = new JasperViewer(print, false);
                jviewer.setTitle("C.A.P.P.D.I.U.T.Y. - Reporte de Usuarios");
                jviewer.setVisible(true);
                */
                JDialog viewer = new JDialog(new JFrame(), "C.A.P.P.D.I.U.T.Y. - Reporte de Cargo", true);
                viewer.setSize(1024, 768);
                viewer.setLocationRelativeTo(null);
                JRViewer jrv = new JRViewer(print);
                viewer.getContentPane().add(jrv);
                viewer.setVisible(true);
                
            } catch (JRException e) {
            }
            // Fin Visor jasperReport
            cerrarConexion(conn);
            sw = true;
        }
        return sw;
    }
       public boolean imprimirBitacceso(String pCodFun) throws JRException, IOException {
        int velim = 0;
        boolean sw = false;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Seguro de Imprimir el Registro?", "Confirmar Impresión", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
            conn = crearConexion();
            try {
                // Obtener ruta de la clase. Cambia MiClase por el nombre de la clase desde donde estas
                String ubica = ModBitacceso.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                File aux =new File(ubica);
                if (aux.isDirectory())
                    ubica = ubica + "../../src/vista/Marca.jasper";
                else
                    ubica = aux.getParent() + "/../src/vista/Marca.jasper";
                
                //System.out.println(ubica + trim(pCodPerUsu)+"h");
                reporte = (JasperReport) JRLoader.loadObject(ubica);
                pCodFun = pCodFun.trim();
                String pcrit = "";
                if(!"".equals(pCodFun)) {
                    pcrit = "WHERE marca.id_marca= '"+ pCodFun +"'";
                }
                //System.out.println(pcrit);
                Map parametro = new HashMap();
                parametro.put("condic", pcrit);
                parametro.put("logo", this.getClass().getResourceAsStream(logotipo));
                
                JasperPrint print = JasperFillManager.fillReport(reporte, parametro, conn);
                
                /*JasperViewer jviewer = new JasperViewer(print, false);
                jviewer.setTitle("C.A.P.P.D.I.U.T.Y. - Reporte de Usuarios");
                jviewer.setVisible(true);
                */
                JDialog viewer = new JDialog(new JFrame(), "S.I.C.F.O.J.Y - Lista de Cargos", true);
                viewer.setSize(1024, 768);
                viewer.setLocationRelativeTo(null);
                JRViewer jrv = new JRViewer(print);
                viewer.getContentPane().add(jrv);
                viewer.setVisible(true);
                
            } catch (JRException e) {
            }
            // Fin Visor jasperReport
            cerrarConexion(conn);
            sw = true;
        }
        return sw;
    }
}
