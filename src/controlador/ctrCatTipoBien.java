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
import modelo.ModTipoBien;
import vista.TipoBien;
import vista.CatTipoBien;

/**
 *
 * @author Heimys
 */
public class ctrCatTipoBien implements ActionListener, KeyListener{

    TipoBien vista;
    ModTipoBien modelo;    
    CatTipoBien catTipoBien;
    
   
    public ctrCatTipoBien(CatTipoBien catTipoBien) {
        this.catTipoBien= catTipoBien;
        this.modelo = new ModTipoBien(this.catTipoBien);
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catTipoBien.btnAceptar) {
            catTipoBien.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catTipoBien.btnCancelar) {
            catTipoBien.dispose();
            //vista_consulta_valores = null;
            
        }
    }
    

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoTipoBien(DefaultTableModel tablaModel){
           System.out.println("Catalago bien");
            modelo = new ModTipoBien();
            modelo.cargarDatosCatalogoTipoBien(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaTipoBien(tabEstudiante, " WHERE " +  catTipoBien.campobus + " LIKE '%"+valor+"%' ORDER BY " + catTipoBien.campobus);
                    break;
                case 1:
                    modelo.CargarTablaTipoBien(tabEstudiante, " WHERE " +  catTipoBien.campobus + " LIKE '"+valor+"%' ORDER BY " + catTipoBien.campobus);
                    break;
                default:
                    modelo.CargarTablaTipoBien(tabEstudiante, " ORDER BY " + catTipoBien.campobus);
                    break;
            }
    }
    
   public void ListadoBien(){
         modelo = new ModTipoBien();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado tipo bien");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ID", "NOMBRE","DESCRIPCION"});
           catTipoBien.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO TIPO BIEN");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"ID", "NOMBRE","DESCRIPCION"});
            catTipoBien.getTabCatalago().setModel(model);
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
