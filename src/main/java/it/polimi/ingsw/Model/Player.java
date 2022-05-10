package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String nickname;
    private final AssistantDeck assistantCards;
    private TowerColor tower;
    private int NumCoin;
    private Boolean playInTeam;
    private final int wizard;
    private final String teammate;
    private final SchoolBoard board;

    private boolean bonusInfluenceEffect;

    public Player(String playerName, Integer integer, TowerColor towerColor, Bag bag, int i, int i1){
        assistantCards = new AssistantDeck();
        nickname = playerName;
        NumCoin = 1;
        wizard = integer;
        playInTeam = false;
        teammate = null;
        tower = towerColor;
        board = new SchoolBoard(bag, this, i, i1);
        bonusInfluenceEffect = false;
    }

    public Player(String playerName, Integer integer, TowerColor towerColor, String name, Bag bag){
        assistantCards = new AssistantDeck();
        nickname = playerName;
        NumCoin = 1;
        wizard = integer;
        playInTeam = true;
        teammate = name;
        tower = towerColor;
        board = new SchoolBoard(bag, this, 8, 7);
        bonusInfluenceEffect = false;
    }

    public Player(String playerName, Integer integer, String name, Bag bag){
        assistantCards = new AssistantDeck();
        nickname = playerName;
        NumCoin = 1;
        wizard = integer;
        playInTeam = true;
        teammate = name;
        board = new SchoolBoard(bag, this, 0, 7);
        bonusInfluenceEffect = false;
    }

    public AssistantDeck getAssistantCards() {
        return assistantCards;
    }

    public String getPlayInTeam() {
        return teammate;
    }

    public String getNickname() {
        return nickname;
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

    public void removeCoin(int num){
        NumCoin = NumCoin- num;
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

    public boolean hasbonus() {
        return this.bonusInfluenceEffect;
    }

    public void removeBonus() {
        bonusInfluenceEffect = false;
    }

    public void setBonusInfluenceEffect() {
        this.bonusInfluenceEffect = true;
    }
}
