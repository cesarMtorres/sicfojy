package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModCatedra;
import vista.Catedra;
import vista.CatCatedra;

/**
 *
 * @author Heimys
 */
public class ctrCatCatedra implements ActionListener, KeyListener{

    Catedra vista;
    ModCatedra modelo;    
    CatCatedra catCatedra;
    
       
    public ctrCatCatedra(CatCatedra catCatedra) {
        this.catCatedra= catCatedra;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catCatedra.btnAceptar) {
            catCatedra.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catCatedra.btnCancelar) {
            catCatedra.dispose();
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
           System.out.println("Catalago catedra");
            modelo = new ModCatedra();
            modelo.cargarDatosCatalogoCatedra(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaCatedra(tabEstudiante, " WHERE " +  catCatedra.campobus + " LIKE '%"+valor+"%' ORDER BY " + catCatedra.campobus);
                    break;
                case 1:
                    modelo.CargarTablaCatedra(tabEstudiante, " WHERE " +  catCatedra.campobus + " LIKE '"+valor+"%' ORDER BY " + catCatedra.campobus);
                    break;
                default:
                    modelo.CargarTablaCatedra(tabEstudiante, " ORDER BY " + catCatedra.campobus);
                    break;
            }
    }
       public void Listado(){
            
        modelo = new ModCatedra();
        String[][] informacion =  modelo.Filtro(catCatedra.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"nombre", "descripcion"});
            catCatedra.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{ "nombre", "descripcion"});
            catCatedra.getTabCatalago().setModel(model);
        }
        
    }
         
            public void ListadoCatedra(){
         modelo = new ModCatedra();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado catedra");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"nombre","descripcion"});
           catCatedra.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO CATEDRA");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"nombre","descripcion"});
            catCatedra.getTabCatalago().setModel(model);
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
        
        if (e.getSource() == catCatedra.getTxtBuscar()){
            Listado();
        }
    }
 
    
}
