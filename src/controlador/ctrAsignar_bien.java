/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ModAsignar;
import modelo.ModAsignarBien;
import modelo.ModBien;
import modelo.ModeloBitacora;
import vista.Asignar_bien;
import vista.CatBien;
import modelo.ModModulo;
import modelo.ModProfesor;
import modelo.ModPrograma;
import vista.CatProfesor;
import vista.CatPrograma;
import vista.CatAsignarBien;
import vista.Movimiento_Bien;
import vista.Principal;
import vista.RenovacionPrestamo;

/**
 *
 * @author betty
 */
public class ctrAsignar_bien extends AbstractAction implements ActionListener {
    

    String fecIUsu = "";
    String just ="";
    String respon ="";
    String nombreBien="";
    String id="";
    String cantidad="";
   
    String id_modulo="";
    String id_programa="";

    //modelo datos pesonales
    public ModModulo modeloM;
    public ModBien modelob;
    public ModAsignar modelo;
    public ModAsignarBien modeloC;
    Date hoy= new Date();
    CatBien vbien;
    ModPrograma modeloPro;
    ModProfesor modeloprof;
    CatProfesor vprof;
    RenovacionPrestamo rp;
    CatAsignarBien asig;
    CatPrograma vprog;
    public Asignar_bien vista;
    private ModeloBitacora bitacora;
    private int acc;
    public ctrAsignar_bien(Asignar_bien vista) {
        this.vista = vista;
        modelo=new ModAsignar();
        bitacora= new ModeloBitacora();
        modeloM=new ModModulo();
        modeloPro=new ModPrograma();
    }
    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getBtnEliminar()){
             Eliminar();
         }else if(evento.getSource()==vista.getBtnCancelar()){
             Cancelar();
         }else if(evento.getSource()==vista.getBtnCatalago()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                asig = new CatAsignarBien(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrAsignar_bien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            asig.setVisible(true);

            if(asig.tabCatalago.isRowSelected(asig.tabCatalago.getSelectedRow()) ){
                codigobus = asig.tabCatalago.getValueAt(asig.tabCatalago.getSelectedRow(),0).toString(); // obtiene el serial
                acc =asig.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
           
            if (codigobus != null) {
                modeloC=new ModAsignarBien();
                boolean existe = modeloC.Consultar(codigobus);
                if (existe) {
                     Date fechact = new Date();
                    vista.nombreBien.setText(modeloC.getNombrebien());
                    vista.getId_bien().setText(modeloC.getId());
                    vista.getProfesor().setText(modeloC.getResponsable());
                    vista.getId_empleado().setText(modeloC.getId_empleado());
                    vista.getCantidad().setText(modeloC.getCantidad());
                    vista.getModulo().setSelectedItem(modeloC.getModelo());
                    vista.getPrograma().setSelectedItem(modeloC.getPrograma());
                    vista.getJustificacion().setText(modeloC.getJustificacion());
                 //   fechact = modeloC.getFecha();
                    vista.getTxtfecing().setText(modeloC.getFecha());
                    System.out.println("dias pues:"+fechact);
                    int totaldias=calcularDias(fechact,hoy);

            if (totaldias<0){
                System.out.println("dias pues:"+totaldias);
               rp = new RenovacionPrestamo(new javax.swing.JFrame(), true);
                               
                    rp.setVisible(true); 
            }
      
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Asignar bien" + " no encontrado ", "Informacion.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
         else if(evento.getSource()==vista.getBtnBien()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vbien = new CatBien(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrAsignar_bien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vbien.setVisible(true);

            if(vbien.tabCatalago.isRowSelected(vbien.tabCatalago.getSelectedRow()) ){
                codigobus = vbien.tabCatalago.getValueAt(vbien.tabCatalago.getSelectedRow(),1).toString(); // obtiene el serial
                acc =vbien.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
           
            if (codigobus != null) {
                modelob=new ModBien();
                boolean existe = modelob.Consultar(codigobus);
                if (existe) {
                    vista.nombreBien.setText(modelob.getNombre());
                    vista.getId_bien().setText(modelob.getId());
      
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Bien" + " no encontrado ", "Informacion.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
         else if(evento.getSource()==vista.getBtnProfesor()){
            System.out.println(" catalago profesor...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vprof = new CatProfesor(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrAsignar_bien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vprof.setVisible(true);

            if(vprof.tabCatalago.isRowSelected(vprof.tabCatalago.getSelectedRow()) ){
                codigobus = vprof.tabCatalago.getValueAt(vprof.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vprof.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
           
            if (codigobus != null) {
                modeloprof=new ModProfesor();
                boolean existe = modeloprof.Consultar(codigobus);
                if (existe) {
                    vista.profesor.setText(modeloprof.getNombre());
                    vista.getId_empleado().setText(modeloprof.getId_prof());
      
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Profesor" + " no encontrado ", "Informacion.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else if(evento.getSource()==vista.getBtnRegistrar()){
            if("".equals(vista.justificacion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        } else if("".equals(vista.profesor.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo responsable no puede estar vacio");
        }
        else{
        asignaval();
        int cantidadBD = Integer.parseInt(vista.getCambiarCantidad().getText());
        int cantidadInput = Integer.parseInt(vista.getCantidad().getText());
        int total = (cantidadBD-cantidadInput);
      
      //  modelo = new ModAsignar(total,cantidad,just,respon,fecIUsu,id_modulo,id_programa,id);
        boolean incluido=modelo.Registrar(nombreBien,total,cantidad,just,respon,fecIUsu,id_modulo,id_programa,id);
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO MOVIMIENTO ASIGNAR "+just+" "+respon+" "+nombreBien+" "+cantidad+" ";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
}else if(evento.getSource()==vista.getBtnModificar()){
     if("".equals(vista.modulo.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo modulo no puede estar vacio");
        } else if("".equals(vista.id_programa.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo programa no puede estar vacio");
        }else if("".equals(vista.profesor.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo profesor no puede estar vacio");
        }else if("".equals(vista.justificacion.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        }else if(vista.txtfecing.getText()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha no puede estar vacio");
        }
        else{
             asignaval();
             modelo.ConsultarCantidadBien(id);
             String myString = modelo.getTemp();
             int total = Integer.parseInt(myString);
             
      //   modelo = new ModAsignar(total,cantidad,just,respon,fecIUsu,id_modulo,id_programa,id);
         boolean modificado=modelo.Modificar(nombreBien,cantidad,just,respon,fecIUsu,id_modulo,id_programa,id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "MODIFICACION EXITOSA");
            limpiar();
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICAR MOVIMIENTO ASIGNAR "+id+" "+just+" "+respon+" "+nombreBien+" ";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
        }

}
}
       
        public void Eliminar(){
          if("".equals(vista.justificacion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        } else if("".equals(vista.nombreBien.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre bien no puede estar vacio");
        } else if(vista.txtfecing.getText()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha  no puede estar vacio");
        }
        else{
        String nombrebien = vista.getNombreBien().getText();
        String responsable = vista.getProfesor().getText();
       asignaval();
        
        modelo = new ModAsignar();
        boolean eliminado=modelo.Eliminar(nombreBien,id,cantidad);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL MOVIMIENTO SIGNAR "+nombrebien+" "+responsable+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        
        }
    }//fin de codigoEliminar

        
    
  

     //   codigoBotonLimpiar();
    
         public void Cancelar(){
        int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
            vista.dispose();
        }
     }
      public void limpiar() {
        vista.justificacion.setText("");
        vista.profesor.setText("");
        vista.id_bien.setText("");
        vista.id_empleado.setText("");
        vista.id_programa.setText("");
        vista.nombreBien.setText("");
        vista.programa.setSelectedItem("");
        vista.cantidad.setText("");
        vista.modulo.setSelectedItem("SELECCIONAR");
        //vista.id_empleado.setText("");
        vista.txtfecing.setText("");
        vista.setVisible(true);
       // vista.fecha.setDateFormatString("");

    }
         public void habilitar(){

    }//fin de habilitar
         
    /*    public void actdesact(boolean activ) {

    }
                     public void ListadoMovimiento(){
         modelo = new ModAsignar();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado ASIGNAR");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ESTADO","SINIESTRO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA","CANTIDAD"});
          
            vista.getTabCatalago().setModel(model);
          vista.getTabCatalago().getColumnModel().getColumn(0).setPreferredWidth(100);
          vista.getTabCatalago().getColumnModel().getColumn(1).setPreferredWidth(150);
          vista.getTabCatalago().getColumnModel().getColumn(2).setPreferredWidth(200);
          vista.getTabCatalago().getColumnModel().getColumn(3).setPreferredWidth(600);
          vista.getTabCatalago().getColumnModel().getColumn(4).setPreferredWidth(100);
          vista.getTabCatalago().getColumnModel().getColumn(5).setPreferredWidth(200);
          
           
        }else{
            System.out.println("LISTADO ASIGNAR");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"ESTADO","SINIESTRO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA","CANTIDAD"});
            vista.getTabCatalago().setModel(model);
        }
        
    } */
         public void LlenarModulo() {
         
        vista.getModulo().removeAllItems();
        ArrayList<String> datos_1 = modeloM.CargarModulo();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getModulo().addItem(cargoList);
        }
    }
         
     /*    public void click_tabla(java.awt.event.MouseEvent evt){
            
        limpiar();
        int cont = vista.getTabCatalago().rowAtPoint(evt.getPoint());
        if(cont >-1){
             Date fechact = new Date();
             SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            vista.getId_bien().setText(String.valueOf(vista.getTabCatalago().getValueAt(cont, 4)));
            modelo.ConsultarTabla(vista.getId_bien().getText());
            vista.id_bien.setText(modelo.getId());
            vista.nombreBien.setText(modelo.getNombre());
            vista.id_programa.setText(modelo.getId_programa());
            vista.profesor.setText(modelo.getResponsable());
            vista.cantidad.setText(modelo.getCantidad());
            vista.id_empleado.setText(modelo.getId_responsable());
            vista.modulo.setSelectedItem(modelo.getModulo());
            vista.programa.setSelectedItem(modelo.getPrograma());
            vista.justificacion.setText(modelo.getJustificacion());
            fechact = modelo.getFecha();
            int totaldias=calcularDias(fechact,hoy);
            
            if (totaldias<=0){
                System.out.println("dias pues:"+totaldias);
               rp = new RenovacionPrestamo(new javax.swing.JFrame(), true);
                               
                    rp.setVisible(true); 
            }
            vista.txtfecing.setText(formateador.format(fechact).toString());
            vista.txtfecing.setVisible(true);

            vista.btnRegistrar.setEnabled(false);
            vista.btnModificar.setEnabled(true);
            vista.btnEliminar.setEnabled(true);
            
        }
    }
         
         */
             public void Llenar_Programa(String dat) {
        vista.getPrograma().removeAllItems();
        ArrayList<String> datos_1 = modeloPro.CargarPrograma(dat);

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getPrograma().addItem(cargoList);
        }
        
    }
             
                 public void asignaval() {
        fecIUsu = vista.txtfecing.getText();
       just = vista.getJustificacion().getText();
       respon = vista.getId_empleado().getText();
       nombreBien=vista.getNombreBien().getText();
       id=vista.getId_bien().getText();
       cantidad=vista.getCantidad().getText();
       id_modulo=vista.getId_modulo().getText();
       id_programa=vista.getId_programa().getText();


    }
           public int calcularDias(Date fecha1,Date fecha2){
                int dias;
		dias=(int) ((fecha1.getTime()-fecha2.getTime())/86400000);
		return dias;      
            }
}
