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
import modelo.ModProfesor;
import vista.*;

/**
 *
 * @author Heimys
 */
public class ctrCatProfesor implements ActionListener, KeyListener{

    Estudiante vista;
    ModProfesor modelo;    
    CatProfesor catProfesor;
    
   
    public ctrCatProfesor(CatProfesor catProfesor) {
        this.catProfesor = catProfesor;
        this.modelo = new ModProfesor(this.catProfesor);
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catProfesor.btnAceptar) {
            catProfesor.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catProfesor.btnCancelar) {
            catProfesor.dispose();
            //vista_consulta_valores = null;
            
        }
    }
    

 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
    }
    
   ////////////////////////
   
       ////////////////////////
    public void eventobusqueda(String valor, JTable ptabcargo, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaEstudiante(ptabcargo, " WHERE " +  CatProfesor.campobus + " LIKE '%"+valor+"%' ORDER BY " + CatProfesor.campobus);
                    break;
                case 1:
                    modelo.CargarTablaEstudiante(ptabcargo, " WHERE " +  CatProfesor.campobus + " LIKE '"+valor+"%' ORDER BY " + CatProfesor.campobus);
                    break;
                default:
                    modelo.CargarTablaEstudiante(ptabcargo, " ORDER BY " + CatProfesor.campobus);
                    break;
            }
    }
        public void ListadoProfesor(){
         modelo = new ModProfesor();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado profesor");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Fecha Nacimiento","Catedra"});
           catProfesor.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO profesor");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Fecha Nacimiento","Catedra"});
            catProfesor.getTabCatalago().setModel(model);
        }
        
    }
        
         public void Listado(){
            
        modelo = new ModProfesor();
        String[][] informacion =  modelo.Filtro(catProfesor.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Fecha Nacimiento","Catedra"});
            catProfesor.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{ "Cedula", "Nombre", "Apellido","Direccion","Fecha Nacimiento","Catedra" });
            catProfesor.getTabCatalago().setModel(model);
        }
        
    }    
                 



    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       //To change body of generated methods, choose Tools | Templates.
    }

        @Override
    public void keyReleased(KeyEvent e) {
        
        if (e.getSource() == catProfesor.getTxtBuscar()){
            Listado();
        }
    }

    
}
