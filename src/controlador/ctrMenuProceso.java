/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.Asignar_bien;
import vista.Desincorporar_bien;
import vista.FondoSistema;
import vista.Prestamo;
import vista.paneles.pnlCollection;

/**
 *
 * @author cesar
 */
public class ctrMenuProceso extends AbstractAction implements ActionListener {
    public pnlCollection vista;
    public Desincorporar_bien desin;
    public Prestamo pres;
    public Asignar_bien asig;
 
    private int acc;
    //modelo datos pesonales

    public ctrMenuProceso(pnlCollection vista) {
        this.vista = vista;
    }

    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.cuatro){
            if (FondoSistema.accesar("ASIGNAR BIEN", "16")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    asig = new Asignar_bien(new javax.swing.JFrame(), true);
                                
                    asig.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.cuatro.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }if(evento.getSource()==vista.cuatro1){
            if (FondoSistema.accesar("DESINCORPORAR BIEN", "20")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    desin = new Desincorporar_bien(new javax.swing.JFrame(), true);
                                
                    desin.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.cuatro1.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }if(evento.getSource()==vista.cuatro2){
            if (FondoSistema.accesar("PRESTAMO BIEN", "21")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    pres = new Prestamo(new javax.swing.JFrame(), true);
                                
                    pres.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.cuatro2.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }
        
    }
}
