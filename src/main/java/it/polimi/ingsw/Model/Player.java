package it.polimi.ingsw.Model;


/**
 * Player Class, one for each player
 * @author elia_laz, filibertoingrosso
 **/
public class Player {
    private final int playerId;
    private final String name;
    private int coin;

    /**
     * Constructor of Player
     * @param player player name
     * @param id id of the player
     * @param coin num of coin
     **/
    public Player(String player, int id, int coin) {
        name = player;
        playerId = id;
        this.coin = coin;
    }

    /**
     * Getter of the id of the player
     * @return of the playerId
     **/
    public int getId(){
        return playerId;
    }

    /**
     * Add one coin to the player reserve
     **/
    public void addCoin(){
        coin++;
    }

    /**
     * Remove one coin from the player reserve
     **/
    public void removeCoin(){
        coin--;
    }

    /**
     * Getter of the coin player reserve
     * @return coin number
     **/
    public int getCoin(){
        return coin;
    }

    /**
     * Getter of the player name
     * @return of the player name
     **/
    public String getName(){
        return name;
    }

    /**
     * Remove coin to the player reserve
     * @param number number of coin to be removed
     **/
    public void removeCoin(int number){
        coin=coin-number;
    }
}
