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
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ModCatedra;
import modelo.ModEstudiante;
import modelo.ModRepresentante;
//import net.sf.jasperreports.engine.JRException;
import vista.CatEstudiante;
import vista.Estudiante;
import modelo.ModeloBitacora;
import vista.CatRepresentanteEST;
import vista.Principal;
import vista.Representante;
/**
 *
 * @author cesar
 */
public class ctrEstudiante extends AbstractAction implements ActionListener {
    public Estudiante vista;
    Validacion validacion;
    ModeloBitacora bitacora;
    Representante repre;
    ModCatedra mcatedra=new ModCatedra();
    ModRepresentante modeloR=new ModRepresentante();
    //modelo datos pesonales
    public ModEstudiante modelo;
    private CatEstudiante vcatEstudiate;
     private int acc;

    public ctrEstudiante(Estudiante vista) {
        this.vista = vista;
        bitacora= new ModeloBitacora();
    }
@Override
     public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getBtnRegistrar()){
            Registrar();
        }else if(evento.getSource()==vista.getBtnNewRepresentante()){
            repre = new Representante(new javax.swing.JFrame(), true);  
            repre.setVisible(true);
        }else if(evento.getSource()==vista.getBtnModificar()){
            Modificar();

        }else if(evento.getSource()==vista.btnEliminar) {
                Eliminar();
        }else if(evento.getSource()==vista.getBtnCancelar()){
            System.out.println("Botón cancelar...");
            Cancelar();
            
        }else if(evento.getSource()==vista.getBtnCatalago()){
            String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatEstudiate = new CatEstudiante(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatEstudiate.setVisible(true);

            if(vcatEstudiate.tabCatalago.isRowSelected(vcatEstudiate.tabCatalago.getSelectedRow()) ){
                codigobus = vcatEstudiate.tabCatalago.getValueAt(vcatEstudiate.tabCatalago.getSelectedRow(),0).toString(); // obtiene la cedula 
                acc =vcatEstudiate.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModEstudiante();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.cedula.setText(modelo.getCedula());
                    vista.nombre.setText(modelo.getNombre());
                    vista.apellido.setText(modelo.getApellido());
                    vista.direccion.setText(modelo.getDireccion());
                    vista.telefono.setText(modelo.getTelefono());
                    vista.id_representante.setText(modelo.getRepresentante());
                    vista.cedulaRepresentante.setText(modelo.getNombrerepre());
                    vista.nacion.setSelectedItem(modelo.getNacion());
                    vista.catedra.setSelectedItem(modelo.getCatedra());
                    vista.sexo.setSelectedItem(modelo.getSexo());
                    vista.getId().setText(modelo.getId());
                    vista.getFechaNac().setDate(modelo.getFechaNac());
                    if((modelo.getStatus().equals("FALSE"))){
                        vista.status.setText("ESTADO INACTIVO: ELIMINACION LOGICA");
                    }else if((modelo.getStatus().equals("TRUE"))){
                        vista.status.setText("ESTADO: ACTIVO");
                    }
                    vista.btnModificar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);
                    vista.btnCancelar.setEnabled(true);
                    
                    //vista.cedula.setEditable(false);
                    
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Estudiante" + " no encontrado ", "ELECTIVA.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            
        }
    /*    else if(evento.getSource()==vista.getBtnRepresentante()){
            String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatRep = new CatRepresentanteEST(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatRep.setVisible(true);

            if(vcatRep.tabCatalago.isRowSelected(vcatRep.tabCatalago.getSelectedRow()) ){
                codigobus = vcatRep.tabCatalago.getValueAt(vcatRep.tabCatalago.getSelectedRow(),0).toString(); // obtiene la cedula 
                acc =vcatRep.tabCatalago.getSelectedRow();
            }else{
                
            }
            
            
        }
*/
        
        
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
        }else if("".equals(vista.id_representante.getText())) {
            JOptionPane.showMessageDialog(vista, "El campo Representante es obligatorio");
        }else if(vista.cedulaRepresentante.getText().length()<7) {
            JOptionPane.showMessageDialog(vista, "Limite de cedula representante");
        }
         else {
        String cedula = vista.getCedula().getText();
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        String telefono = vista.getTelefono().getText();
        String id_representante = vista.getId_representante().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        String nacion =(String) vista.getNacion().getSelectedItem();
        Date fechaNac= vista.getFechaNac().getDate();
        String catedra =vista.id_catedra.getText();
        
        modelo = new ModEstudiante(cedula, nombre,apellido,telefono,direccion,sexo,catedra,fechaNac,nacion,id_representante);
        boolean incluido=modelo.RegistrarEstudiante();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL ESTUDIANTE  "+nombre+" "+cedula+"";
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
        String id_representante = vista.getId_representante().getText();
        String direccion = vista.getDireccion().getText();
        String sexo =(String) vista.getSexo().getSelectedItem();
        String nacion =(String) vista.getNacion().getSelectedItem();
        String catedra =(String) vista.getCatedra().getSelectedItem();
        String id =(String) vista.getId().getText();
        Date fechaNac= vista.getFechaNac().getDate();
        modelo = new ModEstudiante(id,cedula, nombre,apellido,telefono,direccion,sexo,catedra,fechaNac,nacion,id_representante);
        boolean modificado=modelo.ModificarEstudiante(id);
        if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificado Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL USUARIO "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{              
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
        }
        }
    }//fin de codigoModificar
   
 public void Eliminar(){
        String id = vista.getId().getText();
        String nombre = vista.getNombre().getText();
        String cedula = vista.getCedula().getText();
        
        modelo = new ModEstudiante();
        boolean eliminado=modelo.Eliminar(id);
        if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINO EL ESTUDIANTE "+nombre+" "+cedula+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
            
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
        vista.catedra.setSelectedItem("SELECCIONAR");
        vista.cedula.setText("");
        vista.nombre.setText("");
        vista.nacion.setSelectedItem("");
        vista.apellido.setText("");
        vista.direccion.setText("");
        vista.catedra.setSelectedItem("");
        vista.telefono.setText("");
        vista.id.setText("");
        vista.id_representante.setText("");
        vista.cedulaRepresentante.setText("");
        vista.sexo.setSelectedItem("V");
        vista.getFechaNac().setDate(new Date());
    }

         
        public void actdesact(boolean activ) {

        vista.nombre.setEnabled(activ);
        vista.apellido.setEnabled(activ);
        vista.catedra.setEnabled(activ);
        vista.direccion.setEnabled(activ);
        vista.catedra.setEnabled(activ);
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
        
         public void representante() {
             int velim = 0;
        modeloR = new ModRepresentante();
        boolean existe =  modeloR.Consultar(vista.getCedulaRepresentante().getText());
                if (existe) {
                            
               velim = JOptionPane.showConfirmDialog(new JFrame(), "¿ESTA SEGURO QUE DESEA ASIGNAR ESTE REPRESENTANTE AL ALUMNO?", "Confirmar Asignacion", JOptionPane.YES_NO_OPTION);
        
                    if (velim == 0) {
                        vista.id_representante.setText(modeloR.getId());
                        vista.cedulaRepresentante.setEnabled(false);
                     //   vista.btnNuevoRepresentante.setEnabled(false);
                    }else{
                        vista.cedulaRepresentante.setText("");
                    }
                    
                }
                
    }
   
      
                  public void Inhabilitar() throws SQLException {
        System.out.println("BOTON INHABILITAR");
        
         String cargo = vista.id.getText(); 
            modelo =new ModEstudiante();
            boolean resultado= modelo.inhabilitar(cargo);
            if (resultado)
            {
             if(modelo.inhabilitar(cargo)){
              JOptionPane.showMessageDialog(vista,"INHABILITADO");
              vista.status.setText("INACTIVO");
              
           }else
              JOptionPane.showMessageDialog(vista,"ERROR: NO SE PUDO INHABILITAR");
            
             //Cancelar();
    }
         }
    
     public void habilitar() throws SQLException{
         modelo =new ModEstudiante();
        String estatus = vista.id.getText(); 
        boolean resultado= modelo.inhabilitar(estatus);
        
        if(modelo.Activar(estatus)){
          JOptionPane.showMessageDialog(vista,"HABILITADO");
           vista.status.setText("ACTIVO");
          
        }else
          JOptionPane.showMessageDialog(vista,"ERROR: NO PUDO SER ACTIVADO");
      //  Cancelar();
    } 
public int calcularEdad(){
    
    String fecha_nacimiento = new SimpleDateFormat("dd/MM/yyyy").format(vista.fechaNac.getDate());
        
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fechaNac = LocalDate.parse(fecha_nacimiento,fmt);
    LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNac,ahora);
    
    return periodo.getYears();
    }

        
}