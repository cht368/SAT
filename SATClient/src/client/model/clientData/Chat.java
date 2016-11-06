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
public class Chat extends Observable{
    private StringBuilder chats;
    private String withId;

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

    public void setWithId(String withId) {
        this.withId = withId;
        setChanged();
        notifyObservers();
    }
    
    public void addOpponentChat(String newChat){
        this.chats.append(this.withId);
        addChat(newChat);
    }
    
    public void addSelfChat(String newChat){
        this.chats.append("You");
        addChat(newChat);
    }
    
    private void addChat(String newChat){
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
