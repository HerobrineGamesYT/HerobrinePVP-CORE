package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class TrophyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("set")) {
						try {
							OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
							int trophies = Integer.parseInt(args[2]);
							if (target.hasPlayedBefore() || target.isOnline()) {
								HerobrinePVPCore.getFileManager().setTrophies(target.getPlayer(), trophies);
								player.sendMessage(ChatColor.GREEN + "Successfully set "
										+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
										+ target.getName() + ChatColor.GREEN + "'s trophy balance to " + ChatColor.GOLD
										+ HerobrinePVPCore.getFileManager().getTrophies(target.getUniqueId()));
								if (target.isOnline()) {
									target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f,
											1f);
									target.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
											"&6&lTROPHIES! &eYour trophy balance has been set to &6" + trophies));

									if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
											.getDisplayName().equalsIgnoreCase(
													ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

										target.getPlayer().getScoreboard().getTeam("trophies").setSuffix(
												ChatColor.translateAlternateColorCodes('&', "&6" + HerobrinePVPCore
														.getFileManager().getTrophies(target.getPlayer())));
									}
								}
							} else {
								player.sendMessage(
										ChatColor.RED + "That player has never played on the server before.");
							}
						}

						catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED
									+ "Invalid number of Trophies! Please make sure you put in an integer.");
						}

					}

					if (args[0].equalsIgnoreCase("add")) {
						try {
							OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
							int trophies = Integer.parseInt(args[2]);
							if (target.hasPlayedBefore() || target.isOnline()) {
								HerobrinePVPCore.getFileManager().addTrophies(target.getPlayer(), trophies);
								player.sendMessage(ChatColor.GREEN + "Successfully added " + ChatColor.GOLD + trophies
										+ ChatColor.GREEN + "to "
										+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
										+ target.getName() + ChatColor.GREEN + "'s trophy balance.");
								if (target.isOnline()) {
									target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f,
											1f);
									target.getPlayer()
											.sendMessage(ChatColor.translateAlternateColorCodes('&',
													"&6&lTROPHIES! &6" + trophies + ChatColor.YELLOW
															+ " has been added to your account. You now have &6")
													+ HerobrinePVPCore.getFileManager().getTrophies(target.getPlayer())
													+ ChatColor.GOLD + " trophies.");

									if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
											.getDisplayName().equalsIgnoreCase(
													ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

										target.getPlayer().getScoreboard().getTeam("trophies").setSuffix(
												ChatColor.translateAlternateColorCodes('&', "&6" + HerobrinePVPCore
														.getFileManager().getTrophies(target.getPlayer())));
									}
								}
							} else {
								player.sendMessage(
										ChatColor.RED + "That player has never played on the server before.");
							}
						}

						catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED
									+ "Invalid number of trophies! Please make sure you put in an integer.");
						}
					}

					if (args[0].equalsIgnoreCase("remove")) {
						try {
							OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
							int trophies = Integer.parseInt(args[2]);
							if (target.hasPlayedBefore() || target.isOnline()) {
								HerobrinePVPCore.getFileManager().removeTrophies(target.getPlayer(), trophies);

								player.sendMessage(ChatColor.GREEN + "Successfully removed " + ChatColor.GOLD + trophies
										+ ChatColor.GREEN + "from "
										+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
										+ target.getName() + ChatColor.GREEN + "'s trophy balance.");
								if (target.isOnline()) {
									target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.CAT_MEOW, 1f,
											0.5f);
									target.getPlayer()
											.sendMessage(ChatColor.translateAlternateColorCodes('&',
													"&6&lTROPHIES! &6" + trophies + ChatColor.YELLOW
															+ " have been removed from your account. You now have &6")
													+ HerobrinePVPCore.getFileManager().getTrophies(target.getPlayer())
													+ ChatColor.GOLD + " trophies.");

									if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
											.getDisplayName().equalsIgnoreCase(
													ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {
										// target.getPlayer()
										// .sendMessage(ChatColor.GOLD + "[DEBUG] You have the lobby scoreboard!");
										target.getPlayer().getScoreboard().getTeam("trophies").setSuffix(
												ChatColor.translateAlternateColorCodes('&', "&6" + HerobrinePVPCore
														.getFileManager().getTrophies(target.getPlayer())));
									}
								}
							} else {
								player.sendMessage(
										ChatColor.RED + "That player has never played on the server before.");
							}
						}

						catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED
									+ "Invalid number of trophies! Please make sure you put in an integer.");
						}
					}
				} else if (args.length == 2) {

					if (args[0].equalsIgnoreCase("get")) {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

						if (target.hasPlayedBefore() || target.isOnline()) {
							player.sendMessage(
									HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
											+ target.getName() + "'s" + ChatColor.GOLD + " Trophies: "
											+ HerobrinePVPCore.getFileManager().getTrophies(target.getUniqueId()));

						} else {
							player.sendMessage(ChatColor.RED + "That player has never played on the server before.");
						}
					}

				}

				else {
					player.sendMessage(ChatColor.RED
							+ "Invalid usage! Usage: /trophies <set/add/remove> <player> (amount)\nOR \n /trophies get <player>");

				}

			} else {
				player.sendMessage(ChatColor.RED + "You must be admin or higher to use this command!");
			}

		} else {
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("set")) {
					try {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						int trophies = Integer.parseInt(args[2]);
						if (target.hasPlayedBefore() || target.isOnline()) {
							HerobrinePVPCore.getFileManager().setTrophies(target.getUniqueId(), trophies);
							sender.sendMessage(ChatColor.GREEN + "Successfully set "
									+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
									+ target.getName() + ChatColor.GREEN + "'s trophy balance to " + ChatColor.GOLD
									+ HerobrinePVPCore.getFileManager().getTrophies(target.getUniqueId()));
							if (target.isOnline()) {
								target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f, 1f);
								target.getPlayer()
										.sendMessage(ChatColor.translateAlternateColorCodes('&',
												"&6&lTROPHIES! &eYour trophy balance has been set to " + ChatColor.GOLD
														+ trophies));

								if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
										.getDisplayName().equalsIgnoreCase(
												ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

									target.getPlayer().getScoreboard().getTeam("trophies").setSuffix(
											ChatColor.translateAlternateColorCodes('&', "&6" + HerobrinePVPCore
													.getFileManager().getTrophies(target.getPlayer())));
								}
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That player has never played on the server before.");
						}
					}

					catch (NumberFormatException e) {
						sender.sendMessage(
								ChatColor.RED + "Invalid number of trophies! Please make sure you put in an integer.");
					}

				}

				if (args[0].equalsIgnoreCase("add")) {
					try {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						int trophies = Integer.parseInt(args[2]);
						if (target.hasPlayedBefore() || target.isOnline()) {
							HerobrinePVPCore.getFileManager().addTrophies(target.getUniqueId(), trophies);
							sender.sendMessage(ChatColor.GREEN + "Successfully added " + ChatColor.GOLD + trophies
									+ ChatColor.GREEN + " to "
									+ HerobrinePVPCore.getFileManager().getRank(target.getPlayer()).getColor()
									+ target.getName() + ChatColor.GREEN + "'s trophy balance.");
							if (target.isOnline()) {
								target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f, 1f);
								target.getPlayer()
										.sendMessage(ChatColor.translateAlternateColorCodes('&',
												"&6&lTROPHIES! &6" + trophies + ChatColor.YELLOW
														+ " trophies has been added to your account. You now have &6")
												+ HerobrinePVPCore.getFileManager().getTrophies(target.getPlayer())
												+ ChatColor.GOLD + " trophies.");

								if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
										.getDisplayName().equalsIgnoreCase(
												ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

									target.getPlayer().getScoreboard().getTeam("trophies").setSuffix(
											ChatColor.translateAlternateColorCodes('&', "&6" + HerobrinePVPCore
													.getFileManager().getTrophies(target.getPlayer())));
								}
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That player has never played on the server before.");
						}
					}

					catch (NumberFormatException e) {
						sender.sendMessage(
								ChatColor.RED + "Invalid number of trophies! Please make sure you put in an integer.");
					}
				}

				if (args[0].equalsIgnoreCase("remove")) {
					try {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						int trophies = Integer.parseInt(args[2]);
						if (target.hasPlayedBefore() || target.isOnline()) {
							HerobrinePVPCore.getFileManager().removeTrophies(target.getUniqueId(), trophies);

							sender.sendMessage(ChatColor.GREEN + "Successfully removed " + ChatColor.GOLD + trophies
									+ ChatColor.GREEN + " from "
									+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
									+ target.getName() + ChatColor.GREEN + "'s trophy balance.");
							if (target.isOnline()) {
								target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.CAT_MEOW, 1f,
										0.5f);
								target.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
										"&6&lTROPHIES! &6" + trophies + ChatColor.YELLOW
												+ " trophies have been removed to your account. You now have &6")
										+ HerobrinePVPCore.getFileManager().getTrophies(target.getPlayer())
										+ ChatColor.GOLD + " trophies.");

								if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
										.getDisplayName().equalsIgnoreCase(
												ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

									target.getPlayer().getScoreboard().getTeam("trophies").setSuffix(
											ChatColor.translateAlternateColorCodes('&', "&6" + HerobrinePVPCore
													.getFileManager().getTrophies(target.getPlayer())));
								}

							}
						} else {
							sender.sendMessage(ChatColor.RED + "That player has never played on the server before.");
						}
					}

					catch (NumberFormatException e) {
						sender.sendMessage(
								ChatColor.RED + "Invalid number of trophies! Please make sure you put in an integer.");
					}
				}
			} else if (args.length == 2) {

				if (args[0].equalsIgnoreCase("get")) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

					if (target.hasPlayedBefore() || target.isOnline()) {
						sender.sendMessage(HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
								+ target.getName() + "'s" + ChatColor.GOLD + " Trophies:"
								+ HerobrinePVPCore.getFileManager().getTrophies(target.getUniqueId()));
					} else {
						sender.sendMessage(ChatColor.RED + "That player has never played on the server before.");
					}
				}

			}

			else {
				sender.sendMessage(ChatColor.RED
						+ "Invalid usage! Usage: /trophies <set/add/remove> <player> (amount)\nOR \n /trophies get <player>");

			}
		}
		return false;
	}

}
