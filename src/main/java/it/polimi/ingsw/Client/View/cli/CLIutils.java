package it.polimi.ingsw.Client.View.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class containing all the utilities needed to create a visual CLI
 * @author litovn
 **/

public class CLIutils {

    //ANSI character color
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[36m"; //cyan
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PINK = "\u001B[35m"; //magenta
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GRAY = "\u001b[38;5;249m";
    public static final String ANSI_BLACK = "\u001b[0;30m";
    public static final String ANSI_RESET = "\u001B[0m";

    //ANSI background color
    public static final String ANSI_BRIGHT_BLACK = "\u001B[30;1m";
    public static final String ANSI_BRIGHT_RED = "\u001B[31;1m";
    public static final String ANSI_BRIGHT_GREEN = "\u001B[32;1m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[33;1m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[34;1m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[35;1m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[36m;1";
    public static final String ANSI_BRIGHT_WHITE = "\u001B[37m:1";

    //Symbols
    public static final String VERT_DIVIDER = "⎪";
    public static final String BOTTOM_LEFT_CORNER = "⌊";
    public static final String BOTTOM_RIGHT_CORNER = "⌋";
    public static final String TOP_LEFT_CORNER = "⌈";
    public static final String TOP_RIGHT_CORNER = "⌉";
    public static final String HOR_DIVIDER = "—";

    public static final String STUDENT = "\u2A37"; // ⊛
    public static final String PROF = "\u272A"; // ✪
    public static final String MOTHER_NATURE = "\u15F0"; // ᗰ
    public static final String TOWER = "\u265C"; // ♜
    public static final String COIN = "$";



    public static final String ERIANTYS =
                 " ██████████            ███                        █████                      \n"
                +"░░███░░░░░█           ░░░                        ░░███                       \n"
                +" ░███  █ ░  ████████  ████   ██████   ████████   ███████   █████ ████  █████ \n"
                +" ░██████   ░░███░░███░░███  ░░░░░███ ░░███░░███ ░░░███░   ░░███ ░███  ███░░  \n"
                +" ░███░░█    ░███ ░░░  ░███   ███████  ░███ ░███   ░███     ░███ ░███ ░░█████ \n"
                +" ░███ ░   █ ░███      ░███  ███░░███  ░███ ░███   ░███ ███ ░███ ░███  ░░░░███\n"
                +" ██████████ █████     █████░░████████ ████ █████  ░░█████  ░░███████  ██████ \n"
                +"░░░░░░░░░░ ░░░░░     ░░░░░  ░░░░░░░░ ░░░░ ░░░░░    ░░░░░    ░░░░░███ ░░░░░░  \n"
                +"                                                            ███ ░███         \n"
                +"                                                           ░░██████          \n"
                +"                                                            ░░░░░░           \n";

    public static final String AUTHORS =
                 "\nDeveloped by: \n"
                +"                            ┌┬┐┌─┐┌─┐┌┬┐    ┌─┐ ┌─┐                          \n"
                +"                             │ ├┤ ├─┤│││     /   ├┤                          \n"
                +"                             ┴ └─┘┴ ┴┴ ┴    └── └─┘                          \n"
                +CLIutils.ANSI_YELLOW + "Elia Lazzeri" + CLIutils.ANSI_RESET + ", "
                +CLIutils.ANSI_WHITE + "Nikita Litovchenko"  + CLIutils.ANSI_RESET + " & "
                +CLIutils.ANSI_GREEN + "Filiberto Ingrosso" + CLIutils.ANSI_RESET;
}
