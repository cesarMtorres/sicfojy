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
import modelo.ModMarca;
import vista.Marca;
import vista.CatMarca;

/**
 *
 * @author betty
 */
public class ctrCatMarca implements ActionListener, KeyListener {
    
    Marca vista;
    ModMarca modelo;    
    CatMarca catMarca;
    
   
    public ctrCatMarca(CatMarca catMarca) {
        this.catMarca= catMarca;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catMarca.btnAceptar) {
            catMarca.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catMarca.btnCancelar) {
            catMarca.dispose();
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
           System.out.println("Catalago Marca");
            modelo = new ModMarca();
            modelo.cargarDatosCatalogoMarca(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabMarca, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaMarca(tabMarca, " WHERE " +  catMarca.campobus + " LIKE '%"+valor+"%' ORDER BY " + catMarca.campobus);
                    break;
                case 1:
                    modelo.CargarTablaMarca(tabMarca, " WHERE " +  catMarca.campobus + " LIKE '"+valor+"%' ORDER BY " + catMarca.campobus);
                    break;
                default:
                    modelo.CargarTablaMarca(tabMarca, " ORDER BY " + catMarca.campobus);
                    break;
            }
    }

 public void Listado(){
            
        modelo = new ModMarca();
        String[][] informacion =  modelo.Filtro(catMarca.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Nombre", "Descripcion"});
            catMarca.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{ "Nombre", "Descripcion"});
            catMarca.getTabCatalago().setModel(model);
        }
        
    }
         
         public void ListadoMarca(){
         modelo = new ModMarca();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado cargo");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"NOMBRE", "DESCRIPCION"});
           catMarca.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO CARGO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"NOMBRE", "DESCRIPCION"});
            catMarca.getTabCatalago().setModel(model);
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
        
        if (e.getSource() == catMarca.getTxtBuscar()){
            Listado();
        }
    }
    
}
