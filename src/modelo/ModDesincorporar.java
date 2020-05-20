/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author betty
 */
public class ModDesincorporar {
    
    ConexionBD conexion  = new ConexionBD();
    // Datos de Usuario
    private String justificacion;
    private String responsable;
    private String id_empleado;
    private Date fecha;
    private String id;
    private String nombre;
    private String siniestro;
    private String temp;
    private String valor="TRUE";
    private String valor1="FALSE";
    
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModDesincorporar() {
    }

    public ModDesincorporar(String justificacion, String responsable, Date fecha,String siniestro) {
        this.justificacion = justificacion;
        this.responsable = responsable;
        this.fecha = fecha;
        this.siniestro=siniestro;
    }

    public ModDesincorporar(String justificacion, String responsable, Date fecha,String siniestro,String id) {
        this.justificacion = justificacion;
        this.responsable = responsable;
        this.fecha = fecha;
        this.siniestro=siniestro;
        this.id=id;
    }

    public String getSiniestro() {
        return siniestro;
    }

    public void setSiniestro(String siniestro) {
        this.siniestro = siniestro;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public boolean Consultar(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT id,nombreb FROM public.vbien WHERE serial='"+id+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setNombre(resultadoConsulta.getString("nombreb"));

                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModDesincorporar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    public boolean ConsultarTabla(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.vmovientos WHERE nombre_move='DESINCORPORAR' AND nombreb='"+id+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                    
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setId_empleado(resultadoConsulta.getString("id_persona"));
                    this.setNombre(resultadoConsulta.getString("nombreb"));
                    this.setResponsable(resultadoConsulta.getString("nombreper"));
                    this.setSiniestro(resultadoConsulta.getString("nombrets"));
                    this.setJustificacion(resultadoConsulta.getString("justificacion"));
                    this.setFecha(resultadoConsulta.getDate("fecha_move"));
                        
                    System.out.println("ID EMPLEADO: "+getId_empleado());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModRepararBien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
     public boolean ConsultarCantidadBien(String nombre){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT count(cantidad) from bien WHERE nombreb='"+nombre+"' AND eliminacion='TRUE'";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setTemp(resultadoConsulta.getString("cantidad"));
                    System.out.println("cantidad: "+getTemp());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModAsignar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarDesincorporar(String just,String respon,String fecIUsu,String siniestro,String id){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
        int tipo_move=3;
                               
        String sentenciaSql = "INSERT INTO public.movimiento_bien (justificacion,reponsable,fecha_move,id_tipo_movimiento,id_tipo_siniestro,id_bien) VALUES ('"+just+"','"+respon+"','"+fecIUsu+"','"+tipo_move+"','"+siniestro+"','"+id+"' )";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
        sentenciaSql = "UPDATE public.bien SET eliminacion='"+valor1+"',tipo_move='"+tipo_move+"' WHERE id='"+id+"' AND eliminacion='TRUE'";
        System.out.println(sentenciaSql);
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
   public boolean ModificarRepararBien(String just, String respon, String fecIUsu, String siniestro, String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();  
        
        String sentenciaSql = "UPDATE public.movimiento_bien SET justificacion='"+just+"',reponsable='"+respon+"',fecha_move='"+fecIUsu+"',id_tipo_siniestro='"+siniestro+"' WHERE id_bien='"+id+"';";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
         
              public boolean Eliminar(String id){
        boolean statusEliminar;
        conexion = new ConexionBD();
        conexion.conectar();
        int tipo_move=5;
        String sentenciaSql = "DELETE FROM public.movimiento_bien WHERE id_bien='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
        sentenciaSql = "UPDATE public.bien SET tipo_move='"+tipo_move+"',eliminacion='TRUE' WHERE id='"+id+"' ";
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        
        conexion.cerrar();
        return statusEliminar;
    }//fin de incluir 
              
    
    
   public ArrayList<String> CargarTipoS() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombrets FROM public.tipo_siniestro order by nombrets";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
               try {
            ListPartida.add(("SELECCIONAR"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombrets"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModDesincorporar.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return ListPartida;
    }
   
   public String[][] consultarListado() {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT * FROM public.vmovientos WHERE nombre_move='DESINCORPORAR' ORDER BY fecha_move DESC LIMIT 10";
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
                    datos[i][0] = resultadoConsulta.getString("nombre_move");
                    datos[i][1] = resultadoConsulta.getString("nombrets");
                    datos[i][2] = resultadoConsulta.getString("nombreper");
                    datos[i][3] = resultadoConsulta.getString("justificacion");
                    datos[i][4] = resultadoConsulta.getString("nombreb");
                    datos[i][5] = resultadoConsulta.getString("fecha_move");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
   
}
