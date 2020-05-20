package vista;
import java.util.Date;
import java.util.GregorianCalendar;

public class Formatos {
    private Date date;
    private String fecha,hora;
    Formatos(Date d){
        setDate(d);
        setFecha(obtenerFecha(d));
        setHora(obtenerHora());
    }
    
    String obtenerFecha(Date dat){
        GregorianCalendar gc=new GregorianCalendar();
        gc.setTime(dat); //Obtiene la fecha en formato Date
        ////Extrae el día, mes y año de la fecha dada
        int dia=gc.get(GregorianCalendar.DAY_OF_MONTH);
        int mes= gc.get(GregorianCalendar.MONTH) + 1;///Se suma el 1, porque los meses están dados del 0 al 11
        int año=gc.get(GregorianCalendar.YEAR);
        String d="",m="";
        if(dia<10){
            d="0"+dia;
        }else{
            d=String.valueOf(dia);
        }
        if(mes<10){
            m="0"+mes;
        }else{
            m=String.valueOf(mes);
        }
        return d+"/"+m+"/"+año;
    }
    
    String obtenerHora(){
        GregorianCalendar gc=new GregorianCalendar();
        ////Extrae la hora, minuto y segundo de la hora actual del sistema
        int hor=gc.get(GregorianCalendar.HOUR_OF_DAY);
        int minuto=gc.get(GregorianCalendar.MINUTE);
        int segundo=gc.get(GregorianCalendar.SECOND);
        String h="",m="",s="";
        if(hor<10){
            h="0"+hor;
        }else{
            h=String.valueOf(hor);
        }
        if(minuto<10){
            m="0"+minuto;
        }else{
            m=String.valueOf(minuto);
        }
        if(segundo<10){
            s="0"+segundo;
        }else{
            s=String.valueOf(segundo);
        }
        return h+":"+m+":"+s;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}