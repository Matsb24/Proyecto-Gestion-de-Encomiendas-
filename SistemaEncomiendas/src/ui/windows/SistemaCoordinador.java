/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.windows;

import pnlInterfaces.GestionEnvios;
import pnlInterfaces.RegistroPaquetes;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pnlInterfaces.Motorizados;
import pnlInterfaces.RegistroEnvios;

/**
 *
 * @author User
 */
public class SistemaCoordinador extends javax.swing.JFrame {

    private Timer inactivityTimer;
    private final int INACTIVITY_LIMIT = 120000;
    
    public SistemaCoordinador() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public void llamarPanel(JPanel p){
        p.setSize(1150, 720);
        p.setLocation(0,0);
        
        addInactivityListeners();
        resetInactivityTimer();
        pnlBaseLlamadas.removeAll();
        pnlBaseLlamadas.add(p, BorderLayout.CENTER);
        pnlBaseLlamadas.revalidate();
        pnlBaseLlamadas.repaint();
        
    }
    
       private void addInactivityListeners() {
        // Detectar movimiento del mouse
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                resetInactivityTimer();
            }
        });

        // Detectar actividad del teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                resetInactivityTimer();
            }
        });
    }

    private void resetInactivityTimer() {
        if (inactivityTimer != null) {
            inactivityTimer.cancel(); // Cancela el temporizador previo si existe
        }
        
        // Crear un nuevo temporizador y programar una tarea para cerrar la sesión
        inactivityTimer = new Timer();
        inactivityTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                redirectToLogin();
            }
        }, INACTIVITY_LIMIT);
    }

    private void redirectToLogin() {
        SwingUtilities.invokeLater(() -> {
            dispose(); // Cierra la ventana actual de SistemaAdministrador
            new Login().setVisible(true); // Abre la pantalla de login
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        pnlBarraLateral = new javax.swing.JPanel();
        btnGestionarPaquetes = new javax.swing.JButton();
        btnMotorizados = new javax.swing.JButton();
        btnPesaje = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnRegistrarPedido = new javax.swing.JButton();
        pnlBaseLlamadas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtTitulos = new javax.swing.JLabel();

        jLabel12.setText("jLabel12");

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBarraLateral.setBackground(new java.awt.Color(204, 0, 51));
        pnlBarraLateral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGestionarPaquetes.setBackground(new java.awt.Color(204, 0, 51));
        btnGestionarPaquetes.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnGestionarPaquetes.setForeground(new java.awt.Color(255, 255, 255));
        btnGestionarPaquetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gestion encomiendas.png"))); // NOI18N
        btnGestionarPaquetes.setText("Gestión Envios");
        btnGestionarPaquetes.setBorder(null);
        btnGestionarPaquetes.setBorderPainted(false);
        btnGestionarPaquetes.setContentAreaFilled(false);
        btnGestionarPaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarPaquetesActionPerformed(evt);
            }
        });
        pnlBarraLateral.add(btnGestionarPaquetes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 245, 78));

        btnMotorizados.setBackground(new java.awt.Color(204, 0, 51));
        btnMotorizados.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnMotorizados.setForeground(new java.awt.Color(255, 255, 255));
        btnMotorizados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/motorizados.png"))); // NOI18N
        btnMotorizados.setText("Motorizados");
        btnMotorizados.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnMotorizados.setBorderPainted(false);
        btnMotorizados.setContentAreaFilled(false);
        btnMotorizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMotorizadosActionPerformed(evt);
            }
        });
        pnlBarraLateral.add(btnMotorizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 245, 78));

        btnPesaje.setBackground(new java.awt.Color(204, 0, 51));
        btnPesaje.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnPesaje.setForeground(new java.awt.Color(255, 255, 255));
        btnPesaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons8-caja-de-entrega-externa-a-la-direccion-de-envio-original-entrega-llena-tal-revivo-48.png"))); // NOI18N
        btnPesaje.setText("Registrar Envio");
        btnPesaje.setBorder(null);
        btnPesaje.setBorderPainted(false);
        btnPesaje.setContentAreaFilled(false);
        btnPesaje.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPesaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesajeActionPerformed(evt);
            }
        });
        pnlBarraLateral.add(btnPesaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 245, 80));

        jButton6.setBackground(new java.awt.Color(255, 0, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Cerrar Sesión");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pnlBarraLateral.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 710, 150, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo_2.png"))); // NOI18N
        pnlBarraLateral.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 245, 138));

        btnRegistrarPedido.setBackground(new java.awt.Color(204, 0, 51));
        btnRegistrarPedido.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnRegistrarPedido.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons8-registro-48.png"))); // NOI18N
        btnRegistrarPedido.setText("Nuevo Pedido");
        btnRegistrarPedido.setBorder(null);
        btnRegistrarPedido.setBorderPainted(false);
        btnRegistrarPedido.setContentAreaFilled(false);
        btnRegistrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPedidoActionPerformed(evt);
            }
        });
        pnlBarraLateral.add(btnRegistrarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 245, 78));

        getContentPane().add(pnlBarraLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 850));

        pnlBaseLlamadas.setBackground(new java.awt.Color(255, 255, 255));
        pnlBaseLlamadas.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlBaseLlamadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 1150, 720));

        jPanel1.setBackground(new java.awt.Color(217, 0, 51));

        txtTitulos.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        txtTitulos.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtTitulos, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitulos, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 1150, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnMotorizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMotorizadosActionPerformed
        Motorizados p3 = new Motorizados();
        llamarPanel(p3);
        txtTitulos.setText("Registro de Motorizados");
    }//GEN-LAST:event_btnMotorizadosActionPerformed

    private void btnGestionarPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarPaquetesActionPerformed
        GestionEnvios p1 = new GestionEnvios();
        llamarPanel(p1);
        txtTitulos.setText("Gestion de Envios");
    }//GEN-LAST:event_btnGestionarPaquetesActionPerformed

    private void btnRegistrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPedidoActionPerformed
        RegistroPaquetes p2 = new RegistroPaquetes();
        llamarPanel(p2);
        txtTitulos.setText("Registro de paquetes");
    }//GEN-LAST:event_btnRegistrarPedidoActionPerformed

    private void btnPesajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesajeActionPerformed
        RegistroEnvios p5 = new RegistroEnvios();
        llamarPanel(p5);
        txtTitulos.setText("Registrar Envios");
    }//GEN-LAST:event_btnPesajeActionPerformed

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
            java.util.logging.Logger.getLogger(SistemaCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaCoordinador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGestionarPaquetes;
    private javax.swing.JButton btnMotorizados;
    private javax.swing.JButton btnPesaje;
    private javax.swing.JButton btnRegistrarPedido;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JPanel pnlBarraLateral;
    public javax.swing.JPanel pnlBaseLlamadas;
    private javax.swing.JLabel txtTitulos;
    // End of variables declaration//GEN-END:variables
}
