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
import java.net.UnknownHostException;

/**
 *
 * @author Rish
 */
public class Sender {
    
    MulticastSocket sendSocket;
    
    public Sender(MulticastSocket s) {
        sendSocket = s;
    }
    
    public void send(String s) throws IOException {
      //For testing purposes only. Replace String s with Protocol p.
        DatagramPacket packet = new DatagramPacket(s.getBytes(), s.length(), InetAddress.getByName("234.235.236.237"), 58394);
        packet.setData(s.getBytes());
        sendSocket.send(packet);
    }
    
    public void send(byte[] s) throws IOException{
        DatagramPacket packet = new DatagramPacket(s, s.length, InetAddress.getByName("234.235.236.237"), 58394);
        packet.setData(s);
        sendSocket.send(packet);
    }
}
