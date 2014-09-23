/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seachat.net.protocol;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author evan__000
 */
public class ProtocolEventHandler {
    
    Map<Integer, ProtocolEvent> mapOfListener = new LinkedHashMap();
    
    public ProtocolEventHandler(){
        
    }
    
    public void regesterListner(ProtocolEvent protocolEvent){
        this.mapOfListener.put(-1, protocolEvent);
    }
    
    public void regesterListner(ProtocolEvent protocolEvent, int listenFor){
        this.mapOfListener.put(listenFor, protocolEvent);
    }
    
    public void removeListner(ProtocolEvent protocolEvent){
        for(int a = 0; a < this.mapOfListener.size(); a++){
            if(this.mapOfListener.get(a).equals(protocolEvent)){
                this.mapOfListener.remove(a);
            }
        }
    }
    
    protected void messageGotten(Protocol protocol){
        
    }
    
    private ProtocolEvent[] iterateThoughMapForKey(int key){
        Set<Integer> keySet = this.mapOfListener.keySet();
    }
}
