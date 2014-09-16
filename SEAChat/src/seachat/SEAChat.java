/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import seachat.gui.Chat;
import seachat.net.Listener;
import seachat.net.PeerHandler;
import seachat.net.Sender;
import seachat.net.protocol.Protocol;
import seachat.net.protocol.Protocol0;

/**
 *
 * @author Rish
 */
public class SEAChat {
    
    public static Chat chat = new Chat();
    public static Sender s;
    public static String name = "Thing";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
 
            // TODO code application logic here
//            PeerHandler ph = new PeerHandler();
//            try {
//                discoveryListener = new Listener(ph.joinDiscoveryGroup(InetAddress.getByName("234.235.236.237")));
//            } catch (IOException ex) {
//                Logger.getLogger(SEAChat.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            Thread discoveryListenerThread = new Thread(discoveryListener);
//            discoveryListenerThread.start();
//            s = new Sender(discoveryListener.getSocket());
//        seachat.gui.Chat c = new seachat.gui.Chat();
//        c.main();
//            Protocol prot = new Protocol0();
//        prot.setSender("xzxy");
//        prot.setContent("Test Message");
//        prot.sendMessage();
//        chat.main();
        
        PeerHandler ph = new PeerHandler();
        Listener discoveryListener;
        Listener groupListener;
        discoveryListener = new Listener(ph.joinDiscoveryGroup(InetAddress.getByName("234.235.236.237")));
        groupListener = new Listener(ph.createGroup("testgroup"));
        Sender discoverySender = new Sender(discoveryListener.getSocket());
        Sender groupSender = new Sender(groupListener.getSocket());
        Thread discoveryListenerThread = new Thread(discoveryListener);
        Thread groupListenerThread = new Thread(groupListener);
        discoveryListenerThread.start();
        groupListenerThread.start();
        
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            
            discoverySender.send(sc.nextLine(), InetAddress.getByName("234.235.236.237"), 58394);
            
        }
        
        
    }
    
    public static void log(String s) {
        System.out.println(s);
    }
    
    public static void log(int i) {
        System.out.println(i);
    }
}
