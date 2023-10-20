/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maria
 */
public class JDBCManager {
    private Connection c;
    
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            //here we get the connection
            this.c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\maria\\OneDrive\\Documents\\NetBeansProjects\\Telemedicine-Project");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            //patient = new PatientManager(c);
            //ecg = new ECGManager(c);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public void disconnect() {
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void createTables() {
        try {
            Statement stmt = c.createStatement();
            
            String sq1 = "CREATE TABLE IF NOT EXISTS patient " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    + " name  TEXT   NOT NULL, " + " lastname  TEXT   NOT NULL, " + " email   TEXT NOT NULL, " 
                    + " username  TEXT   NOT NULL, " + " password  TEXT   NOT NULL, " + " gender TEXT CHECK (gender = 'M' OR gender = 'F'), " 
                    + " mac TEXT NOT NULL, ";
            stmt.executeUpdate(sq1);
         
            //sq1 = "CREATE TABLE IF NOT EXISTS ECG " 
            //stmt.executeUpdate(sq1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
