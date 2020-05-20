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
import modelo.ModBien;
import modelo.ModTipoBien;
import modelo.ModeloBitacora;
//import net.sf.jasperreports.engine.JRException;
import vista.CatBien;
import vista.Bien;
import modelo.ModModelo;
import modelo.ModMarca;
import vista.Principal;
/**
 *
 * @author cesar
 */
public class ctrBien extends AbstractAction implements ActionListener {
    public Bien vista;
   private ModeloBitacora bitacora;
    //modelo datos pesonales
    public ModBien modelo=new ModBien();
    ModMarca mmarca=new ModMarca();
    ModModelo mmodelo=new ModModelo();
    ModTipoBien mtipob = new ModTipoBien();
    private CatBien vcatBien;
    private int acc;
    
    public ctrBien(Bien vista) {
        this.vista = vista;
        bitacora= new ModeloBitacora();
    }
@Override
     public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==vista.getBtnRegistrar()){
            Registrar();
            System.out.println("Botón incluir...");
        }else if(evento.getSource()==vista.getBtnModificar()){
            Modificar();
            System.out.println("Botón Modificar...");
        }else if(evento.getSource()==vista.getBtnCancelar()){
            Cancelar();
            System.out.println("Botón cancelar...");
        }else if(evento.getSource()==vista.getBtnCatalago()){
            System.out.println(" catalago...");
              String codigobus = ""; //JOptionPane.showInputDialog("Ingrese " + varclase + " a Buscar: ");
                //System.out.println("algoCargo");
              try {
                vcatBien = new CatBien(new javax.swing.JFrame(), true);
            } catch (IllegalArgumentException | SQLException ex) {
                Logger.getLogger(ctrBien.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            vcatBien.setVisible(true);

            if(vcatBien.tabCatalago.isRowSelected(vcatBien.tabCatalago.getSelectedRow()) ){
                codigobus = vcatBien.tabCatalago.getValueAt(vcatBien.tabCatalago.getSelectedRow(),1).toString(); // obtiene el serial
                acc =vcatBien.tabCatalago.getSelectedRow();
            }
            System.out.println("codigobus --: " + codigobus);
            modelo =new ModBien();
           
            if (codigobus != null) {
                
                boolean existe = modelo.Consultar(codigobus);
                if (existe) {
                    vista.serial.setText(modelo.getSerial());
                    vista.medida.setText(modelo.getMedida());
                    vista.nInventario.setText(modelo.getnInventario());
                    vista.cantidad.setText(modelo.getCantidad());
                    vista.marca.setSelectedItem(modelo.getMarca());
                    vista.modelo.setSelectedItem(modelo.getModelo());
                    vista.tipoBien.setSelectedItem(modelo.getTipoB());
                    vista.nombre.setText(modelo.getNombre());
                    vista.getId().setText(modelo.getId());
                   
                    vista.btnModificar.setEnabled(true);
                    
                    vista.btnCancelar.setEnabled(true);
                    vista.btnEliminar.setEnabled(true);

                    
                   
                            
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Bien " + " no encontrado ", ".",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            
        }else if(evento.getSource()==vista.btnEliminar){
                       Eliminar();
                       }
        
        
    }
     
    public void Consultar(){
        modelo = new ModBien();
            boolean encontrado = modelo.Consultar(vista.getSerial().getText());
            if (encontrado){
                vista.getMarca().setSelectedItem(modelo.getMarca());
                vista.getMedida().setText(modelo.getMedida());
                vista.getnInventario().setText(modelo.getnInventario());
                vista.getTipobien().setSelectedItem(modelo.getTipoB());
                vista.getModelo().setSelectedItem(modelo.getModelo());
                vista.getNombre().setText(modelo.getNombre());
                vista.getSerial().setText(modelo.getSerial());
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnRegistrar().setEnabled(true);
                
                
            }else{
                vista.getBtnRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
            
    }
    public void Registrar(){
        if("".equals(vista.marca.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo marca no puede estar vacio");
        } else if("".equals(vista.medida.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo medida no puede estar vacio");
        }
        else{
            String id_tipoB = this.consultarIdtipoB();
            String modeloq = this.consultarIdModelo();
            String marca = this.consultarIdMarca();
            
        //String marca =(String) vista.getId_marca().getText();
        String medida = vista.getMedida().getText();
        String nombre = vista.getNombre().getText();
        String serial = vista.getSerial().getText();
        String nInvetario = vista.getnInventario().getText();
        String cantidad = vista.getCantidad().getText();
        
      modelo = new ModBien(nombre,modeloq, serial, medida, id_tipoB,nInvetario, marca, cantidad);
        boolean incluido=modelo.RegistrarBien();
        if (incluido){
            JOptionPane.showMessageDialog(vista, "Registro Exitoso");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" REGISTRO EL BIEN "+nombre+" "+serial+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Registro no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        }
     //   codigoBotonLimpiar();
    }//fin de codigoIncluir
 public void Modificar(){
        if("".equals(vista.marca.getSelectedItem())) {
            JOptionPane.showMessageDialog(vista, "El campo marca no puede estar vacio");
        } else if("".equals(vista.medida.getText().trim())) {
            JOptionPane.showMessageDialog(vista, "El campo medida no puede estar vacio");
        }
        else{ 
            String id_tipoB = this.consultarIdtipoB();
            String modeloq = this.consultarIdModelo();
            String marca = this.consultarIdMarca();

        String medida = vista.getMedida().getText();
         String nombre = vista.getNombre().getText();
        String serial = vista.getSerial().getText();
        String nInventario = vista.getnInventario().getText();
        String cantidad = vista.getCantidad().getText();
        String id = vista.getId().getText();
        modelo = new ModBien(nombre,modeloq, serial,medida,id_tipoB,nInventario,marca,cantidad,id);
        boolean modificado=modelo.ModificarBien(id);
      if (modificado){
            JOptionPane.showMessageDialog(vista, "Modificacion Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" MODIFICO EL BIEN "+nombre+" "+serial+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
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
        String nombre = vista.getNombre().getText();
        String serial = vista.getSerial().getText();
        modelo = new ModBien();
        boolean eliminado=modelo.Eliminar(id);
    if (eliminado){
            JOptionPane.showMessageDialog(vista, "Eliminación Exitosa");
            String desc = "EL USUARIO "+Principal.getUsuario().getText()+" ELIMINO EL BIEN "+nombre+" "+serial+"";
            bitacora.RegistrarBitacora(Principal.getCodigo_personalLocal() , desc);
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
        vista.id_modelo.setText("");
        vista.nombre.setText("");
        vista.modelo.setSelectedItem("");   
        vista.marca.setSelectedItem("");
        vista.id_marca.setText("");
        vista.tipoBien.setSelectedItem("");
        vista.cantidad.setText("");
        vista.medida.setText("");
        vista.tipoBien.setSelectedItem("");
        vista.nInventario.setText("");
        vista.serial.setText("");
        
    }
        public void Inhabilitar() throws SQLException {
        System.out.println("BOTON INHABILITAR");
        
         String cargo = vista.id.getText(); 
            modelo =new ModBien();
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
         modelo =new ModBien();
        String estatus = vista.id.getText(); 
        boolean resultado= modelo.inhabilitar(estatus);
        
        if(modelo.Activar(estatus)){
          JOptionPane.showMessageDialog(vista,"HABILITADO");
           vista.status.setText("ACTIVO");
          
        }else
          JOptionPane.showMessageDialog(vista,"ERROR: NO PUDO SER ACTIVADO");
      //  Cancelar();
    }
     

         public void LLenarMarca() {
            ModBien  modelo=new ModBien();
        vista.getMarca().removeAllItems();
        ArrayList<String> datos_1 = modelo.Cargarmarca();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getMarca().addItem(cargoList);
            
        }
    }
         public void LLenarTipoBien() {
             ModBien modelo=new ModBien();
        vista.getTipobien().removeAllItems();
        ArrayList<String> datos_1 = modelo.Cargartipobien();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getTipobien().addItem(cargoList);
            
        }
    }
        
         public void LLenarModelo() {
         ModBien modelo=new ModBien();
        vista.getModelo().removeAllItems();
        ArrayList<String> datos_1 = modelo.Cargarmodelo();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getModelo().addItem(cargoList);
            
        }
    }
           public String consultarIdtipoB() {
        System.out.println("COMBO TIPO BIEN");
        
        String id ="";
        
        String itemSeleccionado = vista.tipoBien.getSelectedItem().toString();
        boolean consultado = mtipob.consultartipob(itemSeleccionado);
        
        if(consultado) {
            id = mtipob.getId_tipob();
            System.err.println(id);
        } else {
            id ="";
        }
        
        
        return id;
    }
        public String consultarIdMarca() {
        System.out.println("COMBO TIPO MARCA");
        
        String id ="";
        
        String itemSeleccionado = vista.marca.getSelectedItem().toString();
        boolean consultado = mtipob.consultaridmarca(itemSeleccionado);
        
        if(consultado) {
            id = mtipob.getId_marca();
            System.err.println(id);
        } else {
            id ="";
        }
        
        
        return id;
    }
          public String consultarIdModelo() {
        System.out.println("COMBO TIPO MODELO");
        
        String id ="";
        
        String itemSeleccionado = vista.modelo.getSelectedItem().toString();
        boolean consultado = mtipob.consultaridmodelo(itemSeleccionado);
        
        if(consultado) {
            id = mtipob.getId_modelo();
            System.err.println(id);
        } else {
            id ="";
        }
        
        
        return id;
    }
        
          public void LLenarmodelo() {
        vista.getModelo().removeAllItems();
        ArrayList<String> datos_1 = modelo.Cargarmodelo();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getModelo().addItem(cargoList);
            
        }
    }
                  public void LLenarmarca() {
        vista.getMarca().removeAllItems();
        ArrayList<String> datos_1 = modelo.Cargarmarca();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getMarca().addItem(cargoList);
            
        }
    }
        public void LLenartipobien() {
        vista.getTipobien().removeAllItems();
        ArrayList<String> datos_1 = modelo.Cargartipobien();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getTipobien().addItem(cargoList);
            
        }
    }
           public void Llenar_Marca() {
        vista.getMarca().removeAllItems();
        ArrayList<String> datos_1 = mmarca.CargarMarca();

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getMarca().addItem(cargoList);
        }
    }
    
    public void Llenar_Modelo(String dat) {
        vista.getModelo().removeAllItems();
        ArrayList<String> datos_1 = mmodelo.CargarModelo(dat);

        for (int i = 0; i < datos_1.size(); i++) {
            String cargoList = datos_1.get(i);
            System.out.println(cargoList);
            vista.getModelo().addItem(cargoList);
        }
        
    } 
}