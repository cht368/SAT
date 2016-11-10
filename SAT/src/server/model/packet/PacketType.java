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
public enum PacketType {
    CHAT_SEND,
    CHAT_SEND_RESPONSE,
    CREATE_GROUP,
    DEFAULT_RESPONSE,
    GET_CHAT_CLIENT,
    GET_CHAT_SERVER,
    GET_KEY,
    GET_ONLINE_SERVER,
    GET_ONLINE_CLIENT,
    GOT_ONLINE,
    KEY,
    LOGIN_SERVER,
    LOGIN_RESPONSE,
    LOGIN_CLIENT,
    LOGOUT,
    REGISTER
}
