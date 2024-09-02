package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			// this command is locked to only me. yep. only me. sorry!
			// If you have downloaded the plugin for use on your own server, feel free to change this. But I shall not.
			if (player.getUniqueId().toString().equals("052e237b-b8ae-4756-a6a4-db139fbf47a3")) {

				if (args.length <= 1) {

					player.sendMessage(
							ChatColor.RED + "Invalid usage! Usage: /sudo <player> <chat/command> <command/message>");
				}

				else {

					if (args[1].equalsIgnoreCase("chat") || args[1].equalsIgnoreCase("c")) {
						int al = args.length;
						StringBuilder sb = new StringBuilder(args[2]);
						for (int i = 3; i < al; i++) {
							sb.append(' ').append(args[i]);
							// message is sb.toString();

						}

						Player target = Bukkit.getPlayer(args[0]);

						if (target != null) {

							if (target.isOnline()) {

								target.chat(sb.toString());

							} else {
								player.sendMessage(ChatColor.RED + "That player is not online!");
							}
						} else {
							player.sendMessage(ChatColor.RED + "Invalid player!");
						}

					}

					else if (args[1].equalsIgnoreCase("command") || args[1].equalsIgnoreCase("cmd")) {
						int al = args.length;
						StringBuilder sb = new StringBuilder(args[2]);
						for (int i = 3; i < al; i++) {
							sb.append(' ').append(args[i]);
							// command is sb.toString();
						}

						Player target = Bukkit.getPlayer(args[0]);

						if (target != null) {

							if (target.isOnline()) {

								target.performCommand(sb.toString());

							} else {
								player.sendMessage(ChatColor.RED + "That player is not online!");
							}
						} else {
							player.sendMessage(ChatColor.RED + "Invalid player!");
						}
					}

				}

			} else {

				player.sendMessage(ChatColor.RED + "You do not have permission to use this!");

			}

		} else {
			sender.sendMessage(ChatColor.RED + "You cannot use this!");
		}

		return false;
	}

}
