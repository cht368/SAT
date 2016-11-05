/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Ega Prianto
 */
public class ConnectionReceiver implements Runnable {

    public int port;
    public String ip;
    private Socket socket;
    private Thread thread;
    private boolean isFinish;

    public ConnectionReceiver(int port, String ip) throws IOException {
        this.port = port;
        this.ip = ip;
        socket = new Socket(ip, port);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
