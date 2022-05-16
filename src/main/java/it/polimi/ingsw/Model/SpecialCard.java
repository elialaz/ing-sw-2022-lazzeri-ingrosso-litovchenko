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
    private boolean neverUse;

    /**
     * Service method for effect usage
     * @author elia_laz
     **/
    public void GetEffect() {
    }

    /**
     * Service method for setup
     * @author elia_laz
     **/
    public void setup(int num){
        price = num;
        neverUse = true;
    }

    /**
     * Service method for price get
     * @author elia_laz
     **/
    public int getPrice(){
        return price;
    }

    /**
     * Service method for new price set
     * @author elia_laz
     **/
    public void setPrice(){
        price++;
    }

    /**
     * Getter method for neverUse
     * @author elia_laz
     **/
    public boolean isNeverUse() {
        return neverUse;
    }

    /**
     * Setter method for neverUse
     * @author elia_laz
     **/
    public void setNeverUse() {
        neverUse=false;
    }

    /**
     * Factory Constructor for CharacterCard Array random selected
     * @author elia_laz
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