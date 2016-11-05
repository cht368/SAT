/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.task.manager;

/**
 *
 * @author Ega Prianto
 */
public class TaskManager implements Runnable {

    private boolean isFinish;
    private Thread thread;

    public TaskManager() {
        this.thread = new Thread(this);
    }
    
    
    @Override
    public void run() {
        while (!isFinish) {
            
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
