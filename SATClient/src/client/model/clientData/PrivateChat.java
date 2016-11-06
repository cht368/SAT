/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model.clientData;

/**
 *
 * @author Ega Prianto
 */
public class PrivateChat extends Chat {
    
    public PrivateChat(String withId) {
        super(withId);
    }
    
    public void addOpponentChat(String newChat){
        this.chats.append(this.withId);
        addChat(newChat);
    }
    
}
