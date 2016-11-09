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
public class User extends Observable {
    private String id;
    private String username;
    private boolean authenticated;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        System.out.println("ada yang ngubah");
        this.authenticated = authenticated;
        setChanged();
        notifyObservers();
    }

    public User(String id, String username,boolean authenticated) {
        this.id = id;
        this.username = username;
        this.authenticated = authenticated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
