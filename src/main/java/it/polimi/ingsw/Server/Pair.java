package it.polimi.ingsw.Server;

public class Pair {
    private String nickname;
    private ServerThread client;
    public Pair(String nickname, ServerThread client){
        this.nickname = nickname;
        this.client = client;
    }
    public ServerThread getClient(){ return client; }

    public String getNickname(){ return nickname; }

    public void setClient(ServerThread client) {
        this.client = client;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
