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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModCargo;
import modelo.ModModulo;
import vista.CatModulo;
import vista.Modulo;

/**
 *
 * @author betty
 */
public class ctrCatModulo implements ActionListener, KeyListener{
    
    Modulo vista;
    ModModulo Modulo;    
    CatModulo catModulo;
    
   
    public ctrCatModulo(CatModulo catModulo) {
        this.catModulo= catModulo;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catModulo.btnAceptar) {
            catModulo.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catModulo.btnCancelar) {
            catModulo.dispose();
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
            Modulo = new ModModulo();
            Modulo.cargarDatosCatalogoModulo(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabModulo, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    Modulo.CargarTablaModulo(tabModulo, " WHERE " +  catModulo.campobus + " LIKE '%"+valor+"%' AND eliminacion='TRUE' ORDER BY" + catModulo.campobus);
                    break;
                case 1:
                    Modulo.CargarTablaModulo(tabModulo, " WHERE " +  catModulo.campobus + " LIKE '"+valor+"%' AND eliminacion='TRUE' ORDER BY" + catModulo.campobus);
                    break;
                default:
                    Modulo.CargarTablaModulo(tabModulo, " ORDER BY " + catModulo.campobus);
                    break;
            }
    }
         public void Listado(){
            
        Modulo = new ModModulo();
        String[][] informacion =  Modulo.Filtro(catModulo.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Nombre", "Descripcion"});
            catModulo.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{ "Nombre", "Descripcion"});
            catModulo.getTabCatalago().setModel(model);
        }
        
    }
     
            public void ListadoModulo(){
         Modulo = new ModModulo();
        String[][] informacion =  Modulo.consultarListado();
        if(informacion == null){
            System.out.println("null listado modulo");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"NOMBRE", "DESCRIPCION"});
           catModulo.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO MODULO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"NOMBRE", "DESCRIPCION"});
            catModulo.getTabCatalago().setModel(model);
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
        
        if (e.getSource() == catModulo.getTxtBuscar()){
            Listado();
        }
    }
    
}
