package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnmuteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);

			if (rank.getPermLevel() >= 9) {

				if (args.length < 2) {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /unmute <player> <reason>");

				} else {
					int al = args.length;
					StringBuilder sb = new StringBuilder(args[1]);
					for (int i = 2; i < al; i++) {
						sb.append(' ').append(args[i]);
						// reason is sb.toString();
					}

					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					if (target != null && HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

						if (HerobrinePVPCore.getFileManager().isMuted(target.getUniqueId())) {

							HerobrinePVPCore.getFileManager().removeMute(target.getUniqueId(), player.getName(),
									sb.toString());

							player.sendMessage(ChatColor.GREEN + "The player " + ChatColor.GOLD + target.getName()
									+ ChatColor.GREEN + " has been unmuted successfully.");
						} else {
							player.sendMessage(ChatColor.GRAY + "You cannot unmute " + ChatColor.GOLD + target.getName()
									+ ChatColor.GRAY + " because they are not muted.");
						}
					} else {
						player.sendMessage(ChatColor.GRAY + "You cannot unmute " + ChatColor.GOLD + args[0]
								+ ChatColor.GRAY + " because they are an unknown player.");
					}
				}

			}

			else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}
		return false;
	}

}
