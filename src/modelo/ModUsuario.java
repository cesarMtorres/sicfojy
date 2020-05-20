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
public class ModUsuario {
    
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String sexo;
    private String cargo;
    private Date fechaNac;
    private String clave;
    private String perfil;
    private String nacion;
    private String login;
   // public JasperReport reporte;

    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    private String id;
    private String valor="TRUE";
    //private JasperViewer jasperViewer;

    public ModUsuario() {
    }

    public ModUsuario(String id, String cedula,String login,String clave, String perfil, String nombre, String apellido, String telefono, String direccion, String sexo, String cargo, Date fechaNac,String nacion) {
        this.login=login;
        this.clave=clave;
        this.perfil=perfil;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.cargo = cargo;
        this.fechaNac=fechaNac;
        this.nacion=nacion;
        this.id = id;
    }



    public ModUsuario(String cedula,String login,String clave, String perfil, String nombre, String apellido, String telefono, String direccion, String sexo, String cargo, Date fechaNac,String nacion) {
        this.login=login;
        this.clave=clave;
        this.perfil=perfil;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.cargo = cargo;
        this.fechaNac = fechaNac;
        this.nacion=nacion;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }

    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
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
            
            String sentenciaSQL = "SELECT * FROM public.vrusuporperfil WHERE cedulaper='" + cedula + "'";
            System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setCedula(resultadoConsulta.getString("cedulaper"));
                    this.setNombre(resultadoConsulta.getString("nombreper"));
                    this.setApellido(resultadoConsulta.getString("apellidoper"));
                    this.setTelefono(resultadoConsulta.getString("telefonoper"));
                    this.setDireccion(resultadoConsulta.getString("direccionper"));
                    this.setNacion(resultadoConsulta.getString("nacionper"));
                    this.setCargo(resultadoConsulta.getString("nombrecar"));
                    this.setClave(resultadoConsulta.getString("clave"));
                    this.setLogin(resultadoConsulta.getString("login"));
                    this.setPerfil(resultadoConsulta.getString("id_perfil"));
                    this.setSexo(resultadoConsulta.getString("generoper"));
                    this.setFechaNac(resultadoConsulta.getDate("fnacimientoper"));
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("id: "+getId()+"idperfil"+getPerfil());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean Registrar(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql1 = "INSERT INTO public.usuario (clave,login,id_perfil,id_usu_cargo,eliminacion) VALUES (MD5('"+clave+"'),'"+login+"','"+perfil+"','"+cargo+"','"+valor+"')";
        String sentenciaSql = "INSERT INTO public.persona (cedulaper,nombreper,apellidoper,direccionper,telefonoper,generoper,fnacimientoper,nacionper,eliminacion) VALUES ('"+cedula+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"','"+sexo+"','"+fechaNac+"','"+nacion+"','"+valor+"')";
        
        int ejecutado1 = conexion.ejecutarActualizacion(sentenciaSql1);
        System.out.println(sentenciaSql+" + "+sentenciaSql1);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        if(ejecutado!=0 && ejecutado1!=0){
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
        
        String sentenciaSql = "UPDATE public.persona SET nombreper='"+nombre+"',apellidoper='"+apellido+"',telefonoper='"+telefono+"',direccionper='"+direccion+"',generoper='"+sexo+"',fnacimientoper='"+fechaNac+"',cedulaper='"+cedula+"' WHERE id_persona='"+id+"' ;";
        String sentenciaSql1 = "UPDATE public.usuario SET clave=MD5('"+clave+"'),login='"+login+"',id_perfil='"+perfil+"',id_usu_cargo='"+cargo+"' WHERE id='"+id+"' ;";
         int ejecutado1 = conexion.ejecutarActualizacion(sentenciaSql1);
        System.out.println("SQL.-"+sentenciaSql+""+sentenciaSql1);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
       
        
        if(ejecutado!=0 && ejecutado1!=0){
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
             String sentencia = "SELECT * FROM public.empleado WHERE eliminacion='TRUE' order by nombre asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=9;
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
                     Logger.getLogger(ModUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String sentenciaSql = "UPDATE  public.usuario set eliminacion='FALSE' WHERE id='"+id+"';";
        String sentenciaSql1 = "UPDATE  public.persona set eliminacion='FALSE' WHERE id_persona='"+id+"';";
        System.out.println(sentenciaSql+" "+sentenciaSql1);
        int ejecutado1 = conexion.ejecutarActualizacion(sentenciaSql1);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0 && ejecutado!=0){
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
        String tiraSQL = "SELECT * FROM public.empleado WHERE eliminacion='TRUE'";
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
            Logger.getLogger(ModUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
                      
        String tirasql= "SELECT * FROM public.vrusuporperfil ";
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
                    
                    datos[i][0] = resultadoConsulta.getString("cedulaper");
                    datos[i][1] = resultadoConsulta.getString("nombreper");
                    datos[i][2] = resultadoConsulta.getString("apellidoper");
                    datos[i][3] = resultadoConsulta.getString("direccionper");
                    datos[i][4] = resultadoConsulta.getString("nombrecar");
                    datos[i][5] = resultadoConsulta.getString("telefonoper");
                    datos[i][6] = resultadoConsulta.getString("generoper");
                    datos[i][7] = resultadoConsulta.getString("fnacimientoper");
                    datos[i][8] = resultadoConsulta.getString("nacionper");
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
            
            String tirasql = "SELECT * FROM public.vrusuporperfil WHERE cedulaper LIKE '%"+busqueda+"%' OR nombreper LIKE '%"+busqueda+"%'";
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
                    datos[i][4] = resultadoConsulta.getString("nombrecar");
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
public boolean permiso(String contrasena) {
        boolean statusConsulta=false;
        
        conexion.conectar();
        
        String sentenciaSQL = "SELECT * FROM usuario WHERE clave='"+contrasena+"' AND id_perfil='6'";
        System.out.println("SQL.-"+sentenciaSQL); 
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
         
          try{
            if(resultadoConsulta!=null){
                resultadoConsulta.next();
                this.setClave(resultadoConsulta.getString("clave"));

                statusConsulta=true;
            }else{
                statusConsulta=false;
            }
            } catch (SQLException ex){
                
                Logger.getLogger(ModUsuario.class.getName()).log(Level.SEVERE,null,ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            }
    
}
