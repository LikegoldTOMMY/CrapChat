/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.*;

public class Message implements Serializable{
    String content;
    String reciver;
    String sender; 
    public Message(String content, String reciver, String sender) {
        this.content = content;
        this.reciver = reciver;
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }
    public String getSender(){
        return sender;
    }
    public String getContent() {
        return content;
    }
    
}
