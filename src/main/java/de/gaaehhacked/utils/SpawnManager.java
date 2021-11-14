package de.gaaehhacked.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnManager {

    File file;
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    String name;

    public SpawnManager(String name){
        this.name = name;
        new File("plugins//1vs1//spawns//" + name + ".yml");
        if(new File("plugins//1vs1//spawns").exists())
            new File("plugins//1vs1//spawns").mkdirs();
    }

    public Location getSpawn(){
        World world = Bukkit.getWorld(cfg.getString(name + ".world"));
        int x = cfg.getInt(cfg.getString(name + ".x"));
        int y = cfg.getInt(cfg.getString(name + ".y"));
        int z = cfg.getInt(cfg.getString(name + ".z"));
        long yaw = cfg.getLong(cfg.getString(name + ".yaw"));
        long pitch = cfg.getLong(cfg.getString(name + ".pitch"));
        Location location = new Location(world, x, y, z, yaw, pitch);

        return location;
    }

    public void createSpawn(Location location) throws IOException {

        cfg.addDefault(name + ".world", location.getWorld().getName());
        cfg.addDefault(name + ".x", location.getX());
        cfg.addDefault(name + ".y", location.getY());
        cfg.addDefault(name + ".z", location.getZ());
        cfg.addDefault(name + ".yaw", location.getYaw());
        cfg.addDefault(name + ".pitch", location.getPitch());
        cfg.save(file);
    }

    public void deleteSpawn(){
        if(ifSpawnExists())
            new File("plugins//1vs1//spawns//" + name + ".yml").delete();
    }

    public boolean ifSpawnExists(){
        if(new File("plugins//1vs1//spawns//" + name + ".yml").exists()){
            return true;
        }else {
            return false;
        }
    }
}
