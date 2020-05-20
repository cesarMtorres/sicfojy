/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import modelo.ModPerfil;
import vista.CatPermiso;

/**
 *
 * @author rover
 */
public class ctrCatPermiso extends AbstractAction implements ActionListener {
    private final ModPerfil consulta_valores;
    private CatPermiso vista_consulta_valores;
    
    public ctrCatPermiso(CatPermiso vista_consulta_valores) {
        this.vista_consulta_valores = vista_consulta_valores;
        this.consulta_valores = new ModPerfil(this.vista_consulta_valores);
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == vista_consulta_valores.cmdacep) {
            vista_consulta_valores.dispose();
            //vista_consulta_valores = null;
            CatPermiso.salida = 1;
        }
        if (evento.getSource() == vista_consulta_valores.cmdsalir) {
            vista_consulta_valores.dispose();
            //vista_consulta_valores = null;
            CatPermiso.salida = 0;
        }
        if (evento.getSource() == CatPermiso.buscapor1) {
            CatPermiso.buscapor1.setSelected(true);
            vista_consulta_valores.filtrar(0,0);
        }
        if (evento.getSource() == CatPermiso.buscapor2) {
            CatPermiso.buscapor2.setSelected(true);
                //eventobusqueda(vista_consulta_valores.txtbusca.getText().toString(), vista_consulta_valores.ptabPermiso, 2);
            vista_consulta_valores.filtrar(1,0);
        }
        
        if (evento.getSource() == vista_consulta_valores.cmdtodos){
            try {
                marcaOpcion(vista_consulta_valores.ptabPermiso, "T");
            } catch (Exception ex) {
                Logger.getLogger(ctrCatPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evento.getSource() == vista_consulta_valores.cmdnada){
            try {
                marcaOpcion(vista_consulta_valores.ptabPermiso, "N");
            } catch (Exception ex) {
                Logger.getLogger(ctrCatPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } // fin del metodo actionPerformed

    // Selecciona un registro al dar doble clic
    public void eventoDobleClick(final JTable ptabPermiso) {
        ptabPermiso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    vista_consulta_valores.txtbusca.setText((String) ptabPermiso.getValueAt(ptabPermiso.getSelectedRow(), 0));
                    vista_consulta_valores.dispose();
                    //vista_consulta_valores = null;
                    CatPermiso.salida = 1;
                }
            }
        });
    }

    // Carga los Valores de Permiso segun el Criterio
    public void cargarTablaConsultaValores(JTable ptabPermiso, String cdperf) throws SQLException {
        consulta_valores.CargarTablaPerfil(ptabPermiso, "",cdperf);

    }
    
    public void eventobusqueda(String valor, JTable ptabPermiso, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    consulta_valores.CargarTablaPerfil(ptabPermiso, " WHERE " +  CatPermiso.campobus + " LIKE '%"+valor+"%' ORDER BY " + CatPermiso.campobus,ctrPerfil.codperf);
                    break;
                case 1:
                    consulta_valores.CargarTablaPerfil(ptabPermiso, " WHERE " +  CatPermiso.campobus + " LIKE '"+valor+"%' ORDER BY " + CatPermiso.campobus,ctrPerfil.codperf);
                    break;
                default:
                    consulta_valores.CargarTablaPerfil(ptabPermiso, " ORDER BY " + CatPermiso.campobus,ctrPerfil.codperf);
                    break;
            }
    }
    public void marcaOpcion (JTable ptabPermiso, String pmarca) {
        int columna = 2;
        try {
           for (int fila = 0; fila < ptabPermiso.getRowCount(); fila++) {
               ptabPermiso.setValueAt("T".equals(pmarca), fila, columna);
               System.out.println(pmarca);
           }
       } catch (Exception ex) {
           Logger.getLogger(ctrCatPermiso.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
