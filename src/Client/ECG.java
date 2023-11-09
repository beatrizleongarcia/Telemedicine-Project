/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class ECG {

    private Integer id;
    private ArrayList<Integer> ecg = new ArrayList<>();
    private String observations;
    private int patient_id;

    public ECG(ArrayList<Integer> ecg, String observations, int patient_id) {
        this.ecg = ecg;
        this.observations = observations;
        this.patient_id = patient_id;
    }

    public ECG(Integer id, ArrayList<Integer> ecg, String observations, int patient_id) {
        this.id = id;
        this.ecg = ecg;
        this.observations = observations;
        this.patient_id = patient_id;
    }

    public ECG(Integer id, String observations, int patient_id) {
        this.id = id;
        this.observations = observations;
        this.patient_id = patient_id;
    }

    public ArrayList<Integer> getEcg() {
        return ecg;
    }

    public String getObservations() {
        return observations;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setEcg(ArrayList<Integer> ecg) {
        this.ecg = ecg;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EEGSample{" + "id=" + id + ", ecg=" + ecg + ", observations=" + observations + ", patient_id=" + patient_id + '}';
    }
}
