
package ui.panels;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.DatosPersonalesDAO;
import dao.EncomiendaDAO;
import dao.EnvioDAO;
import dao.EstadoDAO;
import dao.PagoDAO;
import dao.RepartidorDAO;
import dao.UbigeoDAO;
import dto.CategoriaDTO;
import dto.ClienteDTO;
import dto.EncomiendaDTO;
import dto.EnvioDTO;
import dto.PagoDTO;
import dto.RepartidorDTO;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class RegistroEnvios extends javax.swing.JPanel {

    List<ClienteDTO> listaClie;
    ArrayList<EncomiendaDTO> listaEnco;
    ArrayList<RepartidorDTO> listaRepa;
    
    DefaultTableModel tablaClientes = new DefaultTableModel(new String[]{"Cliente ID","Nombre","Direccion","Correo"}, 0);
    DefaultTableModel tablaRepartidor = new DefaultTableModel(new String[]{"Repartidor_ID","DNI_ID","Telefono","Vehiculo_Placa","Codigo_UbiGeo"}, 0);
    DefaultTableModel tablaEncomiendas = new DefaultTableModel(new String[]{"Encomienda_ID","Categoria_ID","Destino","Estado_ID","Fecha"}, 0);


    public RegistroEnvios() {
        initComponents();
        tblClientes.setModel(tablaClientes);
        tblMotori.setModel(tablaRepartidor);
        tblEncomiendas.setModel(tablaEncomiendas);
        cargarComboBoxDistritos(cbxDistrito);
        cargarComboBoxDistritos(cbxDistritoClient);
        
        MostrarClientes();
        MostrarRepartidores();
        MostrarEncomiendas();
        desactivarClientesAnt(false);
    }

    //Cargar Distritos en combobox
    public void cargarComboBoxDistritos(JComboBox<String> comboBox) {
    UbigeoDAO ubigeoDAO = new UbigeoDAO();
    ArrayList<String> listaDistritos = ubigeoDAO.listarDistritos();

    for (String distrito : listaDistritos) {
        comboBox.addItem(distrito);
    }
}
    
    //Cliente Antiguos
        private void desactivarClientesAnt(boolean desact){
        jLabel7.setVisible(desact);
        txtNombre.setVisible(desact);
        jLabel9.setVisible(desact);
        txtCorreo.setVisible(desact);
        Dirección.setVisible(desact);
        txtDireccion.setVisible(desact);
        jLabel14.setVisible(desact);
        cbxDistritoClient.setVisible(desact);
        pnlDatosClienteAnt.setVisible(desact);
        
    }
        
    //Tabla cliente
    
    public void MostrarClientes() {
        ClienteDAO cli = new ClienteDAO(); 
        listaClie = cli.listarTodo();
        tablaClientes.setRowCount(0);

        if (listaClie != null && !listaClie.isEmpty()) {
            for (ClienteDTO cliente : listaClie) {
                Object[] rowData = {
                    cliente.getClienteID(),
                    cliente.getNombreCliente(),
                    cliente.getDireccion(),
                    cliente.getCorreo()
                };
                tablaClientes.addRow(rowData);
            }
        }
    }
    
    //Tabla Motorizados
   
    public void MostrarRepartidores() {
        RepartidorDAO rep = new RepartidorDAO();
        listaRepa = rep.listarTodo(); // devuelve List<RepartidorDTO>

        // Limpiar la tabla antes de cargar nuevos datos
        tablaRepartidor.setRowCount(0);

        if (listaRepa != null && !listaRepa.isEmpty()) {
            DatosPersonalesDAO datospersonalesDAO = new DatosPersonalesDAO();
            UbigeoDAO ubigeoDAO = new UbigeoDAO();

            for (RepartidorDTO repartidor : listaRepa) {
                String nombreRepartidor = (datospersonalesDAO.buscarDatosPersonalesPor("Nombre",repartidor.getDniID())).toString();
                if (nombreRepartidor == null || nombreRepartidor.isEmpty()) {
                    nombreRepartidor = "Nombre no encontrado";
                }

                String distrito = ubigeoDAO.obtenerDistritoPorCodigoUbigeo(repartidor.getCodigoUbigeo());
                if (distrito == null || distrito.isEmpty()) {
                    distrito = "Distrito no encontrado";
                }

                Object[] rowData = {
                    repartidor.getRepartidorID(),
                    nombreRepartidor,
                    repartidor.getTelefono(),
                    repartidor.getVehiculoPlaca(),
                    distrito
                };
                tablaRepartidor.addRow(rowData);
            }
        }
    }

    //Encomiendas
    public void MostrarEncomiendas() {
        EncomiendaDAO en = new EncomiendaDAO();
        listaEnco = en.listarTodo(); // Obtener todas las encomiendas de la base de datos

        // Limpiar la tabla antes de agregar nuevos datos
        tablaEncomiendas.setRowCount(0); 

        // Eliminar filtros y ordenación activa en la tabla
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblEncomiendas.getRowSorter();
        if (sorter != null) {
            sorter.setRowFilter(null); 
            sorter.setSortKeys(null);
        }

        if (listaEnco != null && !listaEnco.isEmpty()) {
            EstadoDAO estadoDAO = new EstadoDAO();

            for (EncomiendaDTO encomienda : listaEnco) {
                // Obtener la descripción del estado usando EstadoID
                String descripcionEstado = estadoDAO.obtenerEstadoNombrePorEstadoID(encomienda.getEstadoID());

                // Verificar que la descripción no sea nula
                if (descripcionEstado == null || descripcionEstado.isEmpty()) {
                    descripcionEstado = "Descripción no encontrada";
                }

                Object[] rowData = {
                    encomienda.getEncomiendaID(),
                    encomienda.getCategoriaID(),
                    encomienda.getDestino(),
                    descripcionEstado,  // Mostrar la descripción del estado en lugar del EstadoID
                    encomienda.getFecha()
                };

                tablaEncomiendas.addRow(rowData); // Agregar una nueva fila con los datos
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblRepartidor = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtPago = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxDistrito = new javax.swing.JComboBox<>();
        txtMontoCobrar = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtPrecioPaquete = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtTotalPagar = new javax.swing.JTextField();
        btnCalcularPagoTotal = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cbxTipoPago = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtCantidadPaquete = new javax.swing.JTextField();
        txtEstado = new javax.swing.JLabel();
        txtEncomienda = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        jpnlClientes = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEncomiendas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMotori = new javax.swing.JTable();
        btnRecargarEncomienda = new javax.swing.JButton();
        btnRecargarRepartidor = new javax.swing.JButton();
        btnRecargarCliente = new javax.swing.JButton();
        pnlDatosClienteAnt = new javax.swing.JPanel();
        txtDireccion = new javax.swing.JTextField();
        Dirección = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbxDistritoClient = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        chbClienteNuevo = new javax.swing.JCheckBox();
        txtCliente = new javax.swing.JLabel();
        cbxTipoEstado = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescrpEstado = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtFechaEnvio = new javax.swing.JTextField();
        txtFechaEntrega = new javax.swing.JTextField();
        txtRepartidor = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtCategoria = new javax.swing.JTextArea();

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
        jScrollPane2.setViewportView(tblRepartidor);

        setPreferredSize(new java.awt.Dimension(1150, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrar Envio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 590, 120, 70));

        jPanel2.setBackground(new java.awt.Color(255, 97, 97));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPago.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtPago.setText("Pago:");
        jPanel2.add(txtPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("SubTotal:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Precio Paquete:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("IGV:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Total a Pagar:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Destino:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        cbxDistrito.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxDistrito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un Distrito" }));
        cbxDistrito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDistritoItemStateChanged(evt);
            }
        });
        cbxDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDistritoActionPerformed(evt);
            }
        });
        jPanel2.add(cbxDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 150, -1));

        txtMontoCobrar.setEditable(false);
        jPanel2.add(txtMontoCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 150, -1));

        txtIVA.setEditable(false);
        txtIVA.setText("18");
        jPanel2.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 150, -1));

        txtPrecioPaquete.setText("00.00");
        jPanel2.add(txtPrecioPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 150, -1));

        txtSubtotal.setText("00.00");
        jPanel2.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 150, -1));

        txtTotalPagar.setEditable(false);
        jPanel2.add(txtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 150, -1));

        btnCalcularPagoTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCalcularPagoTotal.setText("Calcular");
        btnCalcularPagoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularPagoTotalActionPerformed(evt);
            }
        });
        jPanel2.add(btnCalcularPagoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 130, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Tipo de cobro:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        cbxTipoPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Tipo", "No cobrar", "Efectivo", "Transferencia", "POS", "Yape", "Plin" }));
        jPanel2.add(cbxTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 150, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Cantidad:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        txtCantidadPaquete.setText("0");
        jPanel2.add(txtCantidadPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 150, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 330, 430));

        txtEstado.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtEstado.setText("Estado del Paquete:");
        jPanel1.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, -1, 20));

        txtEncomienda.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtEncomienda.setText("Encomienda:");
        jPanel1.add(txtEncomienda, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, 20));

        txtFecha.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtFecha.setText("Fecha:");
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, -1, 20));

        tblClientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jpnlClientes.setViewportView(tblClientes);

        jPanel1.add(jpnlClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 740, 120));

        tblEncomiendas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblEncomiendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEncomiendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEncomiendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEncomiendas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 740, 120));

        tblMotori.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblMotori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblMotori);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 740, 130));

        btnRecargarEncomienda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRecargarEncomienda.setText("Recargar");
        jPanel1.add(btnRecargarEncomienda, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        btnRecargarRepartidor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRecargarRepartidor.setText("Recargar");
        btnRecargarRepartidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarRepartidorActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecargarRepartidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        btnRecargarCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRecargarCliente.setText("Recargar");
        btnRecargarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecargarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, -1, -1));

        pnlDatosClienteAnt.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosClienteAnt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlDatosClienteAnt.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 160, -1));

        Dirección.setText("Dirección:");
        pnlDatosClienteAnt.add(Dirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));
        pnlDatosClienteAnt.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 147, -1));

        jLabel9.setText("Correo:");
        pnlDatosClienteAnt.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));
        pnlDatosClienteAnt.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 149, -1));

        jLabel7.setText("Nombre:");
        pnlDatosClienteAnt.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 50, -1));

        cbxDistritoClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un Distrito" }));
        cbxDistritoClient.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDistritoClientItemStateChanged(evt);
            }
        });
        cbxDistritoClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDistritoClientActionPerformed(evt);
            }
        });
        pnlDatosClienteAnt.add(cbxDistritoClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 160, 20));

        jLabel14.setText("Distrito:");
        pnlDatosClienteAnt.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 56, 50, 20));

        jPanel1.add(pnlDatosClienteAnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 740, 90));

        chbClienteNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chbClienteNuevo.setText("Cliente Nuevo");
        chbClienteNuevo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbClienteNuevoItemStateChanged(evt);
            }
        });
        chbClienteNuevo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chbClienteNuevoStateChanged(evt);
            }
        });
        jPanel1.add(chbClienteNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, -1, -1));

        txtCliente.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtCliente.setText("Cliente:");
        jPanel1.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, -1, 20));

        cbxTipoEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxTipoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecciona el Estado del Paquete --", "En almacén", "Asignado a Motorizado", "En Ruta", "Entregado", "Camino al almacén", "Inconveniente en el envio" }));
        cbxTipoEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoEstadoItemStateChanged(evt);
            }
        });
        jPanel1.add(cbxTipoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 630, 240, -1));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDescrpEstado.setEditable(false);
        txtDescrpEstado.setColumns(20);
        txtDescrpEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDescrpEstado.setLineWrap(true);
        txtDescrpEstado.setRows(5);
        jScrollPane4.setViewportView(txtDescrpEstado);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 600, 400, 90));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Fecha Envio:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 490, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Fecha Entrega:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, -1, -1));

        txtFechaEnvio.setText("yyyy-mm-dd");
        jPanel1.add(txtFechaEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 490, 150, -1));

        txtFechaEntrega.setText("yyyy-mm-dd");
        txtFechaEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaEntregaActionPerformed(evt);
            }
        });
        jPanel1.add(txtFechaEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 530, 150, -1));

        txtRepartidor.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtRepartidor.setText("Repartidor:");
        jPanel1.add(txtRepartidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, 20));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtCategoria.setEditable(false);
        txtCategoria.setColumns(20);
        txtCategoria.setRows(5);
        jScrollPane5.setViewportView(txtCategoria);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 310, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxDistritoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDistritoItemStateChanged
        int DistritoMonto;
        DistritoMonto = cbxDistrito.getSelectedIndex();  // Obtener el índice del distrito seleccionado

        // Usamos los índices para asignar los valores correctos
        if (DistritoMonto == 1 || DistritoMonto == 2 || DistritoMonto == 3 || DistritoMonto == 4 ||
            DistritoMonto == 11 || DistritoMonto == 12 || DistritoMonto == 13 || DistritoMonto == 14 ||
            DistritoMonto == 15 || DistritoMonto == 19 || DistritoMonto == 22) {
            txtMontoCobrar.setText("8.00");  // Precio para Barranco, Breña, Cercado de Lima, Jesús María, etc.
        } else if (DistritoMonto == 5 || DistritoMonto == 7 || DistritoMonto == 9 || DistritoMonto == 10 ||
            DistritoMonto == 16 || DistritoMonto == 17 || DistritoMonto == 18 || DistritoMonto == 20 ||
            DistritoMonto == 21 || DistritoMonto == 23) {
            txtMontoCobrar.setText("10.00");  // Precio para Carabayllo, Chorrillos, Independencia, Comas, etc.
        } else if (DistritoMonto == 24) {
            txtMontoCobrar.setText("12.00");  // Precio para Lurín
        } else if (DistritoMonto == 6) {
            txtMontoCobrar.setText("14.00");  // Precio para Ancón, Chaclacayo, Manchay
        } else if (DistritoMonto == 8) {
            txtMontoCobrar.setText("15.00");  // Precio para Cieneguilla
        } else if (DistritoMonto == 25) {
            txtMontoCobrar.setText("17.00");  // Precio para Pachacamac
        } else if (DistritoMonto == 26) {
            txtMontoCobrar.setText("18.00");  // Precio para Cajamarquilla, Jicamarca Anexo 8, Punta Negra
        } else if (DistritoMonto == 27) {
            txtMontoCobrar.setText("20.00");  // Precio para Chosica, San Bartolo
        } else {
            txtMontoCobrar.setText("0.00");  // Valor por defecto si no hay distrito seleccionado
        }
    }//GEN-LAST:event_cbxDistritoItemStateChanged

    private void cbxDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDistritoActionPerformed

    private void btnRecargarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarClienteActionPerformed
        MostrarClientes();
    }//GEN-LAST:event_btnRecargarClienteActionPerformed

    private void btnRecargarRepartidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarRepartidorActionPerformed
        MostrarRepartidores();
    }//GEN-LAST:event_btnRecargarRepartidorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
dateFormat.setLenient(false);  // Para asegurar que solo fechas válidas sean aceptadas

// Obtener las fechas de los campos de texto
String strFechaEnvio = txtFechaEnvio.getText().trim();
String strFechaEntrega = txtFechaEntrega.getText().trim();

// Validar que las fechas no estén vacías
if (strFechaEnvio.isEmpty() || strFechaEntrega.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Las fechas de envío y entrega no pueden estar vacías.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}

// Intentar convertir las fechas de texto a objetos Date
Date FechaEnvio;
Date FechaEntrega;
try {
    FechaEnvio = dateFormat.parse(strFechaEnvio);
    FechaEntrega = dateFormat.parse(strFechaEntrega);
} catch (ParseException e) {
    JOptionPane.showMessageDialog(null, "Por favor ingresa las fechas en el formato correcto (yyyy-MM-dd).", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}

// Validar que la fecha de envío no sea posterior a la fecha de entrega
if (FechaEnvio.after(FechaEntrega)) {
    JOptionPane.showMessageDialog(null, "La fecha de envío no puede ser posterior a la fecha de entrega.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}

// Validar selección de encomienda
int filaEncomienda = tblEncomiendas.getSelectedRow();
if (filaEncomienda == -1) {
    JOptionPane.showMessageDialog(null, "Por favor selecciona una encomienda.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}
int encomiendaID = Integer.parseInt(tblEncomiendas.getValueAt(filaEncomienda, 0).toString());

// Validar selección de repartidor
int filaRepartidor = tblMotori.getSelectedRow();
if (filaRepartidor == -1) {
    JOptionPane.showMessageDialog(null, "Por favor selecciona un repartidor.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}
int repartidorID = Integer.parseInt(tblMotori.getValueAt(filaRepartidor, 0).toString());

// Validar selección de estado
int estadoID = cbxTipoEstado.getSelectedIndex() + 1; // Asumimos que los índices empiezan desde 1

// Manejar Cliente: Si el checkbox está seleccionado, crear nuevo cliente; si no, usar cliente existente
int clienteID;
if (chbClienteNuevo.isSelected()) {
    // Crear un nuevo cliente
    String Nombre = txtNombre.getText().trim();
    String Correo = txtCorreo.getText().trim();
    String Direccion = txtDireccion.getText().trim();
    String Ubigeo = cbxDistritoClient.getSelectedItem().toString();

    if (Nombre.isEmpty() || Correo.isEmpty() || Direccion.isEmpty() || Ubigeo.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor completa todos los datos del nuevo cliente.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Crear cliente nuevo
    ClienteDTO nuevoCliente = new ClienteDTO();
    nuevoCliente.setNombreCliente(Nombre);
    nuevoCliente.setCorreo(Correo);
    nuevoCliente.setDireccion(Direccion);

    // Obtener el ID de Ubigeo
    UbigeoDAO ubigeoDAO = new UbigeoDAO();
    int ubigeoID = ubigeoDAO.obtenerUbigeoIDPorDistrito(Ubigeo);
    if (ubigeoID == -1) {
        JOptionPane.showMessageDialog(null, "El distrito seleccionado no es válido.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    nuevoCliente.setUbigeo(ubigeoID);

    // Registrar el nuevo cliente en la base de datos
    ClienteDAO clienteDAO = new ClienteDAO();
    clienteID = clienteDAO.crearCliente(nuevoCliente); // Obtener el ID del nuevo cliente
} else {
    // Seleccionar cliente existente de la tabla
    int filaCliente = tblClientes.getSelectedRow();
    if (filaCliente == -1) {
        JOptionPane.showMessageDialog(null, "Por favor selecciona un cliente existente.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    clienteID = Integer.parseInt(tblClientes.getValueAt(filaCliente, 0).toString());
}

// Registrar el envío en la base de datos
EnvioDTO envio = new EnvioDTO();
envio.setEncomiendaID(encomiendaID);
envio.setFechaEnvio(FechaEnvio);
envio.setFechaEntrega(FechaEntrega);
envio.setEstadoID(estadoID);
envio.setRepartidorID(repartidorID);
envio.setClienteID(clienteID);

EnvioDAO envioDAO = new EnvioDAO();
envioDAO.crearEnvio(envio); // Guardar el envío

// Registrar el pago asociado al cliente
String montoTotalStr = txtTotalPagar.getText().trim();
if (montoTotalStr.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Por favor, ingresa el monto total a pagar.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}
double montoTotal;
try {
    montoTotal = Double.parseDouble(montoTotalStr);
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Por favor, ingresa un monto total válido.", "Error", JOptionPane.WARNING_MESSAGE);
    return;
}

String tipoPago = cbxTipoPago.getSelectedItem().toString();

PagoDTO pago = new PagoDTO();
pago.setClienteID(clienteID);
pago.setPrecio(BigDecimal.valueOf(montoTotal));
pago.setTipoPago(tipoPago);

PagoDAO pagoDAO = new PagoDAO();
pagoDAO.crearPago(pago); // Guardar el pago en la base de datos

JOptionPane.showMessageDialog(null, "Envío y pago registrados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxDistritoClientItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDistritoClientItemStateChanged

    }//GEN-LAST:event_cbxDistritoClientItemStateChanged

    private void cbxDistritoClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDistritoClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDistritoClientActionPerformed

    private void chbClienteNuevoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbClienteNuevoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            desactivarClientesAnt(true);
            jpnlClientes.setVisible(false);
            tblClientes.setVisible(false);
        } else {
            desactivarClientesAnt(false);
            jpnlClientes.setVisible(true);
            tblClientes.setVisible(true);
        }
    }//GEN-LAST:event_chbClienteNuevoItemStateChanged

    private void chbClienteNuevoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chbClienteNuevoStateChanged

    }//GEN-LAST:event_chbClienteNuevoStateChanged

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

    private void txtFechaEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEntregaActionPerformed

    private void btnCalcularPagoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularPagoTotalActionPerformed
if (txtPrecioPaquete.getText().isEmpty() || txtCantidadPaquete.getText().isEmpty() || txtMontoCobrar.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
} else {
    try {
        // Parsear los valores ingresados
        double PrePaquete = Double.parseDouble(txtPrecioPaquete.getText());
        int Cantidad = Integer.parseInt(txtCantidadPaquete.getText());
        double MontoDistrito = Double.parseDouble(txtMontoCobrar.getText());
        
        // Realizar los cálculos
        double PrePaqueteTot = MontoDistrito + (PrePaquete * Cantidad);
        double IGV = PrePaqueteTot * 18 / 100;
        double TotalFinal = PrePaqueteTot + IGV;

        // Mostrar el resultado en txtTotalPagar
        txtTotalPagar.setText(String.valueOf(TotalFinal));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos.");
    }
    }
    }//GEN-LAST:event_btnCalcularPagoTotalActionPerformed

    private void tblEncomiendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEncomiendasMouseClicked
 // Obtener el ID de la categoría de la fila seleccionada
    int CategoriaID = Integer.parseInt(tblEncomiendas.getValueAt(tblEncomiendas.getSelectedRow(), 1).toString());
    
    // Crear instancias del DTO y DAO
    CategoriaDAO cat = new CategoriaDAO();
    
    // Obtener la categoría usando el ID
    CategoriaDTO categoria = cat.buscarCategoriaPorId(CategoriaID);
    
    // Si la categoría existe, imprimir peso, alto y ancho en txtCategoria
    if (categoria != null) {
        String detalles = "Peso: " + categoria.getPeso() + "\n" +
                          "Largo: " + categoria.getLargo()+ "\n" +
                          "Ancho: " + categoria.getAncho();
        txtCategoria.setText(detalles);
    } else {
        txtCategoria.setText("Categoría no encontrada.");
    }
    }//GEN-LAST:event_tblEncomiendasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dirección;
    private javax.swing.JButton btnCalcularPagoTotal;
    private javax.swing.JButton btnRecargarCliente;
    private javax.swing.JButton btnRecargarEncomienda;
    private javax.swing.JButton btnRecargarRepartidor;
    private javax.swing.JComboBox<String> cbxDistrito;
    private javax.swing.JComboBox<String> cbxDistritoClient;
    private javax.swing.JComboBox<String> cbxTipoEstado;
    private javax.swing.JComboBox<String> cbxTipoPago;
    private javax.swing.JCheckBox chbClienteNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jpnlClientes;
    private javax.swing.JPanel pnlDatosClienteAnt;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblEncomiendas;
    private javax.swing.JTable tblMotori;
    private javax.swing.JTable tblRepartidor;
    private javax.swing.JTextField txtCantidadPaquete;
    private javax.swing.JTextArea txtCategoria;
    private javax.swing.JLabel txtCliente;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtDescrpEstado;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JLabel txtEncomienda;
    private javax.swing.JLabel txtEstado;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtFechaEntrega;
    private javax.swing.JTextField txtFechaEnvio;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtMontoCobrar;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtPago;
    private javax.swing.JTextField txtPrecioPaquete;
    private javax.swing.JLabel txtRepartidor;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
