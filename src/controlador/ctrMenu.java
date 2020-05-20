/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.Bien;
import vista.Cargo;
import vista.Catedra;
import vista.Empleado;
import vista.Estudiante;
import vista.Profesor;
import vista.FondoSistema;
import vista.Marca;
import vista.Modelo;
import vista.Modulo;
import vista.Perfil;
import vista.Programa;
import vista.Representante;
import vista.TipoBien;
import vista.TipoInstrumento;
import vista.TipoSiniestro;
import vista.Usuario;
import vista.paneles.pnlWeb;

/**
 *
 * @author cesar
 */
public class ctrMenu extends AbstractAction implements ActionListener {
    public pnlWeb vista;
    public Estudiante vdafi;
    public Profesor vpro;
    public Marca vmar;
    public Modelo vmod;
    public Programa vprog;
    public Representante vrep;
    public TipoSiniestro vtsi;
    public TipoInstrumento tins;
    public Modulo vmodu;
    public Catedra vcat;
    public Bien vbie;
    public TipoBien vtbi;
    public Usuario vusu;
    public Cargo vcar;
    public Empleado vemp;
    public Perfil perf;
    private int acc;
    //modelo datos pesonales

    public ctrMenu(pnlWeb vista) {
        this.vista = vista;

    }
	/*
	usuario = new Usuario(new javax.swing.JFrame(), true);
        usuario.setVisible(true);
	*/
    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.bt1){
            if (FondoSistema.accesar("ESTUDIANTE", "1")){
                    System.out.println("True");
                    
                    System.out.println("cambiar");
                // Llamada a la Pantalla VistaAfiliado
                    vdafi = new Estudiante(new javax.swing.JFrame(), true);
                                
                    vdafi.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.bt1.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.bt2){
            if (FondoSistema.accesar("PROFESOR", "6")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vpro = new Profesor(new javax.swing.JFrame(), true);
                                
                    vpro.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.bt2.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn3){
            if (FondoSistema.accesar("EMPLEADO", "11")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vemp = new Empleado(new javax.swing.JFrame(), true);
                                
                    vemp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn3.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn4){
            if (FondoSistema.accesar("REPRESENTANTE", "5")){
                    System.out.println("True");
                    vrep = new Representante(new javax.swing.JFrame(), true);
                                
                    vrep.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn4.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn5){
            if (FondoSistema.accesar("USUARIO", "2")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vusu = new Usuario(new javax.swing.JFrame(), true);
                                
                    vusu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn5.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn6){
            if (FondoSistema.accesar("MODELO", "12")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                     vmod = new Modelo(new javax.swing.JFrame(), true);
                  vmod.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn6.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn7){
            if (FondoSistema.accesar("MARCA", "13")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vmar = new Marca(new javax.swing.JFrame(), true);
                                
                    vmar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn7.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn8){
            if (FondoSistema.accesar("CATEDRA", "3")){
                    System.out.println("True");
                   vcat = new Catedra(new javax.swing.JFrame(), true);
                  vcat.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn8.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn9){
            if (FondoSistema.accesar("PROGRAMA", "10")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vprog = new Programa(new javax.swing.JFrame(), true);
                                
                    vprog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn9.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn10){
            if (FondoSistema.accesar("MODULO", "15")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vmodu = new Modulo(new javax.swing.JFrame(), true);
                                
                    vmodu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn10.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn11){
            if (FondoSistema.accesar("BIEN", "7")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vbie = new Bien(new javax.swing.JFrame(), true);
                                
                    vbie.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn11.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn12){
            if (FondoSistema.accesar("TIPO BIEN", "8")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vtbi = new TipoBien(new javax.swing.JFrame(), true);
                                
                    vtbi.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn12.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn13){
            if (FondoSistema.accesar("CARGO", "4")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vcar = new Cargo(new javax.swing.JFrame(), true);
                                
                    vcar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn13.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn14){
            if (FondoSistema.accesar("TIPO SINIESTRO", "9")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vtsi = new TipoSiniestro(new javax.swing.JFrame(), true);
                                
                    vtsi.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn14.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }
        
    }
}
