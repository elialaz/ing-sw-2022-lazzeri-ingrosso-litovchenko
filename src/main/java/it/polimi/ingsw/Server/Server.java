package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.ControlEventManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Main Class of the server, all the interaction from the controller is made with method here
 * @author filibertoingrosso, elia_laz
 **/
public class Server {
    private ServerSocket serverSocket;
    private int port;

    /**
     * Constructor of the Server
     * @author filibertoingrosso, elia_laz
     * @param port Number of the port for the server
     **/
    public Server(int port){
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
            System.out.println("Server avviato sulla porta " + port);
        }
        catch(IOException e){
            System.out.println("Errore");
            System.exit(-1);
        }
    }

    /**
     * Listener of the server for new connection
     * @author filibertoingrosso, elia_laz
     * @param EventManager EventManager for the server
     **/
    public void listen(ServerEventManager EventManager){
        int count=0;
        while(true){
            try {
                Socket listener = serverSocket.accept();
                System.out.println("connection Established");
                ServerThread st=new ServerThread(listener, EventManager, count);
                st.start();
            } catch (IOException e) {
                System.out.println("Errore nella connessione con il client: "+ e);
            }
        }
    }

    /**
     * Main for testing the Server
     * @author filibertoingrosso, elia_laz
     **/
    public static void main(String[] args){
        int serverPort;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Choose the number of server port (it must be between 1024 and 65535):");
            serverPort = Integer.parseInt(in.nextLine());
        } while( serverPort < 1024  ||  serverPort > 65535 );

        Server server = new Server(serverPort);

        System.out.println("Premere Ctrl + C per fermare il server");

        Thread acceptConnection = new Thread(() -> server.listen(ServerEventManager.createControlEventManager()));
        acceptConnection.start();
    }
}
