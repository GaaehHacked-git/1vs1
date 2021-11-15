package de.gaaehhacked.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configmanager  {

    File file;
    YamlConfiguration cfg;

    public Configmanager(String filename){
        file = new File("plugins//1vs1//configs//" + filename + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        file.mkdirs();
    }

    public void create() throws IOException {
        if(!exists()){
            file.createNewFile();
        }
    }

    public boolean exists(){
        if(file.exists()){
            return true;
        }else {
            return false;
        }
    }

    public void delete(){
        if(exists()){
            file.delete();
        }
    }
}
