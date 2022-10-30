package net.herobrine.core;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class ShowDemoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

       if (sender instanceof Player) {

           Player player = (Player) sender;

           if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 9) {

               if (args.length == 1) {
                   Player target = Bukkit.getPlayer(args[0]);
                   if (target != null && target.isOnline()) {

                       CraftPlayer craftTarget = (CraftPlayer) target;
                       // testing header footer packet too cause why not!
                       IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "&cWelcome to Herobrine PVP!" + "\"}"));
                       IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "&cHope you enjoy your stay!" + "\"}"));
                       craftTarget.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 0F));
                       PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(header);

                       // should i move it above the send ones too
                       try {
                           Field field = packet.getClass().getDeclaredField("b");
                           field.setAccessible(true);
                           field.set(packet, footer);

                       } catch (Exception e) {
                           e.printStackTrace();
                       }

                       craftTarget.getHandle().playerConnection.sendPacket(packet);

                   }
                   else {
                       player.sendMessage(ChatColor.RED + args[0] + " is either offline or invalid so this operation could not be completed.");
                   }

               }
               else {
                   player.sendMessage(ChatColor.RED + "Invalid usage! Usage: /showdemo <player>");
               }

           }
           else {
               player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
           }

       }

        return false;
    }
}
