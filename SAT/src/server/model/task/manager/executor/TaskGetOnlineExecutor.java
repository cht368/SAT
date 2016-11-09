/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.task.manager.executor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.controller.ChatServerController;
import server.model.db.JDBCMySQLManager;
import server.model.packet.Packet;
import server.model.packet.PacketGetOnlineClient;
import server.model.packet.PacketGetOnlineServer;
import server.model.packet.PacketType;
import server.model.packet.SourceType;

/**
 *
 * @author Ega Prianto
 */
public class TaskGetOnlineExecutor extends TaskExecutor {

    public TaskGetOnlineExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        try {
            PacketGetOnlineClient receivedPacket = (PacketGetOnlineClient) packet;
            ArrayList<String> ids = ChatServerController.dbManager.getOnlineExcept(receivedPacket.id);
            PacketGetOnlineServer toSend = new PacketGetOnlineServer(PacketType.GET_ONLINE_SERVER, connectedSockets.size(), SourceType.SERVER, ids);
            Socket socket = connectedSockets.get(receivedPacket.ipAddressPort);
            System.out.print("Sending packet Online : " + toSend.toString());
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(toSend.toString());
            bufferedWriter.flush();
            this.thread.join();
        } catch (SQLException ex) {
            Logger.getLogger(TaskGetOnlineExecutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TaskGetOnlineExecutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(TaskGetOnlineExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
