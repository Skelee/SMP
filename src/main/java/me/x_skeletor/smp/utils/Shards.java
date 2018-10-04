package me.x_skeletor.smp.utils;

import me.x_skeletor.smp.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class Shards implements Listener {

    public Shards(Core main) {
    }

    public static ItemStack enchantmentShard = new ItemStack(Material.PRISMARINE_SHARD);


    @EventHandler
    public void onMobKill(EntityDeathEvent e) {
        Entity mob = (Entity) e.getEntity();
        if (mob instanceof Monster) {
            Random random = new Random();
            int shardChance = random.nextInt(100) + 1;
            if (shardChance <= 3) {
                ItemMeta enchantmentShardMeta = enchantmentShard.getItemMeta();
                ArrayList<String> enchantmentShardLore = new ArrayList<String>();
                enchantmentShardMeta.setDisplayName(ChatColor.GREEN + "Enchantment Shard");
                enchantmentShardLore.add(ChatColor.GRAY + "Use this shard at The Forge to");
                enchantmentShardLore.add(ChatColor.GRAY + "obtain new enchantments.");
                enchantmentShardMeta.setLore(enchantmentShardLore);
                enchantmentShard.setItemMeta(enchantmentShardMeta);
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), enchantmentShard);
            }
        }
    }
}