package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

				if (args.length <= 0) {
					player.sendMessage(ChatColor.RED + "Invalid Usage!");
					player.sendMessage(ChatColor.DARK_AQUA + "Leveling System Manager");
					player.sendMessage(ChatColor.DARK_AQUA + "/level help - Prints this message");
					player.sendMessage(ChatColor.DARK_AQUA + "/level get <player> - Get a player's level and XP.");
					player.sendMessage(ChatColor.DARK_AQUA + "/level xp set <player> <xp> - Set a player's XP.");
					player.sendMessage(ChatColor.DARK_AQUA + "/level xp add <player> <xp> - Give a player XP.");
					// player.sendMessage(ChatColor.DARK_AQUA + "/level set <player> <level> - Set a
					// player's level.");

				}

				if (args.length == 1) {

					if (args[0].equalsIgnoreCase("help")) {
						player.sendMessage(ChatColor.DARK_AQUA + "Leveling System Manager");
						player.sendMessage(ChatColor.DARK_AQUA + "/level help - Prints this message");
						player.sendMessage(ChatColor.DARK_AQUA + "/level get <player> - Get a player's level and XP.");
						player.sendMessage(ChatColor.DARK_AQUA + "/level xp set <player> <xp> - Set a player's XP.");
						player.sendMessage(ChatColor.DARK_AQUA + "/level xp add <player> <xp> - Give a player XP.");
						// player.sendMessage(ChatColor.DARK_AQUA + "/level set <player> <level> - Set a
						// player's level.");
					}

				}

				if (args.length == 2) {

					if (args[0].equalsIgnoreCase("get")) {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						if (target.hasPlayedBefore() || target.isOnline()
								|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

							player.sendMessage(ChatColor.DARK_AQUA + target.getName() + "'s Leveling Stats");

							player.sendMessage(ChatColor.DARK_AQUA + "Level: " + ChatColor.AQUA
									+ HerobrinePVPCore.getFileManager().getPlayerLevel(target.getUniqueId()));
							player.sendMessage(ChatColor.DARK_AQUA + "XP: " + ChatColor.AQUA
									+ HerobrinePVPCore.getFileManager().getPlayerXP(target.getUniqueId()) + "/"
									+ HerobrinePVPCore.getFileManager().getMaxXP(target.getUniqueId()));

						} else {
							player.sendMessage(ChatColor.DARK_AQUA + "That's not a valid player.");
						}
					}

				}

				if (args.length == 4) {
					@SuppressWarnings("deprecation")
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
					if (args[0].equalsIgnoreCase("xp")) {

						if (args[1].equalsIgnoreCase("set")) {

							if (target.hasPlayedBefore() || target.isOnline()
									|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

								try {
									HerobrinePVPCore.getFileManager().setPlayerXP(target.getUniqueId(),
											Integer.parseInt(args[3]));

									player.sendMessage(ChatColor.AQUA + "You just set " + target.getName() + "'s XP to "
											+ Integer.parseInt(args[3]));
									if (target.isOnline()) {
										target.getPlayer().sendMessage(ChatColor.AQUA
												+ "Your XP has been changed by an admin. Your level was not changed.");
									}
								} catch (NumberFormatException e) {
									player.sendMessage(
											ChatColor.RED + "Error! Did you tell us how much XP to give the player?");
								}

							}

						}

						else if (args[1].equalsIgnoreCase("add")) {

							if (target.hasPlayedBefore() || target.isOnline()
									|| HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

								try {
									HerobrinePVPCore.getFileManager().addPlayerXP(target.getUniqueId(),
											Integer.parseInt(args[3]));

									player.sendMessage(
											ChatColor.AQUA + "You just gave " + args[3] + " XP to " + target.getName());
									if (target.isOnline()) {
										target.getPlayer().sendMessage(
												ChatColor.AQUA + "You were just given some XP by an admin!");
									}
								} catch (NumberFormatException e) {
									player.sendMessage(
											ChatColor.RED + "Error! Did you tell us how much XP to give the player?");
								}

							} else {
								player.sendMessage(ChatColor.DARK_AQUA + "That's not a valid player.");
							}

						}

					}

				}
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}
		return false;
	}
}
