package net.herobrine.core;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);

			if (rank.getPermLevel() >= 7) {

				if (args.length == 2) {

					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					int al = args.length;
					StringBuilder sb = new StringBuilder(args[1]);
					for (int i = 2; i < al; i++) {
						sb.append(' ').append(args[i]);
						// reason is sb.toString();
					}
					if (target != null && HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

						HerobrinePVPCore.getFileManager().createMute(target.getUniqueId(), player.getName(),
								sb.toString());

						for (Player players : Bukkit.getOnlinePlayers()) {
							if (HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.OWNER)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.ADMIN)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.MOD)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.HELPER)) {

								players.sendMessage(ChatColor.AQUA + "[STAFF] " + rank.getColor() + rank.getName() + " "
										+ player.getName() + ChatColor.WHITE + " has permanently muted " + ChatColor.RED
										+ target.getName() + ChatColor.WHITE + " for: " + sb.toString());

							}
						}

					} else {
						player.sendMessage(ChatColor.GRAY + "You cannot mute " + ChatColor.RED + args[0]
								+ ChatColor.GRAY
								+ " as they have never played before or do not exist and therefore have never broken any rules on the server.");
					}

				}

				else if (args.length >= 3) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					int al = args.length;
					StringBuilder sb = new StringBuilder(args[2]);
					for (int i = 3; i < al; i++) {
						sb.append(' ').append(args[i]);
						// reason is sb.toString();
					}
// ban time is in seconds, and is added to the current time in a UNIX timestamp
					long banTime = 0;
					if (args[1].endsWith("y") || args[1].endsWith("yr") || args[1].endsWith("years")) {
						int banYears = Integer.parseInt(args[1].replaceAll("[\\D]", ""));

						banTime = 31540000 * banYears;

					} else if (args[1].endsWith("mo") || args[1].endsWith("months")) {

						int banMonths = Integer.parseInt(args[1].replaceAll("[\\D]", ""));
						;
						banTime = 2628000 * banMonths;
					} else if (args[1].endsWith("d") || args[1].endsWith("days")) {

						int banDays = Integer.parseInt(args[1].replaceAll("[\\D]", ""));
						banTime = 86400 * banDays;
					} else if (args[1].endsWith("h") || args[1].endsWith("hr") || args[1].endsWith("hrs")
							|| args[1].endsWith("hours")) {

						int banHours = Integer.parseInt(args[1].replaceAll("[\\D]", ""));

						banTime = 3600 * banHours;
					} else if (args[1].endsWith("m") || args[1].endsWith("minutes")) {

						int banMinutes = Integer.parseInt(args[1].replaceAll("[\\D]", ""));

						banTime = 60 * banMinutes;

					} else if (args[1].endsWith("s") || args[1].endsWith("seconds")) {

						int banSeconds = Integer.parseInt(args[1].replaceAll("[\\D]", ""));

						banTime = banSeconds;
					}
					if (target != null && HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

						HerobrinePVPCore.getFileManager().createTempMute(target.getUniqueId(), player.getName(),
								sb.toString(), banTime);

						Date d1 = new Date(HerobrinePVPCore.getFileManager().getMuteTime(target.getUniqueId(),
								HerobrinePVPCore.getFileManager().getActivePunishmentId(target.getUniqueId())) * 1000);

						Date d2 = new Date(HerobrinePVPCore.getFileManager().getMuteExpiryTime(target.getUniqueId(),
								HerobrinePVPCore.getFileManager().getActivePunishmentId(target.getUniqueId())) * 1000);
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.OWNER)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.ADMIN)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.MOD)
									|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.HELPER)) {

								players.sendMessage(ChatColor.AQUA + "[STAFF] " + rank.getColor() + rank.getName() + " "
										+ player.getName() + ChatColor.WHITE + " has temporarily muted " + ChatColor.RED
										+ target.getName() + ChatColor.WHITE + " for "
										+ HerobrinePVPCore.dateDifference(d1, d2) + " for the reason: "
										+ sb.toString());

							}
						}

					} else {
						player.sendMessage(ChatColor.GRAY + "You cannot mute " + ChatColor.RED + args[0]
								+ ChatColor.GRAY
								+ " as they have never played before or do not exist and therefore have never broken any rules on the server.");
					}
				}

				else {
					player.sendMessage(ChatColor.RED
							+ "Invalid Usage! Usage: /mute <player> <reason> OR /mute <player> <duration> <reason>");
				}

			}

			else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}
		return false;
	}

}
