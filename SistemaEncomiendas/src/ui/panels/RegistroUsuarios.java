/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.panels;

import ui.windows.SistemaAdministrador;
import dao.DatosPersonalesDAO;
import dao.EmpresasDAO;
import dao.RolDAO;
import dao.UsuarioDAO;
import dto.DatosPersonalesDTO;
import dto.EmpresasDTO;
import dto.RolDTO;
import dto.UsuarioDTO;
import utils.Encriptar.Encriptar;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Matias
 */
public class RegistroUsuarios extends javax.swing.JPanel {

    private SistemaAdministrador sistema;
    DefaultTableModel tablaUsuarios = new DefaultTableModel();
    ArrayList<UsuarioDTO> listaUsuario;
    
    public RegistroUsuarios() {
        initComponents();
        cargarRolesEnComboBox();
        mostrarCabeceraUsu();
        MostrarUsuario();
        tblUsuarios.setModel(tablaUsuarios);
    }

    
    private boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    return email.matches(emailRegex);
}

        public void mostrarCabeceraUsu(){
        tablaUsuarios.addColumn("ID Usuario");
        tablaUsuarios.addColumn("DNI");
        tablaUsuarios.addColumn("Telefono");
        tablaUsuarios.addColumn("Rol");
        tblUsuarios.setModel(tablaUsuarios);
    }
   
    //Encomiendas
    public void MostrarUsuario() {
        UsuarioDAO usuar = new UsuarioDAO();
        listaUsuario = usuar.listarTodo(); // Obtener todos los usuarios

        // Limpiar tabla antes de rellenar
        tablaUsuarios.setRowCount(0);

        // Optional: limpiar filtros/orden
        try {
            TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblUsuarios.getRowSorter();
            if (sorter != null) {
                sorter.setRowFilter(null);
                sorter.setSortKeys(null);
            }
        } catch (Exception ex) {
            // Si no hay RowSorter o hay casting problem, lo ignoramos (no crítico).
        }

        if (listaUsuario == null || listaUsuario.isEmpty()) {
            return;
        }

        DatosPersonalesDAO dpDAO = new DatosPersonalesDAO();
        RolDAO rolDAO = new RolDAO();

        for (UsuarioDTO usuario : listaUsuario) {
            // Obtener DNI (intentamos desde DatosPersonales dentro de UsuarioDTO)
            String dni = "";
            try {
                if (usuario.getDatosPersonales() != null) {
                    DatosPersonalesDTO dp = usuario.getDatosPersonales();
                    dni = (dp.getDniID() != null) ? dp.getDniID() : "";
                }
            } catch (Exception ex) {
                // Si la estructura del DTO es distinta, dejamos dni vacío
            }

            String telefono = usuario.getTelefono() != null ? usuario.getTelefono() : "";

            String nombreRol = "Rol no encontrado";
            try {
                nombreRol = rolDAO.obtenerNombrePorRolID(usuario.getUsuarioRol());
                if (nombreRol == null || nombreRol.isEmpty()) {
                    nombreRol = "Rol no encontrado";
                }
            } catch (Exception ex) {
                nombreRol = "Rol no encontrado";
            }

            Object[] rowData = {
                    usuario.getUsuarioID(),
                    dni,
                    telefono,
                    nombreRol
            };
            tablaUsuarios.addRow(rowData);
        }
    }
    
    public void cargarRolesEnComboBox() {
        try {
            RolDAO rolDAO = new RolDAO(); // Instanciar el DAO
            ArrayList<RolDTO> listaRoles = rolDAO.listarRoles(); // Obtener la lista de empresas
            
            // Recorrer la lista de roles y añadirlas al JComboBox
            for (RolDTO rol : listaRoles) {
                cbxRoles.addItem(rol.getNombre()); // Añadir cada nombre de empresa al JComboBox
            }

        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones, imprime la traza del error
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistroPaquetes = new javax.swing.JPanel();
        cbxRoles = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtContraEntrega = new javax.swing.JLabel();
        txtDNIUsuario = new javax.swing.JTextField();
        btnRegistrarUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtID2 = new javax.swing.JLabel();
        jpnlClientes = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtTelefono = new javax.swing.JTextField();
        txtContraseña2 = new javax.swing.JPasswordField();
        txtApell = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        Dirección = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtContraseña1 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        pnlRegistroPaquetes.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistroPaquetes.setPreferredSize(new java.awt.Dimension(1150, 720));
        pnlRegistroPaquetes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxRoles.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxRoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Rol" }));
        cbxRoles.setToolTipText("");
        cbxRoles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxRolesItemStateChanged(evt);
            }
        });
        cbxRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRolesActionPerformed(evt);
            }
        });
        pnlRegistroPaquetes.add(cbxRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 140, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Rol:");
        pnlRegistroPaquetes.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Telefono:");
        pnlRegistroPaquetes.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        txtContraEntrega.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtContraEntrega.setText("DNI Usuario:");
        pnlRegistroPaquetes.add(txtContraEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 94, -1));
        pnlRegistroPaquetes.add(txtDNIUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 140, -1));

        btnRegistrarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarUsuario.setText("Registrar Usuario");
        btnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuarioActionPerformed(evt);
            }
        });
        pnlRegistroPaquetes.add(btnRegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Datos del Usuario:");
        pnlRegistroPaquetes.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        txtID2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtID2.setText("Filtrar:");
        pnlRegistroPaquetes.add(txtID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 170, -1, -1));

        tblUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "DNI", "Teléfono", "Rol"
            }
        ));
        jpnlClientes.setViewportView(tblUsuarios);

        pnlRegistroPaquetes.add(jpnlClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 880, 290));
        pnlRegistroPaquetes.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 150, -1));

        txtContraseña2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseña2ActionPerformed(evt);
            }
        });
        pnlRegistroPaquetes.add(txtContraseña2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 140, -1));

        txtApell.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtApell.setText("Apellido:");
        pnlRegistroPaquetes.add(txtApell, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));
        pnlRegistroPaquetes.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 149, -1));

        Dirección.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Dirección.setText("Dirección:");
        pnlRegistroPaquetes.add(Dirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));
        pnlRegistroPaquetes.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 140, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Confirmar Contraseña:");
        pnlRegistroPaquetes.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Correo:");
        pnlRegistroPaquetes.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));
        pnlRegistroPaquetes.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 190, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Especificaciones:");
        pnlRegistroPaquetes.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Contraseña:");
        pnlRegistroPaquetes.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        txtContraseña1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseña1ActionPerformed(evt);
            }
        });
        pnlRegistroPaquetes.add(txtContraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 140, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Nombre:");
        pnlRegistroPaquetes.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));
        pnlRegistroPaquetes.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 190, -1));

        add(pnlRegistroPaquetes, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cbxRolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxRolesItemStateChanged

    }//GEN-LAST:event_cbxRolesItemStateChanged

    private void btnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioActionPerformed
 // Recolectar valores de formulario
        String dni = txtDNIUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        char[] pass1Chars = txtContraseña1.getPassword();
        char[] pass2Chars = txtContraseña2.getPassword();
        String pass1 = new String(pass1Chars);
        String pass2 = new String(pass2Chars);

        // Validaciones básicas
        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                direccion.isEmpty() || telefono.isEmpty() ||
                pass1.isEmpty() || pass2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puede dejar los campos vacíos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            // limpiar arrays de contraseña por seguridad
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
            return;
        }

        if (!dni.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 dígitos numéricos.", "DNI Inválido", JOptionPane.WARNING_MESSAGE);
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
            return;
        }

        if (!telefono.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(null, "El teléfono debe tener 9 dígitos numéricos.", "Teléfono Inválido", JOptionPane.WARNING_MESSAGE);
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
            return;
        }

        if (pass1.length() < 6 || pass1.length() > 20) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 6 y 20 caracteres.", "Contraseña Inválida", JOptionPane.WARNING_MESSAGE);
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
            return;
        }

        if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.WARNING_MESSAGE);
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
            return;
        }

        if (!correo.isEmpty() && !isValidEmail(correo)) {
            JOptionPane.showMessageDialog(null, "El correo electrónico no tiene un formato válido.", "Correo inválido", JOptionPane.WARNING_MESSAGE);
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
            return;
        }

        if (cbxRoles.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "Por favor selecciona un rol válido.", "Rol inválido", JOptionPane.WARNING_MESSAGE);
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
            return;
        }

        try {
            // 1) Crear Datos Personales
            DatosPersonalesDTO datosPersonales = new DatosPersonalesDTO();
            datosPersonales.setDniID(dni);
            datosPersonales.setNombre(nombre);
            datosPersonales.setApellido(apellido);
            datosPersonales.setDireccion(direccion);
            // Si tu DTO tiene setCorreo, podrías asignarlo aquí: datosPersonales.setCorreo(correo);

            DatosPersonalesDAO dpDAO = new DatosPersonalesDAO();
            dpDAO.crearDatosPersonales(datosPersonales);

            // 2) Obtener rol ID
            String nombreRolSeleccionado = cbxRoles.getSelectedItem().toString();
            RolDAO rolDAO = new RolDAO();
            int rolID = rolDAO.obtenerRolIDPorNombre(nombreRolSeleccionado);
            if (rolID == -1) {
                JOptionPane.showMessageDialog(null, "Rol seleccionado no es válido.", "Error", JOptionPane.WARNING_MESSAGE);
                Arrays.fill(pass1Chars, '\0');
                Arrays.fill(pass2Chars, '\0');
                return;
            }

            // 3) Encriptar contraseña
            String contrasenaEncriptada = Encriptar.hashPassword(pass1);

            // 4) Crear Usuario y guardarlo
            UsuarioDTO u = new UsuarioDTO();
            u.setDatosPersonales(datosPersonales);
            u.setTelefono(telefono);
            u.setContrasena(contrasenaEncriptada);
            u.setUsuarioRol(rolID);

            UsuarioDAO us = new UsuarioDAO();
            us.crearUsuario(u);

            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Refrescar tabla
            MostrarUsuario();

            // Limpiar formulario
            txtDNIUsuario.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtContraseña1.setText("");
            txtContraseña2.setText("");
            txtCorreo.setText("");
            cbxRoles.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // limpiar arrays de contraseña por seguridad
            Arrays.fill(pass1Chars, '\0');
            Arrays.fill(pass2Chars, '\0');
        }
    }//GEN-LAST:event_btnRegistrarUsuarioActionPerformed

    private void cbxRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxRolesActionPerformed

    private void txtContraseña2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseña2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseña2ActionPerformed

    private void txtContraseña1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseña1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseña1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dirección;
    private javax.swing.JButton btnRegistrarUsuario;
    private javax.swing.JComboBox<String> cbxRoles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jpnlClientes;
    private javax.swing.JPanel pnlRegistroPaquetes;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JLabel txtApell;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JLabel txtContraEntrega;
    private javax.swing.JPasswordField txtContraseña1;
    private javax.swing.JPasswordField txtContraseña2;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNIUsuario;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JLabel txtID2;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
