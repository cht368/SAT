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
public class PacketLoginResponse extends Packet {

    public LoginResponseType response;
    public String username;
    public PacketLoginResponse(PacketType command, int serverLoad, SourceType sourceType, LoginResponseType response, String username) {
        super(command, serverLoad, sourceType);
        this.response = response;
        this.username = username;
    }

    @Override
    public String getBodyData() {
        return response + ";" + username;
    }

}
