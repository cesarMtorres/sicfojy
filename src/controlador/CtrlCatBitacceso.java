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
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import modelo.ModBitacceso;
import vista.CatBitacceso;

/**
 *
 * @author rover
 */
public class CtrlCatBitacceso extends AbstractAction implements ActionListener {
    private final ModBitacceso consulta_valores;
    private CatBitacceso vista_consulta_valores;
    
    public CtrlCatBitacceso(CatBitacceso vista_consulta_valores) {
        this.vista_consulta_valores = vista_consulta_valores;
        this.consulta_valores = new ModBitacceso(this.vista_consulta_valores);
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
       
        if (evento.getSource() == vista_consulta_valores.cmdsalir) {
            vista_consulta_valores.dispose();
            //vista_consulta_valores = null;
            CatBitacceso.salida = 0;
        }
        if (evento.getSource() == CatBitacceso.buscapor1) {
            CatBitacceso.buscapor1.setSelected(true);
            vista_consulta_valores.filtrar(0,0);
        }
        if (evento.getSource() == CatBitacceso.buscapor2) {
            CatBitacceso.buscapor2.setSelected(true);
            vista_consulta_valores.filtrar(1,0);
        }
        
        if (evento.getSource() == vista_consulta_valores.cmdtodos){
            try {
               // marcaOpcion(vista_consulta_valores.ptabBitacceso, "T");
            } catch (Exception ex) {
                Logger.getLogger(CtrlCatBitacceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evento.getSource() == vista_consulta_valores.cmdnada){
            try {
               // marcaOpcion(vista_consulta_valores.ptabBitacceso, "N");
            } catch (Exception ex) {
                Logger.getLogger(CtrlCatBitacceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } // fin del metodo actionPerformed

    // Selecciona un registro al dar doble clic
    public void eventoDobleClick(final JTable ptabBitacceso) {
        ptabBitacceso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    //vista_consulta_valores.txtbusca.setText((String) ptabPermiso.getValueAt(ptabPermiso.getSelectedRow(), 0));
                    vista_consulta_valores.dispose();
                    //vista_consulta_valores = null;
                    CatBitacceso.salida = 1;
                }
            }
        });
    }

    // Carga los Valores de Permiso segun el Criterio
    public void cargarTablaConsultaValores(JTable ptabBitacceso) throws SQLException {
        consulta_valores.CargarTablaBitacceso(ptabBitacceso, "");
    }
    
    public void eventobusqueda(String valor, JTable ptabBitacceso, int ptipo) throws SQLException {
            //tipo de busqueda 0:Por contenido; 1:Por Inicio de Letra
            switch(ptipo){
                case 0:
                    consulta_valores.CargarTablaBitacceso(ptabBitacceso, " WHERE " +  CatBitacceso.campobus + " LIKE '%"+valor+"%' ORDER BY " + CatBitacceso.campobus);
                    break;
                case 1:
                    consulta_valores.CargarTablaBitacceso(ptabBitacceso, " WHERE " +  CatBitacceso.campobus + " LIKE '"+valor+"%' ORDER BY " + CatBitacceso.campobus);
                    break;
                default:
                    consulta_valores.CargarTablaBitacceso(ptabBitacceso, " ORDER BY " + CatBitacceso.campobus);
                    break;
            }
    }
    public void marcaOpcion (JTable ptabBitacceso, String pmarca) {
        int columna = 2;
        try {
           for (int fila = 0; fila < ptabBitacceso.getRowCount(); fila++) {
               ptabBitacceso.setValueAt("T".equals(pmarca), fila, columna);
               //System.out.println(pmarca);
           }
       } catch (Exception ex) {
           Logger.getLogger(CtrlCatBitacceso.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
