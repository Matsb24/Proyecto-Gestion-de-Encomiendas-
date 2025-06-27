/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.panels;

import dao.CategoriaDAO;
import dao.EmpresasDAO;
import dao.EncomiendaDAO;
import dao.UbigeoDAO;
import dto.CategoriaDTO;
import dto.EmpresasDTO;
import dto.EncomiendaDTO;
import dto.ubigeoDTO;
import ui.ui.windows.SistemaAdministrador;
import utils.OrdenarDatos.OrdenaCate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Matias
 */
public class RegistroPaquetes extends javax.swing.JPanel {

    private SistemaAdministrador sistema;
   DefaultTableModel tablaCategoria = new DefaultTableModel();
    ArrayList<CategoriaDTO> listaCate;
    OrdenaCate ordC;
    public RegistroPaquetes() {
        initComponents();
        
        cargarComboBoxDistritos(cbxDestino);
        cargarNomEmpresasEnComboBox();
        activar();
        ordC = new utils.OrdenarDatos.OrdenaCate();
        tblCategoria.setModel(tablaCategoria);
        cbxRegistrarEmpresa.setVisible(false);
        txtNomEmpresa.setVisible(false);
        txtNombreEmpresa.setVisible(false);
        mostrarCabeceraCate();
        MostrarCategorias();
    }

    public void mostrarCabeceraCate(){
        tablaCategoria.addColumn("Categoria ID");
        tablaCategoria.addColumn("Peso");
        tablaCategoria.addColumn("Ancho");
        tablaCategoria.addColumn("Largo");
        tblCategoria.setModel(tablaCategoria);
    }
    
    private void desactivar(){
        cbxEmpresas.setEnabled(false);
        txtNomEmpresa.setEnabled(false);
        cbxRegistrarEmpresa.setEnabled(false);
        jTextField8.setEnabled(false);
    }
    
    private boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    return email.matches(emailRegex);
}
    
    private void activar(){
        cbxEmpresas.setEnabled(true);
        txtNomEmpresa.setEnabled(true);
        cbxRegistrarEmpresa.setEnabled(true);
        jTextField8.setEnabled(true);
    }
    
     private void listarCategoria() {
    for (int i = 0; i < ordC.getLista().size(); i++) {
        Object[] rowData = {
            ordC.getLista().get(i).getCategoriaID(),
            ordC.getLista().get(i).getPeso(),
            ordC.getLista().get(i).getAncho(),
            ordC.getLista().get(i).getLargo()
        };
        tablaCategoria.addRow(rowData); // Agregamos una nueva fila con los datos
    }
}
   
   public void MostrarCategorias() {
    CategoriaDAO ca = new CategoriaDAO(); // No es necesario crear un nuevo CategoriaDTO aquí
    listaCate = ca.listarTodo(); // Obtener todas las categorías de la base de datos
    tablaCategoria.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
    TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblCategoria.getRowSorter();
    if (sorter != null) {
        sorter.setRowFilter(null); // Elimina cualquier filtro activo
        sorter.setSortKeys(null);  // Elimina cualquier ordenación activa
    }

    tablaCategoria.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

    if (!listaCate.isEmpty()) {
        ordC.actualizarLista(listaCate); // Actualizar la lista en el objeto Ordena
        listarCategoria(); // Llamar al método para llenar la tabla con los nuevos datos
    }
}
   
   
public void cargarComboBoxDistritos(JComboBox<String> comboBox) {
    UbigeoDAO ubigeoDAO = new UbigeoDAO(); // Asegúrate de tener la instancia correcta
    ArrayList<String> listaDistritos = ubigeoDAO.listarDistritos(); // Obtener distritos

    for (String distrito : listaDistritos) {
        comboBox.addItem(distrito); // Añadir cada distrito al JComboBox
    }
}
    
    public void cargarNomEmpresasEnComboBox() {
        try {
            EmpresasDAO empresasDAO = new EmpresasDAO(); // Instanciar el DAO
            ArrayList<EmpresasDTO> listaEmpresas = empresasDAO.listarTodo(); // Obtener la lista de empresas
            
            // Recorrer la lista de empresas y añadirlas al JComboBox
            for (EmpresasDTO empresa : listaEmpresas) {
                cbxEmpresas.addItem(empresa.getNombre_Empresa()); // Añadir cada nombre de empresa al JComboBox
            }

        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones, imprime la traza del error
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistroPaquetes = new javax.swing.JPanel();
        btnRegistrarPaquete = new javax.swing.JButton();
        txtID2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxDestino = new javax.swing.JComboBox<>();
        txtEst = new javax.swing.JLabel();
        cbxEstadoEnco = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JLabel();
        cbxEmpresas = new javax.swing.JComboBox<>();
        txtNomEmpresa = new javax.swing.JTextField();
        cbxRegistrarEmpresa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtDatosCliente1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCategoria = new javax.swing.JTable();
        chbxFijar = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        pnlRegistroPaquetes.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistroPaquetes.setPreferredSize(new java.awt.Dimension(1150, 720));
        pnlRegistroPaquetes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrarPaquete.setBackground(new java.awt.Color(255, 51, 51));
        btnRegistrarPaquete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarPaquete.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarPaquete.setText("Registrar Paquete");
        btnRegistrarPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPaqueteActionPerformed(evt);
            }
        });
        pnlRegistroPaquetes.add(btnRegistrarPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 490, -1, 70));

        txtID2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtID2.setText("Filtrar:");
        pnlRegistroPaquetes.add(txtID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 170, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 97, 97));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("Detalles del Paquete:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("¿Que envia? ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 360, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Destino:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        cbxDestino.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un Distrito" }));
        cbxDestino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDestinoItemStateChanged(evt);
            }
        });
        cbxDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDestinoActionPerformed(evt);
            }
        });
        jPanel1.add(cbxDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        txtEst.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEst.setText("Estado:");
        jPanel1.add(txtEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 50, 20));

        cbxEstadoEnco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxEstadoEnco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Almacen", "Camino al almacén" }));
        jPanel1.add(cbxEstadoEnco, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 150, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Empresa:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, -1));

        txtNombreEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombreEmpresa.setText("Nombre de la Empresa:");
        jPanel1.add(txtNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        cbxEmpresas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxEmpresas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Empresa", "Agregar Empresa" }));
        cbxEmpresas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEmpresasItemStateChanged(evt);
            }
        });
        jPanel1.add(cbxEmpresas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, -1));
        jPanel1.add(txtNomEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 160, -1));

        cbxRegistrarEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxRegistrarEmpresa.setText("Registrar Empresa");
        cbxRegistrarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRegistrarEmpresaActionPerformed(evt);
            }
        });
        jPanel1.add(cbxRegistrarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, -1, -1));

        pnlRegistroPaquetes.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 820, 170));

        jPanel2.setBackground(new java.awt.Color(255, 97, 97));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDatosCliente1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtDatosCliente1.setText("Categorias:");
        jPanel2.add(txtDatosCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setText("Selecciona una Categoria ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 140, -1));

        tblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CategoriaID", "Peso", "Ancho", "Largo"
            }
        ));
        tblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCategoriaMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblCategoria);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 730, 110));

        chbxFijar.setText("Fijar");
        chbxFijar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chbxFijarStateChanged(evt);
            }
        });
        chbxFijar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxFijarActionPerformed(evt);
            }
        });
        jPanel2.add(chbxFijar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        pnlRegistroPaquetes.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 820, 180));

        add(pnlRegistroPaquetes, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cbxEmpresasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEmpresasItemStateChanged
        switch (cbxEmpresas.getSelectedIndex()) {
            case 1:
                desactivar();
                cbxEmpresas.setEnabled(true);
                txtNomEmpresa.setEnabled(true);
                cbxRegistrarEmpresa.setEnabled(true);
                cbxRegistrarEmpresa.setVisible(true);
                  txtNomEmpresa.setVisible(true);
                  txtNombreEmpresa.setVisible(true);
                break;
            default:
                activar();
                cbxRegistrarEmpresa.setVisible(false);
                  txtNomEmpresa.setVisible(false);
                  txtNombreEmpresa.setVisible(false);
        }
    }//GEN-LAST:event_cbxEmpresasItemStateChanged

    private void cbxRegistrarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRegistrarEmpresaActionPerformed
        activar();
        cbxRegistrarEmpresa.setVisible(false);
        txtNomEmpresa.setVisible(false);
        txtNombreEmpresa.setVisible(false);
        
        EmpresasDTO e = new EmpresasDTO();
        EmpresasDAO em = new EmpresasDAO();
        
        if(txtNomEmpresa.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede dejar los campos vacios", "Campos Vacios", HEIGHT); 
        }else if (txtNomEmpresa.getText().length() > 50) { // Suponiendo que el límite sea 50
              JOptionPane.showMessageDialog(null, "El nombre de la empresa es demasiado largo. Máximo 65 caracteres.");

           } else {
                e.setNombre_Empresa(txtNomEmpresa.getText());
                em.insertar(e);
                JOptionPane.showMessageDialog(null, "Empresa registrada");
                txtNomEmpresa.setText("");
        }
        
        //txtNomEmpresa;
    }//GEN-LAST:event_cbxRegistrarEmpresaActionPerformed

    private void tblCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriaMousePressed

    }//GEN-LAST:event_tblCategoriaMousePressed

    private void chbxFijarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chbxFijarStateChanged
        if(chbxFijar.isSelected()){
            tblCategoria.setEnabled(false);
        } else {
            tblCategoria.setEnabled(true);
        }
    }//GEN-LAST:event_chbxFijarStateChanged

    private void chbxFijarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxFijarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbxFijarActionPerformed

    private void cbxDestinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDestinoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDestinoItemStateChanged

    private void cbxDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDestinoActionPerformed

    private void btnRegistrarPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPaqueteActionPerformed

String Ubigeo = cbxDestino.getSelectedItem() != null ? cbxDestino.getSelectedItem().toString() : null;
if (Ubigeo == null || Ubigeo.isEmpty() || cbxDestino.getSelectedIndex() == 0) {
    JOptionPane.showMessageDialog(null, "Por favor selecciona un distrito válido.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}        
        
// Verificar que cbxEmpresas no esté seleccionando 0 o 1
if (cbxEmpresas.getSelectedIndex() == 0 || cbxEmpresas.getSelectedIndex() == 1) {
    JOptionPane.showMessageDialog(null, "Por favor selecciona una empresa válida.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}

// Verificar si se seleccionó una categoría
int filaCategoria = tblCategoria.getSelectedRow();
if (filaCategoria == -1) {
    JOptionPane.showMessageDialog(null, "Por favor selecciona una categoría.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}

// Verificar el estado del paquete
if (cbxEstadoEnco.getSelectedIndex() == -1) {
    JOptionPane.showMessageDialog(null, "Seleccionar el estado del paquete.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}

// Crear la encomienda con los datos verificados
EncomiendaDTO en = new EncomiendaDTO();
en.setCategoriaID(tblCategoria.getValueAt(filaCategoria, 0).toString()); // Se puede parsear si es necesario

// Verificar el estado del paquete y asignarlo
switch (cbxEstadoEnco.getSelectedIndex()) {
    case 0:
        en.setEstadoID(1); // Estado "Nuevo" por ejemplo
        break;
    case 1:
        en.setEstadoID(5); // Estado "Entregado" por ejemplo
        break;
    default:
        JOptionPane.showMessageDialog(null, "Seleccionar el estado del paquete.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
}

EmpresasDAO empresasdao = new EmpresasDAO();

// Asignar los otros campos
en.setFecha(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
en.setDestino(Ubigeo);
int EmpresaNom = empresasdao.obtenerEmpresaIDPorNombre(cbxEmpresas.getSelectedItem().toString());
en.setEmpresaID(EmpresaNom);

// Insertar la encomienda en la base de datos
EncomiendaDAO encomiendaDAO = new EncomiendaDAO();
encomiendaDAO.crearEncomienda(en); // Método para guardar la encomienda en la base de datos

JOptionPane.showMessageDialog(null, "Paquete registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_btnRegistrarPaqueteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarPaquete;
    private javax.swing.JComboBox<String> cbxDestino;
    private javax.swing.JComboBox<String> cbxEmpresas;
    private javax.swing.JComboBox<String> cbxEstadoEnco;
    private javax.swing.JButton cbxRegistrarEmpresa;
    private javax.swing.JCheckBox chbxFijar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JPanel pnlRegistroPaquetes;
    private javax.swing.JTable tblCategoria;
    private javax.swing.JLabel txtDatosCliente1;
    private javax.swing.JLabel txtEst;
    private javax.swing.JLabel txtID2;
    private javax.swing.JTextField txtNomEmpresa;
    private javax.swing.JLabel txtNombreEmpresa;
    // End of variables declaration//GEN-END:variables
}
