/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model.clientData;

import java.util.Observable;

/**
 *
 * @author Ega Prianto
 */
public abstract class Chat extends Observable{
    protected StringBuilder chats;
    protected String withId;

    public Chat(String withId) {
        this.withId = withId;
        this.chats = new StringBuilder();
    }

    public StringBuilder getChats() {
        return chats;
    }

    public void setChats(StringBuilder chats) {
        this.chats = chats;
        setChanged();
        notifyObservers();
    }

    public String getWithId() {
        return withId;
    }
    
    public void addSelfChat(String newChat){
        this.chats.append("You");
        addChat(newChat);
    }
            
    protected void addChat(String newChat){
        this.chats.append(" : ");
        this.chats.append(newChat);
        this.chats.append("\n");
        setChanged();
        notifyObservers();
    }
    @Override
    public String toString(){
        return this.chats.toString();
    }
    
}
