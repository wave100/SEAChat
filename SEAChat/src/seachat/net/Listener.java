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
            seachat.SEAChat.log(new String(buffer));
            //Send packet to protocol
        } catch (IOException ex) {
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
