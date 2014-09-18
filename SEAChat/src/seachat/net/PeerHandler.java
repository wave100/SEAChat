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
import java.util.Random;
import seachat.net.protocol.Protocol;
import seachat.net.protocol.Protocol1;

/**
 *
 * @author Rish
 */
public class PeerHandler {
    
    private MulticastSocket groupSocket;
    private MulticastSocket discoverySocket;
    private Sender discoverySender;
    private byte[] buffer = new byte[500];
    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    
    
    public MulticastSocket joinDiscoveryGroup(InetAddress i) throws IOException {
        discoverySocket = new MulticastSocket(58394);
        discoverySocket.joinGroup(i);
        discoverySocket.setBroadcast(true); //This is for compatibility.
        discoverySender = new Sender(discoverySocket, 1); //Why the hell am I making a sender here. WHY.
        return discoverySocket;
    }
    
    public InetAddress locateGroup(String name) throws IOException {
        Protocol discovery = new Protocol1();
        discoverySender.send(discovery.getContent(), discoverySocket.getInetAddress(), 58394);
        discoverySocket.receive(packet);
        return InetAddress.getByName(new String(packet.getData()));
    }
    public Boolean groupExists(String name) {
        return false;
    }
    public MulticastSocket joinGroup(InetAddress i) throws IOException {
        groupSocket = new MulticastSocket(58394);
        groupSocket.joinGroup(i);
        groupSocket.setBroadcast(true);
        return groupSocket;
    }
    
    public MulticastSocket createGroup(String name) throws IOException {
        Random rand = new Random();
        groupSocket = new MulticastSocket(58394);
        groupSocket.joinGroup(InetAddress.getByName("234.235.237." + String.valueOf(rand.nextInt(255))));
        return groupSocket;
    }
}
