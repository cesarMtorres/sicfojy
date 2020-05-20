/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModPrograma;
import modelo.ModModulo;
//import net.sf.jasperreports.engine.JRException;
import modelo.ModeloBitacora;
import vista.CatPrograma;
import vista.Principal;
import vista.Programa;

/**
 *
 * @author betty
 */
public class ctrPrograma extends AbstractAction implements ActionListener  {
    
    public Programa vista;
    ModeloBitacora bitacora;
    private int acc;
    //modelo datos pesonales
    public ModPrograma modelo;
    public ModModulo mmodulo;
    private CatPrograma vcatPrograma;

    public ctrPrograma(Programa vista) {
        this.vista = vista;
        modelo = new ModPrograma(vista);
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
                vcatPrograma = new CatPrograma(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatPrograma.setVisible(true);

            if(vcatPrograma.tabCatalago.isRowSelected(vcatPrograma.tabCatalago.getSelectedRow()) ){
                codigobus = vcatPrograma.tabCatalago.getValueAt(vcatPrograma.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatPrograma.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModPrograma();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                vista.getNombre().setText(modelo.getNombre());
                vista.getDescrip().setText(modelo.getDescrip());
                vista.getId().setText(modelo.getId());
                vista.getModulo().setSelectedItem(modelo.getModulo());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Programa" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        
    }
}
     
    public void Consultar(){
        modelo = new ModPrograma();
            boolean encontrado = modelo.Consultar(vista.getNombre().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                
                vista.getId().setText(modelo.getId());
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
        if("".equals(vista.nombre.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.id_modulo.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo modulo no puede estar vacio");
        } else if("".equals(vista.descrip.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo descripción no puede estar vacio");
        }
         else {          

        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id_modulo = vista.getId_modulo().getText();
        System.out.println(nombre+descrip+id_modulo);
        modelo = new ModPrograma(nombre,descrip,id_modulo);
        boolean incluido=modelo.RegistrarPrograma();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL PROGRAMA"+nombre+" "+descrip+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
    
 public void Modificar(){
        if("".equals(vista.nombre.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.descrip.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo descripción no puede estar vacio");
        }
         else {  
        String id_modulo= this.consultarIdModulo();
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id = vista.getId().getText();
        String modulo = vista.getId_modulo().getText();
        modelo = new ModPrograma(id,nombre,descrip,id_modulo);
        boolean modificado=modelo.ModificarPrograma(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitos");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL PROGRAMA"+nombre+" "+descrip+"";
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
        String nombre = vista.getNombre().getText();
        String descrip = vista.getDescrip().getText();
        String id = vista.getId().getText();
        modelo = new ModPrograma();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL PROGRAMA "+nombre+" "+descrip+"";
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
        vista.id.setText("");
        vista.id_modulo.setText("");
    }
         public void habilitar(){
        vista.getNombre().setEnabled(false);
        vista.getDescrip().setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.descrip.setEnabled(activ);

    }
           
        public void LLenarModulo() {
        vista.getModulo().removeAllItems();
        ArrayList<String> datos_1 = modelo.CargarModulo();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getModulo().addItem(cargoList);
            
        }
    }
public String consultarIdModulo() {
        System.out.println("COMBO TIPO MODULO");
        
        String id ="";
        
        String itemSeleccionado = vista.modulo.getSelectedItem().toString();
        boolean consultado = mmodulo.consultaridmodulo(itemSeleccionado);
        if(consultado) {
            id = mmodulo.getId();
            System.err.println(id);
        } else {
            id ="";
        }
        
        
        return id;
    }
    
}
