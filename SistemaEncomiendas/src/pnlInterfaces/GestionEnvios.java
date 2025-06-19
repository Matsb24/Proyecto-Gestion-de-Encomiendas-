/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pnlInterfaces;

import ClasesModeloDAO.ClienteDAO;
import ClasesModeloDAO.EmpresasDAO;
import ClasesModeloDAO.EncomiendaDAO;
import ClasesModeloDAO.EnvioDAO;
import ClasesModeloDAO.EstadoDAO;
import ClasesModeloDAO.RepartidorDAO;
import ClasesModeloDTO.EncomiendaDTO;
import ClasesModeloDTO.EnvioDTO;
import OrdenarDatos.OrdenaEnvio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Matias
 */
public class GestionEnvios extends javax.swing.JPanel {

    DefaultTableModel tablapaquetes = new DefaultTableModel();
    ArrayList<EnvioDTO> listaEnv;
    OrdenaEnvio OrdEnv;
    
    public GestionEnvios() {
        initComponents();
        OrdEnv = new OrdenarDatos.OrdenaEnvio();
        
        mostrarCabeceraPaq();
        MostrarEncomiendas();
    }

    public void mostrarCabeceraPaq(){
    tablapaquetes.addColumn("Cliente Nombre");
    tablapaquetes.addColumn("Cliente Correo");
    tablapaquetes.addColumn("Empresa");
    tablapaquetes.addColumn("Motorizado");
    tablapaquetes.addColumn("Estado");
    tablapaquetes.addColumn("Fecha Envio");
    tablapaquetes.addColumn("Destino");
    tblPaquetes.setModel(tablapaquetes);
    }
    
    private void limpiarFiltrosYOrdenacion() {
    // Obtener el TableRowSorter de la tabla
    TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblPaquetes.getRowSorter();
    
    if (sorter != null) {
        // Limpiar cualquier filtro
        sorter.setRowFilter(null);
        
        // Restablecer el orden (opcional, si deseas un orden específico)
        sorter.setSortKeys(null); // Esto restablece la ordenación a la predeterminada (sin orden específico)
    }

    // Opcionalmente, limpiar los campos de texto de búsqueda
    txtCorreo.setText("");
    txtfEmpresa.setText("");
    txtfMotorizado.setText("");
    txtfDistrito.setText("");
    txtfEstado.setText("");
    
    // Restablecer combobox
    cbxTipoOrdenarEnco.setSelectedIndex(0); // O el valor por defecto que desees
    cbxOrdenMeMa.setSelectedIndex(0); // O el valor por defecto que desees
}
    
    private void listarEnvios() {
        ClienteDAO clientedao = new ClienteDAO();
        EncomiendaDAO encomiendadao = new EncomiendaDAO();
        EmpresasDAO empresasdao = new EmpresasDAO();
        RepartidorDAO repartidordao = new RepartidorDAO();
        EstadoDAO estadodao = new EstadoDAO();
    for (EnvioDTO envio : OrdEnv.getLista()) {
        // Obtener el nombre del repartidor usando su DNI
        String nombreCliente = clientedao.obtenerNombrePorClienteID(envio.getClienteID());
        // Verificar que el nombre no sea nulo o vacío
        if (nombreCliente == null || nombreCliente.isEmpty()) {
            nombreCliente = "Nombre no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        
        //Correo
        String CorreoCliente = clientedao.obtenerCorreoPorClienteID(envio.getClienteID());
        // Verificar que el nombre no sea nulo o vacío
        if (CorreoCliente == null || CorreoCliente.isEmpty()) {
            CorreoCliente = "Correo no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        
        //Empresa nombre
        int IdEmpresa = encomiendadao.obtenerEmpresaIDPorEncomiendaID(envio.getEncomiendaID());
        String NombreEmpresa = empresasdao.obtenerNombreEmpporEmpresaID(IdEmpresa);
        // Verificar que el nombre no sea nulo o vacío
        if (NombreEmpresa == null || NombreEmpresa.isEmpty()) {
            NombreEmpresa = "Empresa no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        
        //Placa Motorizado
        String Placa = repartidordao.obtenerRepartidorPlacaPorRepartidorID(envio.getRepartidorID());
        // Verificar que el nombre no sea nulo o vacío
        if (Placa == null || Placa.isEmpty()) {
            Placa = "Repartidor no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        
        //Estado
        String Estado = estadodao.obtenerEstadoNombrePorEstadoID(envio.getEstadoID());
        // Verificar que el nombre no sea nulo o vacío
        if (Estado == null || Estado.isEmpty()) {
            Estado = "Estado no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        
       //Destino
        String destino = encomiendadao.obtenerDestinoPorEncomiendaID(envio.getEncomiendaID());
        // Verificar que el nombre no sea nulo o vacío
        if (destino == null || destino.isEmpty()) {
            destino = "Destino no encontrado"; // Texto en caso de que no se encuentre el nombre
        }
        
        Object[] rowData = {
           nombreCliente,
            CorreoCliente,
            NombreEmpresa,
            Placa,
            Estado,
            envio.getFechaEnvio(),
            destino
        };
        tablapaquetes.addRow(rowData); 
    }
}
    
public void MostrarEncomiendas() {
    EnvioDAO envioDAO = new EnvioDAO();
    listaEnv = envioDAO.listarTodo(); // Obtener todas las encomiendas de la base de datos
    
    // Limpiar la tabla antes de agregar nuevos datos
    tablapaquetes.setRowCount(0); 

    if (!listaEnv.isEmpty()) {
        OrdEnv.actualizarLista(listaEnv); // Actualizar la lista en el objeto Ordena
        listarEnvios(); // Llamar al método para llenar la tabla con los nuevos datos
    } else {
        
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        pnlTabla = new javax.swing.JScrollPane();
        tblPaquetes = new javax.swing.JTable();
        btnRecargar = new javax.swing.JButton();
        pnlBuscar = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JLabel();
        txtfEmpresa = new javax.swing.JTextField();
        txtMotorizado = new javax.swing.JLabel();
        txtfMotorizado = new javax.swing.JTextField();
        txtTipo = new javax.swing.JLabel();
        txtfEstado = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtfDistrito = new javax.swing.JTextField();
        txtTipo1 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        pnlFiltrar = new javax.swing.JPanel();
        txtTelefono1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxOrdenMeMa = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbxTipoOrdenarEnco = new javax.swing.JComboBox<>();
        btnOrdenarCate = new javax.swing.JButton();
        btnLimpiar1 = new javax.swing.JButton();
        pnlActualizar = new javax.swing.JPanel();
        txtTelefono2 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxTipoEstado = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescrpEstado = new javax.swing.JTextArea();

        pnlFondo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPaquetes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Correo", "Empresa", "Motorizado", "Estado", "Pago", "Tipo Pago", "Fecha Envio", "Fecha Registro"
            }
        ));
        pnlTabla.setViewportView(tblPaquetes);

        pnlFondo.add(pnlTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 1100, 222));

        btnRecargar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRecargar.setText("Recargar");
        btnRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarActionPerformed(evt);
            }
        });
        pnlFondo.add(btnRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 330, -1, -1));

        pnlBuscar.setBackground(new java.awt.Color(255, 97, 97));
        pnlBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTelefono.setText("Correo:");
        pnlBuscar.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));
        pnlBuscar.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 120, -1));

        txtEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEmpresa.setText("Empresa:");
        pnlBuscar.add(txtEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        pnlBuscar.add(txtfEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 120, -1));

        txtMotorizado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMotorizado.setText("Motorizado:");
        pnlBuscar.add(txtMotorizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        txtfMotorizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfMotorizadoActionPerformed(evt);
            }
        });
        pnlBuscar.add(txtfMotorizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 120, -1));

        txtTipo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTipo.setText("Estado:");
        pnlBuscar.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 49, -1));
        pnlBuscar.add(txtfEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 120, -1));

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlBuscar.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 100, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setText("Buscar Datos:");
        pnlBuscar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        pnlBuscar.add(txtfDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 120, -1));

        txtTipo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTipo1.setText("Distrito:");
        pnlBuscar.add(txtTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 49, -1));

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setText("Limpiar Busqueda");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlBuscar.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 130, -1));

        pnlFondo.add(pnlBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 260, 280));

        pnlFiltrar.setBackground(new java.awt.Color(255, 97, 97));
        pnlFiltrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlFiltrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTelefono1.setText("Dato:");
        pnlFiltrar.add(txtTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setText("Filtrar por:");
        pnlFiltrar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        cbxOrdenMeMa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxOrdenMeMa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "menor -> mayor", "mayor -> menor" }));
        pnlFiltrar.add(cbxOrdenMeMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 130, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Orden:");
        pnlFiltrar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        cbxTipoOrdenarEnco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxTipoOrdenarEnco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Filtrar Por --", "Nombre Cliente", "Empresa", "Estado" }));
        pnlFiltrar.add(cbxTipoOrdenarEnco, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 130, -1));

        btnOrdenarCate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOrdenarCate.setText("Filtrar");
        btnOrdenarCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarCateActionPerformed(evt);
            }
        });
        pnlFiltrar.add(btnOrdenarCate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 100, -1));

        btnLimpiar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar1.setText("Limpiar Filtro");
        btnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar1ActionPerformed(evt);
            }
        });
        pnlFiltrar.add(btnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 100, -1));

        pnlFondo.add(pnlFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 250, 280));

        pnlActualizar.setBackground(new java.awt.Color(255, 97, 97));
        pnlActualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlActualizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTelefono2.setText("Estado:");
        pnlActualizar.add(txtTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        pnlActualizar.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 100, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("Actualizar Estado:");
        pnlActualizar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        cbxTipoEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxTipoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecciona el Estado del Paquete --", "En almacén", "Asignado a Motorizado", "En Ruta", "Entregado", "Camino al almacén", "Inconveniente en el envio" }));
        cbxTipoEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoEstadoItemStateChanged(evt);
            }
        });
        pnlActualizar.add(cbxTipoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDescrpEstado.setColumns(20);
        txtDescrpEstado.setLineWrap(true);
        txtDescrpEstado.setRows(5);
        jScrollPane4.setViewportView(txtDescrpEstado);

        pnlActualizar.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 400, 90));

        pnlFondo.add(pnlActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 440, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtfMotorizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfMotorizadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfMotorizadoActionPerformed

    private void cbxTipoEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoEstadoItemStateChanged
        int EstadoID = cbxTipoEstado.getSelectedIndex();
        if (EstadoID != 0){
            EstadoDAO estaDAO = new EstadoDAO();
            // Recuperar la descripción de la base de datos
            String descripcionEstado = estaDAO.obtenerEstadoDescrPorEstadoID(EstadoID);

            if (descripcionEstado != null) {
                // Asignar la descripción al campo de texto
                txtDescrpEstado.setText(descripcionEstado);
            } else {
                txtDescrpEstado.setText("Descripción no encontrada.");
            }
        } else {
            txtDescrpEstado.setText("");
        }
    }//GEN-LAST:event_cbxTipoEstadoItemStateChanged

    private void btnOrdenarCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarCateActionPerformed

TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblPaquetes.getModel());
tblPaquetes.setRowSorter(sorter);

// Obtener el índice de la columna seleccionada en cbxTipoOrdenarEnco
int columnIndex = -1;
switch (cbxTipoOrdenarEnco.getSelectedIndex()) {
    case 1: // Nombre Cliente
        columnIndex = 0; // Cambia el índice según tu estructura de tabla
        break;
    case 2: // Empresa
        columnIndex = 2; // Cambia el índice según tu estructura de tabla
        break;
    case 3: // Estado
        columnIndex = 4; // Cambia el índice según tu estructura de tabla
        break;
    case 4: // Pago
        columnIndex = 5; // Cambia el índice según tu estructura de tabla
        break;
    default:
        JOptionPane.showMessageDialog(null, "Seleccione un tipo válido para ordenar", "Error de selección", JOptionPane.WARNING_MESSAGE);
        return;
}

// Determinar el orden de ordenación
SortOrder sortOrder;
switch (cbxOrdenMeMa.getSelectedIndex()) {
    case 1:
        sortOrder = SortOrder.ASCENDING;
        break;
    case 0:
        sortOrder = SortOrder.DESCENDING;
        break;
    default:
        JOptionPane.showMessageDialog(null, "Seleccione un orden válido", "Error de selección", JOptionPane.WARNING_MESSAGE);
        return;
}

// Establecer las claves de ordenación
sorter.setSortKeys(List.of(new RowSorter.SortKey(columnIndex, sortOrder)));
    }//GEN-LAST:event_btnOrdenarCateActionPerformed

    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarActionPerformed
        limpiarFiltrosYOrdenacion();
        MostrarEncomiendas();
    }//GEN-LAST:event_btnRecargarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

try {
    // Obtenemos el TableRowSorter de la tabla
    TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblPaquetes.getRowSorter();
    if (sorter == null) {
        sorter = new TableRowSorter<>((DefaultTableModel) tblPaquetes.getModel());
        tblPaquetes.setRowSorter(sorter);
    }

    // Creamos una lista de filtros
    List<RowFilter<Object, Object>> filters = new ArrayList<>();

    // Verificamos cada campo de búsqueda y agregamos un filtro si no está vacío
    if (!txtCorreo.getText().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + txtCorreo.getText(), 1));
    }
    if (!txtfEmpresa.getText().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + txtfEmpresa.getText(), 2));
    }
    if (!txtfMotorizado.getText().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + txtfMotorizado.getText(), 3));
    }
    if (!txtfDistrito.getText().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + txtfDistrito.getText(), 8));
    }
    if (!txtfEstado.getText().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + txtfEstado.getText(), 4));
    }

    // Aplicamos el filtro combinado (AND)
    RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
    sorter.setRowFilter(combinedFilter);

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Ocurrió un error al buscar", "Error", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFiltrosYOrdenacion();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        EnvioDAO enviodao = new EnvioDAO();
        int Estado = cbxTipoEstado.getSelectedIndex();
        int EnvioSelec = tblPaquetes.getSelectedRow();
        enviodao.actualizarEstadoEnvio(Estado, EnvioSelec);
        MostrarEncomiendas();
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JButton btnOrdenarCate;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JComboBox<String> cbxOrdenMeMa;
    private javax.swing.JComboBox<String> cbxTipoEstado;
    private javax.swing.JComboBox<String> cbxTipoOrdenarEnco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlActualizar;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlFiltrar;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JScrollPane pnlTabla;
    private javax.swing.JTable tblPaquetes;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtDescrpEstado;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JLabel txtMotorizado;
    private javax.swing.JLabel txtTelefono;
    private javax.swing.JLabel txtTelefono1;
    private javax.swing.JLabel txtTelefono2;
    private javax.swing.JLabel txtTipo;
    private javax.swing.JLabel txtTipo1;
    private javax.swing.JTextField txtfDistrito;
    private javax.swing.JTextField txtfEmpresa;
    private javax.swing.JTextField txtfEstado;
    private javax.swing.JTextField txtfMotorizado;
    // End of variables declaration//GEN-END:variables
}
