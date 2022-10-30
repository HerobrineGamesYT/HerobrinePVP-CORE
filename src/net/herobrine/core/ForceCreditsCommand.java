package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class ForceCreditsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			int permLevel = HerobrinePVPCore.getFileManager().getRank(player).getPermLevel();
			if (permLevel == 9) {

				if (args.length == 1) {

					Player target = Bukkit.getPlayer(args[0]);

					if (target != null) {

						if (target.isOnline()) {
							player.sendMessage(ChatColor.GREEN + "You just forced the Minecraft Credits on "
									+ ChatColor.GOLD + target.getName() + ChatColor.GREEN + "!");
							showMinecraftCredits(target);
						} else {
							player.sendMessage(ChatColor.GRAY + "You cannot force credits on " + ChatColor.GOLD
									+ args[0] + ChatColor.GRAY + " because they are not online!");
						}

					} else {
						player.sendMessage(ChatColor.GRAY + "You cannot force credits on " + ChatColor.GOLD + args[0]
								+ ChatColor.GRAY + " because they are not a valid player!");
					}

				} else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usagage: /forcecredits <player>");
				}

			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			}

		}
		return false;
	}

	public void showMinecraftCredits(Player p) {
		CraftPlayer craft = (CraftPlayer) p;
		EntityPlayer nms = craft.getHandle();

		nms.viewingCredits = true;
		nms.playerConnection.sendPacket(new PacketPlayOutGameStateChange(4, 0.0F));
	}

}
