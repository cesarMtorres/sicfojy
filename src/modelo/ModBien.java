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

/**
 *
 * @author rover
 */
public class ModBien {
    ConexionBD conexion  = new ConexionBD();;
    // Datos de Usuario
    private String modelo;
    private String serial;
    private String medida;
    private String tipoB;
    private String nInventario;
    private String marca;
    private String cantidad;
    private String id;
    private String valor="FALSE";
    private String valor1="TRUE";
    private String nombre;
   // public JasperReport reporte;
    //Conexion con la vista correspondiente
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    //private JasperViewer jasperViewer;

    public ModBien() {
    }

    public ModBien(String nombre,String modelo, String serial, String medida, String tipoB, String nInventario, String marca, String cantidad, String id) {
        this.nombre=nombre;
        this.modelo = modelo;
        this.serial = serial;
        this.medida = medida;
        this.tipoB = tipoB;
        this.nInventario = nInventario;
        this.marca = marca;
        this.cantidad = cantidad;
        this.id = id;
    }
    
    public ModBien(String nombre,String modelo, String serial, String medida, String tipoB, String nInventario, String marca,String cantidad) {
        this.nombre=nombre;
        this.modelo = modelo;
        this.serial = serial;
        this.medida = medida;
        this.tipoB = tipoB;
        this.nInventario = nInventario;
        this.marca = marca;
        this.cantidad = cantidad;
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

    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getTipoB() {
        return tipoB;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    

    public void setTipoB(String tipoB) {
        this.tipoB = tipoB;
    }

    public String getnInventario() {
        return nInventario;
    }

    public void setnInventario(String nInventario) {
        this.nInventario = nInventario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
 

    
    public boolean Consultar(String serial){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.vbien WHERE serial='"+serial+"' AND eliminacion='TRUE';";
            System.out.println("Consultar() SQL .-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setModelo(resultadoConsulta.getString("nombremo"));
                    this.setSerial(resultadoConsulta.getString("serial"));
                    this.setMarca(resultadoConsulta.getString("nombrema"));
                    this.setCantidad(resultadoConsulta.getString("cantidad"));
                    this.setNombre(resultadoConsulta.getString("nombreb"));
                    this.setnInventario(resultadoConsulta.getString("ninventario"));
                    this.setTipoB(resultadoConsulta.getString("nombretb"));
                    this.setMedida(resultadoConsulta.getString("medida"));
                    
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("serial: "+this.getSerial());
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
    
    public boolean ConsultarId(String id){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
            String sentenciaSQL = "SELECT * FROM public.vbien WHERE id='"+id+"' AND eliminacion='TRUE';";
            System.out.println("Consultar() SQL .-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);    
            try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setId(resultadoConsulta.getString("id"));
                    this.setModelo(resultadoConsulta.getString("nombremo"));
                    this.setSerial(resultadoConsulta.getString("serial"));
                    this.setMarca(resultadoConsulta.getString("nombrema"));
                    this.setCantidad(resultadoConsulta.getString("cantidad"));
                    this.setNombre(resultadoConsulta.getString("nombreb"));
                    this.setnInventario(resultadoConsulta.getString("ninventario"));
                    this.setTipoB(resultadoConsulta.getString("nombretb"));
                    this.setMedida(resultadoConsulta.getString("medida"));
                    
                    //this.setFechaNac(resultadoConsulta.get);
//                    this.setCedula(resultadoConsulta.getString(2));
  //                  this.setNombre(resultadoConsulta.getString(3));
                        
                    System.out.println("serial: "+this.getSerial());
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
    
    public boolean RegistrarBien(){
        boolean statusIncluir = false;
        conexion = new ConexionBD();
        conexion.conectar();
        System.out.println("registrar Bien");
        int fijo=1;
        int tipo=5;
        int count = Integer.parseInt(cantidad);
        for (int i=1;i<=count;i++){
        String sentenciaSql = "INSERT INTO public.bien (nombreb,id_modelo,serial, medida,id_tipob,nInventario,id_marca,cantidad,tipo_move,eliminacion) VALUES ('"+nombre+"','"+modelo+"','"+serial+"','"+medida+"','"+tipoB+"','"+nInventario+"','"+marca+"','"+fijo+"','"+tipo+"','"+valor1+"')";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir= true;
        }else{
            statusIncluir = false;
        }
       
        }
        
        
        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir

     public boolean ModificarBien(String id){
        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.bien SET nombreb='"+nombre+"', medida='"+medida+"', serial='"+serial+"',id_marca='"+marca+"',id_modelo='"+modelo+"',id_tipob='"+tipoB+"',ninventario='"+nInventario+"',cantidad='"+cantidad+"' WHERE id='"+id+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }//fin de incluir
     
      public void cargarDatosCatalogoBien(DefaultTableModel tablaModel){
             boolean sw=false; 
	     
	     conexion.conectar();
             String sentencia = "SELECT * FROM public.vbien WHERE eliminacion='TRUE' order by id desc;";
             System.out.println("CargarDatosCatalagobien() "+ sentencia);
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
             if (resul != null){
                 try {
                     int cantidadColumnas=9;
                     //Establecer como cabezeras el nombre de las columnas
                     String titulo[] ={"Id","Modelo"," Serial","Marca","Tipo Bien","Medida","Cantidad","Numero Inventario","Fecha Nacimiento"};
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
                     Logger.getLogger(ModBien.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
             conexion.cerrar();
	     
        }//fin de cargarDatos
      
          public boolean Eliminar(String id){
        boolean statusEliminar=false;

        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.bien set eliminacion='"+valor+"' WHERE id='"+id+"';";
        System.out.println("eliminar() SQL.-" +sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        conexion.cerrar();       
 
        
        return statusEliminar;
    }//fin de incluir 
          
      public boolean CargarTablaBien(JTable ptabcargo, String pcriterio) throws SQLException {
        boolean existe = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        ResultSetMetaData rsm;
        String tiraSQL = "SELECT nombreb,serial,nombremo,nombrema,cantidad,nombretb FROM public.vbien";
        if (!"".equals(pcriterio)) {
           // tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaBien(ptabcargo);
        System.out.println("CargarTablaBien() " +tiraSQL);
        
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
            Logger.getLogger(ModBien.class.getName()).log(Level.SEVERE, null, ex);
        }
       conexion.cerrar();
        return existe;
    }
          public void limpiaTablaBien(JTable ptabcargo) {
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
public String[][] consultarListado() {
       
        boolean consulta = false;
        
        //conexion = new ConexionBD();
        conexion.conectar();
                      
       //VIEJA CONFIABLE String tirasql= "SELECT nombreb,serial,nombremo,nombrema,cantidad,nombretb FROM public.vbien";
       String tirasql= "SELECT nombreb,serial,nombrema,nombretb, COUNT(*) as num_repeticiones FROM vbien u WHERE eliminacion='TRUE'\n" +
"	    GROUP BY nombreb,serial,nombrema,nombretb\n" +
"	    HAVING COUNT(*)=1 OR COUNT(*)>1;";
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
                                        
                    datos[i][0] = resultadoConsulta.getString("nombreb");
                     datos[i][1] = resultadoConsulta.getString("serial");
                    datos[i][2] = resultadoConsulta.getString("nombrema");
                    datos[i][3] = resultadoConsulta.getString("nombretb");
                    datos[i][4] = resultadoConsulta.getString("num_repeticiones");
                    
                    
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
            
            String tirasql = "SELECT * FROM public.vbien WHERE nombreb LIKE '%"+busqueda+"%' AND eliminacion='"+valor1+"' ";
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
                    
                    
                    datos[i][0] = resultadoConsulta.getString("serial");
                    datos[i][1] = resultadoConsulta.getString("nombrema");
                    datos[i][2] = resultadoConsulta.getString("nombremo");
                    datos[i][3] = resultadoConsulta.getString("medida");
                    datos[i][4] = resultadoConsulta.getString("cantidad");
                    datos[i][5] = resultadoConsulta.getString("ninventario");
                    datos[i][6] = resultadoConsulta.getString("nombretb");
                    datos[i][7] = resultadoConsulta.getString("nombreb");
                   
                            
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
     public ArrayList<String> Cargarmodelo() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombremo FROM public.modelo WHERE eliminacion='TRUE'";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
               try {
            ListPartida.add(("Seleccionar"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombremo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Bien.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return ListPartida;
    }   
     
     public ArrayList<String> Cargartipobien() {
        ArrayList<String> ListPartida = new ArrayList();
        conexion = new ConexionBD();
        conexion.conectar();        
        String tiraSQL = "SELECT nombretb FROM tipobien WHERE eliminacion='TRUE' order by nombretb";
        ResultSet rs = conexion.ejecutarConsulta(tiraSQL);
        System.out.println(tiraSQL);
               try {
            ListPartida.add(("SIN TIPO BIEN"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombretb"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Bien.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return ListPartida;
    }
 public ArrayList<String> Cargarmarca() {
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
            ListPartida.add(("SELECCIONAR"));
            while (rs.next()) {
                ListPartida.add(rs.getString("nombrema"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vista.Bien.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
               
        return ListPartida;
    }
     public boolean inhabilitar(String id) {
        boolean resultado = false;
        
        conexion = new ConexionBD();
        conexion.conectar();
        
        String SQL= "UPDATE bien SET "
                + "eliminacion= 'FALSE'"
                + " WHERE id= '"+id+"';";
        
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
        
        String tirasql = "UPDATE bien SET eliminacion ='TRUE' WHERE id='"+id+"';";
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
     
          public boolean consultarCantidad(String bien) throws SQLException{
        boolean statusA = false;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String tirasql = "SELECT count(id) from bien WHERE nombreb='"+bien+"'AND eliminacion='TRUE'";
        System.out.println(tirasql);
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);    
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setCantidad(resultadoConsulta.getString("count"));
        }else{
            statusA = false;
        }
        
        conexion.cerrar();
        return statusA;
     }
     
}
