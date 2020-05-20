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
import modelo.ModBien;
import modelo.ModTipoInstrumento;
import vista.Bien;
import vista.CatTipoInstrumento;

/**
 *
 * @author Heimys
 */
public class ctrCatTInstrumento implements ActionListener, KeyListener{

    Bien vista;
    ModTipoInstrumento modelo;    
    CatTipoInstrumento catIns;
    
   
    public ctrCatTInstrumento(CatTipoInstrumento catIns) {
        this.catIns = catIns;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catIns.btnAceptar) {
            catIns.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catIns.btnCancelar) {
            catIns.dispose();
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
           System.out.println("Catalago Tipo Instrumento");
            modelo = new ModTipoInstrumento();
            modelo.cargarDatosCatalogoIns(tablaModel);        
    }


     public void Listado(){
            
        modelo = new ModTipoInstrumento();
        String[][] informacion =  modelo.Filtro(catIns.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ID", "NOMBRE","DESCRIPCION"});
            catIns.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{ "ID", "NOMBRE","DESCRIPCION"});
            catIns.getTabCatalago().setModel(model);
        }
        
    }
         
            public void ListadoInst(){
         modelo = new ModTipoInstrumento();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado INSTRUMENTO");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"NOMBRE", "SERIAL","MARCA","MODELO","CANTIDAD"});
           catIns.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO INSTRUMENTO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"NOMBRE", "SERIAL","MARCA","MODELO","CANTIDAD"});
            catIns.getTabCatalago().setModel(model);
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
        
        if (e.getSource() == catIns.getTxtBuscar()){
            Listado();
        }
    }

 
    
}
