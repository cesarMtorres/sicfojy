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
import modelo.ConexionBD;
import vista.CatREstudiante;
import vista.CatRepresentante;
import vista.CatRepresentante;
import vista.Representante;

/**
 *
 * @author rover
 */
public class ModRepresentante {
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String sexo;
    private Date fechaNac;
    private String valor="FALSE";
    private String valor1="TRUE";
    private String id;
    private String idd;
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    Representante vista;
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModRepresentante() {
    }

    public ModRepresentante(CatRepresentante catRepresentante) {

    }
    public ModRepresentante(Representante vista) {
        this.vista = vista;
    }

    public ModRepresentante(String cedula, String nombre, String apellido, String telefono, String direccion, String sexo, Date fechaNac) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
    }

    public ModRepresentante(String id,String cedula, String nombre, String apellido, String telefono, String direccion, String sexo, Date fechaNac) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
        this.id = id;
    }

    public ModRepresentante(CatREstudiante catRestudiante) { //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    


    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
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
            
            String sentenciaSQL = "SELECT id_persona,cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper FROM public.persona WHERE cedulaper='"+cedula+"';";
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
                    this.setFechaNac(resultadoConsulta.getDate("fnacimientoper"));
                    this.setId(resultadoConsulta.getString("id_persona"));
                    
                //    this.setCatedra(resultadoConsulta.getString("catedra"));
                    this.setSexo(resultadoConsulta.getString("generoper"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("ID : "+getId());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModRepresentante.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
        public boolean ConsultarEstudiante(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper FROM public.persona pr JOIN estudiante es ON pr.id_persona::text=es.id_persona::text WHERE es.id_representante='"+id+"' ";
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
                    this.setFechaNac(resultadoConsulta.getDate("fnacimientoper"));
                    
                //    this.setCatedra(resultadoConsulta.getString("catedra"));
                    this.setSexo(resultadoConsulta.getString("generoper"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModRepresentante.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarRepresentante(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
        int tipoper=5;
        String sentenciaSql = "INSERT INTO public.persona (cedulaper,nombreper,apellidoper,direccionper,telefonoper,generoper,fnacimientoper,eliminacion,tipoper) VALUES ('"+cedula+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"','"+sexo+"','"+fechaNac+"','"+valor1+"','"+tipoper+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
           String sentenciaSql1="SELECT MAX(id_persona) FROM persona WHERE tipoper='5' ";
          //  s
            System.out.println(sentenciaSql);
             ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSql1);  
             try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setIdd(resultadoConsulta.getString("max"));
                }else{
                    
                }
             } catch (SQLException ex) {
                Logger.getLogger(ModEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
             sentenciaSql = "INSERT INTO public.representante (id_persona,eliminacion) values ('"+idd+"','"+valor1+"')";
             conexion.ejecutarActualizacion(sentenciaSql);
             
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarRepresentante(String cedula){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.persona SET nombreper='"+nombre+"',apellidoper='"+apellido+"',telefonoper='"+telefono+"',direccionper='"+direccion+"',generoper='"+sexo+"',fnacimientoper='"+fechaNac+"' WHERE cedulaper='"+cedula+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoRepresentante(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT * FROM public.persona WHERE tipoper='5' AND eliminacion='TRUE' order by nombreper asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=8;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Id","Nombre"," Apellido","Cedula","Telefono","Direccion","Sexo","Fecha Nacimiento"};
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
                     Logger.getLogger(ModRepresentante.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos
      public void cargarDatosCatalogoRepEst(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT * FROM public.vrepresentante order by nombreper asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=8;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Id","Cedula"," Nombre","Apellido","Telefono","Direccion","Sexo","Fecha Nacimiento"};
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
                     Logger.getLogger(ModRepresentante.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos
      
          public boolean Eliminar(String id){
         int velim = 0;
        boolean statusEliminar=false;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Seguro de Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (velim == 0) {
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.persona set eliminacion='FALSE' WHERE id_persona='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            sentenciaSql = "UPDATE public.representante set eliminacion='FALSE' WHERE id_persona='"+id+"';";
           ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        conexion.cerrar();       
        }
 
        
        return statusEliminar;
    }//fin de incluir 
          
      public boolean CargarTablaRepresentante(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT id, cedula,nombre,apellido,direccion,sexo,telefono,fnacimiento FROM public.representante";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaRepresentante(ptabcargo);
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
            Logger.getLogger(ModRepresentante.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaRepresentante(JTable ptabcargo) {
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
          public String[][] consultarListadoE(String id) {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper FROM public.persona pr JOIN estudiante es ON pr.id_persona::text=es.id_persona::text WHERE es.id_representante='"+id+"' AND eliminacion='TRUE'";
        System.out.println("consultarListado() "+tirasql);
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
                    datos[i][4] = resultadoConsulta.getString("telefonoper");
                    datos[i][6] = resultadoConsulta.getString("generoper");
                    datos[i][7] = resultadoConsulta.getString("fnacimientoper");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
          public String[][] consultarListadoRepEst(String id) {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper FROM public.persona WHERE es.id_estudiante='"+id+"'";
        System.out.println("consultarListado() "+tirasql);
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
                    datos[i][4] = resultadoConsulta.getString("telefonoper");
                    datos[i][6] = resultadoConsulta.getString("generoper");
                    datos[i][7] = resultadoConsulta.getString("fnacimientoper");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
          
 
}
