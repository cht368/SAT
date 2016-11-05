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
public class PacketChatSend extends Packet{
    private ChatType chatType;
    private String idPengirim;
    private String idPenerima;
    private String chat;
    
    public PacketChatSend(PacketType command, int serverLoad, SourceType sourceType) {
        super(command, serverLoad, sourceType);
    }

    @Override
    public void assignFromString(String string) {
        String[] splitted = string.split(";");
        this.splitted[0];
    }

    @Override
    public String getBodyData() {
        return chatType+";"+idPengirim+";"+idPenerima+";"+chat;
    }
    
}
