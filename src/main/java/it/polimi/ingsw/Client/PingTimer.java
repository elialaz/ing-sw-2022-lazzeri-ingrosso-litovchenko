package it.polimi.ingsw.Client;

import java.util.TimerTask;

public class PingTimer extends TimerTask {
    private ClientEventManager manager;

    public PingTimer(ClientEventManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        manager.notify("ping");
    }
}
