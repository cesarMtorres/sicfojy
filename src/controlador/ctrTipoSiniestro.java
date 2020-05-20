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
import modelo.ModTipoSiniestro;
import modelo.Validacion;
//import net.sf.jasperreports.engine.JRException;
import vista.CatTipoSiniestro;
import modelo.ModeloBitacora;
import vista.Principal;
import vista.TipoSiniestro;

/**
 *
 * @author cesar
 */
public class ctrTipoSiniestro extends AbstractAction implements ActionListener {
    public TipoSiniestro vista;
    Validacion validacion;
    ModeloBitacora bitacora;

    //modelo datos pesonales
    public ModTipoSiniestro modelo;
    private CatTipoSiniestro vcatipob;
    int acc;

    public ctrTipoSiniestro(TipoSiniestro vista) {
        this.vista = vista;
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
                vcatipob = new CatTipoSiniestro(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrBien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatipob.setVisible(true);

            if(vcatipob.tabCatalago.isRowSelected(vcatipob.tabCatalago.getSelectedRow()) ){
                codigobus = vcatipob.tabCatalago.getValueAt(vcatipob.tabCatalago.getSelectedRow(),0).toString(); // obtiene el serial
                acc =vcatipob.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModTipoSiniestro();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.descrip.setText(modelo.getDescrip());
                    vista.nombre.setText(modelo.getNombre());
                    vista.getId().setText(modelo.getId_tipob());
                   vista.btnRegistrar.setEnabled(false);
                    vista.btnModificar.setEnabled(true);
                    
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Tipo Siniestro" + " no encontrado ", ".",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            
        }
        
        
    }
     
    public void Consultar(){
        modelo = new ModTipoSiniestro();
            boolean encontrado = modelo.Consultar(vista.getNombre().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                vista.getDescrip().setText(modelo.getDescrip());
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
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        modelo = new ModTipoSiniestro(nombre,descrip);
        boolean incluido=modelo.RegistrarTipoBien();
       if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL TIPO SINIESTRO"+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
    
 public void Modificar(){


        String id = vista.getId().getText();
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();;
        modelo = new ModTipoSiniestro(id,nombre,descrip);
        boolean modificado=modelo.ModificarTipoBien(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL TIPO SINIESTRO "+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
     int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Eliminar el Registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id = vista.getId().getText();
        modelo = new ModTipoSiniestro(id,nombre,descrip);
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL TIPO SINIESTRO "+nombre+" "+descrip+"";
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
        vista.nombre.setText("");
        vista.descrip.setText("");
    }
         public void habilitar(){
        vista.getNombre().setEnabled(false);
        vista.getDescrip().setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.descrip.setEnabled(activ);

    }
        

}