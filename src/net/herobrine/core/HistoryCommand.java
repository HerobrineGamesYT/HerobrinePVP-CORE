package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HistoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >=7) {

                if (args.length < 1) {
                    player.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /history <player> OR /history <player> <type>");
                }
                else if (args.length == 1) {

                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                    if (target != null && HerobrinePVPCore.getFileManager().isUserRegistered(target.getUniqueId())) {

                        if (HerobrinePVPCore.getFileManager().hasBeenPunished(target.getUniqueId())) {
                            player.sendMessage(ChatColor.GOLD + "[DEBUG] " + HerobrinePVPCore.getFileManager().getPunishments(target.getUniqueId(), PunishmentType.BAN));
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "That player has no punishment history.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "This player has never played before.");
                    }



                }
                else if (args.length >= 2) {

                }



            }
            else {
                player.sendMessage(ChatColor.RED  + "You do not have permission to use this command!");
            }


        }


        return false;
    }
}
