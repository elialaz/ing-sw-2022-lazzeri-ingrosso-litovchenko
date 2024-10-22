package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.TowerColor;

import java.util.ArrayList;

public class VirtualModel {
    private int gameId;
    private int playerNum;
    private ArrayList<String> player;
    private ArrayList<Integer> coinPlayer;
    private ArrayList<int[][]> deck;
    private int[] assistantsPlayedInTurn;
    private ArrayList<Integer> lastCardUsed;
    private ArrayList<Integer> lastMotherNatureValueCard;
    private ArrayList<int[]> schoolboardEntrance;
    private ArrayList<Integer> schoolboardTower;
    private ArrayList<TowerColor> schoolboardColorTower;
    private ArrayList<int[]> schoolboardCorridor;
    private ArrayList<boolean[]> schoolboardProfessor;
    private boolean expert;
    private int islandNum;
    private ArrayList<int[]> islandStudents;
    private ArrayList<TowerColor> islandColor;
    private ArrayList<Integer> islandTowerNum;
    private ArrayList<Boolean> islandNoEntryTile;
    private ArrayList<Integer> islandNoEntryTileNum;
    private ArrayList<int[]> cloudTileStudents;
    private ArrayList<Integer> expertCardId;
    private int positionMotherNature;
    private boolean[] professorOnGameboard;
    private int coinPile;
    private int[] expertCardPrice;
    private ArrayList<int[]> expertCardStudents;

    /**
     * Constructor of the VirtualModel and parser of the data
     * @param input All the Data from the model
     **/
    public VirtualModel(String input) {
        String[] parsed = input.split("/");
        gameId = Integer.parseInt(parsed[1]);
        playerNum = Integer.parseInt(parsed[2]);
        String[] parsed2 = parsed[3].split(":");
        player = new ArrayList<>();
        coinPlayer = new ArrayList<>();
        int count=0;
        int count2=0;
        while(count<playerNum){
            player.add(parsed2[count2]);
            coinPlayer.add(Integer.parseInt(parsed2[count2+1]));
            count2+=2;
            count++;
        }
        parsed2 = parsed[4].split("!");
        deck = new ArrayList<>();
        lastCardUsed = new ArrayList<>();
        lastMotherNatureValueCard = new ArrayList<>();
        count=0;
        while(count<playerNum){
            String[] parsed3 = parsed2[count].split(":");
            String[] parsed4 = parsed3[0].split("#");
            String[] parsed5 = parsed4[0].split("£");
            int[][] AssistantCards = new int[2][10];
            for(int j=0; j<10; j++){
                AssistantCards[0][j] = Integer.parseInt(parsed5[j]);
            }
            parsed5 = parsed4[1].split("£");
            for(int j=0; j<10; j++){
                AssistantCards[1][j] = Integer.parseInt(parsed5[j]);
            }
            deck.add(AssistantCards);
            lastCardUsed.add(Integer.parseInt(parsed3[1]));
            lastMotherNatureValueCard.add(Integer.parseInt(parsed3[2]));
            count++;
        }
        parsed2 = parsed[5].split("!");
        count=0;
        schoolboardEntrance = new ArrayList<>();
        schoolboardTower = new ArrayList<>();
        schoolboardColorTower = new ArrayList<>();
        schoolboardCorridor = new ArrayList<>();
        schoolboardProfessor = new ArrayList<>();
        while(count<playerNum){
            String[] parsed3 = parsed2[count].split(":");
            int[] entrance = new int[]{0, 0, 0, 0, 0};
            entrance[0] = Integer.parseInt(parsed3[0]);
            entrance[1] = Integer.parseInt(parsed3[1]);
            entrance[2] = Integer.parseInt(parsed3[2]);
            entrance[3] = Integer.parseInt(parsed3[3]);
            entrance[4] = Integer.parseInt(parsed3[4]);
            schoolboardEntrance.add(entrance);
            schoolboardTower.add(Integer.parseInt(parsed3[5]));
            if(parsed3[6].equals("WHITE")){
                schoolboardColorTower.add(TowerColor.WHITE);
            } else if (parsed3[6].equals("GRAY")) {
                schoolboardColorTower.add(TowerColor.GRAY);
            }
            else {
                schoolboardColorTower.add(TowerColor.BLACK);
            }
            int[] corridor = new int[]{0, 0, 0, 0, 0};
            corridor[0] = Integer.parseInt(parsed3[7]);
            corridor[1] = Integer.parseInt(parsed3[8]);
            corridor[2] = Integer.parseInt(parsed3[9]);
            corridor[3] = Integer.parseInt(parsed3[10]);
            corridor[4] = Integer.parseInt(parsed3[11]);
            schoolboardCorridor.add(corridor);
            boolean[] professor = new boolean[]{false, false, false, false, false};
            professor[0] = Boolean.parseBoolean(parsed3[12]);
            professor[1] = Boolean.parseBoolean(parsed3[13]);
            professor[2] = Boolean.parseBoolean(parsed3[14]);
            professor[3] = Boolean.parseBoolean(parsed3[15]);
            professor[4] = Boolean.parseBoolean(parsed3[16]);
            schoolboardProfessor.add(professor);
            count++;
        }
        expert = Integer.parseInt(parsed[6]) != 0;
        islandNum = Integer.parseInt(parsed[7]);
        parsed2 = parsed[8].split("!");
        islandStudents = new ArrayList<>();
        islandColor = new ArrayList<>();
        islandTowerNum = new ArrayList<>();
        islandNoEntryTile = new ArrayList<>();
        islandNoEntryTileNum = new ArrayList<>();
        count=0;
        while(count<islandNum){
            String[] parsed3 = parsed2[count].split(":");
            int[] students = new int[]{0, 0, 0, 0, 0};
            students[0] = Integer.parseInt(parsed3[0]);
            students[1] = Integer.parseInt(parsed3[1]);
            students[2] = Integer.parseInt(parsed3[2]);
            students[3] = Integer.parseInt(parsed3[3]);
            students[4] = Integer.parseInt(parsed3[4]);
            islandStudents.add(students);
            if(parsed3[5].equals("WHITE")){
                islandColor.add(TowerColor.WHITE);
            } else if (parsed3[5].equals("GRAY")) {
                islandColor.add(TowerColor.GRAY);
            }
            else if(parsed3[5].equals("BLACK")){
                islandColor.add(TowerColor.BLACK);
            }
            else {
                islandColor.add(TowerColor.NOT);
            }
            islandTowerNum.add(Integer.parseInt(parsed3[6]));
            if(expert){
                islandNoEntryTile.add(Boolean.parseBoolean(parsed3[7]));
                islandNoEntryTileNum.add(Integer.parseInt(parsed3[8]));
            }
            count++;
        }
        parsed2 = parsed[9].split("!");
        cloudTileStudents = new ArrayList<>();
        count=0;
        while(count<playerNum){
            String[] parsed3 = parsed2[count].split(":");
            int[] students = new int[]{0, 0, 0, 0, 0};
            students[0] = Integer.parseInt(parsed3[0]);
            students[1] = Integer.parseInt(parsed3[1]);
            students[2] = Integer.parseInt(parsed3[2]);
            students[3] = Integer.parseInt(parsed3[3]);
            students[4] = Integer.parseInt(parsed3[4]);
            count++;
            cloudTileStudents.add(students);
        }
        if(expert){
            expertCardId = new ArrayList<>();
            expertCardStudents = new ArrayList<>();
            int[] temp = new int[]{0, 0, 0, 0, 0};
            expertCardPrice = new int[]{0, 0, 0};
            parsed2 = parsed[10].split("!");
            count = 0;
            expertCardId.add(Integer.parseInt(parsed2[count]));
            if(Integer.parseInt(parsed2[count])==2 || Integer.parseInt(parsed2[count])==11 || Integer.parseInt(parsed2[count])==12){
                count++;
                String[] parsed3 = parsed2[count].split(":");
                for(int i=0; i<5; i++){
                    temp[i] = Integer.parseInt(parsed3[i]);
                }
                expertCardStudents.add(temp);
                count++;
                expertCardPrice[0] = Integer.parseInt(parsed2[count]);
                count++;
            } else{
                count++;
                expertCardStudents.add(new int[]{0, 0, 0, 0, 0});
                expertCardPrice[0] = Integer.parseInt(parsed2[count]);
                count++;
            }
            expertCardId.add(Integer.parseInt(parsed2[count]));
            if(Integer.parseInt(parsed2[count])==2 || Integer.parseInt(parsed2[count])==11 || Integer.parseInt(parsed2[count])==12){
                count++;
                String[] parsed3 = parsed2[count].split(":");
                for(int i=0; i<5; i++){
                    temp[i] = Integer.parseInt(parsed3[i]);
                }
                expertCardStudents.add(temp);
                count++;
                expertCardPrice[1] = Integer.parseInt(parsed2[count]);
                count++;
            } else{
                count++;
                expertCardStudents.add(new int[]{0, 0, 0, 0, 0});
                expertCardPrice[1] = Integer.parseInt(parsed2[count]);
                count++;
            }
            expertCardId.add(Integer.parseInt(parsed2[count]));
            if(Integer.parseInt(parsed2[count])==2 || Integer.parseInt(parsed2[count])==11 || Integer.parseInt(parsed2[count])==12){
                count++;
                String[] parsed3 = parsed2[count].split(":");
                for(int i=0; i<5; i++){
                    temp[i] = Integer.parseInt(parsed3[i]);
                }
                expertCardStudents.add(temp);
                count++;
                expertCardPrice[2] = Integer.parseInt(parsed2[count]);
            } else{
                count++;
                expertCardStudents.add(new int[]{0, 0, 0, 0, 0});
                expertCardPrice[2] = Integer.parseInt(parsed2[count]);
            }
            positionMotherNature = Integer.parseInt(parsed[11]);
            professorOnGameboard = new boolean[]{true, true, true, true, true};
            parsed2 = parsed[12].split(":");
            for(int i=0; i<5; i++){
                professorOnGameboard[i] = Boolean.parseBoolean(parsed2[i]);
            }
            coinPile = Integer.parseInt(parsed[13]);
            assistantsPlayedInTurn = new int[]{-1, -1, -1, -1};
            parsed2 = parsed[14].split(":");
            for(int i=0; i<4; i++){
                assistantsPlayedInTurn[i] = Integer.parseInt(parsed2[i]);
            }
        }
        else{
            positionMotherNature = Integer.parseInt(parsed[10]);
            professorOnGameboard = new boolean[]{true, true, true, true, true};
            parsed2 = parsed[11].split(":");
            for(int i=0; i<5; i++){
                professorOnGameboard[i] = Boolean.parseBoolean(parsed2[i]);
            }
            coinPile = Integer.parseInt(parsed[12]);
            assistantsPlayedInTurn = new int[]{-1, -1, -1, -1};
            parsed2 = parsed[13].split(":");
            for(int i=0; i<4; i++){
                assistantsPlayedInTurn[i] = Integer.parseInt(parsed2[i]);
            }
        }
        //expertCardPrice = new int[]{3, 1, 1, 1, 3, 3, 2, 2, 2, 3, 2, 1};
    }

    /**
     * Getter of players of the game
     * @return array of nickname of players
     **/
    public ArrayList<String> getPlayer() {
        return player;
    }

    public int getCoinPlayer(String p) {
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                return coinPlayer.get(i);
            }
        }
        return -1;
    }

    /**
     * Getter of the deck
     * @param p nickname of selected player
     * @return matrix that represent the deck of player p
     **/
    public int[][] getDeck(String p) {
        int[][] playerDeck = new int[][]{};
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                playerDeck = deck.get(i);
            }
        }
        return playerDeck;
    }

    /**
     * Getter of last card used value
     * @param p nickname of selected player
     * @return Integer value of last card played by p
     **/
    public Integer getLastCardUsed(String p) {
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                return lastCardUsed.get(i);
            }
        }
        return -1;
    }

    /**
     * Getter of students in schoolBoard entrance
     * @param p nickname of selected player
     * @return array of students in entrance
     **/
    public int[] getSchoolboardEntrance(String p) {
        int[] entrance = new int[]{};
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                entrance = schoolboardEntrance.get(i);
            }
        }
        return entrance;
    }

    /**
     * Getter of towers num on schoolBoard owned by a selected player
     * @param p nickname of selected player
     * @return num of towers on schoolBoard owned by p
     **/
    public int getSchoolboardTower(String p) {
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                return schoolboardTower.get(i);
            }
        }
        return -1;
    }

    /**
     * Getter of towers Color
     * @param p nickname of selected player
     * @return value of towers color
     **/
    public TowerColor getSchoolboardColorTower(String p) {
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                return schoolboardColorTower.get(i);
            }
        }
        return TowerColor.NOT;
    }

    /**
     * Getter of students in corridor
     * @param p nickname of selected player
     * @return array of students in each corridor of p
     **/
    public int[] getSchoolboardCorridor(String p) {
        int[] corridor = new int[]{};
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                corridor = schoolboardCorridor.get(i);
            }
        }
        return corridor;
    }

    /**
     * Getter of professor on schoolBoard of selected player
     * @param p nickname of selected player
     * @return boolean array that represent if a professor is present or not in each corridor
     **/
    public boolean[] getSchoolboardProfessor(String p) {
        boolean[] prof = new boolean[]{};
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                prof = schoolboardProfessor.get(i);
            }
        }
        return prof;
    }

    /**
     * Getter of id of player
     * @param p nickname of selected player
     * @return id of selected player p
     **/
    public int playerId(String p){
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Service method to know if the game is in expert mode
     * @return boolean value
     **/
    public boolean isExpert() {
        return expert;
    }

    /**
     * Getter of students on islands
     * @return arrayList of students on islands
     **/
    public ArrayList<int[]> getIslandStudents() {
        return islandStudents;
    }

    /**
     * Getter of towers color on islands
     * @return arrayList of towers color on islands
     **/
    public ArrayList<TowerColor> getIslandColor() {
        return islandColor;
    }

    /**
     * Getter of num of towers on islands
     * @return arrayList of num of towers on each island
     **/
    public ArrayList<Integer> getIslandTowerNum() {
        return islandTowerNum;
    }

    /**
     * Service method used to know if there is a NoEntryTile on an island
     * @param i id of island
     * @return boolean value
     **/
    public boolean getIslandNoEntryTile(int i) {
        return islandNoEntryTile.get(i);
    }

    /**
     * Getter of num of NoEntryTile on an island
     * @param i id of island
     * @return num of NoEntryTile on island i
     **/
    public int getIslandNoEntryTileNum(int i) {
        return islandNoEntryTileNum.get(i);
    }

    public ArrayList<int[]> getCloudTileStudents() {
        return cloudTileStudents;
    }

    /**
     * Getter of expert cards' id casually selected
     * @return arrayList of id of the character cards
     **/
    public ArrayList<Integer> getExpertCardId() {
        return expertCardId;
    }

    /**
     * Getter of mother nature position
     * @return position of mother nature
     **/
    public int getPositionMotherNature() {
        return positionMotherNature;
    }

    /**
     * Getter of professor on GameBoard
     * @return boolean array with professor value
     **/
    public boolean[] getProfessorOnGameBoard() {
        return professorOnGameboard;
    }

    /**
     * Getter of coin pile
     * @return num of coin in coin pile
     **/
    public int getCoinPile() {
        return coinPile;
    }

    /**
     * Getter of game id
     * @return id of the game
     **/
    public int getGameId() {
        return gameId;
    }

    /**
     * Getter of price of character cards
     * @return array of price of each character card  casually selected
     **/
    public int[] getExpertCardPrice() {
        return expertCardPrice;
    }

    /**
     * Getter of mother nature value of last card used
     * @param p nickname of player who has played last card
     * @return mother nature value of last card used by p
     **/
    public int getLastCardMotherNature(String p) {
        for(int i=0; i<playerNum; i++){
            if(player.get(i).equals(p)){
                return lastMotherNatureValueCard.get(i);
            }
        }
        return -1;
    }

    /**
     * Getter of num of players in game
     * @return value that represent num of players in the current game
     **/
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * Getter of assistant cards played in this turn
     * @return array of assistant cards just played
     **/
    public int[] getAssistantsPlayedInTurn() {
        return assistantsPlayedInTurn;
    }

    /**
     * Getter of nickname of selected player
     * @param p id of selected player
     * @return nickname of the player who has id p
     **/
    public String getPlayedById(int p){
        return player.get(p);
    }

    /**
     * Service method to know mother nature value
     * @param c value of card
     * @return mother nature value of card
     **/
    public int cardValueOf(int c){
        int[][] card = new int[][]{{1,2,3,4,5,6,7,8,9,10},{1,1,2,2,3,3,4,4,5,5}};
        return card[1][c];
    }

    /**
     * Getter of num of islands
     * @return new num of islands
     **/
    public int getIslandNum() {
        return islandNum;
    }

    /**
     * Service method to increase the price of a character card after the first activation
     * @param id id of expert card selected
     **/
    /*
    public void addOneExpertCardUsed(int id){
        if(!expertCardPriceCheck[id]){
            expertCardPrice[id]++;
            expertCardPriceCheck[id]=true;
        }
    }*/

    /**
     * Getter of students on expert cards
     * @param id num of students
     * @return array of students present on card selected
     **/
    public int[] getStudentsExpertCard(int id){
        return expertCardStudents.get(id);
    }
}
