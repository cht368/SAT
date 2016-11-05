/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.task.manager.executor;

import java.net.Socket;

/**
 *
 * @author Ega Prianto
 */
public abstract class TaskExecutor implements Runnable {
    private Socket connection;
    
}
