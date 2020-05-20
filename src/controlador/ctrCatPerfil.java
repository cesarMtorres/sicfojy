/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModPerfil;
import vista.Perfil;
import vista.CatPerfil;

/**
 *
 * @author betty
 */
public class ctrCatPerfil implements ActionListener, KeyListener {
    
    Perfil vista;
    ModPerfil modelo;    
    CatPerfil catPerfil;
    
   
    public ctrCatPerfil(CatPerfil catPerfil) {
        this.catPerfil= catPerfil;
    }
        
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == catPerfil.btnAceptar) {
            catPerfil.dispose();
            //vista_consulta_valores = null;
            
        }
        if (evento.getSource() == catPerfil.btnCancelar) {
            catPerfil.dispose();
            //vista_consulta_valores = null;
            
        }
    }
    
    public void eventoDobleClick(final JTable ptabperfil) {
        ptabperfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    //vista_consulta_valores.txtbusca.setText((String) ptabperfil.getValueAt(ptabperfil.getSelectedRow(), 0));
              //     catPerfil.dispose();
                    //vista_consulta_valores = null;
                    CatPerfil.salida = 1;
                }
            }
        });
    }
        public void cargarTablaConsultaValores(JTable ptabperfil) throws SQLException {
        modelo.CargarTablaPerfil(ptabperfil, "","");
    }
 
    public void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("mouse clicked");
        
    }
    
    private void tablaLineasMouseClicked(java.awt.event.MouseEvent evt) {
        
}
    
   ////////////////////////
    public void controlLlenarCatalogoBien(DefaultTableModel tablaModel){
           System.out.println("Catalago perfil");
            modelo = new ModPerfil();
            modelo.cargarDatosCatalogoPerfil(tablaModel);        
    }
    
       ////////////////////////


 public void Listado(){
            
        modelo = new ModPerfil();
        String[][] informacion =  modelo.Filtro(catPerfil.getTxtBuscar().getText());
        if(informacion == null){
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Nombre", "Descripcion"});
            catPerfil.getTabCatalago().setModel(model);
        }else{
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{ "Nombre", "Descripcion"});
            catPerfil.getTabCatalago().setModel(model);
        }
        
    }

        public void ListadoPerfil(){
         modelo = new ModPerfil();
        String[][] informacion =  modelo.consultarListado();
        if(informacion == null){
            System.out.println("null listado perfil");
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"Codigo", "Nombre"});
           catPerfil.getTabCatalago().setModel(model);
        }else{
            System.out.println("LISTADO perfil");
            DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Codigo", "Nombre"});
            catPerfil.getTabCatalago().setModel(model);
        }
        
    }
             public void eventobusqueda(String valor, JTable tabEstudiante, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    modelo.CargarTablaPerfil(tabEstudiante, " WHERE " +  catPerfil.campobus + " LIKE '%"+valor+"%' ORDER BY " + catPerfil.campobus);
                    break;
                case 1:
                    modelo.CargarTablaPerfil(tabEstudiante, " WHERE " +  catPerfil.campobus + " LIKE '"+valor+"%' ORDER BY " + catPerfil.campobus);
                    break;
                default:
                    modelo.CargarTablaPerfil(tabEstudiante, " ORDER BY " + catPerfil.campobus);
                    break;
            }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

       @Override
    public void keyReleased(KeyEvent e) {
        
        if (e.getSource() == catPerfil.getTxtBuscar()){
            ListadoPerfil();
        }
    }
    
}
