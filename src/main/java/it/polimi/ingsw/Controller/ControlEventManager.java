package it.polimi.ingsw.Controller;
import it.polimi.ingsw.Event.EventManager;
import it.polimi.ingsw.Event.EventReciver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlEventManager implements EventManager {
    private Map<String, List<EventReciver>> listeners = new HashMap<>();

    /**
     * Constructor
     * @author elia_laz
     **/
    private ControlEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    //TODO
    /**
     * Factory Constructor
     * @author elia_laz
     * @return new ClientEventManager instance
     **/
    static public ControlEventManager createControlEventManager(){
        return new ControlEventManager("nextmove", "muchPlayer", "gamerPlanningTurnNotify", "gamerActionTurnNotify", "movingMotherNatureGamerTurn", "selectCloudTile", "win");
    }

    /**
     * Adding EventReciver instance to the subscriber List
     * @author elia_laz
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
     * @author elia_laz
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
