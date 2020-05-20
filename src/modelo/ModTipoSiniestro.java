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
import vista.CatTipoBien;
import vista.TipoBien;

/**
 *
 * @author rover
 */
public class ModTipoSiniestro {
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String id_marca;
    private String id_modelo;
    private String descrip;
    private String nombre;
    private String valor="TRUE";
    private String id_tipob;
    private String nombre_tipob;
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    TipoBien vista;
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;

    //private JasperViewer jasperViewer;

    public ModTipoSiniestro() {
    }

    public ModTipoSiniestro(CatTipoBien catTipoBien) {

    }

    public String getId_marca() {
        return id_marca;
    }

    public void setId_marca(String id_marca) {
        this.id_marca = id_marca;
    }

    public String getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(String id_modelo) {
        this.id_modelo = id_modelo;
    }
    


    public String getId_tipob() {
        return id_tipob;
    }

    public void setId_tipob(String id_tipob) {
        this.id_tipob = id_tipob;
    }

    public String getNombre_tipob() {
        return nombre_tipob;
    }

    public void setNombre_tipob(String nombre_tipob) {
        this.nombre_tipob = nombre_tipob;
    }
    
    public ModTipoSiniestro(TipoBien vista) {
        this.vista = vista;
    }

    public ModTipoSiniestro(String nombre, String descrip) {
        this.descrip = descrip;
        this.nombre = nombre;
 
    }

    public ModTipoSiniestro(String id_tipob,String nombre, String descrip) {
        this.descrip = descrip;
        this.nombre = nombre;
        this.id_tipob = id_tipob;
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
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.tipo_siniestro WHERE id_tipos='"+id+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    
                    this.setNombre(resultadoConsulta.getString("nombrets"));
                    this.setDescrip(resultadoConsulta.getString("descripcionts"));
                    this.setId_tipob(resultadoConsulta.getString("id_tipos"));
                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModTipoSiniestro.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarTipoBien(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
                               
        String sentenciaSql = "INSERT INTO public.tipo_siniestro (nombrets,descripcionts,eliminacion) VALUES ('"+nombre+"','"+descrip+"','"+valor+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarTipoBien(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.tipo_siniestro SET nombrets='"+nombre+"', descripcionts='"+descrip+"' WHERE id_tipos='"+id_tipob+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoTipoBien(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT * FROM public.tipo_siniestro WHERE eliminacion='TRUE' order by nombrets;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=3;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Id","Nombre"," Descripcion"};
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
                     Logger.getLogger(ModTipoSiniestro.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String sentenciaSql = "UPDATE public.tipo_siniestro set eliminacion='FALSE' WHERE id_tipos='"+id+"';";
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
          
      public boolean CargarTablaTipoBien(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT * FROM public.tipo_siniestro WHERE eliminacion='TRUE'";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaTipoBien(ptabcargo);
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
            Logger.getLogger(ModTipoSiniestro.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaTipoBien(JTable ptabcargo) {
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
          
       public boolean consultartipob (String tipob){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT id_tipos FROM tipo_siniestro WHERE nombrets='"+tipob+"' AND eliminacion='TRUE'";
            //System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId_tipob(resultadoConsulta.getString("id_tipos"));
                    
                 //   System.out.println("Nombre: "+getNombre());
                    
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModTipoSiniestro.class.getName()).log(Level.SEVERE, null, ex);
            }
             conexion.cerrar();
            return statusConsulta;
            
        }
     
     
                           public String[][] Filtro (String busqueda){
       
            boolean estatusConsulta=false;
            
            //conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT * FROM public.tipo_siniestro WHERE nombrets LIKE '%"+busqueda+"%' OR descripcionts LIKE '%"+busqueda+"%' AND eliminacion='TRUE'";
            System.out.println("desde Filtro() "+ tirasql);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            if(resultadoConsulta == null) return null;
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][3];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    
                    datos[i][0] = resultadoConsulta.getString("id_tipos");
                    datos[i][1] = resultadoConsulta.getString("nombrets");
                    datos[i][2] = resultadoConsulta.getString("descripcionts");
                   
                            
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
                      
        String tirasql= "SELECT * FROM public.tipo_siniestro WHERE eliminacion='TRUE'";
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
                    
                    
                    datos[i][0] = resultadoConsulta.getString("id_tipos");
                    datos[i][1] = resultadoConsulta.getString("nombrets");
                    datos[i][2] = resultadoConsulta.getString("descripcionts");

                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
    public boolean consultaridts(String seleccionado) { //inicio del metodo consultar
        boolean existe = false;
        String tiraSQL = "SELECT id_tipos FROM tipo_siniestro WHERE nombrets='"+seleccionado+"' AND eliminacion='TRUE' ";
        conexion= new ConexionBD();
        conexion.conectar();
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);

        try {
            existe = rs.next();
            if (existe) {
                id_tipob = rs.getString(1);
            } else {
                System.out.println("¡No Existe el id_modulo!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModTipoSiniestro.class.getName()).log(Level.SEVERE, null, ex);
        }

       // cerrarConexion(cn);
        return existe;

    }
}
