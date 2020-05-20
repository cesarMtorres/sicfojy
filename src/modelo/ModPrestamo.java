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
public class ModPrestamo {
    
    ConexionBD conexion  = new ConexionBD();
    private String justificacion;
    private String responsable;
    private Date fechae;
    private Date fechad;
    private String estudiante;
    private String id;
    private String catedra;
    private String dias;
    private String tipoprestamo;
    private String bien;
    private String tipo_prestamo;
    private String cantidad;
    private String valor="TRUE";
    private String valor1="FALSE";
    int tipo_move=4;
    int siniestro=6;
    private String temp;
    private String temp1;
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModPrestamo() {
    }

    public ModPrestamo(String justificacion, String responsable, Date fechae, Date fechad, String estudiante,String bien,String catedra, String tipoprestamo, String cantidad) {
        this.justificacion = justificacion;
        this.responsable = responsable;
        this.fechae = fechae;
        this.fechad = fechad;
        this.bien = bien;
        this.estudiante = estudiante;
        this.catedra = catedra;
        this.tipoprestamo = tipoprestamo;
        this.cantidad = cantidad;
    }

    public ModPrestamo(String justificacion, String responsable, Date fechae, Date fechad, String estudiante,String bien, String catedra, String tipoprestamo, String cantidad,String id) {
        this.justificacion = justificacion;
        this.responsable = responsable;
        this.fechae = fechae;
        this.fechad = fechad;
        this.bien = bien;
        this.estudiante = estudiante;
        this.id = id;
        this.catedra = catedra;
        this.tipoprestamo = tipoprestamo;
        this.cantidad = cantidad;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }



    public String getBien() {
        return bien;
    }

    public void setBien(String bien) {
        this.bien = bien;
    }

    public String getTipo_prestamo() {
        return tipo_prestamo;
    }

    public void setTipo_prestamo(String tipo_prestamo) {
        this.tipo_prestamo = tipo_prestamo;
    }



    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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

    public Date getFechae() {
        return fechae;
    }

    public void setFechae(Date fechae) {
        this.fechae = fechae;
    }

    public Date getFechad() {
        return fechad;
    }

    public void setFechad(Date fechad) {
        this.fechad = fechad;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatedra() {
        return catedra;
    }

    public void setCatedra(String catedra) {
        this.catedra = catedra;
    }

    public String getTipoprestamo() {
        return tipoprestamo;
    }

    public void setTipoprestamo(String tipoprestamo) {
        this.tipoprestamo = tipoprestamo;
    }
    
       public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    
           public boolean ConsultarId_bien_mb(String id){
       
            boolean statusConsulta=false;
            conexion = new ConexionBD();
            conexion.conectar();
           String sentenciaSQL ="SELECT id_bien,id_mov_bien from movimiento_bien where nombrebien='"+id+"' AND id_tipo_movimiento='4'  order by id_bien DESC LIMIT 1";
 
            System.out.println("SQL ============.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setTemp(resultadoConsulta.getString("id_bien"));
                    this.setTemp1(resultadoConsulta.getString("id_mov_bien"));
                    System.out.println("id: "+getTemp());
                    System.out.println("id: "+getTemp1());
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
           
 public boolean ConsultarTabla(String nombre){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.vmoviento_prestamo WHERE nombre_move='PRESTAR' AND nombreb='"+nombre+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                    
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("bien"));
                    this.setBien(resultadoConsulta.getString("nombreb"));
                    this.setResponsable(resultadoConsulta.getString("responsable"));
                    this.setTipoprestamo(resultadoConsulta.getString("tipo_prestamo"));
                    this.setJustificacion(resultadoConsulta.getString("justificacion"));
                    this.setFechae(resultadoConsulta.getDate("fecha_move"));
                    this.setFechad(resultadoConsulta.getDate("fecha_d"));
                    this.setCantidad(resultadoConsulta.getString("cantidad"));
                    this.setDias(resultadoConsulta.getString("dias"));
                    this.setCatedra(resultadoConsulta.getString("nombrec"));
                    this.setEstudiante(resultadoConsulta.getString("estudiante"));
                    
                        
                    System.out.println("Nombre: "+getBien());
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
    
    public boolean RegistrarPrestamo(String nombreBien){
        boolean statusIncluir = false;
        conexion = new ConexionBD();
        conexion.conectar();  
       int count = Integer.parseInt(cantidad);
       
        //String sentenciaSql = "INSERT INTO public.movimiento_bien (justificacion,id_mp_responsable,id_mp_catedra,id_mp_estudiante,id_mp_tprestamo,fecha_i,fecha_d,cantidad,id_mp_bien,id_mp_tipomv) VALUES ('"+justificacion+"','"+responsable+"','"+catedra+"','"+estudiante+"','"+tipoprestamo+"','"+fechae+"','"+fechad+"','"+cantidad+"','"+bien+"','"+tipo_move+"' )";
        String sentenciaSql = "INSERT INTO public.movimiento_bien (nombrebien,justificacion,reponsable,id_mp_catedra,id_mp_estudiante,id_mp_tprestamo,fecha_move,fecha_d,cantidad,id_bien,id_tipo_movimiento,id_tipo_siniestro) VALUES ('"+nombreBien+"','"+justificacion+"','"+responsable+"','"+catedra+"','"+estudiante+"','"+tipoprestamo+"','"+fechae+"','"+fechad+"','"+cantidad+"','"+bien+"','"+tipo_move+"','"+siniestro+"' )";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0 & count>1){

           for (int i=1;i<=count;i++){
               if("".equals(temp)){
                   System.out.println();
        sentenciaSql = "UPDATE public.bien SET eliminacion='"+valor1+"', tipo_move='"+tipo_move+"' WHERE id='"+id+"' AND eliminacion='TRUE'";
        System.out.println("TEMP==="+sentenciaSql);
         ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
         

               }else{
                   ConsultarId_bien_mb(nombreBien);
                   sentenciaSql = "UPDATE public.bien SET eliminacion='"+valor1+"', tipo_move='"+tipo_move+"',id_move='"+temp1+"' WHERE id='"+temp+"' AND eliminacion='TRUE' ";
                   System.out.println("else temp=="+sentenciaSql);
                   ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
         
               }
        }
            statusIncluir= true;
        }else if(ejecutado!=0 & count==1){
            sentenciaSql = "UPDATE public.bien SET eliminacion='"+valor1+"', tipo_move='"+tipo_move+"' WHERE id='"+id+"' AND eliminacion='TRUE'";
        System.out.println("TEMP==="+sentenciaSql);
         ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        }else if (ejecutado==0){
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
    
    public boolean Modificar(int total,String nombreBien){
        boolean statusModificar;
        conexion = new ConexionBD();                                          
        conexion.conectar();  
        String sentenciaSql = "UPDATE public.movimiento_bien SET id_bien='"+id+"', justificacion='"+justificacion+"',id_mp_tprestamo='"+tipo_prestamo+"',reponsable='"+responsable+"',fecha_move='"+fechae+"',fecha_d='"+fechad+"',id_mp_estudiante='"+estudiante+"',id_mp_catedra='"+catedra+"',id_tipo_movimiento='"+tipo_move+"',cantidad='"+cantidad+"', id_tipo_siniestro='"+siniestro+"' WHERE id_bien='"+id+"';";
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
                      
        String tirasql= "SELECT nombre_move,fecha_move,justificacion,responsable,nombreb, COUNT(*) as cantidad FROM public.vmoviento_prestamo\n" +
"     GROUP BY nombre_move,fecha_move,justificacion,responsable,nombreb\n" +
"     HAVING COUNT(*)=1 OR COUNT(*)>1;";
        System.out.println("consultarListado() "+tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
        
        if(resultadoConsulta == null) return null;
        int i = 0;
        try {
            
           while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][5];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    datos[i][0] = resultadoConsulta.getString("nombre_move");
                    datos[i][1] = resultadoConsulta.getString("responsable");
                    datos[i][2] = resultadoConsulta.getString("justificacion");
                    datos[i][3] = resultadoConsulta.getString("nombreb");
                    datos[i][4] = resultadoConsulta.getString("fecha_move");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
    
     public boolean Eliminar(String nombreBien,String id){
        boolean statusEliminar;
        conexion = new ConexionBD();
        conexion.conectar();
        int tipo_move=5;
        ConsultarId_bien_mb(nombreBien);
        String sentenciaSql = "DELETE FROM public.movimiento_bien WHERE id_bien='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
        sentenciaSql = "UPDATE public.bien SET tipo_move='"+tipo_move+"',eliminacion='TRUE',id_move='0' WHERE id='"+id+"' AND eliminacion='FALSE' AND tipo_move='4' AND id_move='"+temp1+"' ";
        System.out.println(sentenciaSql);
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        
        conexion.cerrar();
        return statusEliminar;
    }//fin de incluir 
            
    
   public boolean Modificar(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();  
        
        String sentenciaSql = "";
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
   
    public ArrayList<String> CargarTipoPrestamo() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombre_tprestamo FROM tipo_prestamo WHERE eliminacion='TRUE' order by nombre_tprestamo";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        if (rs==null){
            ListPartida.add("SIN TIPO PRESTAMO");
        }else{
               try {
            ListPartida.add(("SIN TIPO PRESTAMO"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombre_tprestamo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
               
        return ListPartida;
    }
 public boolean Renovacion(String id,String tp){
        boolean statusModificar;
        conexion = new ConexionBD();                                          
        conexion.conectar();  
        String sentenciaSql = "UPDATE public.movimiento_bien SET id_mp_tprestamo='"+tipo_prestamo+"' WHERE id_bien='"+id+"';";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
 
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
}
