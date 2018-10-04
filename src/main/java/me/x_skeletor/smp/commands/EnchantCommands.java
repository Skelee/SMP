package me.x_skeletor.smp.commands;

import me.x_skeletor.smp.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EnchantCommands implements CommandExecutor, Listener {

    Inventory forgeEnchantmentsGUI = Bukkit.createInventory(null, 54, "The Forge - List");

    public EnchantCommands(Core main) {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("forge")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "In-game command only!");
                return true;
            }

            Player p = (Player) sender;
            ItemStack invis = new ItemStack(Material.NETHER_STAR);
            ItemMeta invisMeta = invis.getItemMeta();
            ArrayList<String> invisLore = new ArrayList<String>();
            invisMeta.setDisplayName("Invisibility");
            invisLore.add(ChatColor.YELLOW + "Level Range: " + ChatColor.WHITE + "I-II");
            invisLore.add("");
            invisLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
            invisLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Leggings");
            invisMeta.setLore(invisLore);
            invis.setItemMeta(invisMeta);
            forgeEnchantmentsGUI.setItem(0, invis);

            ItemStack speed = new ItemStack(Material.NETHER_STAR);
            ItemMeta speedMeta = speed.getItemMeta();
            ArrayList<String> speedLore  = new ArrayList<String>();
            speedMeta.setDisplayName(ChatColor.GREEN + "Speed");
            speedLore.add(ChatColor.YELLOW + "Level Range: " + ChatColor.WHITE + "N/A");
            speedLore.add("");
            speedLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
            speedLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Boots");
            speedMeta.setLore(speedLore);
            speed.setItemMeta(speedMeta);
            forgeEnchantmentsGUI.setItem(9, speed);

            ItemStack magnet = new ItemStack(Material.NETHER_STAR);
            ItemMeta magnetMeta = magnet.getItemMeta();
            ArrayList<String> magnetLore  = new ArrayList<String>();
            magnetMeta.setDisplayName(ChatColor.GREEN + "Magnet");
            magnetLore.add(ChatColor.YELLOW + "Level Range: " + ChatColor.WHITE + "N/A");
            magnetLore.add("");
            magnetLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
            magnetLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Chestplate");
            magnetMeta.setLore(magnetLore);
            magnet.setItemMeta(magnetMeta);
            forgeEnchantmentsGUI.setItem(18, magnet);

            p.openInventory(forgeEnchantmentsGUI);
            p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "For more information about the enchantments, please visit The Forge.");
        }
        return true;
    }

    @EventHandler
    public void onForgeListClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inventory = e.getInventory();
        if (inventory.getName().equals(forgeEnchantmentsGUI.getName())) {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 0);
        }
    }
}
