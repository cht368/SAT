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
public class PacketGetChatClient extends Packet{
    public String id_pengirim;
    public String id_penerima;
    public long timestamp;
    
    public PacketGetChatClient(PacketType command, int serverLoad, SourceType sourceType, String id_pengirim, String id_penerima, long timestamp) {
        super(command, serverLoad, sourceType);
        this.id_penerima=id_penerima;
        this.id_pengirim=id_pengirim;
        this.timestamp=timestamp;
    }

    @Override
    public String getBodyData() {
        return id_pengirim + ";" + id_penerima + ";"+timestamp;
    }
}
