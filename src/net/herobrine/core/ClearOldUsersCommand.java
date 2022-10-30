package net.herobrine.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ClearOldUsersCommand implements CommandExecutor {

    private HerobrinePVPCore main;

    public ClearOldUsersCommand(HerobrinePVPCore main) {
        this.main = main;
    }



    HashMap<UUID, Boolean> awaitingConfirmation = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;




            if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

                if(!awaitingConfirmation.containsKey(player.getUniqueId())) {
                    awaitingConfirmation.put(player.getUniqueId(), true);
                    player.sendMessage(HerobrinePVPCore.translateString("&c&lWARNING: &r&cThis will remove ALL players who haven't played in THREE MONTHS. Run the command again within 5 seconds if you are sure you want to do this."));
                    confirmTimer(player.getUniqueId());
                }
                else {
                    HerobrinePVPCore.getFileManager().removeOldUsers(player);
                    awaitingConfirmation.remove(player.getUniqueId());
                }

            }
            else {
                player.sendMessage(ChatColor.RED + "You cannot do this!");
            }


        }
        else {
            sender.sendMessage(ChatColor.RED + "This command is only usable in-game!");
        }



        return false;
    }


    public void confirmTimer(UUID uuid) {

        new BukkitRunnable() {

            @Override
            public void run() {

                if (awaitingConfirmation.containsKey(uuid)) {
                    awaitingConfirmation.remove(uuid);
                    Bukkit.getPlayer(uuid).sendMessage(ChatColor.GREEN + "Crisis averted!");
                }



            }

        }.runTaskLater(main, 100L);

    }
}
