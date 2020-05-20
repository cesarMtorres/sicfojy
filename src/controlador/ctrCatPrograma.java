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
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModPrograma;
import vista.Programa;
import vista.CatPrograma;

/**
 *
 * @author betty
 */
public class ctrCatPrograma implements ActionListener, KeyListener{
    
    Programa vista;
    ModPrograma modelo;    
    CatPrograma catPrograma;
    
   
    public ctrCatPrograma(CatPrograma catPrograma) {
        this.catPrograma= catPrograma;
        this.modelo = new ModPrograma(this.catPrograma);
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catPrograma.btnAceptar) {
            catPrograma.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catPrograma.btnCancelar) {
            catPrograma.dispose();
            //vista_consulta_valores = null;
            
        }
    }
    

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt)throws SQLException {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoBien(DefaultTableModel tablaModel){
           System.out.println("Catalago Programa");
            modelo = new ModPrograma();
            modelo.cargarDatosCatalogoPrograma(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabPrograma, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaPrograma(tabPrograma, " WHERE " +  catPrograma.campobus + " LIKE '%"+valor+"%' ORDER BY " + catPrograma.campobus);
                    break;
                case 1:
                    modelo.CargarTablaPrograma(tabPrograma, " WHERE " +  catPrograma.campobus + " LIKE '"+valor+"%' ORDER BY " + catPrograma.campobus);
                    break;
                default:
                    modelo.CargarTablaPrograma(tabPrograma, " ORDER BY " + catPrograma.campobus);
                    break;
            }
            
    }
    
 public void Listado(){
            
        modelo = new ModPrograma();
        String[][] informacion =  modelo.Filtro(catPrograma.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Nombre", "Descripcion"});
            catPrograma.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{ "Nombre", "Descripcion"});
            catPrograma.getTabCatalago().setModel(model);
        }
        
    }
         
         public void ListadoPrograma(){
         modelo = new ModPrograma();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado cargo");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ID","NOMBRE", "DESCRIPCION"});
           catPrograma.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO CARGO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"ID","NOMBRE", "DESCRIPCION"});
            catPrograma.getTabCatalago().setModel(model);
        }
         }
         
        @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       //To change body of generated methods, choose Tools | Templates.
    }

        @Override
    public void keyReleased(KeyEvent e) {
        
            if (e.getSource() == catPrograma.getTxtBuscar()){
            Listado();
        }
    }
    
    
    
}
