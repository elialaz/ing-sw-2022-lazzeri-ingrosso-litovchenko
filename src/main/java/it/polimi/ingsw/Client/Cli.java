package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.View.cli.CLIutils;
import it.polimi.ingsw.Event.EventReciver;
import it.polimi.ingsw.Model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Main Class of the Client Connection
 * @author  litovn, elia_laz
 **/
public class Cli implements EventReciver {
    private final ClientEventManager manager;
    private final Client connection;
    private final Scanner scan;
    private boolean expert;
    private boolean chat;
    private int cardPlayed;
    private String nickname;
    private int playerNumber;
    private int gameID;
    private String statusGameBoard;
    private final int[] studentsToSchoolboard;
    private final ArrayList<int[]> studentsToIsland;
    private int moveMotherNature;
    private int whichClodTile;
    private String winner;
    private String toFind;

    private final ArrayList<String> players = new ArrayList<>();
    private int playerID;
    private final ArrayList<int[]> cloudTiles = new ArrayList<>();
    private final ArrayList<int[][]> assistantCard = new ArrayList<>();
    private final int[] lastPlayedAssistantCard = new int[3];
    private final ArrayList<int[]> entranceSchoolBoard = new ArrayList<>();
    private final ArrayList<String[]> towerSchoolBoard = new ArrayList<>();
    private final ArrayList<String[]> corridorSchoolBoard = new ArrayList<>();
    private final ArrayList<String[]> profSchoolBoard = new ArrayList<>();
    private final ArrayList<int[]> StudentsOnIslands = new ArrayList<>();
    private ArrayList<String[]> tower_island = new ArrayList<>();
    private final int[] professor = new int[5];
    private int positionMN;
    private int coinPile;

    /**
     * Constructor of the Cli
     * @param manager EventManager for the Client
     * @param owner   ConnectionHandler of the Client
     **/
    public Cli(ClientEventManager manager, Client owner) {
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
            System.out.print("> ");
            str = scan.nextLine();
            if (str.equals(""))
                System.out.println(CLIutils.ANSI_BRIGHT_RED + "Empty string is not valid. Write something." + CLIutils.ANSI_RESET);
        } while (str.equals(""));
        return str;
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
            System.out.print("> ");
            if (scan.hasNextInt()) {
                num = scan.nextInt();
                if (num < min || num > max)
                    System.out.println(CLIutils.ANSI_BRIGHT_RED + "The input must be between " + min + " and " + max + CLIutils.ANSI_RESET);
                else if (min == max){
                    System.out.println(CLIutils.ANSI_BRIGHT_RED + "The input must be " + min + CLIutils.ANSI_RESET);
                }

            } else
                System.out.println("Invalid input, reinsert another chosen number.");
            scan.nextLine();
        } while (num < min || num > max);
        return num;
    }

    /**
     * Login class, to pass the connection port and choosen nickname
     */
    public void login() {
        int serverPort;
        clearScreen();
        System.out.println(CLIutils.ERIANTYS);
        System.out.println(CLIutils.AUTHORS);
        System.out.println();
        System.out.println("Choose the number of server port you want to connect to (value must be between 1024 and 65535):");
        serverPort = ReadIntInput(1024, 65535);
        connection.setServerPort(serverPort);
        System.out.println("Select your unique Nickname: ");
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
        System.out.println();
        System.out.println("Choose a game option: 1) New Game || 2) Load Game");
        selection = ReadIntInput(1, 2);
        if (selection == 1) {
            System.out.println("Select number of player (from 2 to 4): ");
            playerNumber = ReadIntInput(2, 4);
            System.out.println("Create a Game ID: ");
            gameID = ReadIntInput(1, 99999);
            System.out.println("You want to enable ExpertMode?: 1) Yes || 2) No ");
            selection = ReadIntInput(1, 2);
            expert = selection == 1;
            System.out.println("You want to enable Chat? 1) Yes || 2) No ");
            selection = ReadIntInput(1, 2);
            System.out.println("waiting for players to join...");
            chat = selection == 1;
            manager.notify("newGameSend");
        } else {
            System.out.println("Insert the ID of the Game you want to join: ");
            gameID = ReadIntInput(1, 99999);
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
        switch (eventType) {
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

    //TODO
    private void finish() {
        clearScreen();
        //winner =
        manager.notify("finishSend");
    }

    //TODO
    private void actionPhase3() {
        int[] cloud;
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- ACTION PHASE 3 --------------");
        System.out.println("--------------------------------------------\n");
        System.out.println("Choose a cloud between those below:");
        /*
        for (int i = 0; i < playerNumber; i++) {
            toFind = "cloudTile" + i + ":";
            String schoolBoard_island_students = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("]endCloud/", statusGameBoard.indexOf(toFind)));
            schoolBoard_island_students = schoolBoard_island_students.replace("[", "");
            cloud = Arrays.stream(schoolBoard_island_students.split(", ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(i + 1 + ") Cloud " + i + 1 + ": ");
            displayStudents(cloud);
        }*/

        System.out.println();
        System.out.println("Which cloud will you choose (from 1 to " + playerNumber + "): ");
        whichClodTile = ReadIntInput(1, playerNumber) - 1;
        manager.notify("actionPhase3Send");
    }

    //TODO
    private void actionPhase2() {
        int[][] AssistantCard = new int[][]{{1,2,3,4,5,6,7,8,9,10},{1,1,2,2,3,3,4,4,5,5}};
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- ACTION PHASE 2 --------------");
        System.out.println("--------------------------------------------\n");
        toFind = "position:";
        String MotherNaturePosition = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("/", statusGameBoard.indexOf(toFind)));
        showIslands();
        System.out.println("\nMother Nature ("+ CLIutils.MOTHER_NATURE +") is currently on island "+ MotherNaturePosition+"");
        System.out.println("Because of the Assistant Card you previously played, you can move the "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET +" up to a max of "+ AssistantCard[1][cardPlayed] +" islands.");
        System.out.print("How many islands you want to move "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET +" to: ");
        moveMotherNature = ReadIntInput(1,AssistantCard[1][cardPlayed]);
        manager.notify("actionPhase2Send");
    }

    //TODO
    private void actionPhase1() {
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- ACTION PHASE 1 --------------");
        System.out.println("--------------------------------------------\n");

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
        showSchoolBoard(playerID);
        showIslands();
        //choose if you want to move students into 1 or 2
        System.out.println("\nDecide which student to move: 1) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 5) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
        System.out.println("Decide where to move students: 1) Dining Room || 2) Island\n");
        for (int j = 0; j < studentsToMove; j++) {
            System.out.print("Move student: ");
            chosenStudent = ReadIntInput(1, 5) - 1;
            System.out.print("to: ");
            action1 = ReadIntInput(1, 2);
            int[] students = StudentsOnIslands.get(playerID);
            switch (action1) {
                case 1:
                    studentsToSchoolboard[chosenStudent]++;
                    break;
                case 2:
                    System.out.print("\nTo which island (choose from 1 to 12): ");
                    chosenIsland = ReadIntInput(1, 12) - 1;
                    students[chosenStudent]++;
                    studentsToIsland.set(chosenIsland, students);
                    break;
            }
            System.out.println();
        }
        manager.notify("actionPhase1Send");
    }

    //TODO gestire expertmode
    public void planningPhase() {
        int k = 0;
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- PLANNING PHASE --------------");
        System.out.println("--------------------------------------------\n");
        System.out.println("1. The " + playerNumber + " clouds, have been filled.\n");

        System.out.println("2. Play 1 assistant card of your choice, you currently have: ");
        int[][] AssistantCards = assistantCard.get(playerID);
        int[] usable = new int[10];
        while (k < 10) {
            if (AssistantCards[0][k] != -1 || AssistantCards[1][k] != -1) {
                System.out.println(k + 1 + ") V:" + AssistantCards[0][k] + " - "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET + ":" + AssistantCards[1][k]);
                usable[k] = 1;
            } else {
                usable[k] = 0;
            }
            k++;
        }
        System.out.println("\nWhich one will you play (choose between the ones above): ");
        cardPlayed = ReadIntInput(1, 10) - 1;
        while (usable[cardPlayed] == 0 || IntStream.of(lastPlayedAssistantCard).anyMatch(x -> x == cardPlayed)) {
            if (usable[cardPlayed] == 0) {
                System.out.println("This card does not exist, please choose another one: ");
                cardPlayed = ReadIntInput(1, 10) - 1;
            } else {
                System.out.println("This card was already played in your turn, choose another one: ");
                cardPlayed = ReadIntInput(1, 10) - 1;
            }
        }
        manager.notify("planningPhaseSend");
    }

    private void statusGameBoard() {
        clearScreen();
        System.out.println("Your School Board: ");
        showSchoolBoard(playerID);
        System.out.println();
        System.out.println("Other School Board: ");
        for(int i=0; i<playerNumber; i++){
            if(playerID!=i)
                showSchoolBoard(i);
        }
        System.out.println();
        System.out.print("Current islands are: ");
        showIslands();
        System.out.println("\nProfessors remaining: ");
        for (int i=0; i<5; i++){
            switch (i) {
                case 0:
                    System.out.print(CLIutils.ANSI_GREEN + professor[i] + CLIutils.PROF + CLIutils.ANSI_RESET + ", ");
                    break;
                case 1:
                    System.out.print(CLIutils.ANSI_RED + professor[i] + CLIutils.PROF + CLIutils.ANSI_RESET + ", ");
                    break;
                case 2:
                    System.out.print(CLIutils.ANSI_YELLOW + professor[i] + CLIutils.PROF + CLIutils.ANSI_RESET + ", ");
                    break;
                case 3:
                    System.out.print(CLIutils.ANSI_PINK + professor[i] + CLIutils.PROF + CLIutils.ANSI_RESET + ", ");
                    break;
                case 4:
                    System.out.println(CLIutils.ANSI_BLUE + professor[i] + CLIutils.PROF + CLIutils.ANSI_RESET + ".");
                    break;
            }
        }
        System.out.print("\nCoins remaining: ");
        System.out.println(coinPile);
        System.out.println();
    }

    //TODO gestire updateGameBoard/idGame/playerNum ?
    /**
     * Setter for the GameBoard status
     */
    public void setData(String input) {
        statusGameBoard = input;

        toFind = "playerNum";
        String playerNum = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("/", statusGameBoard.indexOf(toFind)));
        playerNumber = Integer.parseInt(playerNum);

        for (int id=0; id<playerNumber; id++) {
            toFind = "gamer"+id+":";
            String gamerRemains = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("/", statusGameBoard.indexOf(toFind)));
            players.add(gamerRemains);
        }
        playerID = players.indexOf(nickname);

        int[][] AssistantCards = new int[2][10];
        for (int id=0; id<playerNumber; id++) {
            toFind = "assistantCard" + id + ":";
            String assCardsRemains = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("]]/", statusGameBoard.indexOf(toFind)));
            assCardsRemains = assCardsRemains.replace("[[", "");
            String[] cardArray = assCardsRemains.split(Pattern.quote("], ["));
            for (int i = 0; i < cardArray.length; i++) {
                cardArray[i] = cardArray[i].trim();
                String[] oneInt = cardArray[i].split(", ");
                for(int j=0; j<oneInt.length; j++){
                    AssistantCards[i][j] = Integer.parseInt(oneInt[j]);
                }
            }
            assistantCard.add(AssistantCards);

            toFind = "lastValue"+id;
            String lastValue = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("/", statusGameBoard.indexOf(toFind)));
            if (!lastValue.equals("-1")) {
                lastPlayedAssistantCard[id] = Integer.parseInt(lastValue);
            } else {
                lastPlayedAssistantCard[id] = 0;
            }
        }

        //TODO gestire charachterCard in base all'ID ricevuto

        for(int id=0; id<playerNumber; id++) {
            toFind = "schoolBoard" + id + ":";
            String schoolBoard_entrance = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("]entranceEnd/", statusGameBoard.indexOf(toFind)));
            schoolBoard_entrance = schoolBoard_entrance.replace("[", "");
            entranceSchoolBoard.add(Arrays.stream(schoolBoard_entrance.split(", ")).mapToInt(Integer::parseInt).toArray());

            toFind = "entranceEnd/";
            String schoolBoard_tower = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("tower/", statusGameBoard.indexOf(toFind)));
            towerSchoolBoard.add(schoolBoard_tower.split("/"));

            toFind = "/corridor";
            String schoolBoard_corridor = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("/prof", statusGameBoard.indexOf(toFind)));
            corridorSchoolBoard.add(schoolBoard_corridor.split("/"));

            toFind = "/prof";
            String schoolBoard_prof = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("end/", statusGameBoard.indexOf(toFind)));
            profSchoolBoard.add(schoolBoard_prof.split("/"));
        }


        for (int id = 0; id < 12; id++){
            toFind = "island" + id + ":";
            String schoolBoard_island_students = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("]studentsOnIsland/", statusGameBoard.indexOf(toFind)));
            schoolBoard_island_students = schoolBoard_island_students.replace("[", "");
            StudentsOnIslands.add(Arrays.stream(schoolBoard_island_students.split(", ")).mapToInt(Integer::parseInt).toArray());
            toFind = "tower:";
            String schoolBoard_island_tower = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("endTower/", statusGameBoard.indexOf(toFind)));
            tower_island.add(schoolBoard_island_tower.split("/"));
            //TODO gestire expertMode
        }

        for (int id = 0; id < playerNumber; id++) {
            toFind = "cloudTile"+id+":";
            String schoolBoard_island_students = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("]endCloud/", statusGameBoard.indexOf(toFind)));
            schoolBoard_island_students = schoolBoard_island_students.replace("[", "");
            int[] cloud = Arrays.stream(schoolBoard_island_students.split(", ")).mapToInt(Integer::parseInt).toArray();
            cloudTiles.add(cloud);
        }

        toFind = "position:";
        String MNPosition = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("/professor", statusGameBoard.indexOf(toFind)));
        positionMN = Integer.parseInt(MNPosition);

        toFind = "professor:";
        String profRemains = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length(), statusGameBoard.indexOf("]/", statusGameBoard.indexOf(toFind)));
        profRemains = profRemains.replace("[", "");
        String[] profRemain = profRemains.split(", ");
        for (int i=0; i<5; i++) {
            if (Objects.equals(profRemain[i], "false")) {
                professor[i] = 0;
            } else {
                professor[i] = 1;
            }
        }

        toFind = "coinPile:";
        String coinPiles = statusGameBoard.substring(statusGameBoard.indexOf(toFind) + toFind.length());
        coinPile = Integer.parseInt(coinPiles);
    }

    /**
     * Getter of the played card
     */
    public int getCardPlayed() {
        return cardPlayed;
    }

    /**
     * Getter of the mother nature moves
     */
    public int getMoveMotherNature() {
        return moveMotherNature;
    }

    /**
     * Getter of the chosen cloud tile
     */
    public int getWhichClodTile() {
        return whichClodTile;
    }

    /**
     * Setter for the game winner
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * Getter of the students moved to an island
     */
    public ArrayList<int[]> getStudentsToIsland() {
        return studentsToIsland;
    }

    /**
     * Getter of the students moved to a corridor
     */
    public int[] getStudentsToSchoolboard() {
        return studentsToSchoolboard;
    }

    /**
     * Class to display students with color and icon
     * @param student array int of students
     */
    public void displayStudents(int[] student) {
        for (int j = 0; j < 5; j++) {
            switch (j) {
                case 0:
                    System.out.print(CLIutils.ANSI_GREEN + student[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ", ");
                    break;
                case 1:
                    System.out.print(CLIutils.ANSI_RED + student[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ", ");
                    break;
                case 2:
                    System.out.print(CLIutils.ANSI_YELLOW + student[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ", ");
                    break;
                case 3:
                    System.out.print(CLIutils.ANSI_PINK + student[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ", ");
                    break;
                case 4:
                    System.out.print(CLIutils.ANSI_BLUE + student[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and ");
                    break;
            }
        }
    }

    /**
     * Class to display a schoolBoard
     * @param playerID in representing the player you want to show the schoolBoard of
     */
    public void showSchoolBoard(int playerID) {
        System.out.print("School Board of player'" + players.get(playerID) + "', has these students (" + CLIutils.STUDENT + ") in its entrance: ");
        displayStudents(entranceSchoolBoard.get(playerID));

        String[] tempTower = towerSchoolBoard.get(playerID);
        switch (tempTower[1]) {
            case "WHITE":
                System.out.println("Towers:" + CLIutils.ANSI_WHITE + tempTower[0] + CLIutils.TOWER + CLIutils.ANSI_RESET );
                break;
            case "BLACK":
                System.out.println("Towers:" + CLIutils.ANSI_BLACK + tempTower[0] + CLIutils.TOWER + CLIutils.ANSI_RESET );
                break;
            case "GRAY":
                System.out.println("Towers:" + CLIutils.ANSI_GRAY + tempTower[0] + CLIutils.TOWER + CLIutils.ANSI_RESET );
                break;
        }
        String[] corridor = corridorSchoolBoard.get(playerID);
        String[] professor = profSchoolBoard.get(playerID);
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    System.out.println("... Corridor" + i + ") Green corridor: " + CLIutils.ANSI_GREEN + corridor[0] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + professor[0]);
                    break;
                case 1:
                    System.out.println("... Corridor" + i + ") Red corridor: " + CLIutils.ANSI_RED + corridor[1] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + professor[1]);
                    break;
                case 2:
                    System.out.println("... Corridor" + i + ") Yellow corridor: " + CLIutils.ANSI_YELLOW + corridor[2] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + professor[2]);
                    break;
                case 3:
                    System.out.println("... Corridor" + i + ") Pink corridor: " + CLIutils.ANSI_PINK + corridor[3] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + professor[3]);
                    break;
                case 4:
                    System.out.println("... Corridor" + i + ") Blue corridor: " + CLIutils.ANSI_BLUE + corridor[4] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + professor[4]);
                    break;
            }
        }
        System.out.println();
    }

    /**
     * Class to display all 12 island and what's on them
     */
    public void showIslands() {
        for (int i = 0; i < 12; i++) {
            int[] students = StudentsOnIslands.get(i);
            String[] towers = tower_island.get(i);
            System.out.print("\nIsland "+ i +" has ");
            System.out.print("students: ");
            displayStudents(students);
            System.out.print("towers: ");
            switch (towers[0]) {
                case "WHITE":
                    System.out.println(CLIutils.ANSI_WHITE + towers[1] + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
                case "GRAY":
                    System.out.println(CLIutils.ANSI_GRAY + towers[1] + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
                case "BLACK":
                    System.out.println(CLIutils.ANSI_BRIGHT_BLACK + towers[1] + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
            }
            if (i == positionMN){
                System.out.print(" also "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET);
            }
        }
        System.out.println();
    }

}

