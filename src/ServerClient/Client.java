/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author beatr
 */

public class Client {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        new Client().startClient();
    }

    public void startClient() {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server.");

            // Handle incoming messages in a separate thread, so many clients can connect at the same time
            new Thread(() -> {
                try {
                    while (true) {
                        String message = (String) inputStream.readObject();
                        System.out.println("Received from server: " + message);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();

            // Send messages to the server
            while (true) {
                String userMessage = scanner.nextLine();
                outputStream.writeObject(userMessage);
                outputStream.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
