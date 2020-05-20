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
import modelo.ModUsuario;
import vista.Usuario;
import vista.CatUsuario;

/**
 *
 * @author Heimys
 */
public class ctrCatUsuario implements ActionListener, KeyListener{

    Usuario vista;
    ModUsuario modelo;    
    CatUsuario catUsuario;
    
   
    public ctrCatUsuario(CatUsuario catUsuario) {
        this.catUsuario = catUsuario;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catUsuario.btnAceptar) {
            catUsuario.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catUsuario.btnCancelar) {
            catUsuario.dispose();
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
            modelo = new ModUsuario();
            modelo.cargarDatosCatalogoPersona(tablaModel);        
    }
    
       ////////////////////////

       public void Listado(){
            
        modelo = new ModUsuario();
        String[][] informacion =  modelo.Filtro(catUsuario.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Cargo","Telefono","Sexo","Fecha Nacimiento","Nacion"});
            catUsuario.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Cargo","Telefono","Sexo","Fecha Nacimiento","Nacion"});
            catUsuario.getTabCatalago().setModel(model);
        }
        
    }
        public void ListadoEstudiante(){
         modelo = new ModUsuario();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado usuario");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Cargo","Telefono","Sexo","Fecha Nacimiento","Nacion"});
           catUsuario.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO USUARIO");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cedula", "Nombre", "Apellido","Direccion","Cargo","Telefono","Sexo","Fecha Nacimiento","Nacion"});
            catUsuario.getTabCatalago().setModel(model);
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
        
        if (e.getSource() == catUsuario.getTxtBuscar()){
            Listado();
        }
    }

 
    
}
