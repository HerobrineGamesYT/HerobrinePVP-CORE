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

public class CoinsCommand implements CommandExecutor {

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
							int coins = Integer.parseInt(args[2]);
							if (target.hasPlayedBefore() || target.isOnline()
									|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
								HerobrinePVPCore.getFileManager().setCoins(target.getUniqueId(), coins);
								player.sendMessage(ChatColor.GREEN + "Successfully set "
										+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
										+ target.getName() + ChatColor.GREEN + "'s coin balance to " + ChatColor.YELLOW
										+ HerobrinePVPCore.getFileManager().getCoins(target.getUniqueId()));
								if (target.isOnline()) {
									target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f,
											1f);
									target.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
											"&e&lCOINS! &eYour coin balance has been set to " + coins));

									target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f,
											1f);

									if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
											.getDisplayName().equalsIgnoreCase(
													ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

										target.getPlayer().getScoreboard().getTeam("coins").setSuffix(
												ChatColor.translateAlternateColorCodes('&', "&e" + HerobrinePVPCore
														.getFileManager().getCoins(target.getPlayer())));
									}
								}
							} else {
								player.sendMessage(
										ChatColor.RED + "That player has never played on the server before.");
							}
						}

						catch (NumberFormatException e) {
							player.sendMessage(
									ChatColor.RED + "Invalid number of coins! Please make sure you put in an integer.");
						}

					}

					if (args[0].equalsIgnoreCase("add")) {
						try {
							OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
							int coins = Integer.parseInt(args[2]);
							if (target.hasPlayedBefore() || target.isOnline()
									|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
								HerobrinePVPCore.getFileManager().addCoins(target.getUniqueId(), coins);
								player.sendMessage(ChatColor.GREEN + "Successfully added " + ChatColor.YELLOW + coins
										+ ChatColor.GREEN + "to "
										+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
										+ target.getName() + ChatColor.GREEN + "'s coin balance.");
								if (target.isOnline()) {
									target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f,
											1f);
									target.getPlayer()
											.sendMessage(ChatColor.translateAlternateColorCodes('&',
													"&e&lCOINS! &e" + coins + ChatColor.YELLOW
															+ " have been added to your account. You now have ")
													+ HerobrinePVPCore.getFileManager().getCoins(target.getPlayer())
													+ ChatColor.YELLOW + " coins.");

									if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
											.getDisplayName().equalsIgnoreCase(
													ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

										target.getPlayer().getScoreboard().getTeam("coins").setSuffix(
												ChatColor.translateAlternateColorCodes('&', "&e" + HerobrinePVPCore
														.getFileManager().getCoins(target.getPlayer())));
									}
								}
							} else {
								player.sendMessage(
										ChatColor.RED + "That player has never played on the server before.");
							}
						}

						catch (NumberFormatException e) {
							player.sendMessage(
									ChatColor.RED + "Invalid number of coins! Please make sure you put in an integer.");
						}
					}

					if (args[0].equalsIgnoreCase("remove")) {
						try {
							OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
							int coins = Integer.parseInt(args[2]);
							if (target.hasPlayedBefore() || target.isOnline()
									|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
								HerobrinePVPCore.getFileManager().removeCoins(target.getUniqueId(), coins);

								player.sendMessage(ChatColor.GREEN + "Successfully removed " + ChatColor.YELLOW + coins
										+ ChatColor.GREEN + "from "
										+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
										+ target.getName() + ChatColor.GREEN + "'s coin balance.");
								if (target.isOnline()) {
									target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.CAT_MEOW, 1f,
											0.5f);
									target.getPlayer()
											.sendMessage(ChatColor.translateAlternateColorCodes('&',
													"&e&lCOINS! &e" + coins + ChatColor.YELLOW
															+ " have been removed to your account. You now have ")
													+ HerobrinePVPCore.getFileManager().getCoins(target.getPlayer())
													+ ChatColor.YELLOW + " coins.");

									if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
											.getDisplayName().equalsIgnoreCase(
													ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

										target.getPlayer().getScoreboard().getTeam("coins").setSuffix(
												ChatColor.translateAlternateColorCodes('&', "&e" + HerobrinePVPCore
														.getFileManager().getCoins(target.getPlayer())));
									}
								}
							} else {
								player.sendMessage(
										ChatColor.RED + "That player has never played on the server before.");
							}
						}

						catch (NumberFormatException e) {
							player.sendMessage(
									ChatColor.RED + "Invalid number of coins! Please make sure you put in an integer.");
						}
					}
				} else if (args.length == 2) {

					if (args[0].equalsIgnoreCase("get")) {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

						if (target.hasPlayedBefore() || target.isOnline()
								|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
							player.sendMessage(
									HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
											+ target.getName() + "'s" + ChatColor.YELLOW + " coins: "
											+ HerobrinePVPCore.getFileManager().getCoins(target.getUniqueId()));
						} else {
							player.sendMessage(ChatColor.RED + "That player has never played on the server before.");
						}
					}

				}

				else {
					player.sendMessage(ChatColor.RED
							+ "Invalid usage! Usage: /coins <set/add/remove> <player> (amount)\nOR \n /coins get <player>");

				}

			} else {
				player.sendMessage(ChatColor.RED + "You must be admin or higher to use this command!");
			}

		} else {
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("set")) {
					try {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						int coins = Integer.parseInt(args[2]);
						if (target.hasPlayedBefore() || target.isOnline()
								|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
							HerobrinePVPCore.getFileManager().setCoins(target.getUniqueId(), coins);
							sender.sendMessage(ChatColor.GREEN + "Successfully set "
									+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
									+ target.getName() + ChatColor.GREEN + "'s coin balance to " + ChatColor.YELLOW
									+ HerobrinePVPCore.getFileManager().getCoins(target.getUniqueId()));
							if (target.isOnline()) {
								target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f, 1f);
								target.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
										"&e&lCOINS! &eYour coin balance has been set to " + coins));

								if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
										.getDisplayName().equalsIgnoreCase(
												ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

									target.getPlayer().getScoreboard().getTeam("coins")
											.setSuffix(ChatColor.translateAlternateColorCodes('&', "&e"
													+ HerobrinePVPCore.getFileManager().getCoins(target.getPlayer())));
								}
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That sender has never played on the server before.");
						}
					}

					catch (NumberFormatException e) {
						sender.sendMessage(
								ChatColor.RED + "Invalid number of coins! Please make sure you put in an integer.");
					}

				}

				if (args[0].equalsIgnoreCase("add")) {
					try {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						int coins = Integer.parseInt(args[2]);
						if (target.hasPlayedBefore() || target.isOnline()
								|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
							HerobrinePVPCore.getFileManager().addCoins(target.getUniqueId(), coins);
							sender.sendMessage(ChatColor.GREEN + "Successfully added " + ChatColor.YELLOW + coins
									+ ChatColor.GREEN + " to "
									+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
									+ target.getName() + ChatColor.GREEN + "'s coin balance.");
							if (target.isOnline()) {
								target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1f, 1f);
								target.getPlayer()
										.sendMessage(ChatColor.translateAlternateColorCodes('&',
												"&e&lCOINS! &e" + coins + ChatColor.YELLOW
														+ " have been added to your account. You now have ")
												+ HerobrinePVPCore.getFileManager().getCoins(target.getPlayer())
												+ ChatColor.YELLOW + " coins.");

								if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
										.getDisplayName().equalsIgnoreCase(
												ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

									target.getPlayer().getScoreboard().getTeam("coins")
											.setSuffix(ChatColor.translateAlternateColorCodes('&', "&e"
													+ HerobrinePVPCore.getFileManager().getCoins(target.getPlayer())));
								}
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That sender has never played on the server before.");
						}
					}

					catch (NumberFormatException e) {
						sender.sendMessage(
								ChatColor.RED + "Invalid number of coins! Please make sure you put in an integer.");
					}
				}

				if (args[0].equalsIgnoreCase("remove")) {
					try {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						int coins = Integer.parseInt(args[2]);
						if (target.hasPlayedBefore() || target.isOnline()
								|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
							HerobrinePVPCore.getFileManager().removeCoins(target.getUniqueId(), coins);
							sender.sendMessage(ChatColor.GREEN + "Successfully removed " + ChatColor.YELLOW + coins
									+ ChatColor.GREEN + " from "
									+ HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
									+ target.getName() + ChatColor.GREEN + "'s coin balance.");
							if (target.isOnline()) {
								target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.CAT_MEOW, 1f,
										0.5f);
								target.getPlayer()
										.sendMessage(ChatColor.translateAlternateColorCodes('&',
												"&e&lCOINS! &e" + coins + ChatColor.YELLOW
														+ " have been removed from your account. You now have ")
												+ HerobrinePVPCore.getFileManager().getCoins(target.getPlayer())
												+ ChatColor.YELLOW + " coins.");

								if (target.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR)
										.getDisplayName().equalsIgnoreCase(
												ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

									target.getPlayer().getScoreboard().getTeam("coins")
											.setSuffix(ChatColor.translateAlternateColorCodes('&', "&e"
													+ HerobrinePVPCore.getFileManager().getCoins(target.getPlayer())));
								}
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That sender has never played on the server before.");
						}
					}

					catch (NumberFormatException e) {
						sender.sendMessage(
								ChatColor.RED + "Invalid number of coins! Please make sure you put in an integer.");
					}
				}
			} else if (args.length == 2) {

				if (args[0].equalsIgnoreCase("get")) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

					if (target.hasPlayedBefore() || target.isOnline()
							|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
						sender.sendMessage(HerobrinePVPCore.getFileManager().getRank(target.getUniqueId()).getColor()
								+ target.getName() + "'s " + ChatColor.YELLOW + " coins: "
								+ HerobrinePVPCore.getFileManager().getCoins(target.getUniqueId()));
					} else {
						sender.sendMessage(ChatColor.RED + "That sender has never played on the server before.");
					}
				}

			}

			else {
				sender.sendMessage(ChatColor.RED
						+ "Invalid usage! Usage: /coins <set/add/remove> <sender> (amount)\nOR \n /coins get <sender>");

			}
		}

		return false;
	}

}
