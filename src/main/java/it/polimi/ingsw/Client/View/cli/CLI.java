package it.polimi.ingsw.Client.View.cli;

import it.polimi.ingsw.Client.View.cli.CLIutils;
import it.polimi.ingsw.Client.Client;
import it.polimi.ingsw.Client.ClientEventManager;
import java.util.*;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


//    PROFESSOR('\u2742'), // ❂
//    STUDENT('\u2A37'), // ⊛
//    MOTHER_NATURE('\u26B6'), // ⚶
//    TOWER('\u265C'), // ♜
//    COIN('$');

/**
 * Main CLI class, all the created View modules for controlling the CLI will be used here
 * @author Nikita Litovchenko
 **/
public class CLI {
    private final Scanner input;
    private final PrintStream output;
    private boolean isAck;

    /**
     * CLI Constructor creates a new CLI instance
     */
    public CLI() {
        input = new Scanner(System.in);
        output = new PrintStream(System.out);
        isAck = false;
        //...

    }

    /**
     * Main Class of the CLI. A new CLI class is going to be run
     *
     * @param args of type String[], the standard java main parameter
     */
    public static void main(String[] args) {
        System.out.println(CLIutils.ERIANTYS);
        System.out.println(CLIutils.AUTHORS + "\n");
        CLI cli = new CLI();
        cli.run();
    }

    /**
     * Method clearScreen, used to flush the screen of the terminal
     */
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Ask for the player's username
     */
    public String askNickname() {
        System.out.print("Enter your username: ");
        return input.nextLine();
        //notify observer
    }

    /**
     * Ask how many people will be playing the game
     */
    public int askPlayersNumber() {
        int playerNumber;
        do {
            System.out.print("How many people will be playing? (Choose between 2 or 4 players): ");
            playerNumber = input.nextInt();
            if (playerNumber < 2 || playerNumber > 4) {
                System.out.print("You can't play with " + playerNumber + " players. Please choose 2, 3 or 4 players.");
            }
        } while (playerNumber < 2 || playerNumber > 4);
        return input.nextInt();
        //notify observer
    }

    /**
     * Manage disconnection from the server
     */
    public void DisconnectionFromGame() {
        //handle disconnection
        System.out.println("Server Connection Lost.");
        System.exit(1);
    }



}



