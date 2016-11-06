/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.packet;

import java.util.Observable;

/**
 *
 * @author Ega Prianto
 */
public abstract class Packet extends Observable {
    public final PacketType command;
    public final int serverLoad;
    public final SourceType sourceType;

    public Packet(PacketType command, int serverLoad, SourceType sourceType) {
        this.command = command;
        this.serverLoad = serverLoad;
        this.sourceType = sourceType;
    }
    
    @Override
    public  String toString(){
        return command+";"+serverLoad+";"+sourceType+";"+getBodyData()+"\n";
    }

    public abstract String getBodyData();

}
