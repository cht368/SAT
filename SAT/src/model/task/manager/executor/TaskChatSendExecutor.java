/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.task.manager.executor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.db.JDBCManager;
import model.packet.ChatType;
import model.packet.Packet;
import model.packet.PacketChatSend;
import model.packet.SourceType;

/**
 *
 * @author Ega Prianto
 */
public class TaskChatSendExecutor extends TaskExecutor {

    public TaskChatSendExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        try {
            PacketChatSend receivedPacket = (PacketChatSend) this.packet;
            JDBCManager jdbcManager = new JDBCManager();
            BufferedWriter bufferedWriter;
            PacketChatSend chatSend = new PacketChatSend(receivedPacket.command,
                    this.connectedSockets.size(),
                    SourceType.SERVER,
                    receivedPacket.chatType,
                    receivedPacket.idPengirim,
                    receivedPacket.idPenerima,
                    receivedPacket.chat);
            if (receivedPacket.chatType == ChatType.GROUP) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } else {
                //Masukkan data ke database
                int a;
                if (receivedPacket.chatType == ChatType.BROADCAST) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } else if (receivedPacket.chatType == ChatType.PRIVATE) {
                    if (receivedPacket.sourceType == SourceType.CLIENT) {
                        for (Socket connectedServerSocket : connectedServerSockets) {
                            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connectedServerSocket.getOutputStream()));

                            bufferedWriter.write(chatSend.toString());
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        }
                        //cari ipnya dl d database
                        String ipAddressPenerima = "";
                        if (connectedSockets.containsKey(ipAddressPenerima)) {
                            Socket clientSocket = connectedSockets.get(ipAddressPenerima);
                            bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                            bufferedWriter.write(chatSend.toString());
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        }
                    }
                } else {
                    // Tipe pesannya salah
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TaskChatSendExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
