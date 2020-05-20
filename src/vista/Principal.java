/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
//import rsbuttom.RSButtonMetro ;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.ModeloSesion;
import controlador.ctrMenuProceso;
import vista.paneles.CambiaPanel;

/**
 *
 * @author RojeruSan
 */
public class Principal extends javax.swing.JFrame {
static String codigo_personalLocal;
static String perfilLocal;
static String usuarioLocal;
ctrMenuProceso c;
    int x, y;
    /**
     * Creates new form Principal
     */
    
    public Principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
      //  this.setLocationRelativeTo(this);
        this.uno.setSelected(true);
        usuario.setText(usuarioLocal);
        new CambiaPanel(pnlPrincipal, new vista.paneles.pnlHome());
    }

   
    public static String getUsuarioLocal() {
        return usuarioLocal;
    }

    public static void setUsuarioLocal(String usuarioLocal) {
        Principal.usuarioLocal = usuarioLocal;
    }

    public static String getCodigo_personalLocal() {
        return codigo_personalLocal;
    }

    public static void setCodigo_personalLocal(String codigo_personalLocal) {
        Principal.codigo_personalLocal = codigo_personalLocal;
    }

    public static String getPerfilLocal() {
        return perfilLocal;
    }

    public static void setPerfilLocal(String perfilLocal) {
        Principal.perfilLocal = perfilLocal;
    }

    public static JLabel getPerfil() {
        return perfil;
    }

    public static void setPerfil(JLabel perfil) {
        Principal.perfil = perfil;
    }

    public static JLabel getUsuario() {
        return usuario;
    }

    public static void setUsuario(JLabel usuario) {
        Principal.usuario = usuario;
    }



    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        uno = new rsbuttom.RSButtonMetro();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dos = new rsbuttom.RSButtonMetro();
        cinco = new rsbuttom.RSButtonMetro();
        ocho = new rsbuttom.RSButtonMetro();
        diez = new rsbuttom.RSButtonMetro();
        jPanel2 = new javax.swing.JPanel();
        perfil = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlMenu.setBackground(new java.awt.Color(239, 238, 244));
        pnlMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(239, 238, 244)));

        uno.setForeground(new java.awt.Color(128, 128, 131));
        uno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        uno.setText("Inicio");
        uno.setColorHover(new java.awt.Color(204, 204, 204));
        uno.setColorNormal(new java.awt.Color(204, 204, 204));
        uno.setColorPressed(new java.awt.Color(204, 204, 204));
        uno.setColorTextHover(new java.awt.Color(128, 128, 131));
        uno.setColorTextNormal(new java.awt.Color(128, 128, 131));
        uno.setColorTextPressed(new java.awt.Color(128, 128, 131));
        uno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uno.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        uno.setIconTextGap(25);
        uno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                unoMousePressed(evt);
            }
        });
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(239, 238, 244));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(128, 128, 131));
        jLabel1.setText("Menú");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        dos.setBackground(new java.awt.Color(239, 238, 244));
        dos.setForeground(new java.awt.Color(128, 128, 131));
        dos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/collection.png"))); // NOI18N
        dos.setText("Proceso");
        dos.setColorHover(new java.awt.Color(204, 204, 204));
        dos.setColorNormal(new java.awt.Color(239, 238, 244));
        dos.setColorPressed(new java.awt.Color(204, 204, 204));
        dos.setColorTextHover(new java.awt.Color(128, 128, 131));
        dos.setColorTextNormal(new java.awt.Color(128, 128, 131));
        dos.setColorTextPressed(new java.awt.Color(128, 128, 131));
        dos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dos.setIconTextGap(25);
        dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dosMousePressed(evt);
            }
        });
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });

        cinco.setBackground(new java.awt.Color(239, 238, 244));
        cinco.setForeground(new java.awt.Color(128, 128, 131));
        cinco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/menu sistema.png"))); // NOI18N
        cinco.setText("Sistema");
        cinco.setColorHover(new java.awt.Color(204, 204, 204));
        cinco.setColorNormal(new java.awt.Color(239, 238, 244));
        cinco.setColorPressed(new java.awt.Color(204, 204, 204));
        cinco.setColorTextHover(new java.awt.Color(128, 128, 131));
        cinco.setColorTextNormal(new java.awt.Color(128, 128, 131));
        cinco.setColorTextPressed(new java.awt.Color(128, 128, 131));
        cinco.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cinco.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cinco.setIconTextGap(19);
        cinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cincoMousePressed(evt);
            }
        });
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoActionPerformed(evt);
            }
        });

        ocho.setBackground(new java.awt.Color(239, 238, 244));
        ocho.setForeground(new java.awt.Color(128, 128, 131));
        ocho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/menu reporte.png"))); // NOI18N
        ocho.setText("Archivos");
        ocho.setColorHover(new java.awt.Color(204, 204, 204));
        ocho.setColorNormal(new java.awt.Color(239, 238, 244));
        ocho.setColorPressed(new java.awt.Color(204, 204, 204));
        ocho.setColorTextHover(new java.awt.Color(128, 128, 131));
        ocho.setColorTextNormal(new java.awt.Color(128, 128, 131));
        ocho.setColorTextPressed(new java.awt.Color(128, 128, 131));
        ocho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ocho.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ocho.setIconTextGap(19);
        ocho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ochoMousePressed(evt);
            }
        });
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochoActionPerformed(evt);
            }
        });

        diez.setBackground(new java.awt.Color(239, 238, 244));
        diez.setForeground(new java.awt.Color(128, 128, 131));
        diez.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/menu salir.png"))); // NOI18N
        diez.setText("Salir");
        diez.setColorHover(new java.awt.Color(204, 204, 204));
        diez.setColorNormal(new java.awt.Color(239, 238, 244));
        diez.setColorPressed(new java.awt.Color(204, 204, 204));
        diez.setColorTextHover(new java.awt.Color(128, 128, 131));
        diez.setColorTextNormal(new java.awt.Color(128, 128, 131));
        diez.setColorTextPressed(new java.awt.Color(128, 128, 131));
        diez.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        diez.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        diez.setIconTextGap(19);
        diez.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                diezMousePressed(evt);
            }
        });
        diez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diezActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uno, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dos, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cinco, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ocho, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diez, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(uno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ocho, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cinco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diez, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 8.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel1.add(pnlMenu, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(38, 86, 186));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        perfil.setForeground(new java.awt.Color(255, 255, 255));
        perfil.setText("USUARIO:");

        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setText("  ''USUARIO\"");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(540, 540, 540)
                .addComponent(perfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuario)
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuario)
                    .addComponent(perfil))
                .addGap(24, 24, 24))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(jPanel2, gridBagConstraints);

        pnlCentro.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(new javax.swing.BoxLayout(pnlPrincipal, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(pnlPrincipal);

        javax.swing.GroupLayout pnlCentroLayout = new javax.swing.GroupLayout(pnlCentro);
        pnlCentro.setLayout(pnlCentroLayout);
        pnlCentroLayout.setHorizontalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
        pnlCentroLayout.setVerticalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentroLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(pnlCentro, gridBagConstraints);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int posicion = pnlMenu.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 2, 2, pnlMenu);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 2, 2, pnlMenu);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        Point mueve = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(mueve.x - x, mueve.y - y);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void cincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincoActionPerformed
       new CambiaPanel(pnlPrincipal, new vista.paneles.pnlFeeBack());
        if(this.cinco.isSelected()){
            
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(204,204,204));

            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(204,204,204));
            this.dos.setColorPressed(new Color(204,204,204));
            this.uno.setColorPressed(new Color(204,204,204));
/*
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(204,204,204));
            this.tres.setColorPressed(new Color(204,204,204));
*/
//            this.cuatro.setColorNormal(new Color(239,238,244));
//            this.cuatro.setColorHover(new Color(204,204,204));
 //           this.cuatro.setColorPressed(new Color(204,204,204));

            this.cinco.setColorNormal(new Color(204,204,204));
            this.cinco.setColorHover(new Color(204,204,204));
            this.cinco.setColorPressed(new Color(204,204,204));

      /*      this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(204,204,204));
            this.seis.setColorPressed(new Color(204,204,204));

            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(204,204,204));
            this.siete.setColorPressed(new Color(204,204,204));
*/
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(204,204,204));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(204,204,204));
            this.cinco.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_cincoActionPerformed

    private void cincoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cincoMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
    //    this.tres.setSelected(false);
   //     this.cuatro.setSelected(false);
        this.cinco.setSelected(true);
  //      this.seis.setSelected(false);
    //    this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_cincoMousePressed

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosActionPerformed
        new CambiaPanel(pnlPrincipal, new vista.paneles.pnlCollection());
        if(this.cinco.isSelected()){
            
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(204,204,204));

            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(204,204,204));
            this.dos.setColorPressed(new Color(204,204,204));
            this.uno.setColorPressed(new Color(204,204,204));
/*
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(204,204,204));
            this.tres.setColorPressed(new Color(204,204,204));
*/
//            this.cuatro.setColorNormal(new Color(239,238,244));
//            this.cuatro.setColorHover(new Color(204,204,204));
 //           this.cuatro.setColorPressed(new Color(204,204,204));

            this.cinco.setColorNormal(new Color(204,204,204));
            this.cinco.setColorHover(new Color(204,204,204));
            this.cinco.setColorPressed(new Color(204,204,204));

      /*      this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(204,204,204));
            this.seis.setColorPressed(new Color(204,204,204));

            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(204,204,204));
            this.siete.setColorPressed(new Color(204,204,204));
*/
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(204,204,204));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(204,204,204));
            this.cinco.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_dosActionPerformed

    private void dosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(true);
      //  this.tres.setSelected(false);
    //    this.cuatro.setSelected(false);
    //    this.cinco.setSelected(false);
        //this.seis.setSelected(false);
        //this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_dosMousePressed

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
        new CambiaPanel(pnlPrincipal, new vista.paneles.pnlHome());
        if(this.uno.isSelected()){
            this.uno.setColorNormal(new Color(204,204,204));
            this.uno.setColorHover(new Color(204,204,204));
            this.uno.setColorPressed(new Color(204,204,204));

            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(204,204,204));
            this.dos.setColorPressed(new Color(204,204,204));
/*
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(204,204,204));
            this.tres.setColorPressed(new Color(204,204,204));
*/
      //      this.cuatro.setColorNormal(new Color(239,238,244));
      //      this.cuatro.setColorHover(new Color(204,204,204));
      //      this.cuatro.setColorPressed(new Color(204,204,204));

            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(204,204,204));
            this.cinco.setColorPressed(new Color(204,204,204));
/*
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(204,204,204));
            this.seis.setColorPressed(new Color(204,204,204));

            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(204,204,204));
            this.siete.setColorPressed(new Color(204,204,204));
*/
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(204,204,204));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(204,204,204));
            this.uno.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_unoActionPerformed

    private void unoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unoMousePressed
        this.uno.setSelected(true);
        this.dos.setSelected(false);
  //      this.tres.setSelected(false);
       // this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
    //    this.seis.setSelected(false);
      //  this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_unoMousePressed

    private void ochoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ochoActionPerformed
        new CambiaPanel(pnlPrincipal, new vista.paneles.pnlWeb());
        if(this.ocho.isSelected()){
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(204,204,204));
            this.uno.setColorPressed(new Color(204,204,204));

            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(204,204,204));
            this.dos.setColorPressed(new Color(204,204,204));
/*
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(204,204,204));
            this.tres.setColorPressed(new Color(204,204,204));
*/
    //        this.cuatro.setColorNormal(new Color(239,238,244));
     //       this.cuatro.setColorHover(new Color(204,204,204));
     //       this.cuatro.setColorPressed(new Color(204,204,204));

            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(204,204,204));
            this.cinco.setColorPressed(new Color(204,204,204));
/*
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(204,204,204));
            this.seis.setColorPressed(new Color(204,204,204));

            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(204,204,204));
            this.siete.setColorPressed(new Color(204,204,204));
*/
            this.ocho.setColorNormal(new Color(204,204,204));
            this.ocho.setColorHover(new Color(204,204,204));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(204,204,204));
            this.ocho.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_ochoActionPerformed

    private void ochoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ochoMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
  //      this.tres.setSelected(false);
   //     this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
    //    this.seis.setSelected(false);
      //  this.siete.setSelected(false);
        this.ocho.setSelected(true);
    }//GEN-LAST:event_ochoMousePressed

    private void diezMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diezMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_diezMousePressed

    private void diezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diezActionPerformed
         ModeloSesion modelo = new ModeloSesion();
        System.out.println(codigo_personalLocal);
        modelo.actualizarStatus(codigo_personalLocal,"FALSE");
        this.dispose();
        new sesion().setVisible(true);
         Runtime.getRuntime().gc();
        
    }//GEN-LAST:event_diezActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Principal oo=new Principal();
                    oo.setVisible(true);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public rsbuttom.RSButtonMetro cinco;
    public rsbuttom.RSButtonMetro diez;
    public rsbuttom.RSButtonMetro dos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public rsbuttom.RSButtonMetro ocho;
    private static javax.swing.JLabel perfil;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlMenu;
    public javax.swing.JPanel pnlPrincipal;
    public rsbuttom.RSButtonMetro uno;
    public static javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}