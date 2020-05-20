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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ModDesincorporar;
import modelo.ModEmpleado;
import modelo.ModProfesor;
import modelo.ModRepararBien;
import modelo.ModeloBitacora;
//import net.sf.jasperreports.engine.JRException;
import vista.CatProfesor;
import vista.Desincorporar_bien;

import vista.CatBien;
import vista.Movimiento_Bien;
import vista.Principal;
/**
 *
 * @author cesar
 */
public class ctrDesincorporar extends AbstractAction implements ActionListener {
    String fecIUsu = "";
    String just ="";
    String respon ="";
    String nombreBien="";
    String id="";
    String siniestro="";
    String cantidad="";
    Movimiento_Bien principal;
    String id_modulo="";
    String id_programa="";
    
    public Desincorporar_bien vista;
    private int acc;
    private ModeloBitacora bitacora;
    CatBien catbien;
    //modelo datos pesonales
    ModProfesor modeloEmp=new ModProfesor();
    ModDesincorporar modelo=new ModDesincorporar();
    private CatProfesor vcatProfesor;

    public ctrDesincorporar(Desincorporar_bien vista) {
        this.vista = vista;
        bitacora= new ModeloBitacora();
    }
    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getBtnRegistrar()){
            Registrar();
            System.out.println("Botón incluir...");
        }else if(evento.getSource()==vista.btnCancelar){
         int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
         
        }
        
        }else if(evento.getSource()==vista.getBtnModificar()){
            
            Modificar();
        
        }else if(evento.getSource()==vista.getBtnEliminar()){
            Eliminar();
        
        }else if(evento.getSource()==vista.getBtnCatalagoResponsable()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatProfesor = new CatProfesor(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrDesincorporar.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatProfesor.setVisible(true);

            if(vcatProfesor.tabCatalago.isRowSelected(vcatProfesor.tabCatalago.getSelectedRow()) ){
                codigobus = vcatProfesor.tabCatalago.getValueAt(vcatProfesor.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatProfesor.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);

           
            if (codigobus != null) {
                
                boolean existe = modeloEmp.Consultar(codigobus);
                if (existe) {
                    vista.responsable.setText(modeloEmp.getNombre());
                    vista.getId_empleado().setText(modeloEmp.getId_prof());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);
                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Responsable" + " no encontrado ", "Informacion.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else if(evento.getSource()==vista.getBtnCatalagoBien()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                catbien = new CatBien(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrDesincorporar.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            catbien.setVisible(true);

            if(catbien.tabCatalago.isRowSelected(catbien.tabCatalago.getSelectedRow()) ){
                codigobus = catbien.tabCatalago.getValueAt(catbien.tabCatalago.getSelectedRow(),3).toString(); // obtiene el nombre
                acc =catbien.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModDesincorporar();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.id_bien.setText(modelo.getId());
                    vista.nombreBien.setText(modelo.getNombre());

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Bien" + "no encontrado", "Informacion.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
    }
     
    public void Registrar(){
            if("".equals(vista.justificacion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        }else{
     asignaval();
       // modelo = new ModDesincorporar(just,respon,fecIUsu,siniestro,id);
        boolean incluido=modelo.RegistrarDesincorporar(just,respon,fecIUsu,siniestro,id);
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            limpiar();
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO MOVIMIENTO "+siniestro+" "+just+" "+respon+" "+nombreBien+" ";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }

        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
    
        public void Modificar(){
                 if("".equals(vista.justificacion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        } else if("".equals(vista.nombreBien.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre bien no puede estar vacio");
        } else if(vista.txtfecing1.getText()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha  no puede estar vacio");
        }  else if(vista.tipo_siniestro.getSelectedItem()=="") {
            JOptionPane.showMessageDialog(vista, "El campo tipo siniestro no puede estar vacio");
        }else if("".equals(vista.id_empleado.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo responsable no puede estar vacio");
        } 
         else {

        asignaval();
        //modelo = new ModDesincorporar(just,respon,fecha,siniestro,id);
        System.out.println("PARAMETROS:"+just+vista.id_empleado.getText()+fecIUsu+siniestro+id);
        boolean modificado=modelo.ModificarRepararBien(just,vista.id_empleado.getText(),fecIUsu,siniestro,id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL MOVIMIENTO REPRAR "+nombreBien+" "+respon+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
    }//fin de codigoModificar
        public void Eliminar(){
          if("".equals(vista.justificacion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        } else if("".equals(vista.nombreBien.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre bien no puede estar vacio");
        } else if(vista.txtfecing1.getText()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha  no puede estar vacio");
        }  else if(vista.tipo_siniestro.getSelectedItem()=="") {
            JOptionPane.showMessageDialog(vista, "El campo tipo siniestro no puede estar vacio");
        }else{
        String nombrebien = vista.getNombreBien().getText();
        String responsable = vista.getResponsable().getText();
        asignaval();
        
        modelo = new ModDesincorporar();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL MOVIMIENTO DESINCORPORAR "+nombrebien+" "+responsable+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        
        }
    }//fin de codigoEliminar

     public void LlenarTipoS() {
         
        vista.getTipo_siniestro().removeAllItems();
        ArrayList<String> datos_1 = modelo.CargarTipoS();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getTipo_siniestro().addItem(cargoList);
        }
    }
   
     public void limpiar() {
        vista.justificacion.setText("");
        vista.responsable.setText("");
        vista.nombreBien.setText("");
        vista.id_empleado.setText("");
        vista.tipo_siniestro.setSelectedItem("");
        vista.id_bien.setText("");
        vista.txtfecing1.setText("");

    }
         /*    public void ListadoMovimiento(){
         modelo = new ModDesincorporar();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado Prestamo");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ESTADO","SINIESTRO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA"});
           vista.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO Prestamo");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"ESTADO","SINIESTRO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA"});
            vista.getTabCatalago().setModel(model);
        }
        
    }
             public void click_tabla(java.awt.event.MouseEvent evt){
            
        limpiar();
        int cont = vista.getTabCatalago().rowAtPoint(evt.getPoint());
        if(cont >-1){
            Date fechact = new Date();
             SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            vista.getId_bien().setText(String.valueOf(vista.getTabCatalago().getValueAt(cont, 4)));
            modelo.ConsultarTabla(vista.getId_bien().getText());
            vista.id_bien.setText(modelo.getId());
            vista.nombreBien.setText(modelo.getNombre());
            vista.tipo_siniestro.setSelectedItem(modelo.getSiniestro());
            vista.responsable.setText(modelo.getResponsable());
            vista.id_empleado.setText(modelo.getId_empleado());
            vista.justificacion.setText(modelo.getJustificacion());
            fechact = modelo.getFecha();
            vista.txtfecing1.setText(formateador.format(fechact).toString());
            vista.txtfecing1.setVisible(true);
            vista.btnCatalagoBien.setEnabled(false);
            vista.btnCatalagoResponsable.setEnabled(false);
            vista.btnRegistrar.setEnabled(false);
            vista.btnModificar.setEnabled(true);
            vista.btnEliminar.setEnabled(true);
            
        }
    }
     */
        
    public void asignaval() {
       fecIUsu = vista.txtfecing1.getText();
       just = vista.getJustificacion().getText();
       respon = vista.getId_empleado().getText();
       nombreBien=vista.getNombreBien().getText();
       id=vista.getId_bien().getText();
       siniestro=vista.getId_ts().getText();
    }
}