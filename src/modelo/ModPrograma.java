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
import vista.Programa;
import vista.CatPrograma;

/**
 *
 * @author betty
 */
public class ModPrograma {
      ConexionBD conexion  = new ConexionBD();
    // Datos de Usuario
    private String descrip;
    private String nombre;
    private String id;
    private String valor="TRUE";

   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    Programa vista;
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    private String modulo;
    //private JasperViewer jasperViewer;

    public ModPrograma() {
    }

    public ModPrograma(CatPrograma catPrograma) {

    }
    public ModPrograma(Programa vista) {
        this.vista = vista;
    }

    public ModPrograma(String nombre, String descrip, String modulo) {
        this.descrip = descrip;
        this.nombre = nombre;
        this.modulo = modulo;
    }
    

    public ModPrograma(String id, String nombre, String descrip,String modulo) {
        this.descrip = descrip;
        this.nombre = nombre;
        this.id = id;
        this.modulo=modulo;
    }


    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


 

    public boolean Consultar(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT pro.id_programa,pro.nombrepro,pro.descripcionpro,md.id_modulo,md.nombremd FROM public.programa pro INNER JOIN public.modulo md ON pro.id_modulo=md.id_modulo WHERE pro.id_programa='"+id+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setDescrip(resultadoConsulta.getString("descripcionpro"));
                    this.setNombre(resultadoConsulta.getString("nombrepro"));
                    this.setId(resultadoConsulta.getString("id_programa"));
                    this.setModulo(resultadoConsulta.getString("nombremd"));
                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModPrograma.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarPrograma(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
                               
        String sentenciaSql = "INSERT INTO public.programa (nombrepro,descripcionpro,id_modulo,eliminacion) VALUES ('"+nombre+"','"+descrip+"','"+modulo+"','"+valor+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarPrograma(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.programa SET descripcionpro='"+descrip+"', nombrepro='"+nombre+"', id_modulo='"+modulo+"' WHERE id_programa='"+id+"' ";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoPrograma(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT id_programa, nombrepro, descripcionpro FROM public.programa WHERE eliminacion='TRUE' order by nombrepro asc;";
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
                     Logger.getLogger(ModPrograma.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String sentenciaSql = "UPDATE  public.programa set eliminacion='FALSE' WHERE id_programa='"+id+"';";
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
          
          public void limpiaTablaPrograma(JTable ptabprograma) {
        DefaultTableModel temp;
        try {
            temp = (DefaultTableModel) ptabprograma.getModel();
            int a = temp.getRowCount() - 1;
            for (int i = 0; i <= a; i++) {
                temp.removeRow(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
          
          public ArrayList<String> CargarPrograma() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombrec FROM catedra WHERE eliminacion='FALSE' order by nombrec";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
               try {
            ListPartida.add(("SIN PROGRAMA"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombrec"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return ListPartida;
    }
                      public String[][] Filtro (String busqueda){
       
            boolean estatusConsulta=false;
            
            //conexion = new ConexionBD();
            conexion.conectar();
            
            String tirasql = "SELECT * FROM public.programa WHERE nombrepro LIKE '%"+busqueda+"%' OR descripcionpro LIKE '%"+busqueda+"%' AND eliminacion='TRUE' ";
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
                    
                    
                    datos[i][0] = resultadoConsulta.getString("id_programa");
                    datos[i][1] = resultadoConsulta.getString("nombrepro");
                    datos[i][2] = resultadoConsulta.getString("descripcionpro");
                   
                            
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
                      
        String tirasql= "SELECT * FROM public.programa WHERE eliminacion='TRUE'";
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
                    
                    datos[i][0] = resultadoConsulta.getString("id_programa");
                    datos[i][1] = resultadoConsulta.getString("nombrepro");
                    datos[i][2] = resultadoConsulta.getString("descripcionpro");
                    
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            }
    }
        public ArrayList<String> CargarModulo() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombremd FROM modulo WHERE eliminacion='TRUE'  order by nombremd";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
               try {
            ListPartida.add(("SIN MODULO"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombremd"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return ListPartida;
    }
               public boolean CargarTablaPrograma(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
         conexion.conectar();
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT * FROM public.programa WHERE eliminacion='TRUE'";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaPrograma(ptabcargo);
        System.out.println(tiraSQL);
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
            Logger.getLogger(ModProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
         conexion.cerrar();   
        return existe;
    }     
        public ArrayList<String> CargarPrograma(String dat) {
        ArrayList<String> ListPartida = new ArrayList();
        String tiraSQL = "SELECT nombrepro FROM public.programa WHERE id_modulo= '"+dat+"' AND eliminacion='TRUE' order by nombrepro";
        conexion = new ConexionBD();
        conexion.conectar();
         ResultSet resultadoConsulta = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        
        if (resultadoConsulta==null){
            ListPartida.add("SIN PROGRAMA");
        }else{
        try {
            ListPartida.add("SELECCIONAR");
            while (resultadoConsulta.next()) {
                ListPartida.add(resultadoConsulta.getString("nombrepro"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      
        return ListPartida;
    }
                    
                    public boolean consultaridPrograma(String seleccionado) { //inicio del metodo consultar
        boolean existe = false;
        String tiraSQL = "SELECT id_programa FROM programa WHERE nombrepro='"+seleccionado+"' AND eliminacion='TRUE'";
        conexion= new ConexionBD();
        conexion.conectar();
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        
        if(rs!=null) {
        try {
            existe = rs.next();
            if (existe) {
                id = rs.getString(1);
            } else {
                System.out.println("¡No Existe el id_programa!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            existe=false;
        }
       // cerrarConexion(cn);
        return existe;

    }
                    
}
