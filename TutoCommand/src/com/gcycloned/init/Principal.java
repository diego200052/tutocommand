package com.gcycloned.init;

import com.gcycloned.commands.SetTutorial;
import com.gcycloned.commands.Tutorial;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Principal extends JavaPlugin
{
    public FileConfiguration config = this.getConfig();
    
    @Override
    public void onLoad()
    {
        
    }
    
    @Override
    public void onEnable()
    {
        //Registramos el comando kit
        this.getCommand("tutorial").setExecutor(new Tutorial());
        this.getCommand("settutorial").setExecutor(new SetTutorial());
        
        //Revisamos si el config.yml existe
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            getLogger().info("config.yml no encontrado, creando...");
            this.getConfig().set("x", 0);
            this.getConfig().set("y", 0);
            this.getConfig().set("z", 0);
            this.getConfig().set("world", "world");
            this.getConfig().set("yaw", 0);
            this.getConfig().set("pitch", 0);
            this.getConfig().set("active", false);
            this.saveConfig();
        } else {
            getLogger().info("config.yml encontrado, cargando...");
        }
    }

    @Override
    public void onDisable()
    {
        
    }
}
