/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pnlInterfaces;

import ClasesModeloDAO.DatosPersonalesDAO;
import ClasesModeloDAO.EmpresasDAO;
import ClasesModeloDAO.RolDAO;
import ClasesModeloDAO.UsuarioDAO;
import ClasesModeloDTO.DatosPersonalesDTO;
import ClasesModeloDTO.EmpresasDTO;
import ClasesModeloDTO.RolDTO;
import ClasesModeloDTO.UsuarioDTO;
import Encriptar.Encriptar;
import OrdenarDatos.OrdenaUsu;
import Vista.SistemaAdministrador;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    OrdenaUsu ordus;
    
    public RegistroUsuarios() {
        initComponents();
        ordus = new OrdenarDatos.OrdenaUsu();
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
    
    
        private void listarUsuario() {
            DatosPersonalesDAO datospersonalesDAO = new DatosPersonalesDAO();
            RolDAO roldao = new RolDAO();
    for (UsuarioDTO repartidor : ordus.getLista()) {
        String nombre = datospersonalesDAO.obtenerDNINombreMoto(repartidor.getDniID());
        // Verificar que el nombre no sea nulo o vacío
        if (nombre == null || nombre.isEmpty()) {
            nombre = "Nombre no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        String nombreRol = roldao.obtenerNombrePorRolID(repartidor.getUsuarioRol());
        // Verificar que el nombre no sea nulo o vacío
        if (nombre == null || nombre.isEmpty()) {
            nombre = "Nombre no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        Object[] rowData = {
            repartidor.getUsuarioID(),
            nombre,
            repartidor.getTelefono(),
            nombreRol,
        };
        tablaUsuarios.addRow(rowData);
    }
}
   
    //Encomiendas
public void MostrarUsuario() {
    UsuarioDAO usuar = new UsuarioDAO();
    listaUsuario = usuar.listarTodo(); // Obtener todas las encomiendas de la base de datos

    // Limpiar la tabla antes de agregar nuevos datos
    tablaUsuarios.setRowCount(0); 

    if (!listaUsuario.isEmpty()) {
        ordus.actualizarLista(listaUsuario); // Actualizar la lista en el objeto Ordena
        listarUsuario(); // Llamar al método para llenar la tabla con los nuevos datos
    } else {
        
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
 UsuarioDTO u = new UsuarioDTO();
    UsuarioDAO us = new UsuarioDAO();
    DatosPersonalesDTO datosPersonales = new DatosPersonalesDTO();
    DatosPersonalesDAO dpDAO = new DatosPersonalesDAO();
    RolDAO rolDAO = new RolDAO(); // DAO para obtener el Rol_ID

    // Validaciones de los campos de entrada
    if (txtDNIUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
        txtDireccion.getText().isEmpty() || txtTelefono.getText().isEmpty() ||
        txtContraseña1.getText().isEmpty() || txtContraseña2.getText().isEmpty()) {
        
        JOptionPane.showMessageDialog(null, "No puede dejar los campos vacíos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);

    } else if (txtDNIUsuario.getText().length() != 7 || !txtDNIUsuario.getText().matches("\\d+")) {
        // Validación del DNI
        JOptionPane.showMessageDialog(null, "El DNI debe tener 8 dígitos numéricos.", "DNI Inválido", JOptionPane.WARNING_MESSAGE);

    } else if (!txtTelefono.getText().matches("\\d{9}")) {
        // Validación del teléfono
        JOptionPane.showMessageDialog(null, "El teléfono debe tener 9 dígitos numéricos.", "Teléfono Inválido", JOptionPane.WARNING_MESSAGE);

    } else if (txtContraseña1.getText().length() < 6 || txtContraseña1.getText().length() > 20) {
        // Validación de la contraseña
        JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 6 y 20 caracteres.", "Contraseña Inválida", JOptionPane.WARNING_MESSAGE);

    } else if (!txtContraseña1.getText().equals(txtContraseña2.getText())) {
        // Verificación de coincidencia de contraseñas
        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.WARNING_MESSAGE);

    } else {
        try {
            // PASO 1: Registrar los datos personales en la tabla datos_personales
            datosPersonales.setDniID(txtDNIUsuario.getText());
            datosPersonales.setNombre(txtNombre.getText());
            datosPersonales.setApellido(txtApellido.getText());
            datosPersonales.setDireccion(txtDireccion.getText());

            dpDAO.crearDatosPersonales(datosPersonales); // Llamada al DAO para registrar los datos personales

            // PASO 2: Obtener el Rol_ID del rol seleccionado
            String nombreRol = cbxRoles.getSelectedItem().toString(); // Obtener el nombre del rol seleccionado
            int rolID = rolDAO.obtenerRolIDPorNombre(nombreRol); // Obtener el Rol_ID desde el DAO

            if (rolID == -1) {
                JOptionPane.showMessageDialog(null, "Rol seleccionado no es válido.", "Error", JOptionPane.WARNING_MESSAGE);
                return; // Detener la ejecución si no se encuentra un Rol_ID válido
            }

            // Encriptar la contraseña antes de enviarla a la base de datos
            String contrasenaEncriptada = Encriptar.encrypt(txtContraseña1.getText());

            // PASO 3: Registrar el usuario en la tabla usuario usando el DNI como referencia
            u.setDatosPersonales(datosPersonales);  // Referencia a datos personales
            u.setTelefono(txtTelefono.getText());
            u.setContrasena(contrasenaEncriptada); // Establecer la contraseña encriptada
            u.setUsuarioRol(rolID); // Asignar el Rol_ID al usuario

            us.crearUsuario(u); // Llamada al DAO para registrar el usuario

            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");

            MostrarUsuario();
            
            // Limpiar los campos
            txtDNIUsuario.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtContraseña1.setText("");
            txtContraseña2.setText("");
            cbxRoles.setSelectedIndex(0); // Resetear selección de rol
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al encriptar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
