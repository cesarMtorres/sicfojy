/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author betty
 */
public class ModModelo {
    
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String nombre;
    private String id;
    private String valor="TRUE";
    private String valor1="FALSE";
    private String marca;
   // public JasperReport reporte;

    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModModelo() {
    }

    public ModModelo(String id, String nombre,String marca) {
        this.nombre = nombre;
        this.id = id;
        this.marca=marca;
    }




    public ModModelo(String nombre,String marca) {
        this.nombre = nombre;
        this.marca=marca;
 
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


 

    public boolean Consultar(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT mo.id_marca, mo.nombremo,ma.nombrema FROM modelo as mo INNER JOIN marca as ma ON mo.id_marca=ma.id_marca WHERE id_modelo='"+id+"' ";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id_marca"));
                    this.setNombre(resultadoConsulta.getString("nombremo"));
                    this.setMarca(resultadoConsulta.getString("nombrema"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarModelo(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
                               
        String sentenciaSql = "INSERT INTO public.modelo (nombremo,id_marca,eliminacion) VALUES ('"+nombre+"','"+marca+"','"+valor+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarModelo(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.modelo SET id_marca='"+marca+"', nombremo='"+nombre+"' WHERE id_modelo='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoModelo(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT id_modelo, nombremo, id_marca FROM public.modelo WHERE eliminacion='TRUE' order by nombremo asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=2;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"codigo","Nombre"," marca"};
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
                     Logger.getLogger(ModModelo.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos
      
          public boolean Eliminar(String id){
        boolean statusEliminar=false;

        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE  public.modelo set eliminacion='FALSE' WHERE id_modelo='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        conexion.cerrar();       
 
        
        return statusEliminar;
    }//fin de incluir 
          
      public boolean CargarTablaModelo(JTable ptabmodelo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT id_modelo, nombremo,id_marca FROM public.modelo WHERE eliminacion='TRUE'";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaModelo(ptabmodelo);
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
                        //ptabModelo.setToolTipText("Doble Click para Seleccionar Prtfil");
                    }
                    DefaultTableModel dtm = (DefaultTableModel) ptabmodelo.getModel();
                    for (int i = 0; i < datos.size(); i++) {
                        dtm.addRow(datos.get(i));
                    }
                 }
            //} else {
            //    System.out.println("No Existe el Modelo Consultado...");
           // }
        } catch (SQLException ex) {
            Logger.getLogger(ModModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaModelo(JTable ptabmodelo) {
        DefaultTableModel temp;
        try {
            temp = (DefaultTableModel) ptabmodelo.getModel();
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
                      //select m.id,m.nombre from modelo as m join marca as ma on m.id=ma.id;
        String tirasql= "SELECT id_modelo,mo.nombremo,ma.nombrema FROM modelo as mo INNER JOIN marca as ma ON mo.id_modelo=ma.id_marca WHERE ma.eliminacion='TRUE'";
        System.out.println("consultarListado() "+tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
        
        if(resultadoConsulta == null) return null;
        int i = 0;
        try {
            
           while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][4];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    
                    datos[i][0] = resultadoConsulta.getString("id_modelo");
                    datos[i][1] = resultadoConsulta.getString("nombremo");
                    datos[i][2] = resultadoConsulta.getString("nombrema");
                   
                    
                    
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
            
            String tirasql = "SELECT * FROM public.modelo WHERE nombremo LIKE '%"+busqueda+"%' AND eliminacion='TRUE'";
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
                    
                    datos[i][0] = resultadoConsulta.getString("id_modelo");
                    datos[i][1] = resultadoConsulta.getString("nombremo");
                    datos[i][2] = resultadoConsulta.getString("id_marca");
                   
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
            
        }
     public ArrayList<String> CargarModelo(String dat) {
        ArrayList<String> ListPartida = new ArrayList();
        String tiraSQL = "SELECT nombremo FROM public.modelo WHERE id_marca= '"+dat+"' AND eliminacion='TRUE' order by nombremo";
        conexion = new ConexionBD();
        conexion.conectar();
         ResultSet resultadoConsulta = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        
        if (resultadoConsulta==null){
            ListPartida.add("SIN MODELO");
        }else{
        try {
            ListPartida.add("SIN MODELO");
            while (resultadoConsulta.next()) {
                ListPartida.add(resultadoConsulta.getString("nombremo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      
        return ListPartida;
    }
            
            
}
