/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat.net.protocol;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import seachat.net.Sender;

/**
 * This protocol contains the handshake info.
 * @author Yiwen Dong
 */
public class Protocol1 extends Protocol{
    
    static{
        Protocol.regesterProtocol(1, Protocol1.class);
    }
    
    private String message = System.getProperty("user.name");
    
    public Protocol1(){
        super.ProtocolNumber = 1;
    }

    @Override
    public String toString() {
        return Protocol.VERSION + this.ProtocolNumber + this.Sender + this.Recipient + this.message;
    }

    @Override
    public void invoked() {
        seachat.SEAChat.log(this.toString());
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
        return this.message;
    }
    
}
