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

public class Tutorial implements CommandExecutor
{
    
    Plugin plugin = ((Principal)Bukkit.getPluginManager().getPlugin("TutoCommand"));
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            Location loc = null;
            double x = plugin.getConfig().getDouble("x");
            double y = plugin.getConfig().getDouble("y");
            double z = plugin.getConfig().getDouble("z");
            float yaw = (float) plugin.getConfig().getDouble("yaw");
            float pitch = (float) plugin.getConfig().getDouble("pitch");
            String worldName = plugin.getConfig().getString("world");
            World world = Bukkit.getServer().getWorld(worldName);
            System.out.println(yaw + "");
            if(plugin.getConfig().getBoolean("active"))//Si se ha colocado las coordenadas al plugin
            {
                if(world.toString().equals("CraftWorld{name=world}") || world.toString().equals("CraftWorld{name=BorrarMundo}"))
                {
                    loc = new Location(world, x,y,z);
                    loc.setYaw(yaw);
                    loc.setPitch(pitch);
                    player.teleport(loc);
                    
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName() + " subtitle [\"\",{\"text\":\"¡Bienvendio! Camina hacia adelante.\",\"color\":\"green\"}]"); //JSON formatting is invalid!
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName() + " title [\"\",{\"text\":\"Tutorial\",\"color\":\"gold\"}]");
                }
                else
                {
                    player.sendMessage("" + ChatColor.DARK_RED + "No puedes usar este comando estando en el nether o en el end.");
                }
            }
            else
            {
                player.sendMessage("" + ChatColor.DARK_RED + "El punto de spawn del tutorial aún no ha sido configurado.");
            }
        }
        else
        {
            System.out.println("El comando /tutorial solamente funciona en el juego.");
        }
        return true;
    }
}
