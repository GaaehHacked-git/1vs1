package de.gaaehhacked.gamestate.states;

import de.gaaehhacked.Main;
import de.gaaehhacked.countdowns.countdowns.LobbyCountDown;
import de.gaaehhacked.gamestate.GameState;
import de.gaaehhacked.gamestate.GameStateManager;
import de.gaaehhacked.listener.PlayerConnect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class IngameState extends GameState {

    Main plugin;
    LobbyCountDown lobbyCountDown;
    public IngameState(Main plugin){
        this.plugin = plugin;
        this.lobbyCountDown = new LobbyCountDown();
    }

    @Override
    public void start() {
        lobbyCountDown.hasRoundStated = true;
        Bukkit.broadcastMessage("ingamestate");
        for(Player p : PlayerConnect.getPlayers()){
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
        }
    }

    @Override
    public void stop() {
        Main.getPlugin().gameStateManager.setGameStates(GameState.INGAME_STATE);
    }
}
