/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChatServerController;
import java.io.IOException;
import java.util.Scanner;

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
        chatServerController.startServer(port);
    }
}
