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
import vista.CatPerfil;
import vista.CatPermiso;
import vista.Perfil;

/**
 *
 * @author rover
 */
public class ModMovimientoBien extends ModeloBaseDatos{
   ConexionBD conexion  = new ConexionBD();
CatPerfil vcatPerfil;
    // Datos de Usuario
    private String nombre;
    private String id;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    private final String logotipo = "/images/mainsgcarep.png";
    
    public ModMovimientoBien() {
    }


    public ModMovimientoBien(String id, String nombre) {
        this.nombre = nombre;
    }

    public ModMovimientoBien(String nombre) {
        this.nombre = nombre;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    





    public boolean ActualizaBitacora(String pmodu, String petique, String mnomusu, boolean valido, String codusuario) {
        boolean sw;
        sw = false;
        String tiraSQL = "";
        conn = crearConexion();
        if(mnomusu.equals("MASTER")) {
            tiraSQL = "INSERT INTO public.bitacces (codopc, descopc, nomusu, acceso, fecha, hora) ";
            tiraSQL += " VALUES ('" + pmodu + "', '" + petique + "', '" + mnomusu + "', " + valido + ", current_date, current_time)";
        } else {
            tiraSQL = "INSERT INTO datos.bitacces (codopc, descopc, nomusu, acceso, fecha, hora, codusr) ";
            tiraSQL += " VALUES ('" + pmodu + "', '" + petique + "', '" + mnomusu + "', " + valido + ", current_date, current_time, '" + codusuario + "')";
        }
        //System.out.println("tiraSQL; " + tiraSQL);
        sw = actualizarDatos(conn, tiraSQL);
        if (sw) {
            realizarCambios(conn);
        } else {
            deshacerCambios(conn);
        }
        cerrarConexion(conn);
        return sw;
    }
   
       public boolean Consultar(String serial){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.vbien WHERE serial='"+serial+"';";
            System.out.println("Consultar() SQL .-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setNombre(resultadoConsulta.getString("nombreb"));

                    
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("serial: "+this.getId());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModBien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
       
       public String[][] consultarListado() {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT nombre_move,fecha_move,nombreper,nombreb, COUNT(*) as cantidad FROM public.vmovientos\n" +
"     GROUP BY nombre_move,fecha_move,nombreper,nombreb\n" +
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
                    datos[i][1] = resultadoConsulta.getString("nombreb");
                    datos[i][2] = resultadoConsulta.getString("nombreper");
                    datos[i][3] = resultadoConsulta.getString("fecha_move");
                    datos[i][4] = resultadoConsulta.getString("cantidad");
                    
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
            
            String tirasql = "SELECT * FROM public.empleado WHERE cedula LIKE '%"+busqueda+"%' OR nombre LIKE '%"+busqueda+"%'";
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
                    
                    datos[i][0] = resultadoConsulta.getString("cedula");
                    datos[i][1] = resultadoConsulta.getString("nombre");
                    datos[i][2] = resultadoConsulta.getString("apellido");
                    datos[i][3] = resultadoConsulta.getString("direccion");
                    datos[i][4] = resultadoConsulta.getString("telefono");
                    datos[i][5] = resultadoConsulta.getString("catedra");
                    datos[i][6] = resultadoConsulta.getString("sexo");
                    datos[i][7] = resultadoConsulta.getString("fechanac");
                    datos[i][8] = resultadoConsulta.getString("cargo");
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
      public void cargarDatosCatalogo(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT * FROM public.vmovimientos order by id asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=8;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Cedula"," Nombre","Apellido","Telefono","Direccion","Sexo","Fecha Nacimiento","cargo"};
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
                     Logger.getLogger(ModEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos

       
       
}
