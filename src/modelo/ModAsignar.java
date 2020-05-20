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
public class ModAsignar {
    
    ConexionBD conexion  = new ConexionBD();
    // Datos de Usuario
    private String justificacion;
    private String responsable;
    private String id_empleado;
    private Date fecha;
    private String id;
    private String nombre;
    private String modulo;
    private String programa;
    private String id_responsable;
    private String id_programa;
    private String cantidad;
    private String temp;
    private String valor="TRUE";
    private String valor1="FALSE";
    private int total;
    
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModAsignar() {
    }

 
    
    public ModAsignar(int total,String cantidad,String justificacion, String responsable, Date fecha,String modulo,String programa) {
        this.justificacion = justificacion;
        this.cantidad = cantidad;
        this.total = total;
        this.responsable = responsable;
        this.fecha = fecha;
        this.modulo=modulo;
        this.programa=programa;
    }

    public ModAsignar(int total,String cantidad,String justificacion, String responsable, Date fecha,String modulo,String programa,String id) {
        this.justificacion = justificacion;
        this.cantidad = cantidad;
        this.total = total;
        this.responsable = responsable;
        this.fecha = fecha;
        this.modulo=modulo;
        this.programa=programa;
        this.id=id;
    }

    
    public String getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(String id_responsable) {
        this.id_responsable = id_responsable;
    }

    public String getId_programa() {
        return id_programa;
    }

    public void setId_programa(String id_programa) {
        this.id_programa = id_programa;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    
    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    
    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    
    
    public boolean Consultar(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT id,nombreb FROM public.vbien WHERE serial='"+id+"'";
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
                Logger.getLogger(ModAsignar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    public boolean ConsultarTabla(String nombre){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.vmoviento_asignar WHERE nombre_move='ASIGNAR' AND nombreb='"+nombre+"'";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setNombre(resultadoConsulta.getString("nombreb"));
                    this.setModulo(resultadoConsulta.getString("nombremd"));
                    this.setResponsable(resultadoConsulta.getString("nombreper"));
                    this.setId_responsable(resultadoConsulta.getString("id_persona"));
                    this.setPrograma(resultadoConsulta.getString("nombrepro"));
                    this.setId_programa(resultadoConsulta.getString("id_programa"));
                    this.setJustificacion(resultadoConsulta.getString("justificacion"));
                    this.setFecha(resultadoConsulta.getDate("fecha_move"));
                    this.setCantidad(resultadoConsulta.getString("cantidad"));
                    
                    System.out.println("Nombre: "+getNombre());
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
                    this.setTemp(resultadoConsulta.getString("count"));
                    System.out.println("cantidad: "+getCantidad());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModAsignar.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            return statusConsulta;
            
        }//fin de consultar
    
        public boolean ConsultarId_bien(String nombre){
       
            boolean statusConsulta=false;
            conexion = new ConexionBD();
            conexion.conectar();
           String sentenciaSQL ="SELECT id from bien where nombreb='"+nombre+"' AND eliminacion='TRUE' AND tipo_move='5'";
 
            System.out.println("SQL ============.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setTemp(resultadoConsulta.getString("id"));
                    System.out.println("cantidad: "+getTemp());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModAsignar.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            return statusConsulta;
            
        }//fin de consultar
        public boolean EliminarId_bien(String nombre){
       
            boolean statusConsulta=false;
            conexion = new ConexionBD();
            conexion.conectar();
           String sentenciaSQL ="SELECT id from bien where nombreb='"+nombre+"' AND eliminacion='FALSE' AND tipo_move='2'";
 
            System.out.println("SQL ============.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setTemp(resultadoConsulta.getString("id"));
                    System.out.println("cantidad: "+getTemp());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModAsignar.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            return statusConsulta;
            
        }//fin de consultar
    
    public boolean Registrar(String nombreBien,int total,String cantidad,String just,String respon,String fecIUsu,String id_modulo,String id_programa,String id){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
        int tipo_move=2;
        int tipos=6;    
       int count = Integer.parseInt(cantidad);
        String sentenciaSql = "INSERT INTO public.movimiento_bien (cantidad,justificacion,reponsable,fecha_move,id_tipo_movimiento,id_mv_modulo,id_bien,id_tipo_siniestro,id_mv_programa) VALUES ('"+cantidad+"','"+just+"','"+respon+"','"+fecIUsu+"','"+tipo_move+"','"+id_modulo+"','"+id+"' ,'"+tipos+"','"+id_programa+"')";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){

           for (int i=1;i<=count;i++){
               if("".equals(temp)){
        sentenciaSql = "UPDATE public.bien SET eliminacion='"+valor1+"', tipo_move='"+tipo_move+"' WHERE id='"+id+"' AND eliminacion='TRUE' ";
         ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
         System.out.println(sentenciaSql);

               }else{
                   ConsultarId_bien(nombreBien);
                   sentenciaSql = "UPDATE public.bien SET eliminacion='"+valor1+"', tipo_move='"+tipo_move+"' WHERE id='"+temp+"' AND eliminacion='TRUE' ";
         ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
         System.out.println(sentenciaSql);
               }
        }
                   System.out.println(sentenciaSql);
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
    
     public boolean Eliminar(String nombreBien,String id,String cantidad){
        boolean statusEliminar;
        conexion = new ConexionBD();
        conexion.conectar();
        int count = Integer.parseInt(cantidad);
        int tipo_move=5;
        String sentenciaSql = "DELETE FROM public.movimiento_bien WHERE id_bien='"+id+"';";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            for (int i=1;i<=count;i++){
               if("".equals(temp)){
              
        sentenciaSql = "UPDATE public.bien SET eliminacion='TRUE', tipo_move='"+tipo_move+"' WHERE id='"+id+"' ";
        System.out.println(sentenciaSql);
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
               }else{
            EliminarId_bien(nombreBien);
            sentenciaSql = "UPDATE public.bien SET eliminacion='TRUE', tipo_move='"+tipo_move+"' WHERE id='"+temp+"' ";
            System.out.println(sentenciaSql);
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        }
            }
            statusEliminar= true;
        } else{
            statusEliminar= false;
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
            Logger.getLogger(ModAsignar.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return ListPartida;
    }
   
   public boolean Modificar(String nombreBien,String cantidad,String just,String respon,String fecIUsu,String id_modulo,String id_programa,String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();  
        String sentenciaSql = "UPDATE public.movimiento_bien SET id_bien='"+id+"', justificacion='"+just+"',reponsable='"+respon+"',fecha_move='"+fecIUsu+"',id_mv_modulo='"+id_modulo+"',id_mv_programa='"+id_programa+"',cantidad='"+cantidad+"' WHERE id_bien='"+id+"';";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
             ConsultarCantidadBien(nombreBien);
            int cantidadIn = Integer.parseInt(cantidad);
            int cantidadBD = Integer.parseInt(temp);
            int cantidadTo = cantidadIn+cantidadBD;
            sentenciaSql = "UPDATE public.bien SET cantidad='"+cantidadTo+"' WHERE id='"+id+"' ";
        System.out.println(sentenciaSql);
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
   
   
   
             public String[][] consultarListado() {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT * FROM public.vmoviento_asignar WHERE nombre_move='ASIGNAR' ORDER BY fecha_move DESC LIMIT 10";
        System.out.println("consultarListado() "+tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
        
        if(resultadoConsulta == null) return null;
        int i = 0;
        try {
            
           while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][7];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    datos[i][0] = resultadoConsulta.getString("nombre_move");
                    datos[i][1] = resultadoConsulta.getString("nombrets");
                    datos[i][2] = resultadoConsulta.getString("nombreper");
                    datos[i][3] = resultadoConsulta.getString("justificacion");
                    datos[i][4] = resultadoConsulta.getString("nombreb");
                    datos[i][5] = resultadoConsulta.getString("fecha_move");
                    datos[i][6] = resultadoConsulta.getString("cantidad");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
}
