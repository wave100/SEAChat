/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seachat.net.protocol;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evan__000
 */
public class Protocol2 extends Protocol{
    
    private String message = "";
    
    public Protocol2(){
        super.ProtocolNumber = 2;
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
    public void sendMessage(seachat.net.Sender sender) {
        try {
            sender.send(this.returnByteArray());
        } catch (IOException ex) {
            Logger.getLogger(Protocol2.class.getName()).log(Level.SEVERE, null, ex);
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
