/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifaces;

import java.sql.Connection;

/**
 *
 * @author maria
 */
public interface Manager {

    public void connect();

    public void disconnect();

    public void createTables();
    
    public PatientManager getPatient();
    
    public ECGManager getECG();
    
    public Connection getConnection();

}
