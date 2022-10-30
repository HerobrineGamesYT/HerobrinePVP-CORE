package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmotesCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			player.sendMessage(Ranks.KING_PLUS.getColor() + Ranks.KING_PLUS.getName() + ChatColor.LIGHT_PURPLE + "+"
					+ ChatColor.GREEN + " has access to the following emotes:");
			for (Emotes emote : Emotes.values()) {
				if (!emote.isUnlockRequired() && emote.getPermLevel() == 5) {
					player.sendMessage(ChatColor.GOLD + emote.getKey()
							+ ChatColor.translateAlternateColorCodes('&', " &7- ") + emote.getDisplay());
				}

			}

			player.sendMessage(ChatColor.GREEN + "The following emotes are unlocked through the cosmetic shop:");

			for (Emotes emote : Emotes.values()) {
				if (emote.isUnlockRequired()) {
					player.sendMessage(ChatColor.GOLD + emote.getKey()
							+ ChatColor.translateAlternateColorCodes('&', " &7- ") + emote.getDisplay());
				}
			}

		}

		return false;
	}

}
