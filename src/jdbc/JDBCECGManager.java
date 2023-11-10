/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import Client.ECG;
import ifaces.ECGManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class JDBCECGManager implements ECGManager {

    private JDBCManager manager;

    public JDBCECGManager(JDBCManager manager) {
        this.manager = manager;
    }

    @Override
    public void addECG(ECG ecg) {
        try {
            String sql = "INSERT INTO ECG (observation, ecg, patientId) VALUES (?, ?, ?)";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setString(1, ecg.getObservations());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(ecg.getEcg());
            byte[] bytes = bos.toByteArray();
            prep.setBytes(2, bytes);//Revisar que esté bien
            prep.setInt(3, ecg.getPatient_id());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ECG findECG(int id) {
        ECG ecg = null;
        try {
            String sql = "SELECT * FROM ECG WHERE id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                String observations = rs.getString("observation");
                int patient_id = rs.getInt("patientId");
                byte[] bytes = rs.getBytes("ecg");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bis);
                ArrayList<Integer> ecgList = (ArrayList<Integer>) ois.readObject();
                ecg = new ECG(id, ecgList, observations, patient_id);
            }
            rs.close();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ecg;
    }

    @Override
    public ArrayList<ECG> findECGByPatient(int patient_id) {
        ArrayList<ECG> ecgs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ECG WHERE patientId = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, patient_id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                String observations = rs.getString("observation");
                int id = rs.getInt("id");
                byte[] bytes = rs.getBytes("ecg");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bis);
                ArrayList<Integer> ecgList = (ArrayList<Integer>) ois.readObject();
                ECG ecg = new ECG(id, ecgList, observations, patient_id);
                ecgs.add(ecg);
            }
            rs.close();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ecgs;
    }

    @Override
    public ArrayList<ECG> findECGByObservationFragment(String text) {
        ArrayList<ECG> ecgs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ECG WHERE observation = *?*";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setString(1, text);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                String observations = rs.getString("observation");
                int id = rs.getInt("id");
                int patient_id = rs.getInt("patientId");
                byte[] bytes = rs.getBytes("ecg");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bis);
                ArrayList<Integer> ecgList = (ArrayList<Integer>) ois.readObject();
                ECG ecg = new ECG(id, ecgList, observations, patient_id);
                ecgs.add(ecg);
            }
            rs.close();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ecgs;
    }

    @Override
    public void deleteECG(int id) {
        try {
            String sql = "DELETE FROM ECG WHERE id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setECG(ECG ecg, int id) {
        try {
            String sql = "UPDATE ECG SET observation = ?, ecg = ?, patientId = ? WHERE id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setString(1, ecg.getObservations());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(ecg.getEcg());
            byte[] bytes = bos.toByteArray();
            prep.setBytes(2, bytes);//Revisar que esté bien
            prep.setInt(3, ecg.getPatient_id());
            prep.setInt(4, id);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
