package it.polimi.ingsw.Client.View.cli;

import java.io.IOException;

public class CliMain {
    /**
     * Main Class of the CLI. A new CLI class is going to be run
     * @param args of type String[], the standard java main parameter
     */
    public static void main(String[] args) throws IOException {
        String ip;
        int port;
        boolean noConnection = true;
        CLI view = new CLI();

        while(noConnection){
            try {
                //connect to socket
                noConnection = false;
            } catch (IOException e) {
                System.out.println("Something went wrong... Try again");
            }
        }
    }
}
