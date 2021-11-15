package de.gaaehhacked.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Configmanager  {

    File file;
    YamlConfiguration cfg;

    public Configmanager(String filename){
        file = new File("plugins//1vs1//configs//" + filename + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }
}
