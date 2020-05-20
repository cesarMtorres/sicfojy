/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static jdk.nashorn.internal.objects.NativeString.length;
import modelo.ModProfesor;
//import net.sf.jasperreports.engine.JRException;
import vista.CatProfesor;
import modelo.ModeloBitacora;
import vista.Profesor;
import modelo.ModCatedra;
import vista.Principal;

/**
 *
 * @author cesar
 */
public class ctrProfesor extends AbstractAction implements ActionListener {
    public Profesor vista;
    
    ModCatedra mcatedra=new ModCatedra();
    //modelo datos pesonales
    public ModProfesor modelo;
    private CatProfesor vcatProfesor;
    ModeloBitacora bitacora;
    private int acc;

    public ctrProfesor(Profesor vista) {
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
        }else if(evento.getSource()==vista.getBtnTelefono()){
            System.out.println("Botón agregar otro telefono...");
        }else if(evento.getSource()==vista.getBtnCatalago()){
              System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatProfesor = new CatProfesor(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatProfesor.setVisible(true);

            if(vcatProfesor.tabCatalago.isRowSelected(vcatProfesor.tabCatalago.getSelectedRow()) ){
                codigobus = vcatProfesor.tabCatalago.getValueAt(vcatProfesor.tabCatalago.getSelectedRow(),0).toString(); // obtiene el nombre
                acc =vcatProfesor.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModProfesor();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                vista.getId_profesor().setText(modelo.getId_prof());
                if (existe) {
                    String[][] informacion = modelo.CatedraProfesor(vista.getId_profesor().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Lista"});
            vista.getTabcatedra().setModel(model);   
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Lista"});
            vista.getTabcatedra().setModel(model);
        }
                vista.getNombre().setText(modelo.getNombre());
                vista.getCedula().setText(modelo.getCedula());
                vista.getApellido().setText(modelo.getApellido());
                vista.getTelefono().setText(modelo.getTelefono());
                vista.getDireccion().setText(modelo.getDireccion());
                vista.getCatedra().setSelectedItem(modelo.getCatedra());
               // vista.getListaCatedra().setText(modelo.getCatedra()+"\n");
                vista.getSexo().setSelectedItem(modelo.getSexo());
                vista.getFechaNac().setDate(modelo.getFechaNac());
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Estudiante" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        }
        
        
    }
     
    public void Consultar(){

        modelo = new ModProfesor();
            boolean encontrado = modelo.Consultar(vista.getCedula().getText());
            if (encontrado){
                vista.getNombre().setText(modelo.getNombre());
                vista.getCedula().setText(modelo.getCedula());
                vista.getApellido().setText(modelo.getApellido());
                vista.getTelefono().setText(modelo.getTelefono());
                vista.getDireccion().setText(modelo.getDireccion());
             //   vista.getListaCatedra().setText(modelo.getCatedra());
                vista.getCatedra().setSelectedItem(modelo.getCatedra());
                
                vista.getSexo().setSelectedItem(modelo.getSexo());
                vista.getFechaNac().setDate(modelo.getFechaNac());
                //Acción de los botones y cajas de texto
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnEliminar().setEnabled(true);
                vista.getBtnRegistrar().setEnabled(true);
                
            }else{
                vista.getBtnRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
            habilitar();
    }
    
    public void Registrar(){
            if("".equals(vista.cedula.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo cedula no puede estar vacio");
        } else if("".equals(vista.nombre.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.apellido.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo apellido no puede estar vacio");
        } else if(vista.fechaNac.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha de nacimiento no puede estar vacio");
        } else if("".equals(vista.direccion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo direccion no puede estar vacio");
        }else if("".equals(vista.telefono.getText())) {
           JOptionPane.showMessageDialog(vista, "El campo telefono no puede estar vacio");
        } else if("".equals(vista.catedra.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo catedra no puede estar vacio");
        }
         else {
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        String catedra =(String) vista.getCatedra().getSelectedItem();
         
        //int edad = Integer.parseInt(vista.getTxtEdad().getText());
        modelo = new ModProfesor(cedula, nombre,apellido,telefono,direccion,sexo,catedra,fechaNac);
        boolean incluido=modelo.RegistrarEstudiante();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL PROFESOR "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
 public void Modificar(){
                 if("".equals(vista.cedula.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo cedula no puede estar vacio");
        } else if("".equals(vista.nombre.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo nombre no puede estar vacio");
        } else if("".equals(vista.apellido.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo apellido no puede estar vacio");
        } else if(vista.fechaNac.getDate()==null) {
            JOptionPane.showMessageDialog(vista, "El campo fecha de nacimiento no puede estar vacio");
        } else if("".equals(vista.direccion.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo direccion no puede estar vacio");
        }else if("".equals(vista.telefono.getText())) {
           JOptionPane.showMessageDialog(vista, "El campo telefono no puede estar vacio");
        } else if("".equals(vista.catedra.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo catedra no puede estar vacio");
        }
         else {

        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        String catedra =(String) vista.getCatedra().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        modelo = new ModProfesor(cedula, nombre,apellido,telefono,direccion,sexo,catedra,fechaNac);
        boolean modificado=modelo.ModificarEstudiante(cedula);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL PROFESOR "+nombre+" "+cedula+"";
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
        String cedula = vista.getCedula().getText();
        String nombre = vista.getCedula().getText();
        String id = vista.getId();
        
        modelo = new ModProfesor();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINÓ EL PROFESOR "+nombre+" "+cedula+"";
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
        vista.nacion.setSelectedItem("V");
        vista.cedula.setText("");
        vista.nombre.setText("");
        vista.apellido.setText("");
        vista.direccion.setText("");
        vista.telefono.setText("");
        vista.sexo.setSelectedItem("");
        vista.catedra.setSelectedItem("");
        vista.idCatedra.setText("");
        //vista.listaCatedra.setText("");
        vista.fechaNac.setDateFormatString("");
    }
         public void habilitar(){
        vista.getCedula().setEnabled(false);
        vista.nacion.setEnabled(true);
        vista.cedula.setEnabled(true);
        vista.nombre.setEnabled(true);
        vista.apellido.setEnabled(true);
        vista.direccion.setEnabled(true);
        vista.telefono.setEnabled(true);
        vista.sexo.setEnabled(true);
    }//fin de habilitar
         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.apellido.setEnabled(activ);
        vista.telefono.setEnabled(activ);
        vista.direccion.setEnabled(activ);
        vista.nacion.setEnabled(activ);
        vista.sexo.setEnabled(activ);
    }

      
        public void LLenarCatedra() {
        vista.getCatedra().removeAllItems();
        ArrayList<String> datos_1 = mcatedra.CargarCatedra();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getCatedra().addItem(cargoList);
            
        }
        }
         public void cargarTablaAmortizacion(){
        int count=1;
   
          
          //ahora viene la carga de la tabla....
          
          DefaultTableModel tabla_amortizacion =(DefaultTableModel) vista.getTabcatedra().getModel();
          for (int j = 0; j < count; j++) {
              Object[] row = new Object[1];
              row[0] = vista.catedra.getSelectedItem();
              tabla_amortizacion.addRow(row);
          }
          
          
      
      }
}