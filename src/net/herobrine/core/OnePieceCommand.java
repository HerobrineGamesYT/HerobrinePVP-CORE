package net.herobrine.core;

import net.herobrine.gamecore.GameCoreMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

public class OnePieceCommand implements CommandExecutor {

private HerobrinePVPCore main;

    public OnePieceCommand(HerobrinePVPCore main) {
    this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage(ChatColor.GREEN + "The " + ChatColor.AQUA + "one piece " + ChatColor.GREEN + "is real!");

            new BukkitRunnable() {
                public void run() {
                    GameCoreMain.getInstance().sendTitle(player, "&aCan we get much higher", "&b&k!!&r&6so high&r&b&k!!", 0, 1, 0);
                }
            }.runTaskLater(main, 20L);

            new BukkitRunnable() {
                public void run() {

                    long expiryTime = 10;

                    HerobrinePVPCore.getFileManager().createTempBan(player.getUniqueId(), "ONE_PIECE", "The one piece is real!", expiryTime);

                    Date d1 = new Date(HerobrinePVPCore.getFileManager().getBanTime(player.getUniqueId(),
                            HerobrinePVPCore.getFileManager().getActivePunishmentId(player.getUniqueId())) * 1000);

                    Date d2 = new Date(HerobrinePVPCore.getFileManager().getBanExpiryTime(player.getUniqueId(),
                            HerobrinePVPCore.getFileManager().getActivePunishmentId(player.getUniqueId())) * 1000);

                    player.kickPlayer(HerobrinePVPCore.translateString("&cYou have been temporarily banned for &f"
                                    + HerobrinePVPCore.dateDifference(d1, d2)
                                    + "&c from this server!\n &7Reason: &f" + "The one piece is real!"
                                    + "\n&7Find out more: &bhttps://discord.gg/dvyz32pMuQ\n&7Ban ID: &f"
                                    + HerobrinePVPCore.getFileManager()
                                    .getActivePunishmentId(player.getUniqueId())));
                }
            }.runTaskLater(main, 60L);
        }

        return false;
    }
}
