/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seachat.net.protocol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yiwen Dong
 */
public abstract class Protocol {
    
    protected static final String VERSION = "01";
    private static final Map<Integer, Class<? extends Protocol>> mapOfProtocol;
    static{
        mapOfProtocol = new HashMap<>();
        mapOfProtocol.put(0, Protocol0.class);
        mapOfProtocol.put(1, Protocol1.class);
    }
    
    protected int ProtocolNumber;
    protected String Sender;
    
    //char0 char1 is version
    //char2 - 4 is protocol number
    //char5-19 is length info
    //char20-24 is username
    //char length is 500, no less no more
    public Protocol(){
        
    }
    
    public Protocol(String message){
        this.setContent(message);
    }
    
    public Protocol(Protocol protocol){
        
    }
    
    @Deprecated
    public static Protocol getProtocol(String message) throws IllegalAccessException, InstantiationException{
        if(correctVersion(message)){
            int protocolNumber = parseForProtocolNumber(message);
            Protocol protocol = mapOfProtocol.get(protocolNumber).newInstance();
            protocol.setProtocolNumber(protocolNumber);
            protocol.setSender(parseForSender(message));
            protocol.setContent(parseForContent(message, parseForLength(message)));
            return protocol;
        }
        return null;
    }
    
    public static Protocol getProtocol(byte[] message) throws IllegalAccessException, InstantiationException{
        if(correctVersion(message)){
            int protocolNumber = parseForProtocolNumber(message);
            Protocol protocol = mapOfProtocol.get(protocolNumber).newInstance();
            protocol.setProtocolNumber(protocolNumber);
            protocol.setSender(parseForSender(message));
            protocol.setContent(parseForContent(message, parseForLength(message)));
            return protocol;
        }
        return null;
    }
    
    @Deprecated
    private static int parseForProtocolNumber(String message){
        return Integer.valueOf(message.substring(2, 4));
    }
    
    private static int parseForProtocolNumber(byte[] message){
        return Integer.parseInt((new String(Arrays.copyOfRange(message, 2, 5))).trim());
    }
    
    @Deprecated
    private static int parseForLength(String message){
        return Integer.valueOf(message.substring(5, 19));
    }
    
    private static int parseForLength(byte[] message){
        return Integer.valueOf(new String(Arrays.copyOfRange(message, 5, 20)).trim());
    }
    
    @Deprecated
    private static String parseForSender(String message){
        return message.substring(20, 24);
    }
    
    private static String parseForSender(byte[] message){
        return new String(Arrays.copyOfRange(message, 20, 25));
    }
    
    @Deprecated
    private static String parseForContent(String message, int Length){
        if(Length == 0){
            return "";
        }
        return message.substring(50, 49 + Length);
    }
    
    private static String parseForContent(byte[] message, int Length){
        if(Length == 0){
            return "";
        }
        return new String(Arrays.copyOfRange(message, 50, 50 + Length));
    }
    
    @Deprecated
    private static boolean correctVersion(String message){
        return VERSION.equals(message.substring(0, 1));
    }
    
    private static boolean correctVersion(byte[] message){
        return VERSION.equals(new String(Arrays.copyOfRange(message, 0, 2)));
    }
    
    protected static String parseData(byte[] message, int starting, int ending){
    if(ending == starting){
            return "";
        }
        return (new String(Arrays.copyOfRange(message, starting, ending))).trim();
    }
    
    public void setProtocolNumber(int Number){
        this.ProtocolNumber = Number;
    }
    
    public int getProtocolNumber(){
        return this.ProtocolNumber;
    }
    
    public void setSender(String sender){
        if(sender.length() > 5){
            return;
        }
        this.Sender = sender;
    }
    
    public String getSender(){
        return this.Sender;
    }
    
    @Deprecated
    public static Protocol createProtocol(int Number) throws InstantiationException, IllegalAccessException{
        return mapOfProtocol.get(Number).newInstance();
    }
    
    @Deprecated
    @Override
    public abstract String toString();
    public abstract void invoked();
    public abstract void sendMessage();
    public abstract byte[] returnByteArray();
    public abstract void setContent(String content);
    public abstract String getContent();
}
