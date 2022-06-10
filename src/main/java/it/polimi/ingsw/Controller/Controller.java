package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Event.EventReciver;
import it.polimi.ingsw.Exception.MoveNotAllowed;
import it.polimi.ingsw.Exception.PlayerNotexist;
import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Island;

//TODO sistemare ultima condizione vittoria che non chiara e partite a 4 giocatori
/**
 * Main Class of the Controller, one for each game
 * @author elia_laz
 **/
public class Controller implements EventReciver{
    private final int idGame;
    private final int playerNum;
    private Game model;
    private ControlEventManager manager;
    private PlanningPhase phaseone;
    private ActionPhase phasetwo;
    private EndPhase phasethree;
    private int nextMove;
    private String nextTurnPlayer;
    private boolean winPhase;

    /**
     * Constructor of the Controller
     * @param playerNum number of the player attended
     * @param firstPlayer first player nickname
     * @param idGame unique id of the game
     * @param expert boolean value for the expert mode activation
     **/
    public Controller(int playerNum, String firstPlayer, int idGame, boolean expert){
        this.playerNum = playerNum;
        this.idGame = idGame;
        manager = ControlEventManager.createControlEventManager();
        model = new Game(playerNum, firstPlayer, idGame, expert);

        phaseone = new PlanningPhase(manager, model, playerNum, this);
        phasetwo = new ActionPhase(manager, model, this);
        phasethree = new EndPhase(manager, model, this);

        manager.subscribe("nextmove", this);
        model.eventSubscrbe(this, "setupStart");
        nextMove = 0;
        winPhase = false;
    }

    /**
     * Service method to add more player to the current game session
     * @param player player to be added to the game
     **/
    public void addPlayer(String player) throws ToMuchPlayerExcetpion{
        model.addPlayer(player);
    }

    /**
     * Service Method to do the next move
     **/
    @Override
    public void update(String eventType) {
        switch (eventType){
            case "setupStart":
                nextMove = 1;
                phaseone.startGame();
            case "nextmove":
                switch (nextMove){
                    case 1:
                        phaseone.startGame();
                    case 2:
                        phaseone.planningMove();
                    case 3:
                        phasetwo.setup();
                    case 4:
                        phasetwo.studentsMovePhase();
                    case 5:
                        phasetwo.moveMotherNaturePhase();
                    case 6:
                        phasetwo.takeCloudTile();
                    case 7:
                        phasethree.checkWin();
                }
        }
    }

    /**
     * Service Method to decide the next player nickname to play
     * @param name nickname of the player
     **/
    public void setNextPlayerTurn(String name) {
        nextTurnPlayer = name;
    }

    /**
     * Getter of the nextTurnPlayer
     **/
    public String getNextTurnPlayer() {
        return nextTurnPlayer;
    }

    /**
     * Setter of the nextMove
     **/
    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }

    /**
     * Service Method to play an assistant Card
     * @param card card to be played
     **/
    public void playAssistantCard(int card) throws PlayerNotexist{
        int id = model.getGamerIdbynickname(nextTurnPlayer);
        if(id == -1){
            throw new PlayerNotexist();
        }
        else{
            model.playCard(id, card);
        }
    }

    /**
     * Service Method to move Students to Island
     * @param students students array
     * @param islandNum island Number
     **/
    public void moveStudentsToIsland(int[] students, int islandNum) throws PlayerNotexist{
        int id = model.getGamerIdbynickname(nextTurnPlayer);
        Island island = model.getIslandById(islandNum);
        model.moveStudentsToIsland(students, id, island);
    }

    /**
     * Service Method to move Students to SchoolBoard
     * @param students students array
     **/
    public void moveStudentsToSchoolboard(int[] students) throws PlayerNotexist{
        int id = model.getGamerIdbynickname(nextTurnPlayer);
        model.moveStudentsToSchoolBoard(students, id);
    }

    /**
     * Service Method to move Mother Nature
     * @param num number of island to move
     **/
    public void moveMotherNature(int num) throws MoveNotAllowed{
        if (model.getLastCardMovementAllowed(model.getGamerIdbynickname(nextTurnPlayer)) < num){
            throw new MoveNotAllowed();
        }
        else{
            model.moveMotherNature(num);
            if(model.isFinishTower(model.getGamerIdbynickname(nextTurnPlayer))){
                this.setNextMove(7);
                winPhase = true;
            }
        }
    }

    /**
     * Service Method to take the CloudTile
     * @param num id of the cloud tile to be taken
     **/
    public void takeCloudTile(int num){
        int id = model.getGamerIdbynickname(nextTurnPlayer);
        model.takeCloudTile(id, num);
    }

    /**
     * Getter of WinPhase
     **/
    public boolean isWinPhase() {
        return winPhase;
    }
}
