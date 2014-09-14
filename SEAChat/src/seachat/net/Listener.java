/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import seachat.net.protocol.Protocol;

/**
 *
 * @author Rish
 */
public class Listener implements Runnable {
    
    private MulticastSocket socket;
    byte[] buffer = new byte[500];
    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    
    @Override
    public void run() {
        while(1==1) {
        try {
            socket.receive(packet);
            packet.getData();
            seachat.SEAChat.log(buffer.toString());
            for(int a = 0; a < buffer.length; a++){
                seachat.SEAChat.log(a + " : " + buffer[a]);
            }
            seachat.SEAChat.log(new String(buffer));
            //Send packet to protocol
            Protocol prot = Protocol.getProtocol(buffer);
            seachat.SEAChat.log(prot.getContent());
            seachat.SEAChat.log(prot.getProtocolNumber() + "");
            seachat.SEAChat.log(prot.getSender());
        } catch (IOException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public Listener(MulticastSocket s) throws IOException {
        socket = s;
    }
    
    public MulticastSocket getSocket() {
        return socket;
    }
}
