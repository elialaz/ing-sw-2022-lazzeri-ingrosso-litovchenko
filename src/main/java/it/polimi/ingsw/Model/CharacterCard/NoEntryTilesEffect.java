package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Place a No Entry tile on an Island of your choice. The first time Mother Nature ends her movement there, put the No Entry tile back onto this card DO NOT calculate influence on that Island, or place any Towers
 * @author elia_laz, filibertoingrosso
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
    @Override
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
     * @return id of the card
     **/
    public int getId(){
        return id;
    }
}
