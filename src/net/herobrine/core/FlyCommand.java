package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.herobrine.gamecore.Manager;

public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

				if (args.length == 0) {
					if (player.getAllowFlight()) {
						player.setAllowFlight(false);
						player.sendMessage(ChatColor.GREEN + "You can no longer fly.");
					} else {
						player.setAllowFlight(true);
						player.sendMessage(ChatColor.GREEN + "You can now fly.");
					}
				}

				else if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);

					if (target != null) {

						if (target.isOnline()) {
							if (target.getAllowFlight()) {
								target.setAllowFlight(false);
								player.sendMessage(
										ChatColor.GOLD + target.getName() + ChatColor.GREEN + " can no longer fly.");
								target.sendMessage(ChatColor.GREEN + "You can no longer fly.");
							} else {
								target.setAllowFlight(true);
								player.sendMessage(
										ChatColor.GOLD + target.getName() + ChatColor.GREEN + " can now fly.");
								target.sendMessage(ChatColor.GREEN + "You can now fly.");
							}

						}

						else {
							player.sendMessage(ChatColor.GRAY + "You cannot give flight to " + ChatColor.GOLD
									+ target.getName() + ChatColor.GRAY + " because they are unknown or offline.");
						}

					}

					else {
						player.sendMessage(ChatColor.GRAY + "That is an unknown player!");
					}

				} else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /fly OR /fly <name>");
				}

			}

			else if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 2
					&& HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() < 9) {

				if (!Manager.isPlaying(player)) {
					if (player.getAllowFlight()) {
						player.setAllowFlight(false);
						player.sendMessage(ChatColor.GREEN + "You can no longer fly.");
					} else {
						player.setAllowFlight(true);
						player.sendMessage(ChatColor.GREEN + "You can now fly.");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You cannot use this command while in-game!");
				}

			}

			else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this!");
			}

		}

		return false;
	}

}
