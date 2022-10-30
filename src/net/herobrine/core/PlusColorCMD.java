package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlusColorCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 5) {

				if (args.length == 1) {

					if (ChatColor.valueOf(args[0].toUpperCase()) != null) {
						try {
							ChatColor color = ChatColor.valueOf(args[0].toUpperCase());
							switch (color) {

							case AQUA:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case BLACK:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case BLUE:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case BOLD:
								player.sendMessage(ChatColor.RED + "Invalid Plus Color!");
								break;
							case DARK_AQUA:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case DARK_BLUE:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case DARK_GRAY:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case DARK_GREEN:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case DARK_PURPLE:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case DARK_RED:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case GOLD:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case GRAY:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case GREEN:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;

							case ITALIC:
								player.sendMessage(ChatColor.RED + "Invalid Plus Color!");
								break;
							case LIGHT_PURPLE:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case MAGIC:
								player.sendMessage(ChatColor.RED + "Invalid Plus Color!");
								break;
							case RED:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case RESET:
								player.sendMessage(ChatColor.RED + "Invalid Plus Color!");
								break;
							case STRIKETHROUGH:
								player.sendMessage(ChatColor.RED + "Invalid Plus Color!");
								break;
							case UNDERLINE:
								player.sendMessage(ChatColor.RED + "Invalid Plus Color!");
								break;
							case WHITE:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							case YELLOW:
								HerobrinePVPCore.getFileManager().setPlusColor(player, color);
								player.sendMessage(ChatColor.GREEN + "Set your plus color to: " + ChatColor.GOLD
										+ "KING" + color + "+");
								break;
							default:
								player.sendMessage(ChatColor.RED
										+ "Something went horribly wrong! I'm probably giving up on Plus Colors now.");
								break;

							}
						}

						catch (IllegalArgumentException e) {
							player.sendMessage(ChatColor.RED + "Invalid plus color!");
						}

					} else {
						player.sendMessage(ChatColor.RED + "Invalid Plus Color!");
					}

				} else {
					player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /rankcolor <color>");
					player.sendMessage(ChatColor.RED + "This command is in development. It will have a GUI when done.");
				}

			}

			else {
				player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}

		}

		return false;
	}

}
