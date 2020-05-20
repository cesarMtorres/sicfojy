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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModPerfil;
import modelo.ModeloBitacora;
import vista.CatPerfil;
import vista.CatPermiso;
import vista.Perfil;
import vista.Principal;

/**
 *
 * @author usuario
 */
public class ctrPerfil extends AbstractAction implements ActionListener {
    //vista datos pesonales
    ModeloBitacora bitacora;
    String cperUsu = "";
    String dperUsu = "";
    char estaPer = 'A';
    private int acc;
    public Perfil vpperfil;
    //modelo datos pesonales
    public ModPerfil modeloperf;
    String varclase = "Perfil";
    private CatPerfil vcatPer;
    private CatPermiso vcatPermiso;
    ModPerfil modelo;
    public static String codperf = "";
    public static String codop="";

    public ctrPerfil(Perfil vpperfil) {
        this.vpperfil = vpperfil;
        bitacora=new ModeloBitacora();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Inicio de consultar

        if (e.getSource() == vpperfil.btnCancelar) {
int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vpperfil.dispose();
        }
        }

        if (e.getSource() == vpperfil.btnCatalago) {
                String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatPer= new CatPerfil(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatPer.setVisible(true);

            if(vcatPer.tabCatalago.isRowSelected(vcatPer.tabCatalago.getSelectedRow()) ){
                codigobus = vcatPer.tabCatalago.getValueAt(vcatPer.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatPer.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModPerfil();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vpperfil.id.setText(String.valueOf(vcatPer.tabCatalago.getValueAt(acc, 0)));
                    vpperfil.nombre.setText(String.valueOf(vcatPer.tabCatalago.getValueAt(acc, 0)));
                    vpperfil.descrip.setText(String.valueOf(vcatPer.tabCatalago.getValueAt(acc, 1)));
                    
                   // vpperfil.getId().setText(modelo.getId());
                    vpperfil.btnModificar.setEnabled(true);
                    vpperfil.btnEliminar.setEnabled(true);
                    vpperfil.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Perfil" + " no encontrado ", ".",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }//fin de consultar

        //Inicio de incluir
        if (e.getSource() == vpperfil.btnRegistrar) {
        if("".equals(vpperfil.nombre.getText())) {
            JOptionPane.showMessageDialog(vpperfil, "El campo nombre no puede estar vacio");
        } else if("".equals(vpperfil.descrip.getText().trim())) {
            JOptionPane.showMessageDialog(vpperfil, "El campo descripción no puede estar vacio");
        }
         else {            
        asignaval();
        modelo = new ModPerfil(cperUsu, dperUsu);
        boolean incluido=modelo.RegistrarPerfil();
        if (incluido){
            JOptionPane.showMessageDialog(vpperfil, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL PERFIL"+cperUsu+" "+dperUsu+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        limpiar();
        }
     //   codigoBotonLimpiar();
        }//fin de incluir*/

        //Inicio de Modificar
        if (e.getSource() == vpperfil.btnModificar) {
            //System.out.print(vpperfil.cmdedit.getText());
        if("".equals(vpperfil.nombre.getText())) {
            JOptionPane.showMessageDialog(vpperfil, "El campo nombre no puede estar vacio");
        } else if("".equals(vpperfil.descrip.getText().trim())) {
            JOptionPane.showMessageDialog(vpperfil, "El campo descripción no puede estar vacio");
        }
         else {
        String id = vpperfil.getId().getText();;
        String nombre = vpperfil.getNombre().getText();
        String descrip = vpperfil.getDescrip().getText();;
        modelo = new ModPerfil(id,nombre,descrip);
        boolean modificado=modelo.ModificarPerfil(id);
        if (modificado){
            JOptionPane.showMessageDialog(vpperfil, "Modificacion Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL PERFIL "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        }
            
        }
        //fin de moodificar

        //Inicio de eliminar
        if (e.getSource() == vpperfil.btnEliminar) {
     int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
        String id = vpperfil.getId().getText();
        String nombre = vpperfil.getNombre().getText();
        String descrip =(String) vpperfil.getDescrip().getText();
        modelo = new ModPerfil();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vpperfil, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL Perfil "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        limpiar(); 
        }
        }
        //Fin de eliminar
        
        //Inicio de Permiso
        if (e.getSource() == vpperfil.btnPermiso) {
            codperf = vpperfil.id.getText();
            //modeloperf.setId(vpperfil.id.getText());
            
            try {
                vcatPermiso = new CatPermiso(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
            //vp.setVisible(true);
            vcatPermiso.setVisible(true);
            
            if (CatPermiso.salida == 1)
                if (modeloperf.asignarOpcionPerfil(codperf,vcatPermiso.ptabPermiso))
                    JOptionPane.showMessageDialog(new JFrame(), "Permisos Asignados Exitosamente ",
                        ".", JOptionPane.INFORMATION_MESSAGE);
        }
        //fin de cancelar

    }//fin del action performed

    public void asignaval() {
        cperUsu = vpperfil.id.getText();
        dperUsu = vpperfil.descrip.getText();
    }

    public void actdesact(boolean activ) {
        vpperfil.nombre.setEnabled(activ);
    }

    public void limpiar() {
        vpperfil.descrip.setText("");
        vpperfil.nombre.setText("");
    }
}//fin de la clase

