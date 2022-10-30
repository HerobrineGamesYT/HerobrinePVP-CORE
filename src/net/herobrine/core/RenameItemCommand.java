package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

				if (args.length != 0) {
					if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
						int al = args.length;
						StringBuilder sb = new StringBuilder(args[0]);
						for (int i = 1; i < al; i++) {
							sb.append(' ').append(args[i]);
							// content is sb.toString();
						}

						String content = ChatColor.translateAlternateColorCodes('&', sb.toString());
						ItemStack item = player.getItemInHand();
						ItemMeta itemMeta = item.getItemMeta();
						itemMeta.setDisplayName(content);
						item.setItemMeta(itemMeta);
						player.updateInventory();
						player.sendMessage(ChatColor.GREEN + "Item name updated to " + content);
					} else {
						player.sendMessage(ChatColor.RED + "You aren't holding anything!");
					}
				}

				else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /rename <newname>");
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}

		else {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
		}

		return false;
	}

}
