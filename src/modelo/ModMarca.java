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
public class ModMarca {
    
    ConexionBD conexion  = new ConexionBD();
    // Datos de Usuario
    private String descrip;
    private String nombre;
    private String id;
    private String status;
    private String valor="TRUE";
    private String valor1="FALSE";
    
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    
    //private JasperViewer jasperViewer;

    public ModMarca() {
    }

    public ModMarca(String id,String nombre, String descrip) {
        this.id = id;
        this.descrip = descrip;
        this.nombre = nombre;
        
    }
    
    public ModMarca(String nombre, String descrip) {
        this.descrip = descrip;
        this.nombre = nombre;
 
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


 

    public boolean Consultar(String nombre){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.marca WHERE nombrema='"+nombre+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id_marca"));
                    this.setDescrip(resultadoConsulta.getString("descripcion"));
                    this.setNombre(resultadoConsulta.getString("nombrema"));
                    this.setStatus(resultadoConsulta.getString("eliminacion"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarMarca(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
                               
        String sentenciaSql = "INSERT INTO public.marca (nombrema,descripcion,eliminacion) VALUES ('"+nombre+"','"+descrip+"','"+valor+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarMarca(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.marca SET nombrema='"+nombre+"', descripcion='"+descrip+"' WHERE id_marca='"+id+"' ;";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoMarca(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT id_marca, nombrema, descripcion FROM public.marca WHERE eliminacion='TRUE' order by nombrema asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=2;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Nombre"," Descripcion"};
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
                     Logger.getLogger(ModMarca.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos
      
          public boolean Eliminar(String id){
        boolean statusEliminar=false;

        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.marca set eliminacion='FALSE' WHERE id_marca='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        conexion.cerrar();       
 
        
        return statusEliminar;
    }//fin de incluir 
          
      public boolean CargarTablaMarca(JTable ptabmarca, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT id_marca,nombrema,descripcion FROM public.marca";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaMarca(ptabmarca);
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
                        //ptabMarca.setToolTipText("Doble Click para Seleccionar Prtfil");
                    }
                    DefaultTableModel dtm = (DefaultTableModel) ptabmarca.getModel();
                    for (int i = 0; i < datos.size(); i++) {
                        dtm.addRow(datos.get(i));
                    }
                 }
            //} else {
            //    System.out.println("No Existe el Marca Consultado...");
           // }
        } catch (SQLException ex) {
            Logger.getLogger(ModMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaMarca(JTable ptabmarca) {
        DefaultTableModel temp;
        try {
            temp = (DefaultTableModel) ptabmarca.getModel();
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
                      
        String tirasql= "SELECT * FROM public.marca WHERE eliminacion='TRUE' ";
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
                    
                    
                    datos[i][0] = resultadoConsulta.getString("nombrema");
                    datos[i][1] = resultadoConsulta.getString("descripcion");
                    
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
            
            String tirasql = "SELECT * FROM public.marca WHERE nombrema LIKE '%"+busqueda+"%' OR descripcion LIKE '%"+busqueda+"%'";
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
                    
                    
                    datos[i][0] = resultadoConsulta.getString("nombrema");
                    datos[i][1] = resultadoConsulta.getString("descripcion");
                   
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
 public ArrayList<String> CargarMarca() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombrema FROM marca WHERE eliminacion='TRUE' order by nombrema";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        if (rs==null){
            ListPartida.add("SIN MARCA");
        }else{
               try {
            ListPartida.add(("SIN MARCA"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombrema"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
               
        return ListPartida;
    }
  public boolean Consultar_id_Marca(String seleccionado) { //inicio del metodo consultar
        boolean existe = false;
        String tiraSQL = "SELECT id_marca FROM public.marca WHERE nombrema= '" + seleccionado + "'";
        conexion= new ConexionBD();
        conexion.conectar();
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);

        try {
            existe = rs.next();
            if (existe) {
                id = rs.getString(1);
            } else {
                System.out.println("¡No Existe el id_marca!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModMarca.class.getName()).log(Level.SEVERE, null, ex);
        }

       // cerrarConexion(cn);
        return existe;

    }
   public boolean consultaridmarca(String seleccionado) { //inicio del metodo consultar
        boolean existe = false;
        String tiraSQL = "SELECT id_marca FROM marca WHERE nombrema='"+seleccionado+"' ";
        conexion= new ConexionBD();
        conexion.conectar();
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);

        try {
            existe = rs.next();
            if (existe) {
                id= rs.getString(1);
            } else {
                System.out.println("¡No Existe el id_marca!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModTipoSiniestro.class.getName()).log(Level.SEVERE, null, ex);
        }

       // cerrarConexion(cn);
        return existe;

    }
    public boolean inhabilitar(String id) {
        boolean resultado = false;
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String SQL= "UPDATE marca SET "
                + "eliminacion= 'FALSE'"
                + " WHERE id_marca = '"+id+"';";
        
        int ejecutar = conexion.ejecutarActualizacion(SQL);
        
        if(ejecutar!=0) {
            resultado = true;
        } else {
            resultado = false;
        }
        conexion.cerrar();
        return resultado;
    }
    
     public boolean Activar(String id){
        boolean statusA;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String tirasql = "UPDATE marca SET eliminacion ='TRUE' WHERE id_marca='"+id+"';";
        System.out.println(tirasql);
        int ejecutado = conexion.ejecutarActualizacion(tirasql);
        
        if(ejecutado!=0){
            statusA= true;
        }else{
            statusA = false;
        }
        
        conexion.cerrar();
        return statusA;
     }
     
}
