/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ctrCatModulo;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author betty
 */
public class CatModulo extends javax.swing.JDialog {
    
     Modulo vista;
        public static int salida = 0;
        public static String campobus = "nombre";
        ctrCatModulo c = new ctrCatModulo(this);

    /**
     * Creates new form CatModulo
     */
    public CatModulo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       this.txtBuscar.requestFocus();
       c.ListadoModulo();
       txtBuscar.addKeyListener(c);
       this.setLocationRelativeTo(null);
       this.setResizable(false);
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JTable getTabCatalago() {
        return tabCatalago;
    }

    public void setTabCatalago(JTable tabCatalago) {
        this.tabCatalago = tabCatalago;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgordena = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabCatalago = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new rojerusan.RSMaterialButtonRectangle();
        btnAceptar = new rojerusan.RSMaterialButtonRectangle();
        etiBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        buscapor2 = new javax.swing.JRadioButton();
        buscapor1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        tabCatalago.setGridColor(new java.awt.Color(255, 204, 0));
        tabCatalago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCatalagoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabCatalago);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("CÁTALOGO MODÚLO");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        c = new controlador.ctrCatModulo(this);
        btnCancelar.addActionListener(c);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        c = new controlador.ctrCatModulo(this);
        btnAceptar.addActionListener(c);

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

        btgordena.add(buscapor2);
        buscapor2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        buscapor2.setForeground(new java.awt.Color(0, 64, 128));
        buscapor2.setText("Descripción");
        buscapor2.setContentAreaFilled(false);
        buscapor2.setFocusPainted(false);
        buscapor2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buscapor2ItemStateChanged(evt);
            }
        });

        btgordena.add(buscapor1);
        buscapor1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        buscapor1.setForeground(new java.awt.Color(0, 64, 128));
        buscapor1.setSelected(true);
        buscapor1.setText("Nombre");
        buscapor1.setToolTipText("Buscar por Código");
        buscapor1.setContentAreaFilled(false);
        buscapor1.setFocusPainted(false);
        buscapor1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buscapor1ItemStateChanged(evt);
            }
        });
        buscapor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscapor1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscapor2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscapor1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buscapor1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscapor2)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        // Code adding the component to the parent container - not shown here
        c = new controlador.ctrCatModulo(this);
        buscapor2.addActionListener(c);
        // Code adding the component to the parent container - not shown here
        c = new controlador.ctrCatModulo(this);
        buscapor1.addActionListener(c);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabCatalagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCatalagoMouseClicked
        // TODO add your handling code here:
       int selec= tabCatalago.rowAtPoint(evt.getPoint());
       txtBuscar.setText(String.valueOf(tabCatalago.getValueAt(selec, 1)));
    }//GEN-LAST:event_tabCatalagoMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        try {
            c.eventobusqueda(txtBuscar.getText(), tabCatalago, 0);
        } catch (SQLException ex) {
            Logger.getLogger(CatModulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

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

    private void buscapor2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buscapor2ItemStateChanged
        //stateChanged(evt);
        if (buscapor2.isSelected()) {
            try {
                campobus  = "descripcionmd";
                txtBuscar.setText("");
                //FondoSistema.sLetras(txtbusca,25);
                //FondoSistema.sLetras(txtbusca1,25);
                c.eventobusqueda(txtBuscar.getText().toString(), tabCatalago, 2);
                txtBuscar.requestFocus();
            } catch (SQLException ex) {
                Logger.getLogger(CatCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buscapor2ItemStateChanged

    private void buscapor1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buscapor1ItemStateChanged
        //stateChanged(evt);
        if (buscapor1.isSelected()) {
            try {
                campobus  = "nombremd";
                txtBuscar.setText("");
                //FondoSistema.sNumeros(txtbusca,4);
                //FondoSistema.sNumeros(txtbusca1,4);
                c.eventobusqueda(txtBuscar.getText().toString(), tabCatalago, 2);
                txtBuscar.requestFocus(true);
            } catch (SQLException ex) {
                Logger.getLogger(CatCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buscapor1ItemStateChanged

    private void buscapor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscapor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscapor1ActionPerformed

    private void closeDialog(java.awt.event.WindowEvent evt) {                             
        setVisible(false);
        dispose();
    }          
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CatModulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatModulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatModulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatModulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CatModulo dialog = null;
                dialog = new CatModulo(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup btgordena;
    public rojerusan.RSMaterialButtonRectangle btnAceptar;
    public rojerusan.RSMaterialButtonRectangle btnCancelar;
    public static javax.swing.JRadioButton buscapor1;
    public static javax.swing.JRadioButton buscapor2;
    public javax.swing.JLabel etiBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabCatalago;
    public javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
