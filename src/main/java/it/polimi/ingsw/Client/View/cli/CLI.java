package it.polimi.ingsw.Client.View.cli;

import it.polimi.ingsw.Model.Deck;
import it.polimi.ingsw.Model.PawnColor;

import java.util.*;
import java.util.Scanner;

/**
 * CLI class, all the created View modules for controlling the CLI will be used here
 * @author litovn
 **/
public class CLI {
    private static int numPlayers;
    private static ArrayList<ArrayList<PawnColor>> cloud;
    private static ArrayList<Integer> cardIndex;

    protected Scanner scan = new Scanner(System.in);

    /**
     * Used to flush the screen of the terminal
     */
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Reads a String inserted by the user. An empty string is not accepted.
     * @return str of type String
     */
    public String ReadStringInput() {
        String str;
        do {
            System.out.print(">");
            str = scan.nextLine();
            if(str.equals(""))
                System.out.println("Empty string is not valid. Write something.");
        } while(str.equals(""));
        return str.toLowerCase();
    }

    /**
     * Reads a Integer inserted by the user which has to be between a min and a max.
     * @param min of type int
     * @param max of type int
     * @return num of type int
     */
    private int ReadIntInput(int min,int max) {
        int num = -10;
        do {
            System.out.print(">");
            if(scan.hasNextInt()) {
                num = scan.nextInt();
                if(num<min || num>max)
                    System.out.println("The input must be between "+ min +" and "+ max);
            } else
                System.out.println("Invalid input, insert an integer number.");
            scan.nextLine();
        } while(num<min || num>max);
        return num;
    }



    /**
     * Ask for the IP the player wants to connect to
     * @return the game's IP
     */
    public String getIP(){
        System.out.println("Insert IP: ");
        return ReadStringInput();
    }

    /**
     * Ask for the Port the player wants to connect to
     * @return the game's Port
     */
    public int getPort(){
        System.out.println("Insert Port: ");
        return ReadIntInput(1024, 65535);
    }

    /**
     * Ask for the player's username
     * @return the player username
     */
    public String askUsername() {
        System.out.print("Enter your username: ");
        return ReadStringInput();
    }

    /**
     * Ask how many people will be playing the game
     * @return number of players, between 2 and 4
     */
    public int askPlayersNumber() {
        System.out.print("Enter number of players: ");
        numPlayers = ReadIntInput(2,4);
        return numPlayers;
    }

    /**
     * Choose a between creating or joining a game
     * @return arrayList containing the chosen selection
     */
    public ArrayList<Integer> ChooseGame(){
        ArrayList<Integer> to_choose = new ArrayList<>();
        String tempstr;
        System.out.println("Choose: 'c' for Creating a game or 'j' for Joining a game");
        do {
            tempstr = ReadStringInput();
            switch(tempstr) {
                case "c":
                    to_choose.add(0);
                    break;
                case "j":
                    to_choose.add(1);
                    break;
                default:
                    System.out.println("Input was not accepted. Choose between 'c' or 'j'");
            }
        } while(to_choose.size() == 0);
        return to_choose;
    }

    /**
     * Insert game ID to connect to an existing game
     * @return gameID as string
     */
    public String ConnectToGame() {
        System.out.println("Enter GameID you want to connect to: ");
        return ReadStringInput();
    }

    /**
     * Choose a gameplay phase within the current round
     * @return arrayList containing the chosen phases
     */
    public ArrayList<Integer> PhaseChoice(){
        ArrayList<Integer> to_choose = new ArrayList<>();
        String tempstr;
        System.out.println("Choose: 'p' for Planning Phase or 'a' for Action Phase");
        do {
            tempstr = ReadStringInput();
            switch(tempstr) {
                case "p":
                    to_choose.add(0);
                    break;
                case "a":
                    to_choose.add(1);
                    break;
                default:
                    System.out.println("Input was not accepted. Choose between 'p' or 'a'");
            }
        } while(to_choose.size() == 0);
        return to_choose;
    }

    /**
     * Used to print a "Planning Phase" message.
     */
    public void StartPlanningPhase(){
        System.out.println("Begin Planning Phase");
    }

    /**
     * Planning phase 1: cloud setup, adding random students to cloud
     * */
    public void FillCloud(){
        int yellowStudents = 26;
        int blueStudents = 26;
        int greenStudents = 26;
        int redStudents = 26;
        int pinkStudents = 26;
        cloud = new ArrayList<>(); //salva [ID.Isola][studenti_sull'isola]

        for (int i=0; i<numPlayers; i++) {
            PawnColor[] allColors = PawnColor.values();
            System.out.print("Cloud " + i + ": ");
            cloud.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                if (numPlayers == 2 || numPlayers == 4 && j == 3) {
                    break;
                }
                int randInt = new Random().nextInt(5);
                PawnColor randColor = allColors[randInt];
                cloud.get(i).add(randColor);
                switch (randColor) {
                    case YELLOW:
                        yellowStudents--;
                        System.out.print(", " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    case BLUE:
                        blueStudents--;
                        System.out.print(", " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    case GREEN:
                        greenStudents--;
                        System.out.print(", " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    case RED:
                        redStudents--;
                        System.out.print(", " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    case PINK:
                        pinkStudents--;
                        System.out.print(", " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                }
            }
            System.out.println(" ");
        }
    }

    /**
     * Planning phase 2: play assistant card
     * */
    public void PlayAssistantCard(){
        int mn = 0;
        Deck assistantDeck = new Deck();
        cardIndex = new ArrayList<>();
        System.out.println("Choose the Assistant card you wish to play: ");
        for(int i=0; i<10; i++){
            if(i%2 == 0)
                mn++;
            if(!cardIndex.contains(i))
                System.out.println("Card " + i + ": Value = " + i + 1 + ", Moves = " + mn);
        }
        int index = ReadIntInput(0,9);
        cardIndex.add(index);
        assistantDeck.playCard(index);
    }

    /**
     * Used to print an "Action Phase" message.
     */
    public void StartActionPhase(){
        System.out.println("Begin Action Phase");
    }

    /**
     * Action phase 1: move students
     * */
    private void ActionMoveStudents(){
        int studentsToMove = 3;
        /*
        String tempstr;
        System.out.println("Choose: 'i' Move to Island or 'd' Move to Dining Room");
        tempstr = ReadStringInput();
        switch(tempstr) {
            case "i":

            case "d":

            default:
                System.out.println("Input was not accepted. Choose between 'i' or 'd'");
        }
        */
        if(numPlayers==3){
            studentsToMove = 4;
        }

        System.out.println("Choose " + studentsToMove + " students to move from Entrance: to Island or Dining Room");

    }

    /**
     * Action phase 2: Move Mother Nature
     * */
    private void MoveMotherNature(){

    }

    /**
     * Action phase 3: choose cloud
     * */
    private void ChooseCloud(){
        //visiona ciò che è in FillCloud(), sposta in Ingresso ed elimina
    }

    /**
     * Ask player to confirm its move.
     * @return type boolean
     */
    private boolean Confirm() {
        while (true) {
            String stringIn = ReadStringInput();
            if (stringIn.equals("y"))
                return true;
            if (stringIn.equals("n"))
                return false;
            System.out.println("Input not valid. Insert 'y' or 'n': ");
        }
    }

    /**
     * Prints the game title and the creators, before the game starts
     */
    public void PrintSplashScreen(){
        System.out.println(CLIutils.ERIANTYS);
        System.out.println(CLIutils.AUTHORS + "\n");
        CLI.clearScreen();
    }

    //-------------------------------------------
    public void PrintGameBoard() {

    }

    public void PrintBoard() {

    }
    //-------------------------------------------

    /**
     * Wait method used to print a "Waiting for players" message.
     */
    public void Waiting(){
        System.out.println("Waiting for other players to join.");
    }

    public void ActionUnavailable(){
        System.out.println("You cannot perform this action. Choose another one");
    }

    /**
     * Used to print a "Start turn" message.
     */
    public void StartTurn(){
        System.out.println("Starting your turn...");
    }

    /**
     * Used to print an "End turn" message.
     */
    public void EndTurn(){
        System.out.println("Ending your turn...");
    }

    /**
     * Manage the ending game
     */
    public void EndGame() {
        System.out.println("End of the Game, Thanks for playing.\nPress enter to end the program.");
        scan = new Scanner(System.in);
        scan.nextLine();
        System.exit(0);
    }
}



