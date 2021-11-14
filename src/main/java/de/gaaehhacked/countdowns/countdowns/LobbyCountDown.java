package de.gaaehhacked.countdowns.countdowns;

import de.gaaehhacked.Main;
import de.gaaehhacked.countdowns.CountDown;
import de.gaaehhacked.gamestate.GameState;
import org.bukkit.Bukkit;

public class LobbyCountDown extends CountDown {

    private int IDL_ID;
    private int TASK_ID;
    private int IDL_SEC = 15;
    private int CS_SEC = 30;
    public boolean hasRoundStated = false;

    @Override
    public void start() {
        stopIDL();
        TASK_ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch (CS_SEC){
                    case 30: case 15: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage("Die Runde startet in: " + CS_SEC + " sekunden");
                        break;
                    case 1:
                        Bukkit.broadcastMessage("Die Runde startet in einer sekunden");
                        break;
                    case 0:
                        Main.getPlugin().gameStateManager.setGameStates(GameState.INGAME_STATE);
                        break;
                    default:
                        break;
                }
             CS_SEC --;
            }
        }, 0, 20);
    }

    public void startIDL(){
        IDL_ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Warte auf weitere Spieler...");
            }
        }, 0, 20 * IDL_SEC);
    }

    public void stopIDL(){
        if(Bukkit.getScheduler().isCurrentlyRunning(IDL_ID))
            Bukkit.getScheduler().cancelTask(IDL_ID);
    }

    @Override
    public void stop() {
        if(Bukkit.getScheduler().isCurrentlyRunning(TASK_ID))
        Bukkit.getScheduler().cancelTask(TASK_ID);
    }
}
