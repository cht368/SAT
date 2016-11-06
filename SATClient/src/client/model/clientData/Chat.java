/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model.clientData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    public void addSelfChat(String newChat,long timestamp){
        this.chats.append("You");
        addChat(newChat,timestamp);
    }
            
    protected void addChat(String newChat, long timestamp){
        Date date = new Date(timestamp);
        DateFormat df = new SimpleDateFormat("HH:mm");
        this.chats.append(" : ");
        this.chats.append(newChat);
        this.chats.append("     ");
        this.chats.append(df.format(date));
        this.chats.append("\n");
        setChanged();
        notifyObservers();
    }
    @Override
    public String toString(){
        return this.chats.toString();
    }
    
}
