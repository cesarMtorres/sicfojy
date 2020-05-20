package controlador;

import vista.CatRepresentante;
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
import modelo.ModRepresentante;
import vista.CatREstudiante;
import vista.Representante;

/**
 *
 * @author Heimys
 */
public class ctrCatRepresentante implements ActionListener, MouseListener{

    Representante vista;
    ModRepresentante modelo;    
    CatRepresentante catRepresentante;
    CatREstudiante catRestudiante;
   
    public ctrCatRepresentante(CatRepresentante catRepresentante) {
        this.catRepresentante = catRepresentante;
        this.modelo = new ModRepresentante(this.catRepresentante);
    }

    public ctrCatRepresentante(CatREstudiante catRestudiante) {
        this.catRestudiante = catRestudiante;
        this.modelo = new ModRepresentante(this.catRestudiante);
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catRepresentante.btnAceptar) {
            catRepresentante.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catRepresentante.btnCancelar) {
            catRepresentante.dispose();
            //vista_consulta_valores = null;
            
        }
    }
                public void ListadoEstudiante(){
         modelo = new ModRepresentante();
         
        String[][] informacion =  modelo.consultarListadoE(vista.getId().getText());
        if(informacion == null){
            System.out.println("null listado Estudiante");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"SERIAL", "MODELO","MARCA","MEDIDA","Tipo Bien","Numero de Inventario","cantidad"});
            catRestudiante.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO Estudiante");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"SERIAL", "MODELO","MARCA","MEDIDA","Tipo Bien","Numero de Inventario","cantidad"});
            catRestudiante.getTabCatalago().setModel(model);
        }
        
    }

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoPersona(DefaultTableModel tablaModel){
           System.out.println("Catalago persona");
            modelo = new ModRepresentante();
            modelo.cargarDatosCatalogoRepresentante(tablaModel);        
    }
    
       ////////////////////////
    public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaRepresentante(tabEstudiante, " WHERE " +  CatRepresentante.campobus + " LIKE '%"+valor+"%' ORDER BY " + CatRepresentante.campobus);
                    break;
                case 1:
                    modelo.CargarTablaRepresentante(tabEstudiante, " WHERE " +  CatRepresentante.campobus + " LIKE '"+valor+"%' ORDER BY " + CatRepresentante.campobus);
                    break;
                default:
                    modelo.CargarTablaRepresentante(tabEstudiante, " ORDER BY " + CatRepresentante.campobus);
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
