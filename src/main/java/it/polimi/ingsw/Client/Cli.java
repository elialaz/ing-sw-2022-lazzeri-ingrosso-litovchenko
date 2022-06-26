package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.View.cli.CLIutils;
import it.polimi.ingsw.Event.EventReciver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main Class of the Client Connection
 * @author  litovn, elia_laz
 **/
public class Cli implements EventReciver {
    private ClientEventManager manager;
    private Client connection;
    private Scanner scan;
    private String nickname;
    private int playerNumber;
    private int gameID;
    private boolean expert;
    private boolean chat;
    private int cardPlayed;
    private String statusGameBoard;
    private int[] studentsToSchoolboard;
    private int[] StudentsOnIslands;
    private ArrayList<int[]> studentsToIsland;
    private int moveMotherNature;
    private int whichClodTile;
    private String winner;
    private String toFind;

    /**
     * Constructor of the Cli
     * @param manager EventManager for the Client
     * @param owner ConnectionHandler of the Client
     **/
    public Cli(ClientEventManager manager, Client owner){
        this.manager = manager;
        connection = owner;
        scan = new Scanner(System.in);
        studentsToIsland = new ArrayList<>();
        studentsToSchoolboard = new int[]{0, 0, 0, 0, 0};
        manager.subscribe("updateData", this);
        manager.subscribe("loginReceived", this);
        manager.subscribe("planningPhaseRecived", this);
        manager.subscribe("actionPhase1Recived", this);
        manager.subscribe("actionPhase2Recived", this);
        manager.subscribe("actionPhase3Recived", this);
        manager.subscribe("finish", this);
    }

    /**
     * Service function to clear the screen
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
                System.out.println(CLIutils.ANSI_BRIGHT_RED + "Empty string is not valid. Write something." + CLIutils.ANSI_RESET);
        } while(str.equals(""));
        return str.toLowerCase();
    }

    /**
     * Reads a Integer inserted by the user which has to be between a min and a max.
     * @param min of type int
     * @param max of type int
     * @return num of type int
     */
    private int ReadIntInput(int min, int max) {
        int num = -1;
        do {
            System.out.print(">");
            if(scan.hasNextInt()) {
                num = scan.nextInt();
                if(num<min || num>max)
                    System.out.println(CLIutils.ANSI_BRIGHT_RED + "The input must be between "+ min +" and "+ max + CLIutils.ANSI_RESET);
            } else
                System.out.println("Invalid input, reinsert another chosen number.");
            scan.nextLine();
        } while(num<min || num>max);
        return num;
    }

    public void login(){
        int serverPort;
        clearScreen();
        System.out.println(CLIutils.ERIANTYS);
        System.out.println(CLIutils.AUTHORS);
        System.out.print("Choose the number of server port you want to connect to (value must be between 1024 and 65535):");
        serverPort = ReadIntInput(1024, 65535);
        connection.setServerPort(serverPort);
        System.out.print("Select your unique Nickname: ");
        nickname = ReadStringInput();
        manager.notify("loginSend");
    }

    /**
     * Service function to display the menu of the start game
     */
    public void menuStartGame() {
        int selection;
        clearScreen();
        System.out.println(CLIutils.ERIANTYS);
        System.out.println(CLIutils.AUTHORS);
        System.out.println("Choose a game option: 1) New Game || 2) Load Game");
        selection = ReadIntInput(1,2);
        if(selection == 1){
            clearScreen();
            System.out.println(CLIutils.ERIANTYS);
            System.out.println(CLIutils.AUTHORS);
            System.out.print("Select number of player (from 2 to 4): ");
            playerNumber = ReadIntInput(2,4);
            System.out.print("Create a Game ID: >");
            gameID = Integer.parseInt(scan.nextLine());
            System.out.println("You want to enable ExpertMode?: 1) Yes || 2) No ");
            selection = ReadIntInput(1,2);
            expert = selection == 1;
            System.out.println("You want to enable Chat? 1) Yes || 2) No ");
            selection = ReadIntInput(1,2);
            chat = selection == 1;
            manager.notify("newGameSend");
        }
        else{
            clearScreen();
            System.out.println(CLIutils.ERIANTYS);
            System.out.println(CLIutils.AUTHORS);
            System.out.print("Insert the ID of the Game you want to join: ");
            gameID = Integer.parseInt(scan.nextLine());
            manager.notify("loadGameSend");
        }
    }

    /**
     * Getter of the Chat param
     */
    public boolean isChat() {
        return chat;
    }

    /**
     * Getter of the Expert param
     */
    public boolean isExpert() {
        return expert;
    }

    /**
     * Getter of the GameID param
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Getter of the PlayerNum param
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Getter of the Nickname param
     */
    public String getNickname() {
        return nickname;
    }

    @Override
    public void update(String eventType) {
        switch (eventType){
            case "updateData":
                statusGameBoard();
                break;
            case "loginReceived":
                menuStartGame();
                break;
            case "planningPhaseRecived":
                planningPhase();
                break;
            case "actionPhase1Recived":
                actionPhase1();
                break;
            case "actionPhase2Recived":
                actionPhase2();
                break;
            case "actionPhase3Recived":
                actionPhase3();
                break;
            case "finish":
                finish();
                break;
        }
    }

    //TODO gestire game.toString di più di un player. Gestire il num in toFInd

    //TODO
    private void finish() {
        clearScreen();
        manager.notify("finishSend");
    }

    //TODO
    private void actionPhase3() {
        clearScreen();
        manager.notify("actionPhase3Send");
    }

    //TODO
    private void actionPhase2() {
        clearScreen();
        manager.notify("actionPhase2Send");
    }

    //TODO
    private void actionPhase1() {
        clearScreen();
        System.out.println("ACTION PHASE:");
        //show how many students you have in the schoolBoard entrance
        toFind = "schoolBoard:";
        String schoolBoard_entrance = statusGameBoard.substring(statusGameBoard.indexOf(toFind)+toFind.length(), statusGameBoard.indexOf("]entranceEnd/",statusGameBoard.indexOf(toFind)));
        schoolBoard_entrance = schoolBoard_entrance.replace("[","");
        int[] entrance = Arrays.stream(schoolBoard_entrance.split(", ")).mapToInt(Integer::parseInt).toArray();
        System.out.println("You School Board, has these students (" + CLIutils.STUDENT + ") in its entrance: ");
        for (int i=0; i<5; i++){
            switch(i){
                case 0:
                    System.out.println(i+1 + ") " + CLIutils.ANSI_GREEN + entrance[i] + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    break;
                case 1:
                    System.out.println(i+1 + ") " + CLIutils.ANSI_RED + entrance[i] + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    break;
                case 2:
                    System.out.println(i+1 + ") " + CLIutils.ANSI_YELLOW + entrance[i] + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    break;
                case 3:
                    System.out.println(i+1 + ") " + CLIutils.ANSI_PINK + entrance[i] + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    break;
                case 4:
                    System.out.println(i+1 + ") " + CLIutils.ANSI_BLUE + entrance[i] + CLIutils.STUDENT + CLIutils.ANSI_RESET);
                    break;
            }
        }
        System.out.println();
        int studentsToMove, chosenStudent, chosenIsland, action1;
        if (playerNumber == 3) {
            System.out.print("1. Move 4 students ");
            studentsToMove = 4;
        } else {
            System.out.print("1. Move 3 students ");
            studentsToMove = 3;
        }
        System.out.println("to either your Dining Room or a Island.");
        System.out.println();
        showSchoolBoard();
        showIslands();
        //choose if you want to move students into 1 or 2
        System.out.println("Decide which student to move: 1) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 5) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
        System.out.println("Decide where to move students: 1) Dining Room || 2) Island");
        for (int j=0; j<studentsToMove; j++) {
            System.out.print("Move student : " );
            chosenStudent = ReadIntInput(1, 5)-1;
            System.out.print("\nto: ");
            action1 = ReadIntInput(1, 2);
            switch (action1) {
                case 1:
                    studentsToSchoolboard[chosenStudent]++;
                    break;
                case 2:
                    System.out.print("\nTo which island (choose from 1 to 12): ");
                    chosenIsland = ReadIntInput(1, 12)-1;
                    StudentsOnIslands[chosenStudent]++;
                    studentsToIsland.set(chosenIsland, StudentsOnIslands);
                    break;
            }
            System.out.println();
        }
        manager.notify("actionPhase1Send");
    }

    //TODO
    private void statusGameBoard() {
    }

    //TODO gestire il fatto che si debbano giocare 2 carte diverse?
    //TODO gestire expertmode
    public void planningPhase(){
        int k = 0;
        clearScreen();
        System.out.println("PLANNING PHASE:");
        System.out.println("1. The " + playerNumber + " clouds, have been filled.");
        //get the 2d array for the assistantCards
        toFind = "assistantCard:";
        String remaningCards = statusGameBoard.substring(statusGameBoard.indexOf(toFind)+toFind.length(), statusGameBoard.indexOf("]]/",statusGameBoard.indexOf(toFind)));
        remaningCards = remaningCards.replace("[","");
        String[] cardArray = remaningCards.split("],");
        int[][] AssistantCards = new int[2][10];

        for(int i=0; i<cardArray.length; i++){
            cardArray[i] = cardArray[i].trim();
            String[] oneInt = cardArray[i].split(", ");
            for(int j=0; j<oneInt.length; j++){
                AssistantCards[i][j] = Integer.parseInt(oneInt[j]);
            }
        }
        System.out.println("2. Play 1 assistant card of your choice, you currently have: ");
        while(k<10){
            if(AssistantCards[0][k] != -1 || AssistantCards[1][k] != -1) {
                System.out.print(k+1 + ") Value: "+ AssistantCards[0][k] + " - Mother Nature: "+ AssistantCards[1][k] +"\n");
            }
            k++;
        }
        cardPlayed = ReadIntInput(1,10)-1;
        manager.notify("planningPhaseSend");
    }

    public void setData(String input) {
        statusGameBoard = input;
    }

    public int getCardPlayed() {
        return cardPlayed;
    }

    public int getMoveMotherNature() {
        return moveMotherNature;
    }

    public int getWhichClodTile() {
        return whichClodTile;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public ArrayList<int[]> getStudentsToIsland() {
        return studentsToIsland;
    }

    public int[] getStudentsToSchoolboard() {
        return studentsToSchoolboard;
    }

    public void showSchoolBoard() {
        toFind = "entranceEnd/";
        String schoolBoard_tower = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("tower/", statusGameBoard.indexOf(toFind)));
        int towerNum = Integer.parseInt(schoolBoard_tower);
        System.out.print("Your School Board has: ");
        //TODO gestire colore torre per schoolBoard
        System.out.println(CLIutils.ANSI_WHITE + towerNum + CLIutils.TOWER + CLIutils.ANSI_RESET + " and... ");
        System.out.println("\"");
        for (int i = 0; i < 5; i++) {
            toFind = "/get" + i;
            String schoolBoard_corridor = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("end" + i, statusGameBoard.indexOf(toFind)));
            String[] corridor = schoolBoard_corridor.split("/");
            switch (i) {
                case 0:
                    System.out.println("..." + i + ") Green corridor: " + CLIutils.ANSI_GREEN + corridor[1] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + corridor[0]);
                    break;
                case 1:
                    System.out.println("..." + i + ") Red corridor: " + CLIutils.ANSI_RED + corridor[1] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + corridor[0]);
                    break;
                case 2:
                    System.out.println("..." + i + ") Yellow corridor: " + CLIutils.ANSI_YELLOW + corridor[1] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + corridor[0]);
                    break;
                case 3:
                    System.out.println("..." + i + ") Pink corridor: " + CLIutils.ANSI_PINK + corridor[1] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + corridor[0]);
                    break;
                case 4:
                    System.out.println("..." + i + ") Blue corridor: " + CLIutils.ANSI_BLUE + corridor[1] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + corridor[0]);
                    break;
            }
        }
        System.out.println();
    }

    public void showIslands() {
        toFind = "island:";
        String schoolBoard_island_students = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("]studentsOnIsland/", statusGameBoard.indexOf(toFind)));
        schoolBoard_island_students = schoolBoard_island_students.replace("[","");
        StudentsOnIslands = Arrays.stream(schoolBoard_island_students.split(", ")).mapToInt(Integer::parseInt).toArray();
        toFind = "/tower:";
        String schoolBoard_island_tower = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("endTower/", statusGameBoard.indexOf(toFind)));
        String[] tower = schoolBoard_island_tower.split("/");
        System.out.println("Current Island Situation: ");
        for (int i=0; i<12; i++) {
            System.out.println(i+1 + ") This Island has these pawns... ");
            System.out.print("... students: ");
            for (int j = 0; j < 5; j++) {
                switch (j) {
                    case 0:
                        System.out.print(CLIutils.ANSI_GREEN + StudentsOnIslands[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ", ");
                        break;
                    case 1:
                        System.out.print(CLIutils.ANSI_RED + StudentsOnIslands[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ", ");
                        break;
                    case 2:
                        System.out.print(CLIutils.ANSI_YELLOW + StudentsOnIslands[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ", ");
                        break;
                    case 3:
                        System.out.print(CLIutils.ANSI_PINK + StudentsOnIslands[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and ");
                        break;
                    case 4:
                        System.out.println(CLIutils.ANSI_BLUE + StudentsOnIslands[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                        break;
                }
            }
            System.out.print("... towers: ");
            switch (tower[0]){
                case "WHITE":
                    System.out.println(CLIutils.ANSI_WHITE + tower[1] + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
                case "GRAY":
                    System.out.println(CLIutils.ANSI_GRAY + tower[1] + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
                case "BLACK":
                    System.out.println(CLIutils.ANSI_BRIGHT_BLACK + tower[1] + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
            }
        }
        System.out.println();
    }
}
