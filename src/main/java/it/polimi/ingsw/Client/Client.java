package it.polimi.ingsw.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Main Class of the Client Connection
 * @author filibertoingrosso, elia_laz, litovn
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

    public void sendPacket(String data){
        String answer;
        try {
            out.println(data);
            answer = in.readLine();
            String[] tokens = answer.split("/");
            //manager.notify(tokens[0]);
            System.out.println(answer);
        }
        catch(IOException e){
            System.out.println("Errore nell'invio: "+ e);
        }
    }

    /**
     * The Main Class of the Client.
     * @param args of type String[], the standard java main parameter
     */
    public static void main(String[] args) throws InterruptedException {

        int serverPort = 21000;
        String serverIP = "localhost";
        int cont = 0;

        ClientEventManager prova = ClientEventManager.createClientEventManager();
        Client client = new Client(serverPort, serverIP, prova);

        client.sendPacket("login");

        if(Integer.parseInt(args[0])==1){
            client.sendPacket("newGame/stominchia/2/1/true/true");
        }
        if(Integer.parseInt(args[0])==3){
            client.sendPacket("newGame/stominchia3/2/2/true/true");
        }
        else{
            client.sendPacket("loadGame/stominchia2/1");
        }

        client.sendPacket("");

    }
}


/*
public synchronized void sendPacket(String data, String type){
    String answer;
    try {
        out.println(type + "////" + data);
        answer = in.readLine();
        String[] tokens = answer.split("////");
        //manager.notify(tokens[0]);
        Client.response = tokens[1];
        System.out.println(tokens[1] + " " + tokens[0]);
    }
    catch(IOException e){
        System.out.println("Errore nell'invio: "+ e);
    }
}

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

    public void close(){
        try{
            socket.close();
        }
        catch(IOException e){
            System.out.println("Errore nella chiusura della socket: "+ e);
        }
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void sendAck(){
        out.println("ack////");
    }

 */