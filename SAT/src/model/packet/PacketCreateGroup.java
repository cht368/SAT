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
public class PacketCreateGroup extends Packet{
    public String id_group;
    public String nama;
    
    public PacketCreateGroup(PacketType command, int serverLoad, SourceType sourceType, String id_group, String nama) {
        super(command, serverLoad, sourceType);
        this.id_group=id_group;
        this.nama=nama;
    }

    @Override
    public String getBodyData() {
        return id_group + ";" + nama;
    }
}
