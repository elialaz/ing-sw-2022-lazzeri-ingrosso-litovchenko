package it.polimi.ingsw.Client.View.cli.template;

import it.polimi.ingsw.Client.View.cli.CLI;

/**
 * Enumeration to assign a color to the symbol used in {@link CLI}
 * @author Nikita Litovchenko
 **/
public enum Color {
    DEFAULT(""),

    YELLOW("\u001B[33m"),
    BLUE("\u001B[36m"), //cyan
    GREEN("\u001B[32m"),
    RED("\u001B[31m"),
    PURPLE("\u001B[35m"), //magenta
    WHITE("\u001B[37m"),

    BRIGHT_BLACK("\u001B[30;1m"),
    BRIGHT_RED("\u001B[31;1m"),
    BRIGHT_GREEN("\u001B[32;1m"),
    BRIGHT_YELLOW("\u001B[33;1m"),
    BRIGHT_BLUE("\u001B[34;1m"),
    BRIGHT_PURPLE("\u001B[35;1m"),
    BRIGHT_CYAN("\u001B[36m;1"),
    BRIGHT_WHITE("\u001B[37m:1");

    static final String RESET = "\u001B[0m";

    private final String escape;

    Color(String escape) {
        this.escape = escape;
    }

    public String getEscape(){
        return escape;
    }

    @Override
    public String toString() {
        return escape;
    }
}
