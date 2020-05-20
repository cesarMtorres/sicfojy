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
import modelo.ModCatedra;
import modelo.ModeloBitacora;
//import net.sf.jasperreports.engine.JRException;
import vista.CatCatedra;
import vista.Catedra;
import vista.Principal;

/**
 *
 * @author cesar
 */
public class ctrCatedra extends AbstractAction implements ActionListener {
    public Catedra vista;
   private int acc;

    //modelo datos pesonales
    public ModCatedra modelo;
    private CatCatedra vcatCatedra;
    ModeloBitacora bitacora;

    public ctrCatedra(Catedra vista) {
        this.vista = vista;
        bitacora= new ModeloBitacora();
    }
@Override
     public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==vista.getBtnRegistrar()){
            Registrar();
            System.out.println("Botón incluir...");
        }else if(evento.getSource()==vista.getBtnModificar()){
            Modificar();
            System.out.println("Botón Modificar...");
        }else if(evento.getSource()==vista.getBtnEliminar()){
            Eliminar();
            System.out.println("Botón Eliminar...");
        }else if(evento.getSource()==vista.getBtnCancelar()){
            Cancelar();
            System.out.println("Botón cancelar...");
        }else if(evento.getSource()==vista.getBtnCatalago()){
            System.out.println("Botón catalago...");
                String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatCatedra = new CatCatedra(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatCatedra.setVisible(true);

            if(vcatCatedra.tabCatalago.isRowSelected(vcatCatedra.tabCatalago.getSelectedRow()) ){
                codigobus = vcatCatedra.tabCatalago.getValueAt(vcatCatedra.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatCatedra.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModCatedra();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.nombre.setText(String.valueOf(vcatCatedra.tabCatalago.getValueAt(acc, 0)));
                    vista.descrip.setText(String.valueOf(vcatCatedra.tabCatalago.getValueAt(acc, 1)));
                    vista.getId().setText(modelo.getId());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);
                    vista.btnRegistrar.setEnabled(false);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Catedra" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
    }
     
    public void Consultar(){
        modelo = new ModCatedra();
            boolean encontrado = modelo.Consultar(vista.getNombre().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                vista.getDescrip().setText(modelo.getDescrip());
                //Acción de los botones y cajas de texto
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnEliminar().setEnabled(true);
                vista.getBtnRegistrar().setEnabled(false);
                
            }else{
                vista.getBtnRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
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
        modelo = new ModCatedra(nombre,descrip);
        boolean incluido=modelo.RegistrarCatedra();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO CATEDRA "+nombre+" "+descrip+"";
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
        
        modelo = new ModCatedra(id,nombre,descrip);
        boolean modificado=modelo.ModificarCatedra(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO CATEDRA "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
        String id = vista.getId().getText();
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        modelo = new ModCatedra();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINAR CATEDRA "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
    }//fin de codigoEliminar
         public void Cancelar(){
        int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        }
     }
     public void limpiar() {
        vista.nombre.setText("");
        vista.descrip.setText("");
    }
         public void habilitar(){
        vista.getNombre().setEnabled(false);
        vista.getDescrip().setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.descrip.setEnabled(activ);

    }
        

}