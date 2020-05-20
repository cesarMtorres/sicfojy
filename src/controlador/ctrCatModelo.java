/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModModelo;
import vista.Modelo;
import vista.CatModelo;

/**
 *
 * @author betty
 */
public class ctrCatModelo implements ActionListener, KeyListener{
    
    Modelo vista;
    ModModelo modelo;    
    CatModelo catModelo;
    
   
    public ctrCatModelo(CatModelo catModelo) {
        this.catModelo= catModelo;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catModelo.btnAceptar) {
            catModelo.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catModelo.btnCancelar) {
            catModelo.dispose();
            //vista_consulta_valores = null;
            
        }
    }
    

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoBien(DefaultTableModel tablaModel){
           System.out.println("Catalago modelo");
            modelo = new ModModelo();
            modelo.cargarDatosCatalogoModelo(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabModelo, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaModelo(tabModelo, " WHERE " +  catModelo.campobus + " LIKE '%"+valor+"%' ORDER BY " + catModelo.campobus);
                    break;
                case 1:
                    modelo.CargarTablaModelo(tabModelo, " WHERE " +  catModelo.campobus + " LIKE '"+valor+"%' ORDER BY " + catModelo.campobus);
                    break;
                default:
                    modelo.CargarTablaModelo(tabModelo, " ORDER BY " + catModelo.campobus);
                    break;
            }
    }


       public void Listado(){
            
        modelo = new ModModelo();
        String[][] informacion =  modelo.Filtro(catModelo.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"codigo","modelo", "Marca"});
            catModelo.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"codigo","Modelo", "Marca"});
            catModelo.getTabCatalago().setModel(model);
        }
        
    }
         
         public void ListadoModelo(){
         modelo = new ModModelo();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado empleado");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"codigo","Modelo", "Marca"});
           catModelo.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO MODELO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"codigo","Modelo", "Marca"});
            catModelo.getTabCatalago().setModel(model);
        }
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       //To change body of generated methods, choose Tools | Templates.
    }

   @Override
    public void keyReleased(KeyEvent e) {
        
        if (e.getSource() == catModelo.getTxtBuscar()){
            Listado();
        }
    }
    
    
}
