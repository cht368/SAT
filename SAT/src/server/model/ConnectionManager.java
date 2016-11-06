/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

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
import server.model.packet.Packet;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionManager implements Runnable {

    public static final int PORT = 2406;

    public ServerSocket serverSocket;
    public static final int LIMIT_CONNECTION = 10;
    private Thread thread;
    private boolean isFinish;
    private List<String> serverList;
    private ConcurrentHashMap<String, Socket> connectedSockets;
    private CopyOnWriteArrayList<Socket> connectedServerSockets;
    private CopyOnWriteArrayList<ConnectionReceiver> connectionReceivers;
    private ConcurrentLinkedQueue<Packet> packetQueue;

    public ConnectionManager(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, ConcurrentLinkedQueue<Packet> packetQueue) throws IOException {
        init();
        this.packetQueue = packetQueue;
        this.connectedServerSockets = connectedServerSockets;
        this.connectedSockets = connectedSockets;
        this.serverSocket = new ServerSocket(PORT);
    }

    public ConnectionManager(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList< Socket> connectedServerSockets, int customPort, ConcurrentLinkedQueue<Packet> packetQueue) throws IOException {
        init();
        this.packetQueue = packetQueue;
        this.connectedServerSockets = connectedServerSockets;
        this.connectedSockets = connectedSockets;
        this.serverSocket = new ServerSocket(customPort);
    }

    private void init() {
        this.thread = new Thread(this);
        this.isFinish = false;
        this.connectionReceivers = new CopyOnWriteArrayList<>();
        this.serverList = new ArrayList<>();
    }

    public void createConnection(Socket socket) throws IOException {
        ConnectionReceiver newConnection = new ConnectionReceiver(socket, this.packetQueue);
        this.connectedSockets.put(socket.getRemoteSocketAddress().toString().substring(1), socket);
        this.connectionReceivers.add(newConnection);
        newConnection.start();
    }

    @Override
    public void run() {
        System.out.println("Listening started on port: " + serverSocket.getLocalPort());
        try {/*broadcast ke semua server
            for (String string : serverList) {
                String[] splitted = string.split(":");
                Socket serverSocket = new Socket(splitted[0], Integer.parseInt(splitted[1]));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
                bufferedWriter.write("1\n");
                this.connectedServerSockets.add(serverSocket);
            }*/
            while (!isFinish) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection host: " + clientSocket.getRemoteSocketAddress().toString().substring(1));
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String type = br.readLine();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                if (type.equals("0")) {
                    //client
                    if (this.connectedSockets.size() <= LIMIT_CONNECTION) {
                        bw.write("Welcome to this server \n");
                        bw.write("Connection Started\n");
                        createConnection(clientSocket);
                    } else {
                        //TODO Masih belum beres
                        bw.write("Sorry, the server is full please try another server \n");
                        bw.write(this.serverList.get(0) + "\n");
                    }
                } else if (type.equals("1")) {
                    //server
                    this.connectedServerSockets.add(clientSocket);//adding server socket connection
                } else {
                    clientSocket.close();
                    //command wrong close connection
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
