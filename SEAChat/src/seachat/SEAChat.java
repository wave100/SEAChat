/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seachat;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import seachat.gui.Chat;
import seachat.net.Listener;
import seachat.net.PeerHandler;
import seachat.net.Sender;

/**
 *
 * @author Rish
 */
public class SEAChat {

    public static Chat chat = new Chat();
    public static Sender s;
    public static String name = "Thing";

    private static PeerHandler ph;
    private static Listener discoveryListener;
    private static Listener groupListener;
    private static Sender discoverySender;
    private static Sender groupSender;
    private static Thread discoveryListenerThread;
    private static Thread groupListenerThread;

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
        ph = new PeerHandler();
        discoveryListener = new Listener(ph.joinDiscoveryGroup(InetAddress.getByName("234.235.236.237")));

        /*if (!ph.groupExists("testgroup")) {
         groupListener = new Listener(ph.createGroup("testgroup"));
         } else {
         groupListener = new Listener(ph.joinGroup(ph.locateGroup("testgroup")));
         }*/
        groupListener = new Listener(ph.joinDiscoveryGroup(InetAddress.getByName("234.235.236.237")));
        discoverySender = new Sender(discoveryListener.getSocket(), 1);
        groupSender = new Sender(groupListener.getSocket(), 0);
        discoveryListenerThread = new Thread(discoveryListener);
        groupListenerThread = new Thread(groupListener);
        discoveryListenerThread.start();
        groupListenerThread.start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

            discoverySender.send(sc.nextLine(), InetAddress.getByName("234.235.236.237"), 58394);

        }

    }

    public static void log(String s) {
        System.out.println("MAIN-LOG-STR: " + s);
    }

    public static void log(int i) {
        System.out.println("MAIN-LOG-INT:" + i);
    }

    public Sender getGroupSender() {
        return groupSender;
    }

    public Sender getDiscoverySender() {
        return discoverySender;
    }
}
