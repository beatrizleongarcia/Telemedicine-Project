/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author maria
 */
public class Patient implements Serializable {

    private String name;
    private String lastname;
    private Integer id;
    private String gender;
    private String email;
    private String username;
    private byte[] password;

    public Patient() {
        super();
    }

    public Patient(Integer id, String name, String lastname, String gender, String email, String username, String password) {
        try {
            this.name = name;
            this.lastname = lastname;
            this.id = id;
            this.gender = gender;
            this.email = email;
            this.username = username;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            this.password = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Patient(String name, String lastname, String gender, String email, String username, String password) {
        try {
            this.name = name;
            this.lastname = lastname;
            this.gender = gender;
            this.email = email;
            this.username = username;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            this.password = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    public Patient(int id, String name, String lastname, String gender, String email) {
        this.id= id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
    }

    public Patient(String username, String password) {
        try {
            this.username = username;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            this.password = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String address) {
        this.email = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        //To do
        return "Patient{" + "name=" + name + ", lastname=" + lastname + ", id=" + id + ", gender=" + gender + ", email=" + email + ", username=" + username + ", password=" + password + '}';
    }
}
