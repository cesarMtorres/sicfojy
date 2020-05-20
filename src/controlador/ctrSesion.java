

package controlador;

import vista.sesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModeloSesion;
import vista.FondoSistema;
import vista.Principal;


public class ctrSesion implements ActionListener {
    
    sesion vista;
    int acum=0;

    public ctrSesion(sesion vista) {
        this.vista = vista;
    }
    
    public ctrSesion() {    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource()== vista.ingreso){
            
             if(vista.getUsuario().getText().length()==0){
            
                JOptionPane.showMessageDialog(new JFrame(), "DEBE RELLENAR TODOS LOS CAMPOS", "Advertencia",JOptionPane.WARNING_MESSAGE);
                return;
                
            }
            
                if(vista.getContraseña().getText().length()==0){
            
                JOptionPane.showMessageDialog(new JFrame(), "DEBE RELLENAR TODOS LOS CAMPOS", "Advertencia",JOptionPane.WARNING_MESSAGE);
                return;
                
            }
                
            ModeloSesion modelo = new ModeloSesion();
            boolean validar = modelo.ConsultarUsuario(vista.getContraseña().getText(), vista.getUsuario().getText());
            
            if(validar){
            Principal.setCodigo_personalLocal(modelo.getCod_empleado());
            Principal.setPerfilLocal(modelo.getPerfil());
            Principal.setUsuarioLocal(modelo.getUsuario());
            FondoSistema.setMperfil(modelo.getPerfil());
            System.out.println("usuario-perfil:"+modelo.getPerfil()+modelo.getUsuario());
            boolean a=modelo.actualizarStatus(modelo.getCod_empleado(),"TRUE");
                vista.activar();
            }else{
                JOptionPane.showMessageDialog(new JFrame(),"Usuario ó Contraseña invalidos.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                acum++;
                
                if (acum==3){
                    
                    JOptionPane.showMessageDialog(new JFrame(),"le queda 1 intento. El usuario será bloqueado","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                }if (acum>=4){
                    
                    modelo.bloquearUsuario(vista.getUsuario().getText());
                    JOptionPane.showMessageDialog(new JFrame(),"Usuario bloqueado. por favor comuniquese con el Administrador","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        }
       
    }

            
        }
