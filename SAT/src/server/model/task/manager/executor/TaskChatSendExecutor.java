/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.task.manager.executor;

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
import server.model.db.JDBCMySQLManager;
import server.model.packet.ChatType;
import server.model.packet.Packet;
import server.model.packet.PacketChatSend;
import server.model.packet.SourceType;

/**
 *
 * @author Ega Prianto
 */
public class TaskChatSendExecutor extends TaskExecutor {

    private JDBCMySQLManager dbManager;

    public TaskChatSendExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        try {
            dbManager = new JDBCMySQLManager();
            PacketChatSend receivedPacket = (PacketChatSend) this.packet;
            BufferedWriter bufferedWriter;
            PacketChatSend chatSend = new PacketChatSend(receivedPacket.command,
                    this.connectedSockets.size(),
                    SourceType.SERVER,
                    receivedPacket.chatType,
                    receivedPacket.idPengirim,
                    receivedPacket.idPenerima,
                    receivedPacket.chat,
                    receivedPacket.timestamp
            );
            if (receivedPacket.chatType == ChatType.GROUP) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } else {
                dbManager.insertChat(receivedPacket.idPengirim, receivedPacket.idPenerima, receivedPacket.chat, receivedPacket.timestamp);
                int a;
                if (receivedPacket.chatType == ChatType.BROADCAST) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } else if (receivedPacket.chatType == ChatType.PRIVATE) {
                    if (receivedPacket.sourceType == SourceType.CLIENT) {
                        /*
                        //kirim ke semua server
                        for (Socket connectedServerSocket : connectedServerSockets) {
                            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connectedServerSocket.getOutputStream()));

                            bufferedWriter.write(chatSend.toString());
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        }*/
                        //cari ipnya dl d database
                        String ipAddressPenerima = dbManager.getIpAddressPortUser(receivedPacket.idPenerima);
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
            dbManager.close();
        } catch (Exception ex) {
            Logger.getLogger(TaskChatSendExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
