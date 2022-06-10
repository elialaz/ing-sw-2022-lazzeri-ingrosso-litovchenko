package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.ControlEventManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Main Class of the server, all the interaction from the controller is made with method here
 * @author filibertoingrosso, elia_laz, litovn
 **/
public class Server {
    private ServerSocket serverSocket;
    private int port;

    /**
     * Constructor of the Server
     * @param port Number of the port for the server
     **/
    public Server(int port){
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
            System.out.println("Server has started on port" + port);
        }
        catch(IOException e){
            System.out.println("Error");
            System.exit(-1);
        }
    }

    /**
     * Listener of the server for new connection
     * @param EventManager EventManager for the server
     **/
    public void listen(ServerEventManager EventManager){
        int count=0;
        while(true){
            try {
                Socket listener = serverSocket.accept();
                System.out.println("Connection Established");
                ServerThread st=new ServerThread(listener, EventManager, count);
                st.start();
            } catch (IOException e) {
                System.out.println("Error while connecting to client: "+ e);
            }
        }
    }

    /**
     * Main for testing the Server
     **/
    public static void main(String[] args){
        int serverPort;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Choose the number of server port (it must be between 1024 and 65535):");
            serverPort = Integer.parseInt(in.nextLine());
        } while( serverPort < 1024  ||  serverPort > 65535 );

        Server server = new Server(serverPort);

        System.out.println("Press 'Ctrl+C' to stop the server execution");

        Thread acceptConnection = new Thread(() -> server.listen(ServerEventManager.createControlEventManager()));
        acceptConnection.start();
    }
}
