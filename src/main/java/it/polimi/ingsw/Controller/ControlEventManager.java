package it.polimi.ingsw.Controller;
import it.polimi.ingsw.Event.EventManager;
import it.polimi.ingsw.Event.EventReciver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Control class to manage events
 * @author elia_laz
 **/
public class ControlEventManager implements EventManager {
    private final Map<String, List<EventReciver>> listeners = new HashMap<>();

    /**
     * Constructor
     **/
    private ControlEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Factory Constructor
     * @return new ClientEventManager instance
     **/
    static public ControlEventManager createControlEventManager(){
        return new ControlEventManager("nextmove", "muchPlayer", "gamerPlanningTurnNotify", "gamerActionTurnNotify", "movingMotherNatureGamerTurn", "selectCloudTile", "win");
    }

    /**
     * Adding EventReciver instance to the subscriber List
     * @param eventType Event that the Client are interested in
     * @param listener Client that are interested in a some particular Event
     **/
    @Override
    public void subscribe(String eventType, EventReciver listener) {
        List<EventReciver> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Notify to some particular EventReciver that something is appened
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
