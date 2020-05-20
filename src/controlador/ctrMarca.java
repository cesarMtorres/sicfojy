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
import modelo.ModMarca;
import modelo.ModUsuario;
import vista.Marca;
import vista.CatMarca;
import modelo.ModeloBitacora;
import vista.Principal;

/**
 *
 * @author betty
 */
public class ctrMarca extends AbstractAction implements ActionListener {
    
     public Marca vista;
     ModeloBitacora bitacora;
    //modelo datos pesonales
    public ModMarca modelo;
    public ModUsuario musuario;
    private CatMarca vcatMarca;
    private int acc;
    public ctrMarca(Marca vista) {
        this.vista = vista;
        bitacora=new ModeloBitacora();
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
        }else if(evento.getSource()==vista.getBtnEliminar()){
            Eliminar();
            System.out.println("Botón Eliminar...");
        }
        
        else if(evento.getSource()==vista.getBtnCancelar()){
            Cancelar();
            System.out.println("Botón cancelar...");
        }else if(evento.getSource()==vista.getBtnCatalago()){
            System.out.println("Botón catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatMarca= new CatMarca(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatMarca.setVisible(true);

            if(vcatMarca.tabCatalago.isRowSelected(vcatMarca.tabCatalago.getSelectedRow()) ){
                codigobus = vcatMarca.tabCatalago.getValueAt(vcatMarca.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatMarca.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModMarca();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.nombre.setText(String.valueOf(vcatMarca.tabCatalago.getValueAt(acc, 0)));
                    vista.descrip.setText(String.valueOf(vcatMarca.tabCatalago.getValueAt(acc, 1)));
                    vista.getId().setText(modelo.getId());
                    vista.btnModificar.setEnabled(true);
                 //   vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Marca" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }     
        }
        
        
    }
     
    public void Consultar(){
        modelo = new ModMarca();
            boolean encontrado = modelo.Consultar(vista.getNombre().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                vista.getDescrip().setText(modelo.getDescrip());
                //Acción de los botones y cajas de texto
                vista.getBtnModificar().setEnabled(true);
           //     vista.getBtnEliminar().setEnabled(true);
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
        
        modelo = new ModMarca(nombre,descrip);
        boolean incluido=modelo.RegistrarMarca();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO LA MARCA "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
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
         else {
        String id = vista.getId().getText();;
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();;
        modelo = new ModMarca(id,nombre,descrip);
        boolean modificado=modelo.ModificarMarca(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificacion Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL CARGO "+nombre+" "+descrip+"";
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
        String id = vista.getId().getText();;
        modelo = new ModMarca();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);

            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINO LA MARCA "+nombre+" "+id+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no eliminado","Eliminar",JOptionPane.ERROR_MESSAGE);
        }
        }
    }//fin de codigoEliminar
 
 
         public void Inhabilitar() throws SQLException {
        System.out.println("BOTON INHABILITAR");
        
         String cargo = vista.id.getText(); 
            modelo =new ModMarca();
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
         modelo =new ModMarca();
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
    }  
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.descrip.setEnabled(activ);

    }
    
}
