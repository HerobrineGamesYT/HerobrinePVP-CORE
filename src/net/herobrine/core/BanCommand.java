package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.MOD)) {

				if (args.length == 0) {
					player.sendMessage(ChatColor.RED
							+ "Invalid usage! Usage: /ban <player> <reason>\nNOTE: You must provide a reason for the ban!");
				}
				if (args.length > 1) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					int al = args.length;
					StringBuilder sb = new StringBuilder(args[1]);
					for (int i = 2; i < al; i++) {
						sb.append(' ').append(args[i]);
						// reason is sb.toString();
					}
					if (target != null && HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

						HerobrinePVPCore.getFileManager().createBan(target.getUniqueId(), player.getName(),
								sb.toString());

						for (Player players : Bukkit.getOnlinePlayers()) {
							if (HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.OWNER)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.ADMIN)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.MOD)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.HELPER)) {
								Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);
								players.sendMessage(ChatColor.AQUA + "[STAFF] " + rank.getColor() + rank.getName() + " "
										+ player.getName() + ChatColor.WHITE + " has permanently banned "
										+ ChatColor.RED + target.getName() + ChatColor.WHITE + " for: "
										+ sb.toString());

							}
						}

						if (target.isOnline()) {
							Player onlineTarget = (Player) target;

							onlineTarget.kickPlayer(HerobrinePVPCore.translateString(
									"&cYou have been permanently banned from this server!\n&7Reason: &f"
											+ HerobrinePVPCore.getFileManager().getBanReason(target.getUniqueId(),
													HerobrinePVPCore.getFileManager()
															.getActivePunishmentId(target.getUniqueId()))
											+ "\n&7To find out more and appeal: &b&nhttps://discord.gg/dvyz32pMuQ\n&7Ban ID:&f "
											+ HerobrinePVPCore.getFileManager()
													.getActivePunishmentId(target.getUniqueId())));

						}

					} else {
						player.sendMessage(ChatColor.GRAY + "You cannot ban " + ChatColor.RED + args[0] + ChatColor.GRAY
								+ " as they have never played before or do not exist and therefore have never broken any rules on the server.");
					}

				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}

		else {
			sender.sendMessage("This command can only be executed by a player!");
		}

		return false;
	}

}
