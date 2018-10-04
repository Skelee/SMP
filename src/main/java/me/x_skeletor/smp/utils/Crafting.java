package me.x_skeletor.smp.utils;

import java.util.ArrayList;

import me.x_skeletor.smp.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Crafting implements Listener {

    public Crafting(Core main) {
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        HumanEntity p = e.getWhoClicked();
        ItemStack current = e.getCurrentItem();
        ItemStack cursor = e.getCursor();
        if (p instanceof Player && e.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
            if (cursor.getType() == Material.NETHER_STAR) {
                if (cursor.getItemMeta() == null || cursor.getType() == null) return;
                if (cursor.getItemMeta().getLore().contains(ChatColor.GRAY + "Invisibility I")) {
                    if (current.getType() == Material.DIAMOND_LEGGINGS) {
                        p.setItemOnCursor(null);
                        ItemMeta invis1legmeta = current.getItemMeta();
                        ArrayList<String> invis1leglore = new ArrayList<String>();
                        invis1leglore.add(ChatColor.GRAY + "Invisibility I");
                        invis1legmeta.setLore(invis1leglore);
                        current.setItemMeta(invis1legmeta);
                        e.setCancelled(true);

                    }
                }
                if (cursor.getItemMeta() == null || cursor.getType() == null)
                    return;
                if (cursor.getItemMeta().getLore().contains(ChatColor.GRAY + "Invisibility II")) {
                    if (current.getType() == Material.DIAMOND_LEGGINGS) {
                        p.setItemOnCursor(null);
                        ItemMeta invis2legmeta = current.getItemMeta();
                        ArrayList<String> invis12eglore = new ArrayList<String>();
                        invis12eglore.add(ChatColor.GRAY + "Invisibility II");
                        invis2legmeta.setLore(invis12eglore);
                        current.setItemMeta(invis2legmeta);
                        e.setCancelled(true);
                    }
                }
                if (cursor.getItemMeta() == null || cursor.getType() == null)
                    return;
                if (cursor.getItemMeta().getLore().contains(ChatColor.GRAY + "Speed I")) {
                    if (current.getType() == Material.DIAMOND_BOOTS) {
                        p.setItemOnCursor(null);
                        ItemMeta invis2legmeta = current.getItemMeta();
                        ArrayList<String> invis12eglore = new ArrayList<String>();
                        invis12eglore.add(ChatColor.GRAY + "Speed I");
                        invis2legmeta.setLore(invis12eglore);
                        current.setItemMeta(invis2legmeta);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
