package de.gaaehhacked.gamestate;

public abstract class GameState {

    public static int LOBBY_STATE = 0, INGAME_STATE = 1, ENDING_STATE = 2;
    public static int MIN_PLAYERS = 1, MAX_PLAYERS = 2;

    public abstract void start();
    public abstract void stop();
}
