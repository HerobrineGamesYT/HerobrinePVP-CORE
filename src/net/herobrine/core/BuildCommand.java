package net.herobrine.core;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class BuildCommand implements CommandExecutor {
	public static ArrayList<Player> buildEnabledPlayers = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)) {

				if (args.length == 0) {
					if (buildEnabledPlayers.contains(player)) {
						buildEnabledPlayers.remove(player);
						player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						player.sendMessage(ChatColor.GREEN + "BUILD MODE has been disabled.");
					} else {
						buildEnabledPlayers.add(player);
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
						player.sendMessage(ChatColor.GREEN
								+ "BUILD MODE has been enabled. You can place and break blocks as well as edit your inventory.");

					}
				}

				else if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);

					if (target != null) {

						if (target.isOnline()) {
							if (buildEnabledPlayers.contains(target)) {
								buildEnabledPlayers.remove(target);
								player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
								player.sendMessage(ChatColor.GREEN + "BUILD MODE has been disabled for "
										+ ChatColor.GOLD + target.getName());

								target.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
								target.sendMessage(ChatColor.GREEN + "BUILD MODE has been disabled.");
							} else {
								buildEnabledPlayers.add(target);
								player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
								player.sendMessage(ChatColor.GREEN + "BUILD MODE has been enabled for " + ChatColor.GOLD
										+ target.getName() + ChatColor.GREEN
										+ ". They can place and break blocks as well as edit their inventory.");
								target.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
								target.sendMessage(ChatColor.GREEN
										+ "BUILD MODE has been enabled. You can place and break blocks as well as edit your inventory.");

							}
						}

					} else {
						player.sendMessage(ChatColor.RED + "That is an unknown player!");
					}

				} else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /build OR /build <name>");
				}

			} else {
				player.sendMessage(ChatColor.RED + "You must be admin or higher to use this command!");
			}

		} else {
			sender.sendMessage("This comamnd can only be executed by a player!");
		}

		return false;
	}

}
