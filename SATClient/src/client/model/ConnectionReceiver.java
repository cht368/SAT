/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import client.model.clientData.Chat;
import client.model.clientData.PrivateChat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.ParseException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.db.JDBCMySQLManager;
import server.model.packet.ChatType;
import server.model.packet.Packet;
import server.model.packet.PacketChatSend;
import server.model.packet.PacketFactory;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionReceiver implements Runnable {

    public int port;
    public String ip;
    public Socket socket;
    private Thread thread;
    private boolean isFinish;
    public static String MOTD;
    private BufferedReader br;
    private BufferedWriter bw;
    public ConcurrentHashMap<String, Chat> chatRoomsData;

    public ConnectionReceiver(String ip, int port) throws IOException {
        this.thread = new Thread(this);
        this.port = port;
        this.ip = ip;
        socket = new Socket(ip, port);
        System.out.println(socket.getLocalSocketAddress().toString());
        this.chatRoomsData = new ConcurrentHashMap<>();
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            bw.write("0");
            bw.newLine();
            bw.flush();
            MOTD = br.readLine() + " ";//"WELCOME TO SERVER

            System.out.println(MOTD);
            MOTD += br.readLine();//CONNECTED
            System.out.println(MOTD);
            while (!isFinish) {
                String receivedMsg = br.readLine();
                Packet receivedPacket = PacketFactory.createPacketFromString(receivedMsg);
                switch (receivedPacket.command) {
                    case CHAT_SEND:
                        PacketChatSend chatReceived = (PacketChatSend) receivedPacket;
                        if (chatReceived.chatType == ChatType.PRIVATE) {
                            PrivateChat chatRoomData = (PrivateChat) this.chatRoomsData.get(chatReceived.idPengirim);
                            chatRoomData.addOpponentChat(chatReceived.chat, JDBCMySQLManager.DATE_FORMAT.parse(chatReceived.timestamp).getTime());
                        }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ConnectionReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        this.thread.start();
        this.isFinish = false;
    }

    public void stop() throws InterruptedException {
        this.isFinish = true;
        this.thread.join();
    }

}
