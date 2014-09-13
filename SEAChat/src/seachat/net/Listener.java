/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat.net;

import java.net.MulticastSocket;

/**
 *
 * @author Rish
 */
public class Listener implements Runnable {
    
    private MulticastSocket socket;
    
    @Override
    public void run() {
        socket.receive(null);
    }
    
    public Listener(MulticastSocket s) {
        socket = s;
    }
}
