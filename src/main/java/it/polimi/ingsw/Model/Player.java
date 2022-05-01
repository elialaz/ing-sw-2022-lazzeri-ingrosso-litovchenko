package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String nikname;
    private final AssistantDeck assistantCards;
    private TowerColor tower;
    private int NumCoin;
    private Boolean playInTeam;
    private final int whizard;
    private final String teammate;
    private final SchoolBoard board;

    public Player(String playerName, Integer integer, TowerColor towerColor, Bag bag, int i, int i1){
        assistantCards = new AssistantDeck();
        nikname = playerName;
        NumCoin = 1;
        whizard = integer;
        playInTeam = false;
        teammate = null;
        tower = towerColor;
        board = new SchoolBoard(bag, this, i, i1);
    }

    public Player(String playerName, Integer integer, TowerColor towerColor, String name, Bag bag){
        assistantCards = new AssistantDeck();
        nikname = playerName;
        NumCoin = 1;
        whizard = integer;
        playInTeam = true;
        teammate = name;
        tower = towerColor;
        board = new SchoolBoard(bag, this, 8, 7);
    }

    public Player(String playerName, Integer integer, String name, Bag bag){
        assistantCards = new AssistantDeck();
        nikname = playerName;
        NumCoin = 1;
        whizard = integer;
        playInTeam = true;
        teammate = name;
        board = new SchoolBoard(bag, this, 0, 7);
    }

    public AssistantDeck getAssistantCards() {
        return assistantCards;
    }

    public String getPlayInTeam() {
        return teammate;
    }

    public String getNikname() {
        return nikname;
    }

    public TowerColor getTowerColor() {
        return tower;
    }

    public TowerColor getTower() {
        board.removeTower();
        return tower;
    }

    public int getNumCoin() {
        return NumCoin;
    }

    public void addNumCoin(int num) {
        this.NumCoin += num;
    }

    public void bringStudents(CloudTile tile) {
        List<Student> b = board.getEntranceStudent();
        b.addAll(tile.getStudents());
    }

    public void removeStudents(ArrayList<Student> students) {
        List<Student> b = board.getEntranceStudent();
        b.removeAll(students);
    }

    public void addStudentOnCorridor(Student s, GameBoard b) {
        for (Student s1: board.getEntranceStudent()) {
            if (s1.color == s.color){
                board.moveToCorridor(board.getEntranceStudent().indexOf(s1), b);
                break;
            }
        }
    }

    public boolean hasProfessor(Color c) {
        return board.hasProf(c);
    }

    public void moveTower(IslandTile i) {
        board.removeTower();
        i.setTower(tower);
    }

    public void bringTower() {
        board.addTower();
    }

    public int getCorridorStudent(Color color) {
        return board.getCorridorStudent(color);
    }

    public void setProfessor(Color color) {
        board.addProfessor(color);
    }

    public void removeProfessor(Color color) {
        board.removeProfessor(color);
    }

    public SchoolBoard getBoard() {
        return board;
    }
}
