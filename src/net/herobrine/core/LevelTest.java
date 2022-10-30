package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

				if (HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()) == 100) {
					player.sendMessage(ChatColor.RED + "You are already max level!");
				}
				else {
					HerobrinePVPCore.getFileManager().levelUp(player.getUniqueId());
				}


			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this!");
			}

		} else {
			sender.sendMessage(ChatColor.RED + "This command can only be used ingame!");
		}

		return false;
	}

}
