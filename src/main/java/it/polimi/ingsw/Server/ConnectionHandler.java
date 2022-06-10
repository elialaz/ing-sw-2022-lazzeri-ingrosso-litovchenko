package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.ControlEventManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Connection Handler for every Client
 * @author filibertoingrosso, elia_laz, litovn
 **/
public class ConnectionHandler{
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;
    private boolean send;
    private String data_out;
    private String header_out;
    static String data_in;
    private ControlEventManager manager;
    static boolean recived;

    /**
     * Constructor of the ConnectionHandler
     * @param manager Event manager of the Controller
     **/
    public ConnectionHandler(ControlEventManager manager){
        this.manager = manager;
        recived = false;
    }

    /**
     * Client manager for every message incoming
     * @param socket socket where listen
     **/
    public void managerClient(Socket socket){
        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response;

            while(socket.isConnected()){
                client = socket;
                if(send){
                    out.println(header_out + "////" + data_out);
                }
                response = in.readLine();
                if(response!=null){
                    String[] tokens = response.split("////");
                    //manager.notify(tokens[0]);
                    ConnectionHandler.data_in = tokens[1];
                    System.out.println(tokens[0] + " " + tokens[1]);
                    this.sendPacket("recieved", "server");
                }
            }
        }
        catch(IOException e){
            System.out.println("Error while talking with the client: "+ e);
        }
    }

    /**
     * Check if Client is connected
     * @return true or false
     **/
    public boolean connectionStatus(){
        return client.isConnected();
    }

    /**
     * Send a message
     **/
    public void sendPacket(String data, String command){
        this.data_out = data;
        this.header_out = command;
        this.send = true;
    }
}
