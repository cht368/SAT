/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.task.manager.executor;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import model.packet.Packet;

/**
 *
 * @author Ega Prianto
 */
public abstract class TaskExecutor implements Runnable {

    protected ConcurrentHashMap<String, Socket> connectedSockets;
    protected CopyOnWriteArrayList< Socket> connectedServerSockets;
    protected Thread thread;
    protected Packet packet;
    protected boolean isFinish;

    public TaskExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList< Socket> connectedServerSockets, Packet packet) {
        this.connectedSockets = connectedSockets;
        this.connectedServerSockets = connectedServerSockets;
        this.packet = packet;
        this.thread = new Thread(this);
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
