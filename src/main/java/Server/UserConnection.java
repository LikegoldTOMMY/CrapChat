
package Server;

import java.net.*;
import java.io.*;

import Util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserConnection extends Thread{

    Socket s;
    ChatProjectServer server;
    User self; 
    InputStream input;
    ObjectInputStream reader; 
    OutputStream output;
    ObjectOutputStream writer;
    
    public UserConnection(Socket s, ChatProjectServer server) throws IOException {
        this.s = s;
        this.server = server;
        output = s.getOutputStream();
        writer = new ObjectOutputStream(output);
        input = s.getInputStream();
        reader = new ObjectInputStream(new BufferedInputStream(input));
        
    }
    
    boolean handleLogin(){
        try {
            try {
                String username = (String) reader.readObject();
                String password = (String) reader.readObject();
                self = new User(username, password);
                return true;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    Message readMessage(){
        try {
                if(input.available()>0){
                    try {
                        Message m = (Message) reader.readObject();
                        return m;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            } catch (IOException ex) {
                Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    
    @Override
    public void run(){
        System.out.println(s);
        boolean running = false;
        if(handleLogin()){
            running = true;
            server.InizializeUser(self);
            System.out.println("Connection from " + s );
            System.out.println("Username: " + self.getUsername());
            
        }
        
        while(running){
            Message m = readMessage();
            if(m != null){
                //System.out.println("Message sent form: "  + self.getUsername());
                server.AddMessage(m.getReciver(), m);
               
               
            }
           
            ArrayList<Message> m_in = server.getMessages(self.getUsername());
            if( !m_in.isEmpty()){
                for(Message x : m_in){
                    try {
                        writer.writeObject(x);
                    } catch (IOException ex) {
                        Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }   
}
