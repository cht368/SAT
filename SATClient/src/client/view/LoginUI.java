/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import client.model.ConnectionReceiver;
import client.model.ConnectionSender;
import client.model.clientData.Chat;
import client.model.clientData.PrivateChat;
import client.model.clientData.User;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import server.model.packet.ChatType;
import server.model.packet.PacketLoginClient;
import server.model.packet.PacketType;
import server.model.packet.SourceType;

/**
 *
 * @author Ega Prianto
 */
public class LoginUI extends javax.swing.JPanel implements Observer {

    GraphicalUI gui;
    ConnectionReceiver connRecv;
    ConnectionSender connSend;

    /**
     * Creates new form LoginUI
     */
    public LoginUI(GraphicalUI gui, ConnectionReceiver connRecv, ConnectionSender connSend) {
        initComponents();
        this.gui = gui;
        this.connRecv = connRecv;
        this.connSend = connSend;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();
        jButtonRegister = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();

        jTextFieldID.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("ID : ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Password :");

        jButtonLogin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonRegister.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonRegister.setText("Register");
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });

        jButtonBack.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonBack.setText("Back");

        jPasswordField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPasswordField)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(jButtonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterActionPerformed
        this.gui.setMainPanelTo(new RegisterUI(gui, connRecv, connSend));
    }//GEN-LAST:event_jButtonRegisterActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        PacketLoginClient toSend = new PacketLoginClient(PacketType.LOGIN_CLIENT, 0, SourceType.CLIENT, this.jTextFieldID.getText(), connRecv.socket.getLocalSocketAddress().toString().substring(1), new String(this.jPasswordField.getPassword()));
        this.connSend.addPacket(toSend);
        this.jButtonBack.setEnabled(false);
        this.jButtonLogin.setEnabled(false);
        this.jButtonRegister.setEnabled(false);

    }//GEN-LAST:event_jButtonLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldID;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        User user = (User) o;
        this.jButtonBack.setEnabled(true);
        this.jButtonLogin.setEnabled(true);
        this.jButtonRegister.setEnabled(true);
        if (user.isAuthenticated()) {
            String idLawan = JOptionPane.showInputDialog(null, "Input ID", "Input ID", JOptionPane.QUESTION_MESSAGE);

            ChatType chatType = ChatType.PRIVATE;
            Chat newChat = new PrivateChat(idLawan);
            this.connRecv.chatRoomsData.put(idLawan, newChat);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ChatRoom chatRoom = new ChatRoom(newChat, chatType, jTextFieldID.getText(), idLawan, connSend);
                    newChat.addObserver(chatRoom);
                    chatRoom.setVisible(true);
                }
            }).start();
//            HomePage newHomePage = new HomePage(gui,connRecv,connSend);
//            this.connRecv.addObserver(newHomePage);
//            this.gui.setMainPanelTo(newHomePage);
        } else {
            JOptionPane.showConfirmDialog(null, "username and password didn't match", "Not Authenticated", JOptionPane.NO_OPTION, JOptionPane.WARNING_MESSAGE);
        }
    }
}
