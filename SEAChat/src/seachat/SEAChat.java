/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    public static Listener discoveryListener;
    public static Sender s;
    public static String name = "Thing";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
            // TODO code application logic here
            PeerHandler ph = new PeerHandler();
            try {
                discoveryListener = new Listener(ph.joinDiscoveryGroup(InetAddress.getByName("234.235.236.237")));
            } catch (IOException ex) {
                Logger.getLogger(SEAChat.class.getName()).log(Level.SEVERE, null, ex);
            }

            Thread t = new Thread(discoveryListener);
            t.start();
            s = new Sender(discoveryListener.getSocket());
            Protocol prot = new Protocol0();
        prot.setSender("Ugly");
        prot.setContent("Person, You, Fuck you");
        prot.sendMessage();
        chat.main();
    }
    
    public static void log(String s) {
        System.out.println(s);
    }
    
    public static void log(int i) {
        System.out.println(i);
    }
}
