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
import modelo.ModEstudiante;
import vista.*;

/**
 *
 * @author Heimys
 */
public class ctrCatEstudiante implements ActionListener, KeyListener{

    Estudiante vista;
    ModEstudiante modelo;    
    CatEstudiante catEstudiante;
    
   
    public ctrCatEstudiante(CatEstudiante catEstudiante) {
        this.catEstudiante = catEstudiante;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catEstudiante.btnAceptar) {
            catEstudiante.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catEstudiante.btnCancelar) {
            catEstudiante.dispose();
            //vista_consulta_valores = null;
            
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
            modelo = new ModEstudiante();
            modelo.cargarDatosCatalogoPersona(tablaModel);        
    }
    
       ////////////////////////

       public void Listado(){
            
        modelo = new ModEstudiante();
        String[][] informacion =  modelo.Filtro(catEstudiante.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Catedra","Telefono","Sexo","Fecha Nacimiento","Nacion"});
            catEstudiante.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Catedra","Telefono","Sexo","Fecha Nacimiento","Nacion"});
            catEstudiante.getTabCatalago().setModel(model);
        }
        
    }
        public void ListadoEstudiante(){
         modelo = new ModEstudiante();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado empleado");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Catedra","Telefono","Sexo","Fecha Nacimiento","Nacion"});
           catEstudiante.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO EMPLEADO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Catedra","Telefono","Sexo","Fecha Nacimiento","Nacion"});
            catEstudiante.getTabCatalago().setModel(model);
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
        
        if (e.getSource() == catEstudiante.getTxtBuscar()){
            Listado();
        }
    }

 
    
}
