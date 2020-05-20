/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.toedter.calendar.JDateChooser;
import controlador.ctrPrestamo;
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
import modelo.ModCatedra;
import modelo.ModProfesor;
import modelo.ModModulo;
import modelo.ModEstudiante;
import modelo.ModTipoPrestamo;
import rojerusan.RSMaterialButtonRectangle;

/**
 *
 * @author abe
 */
public class Prestamo extends javax.swing.JDialog {
    ctrPrestamo c;
CatOrquestas orquesta;
Date hoy= new Date();
SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
String fecha = sdf.format(hoy); 

    /**
     * Creates new form Prestam
     * @param parent
     * @param modal
     */
    public Prestamo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         c.LlenarCatedra();
        c.LlenarTipoPrestamo();
        c.LlenarProfesor();
        c.LlenarEstudiante();
        fechaEmision.setDate(hoy);
        fechaDevolucion.setDate(hoy);
         this.setLocationRelativeTo(null);
       this.setResizable(false);
    }
     public JLabel getId_catedra() {
        return id_catedra;
    }

    public void setId_catedra(JLabel id_catedra) {
        this.id_catedra = id_catedra;
    }

    public JLabel getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(JLabel id_responsable) {
        this.id_responsable = id_responsable;
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

    public JComboBox<String> getProfesor() {
        return profesor;
    }

    public JComboBox<String> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(JComboBox<String> estudiante) {
        this.estudiante = estudiante;
    }

    public JTextField getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(JTextField instrumento) {
        this.instrumento = instrumento;
    }

    
    public void setProfesor(JComboBox<String> profesor) {
        this.profesor = profesor;
    }

    
    public JLabel getId_bien() {
        return id_bien;
    }

    public JComboBox<String> getCatedra() {
        return catedra;
    }

    public void setCatedra(JComboBox<String> catedra) {
        this.catedra = catedra;
    }

    
    public void setId_bien(JLabel id_bien) {
        this.id_bien = id_bien;
    }

    public JLabel getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(JLabel id_estudiante) {
        this.id_estudiante = id_estudiante;
    }


    public JDateChooser getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(JDateChooser FechaDevolucion) {
        this.fechaDevolucion = FechaDevolucion;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = (RSMaterialButtonRectangle) btnCancelar;
    }

    public JComboBox<String> getBtnCatedra() {
        return catedra;
    }

    public void setBtnCatedra(JComboBox<String> btnCatedra) {
        this.catedra = btnCatedra;
    }

    public JLabel getCambiarCantidad() {
        return cambiarCantidad;
    }

    public void setCambiarCantidad(JLabel cambiarCantidad) {
        this.cambiarCantidad = cambiarCantidad;
    }



    public JButton getBtnInstrumento() {
        return btnInstrumento;
    }

    public void setBtnInstrumento(JButton btnInstrumento) {
        this.btnInstrumento = btnInstrumento;
    }



    public JComboBox<String> getBtnTipoPrestamo() {
        return btnTipoPrestamo;
    }

    public void setBtnTipoPrestamo(JComboBox<String> btnTipoPrestamo) {
        this.btnTipoPrestamo = btnTipoPrestamo;
    }

    public JTextField getCantidad() {
        return cantidad;
    }

    public void setCantidad(JTextField cantidad) {
        this.cantidad = cantidad;
    }


  

    public JDateChooser getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(JDateChooser fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public JTextField getNombreInstrumento() {
        return instrumento;
    }

    public void setNombreInstrumento(JTextField nombreInstrumento) {
        this.instrumento = nombreInstrumento;
    }

    public JTextArea getObservacion() {
        return observacion;
    }

    public void setObservacion(JTextArea observacion) {
        this.observacion = observacion;
    }

/*    public JTable getTabCatalago() {
        return tabCatalago;
    }

    public void setTabCatalago(JTable tabCatalago) {
        this.tabCatalago = tabCatalago;
    }

    */
    
    public JLabel getDias() {
        return dias;
    }

    public void setDias(JLabel dias) {
        this.dias = dias;
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
        id_bien = new javax.swing.JLabel();
        id_estudiante = new javax.swing.JLabel();
        id_responsable = new javax.swing.JLabel();
        id_catedra = new javax.swing.JLabel();
        id_modulo = new javax.swing.JLabel();
        id_tp = new javax.swing.JLabel();
        dias = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        instrumento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnTipoPrestamo = new javax.swing.JComboBox<String>();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        fechaDevolucion = new com.toedter.calendar.JDateChooser();
        fechaEmision = new com.toedter.calendar.JDateChooser();
        btnInstrumento = new javax.swing.JButton();
        catedra = new javax.swing.JComboBox<String>();
        profesor = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        estudiante = new javax.swing.JComboBox<String>();
        cantidad = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnCatalago = new rojerusan.RSMaterialButtonRectangle();
        btnRegistrar = new rojerusan.RSMaterialButtonRectangle();
        btnModificar = new rojerusan.RSMaterialButtonRectangle();
        btnEliminar = new rojerusan.RSMaterialButtonRectangle();
        btnCancelar = new rojerusan.RSMaterialButtonRectangle();
        mensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(950, 550));
        setResizable(false);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("Préstamo de instrumento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 3));
        jPanel2.setOpaque(false);
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Instrumento:");

        instrumento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        instrumento.setEnabled(false);
        instrumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instrumentoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Responsable:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Fecha de devolución:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Cantidad:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Tipo Prestamo:");

        btnTipoPrestamo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnTipoPrestamoItemStateChanged(evt);
            }
        });
        btnTipoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoPrestamoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Justificación");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Fecha de emision:");

        observacion.setColumns(20);
        observacion.setRows(5);
        observacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                observacionKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(observacion);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Catedra:");

        btnInstrumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/agregar.png"))); // NOI18N
        btnInstrumento.setContentAreaFilled(false);
        btnInstrumento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInstrumentoMouseClicked(evt);
            }
        });
        btnInstrumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstrumentoActionPerformed(evt);
            }
        });

        catedra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                catedraItemStateChanged(evt);
            }
        });
        catedra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catedraActionPerformed(evt);
            }
        });

        profesor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                profesorItemStateChanged(evt);
            }
        });
        profesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profesorActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Estudiante:");

        estudiante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estudianteItemStateChanged(evt);
            }
        });

        cantidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fechaDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(estudiante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profesor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTipoPrestamo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(catedra, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(instrumento, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(btnInstrumento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInstrumento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(catedra, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(instrumento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(profesor))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTipoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        c = new controlador.ctrPrestamo(this);
        btnInstrumento.addActionListener(c);
        c=new controlador.ctrPrestamo(this);
        catedra.addActionListener(c);
        c=new controlador.ctrPrestamo(this);
        profesor.addActionListener(c);
        c=new controlador.ctrPrestamo(this);
        estudiante.addActionListener(c);

        jPanel4.setOpaque(false);
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        btnCatalago.setText("CATÁLOGO");

        btnRegistrar.setText("REGISTRAR");

        btnModificar.setText("MODIFICAR");
        btnModificar.setEnabled(false);

        btnEliminar.setText("ELIMINAR");
        btnEliminar.setEnabled(false);

        btnCancelar.setText("SALIR");

        mensaje.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnCatalago, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCatalago, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        c= new controlador.ctrPrestamo(this);
        btnCatalago.addActionListener(c);
        c=new controlador.ctrPrestamo(this);
        btnRegistrar.addActionListener(c);
        c =new controlador.ctrPrestamo(this);
        btnModificar.addActionListener(c);
        c =new controlador.ctrPrestamo(this);
        btnEliminar.addActionListener(c);
        c= new controlador.ctrPrestamo(this);
        btnCancelar.addActionListener(c);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(id_responsable)
                        .addComponent(id_tp)
                        .addComponent(id_modulo)
                        .addComponent(id_estudiante)
                        .addComponent(id_bien)
                        .addComponent(dias)
                        .addComponent(cambiarCantidad)
                        .addComponent(id_catedra))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 317, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(id_responsable)
                        .addComponent(id_tp)
                        .addComponent(id_modulo)
                        .addComponent(id_estudiante)
                        .addComponent(id_bien)
                        .addComponent(dias)
                        .addComponent(cambiarCantidad)
                        .addComponent(id_catedra))
                    .addGap(0, 318, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void instrumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instrumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_instrumentoActionPerformed

    private void btnTipoPrestamoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnTipoPrestamoItemStateChanged
        // TODO add your handling code here:
        int posicion=getBtnTipoPrestamo().getSelectedIndex();
        String valor = (String) getBtnTipoPrestamo().getSelectedItem();
        System.out.println(posicion+" : "+valor);

        if(posicion > 0){

            ModTipoPrestamo mdmodu= new ModTipoPrestamo();
            mdmodu.consultaridtp(valor);
            id_tp.setText(mdmodu.getId());
        }

        if (("ORQUESTA").equals(valor)){
            btnRegistrar.setText("AGREGAR");
        }else if (("SOLISTA").equals(valor)){
        }
    }//GEN-LAST:event_btnTipoPrestamoItemStateChanged

    private void btnTipoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTipoPrestamoActionPerformed

    private void observacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observacionKeyTyped
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
        if((observacion.getText().length()>=50)) evt.consume();
    }//GEN-LAST:event_observacionKeyTyped

    private void btnInstrumentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInstrumentoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInstrumentoMouseClicked

    private void btnInstrumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstrumentoActionPerformed
//        jButton1.setEnabled(true);
//        jButton2.setEnabled(false);
        cantidad.setText("1");
        cantidad.setEnabled(false);
    }//GEN-LAST:event_btnInstrumentoActionPerformed

    private void catedraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_catedraItemStateChanged
        // TODO add your handling code here:
        int posicion=getCatedra().getSelectedIndex();
        String valor = (String) getCatedra().getSelectedItem();

        System.out.println(posicion+" : "+valor);

        if(posicion > 0){
            ModCatedra mdCatedra= new ModCatedra();

            mdCatedra.Consultar_id_Catedra(valor);
            String valorId2 = (mdCatedra.getId());
            getId_catedra().setText(valorId2);
//            c.LlenarProfesor(valorId2);
          //  c.LlenarEstudiante(valorId2);
            profesor.setEnabled(true);

            System.out.println("Imprimir catedra : "+getCatedra().getSelectedIndex());
            /*
            String dato=(String) getTxt_municipio().getSelectedItem();
            System.out.println("El municipio en vista es : "+dato);
            //controlador.Llenar_Mun2(dato);
            System.out.println("Municipio cargado en posicion : "+getTxt_municipio().getSelectedIndex());
            */
        }else{
            profesor.setEnabled(false);
            estudiante.setEnabled(false);

        }
    }//GEN-LAST:event_catedraItemStateChanged

    private void catedraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catedraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catedraActionPerformed

    private void profesorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profesorItemStateChanged
        // TODO add your handling code here:
        int posicion=getProfesor().getSelectedIndex();
        String valor = (String) getProfesor().getSelectedItem();

        System.out.println(posicion+" : "+valor);

        if(posicion > 0){
            ModProfesor mdPro= new ModProfesor();

            mdPro.Consultar_id_Profesor(valor);
            String valorId2 = (mdPro.getId_prof());
            getId_responsable().setText(valorId2);

            //  id_municipio.setText(""+valorId2);
          //  c.LlenarEstudiante(valorId2);

            profesor.setEnabled(true);
            estudiante.setEnabled(true);
            System.out.println("Imprimir profesor : "+getCatedra().getSelectedIndex());
        }else{

            estudiante.setEnabled(false);

        }
    }//GEN-LAST:event_profesorItemStateChanged

    private void profesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profesorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profesorActionPerformed

    private void estudianteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estudianteItemStateChanged
        // TODO add your handling code here:
        int posicion=getEstudiante().getSelectedIndex();
        String valor = (String) getEstudiante().getSelectedItem();

        System.out.println(posicion+" : "+valor);

        if(posicion > 0){
            ModEstudiante mdPro= new ModEstudiante();

            mdPro.consultaridestudiante(valor);
            String valorId2 = (mdPro.getId());
            id_estudiante.setText(valorId2);

        }else{

        }
    }//GEN-LAST:event_estudianteItemStateChanged

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        // TODO add your handling code here:
        int cantidadva = Integer.parseInt(getCantidad().getText());
        String bien=getInstrumento().getText();
        String valorId2 ="";
        ModBien mdbien= new ModBien();
        if(cantidadva>0){

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
        }else{
            this.mensaje.setText("LA CANTIDAD NO PUEDE SER MENOR O IGUAL A CERO (0)");
            this.cantidad.setText("");
        }
    }//GEN-LAST:event_cantidadKeyReleased

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    //    jButton1.setEnabled(true);
      //  jButton2.setEnabled(false);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
    //    jButton1.setEnabled(true);
      //  jButton2.setEnabled(false);
    }//GEN-LAST:event_jPanel4MouseClicked

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
            java.util.logging.Logger.getLogger(Prestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new Prestamo(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public rojerusan.RSMaterialButtonRectangle btnCancelar;
    public rojerusan.RSMaterialButtonRectangle btnCatalago;
    public rojerusan.RSMaterialButtonRectangle btnEliminar;
    public javax.swing.JButton btnInstrumento;
    public rojerusan.RSMaterialButtonRectangle btnModificar;
    public rojerusan.RSMaterialButtonRectangle btnRegistrar;
    public javax.swing.JComboBox<String> btnTipoPrestamo;
    public javax.swing.JLabel cambiarCantidad;
    public javax.swing.JTextField cantidad;
    public javax.swing.JComboBox<String> catedra;
    public javax.swing.JLabel dias;
    public javax.swing.JComboBox<String> estudiante;
    public com.toedter.calendar.JDateChooser fechaDevolucion;
    public com.toedter.calendar.JDateChooser fechaEmision;
    public javax.swing.JLabel id_bien;
    public javax.swing.JLabel id_catedra;
    public javax.swing.JLabel id_estudiante;
    public javax.swing.JLabel id_modulo;
    public javax.swing.JLabel id_responsable;
    public javax.swing.JLabel id_tp;
    public javax.swing.JTextField instrumento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel mensaje;
    public javax.swing.JTextArea observacion;
    public javax.swing.JComboBox<String> profesor;
    // End of variables declaration//GEN-END:variables
}
