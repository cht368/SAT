/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ega Prianto
 */
public class JDBCMySQLManager {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/sat";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    //  Database credentials
    public static final String USER = "root";
    public static final String PASS = "";

    public Connection conn = null;
    public Statement stmt = null;

    public JDBCMySQLManager() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
    }
    
    public void close() throws SQLException{
        this.conn.close();
        this.stmt.close();
    }

    public void insertChat(String id_sender, String id_receiver, String chat, String date) throws SQLException, ParseException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `chat` (`id_sender`, `id_receiver`, `chat_message`, `datetime`) VALUES (?, ?,?, ?)");
        preparedStatement.setString(1, id_sender);
        preparedStatement.setString(2, id_receiver);
        preparedStatement.setString(3, chat);
        preparedStatement.setString(4, date);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void insertUser(String id, String id_server,String ip_address_port, String passwd, String prof_name, String public_key, String current_status) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `user_sat` (`id`, `id_server`, `ip_address_port`, `passwd`, `prof_name`, `public_key`, `current_status`) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, id_server);
        preparedStatement.setString(3, ip_address_port);
        preparedStatement.setString(4, passwd);
        preparedStatement.setString(5, prof_name);
        preparedStatement.setString(6, public_key);
        preparedStatement.setString(7, current_status);
        preparedStatement.execute();
        preparedStatement.close();
    }
    
    public String getIpAddressPortUser(String id) throws SQLException{
        String sql = "SELECT `ip_address_port` FROM `user_sat` WHERE `id`='"+id+"'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        String result = rs.getString("ip_address_port");
        rs.close();
        return result;
    }
    
    public void updateIpAddressUser(String id, String ip_address) throws SQLException {
        String sql = "UPDATE `user_sat` SET `ip_address_port` = '"+ip_address+"' WHERE `user_sat`.`id` = 'egaprianto'" ; 
        stmt.executeUpdate(sql);
    }

}
