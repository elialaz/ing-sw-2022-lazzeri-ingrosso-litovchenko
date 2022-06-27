package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class NoEntryTilesEffect extends SpecialCard {
    int numEntryTile;
    private final int id;

    /**
     * Constructor
     **/
    public NoEntryTilesEffect(){
        setup(2);
        numEntryTile = 4;
        id = 7;
    }

    /**
     * Method that execute the effect
     * @param ChosenIsland island where the NoEntryTile need to be placed
     **/
    public void GetEffect(Island ChosenIsland) {
        if(numEntryTile>0) {
            ChosenIsland.setNoEntryTile();
            numEntryTile--;
        }
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }

    /**
     * Getter for the noEntryTiles on the card
     * @return the number of EntryTile
     **/
    public int getNumEntryTile() {
        return numEntryTile;
    }

    /**
     * Getter of CardId
     **/
    public int getId(){
        return id;
    }
}
