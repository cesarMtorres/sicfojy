/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import modelo.ModProfesor;
import modelo.ModRepararBien;
import modelo.ModeloBitacora;
//import net.sf.jasperreports.engine.JRException;
import vista.CatEmpleado;
import vista.Repara_bien;

import vista.CatBien;
import vista.CatProfesor;
import vista.Principal;
/**
 *
 * @author cesar
 */
public class ctrRepararBien extends AbstractAction implements ActionListener {
    public Repara_bien vista;
    private int acc;
    private ModeloBitacora bitacora;
    CatBien catbien;
    //modelo datos pesonales
    ModProfesor modeloEmp=new ModProfesor();
    ModRepararBien modelo=new ModRepararBien();
    private CatProfesor vcatEmpleado;

    public ctrRepararBien(Repara_bien vista) {
        this.vista = vista;
        modelo=new ModRepararBien();
        bitacora= new ModeloBitacora();
    }
    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getBtnRegistrar()){
            Registrar();
            System.out.println("Botón incluir...");
        }else if(evento.getSource()==vista.getBtnCancelar()){
            System.out.println("Botón SALIR...");
            Cancelar();
        }else if(evento.getSource()==vista.getBtnModificar()){
            System.out.println("Botón modificar...");
            Modificar();
        }else if(evento.getSource()==vista.getBtnEliminar()){
            System.out.println("Botón eliminar...");
            Eliminar();
        }else if(evento.getSource()==vista.getBtnCatalago()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatEmpleado = new CatProfesor(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrRepararBien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatEmpleado.setVisible(true);

            if(vcatEmpleado.tabCatalago.isRowSelected(vcatEmpleado.tabCatalago.getSelectedRow()) ){
                codigobus = vcatEmpleado.tabCatalago.getValueAt(vcatEmpleado.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatEmpleado.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);

           
            if (codigobus != null) {
                
                boolean existe = modeloEmp.Consultar(codigobus);
                if (existe) {
                    vista.reponsable.setText(modeloEmp.getNombre());
                    vista.getId_empleado().setText(modeloEmp.getId_prof());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Responsable " + " no encontrado ", "Informacion.",
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
                Logger.getLogger(ctrRepararBien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            catbien.setVisible(true);

            if(catbien.tabCatalago.isRowSelected(catbien.tabCatalago.getSelectedRow()) ){
                codigobus = catbien.tabCatalago.getValueAt(catbien.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =catbien.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModRepararBien();
           
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
        } else if("".equals(vista.reponsable.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo responsable no puede estar vacio");
        }
        else{
            
        String just = vista.getJustificacion().getText();
        String respon = vista.getId_empleado().getText();
        String tipo_siniestro =(String) vista.getId_ts().getText();
        System.out.println(tipo_siniestro);
        String nombreBien=vista.getNombreBien().getText();
        Date fecha=vista.getFecha().getDate();
        String id=vista.getId_bien().getText();
       
        modelo = new ModRepararBien(just,respon,fecha,id,tipo_siniestro);
        boolean incluido=modelo.RegistrarRepararBien();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            limpiar();
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO MOVIMIENTO REPARAR"+just+" "+respon+" "+nombreBien+" ";
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
        } else if(vista.fecha.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha  no puede estar vacio");
        }  else if(vista.tipo_siniestro.getSelectedItem()=="") {
            JOptionPane.showMessageDialog(vista, "El campo tipo siniestro no puede estar vacio");
        } 
         else {

        String just = vista.getJustificacion().getText();
        String respon = vista.getId_empleado().getText();
        String tipo_siniestro =(String) vista.getTipo_siniestro().getSelectedItem();
        String nombreBien=vista.getNombreBien().getText();
        Date fecha=vista.getFecha().getDate();
        String id=vista.getId_bien().getText();
        modelo = new ModRepararBien(just,respon,fecha,id,tipo_siniestro);
        boolean modificado=modelo.ModificarRepararBien(id);
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
        } else if(vista.fecha.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha  no puede estar vacio");
        }  else if(vista.tipo_siniestro.getSelectedItem()=="") {
            JOptionPane.showMessageDialog(vista, "El campo tipo siniestro no puede estar vacio");
        }else{
        String nombrebien = vista.getNombreBien().getText();
        String responsable = vista.getReponsable().getText();
        Date fecha = vista.getFecha().getDate();
        
        
        String id = vista.getId_bien().getText();
        
        modelo = new ModRepararBien();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL MOVIMIENTO REPARAR "+nombrebien+" "+responsable+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        
        }
    }//fin de codigoEliminar
    

   
     public void limpiar() {
        vista.justificacion.setText("");
        vista.reponsable.setText("");
        vista.nombreBien.setText("");
        vista.id_empleado.setText("");
        vista.id_bien.setText("");
        vista.fecha.setDateFormatString("");

    }
     
              public void LlenarTipoS() {
         
        vista.getTipo_siniestro().removeAllItems();
        ArrayList<String> datos_1 = modelo.CargarTipoS();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getTipo_siniestro().addItem(cargoList);
        }
    }
                                  public void ListadoMovimiento(){
         modelo = new ModRepararBien();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado ASIGNAR");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"CODIGO","ESTADO","SINIESTRO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA"});
          
            vista.getTabCatalago().setModel(model);
            vista.getTabCatalago().getColumnModel().getColumn(0).setPreferredWidth(100);
          vista.getTabCatalago().getColumnModel().getColumn(1).setPreferredWidth(100);
          vista.getTabCatalago().getColumnModel().getColumn(2).setPreferredWidth(150);
          vista.getTabCatalago().getColumnModel().getColumn(3).setPreferredWidth(200);
          vista.getTabCatalago().getColumnModel().getColumn(4).setPreferredWidth(600);
          vista.getTabCatalago().getColumnModel().getColumn(5).setPreferredWidth(100);
          vista.getTabCatalago().getColumnModel().getColumn(6).setPreferredWidth(200);
          
           
        }else{
            System.out.println("LISTADO ASIGNAR");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"CODIGO","ESTADO","SINIESTRO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA"});
            vista.getTabCatalago().setModel(model);
        }
        
    }
        public void click_tabla(java.awt.event.MouseEvent evt){
            
        limpiar();
        int cont = vista.getTabCatalago().rowAtPoint(evt.getPoint());
        if(cont >-1){
            vista.getSerial().setText(String.valueOf(vista.getTabCatalago().getValueAt(cont, 0)));
            modelo.ConsultarNombre(vista.getSerial().getText());
            vista.id_bien.setText(modelo.getId());
            vista.nombreBien.setText(modelo.getNombre());
            vista.reponsable.setText(modelo.getNombreResp());
            vista.tipo_siniestro.setSelectedItem(modelo.getTipo_siniestro());
            vista.justificacion.setText(modelo.getJustificacion());
            String fecha1 = new SimpleDateFormat("dd-MM-yyyy").format(modelo.getFecha());
           // vista.fecha.setText(fecha1);
            //vista.fecha.setDate(modelo.getFecha());
            vista.btnCatalagoBien.setEnabled(false);
            vista.btnCatalago.setEnabled(false);
            vista.btnRegistrar.setEnabled(false);
            vista.btnModificar.setEnabled(true);
            vista.btnEliminar.setEnabled(true);
            
        }
    }
        public void Cancelar(){
        int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        } vista.dispose();
     }
}