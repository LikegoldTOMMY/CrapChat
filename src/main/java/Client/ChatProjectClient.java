
package Client;

import Util.*;
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;


public class ChatProjectClient {

    private HashMap<String, UI> chats = new HashMap<String, UI>();
    private ServerConnection conn;
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
        
        ChatProjectClient c = new ChatProjectClient();
        c.conn = new ServerConnection();    //Creo oggetto per la connessione al server
        LoginForm login = new LoginForm(c.conn); // Creo il form di login e lo rendo visibile
        login.setVisible(true);
        //Aspetto finchè l'utente non si connnette e poi nascondo il login
        while(!c.conn.connected()){}
        login.setVisible(false);
        
        //Creo l'interfaccia grafica per creare le chat
        ChatStarter starter = new ChatStarter(c);
        starter.setVisible(true);
        boolean running = true;
        //Finchè il codice è in esecuzione leggo dalla connessione se ci sono messaggi disponibili
        //Se ci sono messaggi disponibili li passo all'interfaccia della chat da dove provengono
        while(running){
            ArrayList<Message> incoming = c.conn.getMessages();
            if(!incoming.isEmpty()){
                for(Message m : incoming){
                    //System.out.println(m.getSender() + ": " + m.getContent());  
                    //Se non è ancora presente una chat con l'utente che ci ha inviato un messaggio devo crearla
                    try{
                        c.chats.get(m.getSender()).appendMessage(m);
                    }catch(Exception e){
                       c.addChat(m.getSender());
                       c.chats.get(m.getSender()).appendMessage(m);
                    }
                }
            }
            
        }
    }
    //Usato per creare le interfaccie grafiche delle nuove chat
    public void addChat(String username){
        UI u = new UI(conn, username);
        u.setVisible(true);
        chats.put(username, u);
        System.out.println(username );
    }
    
}
