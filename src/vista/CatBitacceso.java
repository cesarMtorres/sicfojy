/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CatPermiso.java
 *
 * Created on 28-abr-2014, 0:15:39
 */

package vista;

import controlador.CtrlCatBitacceso;
import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rover
 */
public class CatBitacceso extends javax.swing.JDialog {
      
    CtrlCatBitacceso c = new CtrlCatBitacceso(this);
    public static int salida = 0;
    public static String campobus = "codopc";
    public TableRowSorter<TableModel> sorter;
    private TableRowSorter trsfiltro;
    private TableModel dftm;

    /** Creates new form CatGeneral
     * @param parent
     * @param modal
     * @throws java.sql.SQLException */
    @SuppressWarnings("empty-statement")
    public CatBitacceso(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        //String codigobus = parent.getText();
        this.setResizable(true);
        this.setPreferredSize(new Dimension(750,586));
        this.setSize(750,585);
        this.setTitle("Catálogo de Bitácora de Accesos - Buscar por: (F6) Código | (F7) Acceso"); 
        
        //if (columna == 9) return Boolean.class;
        //return Object.class;
        
         // Conjunto de teclas que queremos que sirvan para pasar el foco 
        // al siguiente campo de texto: ENTER y TAB
        Set<AWTKeyStroke> teclas = new HashSet<AWTKeyStroke>();
        teclas.add(AWTKeyStroke.getAWTKeyStroke(
                KeyEvent.VK_ENTER, 0));
        teclas.add(AWTKeyStroke.getAWTKeyStroke(
                KeyEvent.VK_TAB, 0));

        initComponents();
        
        cmdtodos.setVisible(false);
        cmdnada.setVisible(false);
       
        JTableHeader header = ptabBitacceso.getTableHeader();
        header.setBackground(new Color(215,215,215));
        header.setForeground(new Color(0,64,128));
        header.setFont(new Font("Verdana", Font.BOLD, 12));
        
        cmdsalir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE),"forward");
        cmdsalir.getActionMap().put("forward", c);
        buscapor1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"),"forward");
        buscapor1.getActionMap().put("forward", c);
        buscapor2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"),"forward");
        buscapor2.getActionMap().put("forward", c);
        
        this.setLocationRelativeTo(null);
        //System.out.println(CtrlPerfil.codperf);
        c.cargarTablaConsultaValores(ptabBitacceso);
        c.eventoDobleClick(ptabBitacceso);
        
        PanelFondo f = new PanelFondo(750,560,"/images/fondo1.jpg");
        this.add(f,BorderLayout.CENTER);
        this.setResizable(false);
    }

    public CatBitacceso(Principal vista, boolean b) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgordena = new javax.swing.ButtonGroup();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        ptabBitacceso = new javax.swing.JTable();
        txtbusca = new javax.swing.JTextField();
        txtbusca1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        buscapor1 = new javax.swing.JRadioButton();
        buscapor2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmdsalir = new javax.swing.JButton();
        cmdacep = new javax.swing.JButton();
        cmdtodos = new javax.swing.JButton();
        cmdnada = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ptabBitacceso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ptabBitacceso.setForeground(new java.awt.Color(0, 64, 128));
        ptabBitacceso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Función", "Usuario", "Fecha", "Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Short.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ptabBitacceso.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        ptabBitacceso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ptabBitacceso.setFillsViewportHeight(true);
        ptabBitacceso.setIntercellSpacing(new java.awt.Dimension(2, 2));
        ptabBitacceso.setName("grid"); // NOI18N
        ptabBitacceso.setNextFocusableComponent(cmdacep);
        ptabBitacceso.setSelectionBackground(new java.awt.Color(0, 64, 128));
        ptabBitacceso.setSelectionForeground(new java.awt.Color(254, 254, 254));
        ptabBitacceso.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ptabBitacceso.setShowHorizontalLines(false);
        ptabBitacceso.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ptabBitacceso);
        ptabBitacceso.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (ptabBitacceso.getColumnModel().getColumnCount() > 0) {
            ptabBitacceso.getColumnModel().getColumn(1).setMinWidth(128);
            ptabBitacceso.getColumnModel().getColumn(1).setMaxWidth(156);
            ptabBitacceso.getColumnModel().getColumn(2).setMinWidth(194);
            ptabBitacceso.getColumnModel().getColumn(2).setMaxWidth(290);
        }

        jLayeredPane1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 80, 570, 390);

        txtbusca.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtbusca.setForeground(new java.awt.Color(0, 64, 128));
        txtbusca.setToolTipText("Buscar por Contenido");
        txtbusca.setCaretColor(new java.awt.Color(0, 64, 128));
        txtbusca.setName("txtcontiene"); // NOI18N
        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txtbusca);
        txtbusca.setBounds(130, 20, 350, 25);

        txtbusca1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtbusca1.setForeground(new java.awt.Color(0, 64, 128));
        txtbusca1.setToolTipText("Buscar por Iniciales");
        txtbusca1.setCaretColor(new java.awt.Color(0, 64, 128));
        txtbusca1.setName("txtempieza"); // NOI18N
        txtbusca1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusca1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbusca1KeyTyped(evt);
            }
        });
        jLayeredPane1.add(txtbusca1);
        txtbusca1.setBounds(130, 50, 350, 25);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 64, 128));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Buscar por:");
        jLabel6.setToolTipText("Buscar por Contenido");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel6.setFocusable(false);
        jLabel6.setName("lblcontiene"); // NOI18N
        jLayeredPane1.add(jLabel6);
        jLabel6.setBounds(480, 10, 90, 30);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 64, 128));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Empieza Por:");
        jLabel7.setToolTipText("Buscar por Iniciales");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setName("lbl1"); // NOI18N
        jLayeredPane1.add(jLabel7);
        jLabel7.setBounds(20, 50, 110, 30);

        btgordena.add(buscapor1);
        buscapor1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        buscapor1.setForeground(new java.awt.Color(0, 64, 128));
        buscapor1.setSelected(true);
        buscapor1.setText("Código");
        buscapor1.setToolTipText("Buscar por Código");
        buscapor1.setContentAreaFilled(false);
        buscapor1.setFocusPainted(false);
        buscapor1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buscapor1ItemStateChanged(evt);
            }
        });
        jLayeredPane1.add(buscapor1);
        buscapor1.setBounds(490, 30, 80, 26);
        // Code adding the component to the parent container - not shown here
        c = new controlador.CtrlCatBitacceso(this);
        buscapor1.addActionListener(c);

        btgordena.add(buscapor2);
        buscapor2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        buscapor2.setForeground(new java.awt.Color(0, 64, 128));
        buscapor2.setText("Nombre");
        buscapor2.setContentAreaFilled(false);
        buscapor2.setFocusPainted(false);
        buscapor2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buscapor2ItemStateChanged(evt);
            }
        });
        jLayeredPane1.add(buscapor2);
        buscapor2.setBounds(490, 50, 80, 26);
        // Code adding the component to the parent container - not shown here
        c = new controlador.CtrlCatBitacceso(this);
        buscapor2.addActionListener(c);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 64, 128));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Contiene:");
        jLabel8.setToolTipText("Buscar por Contenido");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.setFocusable(false);
        jLabel8.setName("lblcontiene"); // NOI18N
        jLayeredPane1.add(jLabel8);
        jLabel8.setBounds(20, 20, 110, 30);

        jLabel1.setFont(new java.awt.Font("URW Chancery L", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 64, 128));
        jLabel1.setText("Bitácora de Accesos al Sistema");
        jLabel1.setToolTipText("Registro de Usuario");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setName("lbl1"); // NOI18N

        cmdsalir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cmdsalir.setForeground(new java.awt.Color(255, 255, 255));
        cmdsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseboton2adm.gif"))); // NOI18N
        cmdsalir.setMnemonic('S');
        cmdsalir.setText("Salir");
        cmdsalir.setToolTipText("Salir");
        cmdsalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdsalir.setDefaultCapable(false);
        cmdsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdsalir.setName("cmdsalir"); // NOI18N

        cmdacep.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cmdacep.setForeground(new java.awt.Color(255, 255, 255));
        cmdacep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseboton2adm.gif"))); // NOI18N
        cmdacep.setMnemonic('R');
        cmdacep.setText("Reporte");
        cmdacep.setToolTipText("Reporte");
        cmdacep.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdacep.setDefaultCapable(false);
        cmdacep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdacep.setName("cmdacep"); // NOI18N

        cmdtodos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cmdtodos.setForeground(new java.awt.Color(255, 255, 255));
        cmdtodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseboton2adm.gif"))); // NOI18N
        cmdtodos.setMnemonic('B');
        cmdtodos.setText("Borrar");
        cmdtodos.setToolTipText("Borrar");
        cmdtodos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdtodos.setDefaultCapable(false);
        cmdtodos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdtodos.setName("cmdborra"); // NOI18N

        cmdnada.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cmdnada.setForeground(new java.awt.Color(255, 255, 255));
        cmdnada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseboton2adm.gif"))); // NOI18N
        cmdnada.setMnemonic('C');
        cmdnada.setText("Criterio");
        cmdnada.setToolTipText("Criterio");
        cmdnada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdnada.setDefaultCapable(false);
        cmdnada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdnada.setName("cmdcrit"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdacep, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdtodos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdnada, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(cmdtodos, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cmdnada, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdacep, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        c = new controlador.CtrlCatBitacceso(this);
        cmdsalir.addActionListener(c);
        c = new controlador.CtrlCatBitacceso(this);
        cmdacep.addActionListener(c);
        c = new controlador.CtrlCatBitacceso(this);
        cmdtodos.addActionListener(c);
        c = new controlador.CtrlCatBitacceso(this);
        cmdnada.addActionListener(c);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyReleased
        //if(!txtbusca.getText().equals("")){
        //try {
            //c.eventobusqueda(txtbusca.getText().toString(), ptabPermiso, 0);
        //    filtrar(0);
        //} catch (SQLException ex) {
        //    Logger.getLogger(CatPermiso.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //}
    }//GEN-LAST:event_txtbuscaKeyReleased

    private void txtbusca1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusca1KeyReleased
        //if(!txtbusca1.getText().equals("")){
        /*try {
            c.eventobusqueda(txtbusca1.getText().toString(), ptabPermiso, 1);
        } catch (SQLException ex) {
            Logger.getLogger(CatPermiso.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //filtrar(1);
        //}
    }//GEN-LAST:event_txtbusca1KeyReleased

    private void buscapor1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buscapor1ItemStateChanged
        //stateChanged(evt);
        if (buscapor1.isSelected()) {
        //try {
                campobus  = "codopc";
                txtbusca.setText("");
                txtbusca1.setText("");
                //FondoSistema.sNumeros(txtbusca,4);
                //FondoSistema.sNumeros(txtbusca1,4);
                //c.eventobusqueda(txtbusca.getText().toString(), ptabPermiso, 2);
                filtrar(0,0);
                txtbusca.requestFocus(true);
        //} catch (SQLException ex) {
         //       Logger.getLogger(CatPermiso.class.getName()).log(Level.SEVERE, null, ex);
          //}
       }
    }//GEN-LAST:event_buscapor1ItemStateChanged

    private void buscapor2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buscapor2ItemStateChanged
        //stateChanged(evt);
        if (buscapor2.isSelected()) {
        //try {
                campobus  = "descopc";
                txtbusca.setText("");
                txtbusca1.setText("");
                //FondoSistema.sLetras(txtbusca,25);
                //FondoSistema.sLetras(txtbusca1,25);
                //c.eventobusqueda(txtbusca.getText().toString(), ptabPermiso, 2);
                filtrar(1,0);
                txtbusca.requestFocus();
        //} catch (SQLException ex) {
        //    Logger.getLogger(CatPermiso.class.getName()).log(Level.SEVERE, null, ex);
        //}
        }
    }//GEN-LAST:event_buscapor2ItemStateChanged

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        txtbusca.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(final KeyEvent e) {
            String cadena = (txtbusca.getText()).toUpperCase().trim();
            txtbusca.setText(cadena);
            repaint();
            filtrar(buscapor2.isSelected()?1:0,0);
        }
        });
        trsfiltro = new TableRowSorter(ptabBitacceso.getModel());
        ptabBitacceso.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtbuscaKeyTyped

    private void txtbusca1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusca1KeyTyped
        txtbusca1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(final KeyEvent e) {
            String cadena = (txtbusca1.getText()).toUpperCase().trim();
            txtbusca1.setText(cadena);
            repaint();
            filtrar(buscapor1.isSelected()?0:1,1);
        }
        });
        trsfiltro = new TableRowSorter(ptabBitacceso.getModel());
        ptabBitacceso.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtbusca1KeyTyped

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CatBitacceso dialog = null;
                try {
                    dialog = new CatBitacceso(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(CatBitacceso.class.getName()).log(Level.SEVERE, null, ex);
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
    public void filtrar(int slec, int ptipo) {
        String BuscaV;
        BuscaV = "";
        System.out.println(slec);
        //filtrar (columna, tipo busqueda)
        switch (ptipo) {
            case 0: BuscaV = (ptipo==0)?txtbusca.getText().trim():txtbusca1.getText().trim();break;//por contenido
            case 1: BuscaV = (ptipo==0)?"^"+txtbusca.getText().trim():"^"+txtbusca1.getText().trim();break;//por iniciales
        }
        System.out.println(BuscaV);
        if (!BuscaV.equals("") && !BuscaV.equals("^"))
            trsfiltro.setRowFilter(RowFilter.regexFilter(BuscaV, slec));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgordena;
    public static javax.swing.JRadioButton buscapor1;
    public static javax.swing.JRadioButton buscapor2;
    public javax.swing.JButton cmdacep;
    public javax.swing.JButton cmdnada;
    public javax.swing.JButton cmdsalir;
    public javax.swing.JButton cmdtodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable ptabBitacceso;
    public javax.swing.JTextField txtbusca;
    public javax.swing.JTextField txtbusca1;
    // End of variables declaration//GEN-END:variables

}
