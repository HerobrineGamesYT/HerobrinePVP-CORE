package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpBuildAreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player)  {

            Player player = (Player) sender;

            if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {
                player.teleport(new Location(Bukkit.getWorld("dungeonTest"), -168.5, 4, 214.5));
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
                player.sendMessage(ChatColor.GREEN + "You warped to the dungeon build area!");
            }
            else {
                player.sendMessage(ChatColor.RED + "You can't use this!");
            }

        }

        return false;
    }
}
