package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.herobrine.gamecore.ClassTypes;

public class UnlockItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;
			Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);

			if (rank.getPermLevel() >= 9) {

				if (args.length == 0) {
					player.sendMessage(ChatColor.RED
							+ "Invalid usage! Usage: /unlockitem <player> <type> <name> OR /unlockitem <type> <name>");

				}

				else if (args.length < 2) {
					player.sendMessage(ChatColor.RED
							+ "Invalid usage! Usage: /unlockitem <player> <type> <name> OR /unlockitem <type> <name>");
				}

				else if (args.length == 2) {

					String type = args[0];
					String item = args[1];

					if (type.equalsIgnoreCase("cosmetic")) {

						player.sendMessage(ChatColor.RED + "No cosmetics available now.");

					} else if (type.equalsIgnoreCase("emote")) {

						try {
							if (Emotes.valueOf(item.toUpperCase()) != null) {

								if (!Emotes.valueOf(item.toUpperCase()).isUnlockRequired()) {
									player.sendMessage(ChatColor.RED + "This emote does not require an unlock.");
									player.playSound(player.getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
								}
								else if (HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.EMOTE, item.toUpperCase(), player.getUniqueId())) player.sendMessage(ChatColor.GREEN + "You already have that emote unlocked!");

								else {
									HerobrinePVPCore.getFileManager().unlockItem(ItemTypes.EMOTE, item.toUpperCase(),
											player.getUniqueId());
									player.sendMessage(ChatColor.GREEN + "Successfully unlocked the "
											+ Emotes.valueOf(item.toUpperCase()).getDisplay() + ChatColor.GREEN
											+ " emote!");
									player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
								}

							} else {
								player.sendMessage(ChatColor.RED + "Invalid emote! Do /emotes to see all emotes.");
								player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
							}
						}
						catch(IllegalArgumentException e) {
							player.sendMessage(ChatColor.RED + "Invalid emote! Do /emotes to see all emotes.");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						}

					} else if (type.equalsIgnoreCase("class")) {
 						try{
						if (ClassTypes.valueOf(item.toUpperCase()) != null) {

							if (!ClassTypes.valueOf(item.toUpperCase()).isUnlockable()) {
								player.sendMessage(ChatColor.RED + "This class does not require an unlock.");
								player.playSound(player.getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
							} else if (HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.CLASS, item.toUpperCase(), player.getUniqueId()))
								player.sendMessage(ChatColor.GREEN + "You already have this class unlocked!");
							else {
								HerobrinePVPCore.getFileManager().unlockItem(ItemTypes.CLASS, item.toUpperCase(),
										player.getUniqueId());
								player.sendMessage(ChatColor.GREEN + "Successfully unlocked the "
										+ ClassTypes.valueOf(item.toUpperCase()).getDisplay() + ChatColor.GREEN
										+ " class!");
								player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
							}

						} else {
							player.sendMessage(ChatColor.RED + "Invalid class type!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						}

					}
						 catch(IllegalArgumentException e) {
							 player.sendMessage(ChatColor.RED + "Invalid class type!");
							 player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						}

					}
					else{
						player.sendMessage(ChatColor.RED + "Invalid item type! Valid Item Types: cosmetic,emote,class");
					}

				}

				else if (args.length == 3) {

					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					String type = args[1];
					String item = args[2];
					if (target.hasPlayedBefore() || target.isOnline()
							|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {
						if (type.equalsIgnoreCase("cosmetic")) {

							player.sendMessage(ChatColor.RED + "No cosmetics available now.");

						} else if (type.equalsIgnoreCase("emote")) {

							try {
								if (Emotes.valueOf(item.toUpperCase()) != null) {

									if (!Emotes.valueOf(item.toUpperCase()).isUnlockRequired()) {
										player.sendMessage(ChatColor.RED + "This emote does not require an unlock.");
										player.playSound(player.getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
									}
									else if (HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.EMOTE, item.toUpperCase(), target.getUniqueId()))
										player.sendMessage(ChatColor.GREEN + target.getName() + " already has this emote unlocked!");
									else {

										HerobrinePVPCore.getFileManager().unlockItem(ItemTypes.EMOTE, item.toUpperCase(),
												target.getUniqueId());
										player.sendMessage(ChatColor.GREEN + "Successfully unlocked the "
												+ Emotes.valueOf(item.toUpperCase()).getDisplay() + ChatColor.GREEN
												+ " emote for " + target.getName() + "!");

										player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);

										if (target.isOnline()) {
											target.getPlayer()
													.sendMessage(
															HerobrinePVPCore
																	.translateString("&6&lNICE! &eYou were just given the "
																			+ Emotes.valueOf(item.toUpperCase())
																			.getDisplay()
																			+ "&e emote! Try typing "
																			+ Emotes.valueOf(item.toUpperCase()).getKey())
																	+ " in chat!");
											target.getPlayer().playSound(target.getPlayer().getLocation(),
													Sound.SUCCESSFUL_HIT, 1f, 1f);
										}
									}

								} else {
									player.sendMessage(ChatColor.RED + "Invalid emote! Do /emotes to see all emotes.");
									player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
								}
							}
							catch(IllegalArgumentException e) {
								player.sendMessage(ChatColor.RED + "Invalid emote! Do /emotes to see all emotes.");
								player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
							}
						} else if (type.equalsIgnoreCase("class")) {
							try {
								if (ClassTypes.valueOf(item.toUpperCase()) != null) {

									if (!ClassTypes.valueOf(item.toUpperCase()).isUnlockable()) {
										player.sendMessage(ChatColor.RED + "This class does not require an unlock.");
									}
									else if (HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.CLASS, item.toUpperCase(), target.getUniqueId()))
										player.sendMessage(ChatColor.GREEN + target.getName() + " already has this class unlocked!");
									else {
										HerobrinePVPCore.getFileManager().unlockItem(ItemTypes.CLASS, item.toUpperCase(),
												target.getUniqueId());
										player.sendMessage(ChatColor.GREEN + "Successfully unlocked the "
												+ ClassTypes.valueOf(item.toUpperCase()).getDisplay() + ChatColor.GREEN
												+ " class for " + target.getName() + "!");
										player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
										if (target.isOnline()) {
											target.getPlayer()
													.sendMessage(HerobrinePVPCore
															.translateString("&6&lNICE! &eYou were just given the "
																	+ ClassTypes.valueOf(item.toUpperCase()).getDisplay())
															+ ChatColor.YELLOW + " class! Try using it in "
															+ ClassTypes.valueOf(item.toUpperCase()).getGame().getDisplay()
															+ ChatColor.YELLOW + "!");
											target.getPlayer().playSound(target.getPlayer().getLocation(),
													Sound.SUCCESSFUL_HIT, 1f, 1f);
										}

									}

								} else {
									player.sendMessage(ChatColor.RED + "Invalid class type!");
									player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
								}
							}
							catch(IllegalArgumentException e) {
								player.sendMessage(ChatColor.RED + "Invalid class type!");
								player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
							}
						} else {
							player.sendMessage(
									ChatColor.RED + "Invalid item type! Valid Item Types: cosmetic,emote,class");
						}

					} else {
						player.sendMessage(ChatColor.RED + "This player has never played before.");
					}

				}

				else {
					player.sendMessage(ChatColor.RED
							+ "Invalid usage! Usage: /unlockitem <player> <type> <name> OR /unlockitem <type> <name>");
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}

		else {
			sender.sendMessage(ChatColor.RED + "This is only available in-game at the moment!");
		}

		return false;
	}

}
