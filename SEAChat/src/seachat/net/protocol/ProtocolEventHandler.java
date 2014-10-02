/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seachat.net.protocol;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author evan__000
 */
public class ProtocolEventHandler {
    
    Map<ProtocolEventListener, Integer> mapOfListener = new LinkedHashMap();
    
    public ProtocolEventHandler(){
        
    }
    
    public void regesterListner(ProtocolEventListener protocolEvent){
        this.mapOfListener.put(protocolEvent, -1);
    }
    
    public void regesterListner(ProtocolEventListener protocolEvent, int listenFor){
        this.mapOfListener.put(protocolEvent, listenFor);
    }
    
    public void removeListner(ProtocolEventListener protocolEvent){
//        for(int a = 0; a < this.mapOfListener.size(); a++){
//            if(this.mapOfListener.get(a).equals(protocolEvent)){
//                this.mapOfListener.remove(a);
//            }
//        }
        this.mapOfListener.remove(protocolEvent);
    }
    
    protected void messageGotten(Protocol protocol){
        List<ProtocolEventListener> arrayOfListener = this.getForValue(protocol.getProtocolNumber());
        arrayOfListener.addAll(this.getForValue(-1));
        for(ProtocolEventListener listener : arrayOfListener){
            listener.gotEvent(protocol);
        }
    }
    
//    private ProtocolEventListener[] iterateThoughMapForKey(int key){
//        //Set<Integer> keySet = this.mapOfListener.keySet();
//    }
    
    private List<ProtocolEventListener> getForValue(int value){
        List<ProtocolEventListener> arrayOfMatch = new LinkedList<>();
        Iterator it = this.mapOfListener.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pairs = (Map.Entry)it.next();
            if(((Integer)pairs.getValue()).equals(value)){
                arrayOfMatch.add((ProtocolEventListener)pairs.getKey());
            }
        }
        return arrayOfMatch;
    }
}
