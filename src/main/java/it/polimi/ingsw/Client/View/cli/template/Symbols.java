package it.polimi.ingsw.Client.View.cli.template;

import it.polimi.ingsw.Client.View.cli.CLI;

/**
 * Enumeration for symbol used in {@link CLI}
 * @author Nikita Litovchenko
 **/
public enum Symbols {
    VERT_DIVIDER('⎪'),
    BOTTOM_LEFT_CORNER('⌊'),
    BOTTOM_RIGHT_CORNER('⌋'),
    TOP_LEFT_CORNER('⌈'),
    TOP_RIGHT_CORNER('⌉'),
    HOR_DIVIDER('—');

    private final char escape;

    Symbols(char escape) {
        this.escape = escape;
    }

    public char sign(){
        return escape;
    }

    public String getString(){
        return String.valueOf(sign());
    }

    public String toRepeat(int n){
        return String.valueOf(sign()).repeat(n);
    }

}
