package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModCargo;
import vista.Cargo;
import vista.CatCargo;

/**
 *
 * @author Heimys
 */
public class ctrCatCargo implements ActionListener, KeyListener{

    Cargo vista;
    ModCargo modelo;    
    CatCargo catCargo;
    
   
    public ctrCatCargo(CatCargo catCargo) {
        this.catCargo= catCargo;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catCargo.btnAceptar) {
            catCargo.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catCargo.btnCancelar) {
            catCargo.dispose();
            //vista_consulta_valores = null;
            
        }if (evento.getSource() == catCargo.buscapor1) {
            try {
                catCargo.buscapor1.setSelected(true);
                eventobusqueda(catCargo.txtBuscar.getText().toString(), catCargo.tabCatalago, 2);
            } catch (SQLException ex) {
                Logger.getLogger(ctrCatCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evento.getSource() == catCargo.buscapor2) {
            try {
                catCargo.buscapor2.setSelected(true);
                eventobusqueda(catCargo.txtBuscar.getText().toString(), catCargo.tabCatalago, 2);
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
           System.out.println("Catalago cargo");
            modelo = new ModCargo();
            modelo.cargarDatosCatalogoCargo(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaCargo(tabEstudiante, " WHERE " +  catCargo.campobus + " LIKE '%"+valor+"%' AND eliminacion='TRUE' ORDER BY " + catCargo.campobus);
                    break;
                case 1:
                    modelo.CargarTablaCargo(tabEstudiante, " WHERE " +  catCargo.campobus + " LIKE '"+valor+"%' AND eliminacion='TRUE' ORDER BY " + catCargo.campobus);
                    break;
                default:
                    modelo.CargarTablaCargo(tabEstudiante, " ORDER BY " + catCargo.campobus);
                    break;
            }
    }

          public void ListadoCargo(){
         modelo = new ModCargo();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado cargo");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"NOMBRE", "DESCRIPCION"});
           catCargo.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO CARGO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"NOMBRE", "DESCRIPCION"});
            catCargo.getTabCatalago().setModel(model);
        }
        
    }

@Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


 
    
}
