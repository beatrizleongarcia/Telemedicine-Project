/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifaces;

import Client.ECG;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public interface ECGManager {

    public void addECG(ECG ecg, Connection c);

    public ECG findECG(Connection c, int id);

    public ArrayList<ECG> findECGByPatient(Connection c, int patient_id);
}
