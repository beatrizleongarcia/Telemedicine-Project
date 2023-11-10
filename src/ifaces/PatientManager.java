/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifaces;

import Client.Patient;
import java.util.List;

/**
 *
 * @author maria
 */
public interface PatientManager {

    public void addPatient(Patient p);

    public Patient searchPatient(String username, String password);

    public boolean verifyUsername(String username);

    public boolean verifyPassword(String username, String passwordIntroduced);

    public List<Patient> listAllPatients();

    public void changePassword(String username, String oldPassword, String newPassword);
}
