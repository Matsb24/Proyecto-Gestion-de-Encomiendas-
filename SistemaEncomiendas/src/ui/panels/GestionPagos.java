
package ui.panels;

import dao.PagoDAO;
import dto.PagoDTO;
import dto.RepartidorDTO;
import utils.OrdenarDatos.OrdenaRepa;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionPagos extends javax.swing.JPanel {

    DefaultTableModel tablaPagos = new DefaultTableModel();
    ArrayList<RepartidorDTO> listaRepa;
    OrdenaRepa ordrep;
    
    public GestionPagos() {
        initComponents();
        tblListaPagos.setModel(tablaPagos);
        ordrep = new utils.OrdenarDatos.OrdenaRepa();
        mostrarCabeceraRep();
        llenarTablaPagos();
    }
    
    public void mostrarCabeceraRep(){
        tablaPagos.addColumn("ID Pagos");
        tablaPagos.addColumn("ID Cliente");
        tablaPagos.addColumn("Precio");
        tablaPagos.addColumn("Tipo Pago");
        tblListaPagos.setModel(tablaPagos);
    }
    
    private void limpiarCampos() {
        txtIDPago.setText("");
        txtIDCliente.setText("");
        txtPrecio.setText("");
    }
    
    private void llenarTablaPagos() {
        // Obtén la lista de pagos
        PagoDAO pagoDAO = new PagoDAO();
        ArrayList<PagoDTO> listaPagos = pagoDAO.listarTodo();

        // Define las columnas del modelo de la tabla
        String[] columnas = {"Pago_ID", "Cliente_ID", "Precio", "Tipo_Pago"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        
        // Llena el modelo con los datos de los pagos
        for (PagoDTO pago : listaPagos) {
            Object[] fila = {
                pago.getPagoID(),
                pago.getClienteID(),
                pago.getPrecio(),
                pago.getTipoPago()
            };
            modeloTabla.addRow(fila); // Agrega cada fila al modelo
        }

        // Asigna el modelo a la tabla
        tblListaPagos.setModel(modeloTabla);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaPagos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtIDPago = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIDCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnModificarDatos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTipoPago = new javax.swing.JTextField();
        btnEliminarPago = new javax.swing.JButton();
        btnMostrarPagos = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        tblListaPagos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblListaPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID de Pago", "ID de Cliete", "Precio", "Tipo de Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListaPagos);

        jPanel2.setBackground(new java.awt.Color(255, 97, 97));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("ID de Pago:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        txtIDPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDPagoActionPerformed(evt);
            }
        });
        jPanel2.add(txtIDPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 152, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ID de Cliente:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        txtIDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txtIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 160, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Precio:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 187, -1));

        btnModificarDatos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarDatos.setText("Modificar Datos");
        btnModificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDatosActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 130, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tipo de Pago:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, -1, -1));
        jPanel2.add(txtTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 180, -1));

        btnEliminarPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminarPago.setText("Eliminar Pago");
        btnEliminarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPagoActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 130, -1));

        btnMostrarPagos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMostrarPagos.setText("Mostrar Pagos");
        btnMostrarPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarPagosActionPerformed(evt);
            }
        });
        jPanel2.add(btnMostrarPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 130, -1));

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
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txtIDPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDPagoActionPerformed

    private void txtIDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDClienteActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnModificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDatosActionPerformed
        // Validación de campos vacíos
        if (txtIDPago.getText().isEmpty() || txtIDCliente.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtTipoPago.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int idPago = Integer.parseInt(txtIDPago.getText());
            int idCliente = Integer.parseInt(txtIDCliente.getText());
            BigDecimal precio = new BigDecimal(txtPrecio.getText()); // Cambiar a BigDecimal directamente
            String tipoPago = txtTipoPago.getText();

            // Modificar datos usando PagoDTO y PagoDAO
            PagoDTO pagoModificado = new PagoDTO();
            pagoModificado.setPagoID(idPago);
            pagoModificado.setClienteID(idCliente);
            pagoModificado.setPrecio(precio);
            pagoModificado.setTipoPago(tipoPago);

            PagoDAO pagoDAO = new PagoDAO();
            pagoDAO.actualizarPago(pagoModificado); // Cambiar a actualizarPago
            JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");

            limpiarCampos(); // Limpiar los campos después de modificar
            llenarTablaPagos(); // Actualizar la tabla después de la modificación
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un valor numérico válido.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarDatosActionPerformed

    private void btnEliminarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPagoActionPerformed
        String idPago = txtIDPago.getText();

        // Validación de campo vacío
        if (idPago.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un ID de Pago para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmación de eliminación
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este pago?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            PagoDAO pagoDAO = new PagoDAO();

            // Verificar si el pago existe
            PagoDTO pago = pagoDAO.obtenerPagoPorID(Integer.parseInt(idPago));

            if (pago == null) {
                JOptionPane.showMessageDialog(null, "No se encontró un pago con el ID especificado o ya fue eliminado.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Eliminar el pago
                pagoDAO.eliminarPago(Integer.parseInt(idPago));
                JOptionPane.showMessageDialog(null, "Pago eliminado correctamente.");
                limpiarCampos(); // Limpiar los campos después de eliminar
                llenarTablaPagos(); // Actualizar la tabla después de la eliminación
            }
        }
    }//GEN-LAST:event_btnEliminarPagoActionPerformed

    private void btnMostrarPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarPagosActionPerformed
        llenarTablaPagos();
    }//GEN-LAST:event_btnMostrarPagosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarPago;
    private javax.swing.JButton btnModificarDatos;
    private javax.swing.JButton btnMostrarPagos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaPagos;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtIDPago;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTipoPago;
    // End of variables declaration//GEN-END:variables
}
