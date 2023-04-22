
package Client;

import Util.*;
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;


public class ChatProjectClient {
    public static void main(String args[]) throws IOException{
        
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected to server");
        

//Si blocca qua
        /*
        InputStream input = socket.getInputStream();
        ObjectInputStream reader = new ObjectInputStream(input);
       */

        Sender s = new Sender(socket);
        s.start();
        boolean running = true;
        /*
        while (running){
            //Leggo i messaggi arrivati

            if(input.available() > 0){
                try {
                    Message m = (Message) reader.readObject();
                    System.out.println(m.getReciver()+": "+m.getContent());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }

           System.out.println("Insert the message reciver: ");
           String dest = terminal.nextLine();
           
           System.out.println("Insert the message: ");
           String mess = terminal.nextLine();
           
           writer.writeObject(new Message(mess,dest));
        }
*/
    }
}
