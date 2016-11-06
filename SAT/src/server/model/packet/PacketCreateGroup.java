/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.packet;

import java.util.ArrayList;

/**
 *
 * @author Ega Prianto
 */
public class PacketCreateGroup extends Packet{
    public String id_group;
    public String nama;
    public ArrayList<String> listID; 
    
    public PacketCreateGroup(PacketType command, int serverLoad, SourceType sourceType, String id_group, String nama,ArrayList<String> listId) {
        super(command, serverLoad, sourceType);
        this.id_group=id_group;
        this.nama=nama;
        this.listID = listId;
    }

    @Override
    public String getBodyData() {
        String hasil="";
        for (int i = 0; i < listID.size()-1; i++) {
            hasil+=listID.get(i)+";";
        }
        hasil+=listID.get(listID.size()-1);
        return id_group + ";" + nama+";"+hasil;
    }
}
