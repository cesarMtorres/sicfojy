/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ctrlBitacora;
import com.toedter.calendar.JDateChooser;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import rojerusan.RSMaterialButtonRectangle;

/**
 *
 * @author abe
 */
public class Bitacora extends javax.swing.JDialog {
     Date hoy= new Date();
    CatUsuario vcatUsuario;
    ctrlBitacora con = new ctrlBitacora(this);

    /**
     * Creates new form Bitacor
     * @param parent
     * @param modal
     */
    public Bitacora(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         con.llenar_tabla();
        tabla_bitacora.setEnabled(false);
        this.getFecha_Desde().setDate(hoy);
        this.getFecha_Hasta().setDate(hoy);
         this.setLocationRelativeTo(null);
       this.setResizable(false);
    }
    
     public JLabel getId() {
        return id;
    }

    public void setId(JLabel id) {
        this.id = id;
    }

    
    public ctrlBitacora getCon() {
        return con;
    }

    public void setCon(ctrlBitacora con) {
        this.con = con;
    }

    public JComboBox<String> getAccion() {
        return Accion;
    }

    public void setAccion(JComboBox<String> Accion) {
        this.Accion = Accion;
    }

    public JButton getBuscarPersonal() {
        return BuscarPersonal;
    }

    public void setBuscarPersonal(JButton BuscarPersonal) {
        this.BuscarPersonal = BuscarPersonal;
    }

    public JLabel getEtiquetaDesde() {
        return EtiquetaDesde;
    }

    public void setEtiquetaDesde(JLabel EtiquetaDesde) {
        this.EtiquetaDesde = EtiquetaDesde;
    }

    public JLabel getEtiquetaFechaHasta() {
        return EtiquetaFechaHasta;
    }

    public void setEtiquetaFechaHasta(JLabel EtiquetaFechaHasta) {
        this.EtiquetaFechaHasta = EtiquetaFechaHasta;
    }


   
    public JLabel getEtiquetaFechaHasta3() {
        return EtiquetaFechaHasta3;
    }

    public void setEtiquetaFechaHasta3(JLabel EtiquetaFechaHasta3) {
        this.EtiquetaFechaHasta3 = EtiquetaFechaHasta3;
    }

    public JLabel getEtiquetaFechaHasta4() {
        return EtiquetaFechaHasta4;
    }

    public void setEtiquetaFechaHasta4(JLabel EtiquetaFechaHasta4) {
        this.EtiquetaFechaHasta4 = EtiquetaFechaHasta4;
    }

    public JLabel getEtiquetaNombre() {
        return EtiquetaNombre;
    }

    public void setEtiquetaNombre(JLabel EtiquetaNombre) {
        this.EtiquetaNombre = EtiquetaNombre;
    }

    public JDateChooser getFecha_Desde() {
        return Fecha_Desde;
    }

    public void setFecha_Desde(JDateChooser Fecha_Desde) {
        this.Fecha_Desde = Fecha_Desde;
    }

    public JDateChooser getFecha_Hasta() {
        return Fecha_Hasta;
    }

    public void setFecha_Hasta(JDateChooser Fecha_Hasta) {
        this.Fecha_Hasta = Fecha_Hasta;
    }

    public JComboBox<String> getFormulario() {
        return Formulario;
    }

    public void setFormulario(JComboBox<String> Formulario) {
        this.Formulario = Formulario;
    }

    public static JTextField getPersona() {
        return Persona;
    }

    public static void setPersona(JTextField Persona) {
        Bitacora.Persona = Persona;
    }




    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = (RSMaterialButtonRectangle) btnCancelar;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public void setBtnConsultar(JButton btnConsultar) {
        this.btnConsultar = (RSMaterialButtonRectangle) btnConsultar;
    }

    public JLabel getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(JLabel fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public JLabel getjLabel13() {
        return jLabel13;
    }

    public void setjLabel13(JLabel jLabel13) {
        this.jLabel13 = jLabel13;
    }

   
    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public void setjPanel2(JPanel jPanel2) {
        this.jPanel2 = jPanel2;
    }


    public JPanel getjPanel4() {
        return jPanel4;
    }

    public void setjPanel4(JPanel jPanel4) {
        this.jPanel4 = jPanel4;
    }


    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }
/*
    public JSeparator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JLabel getOp() {
        return op;
    }

    public void setOp(JLabel op) {
        this.op = op;
    }
*/
    public JTable getTabla_bitacora() {
        return tabla_bitacora;
    }

    public void setTabla_bitacora(JTable tabla_bitacora) {
        this.tabla_bitacora = tabla_bitacora;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        id = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        EtiquetaNombre = new javax.swing.JLabel();
        Persona = new javax.swing.JTextField();
        EtiquetaFechaHasta = new javax.swing.JLabel();
        Fecha_Hasta = new com.toedter.calendar.JDateChooser();
        EtiquetaDesde = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_bitacora = new javax.swing.JTable();
        Fecha_Desde = new com.toedter.calendar.JDateChooser();
        fechaSolicitud = new javax.swing.JLabel();
        BuscarPersonal = new javax.swing.JButton();
        EtiquetaFechaHasta3 = new javax.swing.JLabel();
        EtiquetaFechaHasta4 = new javax.swing.JLabel();
        Formulario = new javax.swing.JComboBox<String>();
        Accion = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new rojerusan.RSMaterialButtonRectangle();
        btnConsultar = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel13.setForeground(java.awt.Color.lightGray);
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 3));

        EtiquetaNombre.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        EtiquetaNombre.setForeground(new java.awt.Color(51, 51, 51));
        EtiquetaNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaNombre.setText("Usuario:");
        EtiquetaNombre.setEnabled(false);

        Persona.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        Persona.setForeground(java.awt.Color.darkGray);
        Persona.setToolTipText("Usuario");
        Persona.setBorder(null);
        Persona.setEnabled(false);
        Persona.setFocusCycleRoot(true);
        Persona.setFocusTraversalPolicyProvider(true);
        Persona.setOpaque(false);
        Persona.setSelectionColor(new java.awt.Color(15, 19, 23));
        Persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PersonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PersonaKeyTyped(evt);
            }
        });

        EtiquetaFechaHasta.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        EtiquetaFechaHasta.setForeground(new java.awt.Color(51, 51, 51));
        EtiquetaFechaHasta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaFechaHasta.setText("Fecha Desde:");
        EtiquetaFechaHasta.setEnabled(false);

        Fecha_Hasta.setBorder(null);
        Fecha_Hasta.setEnabled(true);

        EtiquetaDesde.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        EtiquetaDesde.setForeground(new java.awt.Color(51, 51, 51));
        EtiquetaDesde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaDesde.setText("Fecha Hasta:");
        EtiquetaDesde.setEnabled(false);

        tabla_bitacora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_bitacora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_bitacoraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_bitacora);

        Fecha_Desde.setBorder(null);
        Fecha_Desde.setEnabled(true);

        fechaSolicitud.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        fechaSolicitud.setForeground(java.awt.Color.gray);
        fechaSolicitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechaSolicitud.setVisible(false);

        BuscarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/agregar.png"))); // NOI18N
        BuscarPersonal.setContentAreaFilled(false);
        BuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarPersonalActionPerformed(evt);
            }
        });

        EtiquetaFechaHasta3.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        EtiquetaFechaHasta3.setForeground(new java.awt.Color(51, 51, 51));
        EtiquetaFechaHasta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaFechaHasta3.setText("Formulario:");
        EtiquetaFechaHasta.setEnabled(false);

        EtiquetaFechaHasta4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        EtiquetaFechaHasta4.setForeground(new java.awt.Color(51, 51, 51));
        EtiquetaFechaHasta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaFechaHasta4.setText("Acción:");
        EtiquetaFechaHasta.setEnabled(false);

        Formulario.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        Formulario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "empleado", "estudiante", "bien", "tipo bien", "cargo", "catedra", "modulo", "marca", "modelo", "profesor", "programa", "instrumento", "tipo instrumento", "movimiento bien" }));
        Formulario.setBorder(null);
        Formulario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormularioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FormularioMouseEntered(evt);
            }
        });
        Formulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormularioActionPerformed(evt);
            }
        });

        Accion.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        Accion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Eliminar", "Modificar", "Registrar" }));
        Accion.setBorder(null);
        Accion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AccionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AccionMouseEntered(evt);
            }
        });
        Accion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(EtiquetaNombre)
                                .addGap(69, 69, 69)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(Persona, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(Fecha_Desde, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(EtiquetaDesde))))
                            .addComponent(EtiquetaFechaHasta))
                        .addGap(18, 18, 18)
                        .addComponent(Fecha_Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EtiquetaFechaHasta3)
                            .addComponent(EtiquetaFechaHasta4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Accion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Formulario, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(87, 87, 87)
                .addComponent(fechaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Persona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EtiquetaNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Fecha_Desde, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EtiquetaFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(BuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                        .addComponent(EtiquetaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Fecha_Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(48, 48, 48))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaSolicitud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EtiquetaFechaHasta3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Formulario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EtiquetaFechaHasta4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Accion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        BuscarPersonal.addActionListener(con);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("Bitácora");

        jPanel2.setOpaque(false);

        btnCancelar.setText("CANCELAR");

        btnConsultar.setText("CONSULTAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(388, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(378, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );

        btnCancelar.addActionListener(con);
        btnConsultar.addActionListener(con);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PersonaKeyReleased
        //controladorPermiso.filtro_persona_cedula();

        // TODO add your handling code here:
    }//GEN-LAST:event_PersonaKeyReleased

    private void PersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PersonaKeyTyped

    }//GEN-LAST:event_PersonaKeyTyped

    private void tabla_bitacoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_bitacoraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_bitacoraMouseClicked

    private void BuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarPersonalActionPerformed

    }//GEN-LAST:event_BuscarPersonalActionPerformed

    private void FormularioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormularioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FormularioMouseClicked

    private void FormularioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormularioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_FormularioMouseEntered

    private void FormularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormularioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FormularioActionPerformed

    private void AccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AccionMouseClicked

    private void AccionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AccionMouseEntered

    private void AccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccionActionPerformed

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
            java.util.logging.Logger.getLogger(Bitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new Bitacora(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Accion;
    public javax.swing.JButton BuscarPersonal;
    private javax.swing.JLabel EtiquetaDesde;
    private javax.swing.JLabel EtiquetaFechaHasta;
    private javax.swing.JLabel EtiquetaFechaHasta3;
    private javax.swing.JLabel EtiquetaFechaHasta4;
    private javax.swing.JLabel EtiquetaNombre;
    private com.toedter.calendar.JDateChooser Fecha_Desde;
    private com.toedter.calendar.JDateChooser Fecha_Hasta;
    private javax.swing.JComboBox<String> Formulario;
    public static javax.swing.JTextField Persona;
    public rojerusan.RSMaterialButtonRectangle btnCancelar;
    public rojerusan.RSMaterialButtonRectangle btnConsultar;
    private javax.swing.JLabel fechaSolicitud;
    public javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_bitacora;
    // End of variables declaration//GEN-END:variables
}
