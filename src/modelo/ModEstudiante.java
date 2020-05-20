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

import vista.CatEstudiante;

/**
 *
 * @author rover
 */
public class ModEstudiante {
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String sexo;
    private String valor="FALSE";
    private String valor1="TRUE";
    private String catedra;
    private Date fechaNac;
    private String id;
    private String status;
    private String representante;
    private String Nombrerepre;
    private String nacion;
    private String idd;
    
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModEstudiante() {
    }

    public ModEstudiante(CatEstudiante catEstudiante) {

    }

    public ModEstudiante(String cedula, String nombre, String apellido, String telefono, String direccion, String sexo, String catedra, Date fechaNac,String nacion,String representante) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.catedra = catedra;
        this.fechaNac = fechaNac;
        this.nacion=nacion;
        this.representante=representante;
    }

    
    public ModEstudiante(String id,String cedula, String nombre, String apellido, String telefono, String direccion, String sexo, String catedra, Date fechaNac,String nacion,String representante) {
        this.id=id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.catedra = catedra;
        this.fechaNac = fechaNac;
        this.nacion=nacion;
        this.representante=representante;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getNombrerepre() {
        return Nombrerepre;
    }

    public void setNombrerepre(String Nombrerepre) {
        this.Nombrerepre = Nombrerepre;
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

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
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

    public String getCatedra() {
        return catedra;
    }

    public void setCatedra(String catedra) {
        this.catedra = catedra;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
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
            
            String sentenciaSQL = "SELECT pr.id_persona,pr.cedulaper, pr.nombreper,pr.apellidoper,pr.telefonoper,pr.nacionper,pr.direccionper,pr.generoper,pr.fnacimientoper,pr.nombrec,pr.eliminacion,per.cedulaper as representante,pr.id_representante FROM public.vestudiante as pr JOIN persona as per ON pr.id_representante=per.id_persona WHERE pr.cedulaper='"+cedula+"' "; //AND tipoper=2
            System.out.println("funcion Consultar.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id_persona"));
                    this.setCedula(resultadoConsulta.getString("cedulaper"));
                    this.setNombre(resultadoConsulta.getString("nombreper"));
                    this.setApellido(resultadoConsulta.getString("apellidoper"));
                    this.setTelefono(resultadoConsulta.getString("telefonoper"));
                    this.setDireccion(resultadoConsulta.getString("direccionper"));
                    this.setCatedra(resultadoConsulta.getString("nombrec"));
                    this.setStatus(resultadoConsulta.getString("eliminacion"));
                    this.setNacion(resultadoConsulta.getString("nacionper"));
                    this.setSexo(resultadoConsulta.getString("generoper"));
                    this.setRepresentante(resultadoConsulta.getString("id_representante"));
                    this.setNombrerepre(resultadoConsulta.getString("cedulaper"));
                    this.setFechaNac(resultadoConsulta.getDate("fnacimientoper"));
                    
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("Nombre: "+getNombre());
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    
    public boolean RegistrarEstudiante(){
        boolean statusIncluir;
        conexion = new ConexionBD();
        conexion.conectar();
        int tipoper=2;   

        String sentenciaSql = "INSERT INTO public.persona (cedulaper,nombreper,apellidoper,direccionper,telefonoper,generoper,fnacimientoper,nacionper,tipoper,id_per_representante,eliminacion) VALUES ('"+cedula+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"','"+sexo+"','"+fechaNac+"','"+nacion+"','"+tipoper+"','"+representante+"','"+valor1+"')";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
       
        if(ejecutado!=0){
           String sentenciaSql1="SELECT MAX(id_persona) FROM persona WHERE tipoper='2' ";
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
             sentenciaSql = "INSERT INTO public.estudiante (id_persona,id_representante,id_catedra,eliminacion) values ('"+idd+"','"+representante+"','"+catedra+"','"+valor1+"')";
             conexion.ejecutarActualizacion(sentenciaSql);
             
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    
    
     public boolean ModificarEstudiante(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.estudiante SET cedula='"+cedula+"', nombre='"+nombre+"',apellido='"+apellido+"',telefono='"+telefono+"',direccion='"+direccion+"',sexo='"+sexo+"',catedra='"+catedra+"',fnacimiento='"+fechaNac+"',nacion='"+nacion+"' WHERE id='"+id+"';";
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
             String sentencia = "SELECT id_persona,cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper,nacionper,nombrec FROM public.vestudiante order by nombreper asc;";
             System.out.println(sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=9;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Cedula"," Nombre","Apellido","Telefono","Nacion","Direccion","Sexo","Catedra","Fecha Nacimiento"};
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
                     Logger.getLogger(ModEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String sentenciaSql = "UPDATE public.estudiante set eliminacion='"+valor+"' WHERE id='"+id+"';";
        System.out.println(sentenciaSql);
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
          
      public boolean CargarTablaEstudiante(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT * FROM public.persona";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaEstudiante(ptabcargo);
        System.out.println("Cargar Tabla Estudiante" +tiraSQL);
        
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
            Logger.getLogger(ModEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaEstudiante(JTable ptabcargo) {
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
          
  /////////////CATALAGO
   
        
        
        /////////////CATALAGO
        
        public String[][] consultarListado() {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
        String tirasql= "SELECT cedulaper, nombreper,apellidoper,telefonoper,direccionper,generoper,fnacimientoper,nacionper,nombrec FROM public.vestudiante";
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
                    datos[i][4] = resultadoConsulta.getString("nombrec");
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
            
            String tirasql = "SELECT * FROM public.persona WHERE cedulaper LIKE '%"+busqueda+"%' OR nombreper LIKE '%"+busqueda+"%' AND tipoper='2' ";
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
                    datos[i][4] = resultadoConsulta.getString("telefonoper");
                  //  datos[i][5] = resultadoConsulta.getString("nombrec");
                    datos[i][6] = resultadoConsulta.getString("generoper");
                    datos[i][7] = resultadoConsulta.getString("fnacimientoper");
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
        public ArrayList<String> CargarEstudiante(String dat) {
        ArrayList<String> ListPartida = new ArrayList();
        String tiraSQL = "SELECT nombreper FROM vcatedra_estudiante WHERE id_catedra = '"+dat+"' order by nombreper";
        conexion = new ConexionBD();
        conexion.conectar();
         ResultSet resultadoConsulta = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        
        if (resultadoConsulta==null){
            ListPartida.add("SIN ESTUDIANTE");
        }else{
        try {
            ListPartida.add("SIN ESTUDIANTE");
            while (resultadoConsulta.next()) {
                ListPartida.add(resultadoConsulta.getString("nombreper"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      
        return ListPartida;
    }
                  
    public ArrayList<String> CargarEstudiante() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombreper FROM persona WHERE tipoper='2' AND eliminacion='TRUE' order by nombreper";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        if (rs==null){
            ListPartida.add("SIN ESTUDIANTE");
        }else{
               try {
            ListPartida.add(("SIN ESTUDIANTE"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombreper"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return ListPartida;
    }
    public ArrayList<String> CargarEstudianteProfesor(String dat) {
        ArrayList<String> ListPartida = new ArrayList();
        String tiraSQL = "SELECT nombreper FROM vprofesor_estudiante WHERE id_profesor= '"+dat+"' AND eliminacion='TRUE' order by nombreper";
        conexion = new ConexionBD();
        conexion.conectar();
         ResultSet resultadoConsulta = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
        
        if (resultadoConsulta==null){
            ListPartida.add("SIN ESTUDIANTE");
        }else{
        try {

            while (resultadoConsulta.next()) {
                ListPartida.add(resultadoConsulta.getString("nombreper"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      
        return ListPartida;
    }
    
        public boolean inhabilitar(String cargo) {
        boolean resultado = false;
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String SQL= "UPDATE persona SET "
                + "eliminacion= 'FALSE'"
                + " WHERE id_persona = '"+cargo+"';";
        
        int ejecutar = conexion.ejecutarActualizacion(SQL);
        
        if(ejecutar!=0) {
            resultado = true;
        } else {
            resultado = false;
        }
        conexion.cerrar();
        return resultado;
    }
    
     public boolean Activar(String cargo){
        boolean statusA;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String tirasql = "UPDATE persona SET eliminacion ='TRUE' WHERE id_persona='"+cargo+"';";
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
        public boolean consultaridestudiante(String seleccionado) { //inicio del metodo consultar
        boolean existe = false;
        String tiraSQL = "SELECT id_persona FROM persona WHERE nombreper='"+seleccionado+"' AND tipoper='2' AND eliminacion='TRUE' ";
         System.out.println("TIRA SQL:"+tiraSQL);
        conexion= new ConexionBD();
        conexion.conectar();
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);

        try {
            existe = rs.next();
            if (existe) {
                id= rs.getString(1);
            } else {
                System.out.println("¡No Existe el estudiante!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

       // cerrarConexion(cn);
        return existe;

    }
}
