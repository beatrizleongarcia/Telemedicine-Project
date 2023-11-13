/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import Client.Patient;
import ifaces.PatientManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class JDBCPatientManager implements PatientManager {

    private  JDBCManager manager;
    private  Connection c;

    public JDBCPatientManager(JDBCManager m) {
        this.manager = m;
    }
    public JDBCPatientManager(Connection c) {
            this.c = c;
        }

    @Override
    public void addPatient(Patient p) {
        try {
            String sql = "INSERT INTO patients (name, lastname, gender, email, username, password, MAC) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setString(1, p.getName());
            prep.setString(2, p.getLastname());
            prep.setString(3, p.getGender());
            prep.setString(4, p.getEmail());
            prep.setString(5, p.getUsername());
            prep.setBytes(6, p.getPassword());
            prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Patient searchPatient(String username, String password) {
        Patient p = null;
        try {
            String sql = "SELECT * FROM patients WHERE username = ? AND password = ?";
            PreparedStatement stmt = manager.getConnection().prepareStatement(sql);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            stmt.setString(1, username);
            stmt.setBytes(2, hash);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String MAC = rs.getString("MAC");
                p = new Patient(id, name, lastname, gender, email, username, password);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JDBCPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public boolean verifyUsername(String username) {
        boolean usernameExists = false; // En principio no existe

        // Consulta SQL para verificar la existencia del usuario
        String sql = "SELECT username FROM patient WHERE username = ?";

        try {
            // Preparar la consulta
            try (PreparedStatement statement = manager.getConnection().prepareStatement(sql)) {
                statement.setString(1, username);

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    usernameExists = resultSet.next(); // Si encuentra el username, usernameExists = true
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario " + e.getMessage());
        }

        return usernameExists; // Devuelve true o false
    }

    @Override
    public boolean verifyPassword(String username, String passwordIntroduced) {
        boolean contrasenaCorrecta = false; // Se inicializa como false

        // Consulta SQL para verificar la contraseña
        String sql = "SELECT contraseña FROM patient WHERE username = ?";

        try {
            // Preparar la consulta
            try (PreparedStatement statement = manager.getConnection().prepareStatement(sql)) {
                statement.setString(1, username);

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        byte[] passwordSaved = resultSet.getBytes("contraseña");

                        // Verificar si la contraseña introducida coincide con la almacenada
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        md.update(passwordIntroduced.getBytes());
                        byte[] hash = md.digest();
                        contrasenaCorrecta = Arrays.equals(passwordSaved, hash);
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(JDBCPatientManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la contraseña: " + e.getMessage());
        }

        return contrasenaCorrecta; // Devuelve true si la contraseña es correcta, false en caso contrario
    }

    @Override
    public List<Patient> listAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            Statement stmt = manager.getConnection().createStatement();
            String sql = "SELECT * FROM patients";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                Patient p = new Patient(id, name, lastname, gender, email);
                patients.add(p);
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patients;
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        if (!verifyPassword(username, oldPassword)) {
            System.out.println("ERROR: Username and/or current password are not correct");
        } else {
            try {
                String sql = "UPDATE Patient SET password = ? WHEN username = ?";
                PreparedStatement prep = manager.getConnection().prepareStatement(sql);
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(newPassword.getBytes());
                byte[] hash = md.digest();
                prep.setBytes(1, hash);
                prep.setString(2, username);
                prep.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCPatientManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(JDBCPatientManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
