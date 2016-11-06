/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.task.manager.executor;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import server.model.packet.Packet;
import server.model.packet.PacketRegister;
import server.model.packet.SourceType;

/**
 *
 * @author Ega Prianto
 */
public class TaskRegisterExecutor extends TaskExecutor {

    public TaskRegisterExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        PacketRegister receivedPacket = (PacketRegister) packet;
        if (receivedPacket.sourceType==SourceType.CLIENT) {
            
        }
    }
    
}
