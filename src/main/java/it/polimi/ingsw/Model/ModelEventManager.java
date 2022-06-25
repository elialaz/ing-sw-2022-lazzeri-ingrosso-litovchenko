package it.polimi.ingsw.Model;

import it.polimi.ingsw.Event.EventManager;
import it.polimi.ingsw.Event.EventReciver;

import java.util.*;


/**
 * Event Manager for the entire model classes
 * @author elia_laz
 **/
public class ModelEventManager implements EventManager {

    private Map<String, List<EventReciver>> listeners = new HashMap<>();

    /**
     * Constructor
     **/
    private ModelEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Factory Constructor
     * @return new ModelEventManager instance
     **/
    static public ModelEventManager createModelEventManager(){
        return new ModelEventManager("playerJoin", "setupStart", "update");
    }

    /**
     * Adding EventReceiver instance to the subscriber List
     * @param eventType Event that the Client are interested in
     * @param listener Client that are interested in a some particular Event
     **/
    @Override
    public void subscribe(String eventType, EventReciver listener) {
        List<EventReciver> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Notify to some particular EventReceiver that something is happened
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
