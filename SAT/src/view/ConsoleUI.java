/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChatServerController;
import java.io.IOException;
import java.util.Scanner;
import model.packet.PacketType;

/**
 *
 * @author Ega Prianto
 */
public class ConsoleUI {

    public static void main(String[] args) throws IOException {
        System.out.println("Chat Server is started at:");
        Scanner sc = new Scanner(System.in);
        String port = sc.nextLine();
        ChatServerController chatServerController = new ChatServerController();
        chatServerController.startServer(Integer.parseInt(port));
        System.out.println("InputCommand : ");
        String command = sc.nextLine();
        while (true) {
            ConsoleCommandType commandType= ConsoleCommandType.valueOf(command);
            switch(commandType){
                case GET_CONNECTED_CLIENT:
                    chatServerController.getConnectedClient();
                case GET_CONNECTED_SERVER:
                    chatServerController.getConnectedServer();
                case GET_SERVER_LOAD:
                    chatServerController.getServerLoad();
            }
            System.out.println("InputCommand : ");
            command = sc.nextLine();
        }
    }
}
