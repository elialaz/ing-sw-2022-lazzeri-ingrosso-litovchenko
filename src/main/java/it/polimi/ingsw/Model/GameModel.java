package it.polimi.ingsw.Model;

import  java.util.*;

public final class GameModel {
    private final ArrayList<Player> players;
    private final GameBoard table;

    public GameModel(int nPlayers, String[] playersName) {
        this.players = new ArrayList<Player>();
        ArrayList<Integer> estr = new ArrayList<Integer>();
        estr.add(1);
        estr.add(2);
        estr.add(3);
        estr.add(4);
        ArrayList<TowerColor> estr2 = new ArrayList<TowerColor>();
        estr2.add(TowerColor.WHITE);
        estr2.add(TowerColor.BLACK);
        table = new GameBoard(nPlayers);
        switch (nPlayers) {
            case 2:
                for (int i = 0; i < nPlayers; i++) {
                    int pick2 = new Random().nextInt(estr2.size());
                    int pick = new Random().nextInt(estr.size());
                    this.players.add(new Player(playersName[i], estr.get(pick), estr2.get(pick2), table.getBag(), 7, 8));
                    estr.remove(pick);
                    estr2.remove(pick2);
                }
                break;
            case 3:
                estr2.add(TowerColor.GRAY);
                for (int i = 0; i < nPlayers; i++) {
                    int pick2 = new Random().nextInt(estr2.size());
                    int pick = new Random().nextInt(estr.size());
                    this.players.add(new Player(playersName[i], estr.get(pick), estr2.get(pick2), table.getBag(), 9, 6));
                    estr.remove(pick);
                    estr2.remove(pick2);
                }
                break;
            case 4:
                for (int i = 0; i < nPlayers; i++) {
                    int pick2 = new Random().nextInt(estr2.size());
                    int pick = new Random().nextInt(estr.size());
                    if(i % 2 == 0){
                        this.players.add(new Player(playersName[i], estr.get(pick), estr2.get(pick2), playersName[i+1], table.getBag()));
                    }
                    else{
                        this.players.add(new Player(playersName[i], estr.get(pick), playersName[i-1], table.getBag()));
                    }
                    estr.remove(pick);
                    estr2.remove(pick2);
                }
                break;
        }
    }

    public ArrayList<Effect> getExpert(){
        return table.getExpertCard();
    }

    public AssistantDeck getPlayerCard(String playerName){
        for (Player player: players) {
            if(player.getNikname().equals(playerName)){
                return player.getAssistantCards();
            }
        }
        return null;
    }

    public ArrayList<CloudTile> getCloudTile (){
        return table.getCloud();
    }

    public void updateCloudTile(){
        ArrayList<CloudTile> i = table.getCloud();
        Bag bag = table.getBag();
        for (CloudTile cloud: i) {
            cloud.setStudents(bag);
        }
    }

    public void moveStudentsFromCloudToSchoolboard(String playerName, CloudTile tile){
        for (Player player: players) {
            if(player.getNikname().equals(playerName)){
                player.bringStudents(tile);
            }
        }
    }

    public void motherNatureMove(int islandNum){
        table.moveMotherNature(islandNum);
        this.verifyInfluence();
    }

    public void moveToIslandStudent(String playerName, ArrayList<Student> students, int islandnum){
        for (Player player: players) {
            if(player.getNikname().equals(playerName)){
                player.removeStudents(students);
                IslandTile i = table.getIslandTile(islandnum);
                for (Student s: students) {
                    i.addStudentOnIsland(s);
                }
            }
        }
    }

    public void moveToSchoolboardStudent(String playerName, ArrayList<Student> students){
        for (Player player: players) {
            if(player.getNikname().equals(playerName)){
                player.removeStudents(students);
                for (Student s: students) {
                    player.addStudentOnCorridor(s, table);
                    this.checkProfessorChange(s, player);
                }
            }
        }
    }

    private void checkProfessorChange(Student s, Player player) {
        for (Player p: players) {
            if (p.hasProfessor(s.color) && !p.equals(player)){
                if (p.getCorridorStudent(s.color) < player.getCorridorStudent(s.color)){
                    player.setProfessor(s.color);
                    p.removeProfessor(s.color);
                }
            }
        }
    }

    public ArrayList<IslandTile> getIslandTiles(){
        return table.getIslandTiles();
    }

    //TODO
    private void verifyInfluence(){

        this.checkSourrindIsland(1);
    }

    //TODO
    private void checkSourrindIsland(int idTile) {

    }
}
