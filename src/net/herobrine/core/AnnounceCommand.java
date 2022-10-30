package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnounceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /announce <message>");
				}

				if (args.length >= 1) {
					int al = args.length;
					StringBuilder sb = new StringBuilder(args[0]);
					for (int i = 1; i < al; i++) {
						sb.append(' ').append(args[i]);
						// msg is sb.toString();
					}
					Bukkit.broadcastMessage(ChatColor.RED + "[ANNOUCEMENT]" + ChatColor.WHITE + ": "
							+ ChatColor.translateAlternateColorCodes('&', sb.toString()));
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}
		}

		return false;
	}

}
