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
public class PacketLoginServer extends Packet {

    public String id;
    public String ipAdressPort;

    public PacketLoginServer(PacketType command, int serverLoad, SourceType sourceType, String id, String ipAddressPort) {
        super(command, serverLoad, sourceType);
        this.id=id;
        this.ipAdressPort = ipAddressPort;
    }

    @Override
    public String getBodyData() {
        return id+";"+this.ipAdressPort;
    }

}
