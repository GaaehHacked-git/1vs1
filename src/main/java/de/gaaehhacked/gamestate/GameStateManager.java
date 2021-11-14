package de.gaaehhacked.gamestate;

import de.gaaehhacked.Main;
import de.gaaehhacked.gamestate.states.EndingState;
import de.gaaehhacked.gamestate.states.IngameState;
import de.gaaehhacked.gamestate.states.LobbyState;

import java.util.WeakHashMap;

public class GameStateManager {
    private GameState[] gameStates;
    private Main plugin;
    private GameState currentGameState;

    public GameStateManager(Main plugin){
        this.plugin = plugin;

        gameStates = new GameState[3];
        gameStates[GameState.LOBBY_STATE] = new LobbyState(plugin);
        gameStates[GameState.INGAME_STATE] = new IngameState(plugin);
        gameStates[GameState.ENDING_STATE] = new EndingState(plugin);
    }

    public void setGameStates(int GameStateID) {
        if(currentGameState != null)
            stopCurrentGameState();
            currentGameState = gameStates[GameStateID];
            currentGameState.start();
    }

    public void stopCurrentGameState(){
        if(currentGameState != null)
            currentGameState.stop();
    }

    public Main getPlugin() {
        return plugin;
    }
}
