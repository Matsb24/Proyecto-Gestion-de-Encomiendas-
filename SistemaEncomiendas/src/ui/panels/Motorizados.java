
package ui.panels;

import dao.DatosPersonalesDAO;
import dao.RepartidorDAO;
import dao.UbigeoDAO;
import dto.DatosPersonalesDTO;
import dto.RepartidorDTO;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Motorizados extends javax.swing.JPanel {

    DefaultTableModel tablaRepartidor = new DefaultTableModel();
    ArrayList<RepartidorDTO> listaRepa;
    OrdenaRepa ordrep;
    
    public Motorizados() {
        initComponents();
        tblRepartidor.setModel(tablaRepartidor);
        ordrep = new utils.OrdenarDatos.OrdenaRepa();
        mostrarCabeceraRep();
        cargarComboBoxDistritos(cbxDistrito);
        MostrarRepartidores();
    }
    
    public void mostrarCabeceraRep(){
        tablaRepartidor.addColumn("Repartidor_ID");
        tablaRepartidor.addColumn("DNI_ID");
        tablaRepartidor.addColumn("Telefono");
        tablaRepartidor.addColumn("Vehiculo_Placa");
        tablaRepartidor.addColumn("Codigo_UbiGeo");
        tblRepartidor.setModel(tablaRepartidor);
    }
    
private void listarRepartidor() {
    DatosPersonalesDAO datospersonalesDAO = new DatosPersonalesDAO();
    UbigeoDAO ubigeo = new UbigeoDAO();
    for (RepartidorDTO repartidor : ordrep.getLista()) {
        // Obtener el nombre del repartidor usando su DNI
        String nombreRepartidor = datospersonalesDAO.obtenerDNINombreMoto(repartidor.getDniID());
        // Verificar que el nombre no sea nulo o vacío
        if (nombreRepartidor == null || nombreRepartidor.isEmpty()) {
            nombreRepartidor = "Nombre no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        //obtener nombre distrito usando codigo Ubigeo
        String Distrito = ubigeo.obtenerDistritoPorCodigoUbigeo(repartidor.getCodigoUbigeo());
        // Verificar que el distrito no sea nulo o vacío
        if (Distrito == null || Distrito.isEmpty()) {
            nombreRepartidor = "Distrito no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        Object[] rowData = {
            repartidor.getRepartidorID(),
            nombreRepartidor,  // Mostrar el nombre en lugar del DNI
            repartidor.getTelefono(),
            repartidor.getVehiculoPlaca(),
            Distrito
        };
        
        tablaRepartidor.addRow(rowData); // Agregar una nueva fila con los datos
    }
}

public void MostrarRepartidores() {
    RepartidorDAO rep = new RepartidorDAO();
    listaRepa = rep.listarTodo(); // Obtener todas las encomiendas de la base de datos

    // Limpiar la tabla antes de agregar nuevos datos
    tablaRepartidor.setRowCount(0); 

    if (!listaRepa.isEmpty()) {
        ordrep.actualizarLista(listaRepa); // Actualizar la lista en el objeto Ordena
        listarRepartidor(); // Llamar al método para llenar la tabla con los nuevos datos
    } else {
        
    }
}
    
public void cargarComboBoxDistritos(JComboBox<String> comboBox) {
    UbigeoDAO ubigeoDAO = new UbigeoDAO(); // Asegúrate de tener la instancia correcta
    ArrayList<String> listaDistritos = ubigeoDAO.listarDistritos(); // Obtener distritos

    for (String distrito : listaDistritos) {
        comboBox.addItem(distrito); // Añadir cada distrito al JComboBox
    }
}
    
    private void limpiarCampos() {
        txtDNI.setText("");
        txtTelefono.setText("");
        txtPlaca.setText("");
        cbxDistrito.setSelectedIndex(0);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRepartidor = new javax.swing.JTable();
        btnRecargar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxDistrito = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDirección = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        tblRepartidor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblRepartidor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Motorizado", "DNI", "Teléfono", "Placa de Vehículo", "Código Ubigeo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRepartidor);

        btnRecargar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRecargar.setText("Recargar");
        btnRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 97, 97));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("DNI:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 16, -1, -1));

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        jPanel2.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 40, 152, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Teléfono:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 16, -1, -1));

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 160, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Placa del Vehículo:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        txtPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlacaActionPerformed(evt);
            }
        });
        jPanel2.add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 187, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Distrito laboral:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, -1, -1));

        cbxDistrito.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxDistrito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un Distrito" }));
        cbxDistrito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDistritoItemStateChanged(evt);
            }
        });
        jPanel2.add(cbxDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 187, -1));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 90, 103, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 148, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Apellido:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));
        jPanel2.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 160, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Dirección:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));
        jPanel2.add(txtDirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 187, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btnRecargar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnRecargar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
    String dni = txtDNI.getText();
    String telefono = txtTelefono.getText();
    String vehiculoPlaca = txtPlaca.getText();
    String Ubigeo = cbxDistrito.getSelectedItem().toString();
    String Nombre = txtNombre.getText();
    String Apellido = txtApellido.getText();
    String Direccion = txtDirección.getText();
    
    // Validación de campos vacíos
    if (dni.isEmpty() || telefono.isEmpty() || vehiculoPlaca.isEmpty() || Ubigeo.equals("Selecciona un Distrito") || Nombre.isEmpty() || Apellido.isEmpty() || Direccion.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (dni.trim().length() != 7 || !dni.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "El DNI debe tener 8 dígitos numéricos.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (txtTelefono.getText().trim().length() != 9 || !txtTelefono.getText().matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "El número de teléfono debe tener 9 dígitos numéricos.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        // Crear nuevo objeto DatosPersonalesDTO
        DatosPersonalesDTO nuevoUsuario = new DatosPersonalesDTO();
        
        // Asignar valores a los atributos del DTO
        nuevoUsuario.setDniID(dni);
        nuevoUsuario.setNombre(Nombre);
        nuevoUsuario.setApellido(Apellido);
        nuevoUsuario.setDireccion(Direccion);

        // Crear instancia de DatosPersonalesDAO y llamar al método para guardar
        DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
        datosPersonalesDAO.crearDatosPersonales(nuevoUsuario);

        // Obtener el UbiGEO_ID usando el distrito seleccionado
        UbigeoDAO ubigeoDAO = new UbigeoDAO();
        int ubigeoID = ubigeoDAO.obtenerUbigeoIDPorDistrito(Ubigeo);
        if (ubigeoID == -1) {
    JOptionPane.showMessageDialog(null, "Distrito seleccionado no es válido.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}
        
        // Crear instancia de RepartidorDTO
        RepartidorDTO nuevoRepartidor = new RepartidorDTO();
        
        // Asignar valores a los atributos del DTO
        nuevoRepartidor.setDniID(dni);
        nuevoRepartidor.setTelefono(telefono);
        nuevoRepartidor.setVehiculoPlaca(vehiculoPlaca);
        nuevoRepartidor.setCodigoUbigeo(ubigeoID); // Asigna el UbiGEO_ID al repartidor

        // Crear instancia de RepartidorDAO y llamar al método para guardar
        RepartidorDAO repartidorDAO = new RepartidorDAO();
        repartidorDAO.crearRepartidor(nuevoRepartidor);
        
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "Motorizado Registrado.", "Registrado", JOptionPane.INFORMATION_MESSAGE);
        MostrarRepartidores();
    } catch (Exception e) {
        
    }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarActionPerformed
        MostrarRepartidores();
    }//GEN-LAST:event_btnRecargarActionPerformed

    private void cbxDistritoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDistritoItemStateChanged

    }//GEN-LAST:event_cbxDistritoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cbxDistrito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRepartidor;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDirección;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
