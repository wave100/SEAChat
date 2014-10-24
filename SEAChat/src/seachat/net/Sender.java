/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat.net;

import io.github.theguy191919.udpft.net.AbstractProtocolSender;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import seachat.net.protocol.ProtocolSender;

/**
 *
 * @author Rish
 */
public class Sender implements AbstractProtocolSender{
    //Add a close() method! 
    private final MulticastSocket sendSocket;
    
    public Sender(MulticastSocket s, int protocolNumber) {
        sendSocket = s;
    }
    
    public void send(String s, InetAddress i, int p) throws IOException {
      //For testing purposes only. Replace String s with Protocol p.
        DatagramPacket packet = new DatagramPacket(s.getBytes(), s.length(), i, p);
        packet.setData(s.getBytes());
        sendSocket.send(packet);
    }
    
    public void send(String s) throws IOException{
        DatagramPacket packet = new DatagramPacket(s.getBytes(), s.getBytes().length, sendSocket.getInetAddress(), 58394);
        packet.setData(s.getBytes());
        sendSocket.send(packet);
    }
    
    @Override
    public void send(byte[] b){
        try {
            DatagramPacket packet = new DatagramPacket(b, b.length, sendSocket.getInetAddress(), 58394);
            packet.setData(b);
            sendSocket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(byte[] b, InetAddress i, int p) throws IOException {
        DatagramPacket packet = new DatagramPacket(b, b.length, i, p);
        packet.setData(b);
        sendSocket.send(packet);
    }
}
