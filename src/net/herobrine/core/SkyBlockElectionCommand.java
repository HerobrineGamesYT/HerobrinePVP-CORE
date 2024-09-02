package net.herobrine.core;

import net.herobrine.gamecore.GameCoreMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SkyBlockElectionCommand implements CommandExecutor {

    private HerobrinePVPCore main;
    public SkyBlockElectionCommand(HerobrinePVPCore main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
           Player player = (Player) sender;

            if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {
                Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective obj = board.registerNewObjective("electionBoard", "dummy1");
                obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lSKYBLOCK"));
                obj.setDisplaySlot(DisplaySlot.SIDEBAR);


                Random rand = new Random();
                char c = (char)(rand.nextInt(26) + 'a');
                int randNum = rand.nextInt(99 + 1 - 12) + 12;
                DateFormat df = new SimpleDateFormat("MM/dd/yy");
                Date dateobj = new Date();
                Team dateAndID = board.registerNewTeam("dateandid");
                dateAndID.addEntry(ChatColor.DARK_RED.toString());
                dateAndID.setPrefix(ChatColor.GRAY + df.format(dateobj));
                dateAndID.setSuffix(ChatColor.DARK_GRAY + " m" + randNum + c);
                obj.getScore(ChatColor.DARK_RED.toString()).setScore(10);

                Score blank = obj.getScore(" ");
                blank.setScore(9);

                Score time = obj.getScore(ChatColor.WHITE + "Early Winter 10th");
                time.setScore(8);
                Score time2 = obj.getScore(ChatColor.GRAY + " 12:00pm" + ChatColor.YELLOW + " ☀");
                time2.setScore(7);

                Score region = obj.getScore(ChatColor.GRAY + "⏣ " + ChatColor.AQUA + "Election Room");
                region.setScore(6);

                Score blank2 = obj.getScore("   ");
                blank2.setScore(5);

                Score line1 = obj.getScore(ChatColor.GOLD + "Year 259 votes");
                line1.setScore(4);

                Score line2 = obj.getScore(ChatColor.GRAY + "Waiting for");
                line2.setScore(3);

                Score line3 = obj.getScore(ChatColor.GRAY + "your vote...");

                line3.setScore(2);

                Score blank3 = obj.getScore("  ");
                blank3.setScore(1);

                Score ip = obj.getScore(ChatColor.YELLOW + "alpha.hypixel.net");
                ip.setScore(0);

                player.setScoreboard(board);
                player.setMaxHealth(40.0);
                player.setHealth(40.0);



                new BukkitRunnable() {

                    @Override
                    public void run() {
                        GameCoreMain.getInstance().sendActionBar(player, "&c3200/3200❤   &a1195❈ Defense   &b634/634✎ Mana");
                    }
                }.runTaskTimer(main, 0L, 20L);

            }
            else player.sendMessage(ChatColor.RED + "You can't use this!");


        }


        return false;
    }


}
