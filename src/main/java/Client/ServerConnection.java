
package Client;

import java.net.*;
import java.io.*;
import java.util.*;
import Util.Message;


//Questa classe rappresenta la connessione fra il client e il server

public class ServerConnection {
    
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private String username;
    
    
    //Usato per connettere client e server, ritorna un booleano per informare la  logica che la connessione è avvenuta, o se ci sono stati errori
    public boolean connect(String serverIP, String username, String password){
        try{
            socket = new Socket(serverIP, 7777);
            output = socket.getOutputStream();
            writer = new ObjectOutputStream(output);
            input = socket.getInputStream();
            reader = new ObjectInputStream(input);
            this.username = username;
            
            
            //Invio le credenziali al server per effetturare l'autenticazione
            writer.writeObject(username);
            writer.writeObject(password);
        } catch (IOException ex) {
            return false;
        }
        //System.out.println("Connected");
        return true;
    }
    
    
    //Ritorna la lista dei messaggi arrivati tramite il socket
    public ArrayList<Message> getMessages() throws IOException, ClassNotFoundException{
        ArrayList<Message> mList = new ArrayList();
        while(input.available() > 0){
            Message m;
            m = (Message) reader.readObject();
            mList.add(m);
        }
        return mList;
    }
    //Invia un messaggio tramite socket
    public void sendMessage(String content, String reciver) throws IOException{
        Message m = new Message(content,reciver,username);
        writer.writeObject(m);
    }
    
    //Usata dalla logica per capire se la connessione è attiva
    public boolean connected(){
        try{
            socket.isConnected();
        }catch (Exception Ex){
            return false;
        }
        return true;
    }

    
   
}
