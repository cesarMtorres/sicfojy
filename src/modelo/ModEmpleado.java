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
public class ModEmpleado {
    
    ConexionBD conexion  = new ConexionBD();
    // Datos de Usuario
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String sexo;
    private String cargo;
    private Date fechaNac;
   // public JasperReport reporte;

    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    private String id;
    //private JasperViewer jasperViewer;

    public ModEmpleado() {
    }

    public ModEmpleado(String id, String cedula, String nombre, String apellido, String telefono, String direccion, String sexo, String cargo, Date fechaNac) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.cargo = cargo;
        this.fechaNac=fechaNac;
        this.id = id;
    }



    public ModEmpleado(String cedula, String nombre, String apellido, String telefono, String direccion, String sexo, String cargo, Date fechaNac) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.cargo = cargo;
        this.fechaNac = fechaNac;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
            
            String sentenciaSQL = "SELECT id_persona,cargo,cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper FROM public.empleado emp JOIN public.persona per ON per.id_persona=emp.id_persona  WHERE cedula='"+cedula+"';";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setCedula(resultadoConsulta.getString("cedula"));
                    this.setNombre(resultadoConsulta.getString("nombre"));
                    this.setApellido(resultadoConsulta.getString("apellido"));
                    this.setTelefono(resultadoConsulta.getString("telefono"));
                    this.setDireccion(resultadoConsulta.getString("direccion"));
                    this.setCargo(resultadoConsulta.getString("cargo"));
                    this.setSexo(resultadoConsulta.getString("sexo"));
                    this.setFechaNac(resultadoConsulta.getDate("fechanac"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("Nombre: "+getNombre());
                    System.out.println("CARGO:"+getCargo());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarEmpleado(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
                               
        String sentenciaSql = "INSERT INTO public.empleado (cedula,nombre,apellido,direccion,telefono,sexo,fechanac,cargo) VALUES ('"+cedula+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"','"+sexo+"','"+fechaNac+"','"+cargo+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarEmpleado(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.empleado SET nombre='"+nombre+"',apellido='"+apellido+"',telefono='"+telefono+"',direccion='"+direccion+"',sexo='"+sexo+"',fechanac='"+fechaNac+"',cedula='"+cedula+"' WHERE id='"+id+"' ;";
        System.out.println("SQL.-"+sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoPersona(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT id,cedula, nombre,apellido,telefono,direccion,sexo,fechanac,cargo FROM public.empleado order by nombre asc;";
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
      
          public boolean Eliminar(String id){
         int velim = 0;
        boolean statusEliminar=false;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Seguro de Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (velim == 0) {
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "DELETE FROM public.empleado WHERE id='"+id+"';";
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
          
      public boolean CargarTablaEmpleado(JTable ptabempleado, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT id, cedula,nombre,apellido,direccion,sexo,telefono,fechanac,cargo FROM public.empleado";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaEmpleado(ptabempleado);
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
                        //ptabEmpleado.setToolTipText("Doble Click para Seleccionar Prtfil");
                    }
                    DefaultTableModel dtm = (DefaultTableModel) ptabempleado.getModel();
                    for (int i = 0; i < datos.size(); i++) {
                        dtm.addRow(datos.get(i));
                    }
                 }
            //} else {
            //    System.out.println("No Existe el empleado Consultado...");
           // }
        } catch (SQLException ex) {
            Logger.getLogger(ModEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaEmpleado(JTable ptabempleado) {
        DefaultTableModel temp;
        try {
            temp = (DefaultTableModel) ptabempleado.getModel();
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
                      
        String tirasql= "SELECT * FROM public.empleado";
        System.out.println(tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
        
        if(resultadoConsulta == null) return null;
        int i = 0;
        try {
            
           while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][9];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    
                    datos[i][0] = resultadoConsulta.getString("cedula");
                    datos[i][1] = resultadoConsulta.getString("nombre");
                    datos[i][2] = resultadoConsulta.getString("apellido");
                    datos[i][3] = resultadoConsulta.getString("direccion");
                    datos[i][4] = resultadoConsulta.getString("cargo");
                    datos[i][5] = resultadoConsulta.getString("telefono");
                    datos[i][6] = resultadoConsulta.getString("sexo");
                    datos[i][7] = resultadoConsulta.getString("fechanac");
                    datos[i][7] = resultadoConsulta.getString("cargo");
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
    
      
}
