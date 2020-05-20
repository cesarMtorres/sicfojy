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
public class ModPerfil extends ModeloBaseDatos{
   ConexionBD conexion  = new ConexionBD();
CatPerfil vcatPerfil;
    // Datos de Usuario
    private String codigo;
    private String nombre;
    private String id;
    private String opcion;
    private String valor="TRUE";
    //Conexion con la vista correspondiente
    Perfil vdper;
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    
    public ModPerfil() {
    }

    public ModPerfil(Perfil vdper) {
        this.vdper = vdper;
    }

    public ModPerfil(String id,String codigo, String nombre) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public ModPerfil(String codigo, String nombre) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public ModPerfil(CatPerfil vcatPerfil) {

    }
    public ModPerfil(CatPermiso vcatPerfil) {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
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

    
    //Inicio Busca Perfil
public boolean Consultar(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.perfil WHERE id_perfil='"+id+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id_perfil"));
                    this.setNombre(resultadoConsulta.getString("descrip"));

                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar

    //Inicio de registrar
        public boolean RegistrarPerfil(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
        String sentenciaSql=null;
                               
        sentenciaSql = "INSERT INTO public.perfil (descrip,estatus,eliminacion)";
        sentenciaSql += " VALUES (upper('" + nombre + "'),'A'),'"+valor+"'";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir

    //fin de registrar
    //Inicio de Modificar

 public boolean ModificarPerfil(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.perfil SET codperfil='"+codigo+"', descrip='"+nombre+"' WHERE id='"+id+"' ;";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }
    //Fin de modificar

    //Inicio de Eliminar
    public boolean Eliminar(String id){
         int velim = 0;
        boolean statusEliminar=false;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Seguro de Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (velim == 0) {
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE  public.perfil set eliminacion='FALSE' WHERE id_perfil='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        conexion.cerrar();      
        }
       return statusEliminar;
    }//fin de incluir 

    //Fin de Eliminar
    //Inicio de Imprimir



    //Busca los Datos del Perfil y los carga en un Jtable
    //Busca los Datos del Perfil y los carga en un Jtable
 public boolean CargarTablaPerfil(JTable ptabperfil, String pcriterio, String paramperf) throws SQLException {
        boolean existe = false;
        ResultSetMetaData rsm;
        String tiraSQL = "";
        String codp = "";
        if("gridperfil".equals(ptabperfil.getName())){
            tiraSQL = "SELECT id_perfil, descrip FROM public.perfil";
        } else {
            codp = paramperf;
            
            tiraSQL = "SELECT codopc, nombre, public.spdetsel('"+codp+"', codopc) as seleccion FROM public.Opcion";
        }
        if (!"".equals(pcriterio)) {
            //tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaPerfil(ptabperfil);
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
                        //ptabperfil.setToolTipText("Doble Click para Seleccionar Prtfil");
                    }
                    DefaultTableModel dtm = (DefaultTableModel) ptabperfil.getModel();
                    for (int i = 0; i < datos.size(); i++) {
                        dtm.addRow(datos.get(i));
                    }
                 }
            //} else {
            //    System.out.println("No Existe el Perfil Consultado...");
           // }
        } catch (SQLException ex) {
            Logger.getLogger(ModPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion(conn);
        return existe;
    }
 
public boolean CargarTablaPerfil(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT * FROM public.perfil WHERE eliminacion='TRUE'";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaPerfil(ptabcargo);
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

    //Fin Cargar Tabla Perfil
    

    // Limpiar Tabla de Perfil
    public void limpiaTablaPerfil(JTable ptabperfil) {
        DefaultTableModel temp;
        try {
            temp = (DefaultTableModel) ptabperfil.getModel();
            int a = temp.getRowCount() - 1;
            for (int i = 0; i <= a; i++) {
                temp.removeRow(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Asignar Opciones al Perfil
    public boolean asignarOpcionPerfil (String pperfil, JTable ptabPermiso){
        System.out.println("Perfil" + pperfil);
        boolean sw = false;
        String tiraSQL=null;
        connp = crearConexion();
        tiraSQL="DELETE FROM public.opcxperfil WHERE codperfil = '" + pperfil + "'";
        sw = actualizarDatos(connp, tiraSQL);
        for (int i=0;i<ptabPermiso.getRowCount();i++){
            // Detalle
            boolean a= (boolean) ptabPermiso.getValueAt(i,2);
            String b = (String) ptabPermiso.getValueAt(i,0);
            if (a) {
                tiraSQL = "INSERT INTO public.opcxperfil(codperfil, codopc) ";
                tiraSQL += " VALUES ('" + pperfil +"','"  + b + "')";
                sw = actualizarDatos(connp, tiraSQL);
            }
        }
        
        if (sw) {
            realizarCambios(connp);
        } else {
            deshacerCambios(connp);
        }
        cerrarConexion(connp);
        
        return sw;
    }
    // Fin
    // Devuelve Verdadero si el Perfil Está asociada a la opcion
    public boolean buscarPerfilxOpcion(String mperfil, String pmodu) {
        boolean existe = false;
        String tiraSQL = "SELECT * FROM public.opcxperfil WHERE codperfil='" + mperfil + "' AND codopc='" + pmodu + "'";
        System.out.println(tiraSQL);
        conn = crearConexion();
        System.out.println(conn);
        rs = consultarDatos(conn, tiraSQL);
        try {
            if (rs!=null) {
                existe = rs.next();
                if (existe){
                    existe = true;
                }
            } else {
               existe = false;
                System.out.println("No Existe el Perfil Consultado...");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion(conn);
        return existe;
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
    public void cargarDatosCatalogoPerfil(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT id,cedula, nombre,apellido,telefono,direccion,sexo,fnacimiento,cargo FROM public.empleado order by nombre asc;";
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
    
  
    
       
       public String[][] Filtro (String busqueda){
       
            boolean estatusConsulta=false;
            
            //conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT * FROM public.perfil WHERE codperfil LIKE '%"+busqueda+"%' OR descrip LIKE '%"+busqueda+"%' WHERE eliminacion='TRUE'";
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
                    
                    
                    datos[i][0] = resultadoConsulta.getString("codperfil");
                    datos[i][1] = resultadoConsulta.getString("descrip");
                   
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
       
       public String[][] consultarListado() {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT * FROM public.perfil WHERE eliminacion='TRUE'";
        System.out.println("consultarListado() "+tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
        
        if(resultadoConsulta == null) return null;
        int i = 0;
        try {
            
           while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][3];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    
                    
                    datos[i][0] = resultadoConsulta.getString("id_perfil");
                    datos[i][1] = resultadoConsulta.getString("descrip");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }


       
       
}
