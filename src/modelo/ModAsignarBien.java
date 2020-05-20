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

/**
 *
 * @author rover
 */
public class ModAsignarBien {
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String id_empleado;
    private String nombrets;
    private String nombrebien;
    private String responsable;
    private String justificacion;
    private String fecha;
    private String programa;
    private String modelo;
    private String cantidad;
    private String id;
    private String valor="FALSE";
    private String valor1="TRUE";
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModAsignarBien() {
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombrets() {
        return nombrets;
    }

    public void setNombrets(String nombrets) {
        this.nombrets = nombrets;
    }

    public String getNombrebien() {
        return nombrebien;
    }

    public void setNombrebien(String nombrebien) {
        this.nombrebien = nombrebien;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

  

    
    public boolean Consultar(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.vmoviento_asignar WHERE id='"+id+"';";
            System.out.println("Consultar() SQL .-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setId_empleado(resultadoConsulta.getString("id_persona"));
                    this.setNombrets(resultadoConsulta.getString("nombrets"));
                    this.setNombrebien(resultadoConsulta.getString("nombreb"));
                    this.setModelo(resultadoConsulta.getString("nombremd"));
                    this.setPrograma(resultadoConsulta.getString("nombrepro"));
                    this.setCantidad(resultadoConsulta.getString("cantidad"));
                    this.setResponsable(resultadoConsulta.getString("nombreper"));
                    this.setJustificacion(resultadoConsulta.getString("justificacion"));
                    this.setFecha(resultadoConsulta.getString("fecha_move"));
                    
                    
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("id bien: "+this.getId());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModAsignarBien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
     
     
      public void cargarDatosCatalogoBien(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT * FROM public.vbien WHERE eliminacion='TRUE' order by id desc;";
             System.out.println("CargarDatosCatalagobien() "+ sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=9;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Id","Modelo"," Serial","Marca","Tipo Bien","Medida","Cantidad","Numero Inventario","Fecha Nacimiento"};
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
                     Logger.getLogger(ModAsignarBien.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos
      
        
          
      public boolean CargarTablaBien(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT nombreb,serial,nombremo,nombrema,cantidad,nombretb FROM public.vbien";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaBien(ptabcargo);
        System.out.println("CargarTablaBien() " +tiraSQL);
        
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
            Logger.getLogger(ModAsignarBien.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaBien(JTable ptabcargo) {
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
                      
       String tirasql= "SELECT * FROM public.vmoviento_asignar WHERE nombre_move='ASIGNAR' ORDER BY fecha_move DESC LIMIT 10";
   /*    String tirasql= "SELECT id,nombre_move,fecha_move,nombreper,nombreb, COUNT(*) as cantidad FROM public.vmovientos WHERE nombre_move='ASIGNAR'\n" +
"     GROUP BY id,nombre_move,fecha_move,nombreper,nombreb\n" +
"     HAVING COUNT(*)=1 OR COUNT(*)>1"; */
        System.out.println("consultarListado() "+tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
        
        if(resultadoConsulta == null) return null;
        int i = 0;
        try {
            
           while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][6];
                i = 0;
                resultadoConsulta.beforeFirst();
              
                while(resultadoConsulta.next()){
                                        
                    datos[i][0] = resultadoConsulta.getString("id");
                    datos[i][1] = resultadoConsulta.getString("nombre_move");
                    datos[i][2] = resultadoConsulta.getString("fecha_move");
                    datos[i][3] = resultadoConsulta.getString("nombreper");
                    datos[i][4] = resultadoConsulta.getString("nombreb");
                    datos[i][5] = resultadoConsulta.getString("cantidad");
                    
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
 
     public boolean inhabilitar(String id) {
        boolean resultado = false;
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String SQL= "UPDATE bien SET "
                + "eliminacion= 'FALSE'"
                + " WHERE id= '"+id+"';";
        
        int ejecutar = conexion.ejecutarActualizacion(SQL);
        
        if(ejecutar!=0) {
            resultado = true;
        } else {
            resultado = false;
        }
        conexion.cerrar();
        return resultado;
    }
    
     public boolean Activar(String id){
        boolean statusA;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String tirasql = "UPDATE bien SET eliminacion ='TRUE' WHERE id='"+id+"';";
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
     
  
     
}
