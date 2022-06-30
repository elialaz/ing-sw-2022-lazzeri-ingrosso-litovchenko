package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.CharacterCard.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract Class for the Character Card
 * @author elia_laz
 **/
public abstract class SpecialCard {
    private int price;
    private int id;
    private boolean neverUse;

    /**
     * Service method for setup
     **/
    public void setup(int num){
        price = num;
        neverUse = true;
        id=0;
    }

    /**
     * Service method for price get
     **/
    public int getPrice(){
        return price;
    }

    /**
     * Service method for new price set
     **/
    public void setPrice(){
        price++;
    }

    public void GetEffect(Game game, Island island) {

    }

    public void GetEffect(SchoolBoard schoolBoard, int[] studentsFromCart, int[] studentsFromEntrance) {

    }

    public void GetEffect(Deck deck){

    }

    public void GetEffect(int color, Island island) {

    }

    public void GetEffect(Island island) {
    }

    public void GetEffect(Game g, int playerId) {

    }

    public void GetEffect(Game g) {

    }

    public void GetEffect(ArrayList<SchoolBoard> schoolBoards, int[] students, Bag bag) {

    }

    public void GetEffect(SchoolBoard b, int[] chosenStudent, Bag bag) {

    }

    public void GetEffect(int[] chosenStudent, Island chosenIsland, Bag bag) {

    }

    /**
     * Service method for new price set
     **/
    public int getId(){
        return id;
    }

    /**
     * Getter method for neverUse
     **/
    public boolean isNeverUse() {
        return neverUse;
    }

    /**
     * Setter method for neverUse
     **/
    public void setNeverUse() {
        neverUse=false;
    }

    public int[] getStudents() {
        int[] prova = new int[]{0, 0, 0, 0, 0};
        return prova;
    }

    /**
     * Factory Constructor for CharacterCard Array random selected
     **/
    static public ArrayList<SpecialCard> getCharacter(Bag bag){
        ArrayList<SpecialCard> selector = new ArrayList<SpecialCard>();
        selector.add(new StudentToIsland(bag));
        selector.add(new ProfessorControl());
        selector.add(new ChosenIsland());
        selector.add(new MoveAgainMotherNature());
        selector.add(new NoEntryTilesEffect());
        selector.add(new NoCountTower());
        selector.add(new FromCartToEntrance(bag));
        selector.add(new PlusTwoEffect());
        selector.add(new NoColorCount());
        selector.add(new FromEntranceToDiningRoom());
        selector.add(new StudentToDiningRoom(bag));
        selector.add(new ReplaceStudentsInBag());
        ArrayList<SpecialCard> character = new ArrayList<SpecialCard>();
        Random extractor = new Random();
        for(int i=0; i<3; i++){
            int s = extractor.nextInt(selector.size());
            character.add(selector.get(s));
            selector.remove(s);
        }
        return character;
    }
}