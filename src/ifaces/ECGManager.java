/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifaces;

import Client.ECG;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public interface ECGManager {

    public void addECG(ECG ecg);

    public ECG findECG(int id);

    public ArrayList<ECG> findECGByPatient(int patient_id);

    public ArrayList<ECG> findECGByObservationFragment(String text);

    public void deleteECG(int id);

    public void setECG(ECG ecg, int id);
}
