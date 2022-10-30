package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnvironmentCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;


            if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

                if (args.length == 1) {




                    if (!args[0].equalsIgnoreCase("DEV") && !args[0].equalsIgnoreCase("PRODUCTION")) {
                        player.sendMessage(ChatColor.RED + "You can only set the environment to PRODUCTION or DEV");
                        player.sendMessage(ChatColor.RED + "You tried to set the environment to: " + args[0]);
                    }

                    else {
                        HerobrinePVPCore.getFileManager().setEnvironment(args[0].toUpperCase());
                        player.sendMessage(ChatColor.GREEN + "The environment was set to: " + ChatColor.GOLD + args[0].toUpperCase());
                        player.sendMessage(ChatColor.GREEN + "You may need to reload to see all changes take effect.");
                    }

                }
                else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /setenv <DEV/PRODUCTION>");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
            }




        }

        else {
            if (args.length == 1) {

                if (!args[0].equalsIgnoreCase("DEV") || !args[0].equalsIgnoreCase("PRODUCTION")) {
                    sender.sendMessage(ChatColor.RED + "You can only set the environment to PRODUCTION or DEV");
                }
                else {
                    HerobrinePVPCore.getFileManager().setEnvironment(args[0].toUpperCase());
                    sender.sendMessage(ChatColor.GREEN + "The environment was set to: " + ChatColor.GOLD + args[0].toUpperCase());
                    sender.sendMessage(ChatColor.GREEN + "You may need to reload to see all changes take effect.");
                }

            }
            else {
                sender.sendMessage(ChatColor.RED + "Invalid usage! Usage: /setenv <DEV/PRODUCTION>");
            }
        }


        return false;
    }
}
