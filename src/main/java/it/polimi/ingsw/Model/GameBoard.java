package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class GameBoard {
    private Bag bag;
    private ArrayList<CloudTile> cloudTiles;
    private ArrayList<IslandTile> islandTiles;
    private final ArrayList<Effect> expertCard;
    private int motherNaturePosition;
    private ArrayList<Color> professor;
    private int coinPile;
    private int noEntryTile;

    public GameBoard(int nPlayers) {
        ArrayList<Student> start = new ArrayList<Student>();
        start.add(new Student(Color.BLUE));
        start.add(new Student(Color.BLUE));
        start.add(new Student(Color.YELLOW));
        start.add(new Student(Color.YELLOW));
        start.add(new Student(Color.PINK));
        start.add(new Student(Color.PINK));
        start.add(new Student(Color.GREEN));
        start.add(new Student(Color.GREEN));
        start.add(new Student(Color.RED));
        start.add(new Student(Color.RED));
        ArrayList<Effect> effectsStart = new ArrayList<Effect>();
        effectsStart.add(new StudentToIsland(bag));
        effectsStart.add(new ProfessorControl());
        effectsStart.add(new ChosenIsland());
        effectsStart.add(new MoveAgainMotherNature());
        effectsStart.add(new NoEntryTilesEffect());
        effectsStart.add(new NoCountTower());
        effectsStart.add(new FromCartToEntrance(bag));
        effectsStart.add(new PlusTwoEffect());
        effectsStart.add(new NoColorCount());
        effectsStart.add(new FromEntranceToDiningRoom());
        effectsStart.add(new StudentToDiningRoom(bag));
        effectsStart.add(new ReplaceStudentsInBag());
        islandTiles = new ArrayList<IslandTile>();
        expertCard = new ArrayList<Effect>();
        for(int i = 0; i<3; i++){
            int num = getRandomNumber(0, effectsStart.size()-1);
            expertCard.add(effectsStart.get(num));
            effectsStart.remove(num);
        }
        for(int i = 0; i<12; i++){
            int num = getRandomNumber(0, start.size()-1);
            if (i == 0){
                islandTiles.add(new IslandTile(i, true));
                start.remove(num);
            }
            else if (i == 6){
                islandTiles.add(new IslandTile(i, false));
                start.remove(num);
            }
            else{
                islandTiles.add(new IslandTile(i, start.get(num), false));
                start.remove(num);
            }
        }
        motherNaturePosition = 0;
        professor = new ArrayList<Color>();
        professor.add(Color.GREEN);
        professor.add(Color.YELLOW);
        professor.add(Color.PINK);
        professor.add(Color.BLUE);
        professor.add(Color.RED);
        coinPile = 20-nPlayers;
        noEntryTile = 4;
        switch (nPlayers){
            case 2:
                cloudTiles = new ArrayList<CloudTile>();
                cloudTiles.add(new CloudTile(3));
                cloudTiles.add(new CloudTile(3));
            case 3:
                cloudTiles = new ArrayList<CloudTile>();
                cloudTiles.add(new CloudTile(4));
                cloudTiles.add(new CloudTile(4));
                cloudTiles.add(new CloudTile(4));
            case 4:
                cloudTiles = new ArrayList<CloudTile>();
                cloudTiles.add(new CloudTile(3));
                cloudTiles.add(new CloudTile(3));
                cloudTiles.add(new CloudTile(3));
                cloudTiles.add(new CloudTile(3));
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public ArrayList<CloudTile> getCloud() {
        return cloudTiles;
    }

    public Bag getBag() {
        return bag;
    }

    public ArrayList<Effect> getExpertCard() {
        return expertCard;
    }

    public ArrayList<IslandTile> getIslandTiles(){
        return islandTiles;
    }
    public void moveMotherNature(int island){
        motherNaturePosition = island;
    }

    public IslandTile getIslandTile(int islandnum) {
        return islandTiles.get(islandnum);
    }

    public int getMotherNaturePosition() {
        return motherNaturePosition;
    }

    public void removeCoin(int num) {
        coinPile = coinPile-num;
    }

    public void addCoinPile(int num) {
        coinPile = coinPile + num;
    }
}
