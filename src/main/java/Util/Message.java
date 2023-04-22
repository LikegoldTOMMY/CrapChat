/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.*;

public class Message implements Serializable{
    String content;
    String reciver;

    public Message(String content, String reciver) {
        this.content = content;
        this.reciver = reciver;
    }

    public String getReciver() {
        return reciver;
    }
    
    public String getContent() {
        return content;
    }
    
}
