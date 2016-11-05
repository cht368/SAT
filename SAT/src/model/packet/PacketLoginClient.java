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
public class PacketLoginClient extends Packet {

    private String id;
    private String password;

    public PacketLoginClient(PacketType command, int serverLoad, SourceType sourceType, ChatType chatType, String id, String password) {
        super(command, serverLoad, sourceType);
        this.id=id;
        this.password=password;
    }

    @Override
    public String getBodyData() {
        return id + ";"+password;
    }

}
