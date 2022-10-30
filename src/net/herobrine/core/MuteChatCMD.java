package net.herobrine.core;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCMD implements CommandExecutor {

	public static ArrayList<UUID> toggleChat = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

				if (toggleChat.size() == 1) {

					toggleChat.remove(0);
					player.sendMessage(ChatColor.GREEN + "Chat has been enabled by " + player.getName());

				} else {
					toggleChat.add(player.getUniqueId());
					player.sendMessage(ChatColor.RED + "Chat has been disabled by " + player.getName());
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}

		else {
			sender.sendMessage(ChatColor.RED + "This command only works in-game!");
		}

		return false;
	}

	public static boolean isChatToggled() {

		if (toggleChat.size() == 1) {
			return true;
		} else {
			return false;
		}

	}

}
