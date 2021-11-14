package de.gaaehhacked.commands;

import de.gaaehhacked.utils.SetupManager;
import de.gaaehhacked.utils.SpawnManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Setup_CMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(SetupManager.isSetupStarted()) {
            if (args.length == 0) {

            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("lobbyspawn")) {
                    if (SetupManager.getStep() == 1) {
                        p.sendMessage("Du hast erfolgreich den Lobbyspawn gesetzt, bitte setze nun den Spawn für Team §4ROT §rmit /setup spawnrot");
                        SetupManager.setSpawn(p.getLocation());
                        SetupManager.setStep(2);
                    }
                }else if (args[0].equalsIgnoreCase("spawnrot")) {
                    if (SetupManager.getStep() == 2) {
                        p.sendMessage("Du hast erfolgreich den spawn für team Rot gesetzt, bitte setze nun den Spawn für Team §2BLAU §rmit /setup spawnblau");
                        SetupManager.setSpawnred(p.getLocation());
                        SetupManager.setStep(3);
                    }
                }else if (args[0].equalsIgnoreCase("spawnblau")) {
                    if (SetupManager.getStep() == 3) {
                        p.sendMessage("Du hast erfolgreich den spawn für team Rot gesetzt, bitte setze nun den Spawn für Team §2BLAU §rmit /setup spawnblau");
                        SetupManager.setSpawnblue(p.getLocation());
                        SetupManager.setSetupStarted(false);
                        SetupManager.setIsCompleted(true);
                        try {
                            SetupManager.finishSetup();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (args.length == 2) {

            }
        }else{
            p.sendMessage("Du bist aktuell nicht im Setup!");
        }
        return false;
    }
}
