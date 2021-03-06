package de.gaaehhacked.listener;

import de.gaaehhacked.Main;
import de.gaaehhacked.countdowns.countdowns.LobbyCountDown;
import de.gaaehhacked.gamestate.GameState;
import de.gaaehhacked.gamestate.GameStateManager;
import de.gaaehhacked.utils.SetupManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class PlayerConnect implements Listener {
    Main plugin;

    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<Player> caster = new ArrayList<Player>();
    GameStateManager gameStateManager;
    LobbyCountDown lobbyCountDown;
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(plugin.getMessageFile());

    public PlayerConnect(Main plugin){
        this.plugin = plugin;
        lobbyCountDown = new LobbyCountDown();
        gameStateManager = new GameStateManager(plugin);
    }

    @EventHandler
    public void onHandle(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        if(SetupManager.getIsCompleted()) {
            if (!lobbyCountDown.hasRoundStated) {
                if (players.size() < GameState.MAX_PLAYERS) {
                    Player p = e.getPlayer();
                    players.add(p);
                    Bukkit.broadcastMessage(cfg.getString("player.join").replace("%player%", e.getPlayer().getName()).replace("%player-size%", "" + players.size()).replace("%min-players%", "" + GameState.MIN_PLAYERS).replace("&", "§"));
                    if (players.size() >= GameState.MIN_PLAYERS) {
                        lobbyCountDown.stopIDL();
                        lobbyCountDown.start();
                    }
                } else {
                    e.getPlayer().setGameMode(GameMode.SPECTATOR);
                    e.getPlayer().sendMessage("Du bist nun im Spectator Modus, da die Runde bereits voll ist!");
                    caster.add(e.getPlayer());
                }
            } else {
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                e.getPlayer().sendMessage("Du bist nun im Spectator Modus, da die Runde bereits gestartet ist!");
                caster.add(e.getPlayer());
            }
        }else {
            SetupManager.startSetup(e.getPlayer());
        }
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static ArrayList<Player> getCaster() {
        return caster;
    }
}
