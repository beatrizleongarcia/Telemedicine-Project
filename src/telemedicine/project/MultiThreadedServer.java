/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedicine.project;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author beatr
 */

public class MultiThreadedServer {
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(9000);
        System.out.println("Server listening on port 9000...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from: " + clientSocket.getInetAddress());

            // Create a new thread for each client
            Thread clientThread = new Thread(() -> handleClient(clientSocket));
            clientThread.start();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            int byteRead;
            while (true) {
                byteRead = inputStream.read();
                if (byteRead == -1 || byteRead == 'x') {
                    System.out.println("Character reception finished for " + clientSocket.getInetAddress());
                    clientSocket.close();
                    break;
                }
                char character = (char) byteRead;
                System.out.print(character);
                System.out.print(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
