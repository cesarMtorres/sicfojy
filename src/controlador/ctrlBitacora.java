/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import vista.Bitacora;
import modelo.ModeloBitacora;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModUsuario;
import vista.CatUsuario;
import vista.Principal;
/**
 *
 * @author arca
 */
public class ctrlBitacora implements ActionListener{

    Bitacora vista;
    ModeloBitacora modelo;
    String persona;
    String sqlFecha;
    String sqlHora;
    String fechaDesde;
    String fechaHasta;
    String horaDesde;
    String horaHasta;
    String formulario;
    String accion;
    CatUsuario vusuario;
    private int acc;
    public ModUsuario modelou;
    
    public ctrlBitacora(Bitacora vista){
    this.vista=vista;
    modelo = new ModeloBitacora();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource()==vista.getBuscarPersonal()){
     
                    System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vusuario = new CatUsuario(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrlBitacora.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vusuario.setVisible(true);

            if(vusuario.tabCatalago.isRowSelected(vusuario.tabCatalago.getSelectedRow()) ){
                codigobus = vusuario.tabCatalago.getValueAt(vusuario.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vusuario.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelou =new ModUsuario();
           
            if (codigobus != null) {
                
                boolean existe = modelou.Consultar(codigobus);
                if (existe) {
                    vista.Persona.setText(modelou.getCedula());
         
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Usuario" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
     }
     if(e.getSource()==vista.getBtnConsultar()){
    
    if("".equals(vista.getPersona().getText()) ||  vista.getFecha_Desde().getDate()==null && vista.getFecha_Hasta().getDate()!=null || vista.getFecha_Hasta().getDate()==null && vista.getFecha_Desde().getDate()!=null)
    {
        JOptionPane.showMessageDialog(null, "Por Favor Complete los Datos Restante", "ALERTA", JOptionPane.WARNING_MESSAGE);
   
    }else{
        
    ConsultarBitacora();
    
    }
    
    }
    
    if(e.getSource()==vista.getBtnCancelar()){
         vista.dispose();
        
    }
    
    }
        public void llenar_tabla()
        {
          String tira_SQL =   "SELECT u.login,b.fecha,b.accion,b.hora,b.sentencia,b.descripcion\n" +
                                "FROM bitacora as b INNER JOIN empleado as e ON e.id_usuario=b.id_empleado INNER JOIN usuario as u ON u.id=e.id_usuario INNER JOIN persona as per ON per.id_persona=u.id_persona ORDER BY b.id DESC LIMIT 20";
    
         vista.getTabla_bitacora().setModel(modelo.tabla_bitacora(tira_SQL));
          vista.getTabla_bitacora().getColumnModel().getColumn(0).setPreferredWidth(200);
          vista.getTabla_bitacora().getColumnModel().getColumn(1).setPreferredWidth(150);
          vista.getTabla_bitacora().getColumnModel().getColumn(2).setPreferredWidth(150);
          vista.getTabla_bitacora().getColumnModel().getColumn(3).setPreferredWidth(200);
          vista.getTabla_bitacora().getColumnModel().getColumn(4).setPreferredWidth(400);
          vista.getTabla_bitacora().getColumnModel().getColumn(5).setPreferredWidth(1200);
          
      //      vista.getTabla_bitacora().getColumnModel().getColumn(5).setPreferredWidth(200);
          //  vista.getTabla_bitacora().getColumnModel().getColumn(6).setPreferredWidth(600);
        
         }
        
              public void llenarT_tabla()
        {
          String tira_SQL =   "SELECT *\n" +
                                "FROM bitacora ";
    
            vista.getTabla_bitacora().setModel(modelo.tabla_bitacora(tira_SQL));
            vista.getTabla_bitacora().getColumnModel().getColumn(0).setPreferredWidth(200);
            vista.getTabla_bitacora().getColumnModel().getColumn(1).setPreferredWidth(150);
            vista.getTabla_bitacora().getColumnModel().getColumn(2).setPreferredWidth(150);
           vista.getTabla_bitacora().getColumnModel().getColumn(3).setPreferredWidth(200);
          vista.getTabla_bitacora().getColumnModel().getColumn(4).setPreferredWidth(400);
          vista.getTabla_bitacora().getColumnModel().getColumn(5).setPreferredWidth(1200);
          
      //      vista.getTabla_bitacora().getColumnModel().getColumn(5).setPreferredWidth(200);
          //  vista.getTabla_bitacora().getColumnModel().getColumn(6).setPreferredWidth(600);
        
         }
        
    public void ConsultarBitacora(){
        
        boolean ctrl1= false;
        boolean ctrl2= false;
        boolean ctrl3= false;
        boolean ctrl4= false;
        boolean ctrl5= false;
        persona="";
        sqlFecha="";
        fechaDesde="";
        fechaHasta="";
        formulario="";
        accion="";

      //condiciones para saber cual caja de texto es no esta llena  
       if(!Bitacora.getPersona().getText().isEmpty()){
       
       ctrl1=true;
       
       }
        
       
       
       if(vista.getFormulario().getSelectedIndex()==0){
       }else{
       
       ctrl3=true;
           
       }
      
       if(vista.getFecha_Desde().getDate()==null && vista.getFecha_Hasta().getDate()==null){
           
       }else{
          
       ctrl4=true;
           
               
       }
           
          
      if(vista.getAccion().getSelectedIndex()==0){
      }else{
      
      ctrl5=true;
      
      }
      
      //condicion para captura los valores de las caja de texto activa 
      if(ctrl1){
          
      persona="WHERE per.cedulaper='"+Bitacora.getPersona().getText()+"' ";
      
      }
      
      
      if(ctrl3){
      
      if(ctrl1 || ctrl2){
          
      formulario="AND b.entidad='"+vista.getFormulario().getSelectedItem().toString().toUpperCase()+"' ";
      
      }else{
          
      formulario="WHERE b.entidad='"+vista.getFormulario().getSelectedItem().toString().toUpperCase()+"' ";
      
      }
      }
      
      if(ctrl4){
          
      fechaDesde= new SimpleDateFormat("yyyy-MM-dd").format(vista.getFecha_Desde().getDate());
      fechaHasta= new SimpleDateFormat("yyyy-MM-dd").format(vista.getFecha_Hasta().getDate());
              
      if(ctrl1 || ctrl2 || ctrl3){
          
      sqlFecha="AND b.fecha BETWEEN '"+fechaDesde+"' AND '"+fechaHasta+"' ";
      
      }else{
          
      sqlFecha=" b.fecha BETWEEN '"+fechaDesde+"' AND '"+fechaHasta+"' ";
      
      }
      }
      
      if(ctrl5){
      
      if(ctrl1 || ctrl2 || ctrl3 || ctrl4){
          
      accion="AND b.accion='"+vista.getAccion().getSelectedItem().toString().toUpperCase()+"' ";
      
      }else{
          
      accion="AND b.accion='"+vista.getAccion().getSelectedItem().toString().toUpperCase()+"' ";
      
      }   
      }
      
      if(ctrl1 || ctrl2  || ctrl3 || ctrl4 || ctrl5){

      String tira_SQL="SELECT u.login,b.fecha,b.accion,b.hora,b.sentencia,b.descripcion\n" +
                                "FROM bitacora as b INNER JOIN empleado as e ON e.id_usuario=b.id_empleado INNER JOIN usuario as u ON u.id=e.id_usuario INNER JOIN persona as per ON per.id_persona=u.id_persona\n"+persona+formulario+sqlFecha+accion+
                                "ORDER BY b.id DESC";
      System.out.println(tira_SQL);
      
      modelo.tabla_bitacora(tira_SQL);
      
      vista.getTabla_bitacora().setModel(modelo.tabla_bitacora(tira_SQL));
      
       }else{
          
       String tira_SQL=         "SELECT u.login,b.fecha,b.accion,b.hora,b.sentencia,b.descripcion\n" +
                                "FROM bitacora as b INNER JOIN empleado as e ON e.id_usuario=b.id_empleado INNER JOIN usuario as u ON u.id=e.id_usuario INNER JOIN persona as per ON per.id_persona=u.id_persona ORDER BY b.id DESC";
       
       System.out.println(tira_SQL);
       
       modelo.tabla_bitacora(tira_SQL);
       
       vista.getTabla_bitacora().setModel(modelo.tabla_bitacora(tira_SQL));
       
       }
           
       vista.getTabla_bitacora().getColumnModel().getColumn(0).setPreferredWidth(200);
       vista.getTabla_bitacora().getColumnModel().getColumn(1).setPreferredWidth(150);
       vista.getTabla_bitacora().getColumnModel().getColumn(2).setPreferredWidth(150);
       vista.getTabla_bitacora().getColumnModel().getColumn(3).setPreferredWidth(200);
       vista.getTabla_bitacora().getColumnModel().getColumn(4).setPreferredWidth(150);
       vista.getTabla_bitacora().getColumnModel().getColumn(5).setPreferredWidth(1200);

      
    }
   
    public void limpiar(){
        
    Bitacora.getPersona().setText("");
    vista.getFormulario().setSelectedIndex(0);
    vista.getAccion().setSelectedIndex(0);
    vista.getFecha_Hasta().setDate(null);
    vista.getFecha_Desde().setDate(null);
    llenar_tabla();
    
    }
}

   