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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.db.JDBCMySQLManager;
import server.model.packet.LoginResponseType;
import server.model.packet.Packet;
import server.model.packet.PacketLoginClient;
import server.model.packet.PacketLoginResponse;
import server.model.packet.PacketType;
import server.model.packet.SourceType;

/**
 *
 * @author Ega Prianto
 */
public class TaskLoginExecutor extends TaskExecutor {

    public TaskLoginExecutor(ConcurrentHashMap<String, Socket> connectedSockets, CopyOnWriteArrayList<Socket> connectedServerSockets, Packet packet) {
        super(connectedSockets, connectedServerSockets, packet);
    }

    @Override
    public void run() {
        try {
            JDBCMySQLManager dbManager = new JDBCMySQLManager();
            PacketLoginClient packetLogin = (PacketLoginClient) this.packet;
            String userPassword = dbManager.getPasswordUser(packetLogin.id);
            Socket clientSocket = connectedSockets.get(packetLogin.ipAddress);
            BufferedWriter bufferedWriter;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            PacketLoginResponse loginResponse;
            if ((userPassword != null || !userPassword.equals("")) && packetLogin.password.equals(userPassword)) {
                String username = dbManager.getUsernameUser(packetLogin.id);
                dbManager.updateIpAddressUser(packetLogin.id, packetLogin.ipAddress);
                dbManager.updateStatusUser(packetLogin.id,"online");
                loginResponse = new PacketLoginResponse(PacketType.LOGIN_RESPONSE, connectedSockets.size(), SourceType.SERVER, LoginResponseType.PROCEED, username);
                bufferedWriter.write(loginResponse.toString());
                bufferedWriter.flush();
                //berhasil login
                // sql update table set status=online where id=packetLogin.id
            } else {
                loginResponse = new PacketLoginResponse(PacketType.LOGIN_RESPONSE, connectedSockets.size(), SourceType.SERVER, LoginResponseType.FORBIDDEN, null);
                bufferedWriter.write(loginResponse.toString());
                bufferedWriter.flush();

            }
//            stop();
        } catch (SQLException ex) {
            Logger.getLogger(TaskLoginExecutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskLoginExecutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TaskLoginExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
