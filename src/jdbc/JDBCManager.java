/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import ifaces.ECGManager;
import ifaces.Manager;
import ifaces.PatientManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maria
 */
public class JDBCManager implements Manager {

    private Connection c = null;
    private PatientManager patient;
    private ECGManager ecg;

    @Override
    public Connection getConnection() {
        return c;
    }
    @Override
    public PatientManager getPatient() {
        return patient;
    }

    @Override
    public ECGManager getECG() {
        return ecg;
    }

    @Override
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            //here we get the connection
            this.c = DriverManager.getConnection("jdbc:sqlite:jdbc:sqlite:./db/TelemedicineProject.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            patient = new JDBCPatientManager(c);
            ecg = new JDBCECGManager(c);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTables() {
        try {
            Statement stmt = c.createStatement();

            String sq1 = "CREATE TABLE IF NOT EXISTS Patient " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    + " name  TEXT   NOT NULL, " + " lastname  TEXT   NOT NULL, " + " email   TEXT NOT NULL, "
                    + " username  TEXT   NOT NULL, " + " password  BLOB   NOT NULL, " + " gender TEXT CHECK (gender = 'M' OR gender = 'F')), "
                    + "CREATE TABLE IF NOT EXISTS ECG " + "(id     INTEGER  PRIMARY KEY AUTOINCREMENT, "
                    + " observation TEXT NOT NULL, " + " ecg BLOB NOT NULL, date TEXT NOT NULL"
                    + "patientId INTEGER REFERENCES Patient(id) ON UPDATE CASCADE ON DELETE CASCADE)";
            stmt.executeUpdate(sq1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
