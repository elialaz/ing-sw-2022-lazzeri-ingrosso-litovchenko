package it.polimi.ingsw.Client;

import it.polimi.ingsw.Event.EventManager;
import it.polimi.ingsw.Event.EventReciver;

import java.util.*;


/**
 * Event Manager for the Client
 * @author elia_laz
 **/
public class ClientEventManager implements EventManager {

    private Map<String, List<EventReciver>> listeners = new HashMap<>();

    /**
     * Constructor
     **/
    private ClientEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Factory Constructor
     * @return new ClientEventManager instance
     **/
    static public ClientEventManager createClientEventManager(){
        return new ClientEventManager( "updateData", "loginSend", "newGameSend", "loadGameSend", "loginReceived", "planningPhaseRecived", "actionPhase1Recived", "actionPhase2Recived", "actionPhase3Recived", "finish", "planningPhaseSend", "actionPhase1Send", "actionPhase2Send", "actionPhase3Send", "finishSend", "disconnection", "retryNickname", "waitAddPlayer", "expertPlayedSend" );
    }

    /**
     * Adding EventReciver instance to the subscriber List
     * @param eventType Event that the Client are interested in
     * @param listener Client that are interested in a some particular Event
     **/
    @Override
    public synchronized void subscribe(String eventType, EventReciver listener) {
        List<EventReciver> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Notify to some particular EventReciver that something is happened
     * @param eventType Client type to notify some event
     **/
    @Override
    public void notify(String eventType) {
        List<EventReciver> users = listeners.get(eventType);
        for (EventReciver listener : users) {
            listener.update(eventType);
        }
    }
}
