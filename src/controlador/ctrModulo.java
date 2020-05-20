/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModModulo;
import vista.Modulo;
import vista.CatModulo;
import modelo.ModeloBitacora;
import vista.Principal;


/**
 *
 * @author betty
 */
public class ctrModulo extends AbstractAction implements ActionListener {
    
    public Modulo vista;
    private int acc;
    ModeloBitacora bitacora;
    //modelo datos pesonales
    public ModModulo modelo;
    private CatModulo vcatModulo;

    public ctrModulo(Modulo vista) {
        this.vista = vista;
        bitacora=new ModeloBitacora();
    }

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
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatModulo = new CatModulo(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ctrModulo.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatModulo.setVisible(true);

            if(vcatModulo.tabCatalago.isRowSelected(vcatModulo.tabCatalago.getSelectedRow()) ){
                codigobus = vcatModulo.tabCatalago.getValueAt(vcatModulo.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatModulo.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModModulo();
           
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
                            "Modulo " + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
    }
     
    public void Consultar(){
        modelo = new ModModulo();
            boolean encontrado = modelo.Consultar(vista.getNombre().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                vista.getDescrip().setText(modelo.getDescrip());
                vista.getId().setText(modelo.getId());
                //Acción de los botones y cajas de texto
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnEliminar().setEnabled(true);
                vista.getBtnRegistrar().setEnabled(true);
                
                
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
         else {        
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        modelo = new ModModulo(nombre,descrip);
        boolean incluido=modelo.RegistrarModulo();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL MODULO "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        limpiar();
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
    
 public void Modificar(){
        if("".equals(vista.nombre.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.descrip.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo descripción no puede estar vacio");
        }
         else {

        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id = vista.getId().getText();
        modelo = new ModModulo(nombre,descrip);
        boolean modificado=modelo.ModificarModulo(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO LA MARCA "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
     int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id = vista.getId().getText();
        modelo = new ModModulo();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitos");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMIMO LA MARCA "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Eliminacion fallida error de base de datos","Eliminar",JOptionPane.ERROR_MESSAGE);
        }
        limpiar();
        }
        
    }//fin de codigoEliminar
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
         public void habilitar(){
        vista.getNombre().setEnabled(false);
        vista.getDescrip().setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.descrip.setEnabled(activ);

    }
    
    
}
