package it.polimi.ingsw.Event;

/**
 * Interface for Event Management
 * @author elia_laz
 **/
public interface EventManager {
    public void subscribe(String eventType, EventReciver listener);

    public void notify(String eventType);
}
