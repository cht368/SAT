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
public class PacketFactory {
    public static Packet createPacketFromString(String string){
        String[] splitted = string.split(";");
        PacketType packetType = PacketType.valueOf(splitted[0]);
        switch(packetType){
            case CHAT_SEND:
                return new PacketChatSend(packetType, Integer.parseInt(splitted[1]), SourceType.valueOf(splitted[2]), ChatType.valueOf(splitted[3]), splitted[4], splitted[5], splitted[6]);
        }
        return null;
    }
}
