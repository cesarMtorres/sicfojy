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
import vista.Bien;
import vista.Bitacora;
import vista.Cargo;
import vista.Catedra;
import vista.Empleado;
import vista.Estudiante;
import vista.Profesor;
import vista.FondoSistema;
import vista.Marca;
import vista.Modelo;
import vista.Modulo;
import vista.Movimiento_Bien;
import vista.Perfil;
import vista.Programa;
import vista.Representante;
import vista.RespRestBD;
import vista.TipoBien;
import vista.TipoInstrumento;
import vista.TipoSiniestro;
import vista.Usuario;
import vista.paneles.pnlFeeBack;

/**
 *
 * @author cesar
 */
public class ctrMenuAlterno extends AbstractAction implements ActionListener {
    public pnlFeeBack vista;
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
    public Bitacora b;
    public TipoBien vtbi;
    public Usuario vusu;
    public RespRestBD vres;
    public Cargo vcar;
    public Movimiento_Bien vmb;
    public Empleado vemp;
    public Perfil vper;
//    public Perfil perf;
    private int acc;
    //modelo datos pesonales

    public ctrMenuAlterno(pnlFeeBack vista) {
        this.vista = vista;
    }


    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.btn17){
            if (FondoSistema.accesar("PERFIL", "17")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                   vper = new Perfil(new javax.swing.JFrame(), true);
                   
                   vper.setVisible(true);
                                
                  //  perf.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn17.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }
         else if(evento.getSource()==vista.btn162){
            if (FondoSistema.accesar("BITACORA", "18")){
                // Llamada a la Pantalla VistaAfiliado
                    b = new Bitacora(new javax.swing.JFrame(), true);
                                
                    b.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn162.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }else if(evento.getSource()==vista.btn18){
            if (FondoSistema.accesar("RESTAURAR Y RESPALDAR", "19")){
                    System.out.println("True");
                // Llamada a la Pantalla VistaAfiliado
                    vres =new RespRestBD(new javax.swing.JFrame(), true);
                    
                    vres.setVisible(true);
                             
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                        vista.btn18.getText(), "Acceso Denegado",
                        JOptionPane.INFORMATION_MESSAGE);
                }
        }
        
    }
}
