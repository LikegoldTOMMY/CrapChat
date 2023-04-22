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
    UI u;
    public Reciver(Socket s, UI u) throws IOException {
        this.s = s;
        this.input = s.getInputStream();
        this.reader = new ObjectInputStream(input);
        this.u = u;
    }

    @Override
    public void run(){
        while(true){
            try {
                if(input.available() > 0){
                    try {
                        Message m;
                        
                        m = (Message) reader.readObject();
                        u.appendMessage(m);
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
