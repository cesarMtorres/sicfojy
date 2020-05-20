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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModRepresentante;
//import net.sf.jasperreports.engine.JRException;
import vista.CatREstudiante;
import vista.Representante;
import modelo.ModeloBitacora;
import vista.CatRepresentante;
import vista.Principal;

/**
 *
 * @author cesar
 */
public class ctrRepresentante extends AbstractAction implements ActionListener {
    public Representante vista;
    Validacion validacion;
    //modelo datos pesonales
    public ModRepresentante modelo;
    ModeloBitacora bitacora;
    private CatREstudiante vcatEstudiante;
    private CatRepresentante vcatRepresentante;
    private int acc;

    public ctrRepresentante(Representante vista) {
        this.vista = vista;
        modelo = new ModRepresentante(vista);
        bitacora=new ModeloBitacora();
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
        }
   /*     else if(evento.getSource()==vista.getBtnEstudiante()){
             System.out.println(" catalago Estudiante...");
                //System.out.println("algoCargo");
              try {
                vcatEstudiante = new CatREstudiante(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrRepresentante.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatEstudiante.setVisible(true);

        } */
        else if(evento.getSource()==vista.getBtnCatalago()){
              System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatRepresentante = new CatRepresentante(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrRepresentante.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatRepresentante.setVisible(true);

            if(vcatRepresentante.tabCatalago.isRowSelected(vcatRepresentante.tabCatalago.getSelectedRow()) ){
                codigobus = vcatRepresentante.tabCatalago.getValueAt(vcatRepresentante.tabCatalago.getSelectedRow(),3).toString(); // obtiene el nombre
                acc =vcatRepresentante.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModRepresentante();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                vista.getNombre().setText(modelo.getNombre());
                vista.getCedula().setText(modelo.getCedula());
                vista.getApellido().setText(modelo.getApellido());
                vista.getTelefono().setText(modelo.getTelefono());
                vista.getDireccion().setText(modelo.getDireccion());
                vista.getSexo().setSelectedItem(modelo.getSexo());
                vista.getFechaNac().setDate(modelo.getFechaNac());
                vista.getId().setText(modelo.getId());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Representante" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        }
        
        
    }
     
 
    public void Registrar(){
        if("".equals(vista.cedula.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo cedula no puede estar vacio");
        } else if("".equals(vista.nombre.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.apellido.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo apellido no puede estar vacio");
        } else if(vista.fechaNac.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha de nacimiento no puede estar vacio");
        } else if("".equals(vista.direccion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo dirección no puede estar vacio");
        }else if("".equals(vista.telefono.getText())) {
           JOptionPane.showMessageDialog(vista, "El campo telefono no puede estar vacio");
        }
         else {
        
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        modelo = new ModRepresentante(cedula, nombre,apellido,telefono,direccion,sexo,fechaNac);
        boolean incluido=modelo.RegistrarRepresentante();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL REPRESENTANTE "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
    
 public void Modificar(){
        if("".equals(vista.cedula.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo cedula no puede estar vacio");
        } else if("".equals(vista.nombre.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.apellido.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo apellido no puede estar vacio");
        } else if(vista.fechaNac.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha de nacimiento no puede estar vacio");
        } else if("".equals(vista.direccion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo dirección no puede estar vacio");
        }else if("".equals(vista.telefono.getText())) {
           JOptionPane.showMessageDialog(vista, "El campo telefono no puede estar vacio");
        }
         else {

        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
       // String catedra =(String) vista.getCatedra().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        modelo = new ModRepresentante(cedula, nombre,apellido,telefono,direccion,sexo,fechaNac);
        boolean modificado=modelo.ModificarRepresentante(cedula);
       if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificado Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL REPRESENTANTE "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
     int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String id = vista.getId().getText();
        
        modelo = new ModRepresentante();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL REPRESENTANTE "+nombre+" "+cedula+"";
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
        vista.cedula.setText("");
        vista.nombre.setText("");
        vista.apellido.setText("");
        vista.direccion.setText("");
        vista.telefono.setText("");
        vista.sexo.setSelectedItem("V");
        vista.fechaNac.setDateFormatString("");
    }
         public void habilitar(){
        vista.getCedula().setEnabled(false);
        
        vista.cedula.setEnabled(true);
        vista.nombre.setEnabled(true);
        vista.apellido.setEnabled(true);
        vista.direccion.setEnabled(true);
        vista.telefono.setEnabled(true);
        vista.sexo.setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.apellido.setEnabled(activ);
        vista.telefono.setEnabled(activ);
        vista.direccion.setEnabled(activ);
        
        vista.sexo.setEnabled(activ);
    }
        

}