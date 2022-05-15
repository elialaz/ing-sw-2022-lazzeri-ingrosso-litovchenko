package it.polimi.ingsw.Model;

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
    private int coinPile;
    private boolean professorControl;
    private boolean plusTwoEffect;
    private int plusTwoEffectPlayer;

    /**
     * Constructor of the Model
     * @author elia_laz
     * @param playerNum Number of Players attended to play this Game
     * @param firstPlayer Name of the player creating this Game
     * @param idGame id number of the current Game session
     **/
    public Game(int playerNum, String firstPlayer, int idGame, boolean expert){
        this.idGame = idGame;
        this.playerNum = playerNum;
        manager =  ModelEventManager.createModelEventManager();
        gamer = new ArrayList<Player>();
        assistantCard = new ArrayList<Deck>();
        schoolboards = new ArrayList<SchoolBoard>();
        coinPile = 20;

        gamer.add(new Player(firstPlayer, 0, 1));
        assistantCard.add(new Deck());
        coinPile--;

        //setup of the game parameters
        professorControl = false;
        plusTwoEffect = false;
        professor = new boolean[]{true,true,true,true,true};
        islandTile = Island.tableIslandConstructor(position);
        position = new MotherNature();
        bag = new Bag(24);
        if(expert){
            expertCard = SpecialCard.getCharacter(bag);
        }
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
        schoolboards.get(0).setPlayer(gamer.get(0));
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
            gamer.add(new Player(playerName, gamer.size(), 1));
            schoolboards.get(gamer.size()).setPlayer(gamer.get(gamer.size()));
            assistantCard.add(new Deck());
            coinPile--;
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
     * Service method plusTwoEffect effect
     * @author elia_laz
     **/
    public void setPlusTwoEffectPlayer(int plusTwoEffectPlayer) {
        this.plusTwoEffectPlayer = plusTwoEffectPlayer;
    }

    /**
     * Service method plusTwoEffect effect
     * @author elia_laz
     **/
    public void setPlusTwoEffect(boolean plusTwoEffect) {
        this.plusTwoEffect = plusTwoEffect;
    }

    /**
     * Service method professorControl effect
     * @author elia_laz
     **/
    public void setProfessorControl(boolean professorControl) {
        this.professorControl = professorControl;
    }

    /**
     * Service method for CloudTile to set the students on the Tile
     * @author elia_laz
     **/
    public void updateCloudTile(){
        for (CloudTile c: cloudTiles) {
            if(c.isWithoutPhase()){
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

    /**
     * Service method for play a Card
     * @author elia_laz
     * @param playerId Player id that play the card
     * @param card card that be played by the player
     **/
    public void playCard(int playerId, int card){
        assistantCard.get(playerId).playCard(card);
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
    public void moveStudentsToIsland(int[] students, int idPlayer, Island island){
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
    public void checkProfessorInfluence(SchoolBoard schoolBoard){
        for (SchoolBoard s: schoolboards) {
            for(int i=0; i<5; i++){
                if(((s.getCorridor(i) < schoolBoard.getCorridor(i)) || (s.getCorridor(i) == schoolBoard.getCorridor(i)) && professorControl) && s.isProfessor(i)){
                    s.setProfessor(i, false);
                    schoolBoard.setProfessor(i, true);
                }
                else if(professor[i] && schoolBoard.getCorridor(i)!=0){
                    professor[i]=false;
                    schoolBoard.setProfessor(i, true);
                }
            }
        }
        manager.notify("professorUpdate");
    }

    /**
     * Service method for moving MotherNature
     * @author elia_laz
     * @param move move forward of this number of island
     **/
    public void moveMotherNature(int move){
        position.move(move);
        if(islandTile.get(position.getPosition()).isEntryTileMotherNature()){
            islandTile.get(position.getPosition()).noEntryTileMotherNature();
        }
        else{
            this.checkControl(islandTile.get(position.getPosition()));
            this.checkUnion(islandTile.get(position.getPosition()));
        }
        manager.notify("motherNatureUpdate");
    }

    /**
     * Service method for check control over island
     * @author elia_laz
     * @param island island where Control need to be checked
     **/
    public void checkControl(Island island){
        int playerId = this.checkInfluence(island);
        if(island.checkNotTower() && playerId != -1 && playerId != -2){
            schoolboards.get(playerId).removeTower(island.getTowerNum());
            island.setTower(island.getTowerNum(), schoolboards.get(playerId).getColor());
            manager.notify("islandControlUpdate");
        }
        else if(!island.checkNotTower() && playerId != -1 && playerId != -2){
            for (SchoolBoard s: schoolboards) {
                if(s.getColor().equals(island.colorTower())){
                    s.addTower(island.getTowerNum());
                }
            }
            schoolboards.get(playerId).removeTower(island.getTowerNum());
            island.setTower(island.getTowerNum(), schoolboards.get(playerId).getColor());
            manager.notify("islandControlUpdate");
        }
    }

    /**
     * Service method for checking influence
     * @author elia_laz
     * @param island island where the method need to chek influence
     **/
    public int checkInfluence(Island island){
        int influence = 0;
        boolean notEven = true;
        int id = -1;
        if(!island.isNoCountTower()){
            influence = island.getTowerNum();
            island.setNoCountTower(false);
        }
        for (int i=0; i<playerNum && notEven; i++) {
            int temporaryInfluence = 0;
            for(int j=0; j<5; j++){
                if(schoolboards.get(i).isProfessor(j) && island.getColorNotCount()!=j){
                    temporaryInfluence += schoolboards.get(i).getCorridor(j);
                }
                if(island.getColorNotCount()!=j){
                    island.setColorNotCount(-1);
                }
            }
            if(plusTwoEffect && i==plusTwoEffectPlayer){
                temporaryInfluence += 2;
                this.setPlusTwoEffect(false);
            }
            if(influence < temporaryInfluence){
                influence = temporaryInfluence;
                id = i;
            }
            else if(influence == temporaryInfluence){
                notEven = false;
                id = -2;
            }
        }
        return id;
    }

    /**
     * Service method for checking union
     * @author elia_laz
     * @param island island where the method need to check influence
     **/
    public void checkUnion(Island island){
        boolean unionBefore = false;
        boolean unionAfter = false;
        int idBefore = 0;
        int idAfter = 0;
        int idIsland = 0;
        for(int i=0; i<islandTile.size(); i++){
            if(islandTile.get(i).equals(island)){
                idIsland = i;
                if(i==0){
                    if(islandTile.get(islandTile.size()-1).colorTower().equals(island.colorTower())){
                        unionBefore = true;
                        idBefore = islandTile.size()-1;
                    }
                    if(islandTile.get(i+1).colorTower().equals(island.colorTower())){
                        unionAfter = true;
                        idAfter = i+1;
                    }
                }
                else if(i==islandTile.size()-1){
                    if(islandTile.get(i-1).colorTower().equals(island.colorTower())){
                        unionBefore = true;
                        idBefore = i-1;
                    }
                    if(islandTile.get(0).colorTower().equals(island.colorTower())){
                        unionAfter = true;
                        idAfter = 0;
                    }
                }
                else{
                    if(islandTile.get(i-1).colorTower().equals(island.colorTower())){
                        unionBefore = true;
                        idBefore = i-1;
                    }
                    if(islandTile.get(i+1).colorTower().equals(island.colorTower())){
                        unionAfter = true;
                        idAfter = i+1;
                    }
                }
            }
        }
        Island newIsland;
        int towerNum = island.getTowerNum();
        int[] newStudents = new int[]{0,0,0,0,0};
        for(int i=0; i<5; i++){
            newStudents[i]+= island.getStudents(i);
        }
        if(unionBefore){
            for(int i=0; i<5; i++){
                newStudents[i]+=islandTile.get(idBefore).getStudents(i);
            }
            towerNum += islandTile.get(idBefore).getTowerNum();
        }
        if(unionAfter){
            for(int i=0; i<5; i++){
                newStudents[i]+=islandTile.get(idAfter).getStudents(i);
            }
            towerNum += islandTile.get(idAfter).getTowerNum();
        }
        newIsland = new Island(newStudents, towerNum);
        newIsland.setTower(towerNum, island.colorTower());

        if(unionBefore || unionAfter){
            islandTile.set(idIsland, newIsland);
            if(unionAfter){
                islandTile.remove(idAfter);
            }
            if(unionBefore){
                islandTile.remove(idBefore);
            }
            manager.notify("unionUpdate");
        }
    }

    /**
     * Service method for playing the special card effect
     * @author elia_laz
     * @return the expert card
     **/
    public ArrayList<SpecialCard> playEffect(){
        manager.notify("characterCardUpdate");
        manager.notify("coinUpdate");
        return expertCard;
    }

    /**
     * Service method for getting the special card effect
     * @author elia_laz
     * @return the expert card
     **/
    public ArrayList<SpecialCard> getExpertCard(){
        return expertCard;
    }
}
