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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModMovimientoBien;
import vista.CatMovimientoBien;
import vista.Empleado;

/**
 *
 * @author betty
 */
public class ctrCatMovimiento implements ActionListener, KeyListener{
    
    Empleado vista;
    ModMovimientoBien modelo;    
    CatMovimientoBien catEmpleado;
    
   
    public ctrCatMovimiento(CatMovimientoBien catEmpleado) {
        this.catEmpleado = catEmpleado;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catEmpleado.btnAceptar) {
            catEmpleado.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catEmpleado.btnCancelar) {
            catEmpleado.dispose();
            //vista_consulta_valores = null;
            
        }
    }
    

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoPersona(DefaultTableModel tablaModel){
           System.out.println("Catalago Movimiento");
            modelo = new ModMovimientoBien();
            modelo.cargarDatosCatalogo(tablaModel);        
    }
    
       ////////////////////////

             public void Listado(){
            
        String[][] informacion =  modelo.Filtro(catEmpleado.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Cargo","Telefono","Sexo","Fecha Nacimiento"});
            catEmpleado.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Cargo","Telefono","Usuario","Sexo","Fecha Nacimiento"});
            catEmpleado.getTabCatalago().setModel(model);
        }
        
    }
         
                     public void ListadoMovimiento(){
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado movimiento");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"TIPO MOVIMIENTO", "BIEN", "RESPONSABLE","FECHA MOVIMIENTO","CANTIDAD"});
           catEmpleado.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO MOVIMIENTO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"TIPO MOVIMIENTO", "BIEN", "RESPONSABLE","FECHA MOVIMIENTO","CANTIDAD"});
            catEmpleado.getTabCatalago().setModel(model);
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
        
        if (e.getSource() == catEmpleado.getTxtBuscar()){
            Listado();
        }
    }  
    
}
