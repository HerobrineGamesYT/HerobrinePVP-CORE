package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length <= 1) {
				player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /msg <player> <msg>");
			} else {
				if (HerobrinePVPCore.getFileManager().isMuted(player.getUniqueId())) {
					player.sendMessage(ChatColor.RED + "You cannot use this command while muted!");
				}

				else {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						int al = args.length;
						StringBuilder sb = new StringBuilder(args[1]);
						for (int i = 2; i < al; i++) {
							sb.append(' ').append(args[i]);
							// msg is sb.toString();
						}
						Ranks targetRank = HerobrinePVPCore.getFileManager().getRank(target);
						Ranks playerRank = HerobrinePVPCore.getFileManager().getRank(player);

						target.sendMessage(ChatColor.AQUA + "From " + playerRank.getColor() + playerRank.getName() + " "
								+ player.getName() + ChatColor.WHITE + ": " + sb.toString());
						player.sendMessage(ChatColor.AQUA + "To " + targetRank.getColor() + targetRank.getName() + " "
								+ target.getName() + ChatColor.WHITE + ": " + sb.toString());
					} else {
						player.sendMessage(ChatColor.RED + "That player is invalid or offline.");
					}
				}

			}
		}

		return false;
	}

}
