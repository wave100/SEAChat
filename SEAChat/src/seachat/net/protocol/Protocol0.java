/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seachat.net.protocol;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JLabel;
import seachat.net.Sender;

/**
 * Message Protocol contains message
 *
 * @author Yiwen Dong
 */
public class Protocol0 extends Protocol {

    private String message = "";

    public Protocol0() {
        super.ProtocolNumber = 0;
    }

    @Override
    public String toString() {
        return Protocol.VERSION + this.ProtocolNumber + this.Sender + this.Recipient + this.message;
    }

    @Override
    public void invoked() {

        seachat.SEAChat.log("Working?");
        //seachat.SEAChat.chat.getChatBox().add(new JLabel("Fuck this"));
    }

    @Override
    public void sendMessage(Sender sender) {
        try {
            seachat.SEAChat.s.send(this.returnByteArray());
        } catch (IOException ex) {
            Logger.getLogger(Protocol0.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public byte[] returnByteArray() {
        byte[] byteArray = new byte[500];
        Protocol.addDataL(byteArray, 0, Protocol.VERSION.getBytes());
        Protocol.addDataR(byteArray, 4, (this.ProtocolNumber + "").getBytes());
        Protocol.addDataR(byteArray, 19, (this.message.length() + "").getBytes());
        Protocol.addDataL(byteArray, 20, this.Sender.getBytes());
        Protocol.addDataL(byteArray, 25, this.Recipient.getBytes());
        Protocol.addDataL(byteArray, 50, this.message.getBytes());
        return byteArray;
    }

    @Override
    public void setContent(String content) {
        this.message = content;
    }

    @Override
    public String getContent() {
        return message;
    }

}
