package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HologramCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

				if (args.length < 1) {

					player.sendMessage(ChatColor.RED + "Invalid usage! Do /hologram help to see your options.");
				}

				else if (args.length == 1) {

					if (args[0].equalsIgnoreCase("help")) {

						player.sendMessage(ChatColor.GREEN + "HBPVP Holograms");
						player.sendMessage(ChatColor.GREEN + "- /hologram help: Sends this help message.");
						player.sendMessage(ChatColor.GREEN
								+ "- /hologram create <holoname> <content>: Create a hologram with it's own unique name and text.");
						player.sendMessage(ChatColor.GREEN + "/hologram delete <holoname>: Remove a hologram.");
						player.sendMessage(ChatColor.GREEN
								+ "/hologram edit <holoname> <content>: Update an existing hologram's text.");
						player.sendMessage(ChatColor.GREEN + "/hologram list: List all existing holograms.");
						player.sendMessage(ChatColor.GREEN
								+ "/hologram refresh: Manually refresh all holograms, if they disappear for some reason.");
					}

					else if (args[0].equalsIgnoreCase("create")) {

						player.sendMessage(
								ChatColor.RED + "Invalid usage! Usage: /hologram create <holoname> <content>");

					}

					else if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove")) {

						player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /hologram delete <holoname>");

					}

					else if (args[0].equalsIgnoreCase("edit") || args[0].equalsIgnoreCase("update")) {

						player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /hologram edit <holoname> <content>");

					}

					else if (args[0].equalsIgnoreCase("list")) {

						// print out list of all holograms

					}

				}

				else if (args.length > 2) {

					if (args[0].equalsIgnoreCase("create")) {
						String holoName = args[1];

						int al = args.length;
						StringBuilder sb = new StringBuilder(args[2]);
						for (int i = 2; i < al; i++) {
							sb.append(' ').append(args[i]);
							// content is sb.toString();
						}

						String content = ChatColor.translateAlternateColorCodes('&', sb.toString());

					}

					else if (args[0].equalsIgnoreCase("delete")) {

					}

					else if (args[0].equalsIgnoreCase("edit")) {

					}

				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}

		return false;
	}

}
