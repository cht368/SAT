/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.packet.Packet;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionManager implements Runnable {

    public static final int PORT = 2406;

    public ServerSocket serverSocket;
    public static final int limitConnection = 10;
    private Thread thread;
    private boolean isFinish;
    private List<String> serverList;
    private ConcurrentHashMap<String,Socket> connectedSockets;
    private ConcurrentHashMap<String,Socket> connectedServerSockets;
    private CopyOnWriteArrayList<ConnectionReceiver> connectionReceivers;
    private ConcurrentLinkedQueue<Packet> packetQueue;

    public ConnectionManager(ConcurrentLinkedQueue<Packet> packetQueue) throws IOException {
        init(packetQueue);
        this.serverSocket = new ServerSocket(PORT, 0, InetAddress.getLocalHost());
    }

    public ConnectionManager(int customPort,ConcurrentLinkedQueue<Packet> packetQueue) throws IOException {
        init(packetQueue);
        this.serverSocket = new ServerSocket(customPort, 0, InetAddress.getLocalHost());
    }

    private void init(ConcurrentLinkedQueue<Packet> packetQueue) {
        this.thread = new Thread(this);
        this.connectedSockets = new ConcurrentHashMap<String,Socket>();
        this.connectedServerSockets = new ConcurrentHashMap<String, Socket>();
        this.isFinish = false;
        this.connectionReceivers = new CopyOnWriteArrayList<ConnectionReceiver>();
        this.serverList = new ArrayList<String>();
        this.serverList.add("127.0.0.1:2406");
        this.packetQueue = packetQueue;
    }

    public void createConnection(Socket socket) throws IOException {
        ConnectionReceiver newConnection = new ConnectionReceiver(socket,this.packetQueue);
        this.connectedSockets.put(socket.getInetAddress().toString().substring(1),socket);
        this.connectionReceivers.add(newConnection);
        newConnection.start();
    }

    @Override
    public void run() {
        try {
            for (String string : serverList) {
                String[] splitted = string.split(":");
                Socket serverSocket = new Socket(splitted[0], Integer.parseInt(splitted[1]));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
                bufferedWriter.write("1\n");
                this.connectedServerSockets.put(serverSocket.getRemoteSocketAddress().toString().substring(1),serverSocket);
            }
            while (!isFinish) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String type = br.readLine();
                    for (int i = 0; i < 10; i++) {
                        
                    }
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    if (this.connectedSockets.size()<= limitConnection) {
                        bw.write("Welcome to this server \n");
                        bw.write("Connection Started\n");
                        createConnection(clientSocket);
                    } else {
                        //TODO Masih belum beres
                        bw.write("Sorry, the server is full please try another server \n");
                        bw.write(this.serverList.get(0)+"\n");
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
