package net.herobrine.core;

import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

	// /rank <player> <rank>

	// when the code is sus
	
	/*
	⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀
	⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀
	⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀
	⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀
	⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀
	⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀
	⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀
	⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	
	*/
	
	
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
				|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

			if (args.length == 2) {
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				if (target.hasPlayedBefore() || target.isOnline()
						|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
					if (EnumUtils.isValidEnum(Ranks.class, args[1].toUpperCase())) {
						HerobrinePVPCore.getFileManager().setRank(target.getUniqueId(),
								Ranks.valueOf(args[1].toUpperCase()));
						player.sendMessage(ChatColor.GREEN + "You updated the rank of " + ChatColor.GOLD + args[0]
								+ ChatColor.GREEN + " to " + ChatColor.GOLD + args[1].toUpperCase());
						if (target.isOnline()) {
							target.getPlayer().sendMessage(ChatColor.GREEN + "Your rank has been updated to "
									+ ChatColor.GOLD + Ranks.valueOf(args[1].toUpperCase()));
							if (target.getPlayer().getScoreboard().getTeam("rank") != null) {
								if (HerobrinePVPCore.getFileManager().getRank(target.getPlayer()).hasPlusColor()) {

									target.getPlayer().getScoreboard().getTeam("rank").setSuffix(HerobrinePVPCore
											.getFileManager().getRank(target.getPlayer()).getColor()
											+ HerobrinePVPCore.getFileManager().getRank(target.getPlayer()).getName()
											+ HerobrinePVPCore.translateString(
													HerobrinePVPCore.getFileManager().getPlusColor(target.getUniqueId())
															+ "+"));

									target.getPlayer().setPlayerListName(HerobrinePVPCore.getFileManager()
											.getRank(target.getPlayer()).getColor()
											+ HerobrinePVPCore.getFileManager().getRank(target.getPlayer()).getName()
											+ HerobrinePVPCore.translateString(
													HerobrinePVPCore.getFileManager().getPlusColor(target.getUniqueId())
															+ "+")
											+ " "
											+ HerobrinePVPCore.getFileManager().getRank(target.getPlayer()).getColor()
											+ target.getName());
								}

								else {
									target.getPlayer().getScoreboard().getTeam("rank").setSuffix(HerobrinePVPCore
											.getFileManager().getRank(target.getPlayer()).getColor()
											+ HerobrinePVPCore.getFileManager().getRank(target.getPlayer()).getName());
									target.getPlayer().setPlayerListName(
											HerobrinePVPCore.getFileManager().getRank(target.getPlayer()).getColor()
													+ HerobrinePVPCore.getFileManager().getRank(target.getPlayer())
															.getName()
													+ " " + target.getName());
								}
							}

						}
					}
				} else {
					player.sendMessage(ChatColor.RED + "This player has never logged into the server before.");
				}

			} else {
				player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /rank <player> <rank>");
			}

		} else {
			player.sendMessage(ChatColor.RED + "You must be admin or higher to use this command!");

		}

		return false;
	}

}
