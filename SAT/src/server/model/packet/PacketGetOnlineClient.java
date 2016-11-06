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
public class PacketGetOnlineClient extends Packet {

    public String ipAddressPort;
    
    public PacketGetOnlineClient(PacketType command, int serverLoad, SourceType sourceType, String ipAddressPort) {
        super(command, serverLoad, sourceType);
        this.ipAddressPort = ipAddressPort;
    }

    @Override
    public String getBodyData() {
        return ipAddressPort;
    }

}
