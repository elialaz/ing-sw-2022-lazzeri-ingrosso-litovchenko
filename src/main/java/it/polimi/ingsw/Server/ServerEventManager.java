package it.polimi.ingsw.Server;

import it.polimi.ingsw.Event.EventManager;
import it.polimi.ingsw.Event.EventReceiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Event Manager for the Server
 * @author elia_laz
 **/
public class ServerEventManager implements EventManager {
    private Map<String, List<EventReceiver>> listeners = new HashMap<>();

    /**
     * Constructor of class ServerEventManager
     **/
    private ServerEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Factory Constructor
     * @return new ClientEventManager instance
     **/
    static public ServerEventManager createControlEventManager(){
        return new ServerEventManager("clientError", "LoginSuccess");
    }

    /**
     * Adding EventReceiver instance to the subscriber List
     * @param eventType Event that the Client are interested in
     * @param listener Client that are interested in a some particular Event
     **/
    @Override
    public void subscribe(String eventType, EventReceiver listener) {
        List<EventReceiver> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Notify to some particular EventReceiver that something is appended
     * @param eventType Client type to notify some event
     **/
    @Override
    public void notify(String eventType) {
        List<EventReceiver> users = listeners.get(eventType);
        for (EventReceiver listener : users) {
            listener.update(eventType);
        }
    }
}
