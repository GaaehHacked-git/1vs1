package de.gaaehhacked;

import de.gaaehhacked.commands.Setup_CMD;
import de.gaaehhacked.countdowns.countdowns.LobbyCountDown;
import de.gaaehhacked.gamestate.GameState;
import de.gaaehhacked.gamestate.GameStateManager;
import de.gaaehhacked.listener.PlayerConnect;
import de.gaaehhacked.utils.SetupManager;
import de.gaaehhacked.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static File configFile;
    private static File messageFile;
    boolean upToDate;
    public GameStateManager gameStateManager;
    private static Main plugin;

    @Override
    public void onEnable() {
        if(SetupManager.getIsCompleted()) {
            this.plugin = this;
            gameStateManager = new GameStateManager(plugin);
            LobbyCountDown lobbyCountDown = new LobbyCountDown();
            lobbyCountDown.startIDL();
            if(!getDataFolder().exists()){
                getDataFolder().mkdirs();
            }

            if(!new File(getDataFolder() + "//spawns").exists()){
                new File("plugins//1vs1//spawns").mkdir();
            }

            if (!new File(getDataFolder(), "config.yml").exists()) {
                plugin.saveResource("config.yml", false);
            }

            if (!new File(getDataFolder(), "messages.yml").exists()) {
                plugin.saveResource("messages.yml", false);
            }
            configFile = new File(getDataFolder() + "//config.yml");
            messageFile = new File(getDataFolder() + "//messages.yml");
            gameStateManager.setGameStates(GameState.LOBBY_STATE);
            Bukkit.getPluginManager().registerEvents(new PlayerConnect(this), this);

            getCommand("setup").setExecutor(new Setup_CMD());
            checkForUpdates(true);
        }
    }

    @Override
    public void onDisable() {
        gameStateManager.stopCurrentGameState();
    }

    public static Main getPlugin() {
        return plugin;
    }

    public void checkForUpdates(boolean check){
        if(check){
            new UpdateChecker(this, 83031).getVersion(version -> {
                if(this.getDescription().getVersion().equalsIgnoreCase(version)) {
                    Bukkit.getLogger().info("[1vs1] Plugin is up to date.");
                    upToDate = true;
                }else{
                    Bukkit.getLogger().warning("=====================================");
                    Bukkit.getLogger().warning("[1vs1] Plugin is outdated!");
                    Bukkit.getLogger().warning("[1vs1] Latest version: " + version);
                    Bukkit.getLogger().warning("[1vs1] Your version: " + getDescription().getVersion());
                    Bukkit.getLogger().warning("=====================================");
                    upToDate = false;
                }
            });
        }
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static File getMessageFile() {
        return messageFile;
    }
}
