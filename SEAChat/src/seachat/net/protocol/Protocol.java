/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat.net.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yiwen Dong
 */
public abstract class Protocol {
    
    private static final String VERSION = "01";
    private static final Map<Integer, Class<Protocol>> mapOfProtocol;
    static{
        mapOfProtocol = new HashMap<>();
        mapOfProtocol.put(0, Protocol.class);
    }
    
    private int ProtocolNumber;
    private String Sender;
    
    //char0 char1 is version
    //char2 - 4 is protocol number
    //char5-19 is length info
    //char20-24 is username
    //char length is 500, no less no more
    public Protocol(){
        
    }
    
    public Protocol(String message){
        this.setMessage(message);
    }
    
    public Protocol(Protocol protocol){
        
    }
    
    public static Protocol getProtocol(String message) throws IllegalAccessException, InstantiationException{
        if(correctVersion(message)){
            int protocolNumber = parseForProtocolNumber(message);
            Protocol protocol = mapOfProtocol.get(protocolNumber).newInstance();
            protocol.setProtocolNumber(protocolNumber);
            protocol.setSender(parseForSender(message));
            protocol.setMessage(parseForContent(message, parseForLength(message)));
            return protocol;
        }
        return null;
    }
    
    private static int parseForProtocolNumber(String message){
        return Integer.valueOf(message.substring(2, 4));
    }
    
    private static int parseForLength(String message){
        return Integer.valueOf(message.substring(5, 19));
    }
    
    private static String parseForSender(String message){
        return message.substring(20, 24);
    }
    
    private static String parseForContent(String message, int Length){
        if(Length == 0){
            return "";
        }
        return message.substring(50, 49 + Length);
    }
    
    private static boolean correctVersion(String message){
        return VERSION.equals(message.substring(0, 1));
    }
    
    public void setProtocolNumber(int Number){
        this.ProtocolNumber = Number;
    }
    
    public int getProtocolNumber(){
        return this.ProtocolNumber;
    }
    
    public void setSender(String sender){
        this.Sender = sender;
    }
    
    public String getSender(){
        return this.Sender;
    }
    
    @Deprecated
    public static Protocol createProtocol(int Number) throws InstantiationException, IllegalAccessException{
        return mapOfProtocol.get(Number).newInstance();
    }
    
    public abstract String toString();
    public abstract void invoked();
    public abstract void sendMessage();
    public abstract byte[] returnByteArray();
    public abstract void setMessage(String content);
}
