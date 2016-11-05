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
public class PacketChatSend extends Packet {

    private ChatType chatType;
    private String idPengirim;
    private String idPenerima;
    private String chat;

    public PacketChatSend(PacketType command, int serverLoad, SourceType sourceType, ChatType chatType, String idPengirim, String idPenerima, String chat) {
        super(command, serverLoad, sourceType);
        this.chatType=chatType;
        this.idPenerima=idPenerima;
        this.idPengirim=idPengirim;
        this.chat= chat;
    }

    @Override
    public String getBodyData() {
            return chatType + ";" + idPengirim + ";" + idPenerima + ";" + chat;
    }

}
