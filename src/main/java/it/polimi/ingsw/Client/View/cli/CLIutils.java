package it.polimi.ingsw.Client.View.cli;

import it.polimi.ingsw.Client.View.cli.template.Color;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class containing all the utilities needed to create a visual CLI
 * @author Nikita Litovchenko
 **/

public class CLIutils {

    private CLIutils() {}

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
                "\nDeveloped by: "
                + "                            ┌┬┐┌─┐┌─┐┌┬┐    ┌─┐ ┌─┐                          \n"
                + "                             │ ├┤ ├─┤│││     /   ├┤                          \n"
                + "                             ┴ └─┘┴ ┴┴ ┴    └── └─┘                          \n\n"
                + Color.YELLOW + "Elia Lazzeri" + Color.DEFAULT + ", "
                + Color.WHITE + "Nikita Litovchenko"  + Color.DEFAULT + " & "
                + Color.GREEN + "Filiberto Ingrosso" + Color.DEFAULT;

    /**
     * Initialization screen, the first thing that pops up once you start the game on CLI
     **/

    public void createGameboard() {

    }
}
