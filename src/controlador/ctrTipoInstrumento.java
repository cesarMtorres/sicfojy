
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModTipoInstrumento;
import modelo.Validacion;
//import net.sf.jasperreports.engine.JRException;
import vista.CatTipoInstrumento;
import vista.TipoInstrumento;
import modelo.ModeloBitacora;
import vista.Principal;

/**
 *
 * @author cesar
 */
public class ctrTipoInstrumento extends AbstractAction implements ActionListener {
    public TipoInstrumento vista;
    Validacion validacion;
    ModeloBitacora bitacora;
    //modelo datos pesonales
    public ModTipoInstrumento modelo;
    private CatTipoInstrumento vcatins;
    int acc;

    public ctrTipoInstrumento(TipoInstrumento vista) {
        this.vista = vista;
        modelo = new ModTipoInstrumento(vista);
        bitacora=new ModeloBitacora();
    }
@Override
     public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==vista.getBtnRegistrar()){
            Registrar();
            System.out.println("Botón incluir...");
        }else if(evento.getSource()==vista.getBtnModificar()){
            Modificar();
            System.out.println("Botón Modificar...");
        }else if(evento.getSource()==vista.getBtnEliminar()){
            Eliminar();
            System.out.println("Botón Eliminar...");
        }else if(evento.getSource()==vista.getBtnCancelar()){
            Cancelar();
            System.out.println("Botón cancelar...");
        }else if(evento.getSource()==vista.getBtnCatalago()){
              System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatins = new CatTipoInstrumento(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrTipoInstrumento.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatins.setVisible(true);

            if(vcatins.tabCatalago.isRowSelected(vcatins.tabCatalago.getSelectedRow()) ){
                codigobus = vcatins.tabCatalago.getValueAt(vcatins.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatins.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModTipoInstrumento();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                vista.getNombre().setText(modelo.getNombre());
                vista.getDescripcion().setText(modelo.getDescrip());
                vista.getId().setText(modelo.getId());

                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Tipo Instrumeto" + " no encontrado ", ".",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
    }
     
    public void Consultar(){
        modelo = new ModTipoInstrumento();
            boolean encontrado = modelo.Consultar(vista.getId().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                vista.getDescripcion().setText(modelo.getDescrip());
                //Acción de los botones y cajas de texto
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnEliminar().setEnabled(true);
                vista.getBtnRegistrar().setEnabled(true);
                
            }else{
                vista.getBtnRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
    }
    public void Registrar(){
        if("".equals(vista.descripcion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.nombre.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo descripción no puede estar vacio");
        }
         else {  
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescripcion().getText();
        modelo = new ModTipoInstrumento(nombre,descrip);
        boolean incluido=modelo.RegistrarTipoBien();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL TIPO DE INSTRUMENTO "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
    
 public void Modificar(){

        if("".equals(vista.descripcion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.nombre.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo descripción no puede estar vacio");
        }
         else {  
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescripcion().getText();
        String id = vista.getId().getText();
        modelo = new ModTipoInstrumento(id,nombre,descrip);
        boolean modificado=modelo.ModificarTipoInstrumento(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL TIPO INSTRUMENTO "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
     int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
        String id = vista.getId().getText();
        String nombre = vista.getId().getText();
        modelo = new ModTipoInstrumento();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL TIPO INSTRUMENTO"+nombre+" ";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
        
    }//fin de codigoEliminar
         public void Cancelar(){
         int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        }
     }
     public void limpiar() {
        vista.descripcion.setText("");
        vista.nombre.setText("");
    }
         public void habilitar(){
        vista.getNombre().setEnabled(false);
        vista.getDescripcion().setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.descripcion.setEnabled(activ);
        vista.nombre.setEnabled(activ);

    }
        

}