
package Client;

import Util.*;
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;


public class ChatProjectClient {

    //Modificare LoginForm per prendere oggetto ChatProjectClient e non sender
    //Implementare la scelta del server con una funzione dentro il login
    
    

    public static void main(String args[]) throws IOException, InterruptedException{
        
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected to server");
        
        Sender sender = new Sender(socket);
        sender.start(); 
        LoginForm l = new LoginForm(sender);
        l.setVisible(true);
        while(!sender.isLogedIn()){
            Thread.sleep(5);
        }
        l.setVisible(false);
        UI u = new UI(sender);
        Reciver reciver = new Reciver(socket,u);
        reciver.start();
        u.setVisible(true);
    }
}
