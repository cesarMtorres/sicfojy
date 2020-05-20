

package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ModeloSesion {
    String perfil;
    String cod_empleado;
    String usuario;
    String contrasena;
    
   ConexionBD conexion;

   public ModeloSesion(){}

    public String getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(String cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
   
public boolean actualizarStatus(String id,String tipo){

        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.usuario SET status='"+tipo+"' WHERE id='"+id+"' ;";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }
  
   public boolean bloquearUsuario(String usuario){

        boolean statusModificar;
        conexion = new ConexionBD();
        conexion.conectar();
        
        String sentenciaSql = "UPDATE public.usuario SET eliminacion='FALSE' WHERE login='"+usuario+"' ;";
        System.out.println(sentenciaSql);
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        
        conexion.cerrar();
        return statusModificar;
    }
    
    public boolean ConsultarUsuario(String contrasena, String usuario){
       
            boolean statusConsulta=false;
            
            conexion = new ConexionBD();
            conexion.conectar();
            
                String tirasql = "SELECT login,clave,id_perfil,id FROM public.vrusuporperfil WHERE login ='"+usuario+"' AND clave = '"+contrasena+"' AND eliminacion='TRUE'";
            
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(tirasql);
            
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    
                    setPerfil(resultadoConsulta.getString("id_perfil"));
                    setCod_empleado(resultadoConsulta.getString("id"));
                    setUsuario(resultadoConsulta.getString("login"));
                    setContrasena(resultadoConsulta.getString("clave"));
                    
                          
                          
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
             conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
}
  
