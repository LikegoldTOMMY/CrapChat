/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import Util.Message;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender extends Thread{
    Socket s;
    OutputStream output;
    ObjectOutputStream writer;
    String user = "";
    
    public Sender(Socket s) throws IOException {
        this.s = s;
        this.output = s.getOutputStream();
        this.writer = new ObjectOutputStream(output);
    }
    
    void setUsername(String u, String pass){
        try {
            user = u;
            writer.writeObject(u);
            writer.writeObject(pass);
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isLogedIn(){
        return (!user.equals(""));
        
    }
    
    void sendMessage(String reciver, String text) throws IOException{
        writer.writeObject(new Message(text,reciver,this.user));
    }

}
