/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import modelo.ModPerfil;
import modelo.ModeloSesion;
import vista.FondoSistema;
import vista.sesion;

/**
 *
 * @author rover
 */
public class CtrlClave extends AbstractAction implements ActionListener {
    public sesion permisoacceso;
    public ModeloSesion mdusucl = new ModeloSesion();
    private boolean sw;
    
    public CtrlClave(sesion permisoacceso) {
        this.permisoacceso = permisoacceso;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == permisoacceso.cancelar) {
            permisoacceso.acceso = false;
            permisoacceso.setVisible(false);
        }
        if (e.getSource() == permisoacceso.ingreso) {
            if (permisoacceso.usuario.getText().length() > 0 && permisoacceso.contraseña.getText().length() > 0) {
                    if (FondoSistema.mmaestra.equals(permisoacceso.usuario.getText())){
                        permisoacceso.acceso = true;
                        permisoacceso.setVisible(false);
                    } else {
                        permisoacceso.acceso = mdusucl.ConsultarUsuario(permisoacceso.usuario.getText(), permisoacceso.contraseña.getText());
                        if (permisoacceso.acceso) {
                            String clperfil = "";
                            clperfil = mdusucl.getPerfil();
                            ModPerfil tmodpercl = new ModPerfil();
                            //System.out.println(clperfil + VistaClave.opcperf);
                            sw = tmodpercl.buscarPerfilxOpcion(clperfil, permisoacceso.opcperf);
                            if (sw) {
                                permisoacceso.acceso = true;
                                permisoacceso.setVisible(false);
                            } else {
                                permisoacceso.acceso = false;
                                JOptionPane.showMessageDialog(permisoacceso, "No Tiene Acceso a esta Opción.\n" +"Vuelva a Intentarlo","SGCA",JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(permisoacceso, "Usuario y/o Contraseña erróneas.\n" +"Vuelva a Intentarlo","SGCA",JOptionPane.ERROR_MESSAGE);
                        }
                    } 
               
            } else {
                permisoacceso.acceso = false;
                JOptionPane.showMessageDialog(permisoacceso, "Debe escribir nombre de Usuario y Contraseña.\n" +"¡Obligatorio!");
            }
        }
    }

}
