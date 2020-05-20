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
public class ModTipoBien {
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String id_marca;
    private String id_modelo;
   
    private String descrip;
    private String nombre;
    private String id_tipob;
    private String nombre_tipob;
    private String valor="TRUE";
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    TipoBien vista;
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;

    //private JasperViewer jasperViewer;

    public ModTipoBien() {
    }

    public ModTipoBien(CatTipoBien catTipoBien) {

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
    
    public ModTipoBien(TipoBien vista) {
        this.vista = vista;
    }

    public ModTipoBien(String nombre, String descrip) {
        this.descrip = descrip;
        this.nombre = nombre;
 
    }

    public ModTipoBien(String id_tipob,String nombre, String descrip) {
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
            
            String sentenciaSQL = "SELECT id_tipob,nombretb,descripcion FROM public.tipobien WHERE id_tipob='"+id+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setDescrip(resultadoConsulta.getString("descripcion"));
                    this.setNombre(resultadoConsulta.getString("nombretb"));
                    this.setId_tipob(resultadoConsulta.getString("id_tipob"));
                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModTipoBien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarTipoBien(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
                               
        String sentenciaSql = "INSERT INTO public.tipobien (nombretb,descripcion,eliminacion) VALUES ('"+nombre+"','"+descrip+"','"+valor+"')";
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
        
        String sentenciaSql = "UPDATE public.tipobien SET nombretb='"+nombre+"', descripcion='"+descrip+"' WHERE id_tipob='"+id_tipob+"';";
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
             String sentencia = "SELECT id_tipob, nombretb, descripcion FROM public.tipobien WHERE eliminacion='TRUE' order by nombretb asc;";
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
                     Logger.getLogger(ModTipoBien.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String sentenciaSql = "UPDATE public.tipobien set eliminacion='FALSE' WHERE id_tipob='"+id+"';";
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
        String tiraSQL = "SELECT * FROM public.tipobien WHERE eliminacion='TRUE'";
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
            Logger.getLogger(ModTipoBien.class.getName()).log(Level.SEVERE, null, ex);
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
            
            String tirasql = "SELECT id_tipob FROM tipobien WHERE nombretb='"+tipob+"' AND eliminacion='TRUE'";
            //System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId_tipob(resultadoConsulta.getString("id_tipob"));
                    
                 //   System.out.println("Nombre: "+getNombre());
                    
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModTipoBien.class.getName()).log(Level.SEVERE, null, ex);
            }
             conexion.cerrar();
            return statusConsulta;
            
        }
     
              public boolean consultaridmarca (String marca){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT id_marca FROM marca WHERE nombrema='"+marca+"' AND eliminacion='TRUE'";
            //System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId_marca(resultadoConsulta.getString("id_marca"));
                    
                 //   System.out.println("Nombre: "+getNombre());
                    
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModTipoBien.class.getName()).log(Level.SEVERE, null, ex);
            }
             conexion.cerrar();
            return statusConsulta;
            
        }
              
               public boolean consultaridmodelo(String modelo){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT id_modelo FROM modelo WHERE nombremo='"+modelo+"' AND eliminacion='TRUE'";
            //System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId_modelo(resultadoConsulta.getString("id_modelo"));
                    
                 //   System.out.println("Nombre: "+getNombre());
                    
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModTipoBien.class.getName()).log(Level.SEVERE, null, ex);
            }
             conexion.cerrar();
            return statusConsulta;
            
        }
                           public String[][] Filtro (String busqueda){
       
            boolean estatusConsulta=false;
            
            //conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT * FROM public.tipobien WHERE nombretb LIKE '%"+busqueda+"%' OR descripcion LIKE '%"+busqueda+"%' AND eliminacion='TRUE'";
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
                    
                    datos[i][0] = resultadoConsulta.getString("id_tipob");
                    datos[i][1] = resultadoConsulta.getString("nombretb");
                    datos[i][2] = resultadoConsulta.getString("descripcion");
                   
                            
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
                      
        String tirasql= "SELECT * FROM public.tipobien WHERE eliminacion='TRUE'";
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
                    
                    
                    datos[i][0] = resultadoConsulta.getString("id_tipob");
                    datos[i][1] = resultadoConsulta.getString("nombretb");
                    datos[i][2] = resultadoConsulta.getString("descripcion");

                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
    
}
