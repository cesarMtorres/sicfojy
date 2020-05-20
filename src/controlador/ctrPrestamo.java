/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ModBien;
import modelo.ModEstudiante;
import modelo.ModeloBitacora;
import java.text.ParseException;
//import net.sf.jasperreports.engine.JRException;
import vista.CatEstudiante;
import vista.CatBien;
import vista.Prestamo;
import modelo.ModCatedra;
import modelo.ModPrestamo;
import modelo.ModModulo;
import modelo.ModProfesor;
import modelo.ModTipoInstrumento;
import net.sf.jasperreports.engine.JRException;
import vista.CatTipoInstrumento;
import vista.Movimiento_Bien;
import vista.Principal;
import vista.RenovacionPrestamo;

/**
 *
 * @author cesar
 */
public class ctrPrestamo extends AbstractAction implements ActionListener {
    public Prestamo vista;
    private int acc;
    Date hoy= new Date();
    private ModeloBitacora bitacora;
    ModCatedra modeloCat;
    CatTipoInstrumento catbien;
    RenovacionPrestamo rp;
    //modelo datos pesonales
    ModEstudiante modeloEst=new ModEstudiante();
    ModProfesor modeloPro=new ModProfesor();
    ModModulo modelomd=new ModModulo();
    ModPrestamo modelo=new ModPrestamo();
    ModBien modelobien=new ModBien();
    ModTipoInstrumento modeloTi=new ModTipoInstrumento();
    private CatEstudiante vcatEstudiante;

    // VARIABLE DE INSTRUMENTOS 
    
    String instrumento="";
    
    public ctrPrestamo(Prestamo vista) {
        this.vista = vista;
        modeloCat=new ModCatedra();
        modeloPro=new ModProfesor();
        modelomd=new ModModulo();
        modeloEst=new ModEstudiante();
        bitacora= new ModeloBitacora();
    }
    
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getBtnRegistrar()){
             if("REGISTRAR".equals(vista.btnRegistrar)){
             Registrar();
            System.out.println("Botón incluir...");
             }else if("AGREGAR".equals(vista.btnRegistrar)){
              Agregar();
            System.out.println("Botón agregar...");
             }

        }else if(evento.getSource()==vista.getBtnCancelar()){
            System.out.println("Botón SALIR...");
        Cancelar();
        }else if(evento.getSource()==vista.getBtnEliminar()){
            System.out.println("Botón eliminar...");
        Eliminar();
        }
        else if(evento.getSource()==vista.getBtnInstrumento()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                catbien = new CatTipoInstrumento(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            catbien.setVisible(true);

            if(catbien.tabCatalago.isRowSelected(catbien.tabCatalago.getSelectedRow()) ){
                codigobus = catbien.tabCatalago.getValueAt(catbien.tabCatalago.getSelectedRow(),1).toString(); // obtiene el serial
                acc =catbien.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modeloTi =new ModTipoInstrumento();
           
            if (codigobus != null) {
                
                boolean existe = modeloTi.Consultar(codigobus);
                if (existe) {
                    
                    vista.id_bien.setText(modeloTi.getId());
                    vista.instrumento.setText(modeloTi.getNombre());
                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Instrumento" + " no encontrado", "Informacion.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
    }
     

    public void Registrar(){
 if("".equals(vista.observacion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        } else if("".equals(vista.catedra.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo catedra no puede estar vacio");
        }else if("".equals(vista.profesor.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo profesor no puede estar vacio");
        }else if("".equals(vista.estudiante.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo estudiante no puede estar vacio");
        }else if("".equals(vista.btnTipoPrestamo.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo tipo prestamo no puede estar vacio");
        }else if("".equals(vista.instrumento.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo instrumento no puede estar vacio");
        }else if(vista.fechaEmision.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha emision no puede estar vacio");
        }else if(vista.fechaDevolucion.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha devolucion no puede estar vacio");
        }else{
        String just = vista.getObservacion().getText();
        String respon =vista.getId_responsable().getText();
        String estudiante = vista.getId_estudiante().getText();
        String catedra = vista.getId_catedra().getText();
        String cantidad = vista.getCantidad().getText().trim();
   //     String instrumento = vista.getId_instrumento().getText();
        String tipoprestamo=vista.id_tp.getText();
        Date fechae=vista.getFechaEmision().getDate();
        Date fechad=vista.getFechaDevolucion().getDate();
        String id=vista.getId_bien().getText();
        String nombreBien=vista.getInstrumento().getText();
        System.out.println("JUS :"+just+"RESPON: "+respon+"FECHAE: "+fechae+"FECHAD: "+fechad+"ESTUDIANTE: "+estudiante+" DID BIEN: "+id+"CATEDRA: "+catedra+"TIPO PRESTAMO: "+tipoprestamo+"CANTIDA: "+cantidad);
        modelo = new ModPrestamo(just,respon,fechae,fechad,estudiante,id,catedra,tipoprestamo,cantidad);

        boolean incluido=modelo.RegistrarPrestamo(nombreBien);
       if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO MOVIMIENTO PRESTAMO "+just+" "+respon+" "+nombreBien+" "+cantidad+" ";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
        }
     
    }//fin de codigoIncluir
    
    public void Modificar(){
        int cantidadva = Integer.parseInt(vista.cantidad.getText());
     if("".equals(vista.catedra.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo modulo no puede estar vacio");
        } else if("".equals(vista.id_bien.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo programa no puede estar vacio");
        }else if("".equals(vista.profesor.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo profesor no puede estar vacio");
        }else if("".equals(vista.observacion.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        }else if(vista.fechaEmision.getDate()==null || vista.fechaDevolucion.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha no puede estar vacio");
        }else if(cantidadva<=0) {
            JOptionPane.showMessageDialog(vista, "El campo cantidad no puede ser menor o igual a Zero (0) ");
        }
        else{
            String id=vista.id_bien.getText();
            String nombreBien=vista.instrumento.getText();
            String just=vista.observacion.getText();
            String respon=vista.id_responsable.getText();
            Date fechae=vista.fechaEmision.getDate();
            Date fechad=vista.fechaDevolucion.getDate();
            String estudiante=vista.id_estudiante.getText();
            String catedra=vista.id_catedra.getText();
            String tipoprestamo=vista.id_tp.getText();
            String cantidad=vista.id_bien.getText();
             modelo.ConsultarCantidadBien(id);
             String myString = modelo.getTemp();
             int total = Integer.parseInt(myString);
             
        modelo = new ModPrestamo(just,respon,fechae,fechad,estudiante,id,catedra,tipoprestamo,cantidad);
         boolean modificado=modelo.Modificar(total,nombreBien);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "MODIFICACION EXITOSA");
            limpiar();
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICAR MOVIMIENTO PRESTAMO "+id+" "+just+" "+respon+" "+nombreBien+" ";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
        }


    }
    
    
    public void Eliminar(){
          if("".equals(vista.id_bien.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo justificacion no puede estar vacio");
        }
        else{
        String nombrebien = vista.getInstrumento().getText();
        String id = vista.getId_bien().getText();
        String responsable = vista.getId_responsable().getText();
        
        modelo = new ModPrestamo();
        boolean eliminado=modelo.Eliminar(nombrebien,id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL MOVIMIENTO PRESTAR "+id+" "+nombrebien+" "+responsable+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        
        }
    }//fin de codigoEliminar

    
     public void LlenarCatedra() {
         
        vista.getCatedra().removeAllItems();
        ArrayList<String> datos_1 = modeloCat.CargarCatedra();
        if(datos_1 == null){

        }else{
        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getCatedra().addItem(cargoList);
        }
        }
        
    }
   /* 
     public void LlenarModulo() {
         
        vista.getModulo().removeAllItems();
        ArrayList<String> datos_1 = modelomd.CargarModulo();
        if(datos_1 == null){

        }else{
        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getModulo().addItem(cargoList);
        }
        }
        
    }

*/
    
     public void LlenarTipoPrestamo() {
         
        vista.getBtnTipoPrestamo().removeAllItems();
        ArrayList<String> datos_1 = modelo.CargarTipoPrestamo();
        if(datos_1 == null){

        }else{
        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getBtnTipoPrestamo().addItem(cargoList);
        }
        }
        
    }
               public void LlenarEstudiante() {
         
        vista.getEstudiante().removeAllItems();
        ArrayList<String> datos_1 = modeloEst.CargarEstudiante();
  
        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getEstudiante().addItem(cargoList);
        }

    }
       public void LlenarProfesor() {
         
        vista.getProfesor().removeAllItems();
        ArrayList<String> datos_1 = modeloPro.CargarProfesor();
        if(datos_1 == null){
            
        }else{
        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getProfesor().addItem(cargoList);
        }
        }   
    }     
  /*
             public void ListadoMovimiento(){
         modelo = new ModPrestamo();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado Prestamo");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ESTADO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA"});
          vista.getTabCatalago().setModel(model);
  
          
           
        }else{
            System.out.println("LISTADO Prestamo");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"ESTADO","RESPONSABLE","JUSTIFICACION","BIEN","FECHA"});
            vista.getTabCatalago().setModel(model);
        }
        
    }
       public void LlenarProfesor(String dat) {
        vista.getProfesor().removeAllItems();
        ArrayList<String> datos_1 = modeloPro.CargarProfesor(dat);

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getProfesor().addItem(cargoList);
        }
        
    }
           public void LlenarEstudiante(String dat) {
        vista.getEstudiante().removeAllItems();
        ArrayList<String> datos_1 = modeloEst.CargarEstudiante(dat);

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getEstudiante().addItem(cargoList);
        }
        
    }
*/
       
     public void limpiar() {
         vista.getObservacion().setText("");
        vista.getId_responsable().setText("");
        vista.getProfesor().setSelectedItem("SIN PROFESOR");
        vista.getEstudiante().setSelectedItem("SIN ESTUDIANTE");
        vista.getCatedra().setSelectedItem("SIN CATEDRA");
        vista.getBtnTipoPrestamo().setSelectedItem("SIN TIPO PRESTAMO");
        vista.getId_estudiante().setText("");
        vista.getInstrumento().setText("");
        vista.getId_catedra().setText("");
        vista.getCantidad().setText("");
   //     String instrumento = vista.getId_instrumento().getText();
        vista.id_tp.setText("");

        vista.getFechaEmision().setDate(hoy);
        vista.getFechaDevolucion().setDate(hoy);
        vista.getId_bien().setText("");

    }
     /*
     public void click_tabla(java.awt.event.MouseEvent evt) throws ParseException{
            
        limpiar();
        int cont = vista.getTabCatalago().rowAtPoint(evt.getPoint());
        if(cont >-1){
            vista.getId_bien().setText(String.valueOf(vista.getTabCatalago().getValueAt(cont, 3)));
            modelo.ConsultarTabla(vista.getId_bien().getText());
            vista.id_bien.setText(modelo.getId());
            vista.cantidad.setText(modelo.getCantidad());
            vista.observacion.setText(modelo.getJustificacion());
            vista.catedra.setSelectedItem(modelo.getCatedra());
            vista.profesor.setSelectedItem(modelo.getResponsable());
            vista.estudiante.setSelectedItem(modelo.getEstudiante());
            vista.btnTipoPrestamo.setSelectedItem(modelo.getTipoprestamo());
            vista.dias.setText(modelo.getDias());
            
            vista.instrumento.setText(modelo.getBien());
            vista.fechaEmision.setDate(modelo.getFechae());
            vista.fechaDevolucion.setDate(modelo.getFechad());
            int totaldias=calcularDias(vista.fechaDevolucion.getDate(),hoy);
            
            if (totaldias<=0){
               rp = new RenovacionPrestamo(new javax.swing.JFrame(), true);
                                
                    rp.setVisible(true); 
            }
            vista.btnRegistrar.setEnabled(false);
            vista.btnModificar.setEnabled(true);
            vista.btnEliminar.setEnabled(true);
            
        }
    }
     
     */
public void Cancelar(){
    
         int velim=0;
        velim = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta Seguro que quiere Salir?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (velim == 0) {
         vista.dispose();
        }
     }

public void Agregar(){
            instrumento = vista.id_bien.getText();
}/*
 public void cargarTablaAmortizacion(){
        int count;
          count = Integer.parseInt(vista.cantidad.getText());
          if (count==0){
          count=1;
          }
          
          //ahora viene la carga de la tabla....
          
          DefaultTableModel tabla_amortizacion =(DefaultTableModel) vista.getTabCatalago().getModel();

           for(int i=tabla_amortizacion.getRowCount()-1;i>=0;i--){  
              tabla_amortizacion.removeRow(i);
           }
          for (int j = 0; j < count; j++) {
              Object[] row = new Object[2];
              row[0] = vista.instrumento.getText();
              row[1] = vista.cantidad.getText();
              tabla_amortizacion.addRow(row);
          }
          
          
      
      } 
 /*
  public void EliminarTabla(){
          
          DefaultTableModel tabla_amortizacion = (DefaultTableModel) vista.getTabCatalago().getModel();
           for(int i=tabla_amortizacion.getRowCount()-1;i>=0;i--){  
              tabla_amortizacion.removeRow(i);
          }    
          
          
      
      }
 
 */
  
  public int calcularDias(Date fecha1,Date fecha2) throws ParseException{
                int dias;
 

		dias=(int) ((fecha1.getTime()-fecha2.getTime())/86400000);

 

		return dias;
                
                
            }
}