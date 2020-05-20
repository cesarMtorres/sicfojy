/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public  class Validacion {
    
    
    
    
    public void soloLetrasPorTeclado(java.awt.event.KeyEvent evt){
        char car = evt.getKeyChar();        
            if((car<'a' || car>'z') && (car<'A' || car>'Z')&& ( car!=(char)java.awt.event.KeyEvent.VK_BACK_SPACE)             
         && car !='á' //Minúsculas             
         && car !='é'           
         && car !='í'           
         && car !='ó'          
         && car !='ú'  
         && car !='Á' //Mayúsculas             
         && car !='É'           
         && car !='Í'           
         && car !='Ó'          
         && car !='Ú'            
         && (car!=(char)java.awt.event.KeyEvent.VK_SPACE)) 
            {
                evt.consume();   //
            }
            
    
    }
    
    
    public void soloCedulaPorTeclado(java.awt.event.KeyEvent evt,JTextField txtCedula){
    char car = evt.getKeyChar ();
        int code = evt.getKeyCode();
        if(txtCedula.getText().length()>=9) 
            evt.consume();
        
              //  JOptionPane.showMessageDialog(null, evt.getKeyChar(),"Validación", 
		//JOptionPane.ERROR_MESSAGE);     
        
        if((car<'0'|| car>'9')&& ( car!=(char)java.awt.event.KeyEvent.VK_BACK_SPACE)){ 
            
                evt.consume();
              // JOptionPane.showMessageDialog(null, " Disculpe la Cédula es Invalida ","Validación", 
		//JOptionPane.ERROR_MESSAGE);
         //txtCedula.setText("");  
                
    }         
    
    
    }
    
    
     public void soloNumeroPorTeclado(java.awt.event.KeyEvent evt){
     char car = evt.getKeyChar ();
     
        if((car<'0'|| car>'9' )&& ( car!=(char)java.awt.event.KeyEvent.VK_BACK_SPACE) && ( car!=(char)java.awt.event.KeyEvent.VK_PERIOD)){ 
            
                evt.consume();
                
        }         
    
    
    }
    
      public void soloNumeroPorTecladoSinDecimal(java.awt.event.KeyEvent evt){
     char car = evt.getKeyChar ();
     
        if((car<'0'|| car>'9' )&& ( car!=(char)java.awt.event.KeyEvent.VK_BACK_SPACE)){ 
            
                evt.consume();
                
        }         
    
    
    }
     public void soloDireccionPorTecladoConEtiqueta(java.awt.event.KeyEvent evt,JTextArea txtDireccion,int contador){
        char car = evt.getKeyChar ();
       
        if(txtDireccion.getText().length()>=contador) 
          
            evt.consume();
        
     }
    
     public void soloDireccionPorTecladoContador(java.awt.event.KeyEvent evt,JTextArea txtDireccion,JLabel lblDireccionContador,int contador){
        char car = evt.getKeyChar ();
        if (contador-txtDireccion.getText().length()>=0){
        
        lblDireccionContador.setText(Integer.toString(contador-txtDireccion.getText().length()));

        } 
        
     }
     
      public void soloDireccionPorTeclado(java.awt.event.KeyEvent evt,JTextArea txtDireccion){
        char car = evt.getKeyChar ();
        if(txtDireccion.getText().length()>=10) 
            evt.consume();
        
     }
      
      public boolean soloCamposVacios(JTextField campo){
      
      boolean vacio = false;
      
      if(campo.getText().equals("")){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Campo "+campo.getName()+" vacio.");
      campo.requestFocus();
      
      }else{
      
      vacio=false;
      }
      
      
      return vacio;
      }
      
      
      public boolean soloTablasVacias(JTable tabla,String texo){
      
      boolean vacio = false;
          DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
      if(modelo.getRowCount()==0){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Tabla "+texo+" vacia.");
      tabla.requestFocus();
      
      }else{
      vacio=false;
      }
      
      
      return vacio;
      }
      
      
      public boolean soloCamposVacios(JTextField campo,String texto){
      
      boolean vacio = false;
      
      if(campo.getText().equals("")){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Campo "+texto+" vacio.");
      campo.requestFocus();
      
      }else{
      
      vacio=false;
      }
      
      
      return vacio;
      }
      
      public boolean soloEtiquetasVacios(JLabel campo,String texto){
      
      boolean vacio = false;
      
      if(campo.getText().equals("")){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Campo "+texto+" vacio.");
      campo.requestFocus();
      
      }else{
      
      vacio=false;
      }
      
      
      return vacio;
      }
      
      
      public boolean soloFechaVacios(JDateChooser fecha,String texto){
      
      boolean vacio = false;
      
      if(fecha.getDate()==null){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Campo "+texto+" vacio.");
      fecha.requestFocus();
      
      }else{
      
      vacio=false;
      }
      
      
      return vacio;
      }
      
      
     public boolean soloAreasVacios(JTextArea campo,String texto){
      
      boolean vacio = false;
      
      if(campo.getText().equals("")){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Campo "+texto+" vacio.");
      campo.requestFocus();
      
      }else{
      
      vacio=false;
      }
      
      
      return vacio;
      }
      public boolean soloSprinnerVacios(JSpinner campo,String texto){
      
      boolean vacio = false;
      
      if(campo.getValue().toString().equals("0") || campo.getValue().toString().equals("0.0") ){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Campo "+texto+" no puede ser 0.");
      campo.requestFocus();
      
      }else{
      
      vacio=false;
      }
      
      
      return vacio;
      }
      
      
      public boolean soloAreasVacios(JTextArea campo){
      
      boolean vacio = false;
      
      if(campo.getText().isEmpty()){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Campo "+campo.getName()+" vacio.");
      campo.requestFocus();
      
      }
      
      
      return vacio;
      }
      
      public boolean soloComboNoSeleccionado(JComboBox combo){
      
      boolean vacio = false;
      
      if(combo.getSelectedIndex()==0){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Combo "+combo.getName()+" no seleccionado.");
      combo.requestFocus();
      
      }
      
      
      return vacio;
      }
      
       public boolean soloComboNoSeleccionado(JComboBox combo,String texto){
      
      boolean vacio = false;
      
      if(combo.getSelectedIndex()==0){
      
      vacio = true;
      JOptionPane.showMessageDialog(null, "Combo "+texto+" no seleccionado.");
      combo.requestFocus();
      
      }
      
      
      return vacio;
      }
      
       public static Integer calcularEdad(String fecha){
         Date fechaNac=null;
        try {
            /*Se puede cambiar la mascara por el formato de la fecha que se 
            //quiera recibir, por ejemplo año mes día "yyyy-MM-dd"
            en este caso es día mes año*/
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:"+ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if(mes<0 || (mes==0 && dia<0)){
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;
    }
       
       
       public void MayusculaJTextField(JTextField campo){
        String toUpperCase = campo.getText().toUpperCase();
        campo.setText(toUpperCase);
       }
    
       public void MayusculaJTextArea(JTextArea campo){
        String toUpperCase = campo.getText().toUpperCase();
        campo.setText(toUpperCase);
       }
    
        public void validarEmail(JTextField campo) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(campo.getText());
        if(matcher.matches()){
        
        
        }else{
        
        JOptionPane.showMessageDialog(null, "Error-> El Correo Electronico es Incorrecto.");
        
        }
 
    }
    public ImageIcon ajustarImagen(String ruta,int x,int y)
    {
        ImageIcon tmpIconAux = new ImageIcon(this.getClass().getResource(ruta));
        //Escalar Imagen
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT));
        return tmpIcon;
    }
}
