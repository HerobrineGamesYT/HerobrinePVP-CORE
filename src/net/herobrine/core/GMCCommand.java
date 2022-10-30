package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)) {
				if (args.length == 1) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target != null) {
						target.setGameMode(GameMode.CREATIVE);
						target.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.GOLD
								+ "Creative" + ChatColor.GRAY + ".");
						player.sendMessage(
								ChatColor.GRAY + "You have set the gamemode of " + ChatColor.GOLD + target.getName()
										+ ChatColor.GRAY + " to " + ChatColor.GOLD + "Creative" + ChatColor.GRAY + ".");
					} else {
						player.sendMessage(ChatColor.GRAY + "You cannot set the gamemode of " + ChatColor.GOLD + args[0]
								+ ChatColor.GRAY + " because they are unknown or offline.");
					}
				} else if (args.length == 0) {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.GOLD + "Creative"
							+ ChatColor.GRAY + ".");
				} else {
					player.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /gmc OR /gmc <player>");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}
		return false;
	}

}
