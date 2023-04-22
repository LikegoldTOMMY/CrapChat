
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
        
        Reciver r = new Reciver(socket);
        r.start();
        Sender s = new Sender(socket);
        s.start();
        
        boolean running = true;

    }
}
