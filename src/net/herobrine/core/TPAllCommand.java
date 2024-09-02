package net.herobrine.core;

import net.herobrine.gamecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (!Manager.isPlaying(player1)) player1.teleport(player.getLocation());

                }
                player.sendMessage(ChatColor.GREEN + "Teleporting all players...");

            }

            else {
                player.sendMessage(ChatColor.RED + "No permission!");
            }
        }

        return false;
    }
}
