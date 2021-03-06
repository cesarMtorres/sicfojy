/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ctrCatTInstrumento;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSMaterialButtonRectangle;

/**
 * @author Ronal
 */
public class CatTipoInstrumento extends java.awt.Dialog {
    /**
     * Creates new form Busqueda
     * 
     */
        Estudiante vista;
        public static int salida = 0;
        public static String campobus = "nombre";
        ctrCatTInstrumento c = new ctrCatTInstrumento(this);
        
    public CatTipoInstrumento(java.awt.Frame parent, boolean modal)throws SQLException {
        super(parent, modal);
        initComponents();
        //         tablaConsulta.setDefaultRenderer (Object.class, new AvisoStockMin());
       this.txtBuscar.requestFocus();
       c.ListadoInst();
       txtBuscar.addKeyListener(c);
       this.setLocationRelativeTo(null);
       this.setResizable(false);
//         tablaConsulta.setDefaultRenderer (Object.class, new AvisoStockMin());
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    public JButton getBtnAceptar() {
        return btnCancelar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnCancelar = (RSMaterialButtonRectangle) btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = (RSMaterialButtonRectangle) btnCancelar;
    }

    public JTable getTabCatalago() {
        return tabCatalago;
    }

    public void setTabCatalago(JTable tabCatalago) {
        this.tabCatalago = tabCatalago;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        etiBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabCatalago = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new rojerusan.RSMaterialButtonRectangle();
        btnAceptar = new rojerusan.RSMaterialButtonRectangle();

        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(905, 370));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        etiBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiBuscar.setText("Buscar:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        tabCatalago = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tabCatalago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabCatalago.setFocusable(false);
        tabCatalago.setGridColor(new java.awt.Color(255, 204, 0));
        tabCatalago.getTableHeader().setReorderingAllowed(false);
        tabCatalago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCatalagoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabCatalago);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("CÁTALOGO  INSTRUMENTO");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        c = new controlador.ctrCatTInstrumento(this);
        btnCancelar.addActionListener(c);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        c = new controlador.ctrCatTInstrumento(this);
        btnAceptar.addActionListener(c);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(etiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(267, 267, 267)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(344, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tabCatalagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCatalagoMouseClicked
        // TODO add your handling code here:
       int selec= tabCatalago.rowAtPoint(evt.getPoint());
       
       txtBuscar.setText(String.valueOf(tabCatalago.getValueAt(selec, 0)));

    }//GEN-LAST:event_tabCatalagoMouseClicked

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
                char car = evt.getKeyChar();
        
       if(Character.isLowerCase(car)){
        
        String cad =(""+car).toUpperCase();
        car=cad.charAt(0);
        evt.setKeyChar(car);
       }
               if(txtBuscar.getText().length() >=20) {
         Toolkit.getDefaultToolkit().beep();
         JOptionPane.showMessageDialog(null, "Limite de Caracteres", "Advertencia",JOptionPane.PLAIN_MESSAGE);      }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CatTipoInstrumento dialog = null;
                try {
                    dialog = new CatTipoInstrumento(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(CatTipoInstrumento.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public rojerusan.RSMaterialButtonRectangle btnAceptar;
    public rojerusan.RSMaterialButtonRectangle btnCancelar;
    public javax.swing.JLabel etiBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabCatalago;
    public javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
