/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import Client.Patient;
import ifaces.PatientManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class JDBCPatientManager implements PatientManager {
    private JDBCManager manager;
    
    public JDBCPatientManager(JDBCManager m){
        this.manager=m;
    }
    
    public void addPatient(Patient p) {
		try {
			String sql = "INSERT INTO patients (name, lastname, gender, email, username, password, MAC) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getLastname());
			prep.setString(3, p.getGender());
			prep.setString(4, p.getEmail());
			prep.setString(5, p.getUsername());
                        //prep.setByte(6, p.getPassword());
                        prep.setString(7, p.getMAC());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Patient searchPatient(String username, String password) {
		Patient p = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients WHERE username=" + username + "AND password=" + password;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            String lastname = rs.getString("lastname");
                            String gender = rs.getString("gender");
                            String email = rs.getString("email");
                            String MAC = rs.getString("MAC");
                            p = new Patient(id, name, lastname, gender, email, username, password, MAC);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
    
    public boolean verifyUsername (String username) {
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
    
    public boolean verifyPassword(String username, String passwordIntroduced) {
        boolean contraseñaCorrecta = false; // Se inicializa como false

        // Consulta SQL para verificar la contraseña
        String sql = "SELECT contraseña FROM patient WHERE username = ?";

        try {
            // Preparar la consulta
            try (PreparedStatement statement = manager.getConnection().prepareStatement(sql)) {
                statement.setString(1, username);

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String passwordSaved = resultSet.getString("contraseña");

                        // Verificar si la contraseña introducida coincide con la almacenada
                        contraseñaCorrecta = passwordSaved.equals(passwordIntroduced);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la contraseña: " + e.getMessage());
        }

        return contraseñaCorrecta; // Devuelve true si la contraseña es correcta, false en caso contrario
    }
    
    public List<Patient> listAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();
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

        } catch (Exception e) {
                e.printStackTrace();
        }
        return patients;
    }
}
