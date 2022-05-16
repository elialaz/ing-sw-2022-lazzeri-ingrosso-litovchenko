package it.polimi.ingsw.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Main Class of the Client Connection
 * @author filibertoingrosso, elia_laz
 **/
public class Client {
    private int serverPort;
    private String serverIP;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ClientEventManager manager;
    static String response;
    static boolean use;

    /**
     * Constructor of the Client
     * @author filibertoingrosso, elia_laz
     * @param serverPort Number of the port of the server
     * @param serverIP Ip of the server
     **/
    public Client(int serverPort, String serverIP, ClientEventManager man){
        try{
            use = false;
            this.serverPort = serverPort;
            this.serverIP = serverIP;
            socket = new Socket(serverIP, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            manager = man;
        }
        catch(IOException e){
            System.out.println("Error: "+ e);
        }
    }

    /**
     * Service method for sending packet to the server
     * @author filibertoingrosso, elia_laz
     **/
    public synchronized void sendPacket(String data, String type){
        String risposta;
        try {
            out.println(type + "////" + data);
            risposta = in.readLine();
            String[] tokens = risposta.split("////");
            //manager.notify(tokens[0]);
            Client.response = tokens[1];
            System.out.println(tokens[1] + " " + tokens[0]);
        }
        catch(IOException e){
            System.out.println("Errore nell'invio: "+ e);
        }
    }

    /**
     * Service method for receive packet from the server
     * @author elia_laz
     **/
    public void recivePacket(){
        String response;
        try {
            response = in.readLine();
            String[] tokens = response.split("////");
            this.sendAck();
            //manager.notify(tokens[0]);
            Client.response = tokens[1];
            System.out.println(tokens[1] + " " + tokens[0]);
        }
        catch(IOException e){
            System.out.println("Errore nella ricezione: "+ e);
        }
    }

    /**
     * Service method for closing the socket
     * @author filibertoingrosso, elia_laz
     **/
    public void close(){
        try{
            socket.close();
        }
        catch(IOException e){
            System.out.println("Errore nella chiusura della socket: "+ e);
        }
    }

    /**
     * Service method for closing the socket
     * @author filibertoingrosso, elia_laz
     **/
    public void sendAck(){
        out.println("ack////");
    }

    public static void main(String[] args) throws InterruptedException {

        int serverPort = 21000;
        String serverIP = "localhost";
        int cont = 0;

        ClientEventManager prova = ClientEventManager.createClientEventManager();
        Client client = new Client(serverPort, serverIP, prova);

        while(cont < 10){
            TimeUnit.SECONDS.sleep(5);
            client.sendPacket("prova" + cont, "ciao");
            cont++;
        }
    }
}
