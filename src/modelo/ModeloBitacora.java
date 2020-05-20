
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bi-lu
 */
public class ModeloBitacora {
    ConexionBD conexion  = new ConexionBD();
    Connection cn;
    ResultSet rs;
    int contador;
    
    
       public boolean RegistrarBitacora(String empleado,String desc){
          boolean inc;
          String SQL="UPDATE  bitacora SET id_empleado='"+empleado+"', descripcion='"+desc+"' WHERE id=(SELECT MAX(id) FROM bitacora)";
           System.out.println("bitacora._SQL-"+SQL);
          conexion.conectar();
           rs = conexion.ejecutarConsulta(SQL);
          if(rs!=null){
                    inc=true;
                }else{
                    inc=false;
                }    
            
            conexion.cerrar();
            return inc;
      }  

 
     public DefaultTableModel tabla_bitacora(String tira_SQL)
        {
            String[] cabecera = {"Usuario","Fecha","Acción","Hora","Sentencia","Descripcion"};
            DefaultTableModel t = new DefaultTableModel(null, cabecera);
            String SQL = tira_SQL ;
            String[] fila = new String[7];
            conexion.conectar();
            rs = conexion.ejecutarConsulta(SQL);
            try
                {
                    contador = 0;
                    
                    while(rs.next())
                        {
                            fila[0] = rs.getString(1);
                            fila[1] = rs.getString(2);
                            fila[2] = rs.getString(3);
                            fila[3] = rs.getString(4);
                            fila[4] = rs.getString(5);
                       //     SimpleDateFormat formatime = new SimpleDateFormat("HH:MM");
                         //   fila[3] = formatime.format(rs.getString(4));
                            fila[5] = rs.getString(6);
                         //   fila[6] = rs.getString(7);
                            contador++;
                            
                            t.addRow(fila);
                        }
                }
                catch(SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
            conexion.cerrar();
            return t;
        }
 
}
