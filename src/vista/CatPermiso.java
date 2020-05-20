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

import controlador.ctrCatPermiso;
import controlador.ctrPerfil;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rover
 */
public class CatPermiso extends javax.swing.JDialog {
      
    ctrCatPermiso c = new ctrCatPermiso(this);
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
    public CatPermiso(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        //String codigobus = parent.getText();
        this.setResizable(true);
        this.setPreferredSize(new Dimension(750,586));
        this.setSize(750,585);
        this.setTitle("Catálogo de Función - Buscar por: (F6) Código | (F7) Función"); 
        
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
       
        JTableHeader header = ptabPermiso.getTableHeader();
        //header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Verdana", Font.BOLD, 12));
        
        cmdsalir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE),"forward");
        cmdsalir.getActionMap().put("forward", c);
        buscapor1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"),"forward");
        buscapor1.getActionMap().put("forward", c);
        buscapor2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"),"forward");
        buscapor2.getActionMap().put("forward", c);
        
        this.setLocationRelativeTo(null);
        //System.out.println(CtrlPerfil.codperf);
        c.cargarTablaConsultaValores(ptabPermiso, ctrPerfil.codperf);
        
        c.eventoDobleClick(ptabPermiso);
    }

   public CatPermiso(Principal vista, boolean b) {
         //To change body of generated methods, choose Tools | Templates.
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgordena = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        ptabPermiso = new javax.swing.JTable();
        txtbusca = new javax.swing.JTextField();
        txtbusca1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        buscapor1 = new javax.swing.JRadioButton();
        buscapor2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmdnada = new rojerusan.RSMaterialButtonRectangle();
        cmdtodos = new rojerusan.RSMaterialButtonRectangle();
        cmdacep = new rojerusan.RSMaterialButtonRectangle();
        cmdsalir = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(900, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 3));

        ptabPermiso.setForeground(new java.awt.Color(1, 1, 1));
        ptabPermiso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Función", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ptabPermiso.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        ptabPermiso.setColumnSelectionAllowed(true);
        ptabPermiso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ptabPermiso.setFocusable(false);
        ptabPermiso.setIntercellSpacing(new java.awt.Dimension(2, 2));
        ptabPermiso.setName("grid"); // NOI18N
        ptabPermiso.setSelectionBackground(new java.awt.Color(0, 64, 128));
        ptabPermiso.setSelectionForeground(new java.awt.Color(254, 254, 254));
        ptabPermiso.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ptabPermiso.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ptabPermiso);
        ptabPermiso.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (ptabPermiso.getColumnModel().getColumnCount() > 0) {
            ptabPermiso.getColumnModel().getColumn(1).setMinWidth(398);
            ptabPermiso.getColumnModel().getColumn(1).setMaxWidth(426);
            ptabPermiso.getColumnModel().getColumn(2).setMinWidth(94);
            ptabPermiso.getColumnModel().getColumn(2).setMaxWidth(190);
        }

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

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 64, 128));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Empieza Por:");
        jLabel7.setToolTipText("Buscar por Iniciales");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setName("lbl1"); // NOI18N

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
        buscapor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscapor2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 64, 128));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Contiene:");
        jLabel8.setToolTipText("Buscar por Contenido");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.setFocusable(false);
        jLabel8.setName("lblcontiene"); // NOI18N

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbusca1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscapor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buscapor2, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                        .addGap(139, 139, 139))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscapor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbusca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscapor2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtbusca, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtbusca1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(buscapor1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        // Code adding the component to the parent container - not shown here
        c = new controlador.ctrCatPermiso(this);
        buscapor1.addActionListener(c);
        jLayeredPane1.setLayer(buscapor2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        // Code adding the component to the parent container - not shown here
        c = new controlador.ctrCatPermiso(this);
        buscapor2.addActionListener(c);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel2.setOpaque(false);

        cmdnada.setText("NINGUNO");

        cmdtodos.setText("TODOS");

        cmdacep.setText("ACEPTAR");

        cmdsalir.setText("CANCELAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdtodos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdnada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdacep, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdacep, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdnada, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdtodos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        c = new controlador.ctrCatPermiso(this);
        cmdnada.addActionListener(c);
        c = new controlador.ctrCatPermiso(this);
        cmdtodos.addActionListener(c);
        c = new controlador.ctrCatPermiso(this);
        cmdacep.addActionListener(c);
        c = new controlador.ctrCatPermiso(this);
        cmdsalir.addActionListener(c);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("Permisos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
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
                campobus  = "nombre";
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
        trsfiltro = new TableRowSorter(ptabPermiso.getModel());
        ptabPermiso.setRowSorter(trsfiltro);
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
        trsfiltro = new TableRowSorter(ptabPermiso.getModel());
        ptabPermiso.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtbusca1KeyTyped

    private void buscapor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscapor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscapor2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CatPermiso dialog = null;
                try {
                    dialog = new CatPermiso(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(CatPermiso.class.getName()).log(Level.SEVERE, null, ex);
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
    public rojerusan.RSMaterialButtonRectangle cmdacep;
    public rojerusan.RSMaterialButtonRectangle cmdnada;
    public rojerusan.RSMaterialButtonRectangle cmdsalir;
    public rojerusan.RSMaterialButtonRectangle cmdtodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable ptabPermiso;
    public javax.swing.JTextField txtbusca;
    public javax.swing.JTextField txtbusca1;
    // End of variables declaration//GEN-END:variables

}
