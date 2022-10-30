package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KaboomCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
					|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

				if (args.length == 0) {

					World world = player.getWorld();

					for (Player victim : world.getPlayers()) {
						Vector vector = new Vector(0, 30, 0);
						victim.getPlayer().setVelocity(vector);
						world.strikeLightningEffect(victim.getLocation());
						player.sendMessage(ChatColor.GOLD + "Launched " + victim.getName() + "!");

					}

				}

				else if (args.length == 1) {

					if (args[0].equals("@a")) {

						for (Player victim : Bukkit.getOnlinePlayers()) {
							World world = victim.getWorld();
							Vector vector = new Vector(0, 30, 0);
							victim.getPlayer().setVelocity(vector);
							world.strikeLightningEffect(victim.getLocation());
							player.sendMessage(ChatColor.GOLD + "Launched " + victim.getName() + "!");
						}

						return false;
					}

					Player victim = Bukkit.getPlayer(args[0]);

					if (victim != null) {
						World world = victim.getWorld();
						Vector vector = new Vector(0, 30, 0);
						victim.getPlayer().setVelocity(vector);
						world.strikeLightningEffect(victim.getLocation());
						player.sendMessage(ChatColor.GOLD + "Launched " + victim.getName() + "!");

					}

				}

				else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /kaboom OR /kaboom <player>");
				}

			} else {
				player.sendMessage(ChatColor.RED + "You must be admin or higher to use this command!");
			}

		}

		else {
			sender.sendMessage(ChatColor.RED + "You can only use this command as a player!");
		}

		return false;
	}

}
