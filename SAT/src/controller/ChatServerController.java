/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import model.ConnectionManager;
import model.packet.Packet;
import model.task.manager.TaskManager;

/**
 *
 * @author Ega Prianto
 */
public class ChatServerController {

    private ConnectionManager connectionManager;
    private TaskManager taskManager;
    private ConcurrentLinkedQueue<Packet> packetQueue;

    public ChatServerController() {
        packetQueue = new ConcurrentLinkedQueue<>();
    }
    
    
    
    public void startServer(int port) throws IOException {
        connectionManager = new ConnectionManager(port,packetQueue);
        taskManager = new TaskManager(packetQueue);
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
