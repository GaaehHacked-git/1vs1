package de.gaaehhacked.utils;

import de.gaaehhacked.Main;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private final Main plugin;
    private int ressourceID;
    public UpdateChecker(Main plugin, int ressourceID) {
        this.plugin = plugin;
        this.ressourceID = ressourceID;
    }

    public void getVersion(final Consumer<String> consumer){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ressourceID).openStream();
                Scanner scanner = new Scanner(inputStream)) {
                if(scanner.hasNext())
                    consumer.accept(scanner.next());
            } catch (IOException e){
                Main.getPlugin().getLogger().warning("Cannot look for updates: " + e.getMessage());
            }
        });
    }
}
