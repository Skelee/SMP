package me.x_skeletor.smp.enchantments;

import me.x_skeletor.smp.Core;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Forge implements Listener {

    ArrayList<String> usingForge = new ArrayList<String>();

    Inventory forgeGUI = Bukkit.createInventory(null, 27, "The Forge");
    Inventory forgeEnchantmentsGUI = Bukkit.createInventory(null, 54, "The Forge - Enchantments");

    public Forge(Core main) {
    }

    Location forgeLocation = new Location(Bukkit.getWorld("world"), 0, 200, 0);

    //TODO ADD A TIMER FOR FORGE

    @EventHandler
    public void onForgeUse(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        Action action = e.getAction();
        Block block = e.getClickedBlock();

        if (action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (block.getType().equals(Material.FURNACE)) {
                if (block.getLocation().equals(forgeLocation)) {
                    for (Player all : Bukkit.getOnlinePlayers())
                        if (usingForge.contains(all.getName())) {
                            p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "The Forge is currently being used by " + all.getName() + ".");
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("SMP"), new Runnable() {
                                public void run() {
                                    p.closeInventory();
                                }
                            }, 1);
                            return;
                        } else {
                            if (!usingForge.contains(all.getName())) {
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("SMP"), new Runnable() {
                                    public void run() {
                                        usingForge.add(p.getName());
                                    }
                                }, 1 * 20);
                                for (int i = 0; i < 27; i++) {
                                    forgeGUI.setItem(i, null);
                                }
                                ItemStack enchantments = new ItemStack(Material.ENCHANTED_BOOK);
                                ItemMeta enchantmentsMeta = enchantments.getItemMeta();
                                ArrayList<String> enchantmentsLore = new ArrayList<String>();
                                //enchantmentsMeta.setDisplayName(ChatColor.GREEN + "Enchantments");
                                enchantmentsLore.add(ChatColor.GRAY + "Click to view all enchantments.");
                                enchantmentsMeta.setLore(enchantmentsLore);
                                enchantments.setItemMeta(enchantmentsMeta);
                                forgeGUI.setItem(11, enchantments);

                                ItemStack consumables = new ItemStack(Material.BLAZE_POWDER);
                                ItemMeta consumablesMeta = consumables.getItemMeta();
                                ArrayList<String> consumablesLore = new ArrayList<String>();
                                //consumablesMeta.setDisplayName(ChatColor.GREEN + "Consumables");
                                consumablesLore.add(ChatColor.GRAY + "Click to view all consumables.");
                                consumablesMeta.setLore(consumablesLore);
                                consumables.setItemMeta(consumablesMeta);
                                forgeGUI.setItem(15, consumables);

                                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("SMP"), new Runnable() {
                                    public void run() {
                                        p.openInventory(forgeGUI);
                                    }
                                }, 1);
                            }
                        }
                }
            }
        }
    }

    @EventHandler
    public void forgeClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inventory = e.getClickedInventory();

        if (clicked == null) return;

        if (inventory.getName().equals(forgeGUI.getName())) {
            e.setCancelled(true);
            if (clicked.getItemMeta() == null || clicked.getItemMeta().getDisplayName() == null) return;
            if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Enchantments")) {
                for (int i = 0; i < 54; i++) {
                    forgeEnchantmentsGUI.setItem(i, null);
                }
                ItemStack invis1Ench = new ItemStack(Material.NETHER_STAR);
                ItemMeta invis1EnchMeta = invis1Ench.getItemMeta();
                ArrayList<String> invis1EnchLore = new ArrayList<String>();
                //invis1EnchMeta.setDisplayName(ChatColor.GREEN + "Invisibility");
                invis1EnchLore.add(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "I");
                invis1EnchLore.add("");
                invis1EnchLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
                invis1EnchLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Leggings");
                invis1EnchMeta.setLore(invis1EnchLore);
                invis1Ench.setItemMeta(invis1EnchMeta);
                forgeEnchantmentsGUI.setItem(0, invis1Ench);

                ItemStack invis2Ench = new ItemStack(Material.NETHER_STAR);
                ItemMeta invis2EnchMeta = invis2Ench.getItemMeta();
                ArrayList<String> invis2EnchLore = new ArrayList<String>();
                //invis2EnchMeta.setDisplayName(ChatColor.GREEN + "Invisibility");
                invis2EnchLore.add(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "II");
                invis2EnchLore.add("");
                invis2EnchLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
                invis2EnchLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Leggings");
                invis2EnchMeta.setLore(invis2EnchLore);
                invis2Ench.setItemMeta(invis2EnchMeta);
                forgeEnchantmentsGUI.setItem(1, invis2Ench);

                ItemStack speed1Ench = new ItemStack(Material.NETHER_STAR);
                ItemMeta speed1EnchMeta = speed1Ench.getItemMeta();
                ArrayList<String> speed1EnchLore = new ArrayList<String>();
               //speed1EnchMeta.setDisplayName(ChatColor.GREEN + "Speed");
                speed1EnchLore.add(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "I");
                speed1EnchLore.add("");
                speed1EnchLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
                speed1EnchLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Boots");
                speed1EnchMeta.setLore(speed1EnchLore);
                speed1Ench.setItemMeta(speed1EnchMeta);
                forgeEnchantmentsGUI.setItem(9, speed1Ench);

                p.openInventory(forgeEnchantmentsGUI);
            }
        } else {
            if (inventory.getName().equals(forgeEnchantmentsGUI.getName())) {
                e.setCancelled(true);
                if (clicked.getItemMeta() == null || clicked.getItemMeta().getDisplayName() == null) return;
                if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Invisibility")) {
                    if (clicked.getItemMeta().getLore().contains(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "I")) {
                        for (int i = 0; i < 54; i++) {
                            forgeEnchantmentsGUI.setItem(i, null);
                        }
                        ItemStack invis1EnchTutorial = new ItemStack(Material.ENCHANTED_BOOK);
                        ItemMeta invis1EnchTutorialMeta = invis1EnchTutorial.getItemMeta();
                        ArrayList<String> invis1EnchTutorialLore = new ArrayList<String>();
                       // invis1EnchTutorialMeta.setDisplayName(ChatColor.GREEN + "Enchant");
                        invis1EnchTutorialLore.add(ChatColor.GRAY + "Click on the star below with");
                        invis1EnchTutorialLore.add(ChatColor.GRAY + "the item you want to enchant to");
                        invis1EnchTutorialLore.add(ChatColor.GRAY + "enchant the item.");
                        invis1EnchTutorialMeta.setLore(invis1EnchTutorialLore);
                        invis1EnchTutorial.setItemMeta(invis1EnchTutorialMeta);
                        forgeEnchantmentsGUI.setItem(4, invis1EnchTutorial);

                        ItemStack invis1Ench = new ItemStack(Material.NETHER_STAR);
                        ItemMeta invis1EnchMeta = invis1Ench.getItemMeta();
                        ArrayList<String> invis1EnchLore = new ArrayList<String>();
                        //invis1EnchMeta.setDisplayName(ChatColor.GREEN + "Invisibility");
                        invis1EnchLore.add(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "I");
                        invis1EnchLore.add("");
                        invis1EnchLore.add(ChatColor.YELLOW + "Cost: " + ChatColor.WHITE + "30 Shards");
                        invis1EnchLore.add("");
                        invis1EnchLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
                        invis1EnchLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Leggings");
                        invis1EnchLore.add("");
                        invis1EnchLore.add(ChatColor.YELLOW + "ID: " + ChatColor.WHITE + "200:1");
                        invis1EnchMeta.setLore(invis1EnchLore);
                        invis1Ench.setItemMeta(invis1EnchMeta);
                        forgeEnchantmentsGUI.setItem(22, invis1Ench);
                    } else {
                        if (clicked.getItemMeta().getLore().contains(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "II")) {
                            for (int i = 0; i < 54; i++) {
                                forgeEnchantmentsGUI.setItem(i, null);
                            }
                            ItemStack invis2EnchTutorial = new ItemStack(Material.ENCHANTED_BOOK);
                            ItemMeta invis2EnchTutorialMeta = invis2EnchTutorial.getItemMeta();
                            ArrayList<String> invis2EnchTutorialLore = new ArrayList<String>();
                           // invis2EnchTutorialMeta.setDisplayName(ChatColor.GREEN + "Enchant");
                            invis2EnchTutorialLore.add(ChatColor.GRAY + "Click on the star below with");
                            invis2EnchTutorialLore.add(ChatColor.GRAY + "the item you want to enchant to");
                            invis2EnchTutorialLore.add(ChatColor.GRAY + "enchant the item.");
                            invis2EnchTutorialMeta.setLore(invis2EnchTutorialLore);
                            invis2EnchTutorial.setItemMeta(invis2EnchTutorialMeta);
                            forgeEnchantmentsGUI.setItem(4, invis2EnchTutorial);

                            ItemStack invis2Ench = new ItemStack(Material.NETHER_STAR);
                            ItemMeta invis2EnchMeta = invis2Ench.getItemMeta();
                            ArrayList<String> invis2EnchLore = new ArrayList<String>();
                           // invis2EnchMeta.setDisplayName(ChatColor.GREEN + "Invisibility");
                            invis2EnchLore.add(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "II");
                            invis2EnchLore.add("");
                            invis2EnchLore.add(ChatColor.YELLOW + "Cost: " + ChatColor.WHITE + "60 Shards");
                            invis2EnchLore.add("");
                            invis2EnchLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
                            invis2EnchLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Leggings");
                            invis2EnchLore.add("");
                            invis2EnchLore.add(ChatColor.YELLOW + "ID: " + ChatColor.WHITE + "200:2");
                            invis2EnchMeta.setLore(invis2EnchLore);
                            invis2Ench.setItemMeta(invis2EnchMeta);
                            forgeEnchantmentsGUI.setItem(22, invis2Ench);
                        }
                    }
                } else {
                    if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Speed")) {
                        if (clicked.getItemMeta().getLore().contains(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "I")) {
                            for (int i = 0; i < 54; i++) {
                                forgeEnchantmentsGUI.setItem(i, null);
                            }
                            ItemStack EnchTutorial = new ItemStack(Material.ENCHANTED_BOOK);
                            ItemMeta EnchTutorialMeta = EnchTutorial.getItemMeta();
                            ArrayList<String> EnchTutorialLore = new ArrayList<String>();
                          //  EnchTutorialMeta.setDisplayName(ChatColor.GREEN + "Enchant");
                            EnchTutorialLore.add(ChatColor.GRAY + "Click on the star below with");
                            EnchTutorialLore.add(ChatColor.GRAY + "the item you want to enchant to");
                            EnchTutorialLore.add(ChatColor.GRAY + "enchant the item.");
                            EnchTutorialMeta.setLore(EnchTutorialLore);
                            EnchTutorial.setItemMeta(EnchTutorialMeta);
                            forgeEnchantmentsGUI.setItem(4, EnchTutorial);

                            ItemStack speed1Ench = new ItemStack(Material.NETHER_STAR);
                            ItemMeta speed1EnchMeta = speed1Ench.getItemMeta();
                            ArrayList<String> speed1EnchLore = new ArrayList<String>();
                            //speed1EnchMeta.setDisplayName(ChatColor.GREEN + "Speed");
                            speed1EnchLore.add(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + "I");
                            speed1EnchLore.add("");
                            speed1EnchLore.add(ChatColor.YELLOW + "Cost: " + ChatColor.WHITE + "30 Shards");
                            speed1EnchLore.add("");
                            speed1EnchLore.add(ChatColor.YELLOW + "Type: " + ChatColor.WHITE + "Armor");
                            speed1EnchLore.add(ChatColor.YELLOW + "Applicable Item: " + ChatColor.WHITE + "Boots");
                            speed1EnchLore.add("");
                            speed1EnchLore.add(ChatColor.YELLOW + "ID: " + ChatColor.WHITE + "201:1");
                            speed1EnchMeta.setLore(speed1EnchLore);
                            speed1Ench.setItemMeta(speed1EnchMeta);
                            forgeEnchantmentsGUI.setItem(22, speed1Ench);
                        }
                    }
                }
            }
        }
    }

    /*@EventHandler
    public void purchaseManager(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();
        ItemStack cursor = e.getCursor();
        Inventory inventory = e.getClickedInventory();
        int successChance = ThreadLocalRandom.current().nextInt(10) + 1;
        int curseOfBinding = ThreadLocalRandom.current().nextInt(10) + 1;
        int curseOfVanishing = ThreadLocalRandom.current().nextInt(10) + 1;
        if (inventory != null && inventory.getName() != null && inventory.getName().equals(forgeEnchantmentsGUI.getName())) {
            if (p instanceof Player && e.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
                if (current == null) return;
                if (current.getType() == null) return;
                if (current.getItemMeta() == null) return;
                if (current.getItemMeta().getLore() == null) return;
                if (current.getType() == Material.NETHER_STAR) {
                    if (current.getItemMeta().getLore().contains(ChatColor.YELLOW + "ID: " + ChatColor.WHITE + "200:1")) {
                        if (p.getInventory() != null && p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Enchantment Shard") && p.getInventory().getItemInMainHand().getAmount() >= 30) {
                            if (cursor.getType().toString().contains("LEGGINGS")) {
                                if (successChance <= 7) {
                                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 30);
                                    ItemMeta invis1legmeta = cursor.getItemMeta();
                                    ArrayList<String> invis1leglore = new ArrayList<String>();
                                    invis1leglore.add(ChatColor.GRAY + "Invisibility I");
                                    invis1legmeta.setLore(invis1leglore);
                                    cursor.setItemMeta(invis1legmeta);
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 0);
                                } else {
                                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 30);
                                    p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "The enchantment has failed to apply!");
                                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5, -1);
                                    if (curseOfBinding + curseOfVanishing >= 11) {
                                        cursor.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                                    } else {
                                        cursor.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
                                    }
                                }
                            } else {
                                p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "Wrong item.");
                                p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
                            }
                        } else {
                            p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "You must be holding enough " + ChatColor.GREEN + "Enchantment Shards" + ChatColor.GRAY + " in your hand.");
                            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
                        }
                    } else {
                        if (current.getItemMeta().getLore().contains(ChatColor.YELLOW + "ID: " + ChatColor.WHITE + "200:2")) {
                            if (p.getInventory() != null && p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Enchantment Shard") && p.getInventory().getItemInMainHand().getAmount() >= 60) {
                                if (cursor.getType().toString().contains("LEGGINGS")) {
                                    if (successChance <= 8) {
                                        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 60);
                                        ItemMeta invis2legmeta = cursor.getItemMeta();
                                        ArrayList<String> invis2leglore = new ArrayList<String>();
                                        invis2leglore.add(ChatColor.GRAY + "Invisibility II");
                                        invis2legmeta.setLore(invis2leglore);
                                        cursor.setItemMeta(invis2legmeta);
                                        e.setCancelled(true);
                                        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 0);
                                    } else {
                                        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 60);
                                        p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "The enchantment has failed to apply!");
                                        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5, -1);
                                        if (curseOfBinding + curseOfVanishing >= 11) {
                                            cursor.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                                        } else {
                                            cursor.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
                                        }
                                    }
                                } else {
                                    p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "Wrong item.");
                                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
                                }
                            } else {
                                p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "You must be holding enough " + ChatColor.GREEN + "Enchantment Shards" + ChatColor.GRAY + " in your hand.");
                                p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
                            }
                        } else {
                            if (current.getItemMeta().getLore().contains(ChatColor.YELLOW + "ID: " + ChatColor.WHITE + "201:1")) {
                                if (p.getInventory() != null && p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Enchantment Shard") && p.getInventory().getItemInMainHand().getAmount() >= 30) {
                                    if (cursor.getType().toString().contains("BOOTS")) {
                                        if (successChance <= 7) {
                                            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 30);
                                            ItemMeta speed1bootmeta = cursor.getItemMeta();
                                            ArrayList<String> speed1bootmetalore = new ArrayList<String>();
                                            speed1bootmetalore.add(ChatColor.GRAY + "Speed I");
                                            speed1bootmeta.setLore(speed1bootmetalore);
                                            cursor.setItemMeta(speed1bootmeta);
                                            e.setCancelled(true);
                                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 0);
                                        } else {
                                            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 30);
                                            p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "The enchantment has failed to apply!");
                                            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5, -1);
                                            if (curseOfBinding + curseOfVanishing >= 11) {
                                                cursor.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                                            } else {
                                                cursor.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
                                            }
                                        }
                                    } else {
                                        p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "Wrong item.");
                                        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
                                    }
                                } else {
                                    p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "You must be holding enough " + ChatColor.GREEN + "Enchantment Shards" + ChatColor.GRAY + " in your hand.");
                                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/

    @EventHandler
    public void onClose(PlayerMoveEvent e) {
        Player p = (Player) e.getPlayer();
        if (usingForge.contains(p.getName())) {
            usingForge.remove(p.getName());
        }
    }

    @EventHandler
    public void onForgeBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlock();

        if (block.getLocation().equals(forgeLocation)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "You are not allowed to break The Forge.");
        }
    }
}

