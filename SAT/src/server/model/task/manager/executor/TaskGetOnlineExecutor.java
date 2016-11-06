/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.task.manager.executor;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import model.packet.Packet;

/**
 *
 * @author Ega Prianto
 */
public class TaskGetOnlineExecutor extends TaskExecutor{

    public TaskGetOnlineExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        //TODO ambil data user yang sedang online dari database
        // TODO kirim daftar user yang sedang online ke client
    }
    
}
