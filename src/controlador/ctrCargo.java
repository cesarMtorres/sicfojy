/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModCargo;
import modelo.ModeloBitacora;
import modelo.ModUsuario;
import net.sf.jasperreports.engine.JRException;
import vista.CatCargo;
import vista.Cargo;

import vista.Principal;

/**
 *
 * @author cesar
 */
public class ctrCargo extends AbstractAction implements ActionListener {
    public Cargo vista;
    private int acc;
    private ModeloBitacora bitacora;
    //modelo datos pesonales
    public ModCargo modelo;
    public ModUsuario musuario;
    private CatCargo vcatCargo;

    public ctrCargo(Cargo vista) {
        this.vista = vista;
        bitacora= new ModeloBitacora();
        this.musuario= new ModUsuario();
    }
    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getBtnRegistrar()){
            Registrar();
            System.out.println("Botón incluir...");
        }else if(evento.getSource()==vista.getBtnModificar()){
            Modificar();
            System.out.println("Botón Modificar...");
        }else if(evento.getSource()==vista.getBtnCancelar()){
            Cancelar();
            System.out.println("Botón cancelar...");
        }else if (evento.getSource() == vista.getBtnReporte()) {      
            String id=vista.id.getText();
             try {
                 modelo.imprimirBitacceso("");
             } catch (JRException ex) {
                 Logger.getLogger(ctrCargo.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(ctrCargo.class.getName()).log(Level.SEVERE, null, ex);
             }
            }else if(evento.getSource()==vista.btnEliminar) {
            String[] estado= {"INACTIVO","ACTIVO", "ELIMINAR"};
                       String resp = (String) JOptionPane.showInputDialog(vista, "SELECCIONE","SELECCIONE EL ESTADO DE LA MARCA", JOptionPane.INFORMATION_MESSAGE, null, estado,estado[0]);
                       
                       if(resp.equalsIgnoreCase("INACTIVO")){
                           try {
                               Inhabilitar();
                               vista.status.setText("INACTIVO");
                           } catch (SQLException ex) {
                               Logger.getLogger(ctrMarca.class.getName()).log(Level.SEVERE, null, ex);
                           }
                          
                       } else if(resp.equalsIgnoreCase("ACTIVO")){
                           try {
                               habilitar();
                               vista.status.setText("ACTIVO");
                           } catch (SQLException ex) {
                               Logger.getLogger(ctrMarca.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                       } else if (resp.equalsIgnoreCase("ELIMINAR")){
                           Eliminar();
                           vista.status.setText("DATO ELIMINADO FISICAMENTE"); 
                          
                       }
                
        }else if(evento.getSource()==vista.getBtnCatalago()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatCargo = new CatCargo(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatCargo.setVisible(true);

            if(vcatCargo.tabCatalago.isRowSelected(vcatCargo.tabCatalago.getSelectedRow()) ){
                codigobus = vcatCargo.tabCatalago.getValueAt(vcatCargo.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatCargo.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModCargo();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.nombre.setText(modelo.getNombre());
                    vista.descrip.setText(modelo.getDescrip());
                    vista.getId().setText(modelo.getId());

                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Cargo" + " no encontrado ", ".",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
    }
     
  
    
    
    
    public void Registrar(){
            if("".equals(vista.nombre.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.descrip.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo descripción no puede estar vacio");
        }
        else{
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        modelo = new ModCargo(nombre,descrip);
        boolean incluido=modelo.RegistrarCargo();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL CARGO "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
    
 public void Modificar(){
        if("".equals(vista.nombre.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.descrip.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo descripción no puede estar vacio");
        }
        else{

        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id = vista.getId().getText();
        modelo = new ModCargo(id,nombre,descrip);
        boolean modificado=modelo.ModificarCargo(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modifico Correctamente");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL CARGO "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
        }
    }//fin de codigoModificar
   
 public void Eliminar()
 { 
                  
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id = vista.getId().getText();
        modelo = new ModCargo();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Élimino Correctamente ");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINO EL CARGO "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no eliminado","Eliminar",JOptionPane.ERROR_MESSAGE);
        } 
          
 }   

         public void Inhabilitar() throws SQLException {
        System.out.println("BOTON INHABILITAR");
        
         String cargo = vista.id.getText(); 
            modelo =new ModCargo();
            boolean resultado= modelo.inhabilitar(cargo);
            if (resultado)
            {
             if(modelo.inhabilitar(cargo)){
              JOptionPane.showMessageDialog(vista,"INHABILITADO");
              vista.status.setText("INACTIVO");
              
           }else
              JOptionPane.showMessageDialog(vista,"ERROR: NO SE PUDO INHABILITAR");
            
             //Cancelar();
    }
         }
    
     public void habilitar() throws SQLException{
         modelo =new ModCargo();
        String estatus = vista.id.getText(); 
        boolean resultado= modelo.inhabilitar(estatus);
        
        if(modelo.Activar(estatus)){
          JOptionPane.showMessageDialog(vista,"HABILITADO");
           vista.status.setText("ACTIVO");
          
        }else
          JOptionPane.showMessageDialog(vista,"ERROR: NO PUDO SER ACTIVADO");
      //  Cancelar();
    }
           
            
         public void Cancelar(){
         int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        }
     }
     public void limpiar() {
        vista.nombre.setText("");
        vista.descrip.setText("");
        vista.id.setText("");
    }

         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.descrip.setEnabled(activ);

    }

        

}