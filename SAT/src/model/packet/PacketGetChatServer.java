/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.packet;

/**
 *
 * @author Ega Prianto
 */
public class PacketGetChatServer extends Packet{
    public String msg;
    public long timestamp;
    
    public PacketGetChatServer(PacketType command, int serverLoad, SourceType sourceType, long timestamp, String msg) {
        super(command, serverLoad, sourceType);
        this.msg=msg;
        this.timestamp=timestamp;
    }

    @Override
    public String getBodyData() {
        return timestamp + ";" + msg + ";"+timestamp;
    }
}
