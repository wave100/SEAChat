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
public class PeerHandler {
    
    private MulticastSocket groupSocket;
    private MulticastSocket discoverySocket;
    private DatagramPacket packet;
    
    public MulticastSocket joinDiscoveryGroup(InetAddress i) throws IOException {
        discoverySocket = new MulticastSocket(58394);
        discoverySocket.joinGroup(i);
        discoverySocket.setBroadcast(true); //This is for compatibility.
        return discoverySocket;
    }
    
    public InetAddress locateGroup(String name) throws IOException {
        discoverySocket.send(packet);
    }
    
    public MulticastSocket joinGroup(InetAddress i) {
        
    }
    
    public MulticastSocket createGroup(String name) {
        
    }
}
