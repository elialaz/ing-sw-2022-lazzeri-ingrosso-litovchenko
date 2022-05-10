package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class NoEntryTilesEffect extends SpecialCard {
    int numEntryTile;

    /**
     * Constructor
     * @author elia_laz
     **/
    public NoEntryTilesEffect(){
        setup(2);
        numEntryTile = 4;
    }

    /**
     * Method that execute the effect
     * @author elia_laz
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
     * @author elia_laz
     * @return the number of EntryTile
     **/
    public int getNumEntryTile() {
        return numEntryTile;
    }
}
