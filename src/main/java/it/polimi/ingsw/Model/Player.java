package it.polimi.ingsw.Model;


/**
 * Player Class, one for each player
 * @author elia_laz
 **/
public class Player {
    private final int playerId;
    private String name;
    private int coin;

    /**
     * Constructor of Player
     * @author elia_laz
     * @param player player name
     * @param id id of the player
     **/
    public Player(String player, int id, int coin) {
        name = player;
        playerId = id;
        this.coin = coin;
    }

    /**
     * Getter of the id of the player
     * @author elia_laz
     * @return of the playerId
     **/
    public int getId(){
        return playerId;
    }

    /**
     * Add one coin to the player reserve
     * @author elia_laz
     **/
    public void addCoin(){
        coin++;
    }

    /**
     * Remove one coin to the player reserve
     * @author elia_laz
     **/
    public void removeCoin(){
        coin--;
    }

    /**
     * Getter of the coin player reserve
     * @author elia_laz
     * @return coin number
     **/
    public int getCoin(){
        return coin;
    }

    /**
     * Getter of the player name
     * @author elia_laz
     * @return of the player name
     **/
    public String getName(){
        return name;
    }
}
