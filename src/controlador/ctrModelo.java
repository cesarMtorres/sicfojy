/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModModelo;
import modelo.ModeloBitacora;
import modelo.ModMarca;
import vista.Modelo;
import vista.CatModelo;
import vista.Principal;

/**
 *
 * @author betty
 */
public class ctrModelo extends AbstractAction implements ActionListener {
    
    ModeloBitacora bitacora;
    public Modelo vista;
    private int acc;

    //modelo datos pesonales
    public ModModelo modelo;
    private CatModelo vcatModelo;
    ModMarca mmarca=new ModMarca();
    public ctrModelo(Modelo vista) {
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
            System.out.println("Botón catalago...");
                         String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatModelo= new CatModelo(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatModelo.setVisible(true);

            if(vcatModelo.tabCatalago.isRowSelected(vcatModelo.tabCatalago.getSelectedRow()) ){
                codigobus = vcatModelo.tabCatalago.getValueAt(vcatModelo.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatModelo.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModModelo();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.nombre.setText(modelo.getNombre());
                    vista.marca.setSelectedItem(modelo.getMarca());
                    vista.getId().setText(modelo.getId());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Modelo" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
    }
     
    public void Consultar(){
        modelo = new ModModelo();
            boolean encontrado = modelo.Consultar(vista.getNombre().getText());
            if (encontrado){
                vista.getId().setText(modelo.getId());
                vista.getNombre().setText(modelo.getNombre());
                vista.getMarca().setSelectedItem(modelo.getMarca());
                
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
        } else if("".equals(vista.marca.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo marca no puede estar vacio");
        }
         else {        
        String nombre = vista.getNombre().getText();
        String marca = vista.getId_marca().getText();
        
        modelo = new ModModelo(nombre,marca);
        boolean incluido=modelo.RegistrarModelo();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL MODELO "+nombre+" "+marca+"";
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
        } else if("".equals(vista.marca.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo marca  no puede estar vacio");
        }
         else {

        String nombre = vista.getNombre().getText();
        String marca =(String) vista.getMarca().getSelectedItem();
        String id = vista.getId().getText();;
        modelo = new ModModelo(id,nombre,marca);
        boolean modificado=modelo.ModificarModelo(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL MODELO "+nombre+" "+marca+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        limpiar();
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
     int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
        String id = vista.getId().getText();
        String nombre = vista.getNombre().getText();
        String marca =(String) vista.getMarca().getSelectedItem();
        modelo = new ModModelo();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitos");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL MODELO "+nombre+" "+marca+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
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
        vista.marca.setSelectedItem("");
        vista.id.setText("");
    }
         public void habilitar(){
        vista.getNombre().setEnabled(false);
        vista.getMarca().setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.marca.setEnabled(activ);


    }
        public void LlenarMarca() {
        vista.getMarca().removeAllItems();
        ArrayList<String> datos_1 = mmarca.CargarMarca();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getMarca().addItem(cargoList);
        }
    }
    
}
