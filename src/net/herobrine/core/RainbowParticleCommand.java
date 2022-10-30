package net.herobrine.core;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RainbowParticleCommand implements CommandExecutor {

 //Unfinished.

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // check if the sender is a player and has perm level >= 9
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on")) {
                        player.sendMessage(ChatColor.GREEN + "Rainbow particles are now enabled!");
                       float red = 0;
                       float green = 0;
                       float blue = 0;

                        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                                EnumParticle.SPELL, true, (float) player.getLocation().getX(),
                                (float)  player.getLocation().getY(), (float) player.getLocation().getZ(), 0.0F, 0.0F, 0.0F, 0.0F,
                                1);
                    }
                    else if (args[0].equalsIgnoreCase("off")) {
                        player.sendMessage(ChatColor.RED + "Rainbow particles are now disabled!");
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /rainbowparticles <on/off>");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /rainbowparticles <on/off>");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
            }
        }




        return false;
    }
}
