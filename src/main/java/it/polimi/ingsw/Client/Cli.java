package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.View.cli.CLIutils;
import it.polimi.ingsw.Event.EventReceiver;
import it.polimi.ingsw.Model.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class of the Client Connection
 * @author  litovn, elia_laz
 **/
public class Cli implements EventReceiver {
    private final ClientEventManager manager;
    private final Client connection;
    private final Scanner scan;

    //Model Stored in the CLi Attributes
    private String winner;
    private VirtualModel model;
    private String nickname;
    private int playerNumber;
    private int gameId;
    private boolean expert;
    private int chat;
    private int cardPlayed;
    private int moveMotherNature;
    private int witchCloudTile;
    private ArrayList<int[]> studentsToIsland;
    private int[] studentsToSchoolboard;
    private int expertIDChosen;

    //Expert character cards
    private String expertMessage;
    private boolean notPlayed;
    private int[] switchFromCard_id2;
    private int[] switchFromEntrance_id2;
    private int[] switchFromCorridor_id3;
    private int[] switchFromEntrance_id3;
    private int colorNoInfluence_id5;


    /**
     * Constructor of the Cli
     * @param manager EventManager for the Client
     * @param owner ConnectionHandler of the Client
     **/
    public Cli(ClientEventManager manager, Client owner) {
        this.manager = manager;
        connection = owner;
        scan = new Scanner(System.in);
        expert = false;
        chat = 0;
        studentsToIsland = new ArrayList<>();
        studentsToSchoolboard = new int[]{0, 0, 0, 0, 0};
        notPlayed = true;
        manager.subscribe("updateData", this);
        manager.subscribe("loginReceived", this);
        manager.subscribe("planningPhaseRecived", this);
        manager.subscribe("actionPhase1Recived", this);
        manager.subscribe("actionPhase2Recived", this);
        manager.subscribe("actionPhase3Recived", this);
        manager.subscribe("finish", this);
        manager.subscribe("retryNickname", this);
        manager.subscribe("waitAddPlayer", this);
        manager.subscribe("disconnection", this);
        manager.subscribe("expertPlayedSend", this);
        manager.subscribe("errorLoading", this);
    }

    @Override
    public void update(String eventType) {
        switch (eventType) {
            case "updateData":
                playerNumber = model.getPlayerNum();
                showGameBoard();
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
            case "retryNickname":
                retryNickname();
                break;
            case "waitAddPlayer":
                waitPlayer();
                break;
            case "disconnection":
                disconnection();
                break;
            case "errorLoading":
                errorLoading();
        }
    }

    private void errorLoading() {
        System.out.println("This game is full of player or not exist. Please select another option");
    }

    /**
     * Setter of the winner attributes
     * @param s name of the winner
     **/
    public void setWinner(String s) {
        winner = s;
    }

    /**
     * Setter of the model attributes
     * @param input a instance from the virtual model
     **/
    public void setModel(VirtualModel input) {
        model = input;
    }

    /**
     * Service metod for setting a new nickname
     **/
    public void retryNickname() {
        System.out.println("An already connected player as this nickname please select a new one");
        System.out.println("Select your unique Nickname: ");
        nickname = ReadStringInput();
        manager.notify("loginSend");
    }

    public String getNickname() {
        return nickname;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public int getGameID() {
        return gameId;
    }

    public boolean isExpert() {
        return expert;
    }

    public int isChat() {
        return chat;
    }

    private void waitPlayer() {
        System.out.print("\nWaiting for other players... ");
    }

    public int getCardPlayed() {
        return cardPlayed;
    }

    public int getMoveMotherNature() {
        return moveMotherNature;
    }

    public int getWhichClodTile() {
        return witchCloudTile;
    }

    private void disconnection() {
        System.out.println("One or more player disconnected, the current game end");
        System.out.println("1 for main Menu, 2 for quit");
        int choise = ReadIntInput(1, 2);
        if(choise==1){
            manager.notify("loginReceived");
        }
        else {
            System.exit(0);
        }
    }

    public int[] getStudentsToSchoolboard() {
        return studentsToSchoolboard;
    }

    public ArrayList<int[]> getStudentsToIsland() {
        return studentsToIsland;
    }

    /**
     * Login class, to pass the connection port and choosen nickname
     **/
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
     **/
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
            gameId = ReadIntInput(1, 99999);
            System.out.println("You want to enable ExpertMode?: 1) Yes || 2) No ");
            selection = ReadIntInput(1, 2);
            expert = selection==1;
            if (playerNumber == 4) {
                System.out.println("You want to enable Chat? 1) Yes || 2) No ");
                selection = ReadIntInput(1, 2);
                chat = selection-1;
            }
            manager.notify("newGameSend");
        } else {
            System.out.println("Insert the ID of the Game you want to join: ");
            gameId = ReadIntInput(1, 99999);
            manager.notify("loadGameSend");
        }
    }

    /**
      * Reads a Integer inserted by the user which has to be between a min and a max.
      * @param min of type int
      * @param max of type int
      * @return num of type int
      **/
    private int ReadIntInput(int min, int max) {
        int num = -1;
        do {
            System.out.print("> ");
            if (scan.hasNextInt()) {
                num = scan.nextInt();
                if (num < min || num > max)
                    System.out.println(CLIutils.ANSI_BRIGHT_RED + "The input must be between " + min + " and " + max + CLIutils.ANSI_RESET);
            } else
                System.out.println("Invalid input, reinsert another chosen number.");
            scan.nextLine();
        } while (num < min || num > max);
        return num;
    }

    /**
     * Reads a String inserted by the user. An empty string is not accepted.
     * @return str of type String
     **/
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
    * Service function to clear the screen
     */
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private void showGameBoard() {
        clearScreen();
        System.out.println("Your School Board: ");
        showSchoolBoard(model.getSchoolboardEntrance(nickname), model.getSchoolboardTower(nickname), model.getSchoolboardColorTower(nickname), model.getSchoolboardProfessor(nickname), model.getSchoolboardCorridor(nickname), nickname);
        System.out.println();
        System.out.println("Other School Board: ");
        for (String p: model.getPlayer()) {
            if(!p.equals(nickname)){
                showSchoolBoard(model.getSchoolboardEntrance(p), model.getSchoolboardTower(p), model.getSchoolboardColorTower(p), model.getSchoolboardProfessor(p), model.getSchoolboardCorridor(p), p);
            }
        }
        System.out.println();
        System.out.print("Current islands are: ");
        showIslands();
        System.out.println("\nProfessors remaining on Gameboard: ");
        boolean[] professor = model.getProfessorOnGameboard();
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
        System.out.println();
        boolean[] witch = new boolean[playerNumber];
        for(int i=0; i<playerNumber; i++){
            witch[i]=true;
        }
        boolean number = false;
        for (int i = 0; i < model.getPlayerNum(); i++) {
            int[] stud = model.getCloudTileStudents().get(i);
            for (int j = 0; j < 5; j++) {
                if(stud[j]!=0){
                    number=true;
                }
            }
            if(number){
                witch[i]=false;
                number=false;
            }
        }
        for (int i = 0; i < model.getPlayerNum(); i++) {
            if(!witch[i]){
                System.out.print(" Cloud " + i + ": ");
                displayStudents(model.getCloudTileStudents().get(i));
            }
            else{
                System.out.print(" Cloud already taken ");
            }
        }
        if (model.isExpert()) {
            System.out.println();
            System.out.print("\nCoins remaining on table: ");
            System.out.println(model.getCoinPile());
            displayCharacterCard(model.getExpertCardId(), model.getExpertCardPrice());
        }
        System.out.println();
    }

    /**
      * Class to display all the island and what's on them
      */
    public void showIslands() {
        ArrayList<int[]> islandStud = model.getIslandStudents();
        ArrayList<Integer> tower = model.getIslandTowerNum();
        ArrayList<TowerColor> towerColor = model.getIslandColor();
        int isNum = model.getIslandNum();
        boolean islandEntryTile;
        int islandEntryTileNum;
        for (int i = 0; i < isNum; i++) {
            int[] students = islandStud.get(i);
            System.out.print("\nIsland "+ i +" has ");
            System.out.print("students: ");
            displayStudents(students);
            System.out.print("towers: ");
            switch (towerColor.get(i)) {
                case WHITE:
                    System.out.print(CLIutils.ANSI_WHITE + tower.get(i) + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
                case GRAY:
                    System.out.print(CLIutils.ANSI_GRAY + tower.get(i) + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
                case BLACK:
                    System.out.print(CLIutils.ANSI_BRIGHT_BLACK + tower.get(i) + CLIutils.TOWER + CLIutils.ANSI_RESET);
                    break;
                case NOT:
                    System.out.print("There aren't towers on this island");
                    break;
            }
            if(model.isExpert()){
                islandEntryTile = model.getIslandNoEntryTile(i);
                islandEntryTileNum = model.getIslandNoEntryTileNum(i);
                if(islandEntryTile){
                    System.out.print(" > also " + islandEntryTileNum + " No entry Tile on the island");
                }
            }
            if (i == model.getPositionMotherNature()){
                System.out.print(" > also "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET);
            }
        }
        System.out.println();
    }

    private void showSchoolBoard(int[] schoolboardEntrance, int schoolboardTower, TowerColor schoolboardColorTower, boolean[] schoolboardProfessor, int[] schoolboardCorridor, String p){
        System.out.print("School Board of player '" + p + "', has these students (" + CLIutils.STUDENT + ") in its entrance: ");
        displayStudents(schoolboardEntrance);
        switch (schoolboardColorTower) {
            case WHITE:
                System.out.println("Towers:" + CLIutils.ANSI_WHITE + schoolboardTower + CLIutils.TOWER + CLIutils.ANSI_RESET );
                break;
            case BLACK:
                System.out.println("Towers:" + CLIutils.ANSI_BLACK + schoolboardTower + CLIutils.TOWER + CLIutils.ANSI_RESET );
                break;
            case GRAY:
                System.out.println("Towers:" + CLIutils.ANSI_GRAY + schoolboardTower + CLIutils.TOWER + CLIutils.ANSI_RESET );
                break;
        }
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    System.out.println("... Corridor" + i + ") Green corridor: " + CLIutils.ANSI_GREEN + schoolboardCorridor[0] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + schoolboardProfessor[0]);
                    break;
                case 1:
                    System.out.println("... Corridor" + i + ") Red corridor: " + CLIutils.ANSI_RED + schoolboardCorridor[1] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + schoolboardProfessor[1]);
                    break;
                case 2:
                    System.out.println("... Corridor" + i + ") Yellow corridor: " + CLIutils.ANSI_YELLOW + schoolboardCorridor[2] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + schoolboardProfessor[2]);
                    break;
                case 3:
                    System.out.println("... Corridor" + i + ") Pink corridor: " + CLIutils.ANSI_PINK + schoolboardCorridor[3] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + schoolboardProfessor[3]);
                    break;
                case 4:
                    System.out.println("... Corridor" + i + ") Blue corridor: " + CLIutils.ANSI_BLUE + schoolboardCorridor[4] + CLIutils.STUDENT + CLIutils.ANSI_RESET + " and with Professor = " + schoolboardProfessor[4]);
                    break;
            }
        }
        System.out.println();

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
                    System.out.print(CLIutils.ANSI_BLUE + student[j] + CLIutils.STUDENT + CLIutils.ANSI_RESET + ". ");
                    break;
            }
        }
    }

    public void chooseCharacterCard(int id, int input) {
        int[] students_entrance = model.getSchoolboardEntrance(nickname);
        int[] students_corridor = model.getSchoolboardCorridor(nickname);
        expertMessage = "";
        System.out.println();
        switch (id) {
            case 1:
                //cost 3
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    System.out.print("Choose the Island that you want to influence: ");
                    System.out.println("Choose the Island that you want to influence: (0 - " + model.getIslandNum() + ")");
                    int chose = ReadIntInput(0, model.getIslandNum());
                    while (chose < 0 || chose > model.getIslandNum()){
                        System.out.println("What are you doing? There isn't this island. Select another one.");
                        chose = ReadIntInput(0, model.getIslandNum());
                    }
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/1/" + chose;
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 2:
                //cost 1
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    switchFromCard_id2 = new int[]{0,0,0,0,0};
                    switchFromEntrance_id2 = new int[]{0,0,0,0,0};
                    int[] studentsCard = model.getStudentsExpertCard(input);
                    int chosenStudent;
                    System.out.print("This card has the following students: ");
                    displayStudents(studentsCard);
                    System.out.print("How many students you want to replace (1, 2 or 3): ");
                    int totake = ReadIntInput(1,3);
                    System.out.println("\nDecide which student to switch from card to entrance: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                    for (int i=0; i<totake; i++) {
                        System.out.print("Student " + (i+1) + " from card, will be of color: ");
                        chosenStudent = ReadIntInput(0, 4) ;
                        while (studentsCard[chosenStudent] == 0){
                            System.out.println("What are you doing? You don't have these students in your entrance. Select another one.");
                            chosenStudent = ReadIntInput(0, 4);
                        }
                        studentsCard[chosenStudent]--;
                        switchFromCard_id2[chosenStudent]++;
                    }
                    showSchoolBoard(model.getSchoolboardEntrance(nickname), model.getSchoolboardTower(nickname), model.getSchoolboardColorTower(nickname), model.getSchoolboardProfessor(nickname), model.getSchoolboardCorridor(nickname), nickname);
                    System.out.print("\nWhich student from the entrance will you exchange them with: ");
                    for (int i=0; i<totake; i++) {
                        System.out.print("Student " + (i+1) + " from entrance, will be of color: ");
                        chosenStudent = ReadIntInput(0, 4) ;
                        while (students_entrance[chosenStudent] == 0){
                            System.out.println("What are you doing? You don't have these students in your entrance. Select another one.");
                            chosenStudent = ReadIntInput(0, 4);
                        }
                        students_entrance[chosenStudent]--;
                        switchFromEntrance_id2[chosenStudent]++;
                    }
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/2/" + model.playerId(nickname) + "/";
                    for(int i=0; i<5; i++){
                        expertMessage = expertMessage + switchFromCard_id2[i] + ":";
                    }
                    expertMessage = expertMessage + "/";
                    for(int i=0; i<5; i++){
                        expertMessage = expertMessage + switchFromEntrance_id2[i] + ":";
                    }
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 3:
                //cost 1
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    switchFromCorridor_id3 = new int[]{0,0,0,0,0};
                    switchFromEntrance_id3 = new int[]{0,0,0,0,0};
                    int chosenStudent;
                    showSchoolBoard(model.getSchoolboardEntrance(nickname), model.getSchoolboardTower(nickname), model.getSchoolboardColorTower(nickname), model.getSchoolboardProfessor(nickname), model.getSchoolboardCorridor(nickname), nickname);
                    System.out.println("From which Dining Room you want to exchange: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                    for (int i=0; i<2; i++) {
                        System.out.print("Student " + (i+1) + " from corridor, will be of color: ");
                        chosenStudent = ReadIntInput(0, 4);
                        while (students_corridor[chosenStudent] == 0){
                            System.out.println("What are you doing? You don't have these students in your corridor. Select another one.");
                            chosenStudent = ReadIntInput(0, 4);
                        }
                        students_corridor[chosenStudent]--;
                        switchFromCorridor_id3[chosenStudent]++;
                    }
                    System.out.println("Which student you want to move from the entrance to the corridor: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                    for (int i=0; i<2; i++) {
                        System.out.print("Student " + (i+1) + " from entrance, will be of color: ");
                        chosenStudent = ReadIntInput(0, 4);
                        while (students_entrance[chosenStudent] == 0){
                            System.out.println("What are you doing? You don't have these students in your entrance. Select another one.");
                            chosenStudent = ReadIntInput(0, 4);
                        }
                        students_entrance[chosenStudent]--;
                        switchFromEntrance_id3[chosenStudent]++;
                    }
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/3/" + model.playerId(nickname) + "/";
                    for(int i=0; i<5; i++){
                        expertMessage = expertMessage + switchFromCorridor_id3[i] + ":";
                    }
                    expertMessage = expertMessage + "/";
                    for(int i=0; i<5; i++){
                        expertMessage = expertMessage + switchFromEntrance_id3[i] + ":";
                    }
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 4:
                //cost 1
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/4/" + model.playerId(nickname);
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 5:
                //cost 3
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    System.out.println("Which color student will you choose: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                    colorNoInfluence_id5 = ReadIntInput(0,4);
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/5/" + colorNoInfluence_id5 + "/" + model.playerId(nickname);
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 6:
                //cost 3
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    System.out.println("Which island will you choose: (0 - " + model.getIslandNum() + ")");
                    int chose = ReadIntInput(0, model.getIslandNum());
                    while (chose < 0 || chose > model.getIslandNum()){
                        System.out.println("What are you doing? There isn't this island. Select another one.");
                        chose = ReadIntInput(0, model.getIslandNum());
                    }
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/6/" + chose;
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 7:
                //cost 2
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    System.out.println("Which island will you choose: (0 - " + model.getIslandNum() + ")");
                    int chose = ReadIntInput(0, model.getIslandNum());
                    while (chose < 0 || chose > model.getIslandNum()){
                        System.out.println("What are you doing? There isn't this island. Select another one.");
                        chose = ReadIntInput(0, model.getIslandNum());
                    }
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/7/" + chose;
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                model.addOneExpertCardUsed(id-1);
                break;
            case 8:
                //cost 2
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/8/" + model.playerId(nickname);
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 9:
                //cost 2
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/9";
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 10:
                //cost 3
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    System.out.println("Which color student will you choose: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                    int chose = ReadIntInput(0,4);
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/10/" + chose;
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 11:
                //cost 2
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    System.out.print("This card has the following students: ");
                    displayStudents(model.getStudentsExpertCard(input));
                    System.out.println("Which student color will you move: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                    int chose = ReadIntInput(0, 4);
                    while (model.getStudentsExpertCard(input)[chose]==0){
                        System.out.println("What are you doing? There isn't this students on the card. Select another one.");
                        chose = ReadIntInput(0, 4);
                    }
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/11/" + model.playerId(nickname) + "/" + chose;
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
            case 12:
                //cost 1
                if(model.getCoinPlayer(nickname)>=model.getExpertCardPrice()[id-1]){
                    System.out.print("This card has the following students: ");
                    displayStudents(model.getStudentsExpertCard(input));
                    System.out.println("Which island will you choose: (0 - " + model.getIslandNum() + ")");
                    int chose = ReadIntInput(0, model.getIslandNum());
                    while (chose < 0 || chose > model.getIslandNum()){
                        System.out.println("What are you doing? There isn't this island. Select another one.");
                        chose = ReadIntInput(0, model.getIslandNum());
                    }
                    System.out.println("Which student color will you move to the chosen island: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
                    int chose2 = ReadIntInput(0, 4);
                    while (model.getStudentsExpertCard(input)[chose2]==0){
                        System.out.println("What are you doing? There isn't this students on the card. Select another one.");
                        chose2 = ReadIntInput(0, 4);
                    }
                    model.addOneExpertCardUsed(id-1);
                    expertMessage = "playExpert/12/" + chose + "/" + chose2;
                    notPlayed = false;
                }
                else{
                    System.out.print("You not have enough coin to play this card ");
                }
                break;
        }
    }

    public void displayCharacterCard(ArrayList<Integer> characterCards, int[] characterCards_prices) {
        System.out.println("\nYou have " + model.getCoinPlayer(nickname) + " Coin");
        System.out.println("\nThe following character cards are on the table, choose which one you want to play:");
        for (Integer i: characterCards){
            switch (i) {
                case 1:
                    //cost 3
                    System.out.println("> Choose and Island and resolve the island as if Mother Nature had ended her movement there. Mother Nature will still move and the Island where she ends her movement will also be resolved: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 2:
                    //cost 1
                    System.out.println("> You may take up to 3 students for this card and replace them with the same number of Students from your Entrance: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 3:
                    //cost 1
                    System.out.println("> You may exchange up to 2 Students between your Entrance and your Dining Room: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 4:
                    //cost 1
                    System.out.println("> You may move Mother Nature up to 2 additional Islands than is indicated by the Assistant card you've played: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 5:
                    //cost 3
                    System.out.println("> Choose a color of Student: during the influence calculation this turn, that color adds no influence: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 6:
                    //cost 3
                    System.out.println("> When resolving a Conquering on an Island, towers do not count towards influence: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 7:
                    //cost 2
                    System.out.println("> Place a No Entry tile on an Island of your choice. The first time Mother Nature ends her movement there, put the No Entry tile back onto this card DO NOT calculate influence on that Island, or place any Towers: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 8:
                    //cost 2
                    System.out.println("> During the influence calculation this turn, you count as having 2 more influence: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 9:
                    //cost 2
                    System.out.println("> During this turn, you take control of any number of Professors even if you have the same number of Students as the player who currently controls them: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 10:
                    //cost 3
                    System.out.println("> Choose a type of Student: every player (including yourself) must return 3 Students of that type from their Dining Room to the bag. If any player has fewer than 3 Students of that type, return as many students as they have: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 11:
                    //cost 2
                    System.out.println("> Take 1 Student from this card and place it in your Dining Room. Then, draw a new Student from the Bag and place it on this card: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
                case 12:
                    //cost 1
                    System.out.println("> Take 1 Student from this card and place it on an Island of your choice. Then, draw a new Student from the Bag and place it on this card: ");
                    System.out.println("  Price = "+ CLIutils.ANSI_BRIGHT_YELLOW + characterCards_prices[i-1] + CLIutils.COIN + CLIutils.ANSI_RESET );
                    break;
            }
        }
    }

    //TODO manca verifica precondizioni
    public void playCharacterCard() {
        if (model.isExpert()) {
            System.out.println("Do you want to play a character card?: 1) Yes || 2) No");
            int playOrNot = ReadIntInput(1,2);
            if (playOrNot == 1){
                System.out.println("Remember that you had these ones (0, 1 and 2): ");
                ArrayList<Integer> ex = model.getExpertCardId();
                do{
                    displayCharacterCard(model.getExpertCardId(), model.getExpertCardPrice());
                    System.out.println("Which one will you play 0, 1 or 2: ");
                    System.out.println("Chose 3 for exit ");
                    int id = ReadIntInput(0,3);
                    if(id!=3){
                        expertIDChosen = ex.get(id);
                        chooseCharacterCard(ex.get(id), id);
                    }
                    else{
                        notPlayed=true;
                        break;
                    }
                }while (notPlayed);
                if(!notPlayed){
                    manager.notify("expertPlayedSend");
                }
            }
        }
    }


    public void planningPhase() {
        int k = 0;
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- PLANNING PHASE --------------");
        System.out.println("--------------------------------------------\n");
        System.out.println("1. The " + playerNumber + " clouds, have been filled.\n");
        playCharacterCard();
        System.out.println("\n2. Play 1 assistant card of your choice, you currently have: ");
        int[][] AssistantCards = model.getDeck(nickname);
        int[] usable = new int[10];
        int[] cardP = model.getAssistantsPlayedInTurn();
        while (k < 10) {
            if (AssistantCards[0][k] != -1 && AssistantCards[1][k] != -1) {
                System.out.println(k + ") V:" + AssistantCards[0][k] + " - "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET + ":" + AssistantCards[1][k]);
                usable[k] = 1;
            } else {
                usable[k] = 0;
            }
            k++;
        }

        System.out.println("\nCards played in this turn: ");

        if (cardP[0] == -1){
            System.out.println("none");
        }
        for(int c=0; c<4; c++){
            if(cardP[c]!=-1){
                System.out.println(cardP[c] + " - "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET + ":" + model.cardValueOf(cardP[c]) + " played by " + model.getPlayedById(c));
            }
        }
        System.out.println("\nWhich one will you play (choose between the ones above): ");
        cardPlayed = ReadIntInput(0, 9);
        while (usable[cardPlayed] == 0) {
            if (usable[cardPlayed] == 0) {
                System.out.println("This card does not exist, please choose another one: ");
                cardPlayed = ReadIntInput(0, 9);
            } else {
                System.out.println("This card was already played by your in your past turn, choose another one: ");
                cardPlayed = ReadIntInput(0, 9);
            }
        }
        boolean alread = false;
        for(int f=0; f<4; f++){
            if(cardP[f] == cardPlayed){
                alread = true;
            }
        }
        while(alread) {
            alread = false;
            System.out.println("This card was already played in your turn by another player. Choose another one to play.");
            cardPlayed = ReadIntInput(0, 9);
            for(int f=0; f<4; f++){
                if(cardP[f] == cardPlayed){
                    alread = true;
                }
            }
        }
        manager.notify("planningPhaseSend");
    }

    private void actionPhase1() {
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- ACTION PHASE 1 --------------");
        System.out.println("--------------------------------------------\n");
        playCharacterCard();

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
        showSchoolBoard(model.getSchoolboardEntrance(nickname), model.getSchoolboardTower(nickname), model.getSchoolboardColorTower(nickname), model.getSchoolboardProfessor(nickname), model.getSchoolboardCorridor(nickname), nickname);
        showIslands();
        //choose if you want to move students into 1 or 2
        System.out.println("\nDecide which student to move: 0) " + CLIutils.ANSI_GREEN + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 1) " + CLIutils.ANSI_RED + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 2) " + CLIutils.ANSI_YELLOW + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 3) " + CLIutils.ANSI_PINK + CLIutils.STUDENT + CLIutils.ANSI_RESET + " || 4) " + CLIutils.ANSI_BLUE + CLIutils.STUDENT + CLIutils.ANSI_RESET + "");
        System.out.println("Decide where to move students: 1) Dining Room || 2) Island\n");
        int[] students_entrance = model.getSchoolboardEntrance(nickname);
        studentsToSchoolboard = new int[]{0, 0, 0, 0, 0};
        studentsToIsland = new ArrayList<>();
        for (int j = 0; j < studentsToMove; j++) {
            int[] students_isle = new int[] {0,0,0,0,0,0};
            System.out.print("Move student: ");
            chosenStudent = ReadIntInput(0, 4) ;
            while (students_entrance[chosenStudent] == 0){
                System.out.println("What are you doing? You don't have these students in your entrance. Select another one.");
                chosenStudent = ReadIntInput(0, 4);
            }
            System.out.print("to: ");
            action1 = ReadIntInput(1, 2);
            switch (action1) {
                case 1:
                    studentsToSchoolboard[chosenStudent]++;
                    students_entrance[chosenStudent]--;
                    break;
                case 2:
                    if(studentsToIsland.size()==0){
                        students_isle[chosenStudent+1]++;
                        students_entrance[chosenStudent]--;
                        System.out.print("To which island (choose from 0 to " + model.getIslandNum() + "): ");
                        chosenIsland = ReadIntInput(0, 11);
                        students_isle[0] = chosenIsland;
                        studentsToIsland.add(students_isle);
                    }
                    else{
                        boolean trovato = false;
                        students_isle[chosenStudent+1]++;
                        students_entrance[chosenStudent]--;
                        System.out.print("To which island (choose from 0 to " + model.getIslandNum() + "): ");
                        chosenIsland = ReadIntInput(0, 11);
                        for (int[] s: studentsToIsland) {
                            if(s[0]==chosenIsland){
                                for(int l=1; l<6; l++){
                                    s[l]+=students_isle[l];
                                }
                                trovato = true;
                            }
                        }
                        if(!trovato){
                            students_isle[0] = chosenIsland;
                            studentsToIsland.add(students_isle);
                        }
                    }
                    break;
            }
            System.out.println();
        }
        manager.notify("actionPhase1Send");
    }

    private void actionPhase2() {
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- ACTION PHASE 2 --------------");
        System.out.println("--------------------------------------------\n");
        playCharacterCard();
        showIslands();
        System.out.println("\nMother Nature ("+ CLIutils.MOTHER_NATURE +") is currently on island "+ model.getPositionMotherNature() +"");
        System.out.println("Because of the Assistant Card you previously played, you can move the "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET +" up to a max of "+ model.getLastCardMotherNature(nickname) +" islands.");
        System.out.print("How many islands you want to move "+ CLIutils.ANSI_BRIGHT_YELLOW + CLIutils.MOTHER_NATURE + CLIutils.ANSI_RESET +" to: ");
        moveMotherNature = ReadIntInput(1, model.getLastCardMotherNature(nickname));
        manager.notify("actionPhase2Send");
    }

    private void actionPhase3() {
        witchCloudTile = -1;
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("-------------- ACTION PHASE 3 --------------");
        System.out.println("--------------------------------------------\n");
        playCharacterCard();
        System.out.print("Choose a cloud between those below:");

        boolean[] witch = new boolean[playerNumber];
        for(int i=0; i<playerNumber; i++){
            witch[i]=true;
        }
        boolean number = false;
        for (int i = 0; i < model.getPlayerNum(); i++) {
            int[] stud = model.getCloudTileStudents().get(i);
            for (int j = 0; j < 5; j++) {
                if(stud[j]!=0){
                    number=true;
                }
            }
            if(number){
                witch[i]=false;
                number=false;
            }
        }
        for (int i = 0; i < model.getPlayerNum(); i++) {
            if(!witch[i]){
                System.out.print("\n"+ i + ") Cloud " + i + ": ");
                displayStudents(model.getCloudTileStudents().get(i));
            }
            else{
                System.out.print("\n"+ i + ") Cloud already taken ");
            }
        }
        System.out.println();
        System.out.println("Which cloud will you choose (from 0 to " + (playerNumber-1) + "): ");
        witchCloudTile = ReadIntInput(0, playerNumber-1);
        while(witch[witchCloudTile]){
            System.out.println("Cloud already taken chose a different one ");
            System.out.println("Which cloud will you choose (from 0 to " + (playerNumber-1) + "): ");
            witchCloudTile = ReadIntInput(0, playerNumber-1);
        }
        manager.notify("actionPhase3Send");
    }

    private void finish() {
        clearScreen();
        if(nickname.equals(winner)){
            System.out.println("YOU'RE THE WINNER!");
        }
        else{
            System.out.println("You Lost. Game Over");
            update("loginReceived");
        }
        manager.notify("finishSend");
        System.out.println("1 for new Game, 2 for exit");
        int chose = ReadIntInput(1, 2);
        if(chose==1){
            update("loginReceived");
        }
        else{
            System.exit(1);
        }
    }

    public int getExpertIDChosen() {
        return expertIDChosen;
    }

    public String getSpecialCard() {
        return expertMessage;
    }
}