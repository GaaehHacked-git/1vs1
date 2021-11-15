package de.gaaehhacked.utils;

import de.gaaehhacked.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetupManager {

    public static Player p;
    private static int step;
    private static boolean setupStarted;
    private static Location spawn;
    private static Location spawnred;
    private static Location spawnblue;
    private static Boolean isCompleted;

    public static void startSetup(Player player){
        p = player;
        step = 1;
        p.sendMessage("Wilkommen im Setup von [1vs1] Bitte setze den Lobby-Spawn mit /setup lobbyspawn");
    }

    public static void finishSetup() throws IOException {
        new SpawnManager("spawn").createSpawn(spawn);
        new SpawnManager("spawn_blue").createSpawn(spawnblue);
        new SpawnManager("spawn_red").createSpawn(spawnred);
        p.sendMessage("Setup abgeschlossen! Der Server stoppt in 10 Sekunden neu!");
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().shutdown();
            }
        }, 20*10);
    }

    public static Boolean getIsCompleted() {
        if(new SpawnManager("spawn").ifSpawnExists()){
            return true;
        }else {
            return false;
        }
    }

    public static void setIsCompleted(Boolean isCompleted) {
        SetupManager.isCompleted = isCompleted;
    }

    public static void setSetupStarted(boolean setupStarted) {
        SetupManager.setupStarted = setupStarted;
    }

    public static boolean isSetupStarted() {
        return setupStarted;
    }

    public static int getStep() {
        return step;
    }

    public static void setStep(int step) {
        SetupManager.step = step;
    }

    public static Location getSpawnblue() {
        return spawnblue;
    }

    public static Location getSpawnred() {
        return spawnred;
    }

    public static void setSpawnblue(Location spawnblue) {
        spawnblue = spawnblue;
    }

    public static void setSpawnred(Location spawnred) {
        spawnred = spawnred;
    }

    public static void setSpawn(Location spawn) {
        SetupManager.spawn = spawn;
    }

    public static Location getSpawn() {
        return spawn;
    }
}
