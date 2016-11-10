/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.packet;

/**
 *
 * @author Ega Prianto
 */
public class PacketGotOnline extends Packet {

    public String id;
    public String ipAddress;
    public int port;

    public PacketGotOnline(PacketType command, int serverLoad, SourceType sourceType, String id, String ipAddress, int port) {
        super(command, serverLoad, sourceType);
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public String getBodyData() {
        return id + ";" + ipAddress + ";" + port;
    }

}
