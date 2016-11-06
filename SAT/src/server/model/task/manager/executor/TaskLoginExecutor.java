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
import model.packet.PacketLoginClient;

/**
 *
 * @author Ega Prianto
 */
public class TaskLoginExecutor extends TaskExecutor{

    public TaskLoginExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        PacketLoginClient packetLogin = (PacketLoginClient) this.packet;
        
//        if (packetLogin.id exist in database) {
//            if (packetLogin.password == 'select password from database where id = packetLogin.id') {
//                //berhasil login
//                // sql update table set status=online where id=packetLogin.id
//            }else{
//                //kirim pesan ke client, password salah  
//            }
//        }
//        else{
//               //kirim pesan ke client, id salah
//        }
    }
    
}
