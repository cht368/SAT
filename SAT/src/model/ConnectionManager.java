/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionManager implements Runnable {

    public static final int PORT = 2406;

    public ServerSocket serverSocket;
    private Thread thread;
    private boolean isFinish;
    private CopyOnWriteArrayList<Socket> connectedSockets;
    private CopyOnWriteArrayList<ConnectionReceiver> connectionReceivers;

    public ConnectionManager() throws IOException {
        this.serverSocket = new ServerSocket(PORT, 0, InetAddress.getLocalHost());
        this.thread = new Thread(this);
        this.connectedSockets = new CopyOnWriteArrayList<Socket>();
        this.isFinish = false;
        this.connectionReceivers = new CopyOnWriteArrayList<ConnectionReceiver>();
    }
    
    public void createConnection(Socket socket) throws IOException{
        ConnectionReceiver newConnection = new ConnectionReceiver(socket);
        this.connectedSockets.add(socket);
        this.connectionReceivers.add(newConnection);
        newConnection.start();
    }

    
    @Override
    public void run() {
        while (!isFinish) {            
            try {
                Socket clientSocket = serverSocket.accept();
                createConnection(clientSocket);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void start(){
        this.thread.start();
        this.isFinish = false;
    }
    
    public void stop() throws InterruptedException{
        this.isFinish = true;
        this.thread.join();
    }

}
