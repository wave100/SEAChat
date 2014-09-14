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
import seachat.net.Listener;
import seachat.net.PeerHandler;
import seachat.net.Sender;

/**
 *
 * @author Rish
 */
public class SEAChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
            // TODO code application logic here
            PeerHandler ph = new PeerHandler();
            Listener discoveryListener = null;
            try {
                discoveryListener = new Listener(ph.joinDiscoveryGroup(InetAddress.getByName("234.235.236.237")));
            } catch (IOException ex) {
                Logger.getLogger(SEAChat.class.getName()).log(Level.SEVERE, null, ex);
            }

            Thread t = new Thread(discoveryListener);
            t.start();
            Sender s = new Sender(discoveryListener.getSocket());
        try {
            s.send("Hello, world!");
        } catch (IOException ex) {
            Logger.getLogger(SEAChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        seachat.gui.Chat.main(new String[1]);
    }
    
    public static void log(String s) {
        System.out.println(s);
    }
    
    public static void log(int i) {
        System.out.println(i);
    }
}
