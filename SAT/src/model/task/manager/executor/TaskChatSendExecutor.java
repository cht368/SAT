/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.task.manager.executor;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import model.packet.ChatType;
import model.packet.Packet;
import model.packet.PacketChatSend;

/**
 *
 * @author Ega Prianto
 */
public class TaskChatSendExecutor extends TaskExecutor{

    public TaskChatSendExecutor(ConcurrentHashMap<String, Socket> connectedSockets, ConcurrentHashMap<String, Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        PacketChatSend receivedPacket = (PacketChatSend) this.packet;
        if (receivedPacket.chatType == ChatType.GROUP) {
            
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }else{
            if (receivedPacket.chatType == ChatType.BROADCAST) {
                
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }else if(receivedPacket.chatType == ChatType.PRIVATE){
            }else{
                
            }
        }
    }

    
}
