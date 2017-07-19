package com.gcycloned.commands;

import com.gcycloned.init.Principal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class SetTutorial implements CommandExecutor
{
    Plugin plugin = ((Principal)Bukkit.getPluginManager().getPlugin("TutoCommand"));
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            World world = player.getWorld();
            String worldName = world.getName();
            worldName = worldName.replace("CraftWorld{name=", "");
            worldName = worldName.replace("}", "");
            if(player.hasPermission("tutorial.admin"))
            {
                if(plugin.getConfig().getBoolean("active"))//Si se ha colocado las coordenadas al plugin
                {
                    //Si quiere desactivar el comando /tutorial
                    plugin.getConfig().set("x", 0);
                    plugin.getConfig().set("y", 0);
                    plugin.getConfig().set("z", 0);
                    plugin.getConfig().set("world", "world");
                    plugin.getConfig().set("yaw", 0);
                    plugin.getConfig().set("pitch", 0);
                    plugin.getConfig().set("active", false);
                    plugin.saveConfig();
                    player.sendMessage("" + ChatColor.GREEN + "El spawn del comando" + ChatColor.GOLD + "/tutorial" + ChatColor.GREEN +" ha sido borrado.");
                }
                else
                {
                    if(world.toString().equals("CraftWorld{name=world}"))
                    {
                        //Si esta en el mundo correcto y no ha sido configurado aún lo configuramos
                        plugin.getConfig().set("x", player.getLocation().getX());
                        plugin.getConfig().set("y", player.getLocation().getY());
                        plugin.getConfig().set("z", player.getLocation().getZ());
                        plugin.getConfig().set("world", worldName);
                        plugin.getConfig().set("yaw", (player.getLocation().getYaw() % 360));
                        plugin.getConfig().set("pitch", player.getLocation().getPitch());
                        plugin.getConfig().set("active", true);
                        plugin.saveConfig();
                        player.sendMessage("" + ChatColor.GREEN + "El spawn del comando" + ChatColor.GOLD + "/tutorial" + ChatColor.GREEN +" ha sido configurado con éxito.");
                    }
                    else
                    {
                        player.sendMessage("" + ChatColor.DARK_RED + "No puedes colocar el spawn del tutorial estando en el nether o en el end.");
                    }
                }
            }
            else
            {
                player.sendMessage("" + ChatColor.DARK_RED + "No puedes usar este comando.");
            }
        }
        else
        {
            System.out.println("El comando /tutorial solamente funciona en el juego.");
        }
        return true;
    }
}
