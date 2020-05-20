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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.CatProfesor;
import vista.Profesor;

/**
 *
 * @author rover
 */
public class ModProfesor extends ModeloBaseDatos {
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String sexo;
    private String catedra;
    private Date fechaNac;
    private String id_prof;
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    Profesor vista;
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModProfesor() {
    }

    public ModProfesor(CatProfesor catProfesor) {

    }
    public ModProfesor(Profesor vista) {
        this.vista = vista;
    }
    public ModProfesor(String cedula, String nombre, String apellido, String telefono, String direccion, String sexo,String catedra,Date fechaNac) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.catedra = catedra;
        this.fechaNac=fechaNac;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getId_prof() {
        return id_prof;
    }

    public void setId_prof(String id_prof) {
        this.id_prof = id_prof;
    }



    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCatedra() {
        return catedra;
    }

    public void setCatedra(String catedra) {
        this.catedra = catedra;
    }

 


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean Consultar(String cedula){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT id_persona, cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper,nombrec FROM public.vprofesor WHERE cedulaper='"+cedula+"'";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setCedula(resultadoConsulta.getString("cedulaper"));
                    this.setNombre(resultadoConsulta.getString("nombreper"));
                    this.setApellido(resultadoConsulta.getString("apellidoper"));
                    this.setTelefono(resultadoConsulta.getString("telefonoper"));
                    this.setDireccion(resultadoConsulta.getString("direccionper"));
                    this.setCatedra(resultadoConsulta.getString("nombrec"));
                    this.setId_prof(resultadoConsulta.getString("id_persona"));
                    this.setSexo(resultadoConsulta.getString("generoper"));
                    this.setFechaNac(resultadoConsulta.getDate("fnacimientoper"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("Nombre: "+getCedula());
                    System.out.println("Nombre catedra: "+getCatedra());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarEstudiante(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "INSERT INTO public.profesor (cedula,nombre,apellido,direccion,telefono,sexo,catedra,fnacimiento,eliminacion) VALUES ('"+cedula+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"','"+sexo+"','"+catedra+"','"+fechaNac+"','TRUE')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
     public boolean ModificarEstudiante(String cedula){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.profesor SET nombre='"+nombre+"',apellido='"+apellido+"',telefono='"+telefono+"',direccion='"+direccion+"',sexo='"+sexo+"',catedra='"+catedra+"',fnacimiento='"+fechaNac+"' WHERE cedula='"+cedula+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
public String[][] CatedraProfesor(String id){
       conexion = new ConexionBD();
        conexion.conectar();
        boolean statusConsulta=false;
            
            String sentenciaSQL = "SElECT cat.nombrec FROM persona as pro JOIN profesor_catedra as pc ON pro.id_persona=pc.id_profesor JOIN catedra as cat ON pc.id_catedra=cat.id_catedra WHERE pro.id_persona='"+id+"'";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null) return null;
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][2];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    datos[i][0] = resultadoConsulta.getString("nombrec");
                    
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
            return null;
            
        } 

    }
      
      
          public boolean Eliminar(String id){
        boolean statusEliminar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE  public.profesor set eliminacion='FALSE' WHERE cedula='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        
        conexion.cerrar();
        return statusEliminar;
    }//fin de incluir 
          
      public boolean CargarTablaEstudiante(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT id, cedula,nombre,apellido,direccion,catedra,sexo,telefono,fnacimiento FROM public.profesor";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaCargo(ptabcargo);
        System.out.println(tiraSQL);
        conn = crearConexion();
        System.out.println(conn);
        rs = consultarDatos(conn, tiraSQL);
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
            Logger.getLogger(ModProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion(conn);
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
                      
        String tirasql= "SELECT cedulaper,nombreper,apellidoper,direccionper,fnacimientoper, COUNT(*) as num_repeticiones FROM vprofesor u \n" +
"	    GROUP BY cedulaper,nombreper,apellidoper,direccionper,fnacimientoper \n" +
"	    HAVING COUNT(*)=1 OR COUNT(*)>1;";
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
                    datos[i][0] = resultadoConsulta.getString("cedulaper");
                    datos[i][1] = resultadoConsulta.getString("nombreper");
                    datos[i][2] = resultadoConsulta.getString("apellidoper");
                    datos[i][3] = resultadoConsulta.getString("direccionper");
                    datos[i][4] = resultadoConsulta.getString("fnacimientoper");
                    datos[i][5] = resultadoConsulta.getString("num_repeticiones");
                    
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
            
            String tirasql = "SELECT * FROM public.vprofesor WHERE nombreper  LIKE '%"+busqueda+"%' AND tipoper='3' AND eliminacion='TRUE'";
            System.out.println("desde Filtro() "+ tirasql);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            if(resultadoConsulta == null) return null;
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][8];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    
                    
                    datos[i][0] = resultadoConsulta.getString("cedulaper");
                    datos[i][1] = resultadoConsulta.getString("nombreper");
                    datos[i][2] = resultadoConsulta.getString("apellidoper");
                    datos[i][3] = resultadoConsulta.getString("direccionper");
                    datos[i][4] = resultadoConsulta.getString("nombrec");
                    datos[i][5] = resultadoConsulta.getString("telefonoper");
                    datos[i][6] = resultadoConsulta.getString("generoper");
                    datos[i][7] = resultadoConsulta.getString("fnacimientoper");
                   
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
                public ArrayList<String> CargarProfesor(String dat) {
        ArrayList<String> ListPartida = new ArrayList();
        String tiraSQL = "SELECT nombreper FROM vcatedra_profesor  WHERE id_catedra= '"+dat+"' AND tipoper='3' AND eliminacion='TRUE' order by nombreper";
        conexion = new ConexionBD();
        conexion.conectar();
         ResultSet resultadoConsulta = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        
        if (resultadoConsulta==null){
            ListPartida.add("SIN PROFESOR");
        }else{
        try {

            while (resultadoConsulta.next()) {
                ListPartida.add("SIN PROFESOR");
                ListPartida.add(resultadoConsulta.getString("nombreper"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      
        return ListPartida;
    }
                
         public ArrayList<String> CargarProfesor() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombreper FROM persona WHERE tipoper='3' AND eliminacion='TRUE' order by nombreper";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        if (rs==null){
            ListPartida.add("SIN PROFESOR");
        }else{
               try {
            ListPartida.add(("SIN PROFESOR"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombreper"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            return ListPartida;
    }
          
         public boolean Consultar_id_Profesor(String seleccionado) { //inicio del metodo consultar
        boolean existe = false;
        String tiraSQL = "SELECT id_persona FROM public.persona WHERE nombreper= '" + seleccionado + "' AND tipoper='3' AND eliminacion='TRUE'";
        conexion= new ConexionBD();
        conexion.conectar();
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);

        try {
            existe = rs.next();
            if (existe) {
                id_prof = rs.getString(1);
            } else {
                System.out.println("¡No Existe el id_persona!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModMarca.class.getName()).log(Level.SEVERE, null, ex);
        }

       // cerrarConexion(cn);
        return existe;

    }
           
}
