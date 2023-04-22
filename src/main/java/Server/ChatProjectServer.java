
package Server;

import Util.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class ChatProjectServer {

    private HashMap<String, ArrayList<Message>> Connections = new HashMap<String, ArrayList<Message>>();

    
    public static void main(String[] args) throws IOException {
        ChatProjectServer server = new ChatProjectServer();
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        boolean running = true;
        while(running){
            Socket s = ss.accept(); 
            UserConnection u = new UserConnection(s, server);
            u.start();
        } 
    }
    
    public void InizializeUser(User u){
        Connections.put(u.getUsername(), new ArrayList());
    }
    public synchronized void AddMessage(String reciver, Message m){
        if(!Connections.containsKey(reciver)){
            Connections.put(reciver, new ArrayList());
        }
        Connections.get(reciver).add(m);
    }
    public synchronized ArrayList<Message> getMessages(String user){
        ArrayList<Message> m = new ArrayList();
        if( Connections.containsKey(user)){
            m = Connections.get(user);
            Connections.remove(user);
            return m;
        }
        else{
            return m;
        }
    }
}
