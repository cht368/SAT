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
public class PacketGetKey extends Packet {

    public String id_pengirim;
    public String id_group;

    public PacketGetKey(PacketType command, int serverLoad, SourceType sourceType, ChatType chatType, String id_pengirim, String id_group) {
        super(command, serverLoad, sourceType);
        this.id_pengirim=id_pengirim;
        this.id_group=id_group;
    }

    @Override
    public String getBodyData() {
        return id_pengirim+";"+id_group;
    }

}
