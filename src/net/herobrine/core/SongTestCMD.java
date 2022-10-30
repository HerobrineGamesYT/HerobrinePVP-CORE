package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SongTestCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);

			if (rank.getPermLevel() >= 9) {

				if (args.length == 0) {

					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /songtest <song>");
				}

				if (args.length == 1) {
					if (Songs.valueOf(args[0].toUpperCase()) != null) {

						SongPlayer.playSong(player, Songs.valueOf(args[0].toUpperCase()));

					} else {
						player.sendMessage(ChatColor.GOLD + "Invalid Song!");
						player.sendMessage(ChatColor.GREEN + "Songs List: ");
						for (Songs song : Songs.values()) {
							player.sendMessage(ChatColor.GREEN + "- " + ChatColor.GOLD + song.getName());

						}
					}
				}

				else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /songtest <song>");
					player.sendMessage(ChatColor.GREEN + "Songs List: ");
					for (Songs song : Songs.values()) {
						player.sendMessage(ChatColor.GREEN + "- " + ChatColor.GOLD + song.getName());

					}
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		} else {
			sender.sendMessage(ChatColor.RED + "You can only use this as a player!");
		}

		return false;
	}

}
