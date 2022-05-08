package it.polimi.ingsw.Server;

import java.net.Socket;

public class ConnectionHandler implements Runnable{
    private Server server;
    private Socket client;

    public ConnectionHandler(Server server, Socket client){
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {

    }
}
