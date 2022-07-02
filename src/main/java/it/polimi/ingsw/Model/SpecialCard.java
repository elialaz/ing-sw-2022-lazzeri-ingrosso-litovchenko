package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.CharacterCard.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract Class for the Character Card
 * @author elia_laz, filibertoingrosso
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
     * Getter of card price
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

    /**
     * Service method to get effect of first card
     **/
    public void GetEffect(Game game, Island island) {

    }

    /**
     * Service method to get effect of second and third card
     **/
    public void GetEffect(SchoolBoard schoolBoard, int[] studentsFromCard, int[] studentsFromEntrance) {

    }

    /**
     * Service method to get effect of fourth card
     **/
    public void GetEffect(Deck deck){

    }

    /**
     * Service method to get effect of fifth card
     **/
    public void GetEffect(int color, Island island) {

    }

    /**
     * Service method to get effect of sixth and seventh card
     **/
    public void GetEffect(Island island) {
    }

    /**
     * Service method to get effect of eighth card
     **/
    public void GetEffect(Game g, int playerId) {

    }

    /**
     * Service method to get effect of ninth card
     **/
    public void GetEffect(Game g) {

    }

    /**
     * Service method to get effect of tenth card
     **/
    public void GetEffect(ArrayList<SchoolBoard> schoolBoards, int[] students, Bag bag) {

    }

    /**
     * Service method to get effect of eleventh card
     **/
    public void GetEffect(SchoolBoard b, int[] chosenStudent, Bag bag) {

    }

    /**
     * Service method to get effect of twelfth card
     **/
    public void GetEffect(int[] chosenStudent, Island chosenIsland, Bag bag) {

    }

    /**
     * Getter of card id
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

    /**
     * Service method to get students on card
     **/
    public int[] getStudents() {
        return new int[]{0, 0, 0, 0, 0};
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
        selector.add(new FromCardToEntrance(bag));
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