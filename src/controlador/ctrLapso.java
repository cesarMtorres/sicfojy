/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModPrestamo;

import vista.RenovacionPrestamo;
import vista.Prestamo;

/**
 *
 * @author cesar
 */
public class ctrLapso extends AbstractAction implements ActionListener {

   
    //modelo datos pesonales
    private RenovacionPrestamo vista;
    private Prestamo prestamo;
    ModPrestamo modelo;
    public ctrLapso(RenovacionPrestamo vista) {
        this.vista = vista;
    }
    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getBtnAceptar()){
            Modificar();
            System.out.println("Botón Modificar Renovacion...");
        }else if(evento.getSource()==vista.getBtnCancelar()){
            Cancelar();
            System.out.println("Botón cancelar...");
        }
        
        
    }
     
  
 public void Modificar(){
        if("".equals(vista.btnTipoPrestamo.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo Tipo prestamo no puede estar vacio");
        }else {
             String tipoprestamo=vista.id_tp.getText();
             String id=prestamo.id_estudiante.getText();
             modelo = new ModPrestamo();
             modelo.Renovacion(id,tipoprestamo);
        }
 }
        
       
   
      public void LlenarTipoPrestamo() {
         
        vista.getBtnTipoPrestamo().removeAllItems();
        ArrayList<String> datos_1 = modelo.CargarTipoPrestamo();
        if(datos_1 == null){

        }else{
        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getBtnTipoPrestamo().addItem(cargoList);
        }
        }
        
    }
   
           
            
         public void Cancelar(){
         int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        }
     }
     public void limpiar() {
        vista.btnTipoPrestamo.setSelectedItem("");
        vista.id_tp.setText("");
    }

        

}