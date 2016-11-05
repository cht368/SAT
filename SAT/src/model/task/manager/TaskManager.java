/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.task.manager;

import java.util.concurrent.ConcurrentLinkedQueue;
import model.packet.Packet;
import model.packet.PacketChatSend;
import model.task.manager.executor.TaskChatSendExecutor;
import model.task.manager.executor.TaskExecutor;

/**
 *
 * @author Ega Prianto
 */
public class TaskManager implements Runnable {

    private boolean isFinish;
    private Thread thread;
    private ConcurrentLinkedQueue<Packet> packetQueue;

    public TaskManager(ConcurrentLinkedQueue<Packet> packetQueue) {
        this.thread = new Thread(this);
        this.packetQueue = packetQueue;
    }
    
    
    @Override
    public void run() {
        while (!isFinish) {
            if (!packetQueue.isEmpty()) {
                Packet newPacket = packetQueue.poll();
                switch(newPacket.command){
                    case CHAT_SEND:
                        new TaskChatSendExecutor((PacketChatSend)newPacket);
                }
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
