package it.polimi.ingsw.Model;

import it.polimi.ingsw.Event.EventManager;
import it.polimi.ingsw.Event.EventReceiver;

import java.util.*;


/**
 * Event Manager for all the model classes
 * @author elia_laz
 **/
public class ModelEventManager implements EventManager {

    private final Map<String, List<EventReceiver>> listeners = new HashMap<>();

    /**
     * Constructor Event Manager
     * @param operations strings of operation accepted
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
     * @param eventType Event that the Client is interested in
     * @param listener Client that is interested in a some particular Event
     **/
    @Override
    public void subscribe(String eventType, EventReceiver listener) {
        List<EventReceiver> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Notify to some particular EventReceiver that something is happened
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
