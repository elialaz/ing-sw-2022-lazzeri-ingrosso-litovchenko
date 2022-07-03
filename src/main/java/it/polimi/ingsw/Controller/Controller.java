package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Event.EventReceiver;
import it.polimi.ingsw.Exception.MoveNotAllowed;
import it.polimi.ingsw.Exception.PlayerNotExist;
import it.polimi.ingsw.Exception.ToMuchPlayerException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Island;
import it.polimi.ingsw.Model.SpecialCard;

import java.util.ArrayList;

//TODO sistemare ultima condizione vittoria che non chiara e partite a 4 giocatori
/**
 * Main Class of the Controller, one for each game
 * @author elia_laz
 **/
public class Controller implements EventReceiver {
    private final int idGame;
    private final int playerNum;
    private final Game model;
    private final ControlEventManager manager;
    private final PlanningPhase phaseone;
    private final ActionPhase phasetwo;
    private final EndPhase phasethree;
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
        manager.subscribe("start", this);
        manager.subscribe("nextmove", this);
        nextMove = 0;
        winPhase = false;
    }

    /**
     * Service method to add more player to the current game session
     * @param player nickname of player to be added to the game
     **/
    public void addPlayer(String player) throws ToMuchPlayerException {
        model.addPlayer(player);
    }

    /**
     * Service Method to do the next move
     **/
    @Override
    public void update(String eventType) {
        switch (eventType){
            case "start":
                nextMove = 1;
                phaseone.startGame();
                break;
            case "nextmove":
                switch (nextMove){
                    case 1:
                        phaseone.startGame();
                        break;
                    case 2:
                        phaseone.planningMove();
                        break;
                    case 3:
                        phasetwo.setup();
                        break;
                    case 4:
                        phasetwo.studentsMovePhase();
                        break;
                    case 5:
                        phasetwo.moveMotherNaturePhase();
                        break;
                    case 6:
                        phasetwo.takeCloudTile();
                        break;
                    case 7:
                        phasethree.checkWin();
                        break;
                }
                break;
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
    public void playAssistantCard(int card) throws PlayerNotExist {
        int id = model.getGamerIdByNickname(nextTurnPlayer);
        if(id == -1){
            throw new PlayerNotExist();
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
    public void moveStudentsToIsland(int[] students, int islandNum) throws PlayerNotExist {
        int id = model.getGamerIdByNickname(nextTurnPlayer);
        Island island = model.getIslandById(islandNum);
        model.moveStudentsToIsland(students, id, island);
    }

    /**
     * Service Method to move Students to SchoolBoard
     * @param students students array
     **/
    public void moveStudentsToSchoolboard(int[] students) throws PlayerNotExist {
        int id = model.getGamerIdByNickname(nextTurnPlayer);
        model.moveStudentsToSchoolBoard(students, id);
    }

    /**
     * Service Method to move Mother Nature
     * @param num number of island to move
     **/
    public void moveMotherNature(int num) throws MoveNotAllowed{
        if (model.getLastCardMovementAllowed(model.getGamerIdByNickname(nextTurnPlayer)) < num){
            throw new MoveNotAllowed();
        }
        else{
            model.moveMotherNature(num);
            if(model.isFinishTower(model.getGamerIdByNickname(nextTurnPlayer))){
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
        int id = model.getGamerIdByNickname(nextTurnPlayer);
        model.takeCloudTile(id, num);
    }

    /**
     * Service method to check if a player is in WinPhase
     * @return boolean value: true if a player is in WinPhase, otherwise false
     **/
    public boolean isWinPhase() {
        return winPhase;
    }

    /**
     * Getter of the EventManager
     * @return current EventManager
     **/
    public ControlEventManager getManager() {
        return manager;
    }

    /**
     * Getter of the Model
     * @return current game
     **/
    public Game getModel() {
        return model;
    }

    /**
     * Getter of the Expert Card
     * @return array of ExpertCard
     **/
    public ArrayList<SpecialCard> getExpertCard(){
        return model.getExpertCard();
    }
}
