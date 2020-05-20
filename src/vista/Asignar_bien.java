/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ctrAsignar_bien;
import java.text.SimpleDateFormat;
import java.util.Date;
import controlador.ctrAsignar_bien;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.ModBien;
import modelo.ModModulo;
import modelo.ModPrograma;
import rojerusan.RSMaterialButtonRectangle;

/**
 *
 * @author abe
 */
public class Asignar_bien extends javax.swing.JDialog {
    ctrAsignar_bien c;
Date hoy= new Date();
SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
String fecha = sdf.format(hoy); 

    /**
     * Creates new form Asignar_bie
     * @param parent
     * @param modal
     */
    public Asignar_bien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        c.LlenarModulo();
      //  c.ListadoMovimiento();
        this.getTxtfecing().setText(fecha);
         this.setLocationRelativeTo(null);
       this.setResizable(false);
    }
    
     public JTextField getCantidad() {
        return cantidad;
    }

    public void setCantidad(JTextField cantidad) {
        this.cantidad = cantidad;
    }

    
    public JLabel getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(JLabel id_modulo) {
        this.id_modulo = id_modulo;
    }

    
    public JLabel getId_programa() {
        return id_programa;
    }

    public void setId_programa(JLabel id_programa) {
        this.id_programa = id_programa;
    }

    
    
    public JButton getBtnCatalago() {
        return btnCatalago;
    }

    public void setBtnCatalago(JButton btnCatalago) {
        this.btnCatalago = (RSMaterialButtonRectangle) btnCatalago;
    }

    
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = (RSMaterialButtonRectangle) btnEliminar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = (RSMaterialButtonRectangle) btnModificar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = (RSMaterialButtonRectangle) btnRegistrar;
    }

    
   /*
    
    public JTable getTabCatalago() {
        return tabCatalago;
    }

    public void setTabCatalago(JTable tabCatalago) {
        this.tabCatalago = tabCatalago;
    }
    
    */



    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = (RSMaterialButtonRectangle) btnCancelar;
    }

  /*
    public JDateChooser getFecha() {
        return fecha;
    }
  public void setFecha(JDateChooser fecha) {
        this.fecha = fecha;
    }

*/

    public JFormattedTextField getTxtfecing() {
        return txtfecing;
    }

    public void setTxtfecing(JFormattedTextField txtfecing) {
        this.txtfecing = txtfecing;
    }
 
 
    public JTextArea getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(JTextArea justificacion) {
        this.justificacion = justificacion;
    }

    public JComboBox<String> getModulo() {
        return modulo;
    }

    public void setModulo(JComboBox<String> modulo) {
        this.modulo = modulo;
    }
    

    public JButton getBtnProfesor() {
        return btnProfesor;
    }

    public void setBtnProfesor(JButton btnProfesor) {
        this.btnProfesor = btnProfesor;
    }

    public JComboBox<String> getPrograma() {
        return programa;
    }

    public void setPrograma(JComboBox<String> programa) {
        this.programa = programa;
    }

    

 

    public JTextField getProfesor() {
        return profesor;
    }

    public void setProfesor(JTextField profesor) {
        this.profesor = profesor;
    }


    public JLabel getId_bien() {
        return id_bien;
    }

    public void setId_bien(JLabel id_bien) {
        this.id_bien = id_bien;
    }

    public JLabel getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(JLabel id_empleado) {
        this.id_empleado = id_empleado;
    }

    public JTextField getNombreBien() {
        return nombreBien;
    }

    public void setNombreBien(JTextField nombreBien) {
        this.nombreBien = nombreBien;
    }

    public JButton getBtnBien() {
        return btnBien;
    }

    public void setBtnBien(JButton btnBien) {
        this.btnBien = btnBien;
    }

    public JLabel getCambiarCantidad() {
        return cambiarCantidad;
    }

    public void setCambiarCantidad(JLabel cambiarCantidad) {
        this.cambiarCantidad = cambiarCantidad;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cambiarCantidad = new javax.swing.JLabel();
        id_modulo = new javax.swing.JLabel();
        id_programa = new javax.swing.JLabel();
        id_bien = new javax.swing.JLabel();
        id_empleado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCatalago = new rojerusan.RSMaterialButtonRectangle();
        btnRegistrar = new rojerusan.RSMaterialButtonRectangle();
        btnModificar = new rojerusan.RSMaterialButtonRectangle();
        btnEliminar = new rojerusan.RSMaterialButtonRectangle();
        btnCancelar = new rojerusan.RSMaterialButtonRectangle();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBien = new javax.swing.JButton();
        modulo = new javax.swing.JComboBox<String>();
        jScrollPane2 = new javax.swing.JScrollPane();
        justificacion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        nombreBien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        programa = new javax.swing.JComboBox<String>();
        txtfecing = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        profesor = new javax.swing.JTextField();
        btnProfesor = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        mensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(950, 500));
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setOpaque(false);

        btnCatalago.setText("Catálago");

        btnRegistrar.setText("Registrar");

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCatalago, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCatalago, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        c= new controlador.ctrAsignar_bien(this);
        btnCatalago.addActionListener(c);
        c=new controlador.ctrAsignar_bien(this);
        btnRegistrar.addActionListener(c);
        c =new controlador.ctrAsignar_bien(this);
        btnModificar.addActionListener(c);
        c =new controlador.ctrAsignar_bien(this);
        btnEliminar.addActionListener(c);
        c=new controlador.ctrAsignar_bien(this);
        btnCancelar.addActionListener(c);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 3));
        jPanel3.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Fecha de asignación:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Programa:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Modúlo:");

        btnBien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/agregar.png"))); // NOI18N
        btnBien.setContentAreaFilled(false);

        modulo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                moduloItemStateChanged(evt);
            }
        });
        modulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloActionPerformed(evt);
            }
        });

        justificacion.setColumns(20);
        justificacion.setRows(5);
        justificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                justificacionKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(justificacion);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Justificación:");

        nombreBien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nombreBien.setEnabled(false);
        nombreBien.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                nombreBienComponentAdded(evt);
            }
        });
        nombreBien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreBienKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Bien:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Cantidad:");

        cantidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
        });

        programa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                programaItemStateChanged(evt);
            }
        });
        programa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programaActionPerformed(evt);
            }
        });

        txtfecing.setBorder(null);
        txtfecing.setForeground(new java.awt.Color(0, 64, 128));
        try {
            txtfecing.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtfecing.setToolTipText("Ingrese Fecha (DD-MM-AAAA");
        txtfecing.setCaretColor(new java.awt.Color(170, 163, 163));
        txtfecing.setDisabledTextColor(new java.awt.Color(170, 163, 163));
        txtfecing.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        txtfecing.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtfecing.setName("txtfecing"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Profésor:");

        profesor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        profesor.setEnabled(false);

        btnProfesor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/agregar.png"))); // NOI18N
        btnProfesor.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nombreBien, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(programa, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modulo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 29, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(profesor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtfecing, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nombreBien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBien, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfecing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(profesor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(modulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(programa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))))
        );

        c=new controlador.ctrAsignar_bien(this);
        btnBien.addActionListener(c);
        c=new controlador.ctrAsignar_bien(this);
        btnProfesor.addActionListener(c);

        jPanel5.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 112, 192));
        jLabel7.setText("Asignar Bien");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        );

        mensaje.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 2, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(id_modulo)
                        .addComponent(id_programa)
                        .addComponent(id_empleado)
                        .addComponent(id_bien))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(id_modulo)
                        .addComponent(id_programa)
                        .addComponent(id_empleado)
                        .addComponent(id_bien))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void programaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_programaActionPerformed

    private void programaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_programaItemStateChanged
        // TODO add your handling code here:
        int posicion=getPrograma().getSelectedIndex();
        String valor = (String) getPrograma().getSelectedItem();
        System.out.println(posicion+" : "+valor);

        if(posicion > -1){

            ModPrograma mdpro= new ModPrograma();
            mdpro.consultaridPrograma(valor);
            id_programa.setText(mdpro.getId());
        }
    }//GEN-LAST:event_programaItemStateChanged

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        // TODO add your handling code here:
        String valor=getCantidad().getText();
        String bien=getNombreBien().getText();
        String valorId2 ="";
        ModBien mdbien= new ModBien();

        try {
            mdbien.consultarCantidad(bien);
            valorId2 = (mdbien.getCantidad());
            cambiarCantidad.setText(valorId2);
            double cantidadBD= Double.parseDouble(valorId2);
            double cantidadInput= Double.parseDouble(this.getCantidad().getText());
            int a=(int) cantidadBD;
            int b=(int) cantidadInput;
            if (a<b)
            {
                this.mensaje.setText("LA CANTIDAD DEL IMBENTARIO ES MENOR");
                this.cantidad.setText("");
            }else{
                this.mensaje.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Asignar_bien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cantidadKeyReleased

    private void nombreBienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreBienKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreBienKeyReleased

    private void nombreBienComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_nombreBienComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreBienComponentAdded

    private void justificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_justificacionKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();

        if(Character.isLowerCase(a)){

            String cad =(""+a).toUpperCase();
            a=cad.charAt(0);
            evt.setKeyChar(a);
        }

        char car = evt.getKeyChar();

        if((car< 'A' || car> 'Z') && (car!=(char)KeyEvent.VK_SPACE && car!=(char)KeyEvent.VK_BACK_SPACE && car!=(char)KeyEvent.VK_ENTER && car!=(char)KeyEvent.VK_DELETE && car != 'Ñ'
            && car != '1' && car != '2' && car != '3'
            && car != '4' && car != '5' && car != '6'
            && car != '7' && car != '8' && car != '9' && car != '0')){

        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, "SOLO LETRAS Y NUMEROS ", "Advertencia",JOptionPane.PLAIN_MESSAGE);

        evt.consume();
        }

        if((justificacion.getText().length()>=50)) evt.consume();
    }//GEN-LAST:event_justificacionKeyTyped

    private void moduloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduloActionPerformed

    private void moduloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_moduloItemStateChanged
        // TODO add your handling code here:
        int posicion=getModulo().getSelectedIndex();
        String valor = (String) getModulo().getSelectedItem();
        System.out.println(posicion+" : "+valor);

        if(posicion > 0){

            ModModulo mdmodu= new ModModulo();
            mdmodu.consultaridmd(valor);
            id_modulo.setText(mdmodu.getId());
            String valorId2 = (mdmodu.getId());

            //  id_municipio.setText(""+valorId2);
            c.Llenar_Programa(valorId2);
            programa.setEnabled(true);
            System.out.println("Imprimir modulo : "+getModulo().getSelectedIndex());
            /*
            String dato=(String) getTxt_municipio().getSelectedItem();
            System.out.println("El municipio en vista es : "+dato);
            //controlador.Llenar_Mun2(dato);
            System.out.println("Municipio cargado en posicion : "+getTxt_municipio().getSelectedIndex());
            */
        }else{
            programa.setEnabled(false);
        }
    }//GEN-LAST:event_moduloItemStateChanged

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
            java.util.logging.Logger.getLogger(Asignar_bien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asignar_bien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asignar_bien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asignar_bien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new Asignar_bien(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBien;
    public rojerusan.RSMaterialButtonRectangle btnCancelar;
    public rojerusan.RSMaterialButtonRectangle btnCatalago;
    public rojerusan.RSMaterialButtonRectangle btnEliminar;
    public rojerusan.RSMaterialButtonRectangle btnModificar;
    private javax.swing.JButton btnProfesor;
    public rojerusan.RSMaterialButtonRectangle btnRegistrar;
    public javax.swing.JLabel cambiarCantidad;
    public javax.swing.JTextField cantidad;
    public javax.swing.JLabel id_bien;
    public javax.swing.JLabel id_empleado;
    public javax.swing.JLabel id_modulo;
    public javax.swing.JLabel id_programa;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea justificacion;
    public javax.swing.JLabel mensaje;
    public javax.swing.JComboBox<String> modulo;
    public javax.swing.JTextField nombreBien;
    public javax.swing.JTextField profesor;
    public javax.swing.JComboBox<String> programa;
    public javax.swing.JFormattedTextField txtfecing;
    // End of variables declaration//GEN-END:variables
}
