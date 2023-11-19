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
import ServerClient.Client;

/**
 *
 * @author beatr
 */

public class ClientHandler{

    /*private Socket clientSocket;
    private Server server;
    private ObjectOutputStream outputStream;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try (ObjectOutputStream clientOutput = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream clientInput = new ObjectInputStream(clientSocket.getInputStream())) {

            this.outputStream = clientOutput;

            while (true) {
                String message = (String) clientInput.readObject();
                System.out.println("Received from client " + clientSocket.getInetAddress() + ": " + message);

                // Broadcast the message to all clients
                server.broadcastMessage(message, this);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client  "  + clientSocket.getInetAddress() + "  disconnected.");
            server.removeClient(this);
        }
    }
    
    // Send a message to this client
    public void sendMessage(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
