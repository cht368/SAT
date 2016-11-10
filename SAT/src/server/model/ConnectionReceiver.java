/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.db.JDBCMySQLManager;
import server.model.packet.Packet;
import server.model.packet.PacketFactory;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionReceiver implements Runnable {

    private Socket socket;
    private Thread thread;
    private BufferedReader inputReader;
    private boolean isFinish;
    private ConcurrentLinkedQueue<Packet> packetQueue;

    public ConnectionReceiver(Socket socket, ConcurrentLinkedQueue<Packet> packetQueue) throws IOException, ClassNotFoundException, SQLException {
        this.socket = socket;
        inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.thread = new Thread(this);
        this.packetQueue = packetQueue;
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                String input = inputReader.readLine();
                Packet packet = PacketFactory.createPacketFromString(input);
                System.out.println("Packet Builded :" + packet.toString());
                packetQueue.add(packet);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
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
