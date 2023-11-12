/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Client.ECG;
import Client.Patient;
import Client.SocketObject;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.JDBCECGManager;
import jdbc.JDBCManager;
import jdbc.JDBCPatientManager;

/**
 *
 * @author angel
 */
public class ShowSignal extends javax.swing.JFrame implements WindowListener {

    private Object bean;
    private JDBCManager manager;
    private JDBCPatientManager patientManager;
    private JDBCECGManager ecgManager;
    private SocketObject socket;
    private Patient patient;
    private SignalsRegistered signalsRegistered;
    private ECG ecg;
    private ShowSignal showSignal;

    public ShowSignal(JDBCManager manager, JDBCPatientManager patientManager, JDBCECGManager ecgManager, SocketObject socket, Patient patient, SignalsRegistered signalsRegistered, ECG ecg) {
        this.manager = manager;
        this.patientManager = patientManager;
        this.ecgManager = ecgManager;
        this.socket = socket;
        this.patient = patient;
        this.signalsRegistered = signalsRegistered;
        this.ecg = ecg;
        initComponents();
    }

    /**
     * Creates new form ShowSignal
     */
    public ShowSignal() {
        initComponents();
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public JDBCManager getManager() {
        return manager;
    }

    public void setManager(JDBCManager manager) {
        this.manager = manager;
    }

    public JDBCPatientManager getPatientManager() {
        return patientManager;
    }

    public void setPatientManager(JDBCPatientManager patientManager) {
        this.patientManager = patientManager;
    }

    public JDBCECGManager getEcgManager() {
        return ecgManager;
    }

    public void setEcgManager(JDBCECGManager ecgManager) {
        this.ecgManager = ecgManager;
    }

    public SocketObject getSocket() {
        return socket;
    }

    public void setSocket(SocketObject socket) {
        this.socket = socket;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public SignalsRegistered getSignalsRegistered() {
        return signalsRegistered;
    }

    public void setSignalsRegistered(SignalsRegistered signalsRegistered) {
        this.signalsRegistered = signalsRegistered;
    }

    public ECG getEcg() {
        return ecg;
    }

    public void setEcg(ECG ecg) {
        this.ecg = ecg;
    }

    public ShowSignal getShowSignal() {
        return showSignal;
    }

    public void setShowSignal(ShowSignal showSignal) {
        this.showSignal = showSignal;
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
        jPanel2 = new javax.swing.JPanel();
        ShowPlotButton = new javax.swing.JButton();
        GoBackButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        ShowPlotButton.setText("Show Plot");
        ShowPlotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPlotButtonActionPerformed(evt);
            }
        });

        GoBackButton.setText("Go Back");
        GoBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoBackButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(ShowPlotButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GoBackButton)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShowPlotButton)
                    .addComponent(GoBackButton))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowPlotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowPlotButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowPlotButtonActionPerformed

    private void GoBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoBackButtonActionPerformed
        // TODO add your handling code here:
        ArrayList<ECG> ecgs = ecgManager.findECGByPatient(patient.getId()); //creamos arraylist
        SignalsRegistered signalsregistered = new SignalsRegistered(manager, patientManager, ecgManager, socket, ecgs, patient);
        signalsregistered.setSignalsRegistered(signalsregistered);
        signalsregistered.setVisible(true);
        int option = 2;//OPTION 1 IS SHOW PLOT
        try {
            socket.getOutputStream().write(option);
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.showSignal.setVisible(false); //cerramos la ventana
    }//GEN-LAST:event_GoBackButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        manager.disconnect();
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ShowSignal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowSignal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowSignal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowSignal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowSignal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GoBackButton;
    private javax.swing.JButton ShowPlotButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
