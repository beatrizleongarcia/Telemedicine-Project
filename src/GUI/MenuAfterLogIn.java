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
public class MenuAfterLogIn extends javax.swing.JFrame implements WindowListener {

    private SocketObject socket;
    private LogIn login;
    public MenuGUI menu;
    public MenuAfterLogIn menuAfterLogIn;
    private JDBCManager jdbcmanager;
    private JDBCPatientManager jdbcpatientmanager;
    private RecordECG record;
    private Patient patient;
    private SignalsRegistered signalsregistered;

    public MenuAfterLogIn(SocketObject socket, JDBCManager jdbcmanager, JDBCPatientManager jdbcpatientmanager) {
        this.socket = socket;
        this.jdbcmanager = jdbcmanager;
        this.jdbcpatientmanager = jdbcpatientmanager;
    }

    public void setMenuAfterLogIn(MenuAfterLogIn menuAfterLogIn) {
        this.menuAfterLogIn = menuAfterLogIn;
    }

    /**
     * Creates new form MenuAfterLogIn
     */
    public MenuAfterLogIn() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ViewMyData = new javax.swing.JButton();
        RecordECG = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        ViewMyData.setText("View my data");
        ViewMyData.setToolTipText("");
        ViewMyData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewMyDataActionPerformed(evt);
            }
        });

        RecordECG.setText("Record ECG");
        RecordECG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecordECGActionPerformed(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RecordECG, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(ViewMyData, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addContainerGap(158, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ExitButton)
                .addGap(72, 72, 72)
                .addComponent(ViewMyData, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(RecordECG)
                .addContainerGap(118, Short.MAX_VALUE))
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

    private void ViewMyDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewMyDataActionPerformed
        JDBCECGManager ecgManager = new JDBCECGManager(jdbcmanager);
        ArrayList<ECG> ecgs = ecgManager.findECGByPatient(patient.getId()); //creamos arraylist
        signalsregistered = new SignalsRegistered(jdbcmanager, jdbcpatientmanager, ecgManager, socket, ecgs, patient);
        signalsregistered.setSignalsRegistered(signalsregistered);
        signalsregistered.setVisible(true);
        int option = 1; //opcion 2 en DESIGN es
        try {
            socket.getOutputStream().write(option);
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.menuAfterLogIn.setVisible(false); //cerramos la ventana
    }//GEN-LAST:event_ViewMyDataActionPerformed

    private void RecordECGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecordECGActionPerformed
        record = new RecordECG(socket, patient);
        record.setRecord(record);
        record.setVisible(true); //abrimos ventana
        int option = 2; //opción 2 que es grabar la señal en DESIGN
        try {
            socket.getOutputStream().write(option);
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:
        this.menuAfterLogIn.setVisible(false); //cerramos ventana
    }//GEN-LAST:event_RecordECGActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        this.jdbcmanager.disconnect();
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton RecordECG;
    private javax.swing.JButton ViewMyData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
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
