package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		sender.sendMessage(ChatColor.AQUA + "You can join our discord here: "
				+ ChatColor.translateAlternateColorCodes('&', "&9&nhttps://discord.gg/ahrWUc2"));

		return false;
	}

}
