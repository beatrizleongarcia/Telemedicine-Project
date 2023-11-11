/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author beatr
 */

public class PatientClient {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            // Prompt the user to enter data to create a patient
            System.out.println("Enter patient data:");
            
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            System.out.print("MAC: ");
            String mac = scanner.nextLine();

            // Create an instance of the Patient class with the user-provided data
            Patient patient = new Patient(name, lastName, gender, email, username, password, mac);

            // Send the Patient instance to the server
            outputStream.writeObject(patient);
            outputStream.flush();

            System.out.println("Patient data sent to the server.");

            // Close the connection
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
