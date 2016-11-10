/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.packet;

import java.util.Date;
import server.model.db.JDBCMySQLManager;

/**
 *
 * @author Ega Prianto
 */
public class PacketDefaultResponse extends Packet {

    public String response;

    public PacketDefaultResponse(PacketType command, int serverLoad, SourceType sourceType, String response) {
        super(command, serverLoad, sourceType);
        this.response = response;
    }

    @Override
    public String getBodyData() {
        return response;
    }

}
