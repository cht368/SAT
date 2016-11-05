/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.ConnectionManager;

/**
 *
 * @author Ega Prianto
 */
public class ChatServerController {

    private ConnectionManager connectionManager;

    public ChatServerController() {
    }
    
    
    
    public void startServer(int port) throws IOException {
        connectionManager = new ConnectionManager(port);
    }
    
}
