package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {
				if (args.length == 1) {
					try {
						float number = Integer.parseInt(args[0]);

						float speedNumber = number / 10;

						if (speedNumber > 1 || speedNumber < -1) {
							player.sendMessage(ChatColor.RED + "You can't set your speed to " + number + "!");
							return false;
						}
						if (player.isFlying()) {
							player.setFlySpeed(speedNumber);
							player.sendMessage(ChatColor.GREEN + "Your flying speed has been set to " + ChatColor.GOLD
									+ number + ChatColor.GREEN + "!");
						} else {
							if (number == 1) {
								speedNumber = 0.2F;
							}

							else if (number == 2) {
								speedNumber = 0.25F;
							}

							player.setWalkSpeed(speedNumber);
							player.sendMessage(ChatColor.GREEN + "Your walking speed has been set to " + ChatColor.GOLD
									+ number + ChatColor.GREEN + "!");

						}
					} catch (NumberFormatException e) {
						player.sendMessage(
								ChatColor.RED + "You can't set your speed to " + args[0] + ChatColor.RED + "!");
					}
				} else if (args.length == 2) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target != null) {
						try {
							float number = Integer.parseInt(args[1]);

							float speedNumber = number / 10;

							if (speedNumber > 1 || speedNumber < -1) {
								player.sendMessage(ChatColor.RED + "You can't set your speed to " + number + "!");
								return false;
							}

							if (target.isFlying()) {
								target.setFlySpeed(speedNumber);
								target.sendMessage(ChatColor.GREEN + "Your flying speed has been set to "
										+ ChatColor.GOLD + number + ChatColor.GREEN + " by " + ChatColor.GOLD
										+ player.getName() + ChatColor.GREEN + "!");
								player.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName()
										+ "'s " + ChatColor.GREEN + "flying speed to " + ChatColor.GOLD + number
										+ ChatColor.GREEN + "!");
							} else {
								if (number == 1) {
									speedNumber = 0.2F;
								}

								else if (number == 2) {
									speedNumber = 0.25F;
								}

								target.setWalkSpeed(speedNumber);
								target.sendMessage(ChatColor.GREEN + "Your walking speed has been set to "
										+ ChatColor.GOLD + number + ChatColor.GREEN + " by " + ChatColor.GOLD
										+ player.getName() + ChatColor.GREEN + "!");
								player.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName()
										+ "'s " + ChatColor.GREEN + "walking speed to " + ChatColor.GOLD + number
										+ ChatColor.GREEN + "!");
							}
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED + "You can't set " + target.getName() + "'s speed to "
									+ args[1] + ChatColor.RED + "!");
						}
					} else {
						player.sendMessage(ChatColor.GRAY + "You cannot set the move speed of " + ChatColor.GOLD
								+ args[0] + ChatColor.GRAY + " because they are unknown or offline.");
					}
				} else {
					player.sendMessage(
							ChatColor.RED + "Invalid usage! Valid usage: /speed <number> OR /speed <player> <number>");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You must be admin or higher to use this command!");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Only players can use this command!");
		}

		return false;
	}

}
