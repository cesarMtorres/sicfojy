package controlador;

import vista.RespRestBD;
import modelo.ModeloRespRestBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;

/**
 *
 * @author julianny
 */
public class CtrlRespRestBD implements ActionListener{

    RespRestBD vista;
    ModeloRespRestBD modelo = new ModeloRespRestBD();

    private String rutaRespaldo = "";
    private String extension = "";
    private boolean parcialActivo = true;

    private final byte RESPALDO = 1;
    private final byte RESTAURACION = 2;

    public CtrlRespRestBD (RespRestBD vista) {
        this.vista = vista;        

        //Ocultar el Boton de "Respaldo"
        //vista.getBtnRespaldar().setVisible(false);
        //Preselección de "Parcial"
        //vista.getRadioParcial().setSelected(true);

        //Cargar tablas de base de datos en 'Listado Rojo'
        //vista.getListadoTablas().setModel(modelo.ConsultarTablas() );
        //Inicializar 'Listado verde' (Limpiar)
        //vista.getListadoRespaldar().setModel( new DefaultListModel() );

        //Detectar el Sistema Operativo
        //vista.getLabelSO().setText( System.getProperty("os.name") );
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object evento = e.getSource();

        if( evento.equals( vista.getRadioTotal() ) ){

            //Cargar tablas base de datos en 'Listado Verde'
            vista.getListadoRespaldar().setModel(modelo.ConsultarTablas() );
            //Inicializar 'Listado Rojo' (Limpiar)
            vista.getListadoTablas().setModel( new DefaultListModel() );

            //Ocultar botones (<, >) ya que no se utilizaran al realizar un respaldo de todas las tablas de la base de datos
            vista.getBtnDerecha().setVisible(false);
            vista.getBtnIzquierda().setVisible(false);
        
        }else

        //Se ejecutara solo cuando la opción parcial no hubiese estado seleccionada anteriormente
        if( evento.equals( vista.getRadioParcial()) && parcialActivo == false ){

            ////Cargar tablas base de datos en 'Listado Rojo'
            vista.getListadoTablas().setModel(modelo.ConsultarTablas() );
            //Inicializar 'Listado Rojo' (Limpiar)
            vista.getListadoRespaldar().setModel( new DefaultListModel() );

            //Mostrar botones (< , >) ya que se utilizaran para elegir las tablas a respaldar
            vista.getBtnDerecha().setVisible(true);
            vista.getBtnIzquierda().setVisible(true);
        }else

        if( evento.equals( vista.getBtnRutaSource() )){

            //Obtenemos la ruta seleccionada
            rutaRespaldo = seleccionarRuta(RESPALDO);
            //Mostramos la ruta seleccionada, concatenando la ruta más la extesión
            vista.getLabelSource().setText(rutaRespaldo + extension);

        }else

        if( evento.equals( vista.getRadioSql() ) ){
            extension = ".sql";

            //Mostramos la ruta seleccionada, concatenando la ruta más la extesión (.sql)
            vista.getLabelSource().setText( rutaRespaldo + extension);

        }else

        if( evento.equals( vista.getRadioBackup()) ){
            extension = ".backup";

            //Mostramos la ruta seleccionada, concatenando la ruta más la extesión (.backup)
            vista.getLabelSource().setText( rutaRespaldo + extension);

        }else

        if( evento.equals( vista.getBtnDerecha() ) ){

            //Se envian como parametros la Lista Roja y Verde
            moverSeleccion(vista.getListadoTablas(), vista.getListadoRespaldar());

        }else

        if( evento.equals( vista.getBtnIzquierda() ) ){

            //Se envian como parametros la Lista Verde y Roja
            moverSeleccion(vista.getListadoRespaldar(), vista.getListadoTablas() );

        }else

        if( evento.equals( vista.getBtnRespaldar() ) ){

            respaldar();
        }else

        if( evento.equals( vista.getBtnRestaurar() ) ){

            restaurar( seleccionarRuta(RESTAURACION) );
        }

        //Metodo encargado de Validar
        todoListo();
    }

    /**
     * Metodo Encargado de Mover la Fila o Filas
     * Selecionadas de un JList
     * 
     * <ul>
     *  <li><b>origen</b>: JList donde se espera sea seleccionada 1 o más filas</li>
     *  <li><b>destino</b>: JList donde se moveran las filas seleccionadas en <b>origen<b>
     */
    private void moverSeleccion(JList origen, JList destino){

        //Se obitnen los modelos de "origen" y "destino" para poder utilizar el metodo 'addElement' y 'removeRange'
        DefaultListModel listaOrigen = (DefaultListModel) origen.getModel();
        DefaultListModel listaDestino = (DefaultListModel) destino.getModel();

        //Se almacenan los identificadores de las tablas seleccionadas
        int[] indice = origen.getSelectedIndices();
        //Se almacena los nombres de las tablas seleccionadas de la "Lista Roja"
        Object[] tabla = origen.getSelectedValues();

            byte i = 0;

            for(; i < indice.length; i++ ){

                //Se insertan los nombres de las tablas seleccionadas
                listaDestino.addElement( tabla[i] );
            }

            if( indice.length > 0)
                //Se eliminan las tablas de la "Lista Roja" que ya han sido insertadas en la "Lista Verde"
                listaOrigen.removeRange(indice[0], indice[i - 1]);
            else
                //Al no seleccionar un elemento de Origen y dar click en el boton ( < - > )
                JOptionPane.showMessageDialog(vista, "Seleccione un elemento de la lista", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metodo Encargado de verificar que todos los campos esten correctos
     * para proceder a mostrar el boton de <b>Respaldar</b>
     */
    private void todoListo(){

        parcialActivo = vista.getRadioParcial().isSelected();

        //Inicializa en 0 (invalido)
        byte[] valido = {0, 0, 0};

        //Si la ruta del respaldo y la extensión no estan vacias
        if( rutaRespaldo.isEmpty() == false && extension.isEmpty() == false){
            valido[0] = 1;
        
        }else{
            valido[0] = 0;
        }

        //Si la lista Verde almenos tiene 1 tabla para respaldar
        if( vista.getListadoRespaldar().getModel().getSize() > 0){
            valido[1] = 1;
        }else{
            valido[1] = 0;
        }

        //Si almenos seleccionamos un formato
        if( vista.getRadioSql().isSelected() == true || vista.getRadioBackup().isSelected() == true){
            valido[2] = 1;
        
        }else{
            valido[2] = 0;
        }

        //Se inicializa en 1 (valido)
        byte resultado = 1;

        for(byte i: valido){
            resultado = (byte) (resultado * i);
        }

        if( resultado == 1)
            //Al todos los campos ser validos se Habilitara el boton de "Respaldo"
            vista.getBtnRespaldar().setEnabled(true);
        else
            vista.getBtnRespaldar().setEnabled(false);
    }

    private String seleccionarRuta(byte tipoRuta){

        //Crear objeto "seleccionar" de tipo "JFileChooser"
        JFileChooser seleccionar = new JFileChooser();
        //Mensaje a mostrar al seleccionar donde almacenaremos la base de datos
        //Y se indican los tipos de archivos que podran ser visualizados

        String[] sql = {" y sql", "sql"};

        if( tipoRuta == RESTAURACION )
            sql[0] = ""; sql[1] = ".backup";

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo backup" + sql[0], "backup", sql[1]);

        //Titulo que mostrara la ventana de exploración
        seleccionar.setDialogTitle("Seleccionar Ruta");
        //Se asigna el filtro previamente declaro a neustra ventana de exploración
        seleccionar.setFileFilter(filtro);

        //"showSaveDialog(vista)" Se mostrara nuestra ventana de exploración
        //Se indica que el padre es la vista (restaurarBD)

        byte estadoVentana = 2;

        if( tipoRuta == RESPALDO){
            estadoVentana = (byte) seleccionar.showSaveDialog(vista);

        }else

        if( tipoRuta == RESTAURACION ){
            estadoVentana = (byte) seleccionar.showOpenDialog(vista);

        }

         //Guardar retorna 0 (APPROVE_OPTION)
        if( estadoVentana == seleccionar.APPROVE_OPTION ){

            return seleccionar.getSelectedFile().getAbsolutePath();

        }else

        //cancelar retorna 1(CANCEL_OPTION)
        if( estadoVentana == seleccionar.CANCEL_OPTION ){
            JOptionPane.showMessageDialog(vista, "Operación Cancelada");
        }else

        if( estadoVentana == seleccionar.ERROR_OPTION){
            JOptionPane.showMessageDialog(vista, "Operación Cancelada", "Error al Seleccionar ruta", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    private void reiniciarValores(){

    //Se inicializan los valores de los atributos y Listas
        vista.getLabelSource().setText("");
        rutaRespaldo = "";
        extension = "";

        vista.getListadoTablas().setModel( modelo.ConsultarTablas() );
        vista.getListadoRespaldar().setModel( new DefaultListModel() );
        vista.getFormatoSalida().clearSelection();
        vista.getRadioParcial().setSelected(true);

        vista.getBtnDerecha().setVisible(true);
        vista.getBtnIzquierda().setVisible(true);
    }

    /**
     * Metodo Encargado de:
     * <ul>
     *  <li>Enviar la Ruta del Respaldo</li>
     *  <li>Las tablas seleccioandas
     *  <li>El formato del respaldo <b>.sql (Texto Plano)</b> o <b>.backup (Comprimido)</b>
     */
    private void respaldar(){

        //Al iniciar el proceso el cursor cambia a modo "Cargando o Espera"
        vista.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );

        //Se obtiene la cantidad de tablas que estan en la "Lista Verde"
        byte cantTablas = (byte) vista.getListadoRespaldar().getModel().getSize();

        String tablas = "";

        //Si se ha seleccionado el respaldo Parcial
        if( vista.getRadioParcial().isSelected() ){

            for(byte i = 0; i < cantTablas; i++){

                //Se recorren todas las tablas y concatenan en el mismo atributo "tablas" agregando ", -t," antes de cada nombre de tabla
                tablas += ",-t," + vista.getListadoRespaldar().getModel().getElementAt(i).toString();
            }
        }

        //Se envia la ruta del respaldo, las tablas y Si fue seleccionado (true) o no (False) el formato .sql (Texto Plano)
        if ( modelo.generarBackup( vista.getLabelSource().getText(), tablas, vista.getRadioSql().isSelected() )  ){

            reiniciarValores();
            JOptionPane.showMessageDialog(vista, "Respaldo Existoso!!!");

        }else{
            JOptionPane.showMessageDialog(vista, "Al respaldar la base de datos", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
        }

        //Al finalizar el proceso el cursor regresa a su estado por defecto
        vista.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
    }

    /**
     * Metodo Encargado de:
     * <ul>
     *  <li>Enviar la Ruta de el archivo .sql o.backup</li>
     *  <li>donde se encuentre la data para respaldar la base de datos
     */
    private void restaurar(String rutaArchivo){

        //Al iniciar el proceso el cursor cambia a modo "Cargando o Espera"
        vista.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );

        if ( rutaArchivo != null && modelo.realizarRestore(rutaArchivo) == true ){
            JOptionPane.showMessageDialog(vista, "Restauración exitosa!!!");

        }else

        if( rutaArchivo != null && modelo.realizarRestore(rutaArchivo) == false){
            JOptionPane.showMessageDialog(vista, "Al restaurar la base de datos", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
        }        

        //Al finalizar el proceso el cursor regresa a su estado por defecto
        vista.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

        reiniciarValores();
    }

 
}