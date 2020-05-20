package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModTipoInstrumento;
import vista.TipoInstrumento;
import vista.CatTipoInstrumento;

/**
 *
 * @author Heimys
 */
public class ctrCatTipoInstrumento implements ActionListener, MouseListener{

    TipoInstrumento vista;
    ModTipoInstrumento modelo;    
    CatTipoInstrumento catTipoInstrumento;
    
   
    public ctrCatTipoInstrumento(CatTipoInstrumento catTipoInstrumento) {
        this.catTipoInstrumento= catTipoInstrumento;
        this.modelo = new ModTipoInstrumento(this.catTipoInstrumento);
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catTipoInstrumento.btnAceptar) {
            catTipoInstrumento.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catTipoInstrumento.btnCancelar) {
            catTipoInstrumento.dispose();
            //vista_consulta_valores = null;
            
        }
    }
    

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoTipoInstrumento(DefaultTableModel tablaModel){
           System.out.println("Catalago Instrumento ");
            modelo = new ModTipoInstrumento();
            modelo.cargarDatosCatalogoTipoInstrumento(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaTipoInstrumento(tabEstudiante, " WHERE " +  catTipoInstrumento.campobus + " LIKE '%"+valor+"%' ORDER BY " + catTipoInstrumento.campobus);
                    break;
                case 1:
                    modelo.CargarTablaTipoInstrumento(tabEstudiante, " WHERE " +  catTipoInstrumento.campobus + " LIKE '"+valor+"%' ORDER BY " + catTipoInstrumento.campobus);
                    break;
                default:
                    modelo.CargarTablaTipoInstrumento(tabEstudiante, " ORDER BY " + catTipoInstrumento.campobus);
                    break;
            }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
}
