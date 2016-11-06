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
public class PacketChatSend extends Packet {

    public ChatType chatType;
    public String idPengirim;
    public String idPenerima;
    public String chat;
    public long timestamp;

    public PacketChatSend(PacketType command, int serverLoad, SourceType sourceType, ChatType chatType, String idPengirim, String idPenerima, String chat,long timestamp) {
        super(command, serverLoad, sourceType);
        this.chatType=chatType;
        this.idPenerima=idPenerima;
        this.idPengirim=idPengirim;
        this.chat= chat;
        this.timestamp=timestamp;
    }

    @Override
    public String getBodyData() {
            return chatType + ";" + idPengirim + ";" + idPenerima + ";" + chat+ ";"+timestamp;
    }

}
