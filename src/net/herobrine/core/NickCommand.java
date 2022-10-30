package net.herobrine.core;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {
	HashMap<UUID, String> oldNames = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /nick <name:reset>");

				} else if (args.length == 1) {

					if (args[0].equalsIgnoreCase("reset")) {
						if (oldNames.containsKey(player.getUniqueId())) {
							HerobrinePVPCore.changeName(oldNames.get(player.getUniqueId()), player);
							player.sendMessage(ChatColor.GREEN + "Your nickname has been reset!");
							oldNames.remove(player.getUniqueId());
						} else {
							player.sendMessage(ChatColor.RED + "You are not nicked!");
						}

					} else {

						if (args[0].chars().count() > 16) {
							player.sendMessage(
									ChatColor.RED + "You cannot have a nickname that is longer than 16 characters!");
						}

						else {

							if (oldNames.containsKey(player.getUniqueId())) {
								HerobrinePVPCore.changeName(args[0], player);
								player.sendMessage(ChatColor.GREEN + "Your nickname has been set to " + args[0]);
							} else {
								oldNames.put(player.getUniqueId(), player.getName());

								HerobrinePVPCore.changeName(args[0], player);
								player.sendMessage(ChatColor.GREEN + "Your nickname has been set to " + args[0]);
							}

						}

					}

				} else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /nick <name:reset>");
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		} else {
			sender.sendMessage(ChatColor.RED + "You cannot do this!");
		}

		return false;
	}

}
