/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.toedter.calendar.JDateChooser;
import controlador.ctrEstudiante;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controlador.Validacion;
import java.util.Date;
import modelo.ModCatedra;
import rojerusan.RSMaterialButtonRectangle;

/**
 *
 * @author abe
 */
public class Estudiante extends javax.swing.JDialog {
    ctrEstudiante c;
Validacion validacion;
    

    /**
     * Creates new form Estudiant
     * @param parent
     * @param modal
     */
    public Estudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        c.LLenarCatedra();
         this.setLocationRelativeTo(null);
       this.setResizable(false);
    }
    
    public JLabel getId_representante() {
        return id_representante;
    }

    public void setId_representante(JLabel id_representante) {
        this.id_representante = id_representante;
    }

    public JLabel getId_catedra() {
        return id_catedra;
    }

    public void setId_catedra(JLabel id_catedra) {
        this.id_catedra = id_catedra;
    }

   /* public JButton getBtnRepresentante() {
        return btnRepresentante;
    }

    public void setBtnRepresentante(JButton btnRepresentante) {
        this.btnRepresentante = btnRepresentante;
    }*/

    public JLabel getId() {
        return id;
    }

    public void setId(JLabel id) {
        this.id = id;
    }

    public JComboBox getNacion() {
        return nacion;
    }

    public void setNacion(JComboBox nacion) {
        this.nacion = nacion;
    }

    public JTextField getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public void setCedulaRepresentante(JTextField cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }

    

    public JTextField getApellido() {
        return apellido;
    }

    public void setApellido(JTextField apellido) {
        this.apellido = apellido;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = (RSMaterialButtonRectangle) btnCancelar;
    }

    public RSMaterialButtonRectangle getBtnEstado() {
        return btnEliminar;
    }

    public void setBtnEstado(RSMaterialButtonRectangle btnEstado) {
        this.btnEliminar = btnEstado;
    }

    public JLabel getStatus() {
        return status;
    }

    public void setStatus(JLabel status) {
        this.status = status;
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

    public JButton getBtnCatalago() {
        return btnCatalago;
    }

    public void setBtnCatalago(JButton btnCatalago) {
        this.btnCatalago = (RSMaterialButtonRectangle) btnCatalago;
    }

    public JDateChooser getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(JDateChooser fechaNac) {
        this.fechaNac = fechaNac;
    }

    public JComboBox<String> getCatedra() {
        return catedra;
    }

    public void setCatedra(JComboBox<String> catedra) {
        this.catedra = catedra;
    }

    public JTextField getCedula() {
        return cedula;
    }

    public void setCedula(JTextField cedula) {
        this.cedula = cedula;
    }

    public JTextArea getDireccion() {
        return direccion;
    }

    public void setDireccion(JTextArea direccion) {
        this.direccion = direccion;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public void setNombre(JTextField nombre) {
        this.nombre = nombre;
    }

    public JComboBox<String> getSexo() {
        return sexo;
    }

    public void setSexo(JComboBox<String> sexo) {
        this.sexo = sexo;
    }


    public JTextField getTelefono() {
        return telefono;
    }

    public void setTelefono(JTextField telefono) {
        this.telefono = telefono;
    }

    public JButton getBtnNewRepresentante() {
        return btnNewRepresentante;
    }

    public void setBtnNewRepresentante(JButton btnNewRepresentante) {
        this.btnNewRepresentante = btnNewRepresentante;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        nacion = new javax.swing.JComboBox();
        cedula = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        direccion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        catedra = new javax.swing.JComboBox<>();
        fechaNac = new com.toedter.calendar.JDateChooser();
        cedulaRepresentante = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnNewRepresentante = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnCatalago = new rojerusan.RSMaterialButtonRectangle();
        btnRegistrar = new rojerusan.RSMaterialButtonRectangle();
        btnEliminar = new rojerusan.RSMaterialButtonRectangle();
        btnCancelar = new rojerusan.RSMaterialButtonRectangle();
        btnModificar = new rojerusan.RSMaterialButtonRectangle();
        btnReporte = new rojerusan.RSMaterialButtonRectangle();
        id_representante = new javax.swing.JLabel();
        id_catedra = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 112, 192));
        jLabel21.setText("Estudiante");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 3));
        jPanel3.setOpaque(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Cédula:");

        nacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "E" }));

        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Nombre:");

        nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Apellido:");

        apellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Sexo:");

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Dirección:");

        direccion.setColumns(20);
        direccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        direccion.setRows(5);
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(direccion);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Telefono:");

        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Fecha de nacimiento:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Cátedra:");

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

        cedulaRepresentante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulaRepresentanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaRepresentanteKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Representante:");

        btnNewRepresentante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/agregar.png"))); // NOI18N
        btnNewRepresentante.setToolTipText("REGISTRAR  NUEVO REPRESENTANTE");
        btnNewRepresentante.setContentAreaFilled(false);
        btnNewRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRepresentanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(apellido)
                                    .addComponent(nombre)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cedulaRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNewRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(50, 50, 50))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(catedra, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechaNac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                .addComponent(telefono, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catedra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cedulaRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnNewRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        c =new controlador.ctrEstudiante(this);
        btnNewRepresentante.addActionListener(c);

        jPanel2.setOpaque(false);

        btnCatalago.setText("Catálago");

        btnRegistrar.setText("Registrar");

        btnEliminar.setText("ELIMINAR");
        btnEliminar.setEnabled(false);

        btnCancelar.setText("Cancelar");

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);

        btnReporte.setText("REPORTE");

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
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCatalago, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        c= new controlador.ctrEstudiante(this);
        btnCatalago.addActionListener(c);
        c =new controlador.ctrEstudiante(this);
        btnRegistrar.addActionListener(c);
        c =new controlador.ctrEstudiante(this);
        btnEliminar.addActionListener(c);
        c=new controlador.ctrEstudiante(this);
        btnCancelar.addActionListener(c);
        c =new controlador.ctrEstudiante(this);
        btnModificar.addActionListener(c);
        c=new controlador.ctrEstudiante(this);
        btnReporte.addActionListener(c);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(320, 320, 320))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id_representante, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id_catedra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id_representante)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id_catedra)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(id)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyTyped
        // TODO add your handling code here:
        btnRegistrar.setEnabled(true);
        char car = evt.getKeyChar();

        if((car< '0' || car> '9') && (car!='.') && (car!=(char)KeyEvent.VK_BACK_SPACE && car!=(char)KeyEvent.VK_ENTER && car!=(char)KeyEvent.VK_DELETE)){

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Solo se permiten Números", "Advertencia",JOptionPane.PLAIN_MESSAGE);

            evt.consume();
        }
        if((cedula.getText().length()>=9)) evt.consume();
    }//GEN-LAST:event_cedulaKeyTyped

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();

        if(Character.isLowerCase(car)){

            String cad =(""+car).toUpperCase();
            car=cad.charAt(0);
            evt.setKeyChar(car);
        }

        if((car< 'a' || car> 'z') && (car< 'A' || car> 'z') && ( car!=(char)KeyEvent.VK_ENTER && car!=(char)KeyEvent.VK_DELETE && car!=(char)KeyEvent.VK_SPACE) && car!=(char)KeyEvent.VK_BACK_SPACE)  {

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Solo Se Permiten Letras", "Advertencia",JOptionPane.PLAIN_MESSAGE);

            evt.consume();

        }

        if((nombre.getText().length()>=20)) evt.consume();
    }//GEN-LAST:event_nombreKeyTyped

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();

        if(Character.isLowerCase(car)){

            String cad =(""+car).toUpperCase();
            car=cad.charAt(0);
            evt.setKeyChar(car);
        }

        if((car< 'a' || car> 'z') && (car< 'A' || car> 'z') && ( car!=(char)KeyEvent.VK_ENTER && car!=(char)KeyEvent.VK_DELETE && car!=(char)KeyEvent.VK_SPACE) && car!=(char)KeyEvent.VK_BACK_SPACE)  {

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Solo Se Permiten Letras", "Advertencia",JOptionPane.PLAIN_MESSAGE);

            evt.consume();

        }

        if((apellido.getText().length()>=20)) evt.consume();
    }//GEN-LAST:event_apellidoKeyTyped

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexoActionPerformed

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
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

        if((direccion.getText().length()>=50)) evt.consume();
    }//GEN-LAST:event_direccionKeyTyped

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();

        if((car< '0' || car> '9') && (car!='.') && (car!=(char)KeyEvent.VK_BACK_SPACE && car!=(char)KeyEvent.VK_ENTER && car!=(char)KeyEvent.VK_DELETE)){

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Solo se permiten Números", "Advertencia",JOptionPane.PLAIN_MESSAGE);

            evt.consume();
        }
        if((telefono.getText().length()>=12)) evt.consume();
    }//GEN-LAST:event_telefonoKeyTyped

    private void catedraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_catedraItemStateChanged
        // TODO add your handling code here:
        int posicion=getCatedra().getSelectedIndex();
        String valor = (String) getCatedra().getSelectedItem();

        System.out.println(posicion+" : "+valor);

        if(posicion > 0){

            ModCatedra mdCatedra= new ModCatedra();

            mdCatedra.Consultar_id_Catedra(valor);
            String valorId2 = (mdCatedra.getId());

            id_catedra.setText(valorId2);
        }
    }//GEN-LAST:event_catedraItemStateChanged

    private void catedraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catedraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catedraActionPerformed

    private void cedulaRepresentanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaRepresentanteKeyReleased
        // TODO add your handling code here:
        if (evt.getSource() == this.getCedulaRepresentante()){
            c.representante();
        }
    }//GEN-LAST:event_cedulaRepresentanteKeyReleased

    private void cedulaRepresentanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaRepresentanteKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();

        if((car< '0' || car> '9') && (car!='.') && (car!=(char)KeyEvent.VK_BACK_SPACE && car!=(char)KeyEvent.VK_ENTER && car!=(char)KeyEvent.VK_DELETE)){

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Solo se permiten Números", "Advertencia",JOptionPane.PLAIN_MESSAGE);

            evt.consume();
        }
        if((cedulaRepresentante.getText().length()>=9)) evt.consume();
    }//GEN-LAST:event_cedulaRepresentanteKeyTyped

    private void btnNewRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRepresentanteActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnNewRepresentanteActionPerformed

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
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new Estudiante(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField apellido;
    public rojerusan.RSMaterialButtonRectangle btnCancelar;
    public rojerusan.RSMaterialButtonRectangle btnCatalago;
    public rojerusan.RSMaterialButtonRectangle btnEliminar;
    public rojerusan.RSMaterialButtonRectangle btnModificar;
    public javax.swing.JButton btnNewRepresentante;
    public rojerusan.RSMaterialButtonRectangle btnRegistrar;
    private rojerusan.RSMaterialButtonRectangle btnReporte;
    public javax.swing.JComboBox<String> catedra;
    public javax.swing.JTextField cedula;
    public javax.swing.JTextField cedulaRepresentante;
    public javax.swing.JTextArea direccion;
    public com.toedter.calendar.JDateChooser fechaNac;
    public javax.swing.JLabel id;
    public javax.swing.JLabel id_catedra;
    public javax.swing.JLabel id_representante;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JComboBox nacion;
    public javax.swing.JTextField nombre;
    public javax.swing.JComboBox<String> sexo;
    public javax.swing.JLabel status;
    public javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
