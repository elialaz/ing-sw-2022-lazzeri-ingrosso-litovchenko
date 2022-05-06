package it.polimi.ingsw.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String args[]) throws IOException {
        int serverPort;
        ServerSocket serverSocket = null;
        Server server = new Server();

        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Choose the number of server port (it must be between 1024 and 65535):");
            serverPort = Integer.parseInt(in.nextLine());
        } while( serverPort < 1024  ||  serverPort > 65535 );

        try {
            serverSocket = new ServerSocket( serverPort );
            System.out.print( "server created with success on port" + serverPort );
        }catch( IOException e ){
            System.out.print( "error" );
            System.exit(-1);
        }

        try {
            Socket ClientConnection = serverSocket.accept();
            ConnectionHandler connectionHandler = new ConnectionHandler(server, ClientConnection);
            Thread t = new Thread(connectionHandler);
            t.start();
        }catch (IOException e){
            System.out.print( "connection error" );
        }
    }
}
