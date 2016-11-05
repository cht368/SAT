/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.task.manager.executor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.packet.Packet;
import model.packet.PacketCreateGroup;

/**
 *
 * @author Ega Prianto
 */
public class TaskCreateGroupExecutor extends TaskExecutor{

    public TaskCreateGroupExecutor(ConcurrentHashMap<String, Socket> connectedSockets, ConcurrentHashMap<String, Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        BufferedWriter bufferedWriter = null;
        try {
            PacketCreateGroup packetCreateGroup = (PacketCreateGroup) this.packet;
            // TODO
            Socket socket = connectedSockets.get("1321");
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           
            
        } catch (IOException ex) {
            Logger.getLogger(TaskCreateGroupExecutor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(TaskCreateGroupExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
