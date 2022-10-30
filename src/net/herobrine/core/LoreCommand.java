package net.herobrine.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoreCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

				if (player.getItemInHand() != null && !player.getItemInHand().getType().equals(Material.AIR)) {

					if (args.length < 1) {
						player.sendMessage(ChatColor.RED + "Invalid usage! Do /lore help to see all you can do!");
					}

					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("help")) {
							player.sendMessage(ChatColor.GREEN + "Lore Management System");
							player.sendMessage(ChatColor.GREEN + "- /lore add <lore>");
							player.sendMessage(ChatColor.GREEN + "- /lore set <line> <lore> (Starts from 0)");
							player.sendMessage(ChatColor.GREEN + "- /lore remove <line>");
							player.sendMessage(ChatColor.GREEN + "- /lore removeall");

						} else if (args[0].equalsIgnoreCase("removeall")) {

							if (player.getItemInHand().getItemMeta().getLore() == null) {
								player.sendMessage(ChatColor.RED + "Your item has no lore on it!");
							} else {
								ItemStack item = player.getItemInHand();

								ItemMeta meta = item.getItemMeta();
								List<String> lore = meta.getLore();

								lore.clear();

								meta.setLore(lore);
								item.setItemMeta(meta);

								player.sendMessage(ChatColor.GREEN + "Lore removed!");
								player.updateInventory();
							}

						} else {
							player.sendMessage(ChatColor.RED + "Invalid usage! Do /lore help to see all you can do!");
						}
					}

					else if (args.length > 1) {
						if (args[0].equalsIgnoreCase("add")) {

							if (args[1] != null) {
								ItemStack item = player.getItemInHand();
								ItemMeta meta = item.getItemMeta();
								List<String> lore = new ArrayList<>();

								if (meta.getLore() != null) {
									for (int i = 0; i < meta.getLore().size(); i++) {
										lore.add(meta.getLore().get(i));
									}

								}
								int al = args.length;
								StringBuilder sb = new StringBuilder(args[1]);
								for (int i = 2; i < al; i++) {
									sb.append(' ').append(args[i]);
									// reason is sb.toString();
								}
								lore.add(ChatColor.translateAlternateColorCodes('&', sb.toString()));

								meta.setLore(lore);

								item.setItemMeta(meta);
								player.updateInventory();
								player.sendMessage(ChatColor.GREEN + "Lore updated!");

							}

						} else if (args[0].equalsIgnoreCase("remove")) {

							if (args[1] != null || !player.getItemInHand().getItemMeta().getLore().isEmpty()) {

								try {

									int index = Integer.parseInt(args[1]);
									ItemStack item = player.getItemInHand();
									ItemMeta meta = item.getItemMeta();

									List<String> lore = new ArrayList<>();

									if (meta.getLore() != null) {
										for (int i = 0; i < meta.getLore().size(); i++) {
											lore.add(meta.getLore().get(i));
										}
									}
									lore.remove(index);

									meta.setLore(lore);

									item.setItemMeta(meta);
									player.updateInventory();
									player.sendMessage(ChatColor.GREEN + "Lore updated!");

								} catch (NumberFormatException e) {
									player.sendMessage(ChatColor.RED
											+ "Invalid line! Make sure you put in an integer (starting from 0).");
								}
							} else {
								player.sendMessage(
										ChatColor.RED + "Your item probably doesn't have any lore to remove.");
							}

						}

						if (args[0].equalsIgnoreCase("set")) {
							if (args[1] != null && args[2] != null) {

								try {

									int index = Integer.parseInt(args[1]);

									int al = args.length;
									StringBuilder sb = new StringBuilder(args[2]);
									for (int i = 3; i < al; i++) {
										sb.append(' ').append(args[i]);
										// reason is sb.toString();
									}

									ItemStack item = player.getItemInHand();
									ItemMeta meta = item.getItemMeta();
									List<String> lore = meta.getLore();

									lore.set(index, ChatColor.translateAlternateColorCodes('&', args[2]));

									meta.setLore(lore);

									item.setItemMeta(meta);
									player.updateInventory();
									player.sendMessage(ChatColor.GREEN + "Lore updated!");

								} catch (NumberFormatException e) {
									player.sendMessage(ChatColor.RED
											+ "Invalid line! Make sure you put in an integer (starting from 0).");
								}
							}
						}
					}

				} else {
					player.sendMessage(ChatColor.RED + "You aren't holding anything!");
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this!");
			}

		} else {
			sender.sendMessage(ChatColor.RED + "Only players can use this!");
		}

		return false;
	}

}
