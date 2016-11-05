/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionReceiver implements Runnable {
    private Socket socket;
    private Thread thread;
    private BufferedReader inputReader;
    private boolean isFinish;

    public ConnectionReceiver(Socket socket) throws IOException {
        this.socket = socket;
        inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                String receiveData = inputReader.readLine();
                // menerima input dari client dan membuat Task pada Queue.
            } catch (IOException ex) {
                Logger.getLogger(ConnectionReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void start(){
        this.thread.start();
        this.isFinish = false;
    }
    
    public void stop() throws InterruptedException{
        this.isFinish = true;
        this.thread.join();
    }
    
}
