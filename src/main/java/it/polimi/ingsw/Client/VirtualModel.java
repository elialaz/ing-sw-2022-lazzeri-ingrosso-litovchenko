package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.TowerColor;

import java.util.ArrayList;

public class VirtualModel {
    private int gameId;
    private int playerNum;
    private ArrayList<String> player;
    private ArrayList<Integer> coinPlayer;
    private ArrayList<int[][]> deck;
    private ArrayList<Integer> lastCardUsed;
    private ArrayList<int[]> schoolboardEntrance;
    private ArrayList<Integer> schoolboardTower;
    private ArrayList<TowerColor> schoolboardColorTower;
    private ArrayList<int[]> schoolboardCorridor;
    private ArrayList<boolean[]> schoolboardProfessor;
    private boolean expert;
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
        count=0;
        while(count<playerNum){
            String[] parsed3 = parsed2[count2].split(":");
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
            lastCardUsed.add(Integer.parseInt(parsed3[2]));
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
            String[] parsed3 = parsed2[count2].split(":");
            int[] entrance = new int[]{0, 0, 0, 0, 0};
            entrance[0] = Integer.parseInt(parsed3[17 * count]);
            entrance[1] = Integer.parseInt(parsed3[1+17*count]);
            entrance[2] = Integer.parseInt(parsed3[2+17*count]);
            entrance[3] = Integer.parseInt(parsed3[3+17*count]);
            entrance[4] = Integer.parseInt(parsed3[4+17*count]);
            schoolboardEntrance.add(entrance);
            schoolboardTower.add(Integer.parseInt(parsed3[5+17*count]));
            if(parsed3[6+17*count].equals("WHITE")){
                schoolboardColorTower.add(TowerColor.WHITE);
            } else if (parsed3[6+17*count].equals("GRAY")) {
                schoolboardColorTower.add(TowerColor.GRAY);
            }
            else {
                schoolboardColorTower.add(TowerColor.BLACK);
            }
            int[] corridor = new int[]{0, 0, 0, 0, 0};
            corridor[0] = Integer.parseInt(parsed3[7+17*count]);
            corridor[1] = Integer.parseInt(parsed3[8+17*count]);
            corridor[2] = Integer.parseInt(parsed3[9+17*count]);
            corridor[3] = Integer.parseInt(parsed3[10+17*count]);
            corridor[4] = Integer.parseInt(parsed3[11+17*count]);
            schoolboardCorridor.add(corridor);
            boolean[] professor = new boolean[]{false, false, false, false, false};
            professor[0] = Boolean.parseBoolean(parsed3[12+17*count]);
            professor[1] = Boolean.parseBoolean(parsed3[13+17*count]);
            professor[2] = Boolean.parseBoolean(parsed3[14+17*count]);
            professor[3] = Boolean.parseBoolean(parsed3[15+17*count]);
            professor[4] = Boolean.parseBoolean(parsed3[16+17*count]);
            schoolboardProfessor.add(professor);
            count++;
        }
        expert = Integer.parseInt(parsed[6]) != 0;
        parsed2 = parsed[7].split("!");
        islandStudents = new ArrayList<>();
        islandColor = new ArrayList<>();
        islandTowerNum = new ArrayList<>();
        islandNoEntryTile = new ArrayList<>();
        islandNoEntryTileNum = new ArrayList<>();
        count=0;
        int cont2=7;
        if(expert){
            cont2=9;
        }
        while(count<playerNum){
            String[] parsed3 = parsed2[count2].split(":");
            int[] students = new int[]{0, 0, 0, 0, 0};
            students[0] = Integer.parseInt(parsed3[cont2 * count]);
            students[1] = Integer.parseInt(parsed3[1+cont2*count]);
            students[2] = Integer.parseInt(parsed3[2+cont2*count]);
            students[3] = Integer.parseInt(parsed3[3+cont2*count]);
            students[4] = Integer.parseInt(parsed3[4+cont2*count]);
            islandStudents.add(students);
            if(parsed3[5+cont2*count].equals("WHITE")){
                islandColor.add(TowerColor.WHITE);
            } else if (parsed3[5+cont2*count].equals("GRAY")) {
                islandColor.add(TowerColor.GRAY);
            }
            else if(parsed3[5+cont2*count].equals("BLACK")){
                islandColor.add(TowerColor.BLACK);
            }
            else {
                islandColor.add(TowerColor.NOT);
            }
            islandTowerNum.add(Integer.parseInt(parsed3[6+cont2*count]));
            if(expert){
                islandNoEntryTile.add(Boolean.parseBoolean(parsed3[7+cont2*count]));
                islandNoEntryTileNum.add(Integer.parseInt(parsed3[8+cont2*count]));
            }
            count++;
        }
        parsed2 = parsed[5].split("!");
        cloudTileStudents = new ArrayList<>();
        count=0;
        while(count<playerNum){
            String[] parsed3 = parsed2[8].split(":");
            int[] students = new int[]{0, 0, 0, 0, 0};
            students[0] = Integer.parseInt(parsed3[cont2 * count]);
            students[1] = Integer.parseInt(parsed3[1+cont2*count]);
            students[2] = Integer.parseInt(parsed3[2+cont2*count]);
            students[3] = Integer.parseInt(parsed3[3+cont2*count]);
            students[4] = Integer.parseInt(parsed3[4+cont2*count]);
            count++;
        }
        if(expert){
            expertCardId = new ArrayList<>();
            parsed2 = parsed[9].split("!");
            expertCardId.add(Integer.parseInt(parsed2[0]));
            expertCardId.add(Integer.parseInt(parsed2[1]));
            expertCardId.add(Integer.parseInt(parsed2[2]));
            positionMotherNature = Integer.parseInt(parsed[10]);
            professorOnGameboard = new boolean[]{true, true, true, true, true};
            parsed2 = parsed[11].split(":");
            for(int i=0; i<5; i++){
                professorOnGameboard[i] = Boolean.parseBoolean(parsed2[i]);
            }
            coinPile = Integer.parseInt(parsed[12]);
        }
        else{
            positionMotherNature = Integer.parseInt(parsed[9]);
            professorOnGameboard = new boolean[]{true, true, true, true, true};
            parsed2 = parsed[10].split(":");
            for(int i=0; i<5; i++){
                professorOnGameboard[i] = Boolean.parseBoolean(parsed2[i]);
            }
            coinPile = Integer.parseInt(parsed[11]);
        }
    }

    public ArrayList<String> getPlayer() {
        return player;
    }

    public ArrayList<Integer> getCoinPlayer() {
        return coinPlayer;
    }

    public ArrayList<int[][]> getDeck() {
        return deck;
    }

    public ArrayList<Integer> getLastCardUsed() {
        return lastCardUsed;
    }

    public ArrayList<int[]> getSchoolboardEntrance() {
        return schoolboardEntrance;
    }

    public ArrayList<Integer> getSchoolboardTower() {
        return schoolboardTower;
    }

    public ArrayList<TowerColor> getSchoolboardColorTower() {
        return schoolboardColorTower;
    }

    public ArrayList<int[]> getSchoolboardCorridor() {
        return schoolboardCorridor;
    }

    public ArrayList<boolean[]> getSchoolboardProfessor() {
        return schoolboardProfessor;
    }

    public boolean isExpert() {
        return expert;
    }

    public ArrayList<int[]> getIslandStudents() {
        return islandStudents;
    }

    public ArrayList<TowerColor> getIslandColor() {
        return islandColor;
    }

    public ArrayList<Integer> getIslandTowerNum() {
        return islandTowerNum;
    }

    public ArrayList<Boolean> getIslandNoEntryTile() {
        return islandNoEntryTile;
    }

    public ArrayList<Integer> getIslandNoEntryTileNum() {
        return islandNoEntryTileNum;
    }

    public ArrayList<int[]> getCloudTileStudents() {
        return cloudTileStudents;
    }

    public ArrayList<Integer> getExpertCardId() {
        return expertCardId;
    }

    public int getPositionMotherNature() {
        return positionMotherNature;
    }

    public boolean[] getProfessorOnGameboard() {
        return professorOnGameboard;
    }

    public int getCoinPile() {
        return coinPile;
    }

    public int getGameId() {
        return gameId;
    }
}
