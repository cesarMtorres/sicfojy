/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlRespRestBD;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import modelo.ModeloRespRestBD;
import rojerusan.RSMaterialButtonRectangle;

/**
 *
 * @author abe
 */
public class RespRestBD extends NewClass {
    ModeloRespRestBD modelo= new ModeloRespRestBD();
     CtrlRespRestBD controlador = new CtrlRespRestBD(this);

    /**
     * Creates new form RespRestB
     */
    public RespRestBD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        
        setLocationRelativeTo(this);
        this.setResizable(false);
        
        btnRutaSource.addActionListener(controlador);
        btnDerecha.addActionListener(controlador);
        btnIzquierda.addActionListener(controlador);
        btnRespaldar.addActionListener(controlador);
        btnRestaurar.addActionListener(controlador);

        radioParcial3.addActionListener(controlador);
        radioTotal3.addActionListener(controlador);

        radioSql3.addActionListener(controlador);
        radioBackup3.addActionListener(controlador);
        
        
        getBtnRespaldar().setEnabled(false);
        //Preselección de "Parcial"
        getRadioParcial().setSelected(true);

        //Cargar tablas de base de datos en 'Listado Rojo'
        getListadoTablas().setModel(modelo.ConsultarTablas() );
        //Inicializar 'Listado verde' (Limpiar)
        getListadoRespaldar().setModel( new DefaultListModel() );

        //Detectar el Sistema Operativo
        getLabelSO().setText( System.getProperty("os.name") );
    
    }


    public JButton getBtnRespaldar() {
        return btnRespaldar;
    }

    public void setBtnRespaldar(JButton btnRespaldar) {
        this.btnRespaldar = (RSMaterialButtonRectangle) btnRespaldar;
    }

    public JButton getBtnRestaurar() {
        return btnRestaurar;
    }

    public void setBtnRestaurar(JButton btnRestaurar) {
        this.btnRestaurar = (RSMaterialButtonRectangle)  btnRestaurar;
    }

    public JRadioButton getRadioBackup() {
        return radioBackup3;
    }

    public void setRadioBackup(JRadioButton radioBackup) {
        this.radioBackup3 = radioBackup;
    }

    public JRadioButton getRadioParcial() {
        return radioParcial3;
    }

    public void setRadioParcial(JRadioButton radioParcial) {
        this.radioParcial3 = radioParcial;
    }

    public JRadioButton getRadioSql() {
        return radioSql3;
    }

    public void setRadioSql(JRadioButton radioSql) {
        this.radioSql3 = radioSql;
    }

    public JRadioButton getRadioTotal() {
        return radioTotal3;
    }

    public void setRadioTotal(JRadioButton radioTotal) {
        this.radioTotal3 = radioTotal;
    }

    public JButton getBtnDerecha() {
        return btnDerecha;
    }

    public void setBtnDerecha(JButton btnDerecha) {
        this.btnDerecha = btnDerecha;
    }

    public JButton getBtnIzquierda() {
        return btnIzquierda;
    }

    public void setBtnIzquierda(JButton btnIzquierda) {
        this.btnIzquierda = btnIzquierda;
    }

    public JButton getBtnRutaSource() {
        return btnRutaSource;
    }

    public void setBtnRutaSource(JButton btnRutaSource) {
        this.btnRutaSource = btnRutaSource;
    }

    public JList getListadoRespaldar() {
        return listadoRespaldar;
    }

    public void setListadoRespaldar(JList listadoRespaldar) {
        this.listadoRespaldar = listadoRespaldar;
    }

    public JList getListadoTablas() {
        return listadoTablas;
    }

    public void setListadoTablas(JList listadoTablas) {
        this.listadoTablas = listadoTablas;
    }

    public JLabel getLabelSO() {
        return labelSO;
    }

    public void setLabelSO(JLabel labelSO) {
        this.labelSO = labelSO;
    }

    public JLabel getLabelSource() {
        return labelSource;
    }

    public void setLabelSource(JLabel labelSource) {
        this.labelSource = labelSource;
    }

    public ButtonGroup getFormatoSalida() {
        return FormatoSalida;
    }

    public void setFormatoSalida(ButtonGroup FormatoSalida) {
        this.FormatoSalida = FormatoSalida;
    }

    public ButtonGroup getTipoRespaldo() {
        return TipoRespaldo;
    }

    public void setTipoRespaldo(ButtonGroup TipoRespaldo) {
        this.TipoRespaldo = TipoRespaldo;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FormatoSalida = new javax.swing.ButtonGroup();
        TipoRespaldo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelSource = new javax.swing.JLabel();
        btnRutaSource = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        labelSO = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listadoTablas = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listadoRespaldar = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        btnDerecha = new javax.swing.JButton();
        btnIzquierda = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        radioParcial3 = new javax.swing.JRadioButton();
        radioTotal3 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        radioSql3 = new javax.swing.JRadioButton();
        radioBackup3 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btnCancelar = new rojerusan.RSMaterialButtonRectangle();
        btnRestaurar = new rojerusan.RSMaterialButtonRectangle();
        btnRespaldar = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("Respladar y Restaurar");

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 3));

        jLabel2.setFont(new java.awt.Font("Ubuntu Light", 1, 14)); // NOI18N
        jLabel2.setText("Guardan en :");

        labelSource.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnRutaSource.setText("...");
        btnRutaSource.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setFont(new java.awt.Font("Ubuntu Light", 1, 14)); // NOI18N
        jLabel7.setText(" Sistema Operativo :");

        labelSO.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        listadoTablas.setBackground(new java.awt.Color(254, 254, 254));
        listadoTablas.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        listadoTablas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(listadoTablas);

        jLabel5.setFont(new java.awt.Font("Ubuntu Light", 1, 15)); // NOI18N
        jLabel5.setText("Seleccione las tablas:");

        listadoRespaldar.setBackground(new java.awt.Color(254, 254, 254));
        listadoRespaldar.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        listadoRespaldar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(listadoRespaldar);

        jLabel6.setFont(new java.awt.Font("Ubuntu Light", 1, 15)); // NOI18N
        jLabel6.setText("Selección :");

        btnDerecha.setFont(new java.awt.Font("Khmer OS System", 1, 15)); // NOI18N
        btnDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Flecha.png"))); // NOI18N
        btnDerecha.setBorderPainted(false);
        btnDerecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnIzquierda.setFont(new java.awt.Font("Khmer OS System", 1, 15)); // NOI18N
        btnIzquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Flecha undo.png"))); // NOI18N
        btnIzquierda.setBorderPainted(false);
        btnIzquierda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel7.setOpaque(false);

        jLabel12.setFont(new java.awt.Font("Ubuntu Light", 1, 14)); // NOI18N
        jLabel12.setText(" Tipo Respaldo :");

        radioParcial3.setBackground(new java.awt.Color(254, 254, 254));
        radioParcial3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        radioParcial3.setText("Parcial");
        radioParcial3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        radioTotal3.setBackground(new java.awt.Color(254, 254, 254));
        radioTotal3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        radioTotal3.setText("Total");
        radioTotal3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel13.setFont(new java.awt.Font("Ubuntu Light", 1, 14)); // NOI18N
        jLabel13.setText("Formato Salida :");

        radioSql3.setBackground(new java.awt.Color(254, 254, 254));
        radioSql3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        radioSql3.setText(".sql");
        radioSql3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        radioBackup3.setBackground(new java.awt.Color(254, 254, 254));
        radioBackup3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        radioBackup3.setText(".backup");
        radioBackup3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(radioParcial3)
                    .addComponent(radioTotal3))
                .addGap(77, 77, 77)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioBackup3)
                    .addComponent(radioSql3)
                    .addComponent(jLabel13))
                .addGap(8, 8, 8))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioParcial3)
                    .addComponent(radioSql3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioTotal3)
                    .addComponent(radioBackup3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSO, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(labelSource, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRutaSource))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSource, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRutaSource, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSO, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel4.setOpaque(false);

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRestaurar.setText("RESTAURAR");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        btnRespaldar.setText("RESPALDAR");
        btnRespaldar.setEnabled(false);
        btnRespaldar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespaldarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnRespaldar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRespaldar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
           dispose();
           // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void btnRespaldarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespaldarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRespaldarActionPerformed

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
            java.util.logging.Logger.getLogger(RespRestBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespRestBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespRestBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespRestBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new RespRestBD(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FormatoSalida;
    private javax.swing.ButtonGroup TipoRespaldo;
    public rojerusan.RSMaterialButtonRectangle btnCancelar;
    private javax.swing.JButton btnDerecha;
    private javax.swing.JButton btnIzquierda;
    public rojerusan.RSMaterialButtonRectangle btnRespaldar;
    public rojerusan.RSMaterialButtonRectangle btnRestaurar;
    private javax.swing.JButton btnRutaSource;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;

    private javax.swing.JPanel jPanel1;
 
    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel4;
   
    private javax.swing.JPanel jPanel7;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelSO;
    private javax.swing.JLabel labelSource;
    private javax.swing.JList listadoRespaldar;
    private javax.swing.JList listadoTablas;

    private javax.swing.JRadioButton radioBackup3;
 
    private javax.swing.JRadioButton radioParcial3;

    private javax.swing.JRadioButton radioSql3;

    private javax.swing.JRadioButton radioTotal3;
   
    // End of variables declaration//GEN-END:variables
}
