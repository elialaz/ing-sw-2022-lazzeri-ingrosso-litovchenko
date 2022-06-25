package it.polimi.ingsw.Server;

import it.polimi.ingsw.Client.ClientEventManager;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Event.EventReciver;
import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;

import java.io.*;
import java.net.Socket;

/**
 * Thread for Server concurrent execution
 * @author elia_laz
 **/
class ServerThread extends Thread implements EventReciver {
    private int id;
    private String line;
    private BufferedReader is;
    private PrintWriter os;
    private Socket s;
    private ServerEventManager eventManager;
    private Server server;
    private boolean setup;
    private boolean game;
    private ClientEventManager clientManager;
    private ConnectionHandler master;
    private String nickname;
    private Controller controller;
    private boolean send;


    /**
     * Constructor of ServerThread class
     * @param s of type Socket
     * @param eventManager of type ServerEventManager
     * @param id of type int
     * @param server reference to the server
     */
    public ServerThread(Socket s, ServerEventManager eventManager, int id, Server server){
        this.id = id;
        line = null;
        this.s = s;
        this.eventManager = eventManager;
        try{
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(s.getOutputStream(), true);
        }catch(IOException e){
            System.out.println("IO error in server thread");
        }
        this.server = server;
        setup = true;
        game=true;
        send=false;
    }

    /**
     * Class to start running concurrent threads
     */
    public void run() {
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
                        setup=false;
                        break;
                    case "loadGame":
                        boolean response = server.loadGame(parsed[1], Integer.parseInt(parsed[2]));
                        if (response) {
                            master = server.getMaster(Integer.parseInt(parsed[2]));
                            controller = master.getController();
                            os.println("success");
                            setup=false;
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
            while(game) {
                if (send && master.isStart()) {
                    send=false;
                    String[] parsed;
                    switch (master.getActionType()) {
                        case 0:
                            os.println("enable/planningPhase");
                            line = is.readLine();
                            parsed = line.split("/");
                            controller.playAssistantCard(Integer.parseInt(parsed[0]));
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
                            break;
                        case 2:
                            os.println("enable/actionPhase2");
                            line = is.readLine();
                            parsed = line.split("/");
                            //TODO inserire if per gioca carte esperto
                            controller.moveMotherNature(Integer.parseInt(parsed[0]));
                            break;
                        case 3:
                            os.println("enable/actionPhase3");
                            line = is.readLine();
                            parsed = line.split("/");
                            //TODO inserire if per gioca carte esperto
                            controller.takeCloudTile(Integer.parseInt(parsed[0]));
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
    }

    @Override
    public void update(String eventType) {
        try{
            if(eventType.equals("updateGameBoard")){
                os.println(master.gameBoardStatus());
            }
            else if(eventType.equals("clientSend")){
                if(master.getCurrentPlayerTurn().equals(nickname)){
                    send=true;
                }
            }
        }
        catch (Exception e) {
            System.out.println("IO Error/ Client terminated abruptly");
            eventManager.notify("clientError");
        }
    }
}