package me.x_skeletor.smp.consumables;

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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Regen implements Listener, CommandExecutor {

    public Regen(Core main) {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "In-game command only!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("consumable")) {
            if (!sender.hasPermission("RANK.HOST")) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "Permissions> " + ChatColor.GRAY + "This requires Permission Rank [" + ChatColor.DARK_PURPLE + "HOST" + ChatColor.GRAY + "].");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Host> " + ChatColor.GRAY + "Usage: " + ChatColor.YELLOW + "/consumable <consumableType>");
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Host> " + ChatColor.GRAY + "Available types:");
                sender.sendMessage(ChatColor.DARK_PURPLE + "  ● " + ChatColor.GRAY + "REGEN");
                return true;
            }
            if (args.length == 1) {
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("regen")) {
                    ItemStack regen = new ItemStack(Material.BLAZE_POWDER);
                    ItemMeta regenMeta = regen.getItemMeta();
                    ArrayList<String> lore = new ArrayList<String>();
                    regenMeta.setDisplayName(ChatColor.GREEN + "Regeneration");
                   // lore.add(ChatColor.GRAY + Main.ench.getName() + " I");
                    regenMeta.setLore(lore);
                    regen.setItemMeta(regenMeta);
                    //regen.addUnsafeEnchantment(Main.ench, 1);
                    p.getInventory().addItem(regen);
                    Bukkit.broadcast(ChatColor.RED + "" + ChatColor.BOLD + "Host> " + ChatColor.GRAY + "Gave" + ChatColor.GREEN + " 1 " + "Regeneration" + ChatColor.GRAY + " consumable to " + ChatColor.YELLOW + p.getName() + ".", "RANK.HOST");
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onConsume(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack regen = p.getInventory().getItemInHand();
        if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (regen.getType() == Material.BLAZE_POWDER) {
                if (regen.hasItemMeta()) {
                    ItemMeta meta = regen.getItemMeta();
                    if (meta.getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Regeneration")) {
                        regen.setAmount(regen.getAmount() - 1);
                    } else {
                        p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
                    }
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 1));
                    p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GREEN + "Regeneration 2 " + ChatColor.GRAY + "has been applied for " + ChatColor.GREEN + "10 seconds" + ChatColor.GRAY + ".");
                    p.playSound(p.getLocation(), Sound.EAT, 1, 1);
                }
            }
        }
    }
}

