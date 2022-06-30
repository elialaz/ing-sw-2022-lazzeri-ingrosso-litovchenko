package it.polimi.ingsw.Server;

import java.io.*;
import java.net.Socket;

/**
 * Thread for Server concurrent execution
 * @author elia_laz
 **/
class ServerThread extends Thread{
    private String[] input;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Server server;
    private ServerEventManager eventManager;
    private boolean connected;
    private ConnectionHandler master;
    private String nickname;
    private final Object inputLock;
    private final Object outputLock;


    /**
     * Constructor of ServerThread class
     * @param s of type Socket
     * @param eventManager of type ServerEventManager
     * @param server reference to the server
     */
    public ServerThread(Socket s, ServerEventManager eventManager, Server server){
        input = null;
        this.socket = s;
        this.eventManager = eventManager;
        this.inputLock = new Object();
        this.outputLock = new Object();
        try{
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
        }catch(IOException e){
            System.out.println("Error during the input output buffet open of the client");
        }
        this.server = server;
        connected = true;
    }

    /**
     * Class to start running concurrent threads
     */
    public void run() {
        try {
            receiveData();
        } catch (IOException e) {
            this.disconnect();
        }
    }

    public void receiveData() throws IOException{
        try {
            while(!Thread.currentThread().isInterrupted()) {
                synchronized (inputLock) {
                    String message = in.readLine();
                    input = message.split("/");
                    if (input[0] != null) {
                        if (input[0].equals("login")) {
                            if(server.verifyClient(input[1])){
                                server.addClient(input[1], this);
                                nickname = input[1];
                                sendMessage("loginSuccess");
                            }
                            else{
                                sendMessage("nickname");
                            }
                        }
                        else if(input[0].equals("newGame")){
                            if(server.verifyGameId(Integer.parseInt(input[2]))){
                                master = server.startGame(eventManager, nickname, this, Integer.parseInt(input[1]), Integer.parseInt(input[2]), Boolean.parseBoolean(input[3]), Boolean.parseBoolean(input[4]));
                                sendMessage("createSuccess");
                            }
                            else{
                                sendMessage("idWrong");
                            }
                        }
                        else if(input[0].equals("loadGame")){
                            boolean response = server.loadGame(nickname, Integer.parseInt(input[1]), this);
                            if (response) {
                                master = server.getMaster(Integer.parseInt(input[1]));
                                sendMessage("success");
                                if (master.getExpectedGamer() == master.getActualGamer()) {
                                    master.update("update");
                                    master.setStart(true);
                                }
                            } else {
                                sendMessage("failed");
                            }
                        }
                        else {
                            master.onMessageReceived(message, nickname);
                        }
                    }
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println("Client " + nickname + " Disconnected");
        }
        catch (Exception e) {
            System.out.println("Error during receive of the data");
        }
        connected = false;
        disconnect();
        socket.close();
    }

    public void disconnect() {
        if(connected) {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error during closing of the socket");
            }
            connected = false;

            master.clientRemove(nickname);
            Thread.currentThread().interrupt();

            server.onDisconnect(this);
        }
        else{
            master.clientRemove(nickname);
            Thread.currentThread().interrupt();

            server.onDisconnect(this);
        }
    }

    public void sendMessage(String message) {
        synchronized (outputLock) {
            out.println(message);
        }
    }

    public String getNickname() {
        return nickname;
    }
}

/*

try{
            while(setup){
                line=is.readLine();
                String[] parsed = line.split("/");
                switch (parsed[0]){
                    case "login":
                        os.println("loginSuccess");
                        break;
                    case "newGame":
                        clientManager = ClientEventManager.createClientEventManager();
                        clientManager.subscribe("clientSend", this);
                        clientManager.subscribe("updateGameBoard", this);
                        nickname = parsed[1];
                        master = server.startGame(eventManager, clientManager, parsed[1], Integer.parseInt(parsed[2]), Integer.parseInt(parsed[3]), Boolean.parseBoolean(parsed[4]), Boolean.parseBoolean(parsed[5]));
                        controller = master.getController();
                        nextMove = controller.getManager();
                        setup=false;
                        break;
                    case "loadGame":
                        boolean response = server.loadGame(parsed[1], Integer.parseInt(parsed[2]));
                        if (response) {
                            master = server.getMaster(Integer.parseInt(parsed[2]));
                            controller = master.getController();
                            nextMove = controller.getManager();
                            setup=false;
                            os.println("success");
                            if(master.getExpectedGamer()== master.getActualGamer()){
                                master.setStart(true);
                            }
                        }
                        else{
                            os.println("failed");
                        }
                        break;
                }
            }
            while(!Thread.currentThread().isInterrupted()) {
                if (send && master.isStart()) {
                    send=false;
                    String[] parsed;
                    switch (master.getActionType()) {
                        case 0:
                            os.println("enable/planningPhase");
                            line = is.readLine();
                            parsed = line.split("/");
                            controller.playAssistantCard(Integer.parseInt(parsed[0]));
                            nextMove.notify("nextmove");
                            break;
                        case 1:
                            os.println("enable/actionPhase1");
                            line = is.readLine();
                            parsed = line.split("/");
                            int[] studentsToIsland = new int[5];
                            int[] studentsToSchoolBoard = new int[5];
                            //TODO inserire if per gioca carte esperto
                            String[] firstMove = parsed[0].split("!");
                            for (int i = 0; i < 5; i++) {
                                studentsToIsland[i] = Integer.parseInt(firstMove[i]);
                            }
                            String[] secondMove = parsed[1].split("!");
                            for (int i = 0; i < 5; i++) {
                                studentsToSchoolBoard[i] = Integer.parseInt(secondMove[i]);
                            }
                            int island = Integer.parseInt(parsed[2]);
                            controller.moveStudentsToIsland(studentsToIsland, island);
                            controller.moveStudentsToSchoolboard(studentsToSchoolBoard);
                            nextMove.notify("nextmove");
                            break;
                        case 2:
                            os.println("enable/actionPhase2");
                            line = is.readLine();
                            parsed = line.split("/");
                            //TODO inserire if per gioca carte esperto
                            controller.moveMotherNature(Integer.parseInt(parsed[0]));
                            nextMove.notify("nextmove");
                            break;
                        case 3:
                            os.println("enable/actionPhase3");
                            line = is.readLine();
                            parsed = line.split("/");
                            //TODO inserire if per gioca carte esperto
                            controller.takeCloudTile(Integer.parseInt(parsed[0]));
                            nextMove.notify("nextmove");
                            break;
                        case 4:
                            os.println("enable/finish");
                            game = false;
                            break;
                    }
                }
            }
            os.println(controller.getNextTurnPlayer());
            os.println("finish");
        }
        catch (Exception e) {
            System.out.println("IO Error/ Client terminated abruptly");
            eventManager.notify("clientError");
        }
 */