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

/**
 * Message Protocol contains message
 * @author Yiwen Dong
 */
public class Protocol0 extends Protocol{
    
    private String message = "";
    
    public Protocol0(){
        super.ProtocolNumber = 0;
    }
    
    @Override
    public String toString() {
        return this.toString();
    }

    @Override
    public void invoked() {
        
        seachat.SEAChat.log("Working?");
        //seachat.SEAChat.chat.getChatBox().add(new JLabel("Fuck this"));
    }

    @Override
    public void sendMessage() {
        try {
            byte[] byteArray = new byte[500];
            
            byteArray[0] = Protocol.VERSION.getBytes()[0];
            byteArray[1] = Protocol.VERSION.getBytes()[1];
            
            int protocolLength = this.ProtocolNumber;
            int protocolStarting = 5 - String.valueOf(protocolLength).getBytes().length;
            int protocolLocation = 5 - String.valueOf(protocolLength).getBytes().length;
            byte[] protocolArray = String.valueOf(ProtocolNumber).getBytes();
            while(protocolLocation <= 4){
                byteArray[protocolLocation] = protocolArray[protocolLocation - protocolStarting];
                protocolLocation++;
            }
            
            int messageLength = message.length();
            int lengthStarting = 20 - String.valueOf(messageLength).getBytes().length;
            int lengthLocation = 20 - String.valueOf(messageLength).getBytes().length;
            byte[] messageLengthArray = String.valueOf(messageLength).getBytes();
            while(lengthLocation <= 19){
                byteArray[lengthLocation] = messageLengthArray[lengthLocation - lengthStarting];
                lengthLocation++;
            }
            
//            int senderLength = super.Sender.length();
//            int senderStarting = 25 - String.valueOf(senderLength).getBytes().length;
//            int senderLocation = 25 - String.valueOf(senderLength).getBytes().length;
//            byte[] senderArray = String.valueOf(senderLength).getBytes();
//            while(senderLocation <= 24){
//                byteArray[senderLocation] = senderArray[senderLocation - senderStarting];
//                senderLocation++;
//            }
            
            for(int a = 20; a < super.Sender.getBytes().length + 20; a++){
                byteArray[a] = super.Sender.getBytes()[a - 20];
            }
            
            for(int a = 50; a < this.message.getBytes().length + 50; a++){
                byteArray[a] = message.getBytes()[a - 50];
            }
            
            seachat.SEAChat.s.send(byteArray);
        } catch (IOException ex) {
            Logger.getLogger(Protocol0.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public byte[] returnByteArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
