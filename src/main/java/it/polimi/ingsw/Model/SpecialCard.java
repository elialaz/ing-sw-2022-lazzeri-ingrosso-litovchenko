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
     * @param num start cost of card
     **/
    public void setup(int num){
        price = num;
        neverUse = true;
        id=0;
    }

    /**
     * Getter of card price
     * @return card cost
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
     * @param game game where the effect need to be applied
     * @param island island on where the influence need to be calculated
     **/
    public void GetEffect(Game game, Island island) {

    }

    /**
     * Service method to get effect of second and third card
     * @param schoolBoard selected schoolboard
     * @param studentsFromCard students to move from card/entrance to entrance/diningRoom
     * @param studentsFromEntrance students to move from entrance/diningRoom to card/entrance
     **/
    public void GetEffect(SchoolBoard schoolBoard, int[] studentsFromCard, int[] studentsFromEntrance) {

    }

    /**
     * Service method to get effect of fourth card
     * @param deck deck of the player who has activated the effect
     **/
    public void GetEffect(Deck deck){

    }

    /**
     * Service method to get effect of fifth card
     * @param color color chosen by the player who has activated the effect
     * @param island island where influence need to be calculated
     **/
    public void GetEffect(int color, Island island) {

    }

    /**
     * Service method to get effect of sixth and seventh card
     * @param island selected island
     **/
    public void GetEffect(Island island) {
    }

    /**
     * Service method to get effect of eighth card
     * @param g game where the effect need to be applied
     * @param playerId id of the player who has activated the effect
     **/
    public void GetEffect(Game g, int playerId) {

    }

    /**
     * Service method to get effect of ninth card
     * @param g game where the effect need to be applied
     **/
    public void GetEffect(Game g) {

    }

    /**
     * Service method to get effect of tenth card
     * @param schoolBoards schoolBoard list of players
     * @param students students that need to be replaced in bag
     * @param bag bag where students are generated
     **/
    public void GetEffect(ArrayList<SchoolBoard> schoolBoards, int[] students, Bag bag) {

    }

    /**
     * Service method to get effect of eleventh card
     * @param game current game
     * @param b schoolBoard on the effect need to be applied
     * @param chosenStudent the students to move
     * @param bag bag where students are generated
     **/
    public void GetEffect( Game game, SchoolBoard b, int[] chosenStudent, Bag bag) {

    }

    /**
     * Service method to get effect of twelfth card
     * @param chosenStudent students that the player chose
     * @param chosenIsland island where the students need to be placed
     * @param bag bag where students are generated
     **/
    public void GetEffect(int[] chosenStudent, Island chosenIsland, Bag bag) {

    }

    /**
     * Getter of card id
     * @return id of the card
     **/
    public int getId(){
        return id;
    }

    /**
     * Getter method for neverUse
     * @return boolean that represents if card is already used
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
     * @return array of students on card
     **/
    public int[] getStudents() {
        return new int[]{0, 0, 0, 0, 0};
    }

    /**
     * Factory Constructor for CharacterCard Array random selected
     * @param bag bag used in current game
     * @return array of character card casually created
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