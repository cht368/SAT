/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public static final int limitConnection = 13;
    private Thread thread;
    private boolean isFinish;
    private List<String> serverList;
    private CopyOnWriteArrayList<Socket> connectedSockets;
    private CopyOnWriteArrayList<Socket> connectedServerSockets;
    private CopyOnWriteArrayList<ConnectionReceiver> connectionReceivers;

    public ConnectionManager() throws IOException {
        this.serverSocket = new ServerSocket(PORT, 0, InetAddress.getLocalHost());
        this.thread = new Thread(this);
        this.connectedSockets = new CopyOnWriteArrayList<Socket>();
        this.isFinish = false;
        this.connectionReceivers = new CopyOnWriteArrayList<ConnectionReceiver>();
        this.serverList = new ArrayList<String>();
        this.serverList.add("127.0.0.1:2406");
    }

    public void createConnection(Socket socket) throws IOException {
        ConnectionReceiver newConnection = new ConnectionReceiver(socket);
        this.connectedSockets.add(socket);
        this.connectionReceivers.add(newConnection);
        newConnection.start();
    }

    @Override
    public void run() {
        try {
            for (String string : serverList) {
                String[] splitted = string.split(":");
                Socket serverSocket = new Socket(splitted[0], Integer.parseInt(splitted[1]));
                this.connectedServerSockets.add(serverSocket);
            }
            while (!isFinish) {
                try {
                    if (this.connectedSockets.size() + this.connectedServerSockets.size() <= limitConnection) {
                        Socket clientSocket = serverSocket.accept();
                        createConnection(clientSocket);
                    } else {
                        //TODO Masih belum beres
                        Socket clientSocket = serverSocket.accept();
                        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        bw.write("Sorry, the server is full please try another server ["+this.serverList.get(0));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        this.thread.start();
        this.isFinish = false;
    }

    public void stop() throws InterruptedException {
        this.isFinish = true;
        this.thread.join();
    }

}
