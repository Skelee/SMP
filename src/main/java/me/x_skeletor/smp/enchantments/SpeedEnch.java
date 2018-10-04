package me.x_skeletor.smp.enchantments;

import me.x_skeletor.smp.Core;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedEnch implements Listener {

    private boolean hasArmorType(ItemStack item, Material type) {
        return (item == null ? false : item.getType() == type);
    }

    @EventHandler
    public void eventInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        PlayerInventory inventory = e.getPlayer().getInventory();
        if (hasArmorType(inventory.getBoots(), Material.DIAMOND_BOOTS) || hasArmorType(inventory.getBoots(), Material.IRON_BOOTS) || hasArmorType(inventory.getBoots(), Material.LEATHER_BOOTS)) {
            if (inventory.getBoots().getItemMeta().getLore().contains("Speed")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50000, 0));
            }
        }
        if (inventory.getBoots() == null || !inventory.getBoots().getItemMeta().getLore().contains("Speed")) {
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
        }
    }
}
