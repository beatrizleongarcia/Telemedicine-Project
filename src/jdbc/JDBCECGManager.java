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
import java.sql.Connection;
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

    @Override
    public void addECG(ECG ecg, Connection c) {
        try {
            String sql = "INSERT INTO ECG (observation, ecg, patientId) VALUES (?, ?, ?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, ecg.getObservations());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(ecg.getEcg());
            byte[] bytes = bos.toByteArray();
            prep.setBytes(2, bytes);//Revisar que esté bien
            prep.setInt(3, ecg.getPatient_id());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ECG findECG(Connection c, int id) {
        ECG ecg = null;
        try {
            String sql = "SELECT * FROM ECG WHERE id = ?";
            PreparedStatement prep = c.prepareStatement(sql);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ecg;
    }

    @Override
    public ArrayList<ECG> findECGByPatient(Connection c, int patient_id) {
        ArrayList<ECG> ecgs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ECG WHERE patientId = ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ecgs;
    }

}
