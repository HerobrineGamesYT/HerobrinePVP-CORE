package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lHerobrine PVP"));
			player.sendMessage(ChatColor.GREEN
					+ "Need Help? Click one of the options below. You can also ask an online staff member.");
			player.sendMessage(ChatColor.RED + "*" + ChatColor.AQUA + "What commands can I use?");
			player.sendMessage(ChatColor.RED + "*" + ChatColor.AQUA + "What is this server?");
			player.sendMessage(ChatColor.RED + "*" + ChatColor.AQUA + "Can you add x feature?");
			player.sendMessage(ChatColor.RED + "*" + ChatColor.AQUA + "Can I be staff?");
			player.sendMessage(ChatColor.RED + "*" + ChatColor.AQUA + "How do I support this server?");

		}

		return false;
	}

}
