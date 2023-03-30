package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
						if (HerobrinePVPCore.getFileManager().getValidHolograms().isEmpty()) {
							player.sendMessage(ChatColor.GRAY + "There are no holograms.");
							return false;
						}

						player.sendMessage(ChatColor.GREEN + "These are all of the holograms:");
						for (String holoName : HerobrinePVPCore.getFileManager().getValidHolograms()) {
							player.sendMessage(ChatColor.GREEN + "- " + ChatColor.GOLD + holoName);
						}

					}

				}

				else if (args.length >= 2) {

					if (args[0].equalsIgnoreCase("create")) {
						String holoName = args[1];

						if (args.length <= 2) {
							player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /hologram create <name> <content>");
							return false;
						}

						if (HerobrinePVPCore.getFileManager().getHologramContent(holoName) != null) {
							player.sendMessage(ChatColor.RED + "A hologram with that name already exists! Please choose another name!");
							return false;
						}

						int al = args.length;
						StringBuilder sb = new StringBuilder(args[2]);
						for (int i = 2; i < al; i++) {
							if (i != 2) sb.append(' ').append(args[i]);
							// content is sb.toString();
						}

						String content = ChatColor.translateAlternateColorCodes('&', sb.toString());

						ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

						stand.setVisible(false);
						stand.setCustomName(content);
						stand.setCustomNameVisible(true);
						stand.setGravity(false);

						HerobrinePVPCore.getFileManager().registerHologram(holoName, sb.toString(), stand.getLocation());

						player.sendMessage(ChatColor.GREEN + "Successfully created the " + ChatColor.GOLD + holoName + ChatColor.GREEN + " hologram!");

						}

						else if (args[0].equalsIgnoreCase("delete")) {

							if (HerobrinePVPCore.getFileManager().getHologramLocation(args[1]) == null) {
								player.sendMessage(ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid hologram!");
								return false;
							}

							Location loc = HerobrinePVPCore.getFileManager().getHologramLocation(args[1]);

							for(Entity ent : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {

								//if(HerobrinePVPCore.getFileManager().getEnvironment().equalsIgnoreCase("DEV")) player.sendMessage(ChatColor.GOLD + "[DEBUG] Checking entity " + ent.getType());
								if (ent instanceof ArmorStand) {
									ArmorStand stand = (ArmorStand) ent;

									if (stand.getCustomName().equals(ChatColor.translateAlternateColorCodes('&', HerobrinePVPCore.getFileManager().getHologramContent(args[1])))) {
								stand.remove();
								HerobrinePVPCore.getFileManager().removeHologram(args[1]);
								player.sendMessage(ChatColor.GREEN + "Successfully deleted hologram " + ChatColor.GOLD + args[1]);

								break;
							}
						}

					}



					}

					else if (args[0].equalsIgnoreCase("edit")) {
						if (HerobrinePVPCore.getFileManager().getHologramLocation(args[1]) == null) {
							player.sendMessage(ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid hologram!");
							return false;
						}

						Location loc = HerobrinePVPCore.getFileManager().getHologramLocation(args[1]);
						int al = args.length;
						StringBuilder sb = new StringBuilder(args[2]);
						for (int i = 2; i < al; i++) {
							if(i != 2) sb.append(' ').append(args[i]);
							// content is sb.toString();
						}

						String content = ChatColor.translateAlternateColorCodes('&', sb.toString());

						for(Entity ent : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
							if (ent instanceof ArmorStand) {
								ArmorStand stand = (ArmorStand) ent;

								if (stand.getCustomName().equals(ChatColor.translateAlternateColorCodes('&', HerobrinePVPCore.getFileManager().getHologramContent(args[1])))) {

									stand.setCustomName(content);
									HerobrinePVPCore.getFileManager().updateHologramContent(args[1], sb.toString());

									player.sendMessage(ChatColor.GREEN + "Successfully updated content for hologram " + ChatColor.GOLD + args[1]);

									break;
								}
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
