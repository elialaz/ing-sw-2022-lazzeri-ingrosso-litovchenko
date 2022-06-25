package it.polimi.ingsw.Event;


/**
 * Observer Pattern Subscriber Interface
 * @author elia_laz
 **/
public interface EventReciever {
    public void update(String eventType);
}
