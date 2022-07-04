package it.polimi.ingsw.Server;

/**
 Class that associate thread of serverClient with client nickname
 **/
public class Pair {
    private String nickname;
    private ServerThread client;

    /**
     * Constructor
     * @param nickname client nickname
     * @param client new client
     **/
    public Pair(String nickname, ServerThread client){
        this.nickname = nickname;
        this.client = client;
    }

    /**
     * Getter of the client
     * @return the new client
     **/
    public ServerThread getClient(){ return client; }

    /**
     * Getter of nickname
     * @return string of nickname
     **/
    public String getNickname(){ return nickname; }

    /**
     * Service Method to set a client
     * @param client client that is going to be set
     **/
    public void setClient(ServerThread client) {
        this.client = client;
    }

    /**
     * Service Method to set client nickname
     * @param nickname nickname of client
     **/
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
