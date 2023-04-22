/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import Util.Message;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender extends Thread{
    Socket s;
    OutputStream output;
    ObjectOutputStream writer;

    public Sender(Socket s) throws IOException {
        this.s = s;
        this.output = s.getOutputStream();
        this.writer = new ObjectOutputStream(output);
    }

    @Override
    public void run() {
        Scanner terminal = new Scanner(System.in);
        try {
            
            System.out.println("Insert your username: ");
            writer.writeObject( terminal.nextLine());
            
            System.out.println("Insert Password: ");
            writer.writeObject(terminal.nextLine());
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean running = true;
        
        while (running){
           
           System.out.println("Insert the message reciver: ");
           String dest = terminal.nextLine();
           
           System.out.println("Insert the message: ");
           String mess = terminal.nextLine();
           
            try {
                writer.writeObject(new Message(mess,dest));
            } catch (IOException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
