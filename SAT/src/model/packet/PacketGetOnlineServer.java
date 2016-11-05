/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.packet;

import java.util.ArrayList;

/**
 *
 * @author Ega Prianto
 */
public class PacketGetOnlineServer extends Packet {

    private ArrayList<String>listID;

    public PacketGetOnlineServer(PacketType command, int serverLoad, SourceType sourceType, ArrayList<String> listID) {
        super(command, serverLoad, sourceType);
        this.listID=listID;
    }

    @Override
    public String getBodyData() {
        String hasil="";
        for (int i = 0; i < listID.size()-1; i++) {
            hasil+=listID.get(i)+";";
        }
        hasil+=listID.get(listID.size()-1);
        return hasil;
    }

}
