/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModCargo;
import modelo.ModPerfil;
import modelo.ModUsuario;
//import net.sf.jasperreports.engine.JRException;
import vista.CatPerfil;
import vista.CatUsuario;
import vista.Usuario;
import modelo.ModeloBitacora;
import vista.Principal;

/**
 *
 * @author usuario
 */
public class ctrUsuario extends AbstractAction implements ActionListener {
    //vista datos pesonales

    String cedUsu = "";
    String nomUsu = "";
    String apeUsu = "";
    String telUsu = "";
    String dirUsu = "";
    String fecIUsu = "";
    String emaUsu = "";
    String codUsu = "";
    String logUsu = "";
    String claUsu = "";
    String perUsu = "";
    String nacion = "";
    String login ="";
    ModeloBitacora bitacora;
    public Usuario vista;
    //modelo datos pesonales
    public ModUsuario modelo;
    public ModCargo modeloca;
    public ModPerfil modelop;
    private int acc;
    private CatPerfil vcatPer;
    private CatUsuario vcatUsuario;

    public ctrUsuario(Usuario vista) {
        this.vista = vista;
        bitacora=new ModeloBitacora();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Inicio de consultar

        if (e.getSource() == vista.btnCancelar) {
         int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        }
        }
        
        if (e.getSource()==vista.btnCatalago){
        
                  System.out.println("Botón catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatUsuario = new CatUsuario(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatUsuario.setVisible(true);

            if(vcatUsuario.tabCatalago.isRowSelected(vcatUsuario.tabCatalago.getSelectedRow()) ){
                codigobus = vcatUsuario.tabCatalago.getValueAt(vcatUsuario.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatUsuario.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModUsuario();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.cedula.setText(modelo.getCedula());
                    vista.nombre.setText(modelo.getNombre());
                    vista.apellido.setText(modelo.getApellido());
                    vista.direccion.setText(modelo.getDireccion());
                    vista.telefono.setText(modelo.getTelefono());
                    vista.login.setText(modelo.getLogin());
                    vista.cargo.setSelectedItem(modelo.getCargo());
                    vista.getFechaNac().setDate(modelo.getFechaNac());
                    vista.sexo.setSelectedItem(modelo.getSexo());
                    vista.clave.setText(modelo.getClave());
                    vista.perfil.setText(modelo.getPerfil());
                    vista.id.setText(modelo.getId());
                    
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Estudiante" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        //Asigna Valor de Perfil
        if (e.getSource() == vista.btnPerfil) {
            //System.out.println("algoperfil");
            String codigobus = "";
            try {
                vcatPer = new CatPerfil(new javax.swing.JFrame(), true);
            } catch (    IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
            //vp.setVisible(true);
            vcatPer.setVisible(true);
            if(vcatPer.tabCatalago.isRowSelected(vcatPer.tabCatalago.getSelectedRow()) ){
                codigobus = vcatPer.tabCatalago.getValueAt(vcatPer.tabCatalago.getSelectedRow(),0).toString(); // obtiene el id_perfil 
                acc =vcatPer.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus perfil --: " + codigobus);
             modelop =new ModPerfil();
                    if (codigobus != null) {
                
                boolean existe = modelop.Consultar(codigobus);
                if (existe) {
                    vista.perfil.setText(modelop.getNombre());                    
                    vista.idperfil.setText(modelop.getId());   
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);
                    vista.btnRegistrar.setEnabled(true);
                    
                    //vista.cedula.setEditable(false);
                    vista.nacion.setEnabled(false);
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Usuario " + " no encontrado ", ".",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        
        
        }


        //Inicio de incluir
        if (e.getSource() == vista.btnRegistrar) {
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String id = vista.getId().getText();
        String direccion = vista.getDireccion().getText();
        String clave = vista.getClave().getText();
        String perfil = vista.getIdperfil().getText();
        String login= vista.getLogin().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        String cargo =(String) vista.getCargo().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        String nacion =(String) vista.getNacion().getSelectedItem();
         
        //int edad = Integer.parseInt(vista.getTxtEdad().getText());
        modelo = new ModUsuario(cedula,login, clave,perfil,nombre, apellido,telefono,direccion,sexo, cargo,fechaNac,nacion);
        boolean incluido=modelo.Registrar();
       if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL USUARIO "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
     //  
     
     
        }//fin de incluir*/

        //Inicio de Modificar
        if (e.getSource() == vista.btnModificar) {
            //System.out.print(vista.cmdedit.getText())
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String id = vista.getId().getText();
        String direccion = vista.getDireccion().getText();
        String clave = vista.getClave().getText();
        String perfil = vista.getIdperfil().getText();
        String login= vista.getLogin().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        String cargo =(String) vista.getCargo().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        String nacion =(String) vista.getNacion().getSelectedItem();
        modelo = new ModUsuario(id,cedula,login,clave,perfil,nombre,apellido,telefono,direccion, sexo,cargo,fechaNac,nacion);
        boolean modificado=modelo.ModificarEmpleado(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificaion Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICAR EL USUARIO "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
             
            //System.out.print(vista.vAddEdit);
        }
        //fin de moodificar

        //Inicio de eliminar
        if (e.getSource() == vista.btnEliminar) {
            int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
            modelo = new ModUsuario();
            String id = vista.getId().getText();
            String nombre = vista.getNombre().getText();
            String cedula = vista.getCedula().getText();
            boolean sw = modelo.Eliminar(id);
            if (sw){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL USUARIO "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }

        }
        //Fin de eliminar


        //fin de cancelar

    }//fin del action performed

    public void asignaval() {

      //  modelo.setNacion((String) vista.nacion.getSelectedItem());
    }

    public void actdesact(boolean activ) {


    }
            public void LlenarCargo() {
        vista.getCargo().removeAllItems();
        ArrayList<String> datos_1 = modeloca.CargarCargo();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getCargo().addItem(cargoList);
            
        }
        }

    public void limpiar() {
        vista.cedula.setText("");
        vista.nombre.setText("");
        vista.apellido.setText("");
        vista.telefono.setText("");
        vista.clave.setText("");
        vista.login.setText("");
        vista.perfil.setText("");
        vista.direccion.setText("");
        vista.sexo.setSelectedItem("");
        vista.cargo.setSelectedItem("");
        vista.fechaNac.setDateFormatString("");
    }
}//fin de la clase