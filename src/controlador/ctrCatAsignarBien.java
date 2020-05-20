package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModAsignarBien;
import vista.Bien;
import vista.CatAsignarBien;

/**
 *
 * @author Heimys
 */
public class ctrCatAsignarBien implements ActionListener, KeyListener{

    Bien vista;
    ModAsignarBien modelo;    
    CatAsignarBien catBien;
    
   
    public ctrCatAsignarBien(CatAsignarBien catBien) {
        this.catBien = catBien;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catBien.btnAceptar) {
            catBien.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catBien.btnCancelar) {
            catBien.dispose();
            //vista_consulta_valores = null;
            
        }if (evento.getSource() == catBien.buscapor1) {
            try {
                catBien.buscapor1.setSelected(true);
                eventobusqueda(catBien.txtBuscar.getText().toString(), catBien.tabCatalago, 2);
            } catch (SQLException ex) {
                Logger.getLogger(ctrCatCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evento.getSource() == catBien.buscapor2) {
            try {
                catBien.buscapor2.setSelected(true);
                eventobusqueda(catBien.txtBuscar.getText().toString(), catBien.tabCatalago, 2);
            } catch (SQLException ex) {
                Logger.getLogger(ctrCatCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoBien(DefaultTableModel tablaModel){
           System.out.println("Catalago bien");
            modelo = new ModAsignarBien();
            modelo.cargarDatosCatalogoBien(tablaModel);        
    }
    public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaBien(tabEstudiante, " WHERE " +  catBien.campobus + " LIKE '%"+valor+"%' AND eliminacion='TRUE' ORDER BY " + catBien.campobus);
                    break;
                case 1:
                    modelo.CargarTablaBien(tabEstudiante, " WHERE " +  catBien.campobus + " LIKE '"+valor+"%' AND eliminacion='TRUE' ORDER BY " + catBien.campobus);
                    break;
                default:
                    modelo.CargarTablaBien(tabEstudiante, " ORDER BY " + catBien.campobus);
                    break;
            }
    }

         
            public void ListadoBien(){
         modelo = new ModAsignarBien();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado bien");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ID","NOMBRE", "MARCA","TIPO BIEN","SERIAL","CANTIDAD"});
           catBien.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO BIEN");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"ID","NOMBRE", "MARCA","TIPO BIEN","SERIAL","CANTIDAD"});
            catBien.getTabCatalago().setModel(model);
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
