package me.x_skeletor.smp.enchantments;

import me.x_skeletor.smp.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InvisibilityEnch extends Enchantment implements Listener {

    ArrayList<String> use = new ArrayList<String>();

    public InvisibilityEnch(int id) {
        super(id);
    }

    private boolean hasArmorType(ItemStack item, Material type) {
        return (item == null ? false : item.getType() == type);
    }

    @EventHandler
    public void playerDamagePlayer(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            final Player victim = (Player) e.getEntity();
            if (victim.getInventory().getLeggings() == null) return;
            if (victim.getInventory().getLeggings().getItemMeta().getLore().contains("Invisibility I")) {
                // if (victim.getInventory().getLeggings().getEnchantmentLevel(this) == 1) {
                if (!use.contains(victim.getName())) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.hidePlayer(victim);
                        use.add(victim.getName());
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(), new Runnable() {
                            public void run() {
                                use.remove(victim.getName());
                            }
                        }, 10 * 20);
                    }
                    victim.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GREEN + "Invisibility I " + ChatColor.GRAY + "has been applied for " + ChatColor.GREEN + "2 seconds" + ChatColor.GRAY + ".");
                } else {
                    return;
                }
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(), new Runnable() {
                    public void run() {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(victim);
                        }
                    }
                }, 2 * 20);
            } else {
                if (victim.getInventory().getLeggings().getItemMeta().getLore().contains("Invisibility II")) {
                    if (!use.contains(victim.getName())) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.hidePlayer(victim);
                            use.add(victim.getName());
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(), new Runnable() {
                                public void run() {
                                    use.remove(victim.getName());
                                }
                            }, 7 * 20);
                        }
                        victim.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GREEN + "Invisibility II " + ChatColor.GRAY + "has been applied for " + ChatColor.GREEN + "4 seconds" + ChatColor.GRAY + ".");
                    } else {
                        return;
                    }
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(), new Runnable() {
                        public void run() {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.showPlayer(victim);
                            }
                        }
                    }, 4 * 20);
                }
            }
        }
    }

    @Override
    public boolean canEnchantItem(ItemStack arg0) {
        return true;
    }

    @Override
    public boolean conflictsWith(Enchantment arg0) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public String getName() {
        return "Invisibility";
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}