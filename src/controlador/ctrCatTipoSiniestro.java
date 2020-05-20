package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModTipoSiniestro;
import vista.CatTipoSiniestro;
import vista.TipoSiniestro;

/**
 *
 * @author Heimys
 */
public class ctrCatTipoSiniestro implements ActionListener, KeyListener{

    TipoSiniestro vista;
    ModTipoSiniestro modelo;    
    CatTipoSiniestro catCargo;
    
   
    public ctrCatTipoSiniestro(CatTipoSiniestro catCargo) {
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
            modelo = new ModTipoSiniestro();
            modelo.cargarDatosCatalogoTipoBien(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaTipoBien(tabEstudiante, " WHERE " +  catCargo.campobus + " LIKE '%"+valor+"%' ORDER BY " + catCargo.campobus);
                    break;
                case 1:
                    modelo.CargarTablaTipoBien(tabEstudiante, " WHERE " +  catCargo.campobus + " LIKE '"+valor+"%' ORDER BY " + catCargo.campobus);
                    break;
                default:
                    modelo.CargarTablaTipoBien(tabEstudiante, " ORDER BY " + catCargo.campobus);
                    break;
            }
    }
         public void Listado(){
            
        modelo = new ModTipoSiniestro();
        String[][] informacion =  modelo.Filtro(catCargo.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Codigo","Nombre", "Descripcion"});
            catCargo.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Codigo" ,"Nombre", "Descripcion"});
            catCargo.getTabCatalago().setModel(model);
        }
        
    }
         
                     public void ListadoCargo(){
         modelo = new ModTipoSiniestro();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado tipo siniestro");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"CODIGO","NOMBRE", "DESCRIPCION"});
           catCargo.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO tipo Siniestro");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"CODIGO","NOMBRE", "DESCRIPCION"});
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
    public void keyReleased(KeyEvent e) {
        
        if (e.getSource() == catCargo.getTxtBuscar()){
            Listado();
        }
    }
 
    
}
