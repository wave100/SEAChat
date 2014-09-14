/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Rish
 */
public class Sender {
    
    MulticastSocket sendSocket;
    
    public Sender(MulticastSocket s) {
        sendSocket = s;
    }
    
    public void send(String s, InetAddress i, int p) throws IOException {
      //For testing purposes only. Replace String s with Protocol p.
        DatagramPacket packet = new DatagramPacket(s.getBytes(), s.length(), i, p);
        packet.setData(s.getBytes());
        sendSocket.send(packet);
    }
}
