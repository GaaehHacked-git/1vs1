package de.gaaehhacked.countdowns.countdowns;

import de.gaaehhacked.Main;
import de.gaaehhacked.countdowns.CountDown;
import de.gaaehhacked.gamestate.GameState;
import de.gaaehhacked.listener.PlayerConnect;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LobbyCountDown extends CountDown {

    public int IDL_ID;
    public int TASK_ID;
    private int IDL_SEC = 15;
    private int CS_SEC = 30;
    public boolean hasRoundStated = false;
    boolean isIdle = false;
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Main.getMessageFile());

    @Override
    public void start() {
        isIdle = false;
        TASK_ID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch (CS_SEC){
                    case 30: case 15: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(cfg.getString("countdown.lobby").replace("%secounds%", "" + CS_SEC).replace("&", "§"));
                        break;
                    case 1:
                        Bukkit.broadcastMessage(cfg.getString("countdown.lobby2").replace("&", "§"));
                        break;
                    case 0:
                        Main.getPlugin().gameStateManager.setGameStates(GameState.INGAME_STATE);
                        for(Player p : PlayerConnect.getPlayers()){
                            p.setLevel(CS_SEC);
                        }
                        break;
                    default:
                        break;
                }
             CS_SEC --;
                for(Player p : PlayerConnect.getPlayers()){
                    p.setLevel(CS_SEC);
                    p.sendTitle(cfg.getString("countdown-title").replace("&", "§"), cfg.getString("countdown-title2").replace("&", "§").replace("%secounds%", "" + CS_SEC));
                }
            }
        }, 0, 20);
    }

    public void startIDL(){
        isIdle = true;
        IDL_ID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(isIdle) {
                    Bukkit.broadcastMessage(cfg.getString("countdown.idl").replace("&", "§").replace("%players%", "" + PlayerConnect.getPlayers()).replace("%min-players%", "" + GameState.MIN_PLAYERS));
                }
            }
        }, 0, 20 * IDL_SEC);
    }

    public void stopIDL(){
        Bukkit.getScheduler().cancelTask(IDL_ID);
    }

    @Override
    public void stop() {
        if(Bukkit.getScheduler().isCurrentlyRunning(TASK_ID))
        Bukkit.getScheduler().cancelTask(TASK_ID);
    }
}
