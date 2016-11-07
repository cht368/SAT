/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.packet.Packet;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionSender implements Runnable {

    private BufferedWriter bw;
    private Thread thread;
    private boolean isFinish;
    private ConcurrentLinkedQueue<Packet> listPacket;

    public ConnectionSender(BufferedWriter bw) {
        this.bw = bw;
        this.thread = new Thread(this);
        this.isFinish = false;
        this.listPacket = new ConcurrentLinkedQueue<>();
    }

    public void addPacket(Packet packet) {
        listPacket.add(packet);
    }

    @Override
    public void run() {
        while (!isFinish) {
            if (!listPacket.isEmpty()) {
                try {
                    Packet toSend = listPacket.poll();
                    System.out.println("Sending Packet : " + toSend.toString());
                    bw.write(toSend.toString());
                    bw.flush();
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionSender.class.getName()).log(Level.SEVERE, null, ex);
                }

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
