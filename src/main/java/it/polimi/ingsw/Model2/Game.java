package it.polimi.ingsw.Model2;

import it.polimi.ingsw.Exception.*;
import java.util.*;

/**
 * Main Class of the model, all the interaction from the controller is made with method here
 * @author elia_laz
 **/
public class Game {
    private final int idGame;
    private final int playerNum;
    private ArrayList<Player> gamer;
    private ArrayList<Island> islandTile;
    private ArrayList<CloudTile> cloudTiles;
    private Bag bag;
    private ArrayList<SpecialCard> expertCard;
    private ArrayList<Deck> assistantCard;
    private ArrayList<SchoolBoard> schoolboards;
    private MotherNature position;
    private ModelEventManager manager;
    private boolean[] professor;

    /**
     * Constructor of the Model
     * @author elia_laz
     * @param playerNum Number of Players attended to play this Game
     * @param firstPlayer Name of the player creating this Game
     * @param idGame id number of the current Game session
     **/
    public Game(int playerNum, String firstPlayer, int idGame){
        this.idGame = idGame;
        this.playerNum = playerNum;
        manager =  ModelEventManager.createModelEventManager();
        gamer = new ArrayList<Player>();
        assistantCard = new ArrayList<Deck>();
        schoolboards = new ArrayList<SchoolBoard>();

        gamer.add(new Player(firstPlayer));
        assistantCard.add(new Deck());

        //setup of the game parameters
        professor = new boolean[]{true,true,true,true,true};
        islandTile = Island.tableIslandConstructor(position);
        position = new MotherNature();
        bag = new Bag(24);
        switch (playerNum){
            case 2:
                this.setupTwo();
                break;
            case 3:
                this.setupTree();
                break;
            case 4:
                this.setupFour();
                break;
        }
        for (CloudTile c: cloudTiles) {
            c.setStudents(bag);
        }
    }

    /**
     * Service method to add the remain Players to the Game
     * @author elia_laz
     * @param playerName name of the player to be added to the current Game
     * @throws ToMuchPlayerExcetpion when playerNum is equal to gamer lenght
     **/
    public void addPlayer(String playerName) throws ToMuchPlayerExcetpion {
        if (playerNum > gamer.size()){
            throw new ToMuchPlayerExcetpion();
        }
        else{
            gamer.add(new Player(playerName));
            assistantCard.add(new Deck());
            manager.notify("playerJoin");
            if(gamer.size() == playerNum){
                manager.notify("setupStart");
            }
        }
    }

    /**
     * Service method setup the model for 2 player Game
     * @author elia_laz
     **/
    private void setupTwo(){
        cloudTiles = new ArrayList<CloudTile>();
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));

        schoolboards.add(new SchoolBoard(TowerColor.BLACK, 8, bag.getStudents(7)));
        schoolboards.add(new SchoolBoard(TowerColor.BLACK, 8, bag.getStudents(7)));
    }

    /**
     * Service method setup the model for 3 player Game
     * @author elia_laz
     **/
    private void setupTree() {
        cloudTiles = new ArrayList<CloudTile>();
        cloudTiles.add(new CloudTile(4));
        cloudTiles.add(new CloudTile(4));
        cloudTiles.add(new CloudTile(4));

        schoolboards.add(new SchoolBoard(TowerColor.BLACK, 6, bag.getStudents(9)));
        schoolboards.add(new SchoolBoard(TowerColor.WHITE, 6, bag.getStudents(9)));
        schoolboards.add(new SchoolBoard(TowerColor.GRAY, 6, bag.getStudents(9)));
    }

    /**
     * Service method setup the model for 4 player Game
     * @author elia_laz
     **/
    private void setupFour() {
        cloudTiles = new ArrayList<CloudTile>();
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));

        schoolboards.add(new SchoolBoard(TowerColor.BLACK, 6, bag.getStudents(7)));
        schoolboards.add(new SchoolBoard(bag.getStudents(7)));
        schoolboards.add(new SchoolBoard(TowerColor.WHITE, 6, bag.getStudents(7)));
        schoolboards.add(new SchoolBoard(bag.getStudents(7)));
    }

    /**
     * Service method for CloudTile to set the students on the Tile
     * @author elia_laz
     **/
    public void updateCloudTile(){
        for (CloudTile c: cloudTiles) {
            if(c.isWhitoutPhase()){
                c.setStudents(bag);
            }
        }
        manager.notify("cloudTileUpdate");
    }

    /**
     * Service method for CloudTile to bring the students on the Schoolboard
     * @author elia_laz
     **/
    public void takeCloudTile(int idPlayer, int indexCloudTile){
        for (int i=0; i<playerNum; i++) {
            if(gamer.get(i).getId() == idPlayer){
                schoolboards.get(i).moveToEntrance(cloudTiles.get(indexCloudTile).getStudents());
            }
        }
        manager.notify("cloudTileUpdate");
    }

    //TODO
    /**
     * Service method for play a Card
     * @author elia_laz
     * @param player Player that play the card
     **/
    public void playCard(Player player){
        manager.notify("deckUpdate");
    }

    /**
     * Service method for moving students from schoolboard entrance to schoolboard corridor
     * @author elia_laz
     * @param toSchoolBoard students moving to SchoolBoard
     * @param idPlayer id of the Player that make the move
     **/
    public void moveStudentsToSchoolBoard(int[] toSchoolBoard, int idPlayer){
        for (int i=0; i<playerNum; i++) {
            if(gamer.get(i).getId() == idPlayer){
                schoolboards.get(i).moveCorridor(toSchoolBoard);
                this.checkProfessorInfluence(schoolboards.get(i));
            }
        }
        manager.notify("schoolBoardUpdate");
    }

    /**
     * Service method for moving students from schoolboard entrance to island
     * @author elia_laz
     * @param students students moving from entrance
     * @param idPlayer id of the Player that make the move
     * @param island island where player move the students
     **/
    public void moveStudentsToSchoolBoard(int[] students, int idPlayer, Island island){
        for (int i=0; i<playerNum; i++) {
            if(gamer.get(i).getId() == idPlayer){
                schoolboards.get(i).removeEntrance(students);
                island.addStudents(students);
            }
        }
        manager.notify("schoolBoardUpdate");
        manager.notify("islandUpdate");
    }

    /**
     * Service method for checking influence over professor
     * @author elia_laz
     * @param schoolBoard schoolboard where the influence over professor need to be checked
     **/
    private void checkProfessorInfluence(SchoolBoard schoolBoard){
        for (SchoolBoard s: schoolboards) {
            for(int i=0; i<5; i++){
                if(s.getCorridor(i) <= schoolBoard.getCorridor(i) && s.isProfessor(i)){
                    s.setProfessor(i, false);
                    schoolBoard.setProfessor(i, true);
                }
            }
        }
        manager.notify("professorUpdate");
    }
}
