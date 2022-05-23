package it.polimi.ingsw.Client.View.cli.template;

import it.polimi.ingsw.Client.View.cli.CLI;

/**
 * Enumeration to assign a background color to the symbol used in {@link CLI}
 * @author Nikita Litovchenko
 **/
public enum BgColor {
    DEFAULT(""),

    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    GOLD_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    WHITE_BACKGROUND("\u001B[47m"),

    BRIGHT_BLACK_BACKGROUND("\u001B[40;1m"),
    BRIGHT_RED_BACKGROUND("\u001B[41;1m"),
    BRIGHT_GREEN_BACKGROUND("\u001B[42;1m"),
    BRIGHT_YELLOW_BACKGROUND("\u001B[43;1m"),
    BRIGHT_GOLD_BACKGROUND("\u001B[43;1m"),
    BRIGHT_BLUE_BACKGROUND("\u001B[44;1m"),
    BRIGHT_PURPLE_BACKGROUND("\u001B[45;1m"),
    BRIGHT_CYAN_BACKGROUND("\u001B[46;1m"),
    BRIGHT_WHITE_BACKGROUND("\u001B[47;1m");

    static final String RESET = "\u001B[0m";

    private final String escape;

    BgColor(String escape) {
        this.escape = escape;
    }

    public String escape() {
        return escape;
    }
}
