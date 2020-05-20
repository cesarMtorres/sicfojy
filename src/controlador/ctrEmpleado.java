
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModEmpleado;
import vista.CatEmpleado;
import vista.Empleado;
import modelo.ModCargo;
import modelo.ModeloBitacora;
import vista.Principal;

/**
 *
 * @author betty
 */
public class ctrEmpleado extends AbstractAction implements ActionListener {
    public Empleado vista;
    ModeloBitacora bitacora;

    //modelo datos pesonales
    public ModEmpleado modelo;
    ModCargo mcargo=new ModCargo();
    private CatEmpleado vcatEmpleado;
    private int acc;

    public ctrEmpleado(Empleado vista) {
        this.vista = vista;
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
        }else if(evento.getSource()==vista.getBtnCatalago()){
            System.out.println("Botón catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatEmpleado = new CatEmpleado(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatEmpleado.setVisible(true);

            if(vcatEmpleado.tabCatalago.isRowSelected(vcatEmpleado.tabCatalago.getSelectedRow()) ){
                codigobus = vcatEmpleado.tabCatalago.getValueAt(vcatEmpleado.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatEmpleado.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModEmpleado();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.cedula.setText(modelo.getCedula());
                    vista.nombre.setText(modelo.getNombre());
                    vista.apellido.setText(modelo.getApellido());
                    vista.direccion.setText(modelo.getDireccion());
                    vista.telefono.setText(modelo.getTelefono());
                    
                    vista.cargo.setSelectedItem(modelo.getCargo());
                    vista.fechaNac.setDate(modelo.getFechaNac());
                    vista.sexo.setSelectedItem(modelo.getSexo());
                    vista.getId().setText(modelo.getId());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Empleado" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        }
        
        
    }
     
    public void Consultar(){
        modelo = new ModEmpleado();
            boolean encontrado = modelo.Consultar(vista.getCedula().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                vista.getCedula().setText(modelo.getCedula());
                vista.getApellido().setText(modelo.getApellido());
                vista.getTelefono().setText(modelo.getTelefono());
                vista.getDireccion().setText(modelo.getDireccion());
                vista.getCargo().setSelectedItem(modelo.getCargo());
                
                vista.getSexo().setSelectedItem(modelo.getSexo());
                vista.getFechaNac().setDate(modelo.getFechaNac());
                //Acción de los botones y cajas de texto
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnEliminar().setEnabled(true);
                vista.getBtnRegistrar().setEnabled(true);
                
            }else{
                vista.getBtnRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
            habilitar();
    }
    public void Registrar(){
        if("".equals(vista.cedula.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo cedula no puede estar vacio");
        } else if("".equals(vista.nombre.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.apellido.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo apellido no puede estar vacio");
        } else if("".equals(vista.fechaNac.getDate()==null)) {
            JOptionPane.showMessageDialog(vista, "El campo fecha de nacimiento no puede estar vacio");
        } else if("".equals(vista.direccion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo direccion no puede estar vacio");
        }else if("".equals(vista.telefono.getText())) {
           JOptionPane.showMessageDialog(vista, "El campo telefono no puede estar vacio");
        } else if("".equals(vista.cargo.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo RIF no puede estar vacio");
        }
         else {
            
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        String cargo =(String) vista.getCargo().getSelectedItem();
        modelo = new ModEmpleado(cedula, nombre,apellido,telefono,direccion,sexo,cargo,fechaNac);
        boolean incluido=modelo.RegistrarEmpleado();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL EMPLEADO "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
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
            JOptionPane.showMessageDialog(vista, "El campo direccion no puede estar vacio");
        }else if("".equals(vista.telefono.getText())) {
           JOptionPane.showMessageDialog(vista, "El campo telefono no puede estar vacio");
        } else if("".equals(vista.cargo.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo cargo no puede estar vacio");
        }
         else {

        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String id = vista.getId().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        String cargo =(String) vista.getCargo().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        modelo = new ModEmpleado(id,cedula, nombre,apellido,telefono,direccion,sexo,cargo,fechaNac);
        boolean modificado=modelo.ModificarEmpleado(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL EMPLEADO "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String id = vista.getId().getText();
        modelo = new ModEmpleado();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitos");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMIMO EL EMPLEADO "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        limpiar();  
        
    }//fin de codigoEliminar
         public void Cancelar(){
         int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        }
     }
     public void limpiar() {
        vista.cargo.setSelectedItem("V");
        vista.cedula.setText("");
        vista.nombre.setText("");
        vista.apellido.setText("");
        vista.direccion.setText("");
        vista.telefono.setText("");
        vista.id.setText("");
        vista.sexo.setSelectedItem("V");
        vista.fechaNac.setDateFormatString("");
    }
         public void habilitar(){
        vista.getCedula().setEnabled(false);
        vista.cargo.setEnabled(true);
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
        vista.cargo.setEnabled(activ);
        vista.sexo.setEnabled(activ);
    }

     public void LlenarCargo() {
        vista.getCargo().removeAllItems();
        ArrayList<String> datos_1 = mcargo.CargarCargo();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getCargo().addItem(cargoList);
        }
    }
}
