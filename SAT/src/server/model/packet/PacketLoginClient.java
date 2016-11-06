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
public class PacketLoginClient extends Packet {

    public String id;
    public String ipAddress;
    public String password;

    public PacketLoginClient(PacketType command, int serverLoad, SourceType sourceType, String id, String ipAddress, String password) {
        super(command, serverLoad, sourceType);
        this.id=id;
        this.ipAddress = ipAddress;
        this.password=password;
    }

    @Override
    public String getBodyData() {
        return id+ ";"+ipAddress + ";"+password;
    }

}
