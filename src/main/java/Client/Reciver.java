package Client;

import Util.Message;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reciver extends Thread{
    Socket s;
    InputStream input;
    ObjectInputStream reader;
    
    public Reciver(Socket s) throws IOException {
        this.s = s;
        this.input = s.getInputStream();
        this.reader = new ObjectInputStream(input);
    }

    @Override
    public void run(){
        while(true){
            try {
                if(input.available() > 0){
                    try {
                        Message m;
                        
                        m = (Message) reader.readObject();
                        System.out.println(m.getSender()+": "+m.getContent());
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(Reciver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(Reciver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
