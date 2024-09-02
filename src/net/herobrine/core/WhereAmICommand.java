package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhereAmICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);
            if (rank.getPermLevel() >= 9) player.sendMessage(ChatColor.GREEN + "You are in " + ChatColor.GOLD + player.getLocation().getWorld().getName());
            else player.sendMessage(ChatColor.RED + "No permission!");
        }

        return false;
    }
}
