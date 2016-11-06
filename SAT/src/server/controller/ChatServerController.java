/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import server.model.ConnectionManager;
import server.model.packet.Packet;
import server.model.task.manager.TaskManager;

/**
 *
 * @author Ega Prianto
 */
public class ChatServerController {

    private ConnectionManager connectionManager;
    private TaskManager taskManager;
    private ConcurrentLinkedQueue<Packet> packetQueue;
    private ConcurrentHashMap<String, Socket> connectedSockets;
    private CopyOnWriteArrayList< Socket> connectedServerSockets;

    public ChatServerController() {
        packetQueue = new ConcurrentLinkedQueue<>();
        connectedSockets = new ConcurrentHashMap<>();
        connectedServerSockets = new CopyOnWriteArrayList<>();
    }

    public void startServer(int port) throws IOException {
        connectionManager = new ConnectionManager(connectedSockets, connectedServerSockets, port, packetQueue);
        taskManager = new TaskManager(connectedSockets, connectedServerSockets, packetQueue);
        taskManager.start();
        connectionManager.start();
    }

    public List<String> getConnectedClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getConnectedServer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getServerLoad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
