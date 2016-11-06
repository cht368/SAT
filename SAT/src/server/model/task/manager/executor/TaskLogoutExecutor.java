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
import model.packet.PacketLogout;

/**
 *
 * @author Ega Prianto
 */
public class TaskLogoutExecutor extends TaskExecutor{

    public TaskLogoutExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        PacketLogout packetLogout = (PacketLogout) this.packet;
        //sql 'update table database set status=offline where id=packetLogout.id'
    }
    
}
