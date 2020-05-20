/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModMovimientoBien;
import vista.CatMovimientoBien;
import vista.Movimiento_Bien;
import vista.Principal;

/**
 *
 * @author betty
 */
public class ctrMovimientoBien implements ActionListener, KeyListener {
    
    ModMovimientoBien modelo;    
    Movimiento_Bien vista;
    CatMovimientoBien vMbien;
    
   
    public ctrMovimientoBien(Movimiento_Bien vista) {
        this.vista= vista;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        int acc;
        if (evento.getSource() == vista.btnCancelar) {
         vista.dispose();
        }else if (evento.getSource() == vista.btnCatalago) {
                     System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vMbien = new CatMovimientoBien(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrMovimientoBien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vMbien.setVisible(true);

            if(vMbien.tabCatalago.isRowSelected(vMbien.tabCatalago.getSelectedRow()) ){
                codigobus = vMbien.tabCatalago.getValueAt(vMbien.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vMbien.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
          
        
        
        }
       
 
    }

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoBien(DefaultTableModel tablaModel){
           System.out.println("Catalago movimiento");
            modelo = new ModMovimientoBien();
    }
    
       ////////////////////////

 
        public void ListadoMovimiento(){
         modelo = new ModMovimientoBien();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado Movimiento");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"TIPO MOVIMIENTO","BIEN", "RESPONSABLE","FECHA MOVIMIENTO","CANTIDAD"});
           vista.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO Movimiento");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"TIPO MOVIMIENTO","BIEN", "RESPONSABLE","FECHA MOVIMIENTO","CANTIDAD"});
            vista.getTabCatalago().setModel(model);
        }
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

       @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
