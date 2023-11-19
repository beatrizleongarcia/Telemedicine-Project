/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerClient;

import ifaces.Manager;
import java.net.Socket;

/**
 *
 * @author maria
 */
public class ServerThreads implements Runnable{
    
    public static Socket socket;
    private static Manager manager;
    
    public ServerThreads(Socket socket, Manager manager) { 
        this.socket = socket;
        this.manager = manager;

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
