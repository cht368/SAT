/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.task.manager;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import server.model.packet.Packet;
import server.model.packet.PacketChatSend;
import server.model.task.manager.executor.TaskChatSendExecutor;
import server.model.task.manager.executor.TaskExecutor;

/**
 *
 * @author Ega Prianto
 */
public class TaskManager implements Runnable {

    private boolean isFinish;
    private Thread thread;
    private ConcurrentHashMap<String, Socket> connectedSockets;
    private CopyOnWriteArrayList< Socket> connectedServerSockets;
    private ConcurrentLinkedQueue<Packet> packetQueue;

    public TaskManager(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList< Socket> connectedServerSockets, ConcurrentLinkedQueue<Packet> packetQueue) {
        this.thread = new Thread(this);
        this.connectedSockets = connectedSockets;
        this.connectedServerSockets = connectedServerSockets;
        this.packetQueue = packetQueue;
    }

    @Override
    public void run() {
        while (!isFinish) {
            if (!packetQueue.isEmpty()) {
                Packet newPacket = packetQueue.poll();
                switch (newPacket.command) {
                    case CHAT_SEND:
                        new TaskChatSendExecutor(connectedSockets, connectedServerSockets, newPacket).start();
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
