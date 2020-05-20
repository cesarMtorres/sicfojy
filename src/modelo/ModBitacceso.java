/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import vista.CatBitacceso;

/**
 *
 * @author rover
 */
public class ModBitacceso extends ModeloBaseDatos {

    // Datos de Bitácora
    private String codopc;
    private String descopc;
    private String nomusu;
    private String acceso;
    private String fecha;
    private String hora;
    
    //Conexion con la vista correspondiente
    CatBitacceso cbitac;
    //Conexion y Resultados
    private Connection conn, connp;
    private ResultSet rs;
    private final String logotipo = "/images/mainsgcarep.png";

    public ModBitacceso() {
    }

    public ModBitacceso(CatBitacceso vista_consulta_valores) {

    }

    public String getCodopc() {
        return codopc;
    }

    public void setCodopc(String codopc) {
        this.codopc = codopc;
    }

    public String getDescopc() {
        return descopc;
    }

    public void setDescopc(String descopc) {
        this.descopc = descopc;
    }

    public String getNomusu() {
        return nomusu;
    }

    public void setNomusu(String nomusu) {
        this.nomusu = nomusu;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
    
    //Inicio de registrar
    public boolean registrarBitacceso(String pmodu, String petique, String mnomusu, boolean valido, String codusuario) {
        boolean sw;
        sw = false;
        String tiraSQL = "";
        conn = crearConexion();
        if(mnomusu.equals("MASTER")) {
            tiraSQL = "INSERT INTO datos.bitacces (codopc, descopc, nomusu, acceso, fecha, hora) ";
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
    //fin de registrar
    //Inicio de Modificar

    //Busca los Datos del Perfil y los carga en un Jtable
    public boolean CargarTablaBitacceso(JTable ptabBitacceso, String pcriterio) throws SQLException {
        boolean existe = false;
        ResultSetMetaData rsm;
        String tiraSQL = "";
        String codp = "";
        //tiraSQL = "SELECT codopc, descopc, nomusu, acceso, fecha, hora FROM datos.perfil";
        tiraSQL = "SELECT codopc, descopc, nomusu, fecha, hora, ";
	tiraSQL += "CASE acceso ";
        tiraSQL += "WHEN 'TRUE'::boolean THEN 'Sí'::text ";
        tiraSQL += "WHEN 'FALSE'::boolean THEN 'No'::text ";
        tiraSQL += "ELSE NULL::text ";
        tiraSQL += "END AS acceso ";
        tiraSQL += "FROM datos.bitacces ";
        
        if (!"".equals(pcriterio)) {
            //tiraSQL += " WHERE " + pcriterio;
            tiraSQL += pcriterio;
        } else {
        }
        limpiaTablaPerfil(ptabBitacceso);
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
                            if (rs.getObject(i + 1) instanceof Date){
                                //SimpleDateFormat formatea = new SimpleDateFormat("dd-MM-yyyy");
                                if(i==3) {
                                    SimpleDateFormat formatea = new SimpleDateFormat("dd-MM-yyyy");
                                    rows[i] = formatea.format(rs.getObject(i + 1)).toString();
                                }
                                if(i==4) {
                                    SimpleDateFormat formatime = new SimpleDateFormat("HH:MM a");
                                    rows[i] = formatime.format(rs.getObject(i + 1)).toString();
                                }
                            } else {
                                rows[i] = rs.getObject(i + 1);
                            }
                        }
                        datos.add(rows);
                        //ptabperfil.setToolTipText("Doble Click para Seleccionar Prtfil");
                    }
                    DefaultTableModel dtm = (DefaultTableModel) ptabBitacceso.getModel();
                    for (int i = 0; i < datos.size(); i++) {
                        dtm.addRow(datos.get(i));
                    }
                 }
            //} else {
            //    System.out.println("No Existe el Perfil Consultado...");
           // }
        } catch (SQLException ex) {
            Logger.getLogger(ModBitacceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion(conn);
        return existe;
    }
    //Fin Cargar Tabla Perfil
    // Limpiar Tabla de Perfil
    public void limpiaTablaPerfil(JTable ptabBitacceso) {
        DefaultTableModel temp;
        try {
            temp = (DefaultTableModel) ptabBitacceso.getModel();
            int a = temp.getRowCount() - 1;
            for (int i = 0; i <= a; i++) {
                temp.removeRow(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
