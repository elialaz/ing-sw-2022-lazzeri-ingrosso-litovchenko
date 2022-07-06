package it.polimi.ingsw.Model;

import it.polimi.ingsw.Event.EventReceiver;
import it.polimi.ingsw.Exception.ToMuchPlayerException;

import java.util.*;

/**
 * Main Class of the model, all the interaction from the controller is made with method here
 * @author elia_laz, filibertoingrosso
 **/
public class Game {
    private final int idGame;
    private int playerNum;
    private ArrayList<Player> gamer;
    private ArrayList<Island> islandTile;
    private ArrayList<CloudTile> cloudTiles;
    private Bag bag;
    private ArrayList<SpecialCard> expertCard;
    private ArrayList<Deck> assistantCard;
    private ArrayList<SchoolBoard> schoolBoards;
    private MotherNature position;
    private ModelEventManager manager;
    private boolean[] professor;
    private int coinPile;
    private boolean professorControl;
    private boolean plusTwoEffect;
    private int plusTwoEffectPlayer;
    private boolean expertMode;
    private int[] cardLastTurn;

    /**
     * Constructor of the Model
     * @param playerNum Number of Players attended to play this Game
     * @param firstPlayer Name of the player creating this Game
     * @param idGame id number of the current Game session
     * @param expert boolean value that represent if this game is in expert mode
     **/
    public Game(int playerNum, String firstPlayer, int idGame, boolean expert){
        this.idGame = idGame;
        this.playerNum = playerNum;
        manager =  ModelEventManager.createModelEventManager();
        gamer = new ArrayList<Player>();
        assistantCard = new ArrayList<Deck>();
        schoolBoards = new ArrayList<SchoolBoard>();
        coinPile = 20;

        gamer.add(new Player(firstPlayer, 0, 1));
        assistantCard.add(new Deck());
        cardLastTurn = new int[]{-1, -1, -1, -1};
        coinPile--;

        //setup of the game parameters
        professorControl = false;
        plusTwoEffect = false;
        professor = new boolean[]{true,true,true,true,true};
        position = new MotherNature();
        islandTile = Island.tableIslandConstructor(position);
        bag = new Bag(24);
        expertMode = expert;
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
        schoolBoards.get(0).setPlayer(gamer.get(0));
    }

    /**
     * Service method to add the remaining Players to the Game
     * @param playerName name of the player to be added to the current Game
     * @throws ToMuchPlayerException when playerNum is greater than gamer length
     **/
    public void addPlayer(String playerName) throws ToMuchPlayerException {
        if (gamer.size() > playerNum){
            throw new ToMuchPlayerException();
        }
        else{
            gamer.add(new Player(playerName, gamer.size(), 1));
            schoolBoards.get(gamer.size()-1).setPlayer(gamer.get(gamer.size()-1));
            assistantCard.add(new Deck());
            coinPile--;
            manager.notify("playerJoin");
            if(gamer.size() == playerNum){
                manager.notify("setupStart");
            }
        }
    }

    /**
     * Service method to set up the model for 2 player Game
     **/
    private void setupTwo(){
        cloudTiles = new ArrayList<CloudTile>();
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));

        schoolBoards.add(new SchoolBoard(TowerColor.BLACK, 8, bag.getStudents(7)));
        schoolBoards.add(new SchoolBoard(TowerColor.WHITE, 8, bag.getStudents(7)));
    }

    /**
     * Service method to set up the model for 3 player Game
     **/
    private void setupTree() {
        cloudTiles = new ArrayList<CloudTile>();
        cloudTiles.add(new CloudTile(4));
        cloudTiles.add(new CloudTile(4));
        cloudTiles.add(new CloudTile(4));

        schoolBoards.add(new SchoolBoard(TowerColor.BLACK, 6, bag.getStudents(9)));
        schoolBoards.add(new SchoolBoard(TowerColor.WHITE, 6, bag.getStudents(9)));
        schoolBoards.add(new SchoolBoard(TowerColor.GRAY, 6, bag.getStudents(9)));
    }

    /**
     * Service method to set up the model for 4 player Game
     **/
    private void setupFour() {
        cloudTiles = new ArrayList<CloudTile>();
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));
        cloudTiles.add(new CloudTile(3));

        schoolBoards.add(new SchoolBoard(TowerColor.BLACK, 6, bag.getStudents(7)));
        schoolBoards.add(new SchoolBoard(bag.getStudents(7)));
        schoolBoards.add(new SchoolBoard(TowerColor.WHITE, 6, bag.getStudents(7)));
        schoolBoards.add(new SchoolBoard(bag.getStudents(7)));
    }

    /**
     * Service method plusTwoEffect effect
     * @param plusTwoEffectPlayer represent the player who has activated the plusTwoEffect
     **/
    public void setPlusTwoEffectPlayer(int plusTwoEffectPlayer) {
        this.plusTwoEffectPlayer = plusTwoEffectPlayer;
    }

    /**
     * Service method to know which player want to use the plusTwoEffect
     * @return player who has activated the plusTwoEffect
     **/
    public int getPlusTwoEffectPlayer() { return this.plusTwoEffectPlayer; }

    /**
     * Service method to set plusTwoEffect effect
     * @param plusTwoEffect boolean that set the plusTwoEffect
     **/
    public void setPlusTwoEffect(boolean plusTwoEffect) {
        this.plusTwoEffect = plusTwoEffect;
    }

    /**
     * Service method to check plusTwoEffect
     * @return boolean that represents if plusTwoEffect is active
     **/
    public boolean isPlusTwoEffect() { return plusTwoEffect; }

    /**
     * Service method to set professorControl effect
     * @param professorControl boolean that set the professorControlEffect
     **/
    public void setProfessorControl(boolean professorControl) {
        this.professorControl = professorControl;
    }

    /**
     * Service method to check professorControl effect
     * @return boolean that represents if professorControlEffect is active
     **/
    public boolean isProfessorControl() { return professorControl; }

    /**
     * Service method for CloudTile to set the students on the Tile
     **/
    public void updateCloudTile(){
        for (CloudTile c: cloudTiles) {
            if(c.isWithoutPhase()){
                c.setStudents(bag);
            }
        }
        manager.notify("update");
    }

    /**
     * Service method for CloudTile to bring the students on the Schoolboard
     * @param idPlayer id of player who chose the cloudTile
     * @param indexCloudTile id of cloudTile chosen
     **/
    public void takeCloudTile(int idPlayer, int indexCloudTile){
        for (int i=0; i<gamer.size(); i++) {
            if(gamer.get(i).getId() == idPlayer){
                schoolBoards.get(i).moveToEntrance(cloudTiles.get(indexCloudTile).getStudents());
            }
        }
        manager.notify("update");
    }

    /**
     * Service method for play a Card
     * @param playerId id of th player who plays the card
     * @param card card that be played by the player
     **/
    public void playCard(int playerId, int card){
        assistantCard.get(playerId).playCard(card);
        cardLastTurn[playerId]=card;
        manager.notify("update");
    }

    /**
     * Service method for moving students from schoolboard entrance to schoolboard corridor
     * @param toSchoolBoard students moving to SchoolBoard
     * @param idPlayer id of the Player who makes the move
     **/
    public void moveStudentsToSchoolBoard(int[] toSchoolBoard, int idPlayer){
        for (int i=0; i<gamer.size(); i++) {
            if(gamer.get(i).getId() == idPlayer){
                schoolBoards.get(i).moveCorridor(toSchoolBoard);
                this.checkProfessorInfluence(schoolBoards.get(i));
            }
        }
        manager.notify("update");
    }

    /**
     * Service method for moving students from schoolboard entrance to island
     * @param students students moving from entrance
     * @param idPlayer id of the Player who makes the move
     * @param island island where player moves the students
     **/
    public void moveStudentsToIsland(int[] students, int idPlayer, Island island){
        for (int i=0; i<gamer.size(); i++) {
            if(gamer.get(i).getId() == idPlayer){
                schoolBoards.get(i).removeEntrance(students);
                island.addStudents(students);
            }
        }
        manager.notify("update");
        manager.notify("update");
    }

    /**
     * Service method for checking influence over professor
     * @param schoolBoard schoolboard where the influence over professor need to be checked
     **/
    public void checkProfessorInfluence(SchoolBoard schoolBoard){
        for (SchoolBoard s: schoolBoards) {
            for(int i=0; i<5; i++){
                if(((s.getCorridor(i) < schoolBoard.getCorridor(i)) || (s.getCorridor(i) == schoolBoard.getCorridor(i)) && professorControl) && s.isProfessor(i) && !s.equals(schoolBoard)){
                    s.setProfessor(i, false);
                    schoolBoard.setProfessor(i, true);
                }
                else if(professor[i] && schoolBoard.getCorridor(i)!=0){
                    professor[i]=false;
                    schoolBoard.setProfessor(i, true);
                }
            }
        }
        manager.notify("update");
    }

    /**
     * Service method for moving MotherNature
     * @param move move forward of this number of island
     **/
    public void moveMotherNature(int move){
        position.move(move, islandTile);
        if(islandTile.get(position.getPosition()).isEntryTileMotherNature()){
            islandTile.get(position.getPosition()).noEntryTileMotherNature();
        }
        else{
            this.checkControl(islandTile.get(position.getPosition()));
            this.checkUnion(islandTile.get(position.getPosition()));
        }
        manager.notify("update");
    }

    /**
     * Service method for check control over island
     * @param island island where Control need to be checked
     **/
    public void checkControl(Island island){
        int playerId = this.checkInfluence(island);
        if(island.checkNotTower() && playerId != -1 && playerId != -2){
            schoolBoards.get(playerId).removeTower(1);
            island.setTower(1, schoolBoards.get(playerId).getColor());
            manager.notify("update");
        }
        else if(!island.checkNotTower() && playerId != -1 && playerId != -2){
            for (SchoolBoard s: schoolBoards) {
                if(s.getColor().equals(island.colorTower())){
                    s.addTower(island.getTowerNum());
                }
            }
            schoolBoards.get(playerId).removeTower(island.getTowerNum());
            island.setTower(island.getTowerNum(), schoolBoards.get(playerId).getColor());
            manager.notify("update");
        }
    }

    /**
     * Service method for checking influence
     * @param island island where the method need to chek influence
     * @return id of player who has greater influence on that island
     **/
    public int checkInfluence(Island island){
        int influence = 0;
        int id = -1;
        int[] studentsOnIsland = island.getStudents();
        for (int i = 0; i<playerNum; i++) {
            int temporaryInfluence = 0;
            if(!island.isNoCountTower()) {
                if (island.colorTower().equals(schoolBoards.get(i).getColor())) {
                    temporaryInfluence += island.getTowerNum();
                }
            }
            for(int j=0; j<5; j++){
                if((schoolBoards.get(i).isProfessor(j)) && island.getColorNotCount()!=j){
                    if(studentsOnIsland[j]!=0){
                        temporaryInfluence += studentsOnIsland[j];
                    }
                }
            }
            if(plusTwoEffect && i==plusTwoEffectPlayer){
                temporaryInfluence += 2;
                this.setPlusTwoEffect(false);
            }
            if(influence < temporaryInfluence && temporaryInfluence!=0){
                influence = temporaryInfluence;
                id = i;
            }
            else if(influence == temporaryInfluence && influence!=0){
                id = -2;
            }
        }
        if(island.getColorNotCount() != -1){
            island.setColorNotCount(-1);
        }
        island.setNoCountTower(false);
        return id;
    }

    /**
     * Service method for checking union
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
                    if(islandTile.get(islandTile.size()-1).colorTower().equals(island.colorTower()) && !island.colorTower().equals(TowerColor.NOT)){
                        unionBefore = true;
                        idBefore = islandTile.size()-1;
                    }
                    if(islandTile.get(i+1).colorTower().equals(island.colorTower()) && !island.colorTower().equals(TowerColor.NOT)){
                        unionAfter = true;
                        idAfter = i+1;
                    }
                }
                else if(i==islandTile.size()-1){
                    if(islandTile.get(i-1).colorTower().equals(island.colorTower()) && !island.colorTower().equals(TowerColor.NOT)){
                        unionBefore = true;
                        idBefore = i-1;
                    }
                    if(islandTile.get(0).colorTower().equals(island.colorTower()) && !island.colorTower().equals(TowerColor.NOT)){
                        unionAfter = true;
                        idAfter = 0;
                    }
                }
                else{
                    if(islandTile.get(i-1).colorTower().equals(island.colorTower()) && !island.colorTower().equals(TowerColor.NOT)){
                        unionBefore = true;
                        idBefore = i-1;
                    }
                    if(islandTile.get(i+1).colorTower().equals(island.colorTower()) && !island.colorTower().equals(TowerColor.NOT)){
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
            if (idIsland == 0 && unionAfter && unionBefore){
                islandTile.remove(idBefore);
                islandTile.remove(idAfter);
            }
            else{
                islandTile.set(idIsland, newIsland);
                if(unionAfter){
                    islandTile.remove(idAfter);
                }
                if(unionBefore){
                    islandTile.remove(idBefore);
                    position.move(-1, islandTile);
                }
            }
            manager.notify("update");
        }
    }

    /**
     * Service method for getting the special card effect
     * @return the expert cards
     **/
    public ArrayList<SpecialCard> getExpertCard(){
        return expertCard;
    }

    /**
     * Service method for subscribe to a particular event
     * @param subscriber listener for the events
     * @param events event type
     **/
    public void eventSubscribe(EventReceiver subscriber, String events) {
        manager.subscribe(events, subscriber);
    }

    /**
     * Getter of the player nickname
     * @param id number of the player in the arraylist
     * @return String nickname of the player selected
     **/
    public String getGamerById(int id) {
        return gamer.get(id).getName();
    }

    /**
     * Getter of the player id
     * @param nickname nickname of the player to get the id
     * @return int id of the player selected
     **/
    public int getGamerIdByNickname(String nickname){
        for (Player p: gamer) {
            if(p.getName().equals(nickname)){
                return p.getId();
            }
        }
        return -1;
    }

    /**
     * Service Method to get the player order
     * @return int array with the player order of the next turn based on player id
     **/
    public ArrayList<Integer> getPlanningPhaseOrder(){
        ArrayList<Integer> order = new ArrayList<Integer>();
        ArrayList<Integer> value = new ArrayList<Integer>();
        boolean inserted=false;
        order.add(0);
        value.add(assistantCard.get(0).getLastCardValue());
        for(int i=1; i<gamer.size(); i++){
            inserted=false;
            for(int j=0; j<(order.size()) && !inserted; j++){
                if(assistantCard.get(i).getLastCardValue() < value.get(j)){
                    value.add(j, assistantCard.get(i).getLastCardValue());
                    order.add(j, i);
                    inserted=true;
                }
            }
            if(!inserted){
                value.add(assistantCard.get(i).getLastCardValue());
                order.add(i);
            }
        }
        return order;
    }

    /**
     * Service Method to get the Island by the id
     * @param id id of the island
     * @return Island selected
     **/
    public Island getIslandById(int id){
        return islandTile.get(id);
    }

    /**
     * Service Method to get last card used value by a player
     * @param id id of the player
     * @return int movement allowed
     **/
    public int getLastCardMovementAllowed(int id) {
        return assistantCard.get(id).getLastMotherNatureValue();
    }

    /**
     * Service Method to get value of the last used card by a player
     * @param id id of the player
     * @return last card used value
     **/
    public int getLastCardValue(int id) {
        return assistantCard.get(id).getLastCardValue();
    }

    /**
     * Service Method to check if the player has ended his towers
     * @param id id of the player
     * @return boolean that represents if he really finishes his towers
     **/
    public boolean isFinishTower(int id) {
        return (schoolBoards.get(id).getTower() == 0);
    }

    /**
     * Service Method to check island number
     * @return num of island
     **/
    public int islandNUm() {
        return islandTile.size();
    }

    /**
     * Service Method to check which player get the big number of tower on the gameBoard
     * @return nickname of the player
     **/
    public String checkTowerNum() {
        int id = 0;
        int num = schoolBoards.get(0).getTower();
        for(int i = 1; i< schoolBoards.size(); i++){
            if(schoolBoards.get(i).getTower() > num){
                num = schoolBoards.get(i).getTower();
                id = i;
            }
            if(schoolBoards.get(i).getTower() == num){
                if(schoolBoards.get(i).getProfessor() > schoolBoards.get(id).getProfessor()){
                    id = i;
                }
            }
        }
        return this.getGamerById(id);
    }

    /**
     * Getter of bag
     * @return bag of current game
     **/
    public Bag getBag() {
        return bag;
    }

    /**
     * Getter of schoolBoards
     * @return array of player's schoolBoards
     **/
    public ArrayList<SchoolBoard> getSchoolBoards() { return schoolBoards; }

    /**
     * Getter of the ModelManager
     * @return ModelEventManager
     **/
    public ModelEventManager getManager() { return manager; }

    /**
     * Getter of gamers
     * @return array of players who are playing this game
     **/
    public ArrayList<Player> getGamer() {
        return gamer;
    }

    /**
     * Getter of cloudTiles
     * @return array of cloudTile
     **/
    public ArrayList<CloudTile> getCloudTiles() {
        return cloudTiles;
    }

    /**
     * Getter of islandTiles
     * @return array of island
     **/
    public ArrayList<Island> getIslandTile() {
        return islandTile;
    }

    @Override
    public String toString() {
        String text = "updateGameBoard" + "/" + idGame + "/" + playerNum + "/";
        int temp = 0;
        for (Player p: gamer) {
            text = text + p.getName() + ":";
            text = text + p.getCoin() + ":";
            temp++;
        }
        text = text + "/";
        temp = 0;
        for (Deck d: assistantCard) {
            text = text + matrixToString(d.getAssistantCardDeck()) + ":" + d.getLastCardValue() + ":" + d.getLastMotherNatureValue() + "!";
            temp++;
        }
        temp = 0;
        text = text + "/";
        for (SchoolBoard b: schoolBoards) {
            text = text + arrayToString(b.getEntranceStudents()) + ":" + b.getTower() + ":" + b.getColor().toString() + ":" + b.getCorridor(0) + ":" + b.getCorridor(1) + ":" + b.getCorridor(2) + ":" + b.getCorridor(3) + ":" + b.getCorridor(4) + ":" + b.isProfessor(0) + ":" + b.isProfessor(1) + ":" + b.isProfessor(2) + ":" + b.isProfessor(3) + ":" + b.isProfessor(4) + "!";
            temp++;
        }
        temp = 0;
        text = text + "/";
        if(expertMode){
            text = text + "1/";
        }
        else{
            text = text + "0/";
        }
        text = text + islandTile.size() + "/";
        for (Island i: islandTile) {
            text = text + arrayToString(i.getStudents()) + ":";
            text = text + i.colorTower().toString() + ":" + i.getTowerNum() + ":";
            if(expertMode){
                text = text + i.isEntryTileMotherNature() + ":" + i.getNoEntryTile() + "!";
            }
            else{
                text = text + "!";
            }
            temp++;
        }
        temp = 0;
        text = text + "/";
        for (CloudTile c: cloudTiles) {
            text = text + arrayToString(c.getStudents2()) + "!";
            temp++;
        }
        text = text + "/";
        if(expertMode){
            for (SpecialCard s: expertCard) {
                if(s.getId()==2 || s.getId()==12 || s.getId()==11){
                    text = text + s.getId() + "!" + arrayToString(s.getStudents()) + "!";
                }
                else {
                    text = text + s.getId() + "!";
                }
            }
            text = text + "/";
        }
        text = text + position.getPosition() + "/";
        text = text + arrayToString(professor) + "/";
        text = text + coinPile;
        text = text + "/" + arrayToString(cardLastTurn);
        return text;
    }

    /**
     * Service method to convert an array to a string
     * @param input boolean array that need to be converted
     * @return string converted
     **/
    private String arrayToString(boolean[] input){
        String exit = "";
        for(int i=0; i<input.length; i++){
            if(i==(input.length-1)){
                exit = exit + String.valueOf(input[i]);
            }
            else{
                exit = exit + String.valueOf(input[i]) + ":";
            }
        }
        return exit;
    }

    /**
     * Service method to convert an array to a string
     * @param input array that need to be converted
     * @return string converted
     **/
    private String arrayToString(int[] input){
        String exit = "";
        for(int i=0; i<input.length; i++){
            if(i==(input.length-1)){
                exit = exit + String.valueOf(input[i]);
            }
            else{
                exit = exit + String.valueOf(input[i]) + ":";
            }
        }
        return exit;
    }

    /**
     * Service method to convert a matrix to a string
     * @param input matrix that need to be converted
     * @return string converted
     **/
    private String matrixToString(int[][] input){
        String exit = "";
        for(int i=0; i<2; i++){
            for(int j=0; j<10; j++){
                if(j==9){
                    exit = exit + String.valueOf(input[i][j]);
                }
                else{
                    exit = exit + String.valueOf(input[i][j]) + "£";
                }
            }
            exit = exit + "#";
        }
        return exit;
    }

    /**
     * Service method to reset card
     **/
    public void resetCardLastTurn(){
        cardLastTurn = new int[]{-1, -1, -1, -1};
    }

    /**
     * Service method to remove coin from players
     * @param number num of coin that need to be removed
     * @param nickname nickname of the player from whom remove coins
     **/
    public void removeCoin(int number, String nickname){
        for (Player p: gamer) {
            if(p.getName().equals(nickname)){
                p.removeCoin(number);
            }
        }
    }

    /**
     * Service method to get deck
     * @param p id of player
     * @return deck of player p
     **/
    public Deck getDeck(int p) {
        return assistantCard.get(p);
    }

    /**
     * Getter of id game
     * @return id game
     **/
    public int getIdGame() {
        return idGame;
    }

    /**
     * Getter of the deck of all players
     * @return array of all decks
     **/
    public ArrayList<Deck> getAssistantCard() {
        return assistantCard;
    }

    /**
     * Service method to know if this game is playing in expert mode
     * @return boolean that represents expert mode
     **/
    public boolean isExpertMode() {
        return expertMode;
    }

    /**
     * Getter of mother nature position
     * @return int represent pos of mother nature
     **/
    public MotherNature getPosition() {
        return position;
    }

    /**
     * Getter of professors
     * @return boolean array that represents if professor of each color is present or not
     **/
    public boolean[] getProfessor() {
        return professor;
    }

    /**
     * Getter of coin pile
     * @return number of coin
     **/
    public int getCoinPile() {
        return coinPile;
    }

    /**
     * Getter of cards played on last turn
     * @return array of cards
     **/
    public int[] getCardLastTurn() {
        return cardLastTurn;
    }

    /**
     * Getter of num of player
     * @return num of player who are playing the current game
     **/
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * Service method to check if card number is valid
     * @return boolean that represents if card is valid or not
     **/
    public boolean checkCardNumber() {
        boolean check = true;
        for (Deck d: assistantCard) {
            for(int i=0; i<d.getAssistantCardDeck()[0].length; i++){
                if(d.getAssistantCardDeck()[0][i]!=-1){
                    check = false;
                }
            }
            if(check){
                return check;
            }
            check = false;
        }
        return check;
    }

    /**
     * Service method to check which player has the most towers
     * @return name of that player
     **/
    public String checkMuchTower() {
        int high=100;
        String name = "";
        int professor = -1;
        for (SchoolBoard s: schoolBoards) {
            if(s.getTower() < high){
                high = s.getTower();
                name = s.getPlayer().getName();
                professor = s.getProfessor();
            }
            if(high == s.getTower()){
                if(s.getProfessor() > professor){
                    high = s.getTower();
                    name = s.getPlayer().getName();
                    professor = s.getProfessor();
                }
            }
        }
        return name;
    }

    /**
     * Service method to check in the bad has students in it
     * @return boolean
     */
    public boolean checkEmptyBag() {
        boolean empty = true;
        int[] noStudents = bag.getStudents(1);
        for (int i=0; i<5; i++) {
            if (noStudents[i] != 0) {
                empty = false;
                bag.addStudents(noStudents);
                break;
            }
        }
        return empty;
    }

}


