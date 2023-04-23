
package Client;

import java.net.*;
import java.io.*;
import java.util.*;
import Util.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {
    
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private String username;
    
    public boolean connect(String serverIP, String username, String password){
        try{
            socket = new Socket(serverIP, 7777);
            output = socket.getOutputStream();
            writer = new ObjectOutputStream(output);
            input = socket.getInputStream();
            reader = new ObjectInputStream(input);
            this.username = username;
            
            writer.writeObject(username);
            writer.writeObject(password);
        } catch (IOException ex) {
            return false;
        }
        System.out.println("Connected");
        return true;
    }
    
    public ArrayList<Message> getMessages() throws IOException, ClassNotFoundException{
        ArrayList<Message> mList = new ArrayList();
        while(input.available() > 0){
            Message m;
            m = (Message) reader.readObject();
            mList.add(m);
        }
        return mList;
    }
    
    public void sendMessage(String content, String reciver) throws IOException{
        Message m = new Message(content,reciver,username);
        writer.writeObject(m);
    }
    
    public boolean connected(){
        try{
            socket.isConnected();
        }catch (Exception Ex){
            return false;
        }
        return true;
    }
    
}
