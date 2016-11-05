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
public class PacketLogout extends Packet {

    private String id;

    public PacketLogout(PacketType command, int serverLoad, SourceType sourceType, ChatType chatType, String id) {
        super(command, serverLoad, sourceType);
        this.id=id;
    }

    @Override
    public String getBodyData() {
        return id;
    }

}
