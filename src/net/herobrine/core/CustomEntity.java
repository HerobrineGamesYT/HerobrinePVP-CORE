package net.herobrine.core;
import net.minecraft.server.v1_8_R3.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEntity extends EntityZombie {


    public CustomEntity(Location loc, Player player) {
        super(((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setEquipment(1, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_HELMET)));
        this.setEquipment(0, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_SWORD)));

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalLeapAtTarget(this, 5.0F));

        // it wants nms item stack idk what it is



    }


    // how do i fix this override
}
