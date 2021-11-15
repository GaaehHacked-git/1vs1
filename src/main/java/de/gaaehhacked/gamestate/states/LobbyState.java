package de.gaaehhacked.gamestate.states;

import de.gaaehhacked.Main;
import de.gaaehhacked.countdowns.countdowns.LobbyCountDown;
import de.gaaehhacked.gamestate.GameState;

public class LobbyState extends GameState {

    Main plugin;
    LobbyCountDown lobbyCountDown;

    public LobbyState(Main plugin){this.plugin = plugin;}

    @Override
    public void start() {
    }

    @Override
    public void stop() {

    }
}
