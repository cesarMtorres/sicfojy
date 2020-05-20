package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModRepresentante;
import vista.CatRepresentanteEST;
import vista.Estudiante;


/**
 *
 * @author Heimys
 */
public class ctrCatRepresentanteEST implements ActionListener, KeyListener{

    Estudiante vista;
    ModRepresentante modelo;    
    CatRepresentanteEST catCargo;
    
   
    public ctrCatRepresentanteEST(CatRepresentanteEST catCargo) {
        this.catCargo= catCargo;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catCargo.btnAceptar) {
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
    public void Listado(String id){
           System.out.println("Catalago representante ");
            modelo = new ModRepresentante();
            modelo.consultarListadoRepEst(id);        
    }
    /**
      public void click_tabla(MouseEvent me) {
          
           
          int punto = vista.Tabla.rowAtPoint(me.getPoint()); 
          
          vista.nombre.getNombre().setText(String.valueOf(vista.tabla.getValueAt(punto, 10)));
          int id_afili = Integer.parseInt(Vistas.Afiliacion.getNro_afiliacion().getText());
          modelo.consultarIdAsignacion_catalogo(id_afili);
           
            //String fecha1 = new SimpleDateFormat("dd/MM/yyyy").format(modelo.getFecha());
            //Vistas.Afiliacion.getFecha().setText(fecha1);
            vista.Afiliacion.getNacionalidad().setText(modelo.getNacionalidad());
            vista.Afiliacion.getCedula().setText(modelo.getCedula());
 
   
          }
      **/

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
