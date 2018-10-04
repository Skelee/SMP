package me.x_skeletor.smp.supplydrop;

import io.netty.util.internal.ThreadLocalRandom;
import me.x_skeletor.smp.Core;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Drop {

    public Drop(Core main) {
    }

    public static void callStart() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getPlugin(Core.class), new Runnable() {
            public void run() {
                Random rnd = new Random();
                int x = rnd.nextInt(1000);
                int z = rnd.nextInt(1000);

                World w = Bukkit.getWorld("world");
                start(w, x, z);
            }
        }, 1800 * 20, 3600 * 20);
    }

    public static void start(final World w, final int x, final int z) {
        if (Bukkit.getOnlinePlayers().size() >= 2) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("SMP"), new Runnable() {
                public void run() {

                    try {
                        drop(w, x, z);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 4L);
        }
    }

    public static void drop(World w, int x, int z) throws IOException {
        if (Bukkit.getOnlinePlayers().size() >= 2) {
            Location loc = new Location(w, x, 0.0D, z);
            Location aloc = loc.getWorld().getHighestBlockAt(loc).getLocation();
            aloc.getBlock().setType(Material.CHEST);
            final Chest chest = (Chest) aloc.getBlock().getState();
            int chance = ThreadLocalRandom.current().nextInt(5) + 1;
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "A supply drop has spawned at " + ChatColor.GREEN + "x: " + x + ChatColor.GRAY + ", " + ChatColor.GREEN + "z: " + z + ChatColor.GRAY + ".");
            if (chance == 1) {
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                ItemStack diaChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
                ItemMeta diaChestMeta = diaChest.getItemMeta();
                ArrayList<String> diaChestLore = new ArrayList<String>();
                diaChestLore.add("");
                diaChestLore.add(ChatColor.YELLOW + "Spawned On: " + ChatColor.GREEN + format.format(now));
                diaChestMeta.setLore(diaChestLore);
                diaChest.setItemMeta(diaChestMeta);
                chest.getBlockInventory().setItem(13, diaChest);
            } else {
                if (chance == 2) {
                    int itemChance = ThreadLocalRandom.current().nextInt(32) + 1;
                    Date now = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                    ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, itemChance);
                    ItemMeta goldIngotMeta = goldIngot.getItemMeta();
                    ArrayList<String> goldIngotLore = new ArrayList<String>();
                    goldIngotLore.add("");
                    goldIngotLore.add(ChatColor.YELLOW + "Spawned On: " + ChatColor.GREEN + format.format(now));
                    goldIngotMeta.setLore(goldIngotLore);
                    goldIngot.setItemMeta(goldIngotMeta);
                    chest.getBlockInventory().setItem(13, goldIngot);
                } else {
                    if (chance == 3) {
                        int itemChance = ThreadLocalRandom.current().nextInt(32) + 1;
                        Date now = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                        ItemStack ironIngot = new ItemStack(Material.IRON_INGOT, itemChance);
                        ItemMeta ironIngotMeta = ironIngot.getItemMeta();
                        ArrayList<String> ironIngotLore = new ArrayList<String>();
                        ironIngotLore.add("");
                        ironIngotLore.add(ChatColor.YELLOW + "Spawned On: " + ChatColor.GREEN + format.format(now));
                        ironIngotMeta.setLore(ironIngotLore);
                        ironIngot.setItemMeta(ironIngotMeta);
                        chest.getBlockInventory().setItem(13, ironIngot);
                    } else {
                        if (chance == 4) {
                            int itemChance = ThreadLocalRandom.current().nextInt(32) + 1;
                            Date now = new Date();
                            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                            ItemStack emeraldIngot = new ItemStack(Material.EMERALD, itemChance);
                            ItemMeta emeraldIngotMeta = emeraldIngot.getItemMeta();
                            ArrayList<String> emeraldIngotLore = new ArrayList<String>();
                            emeraldIngotLore.add("");
                            emeraldIngotLore.add(ChatColor.YELLOW + "Spawned On: " + ChatColor.GREEN + format.format(now));
                            emeraldIngotMeta.setLore(emeraldIngotLore);
                            emeraldIngot.setItemMeta(emeraldIngotMeta);
                            chest.getBlockInventory().setItem(13, emeraldIngot);
                        } else {
                            if (chance == 5) {
                                int itemChance = ThreadLocalRandom.current().nextInt(16) + 1;
                                Date now = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                                ItemStack exp = new ItemStack(Material.EXP_BOTTLE, itemChance);
                                ItemMeta expMeta = exp.getItemMeta();
                                ArrayList<String> expLore = new ArrayList<String>();
                                expLore.add("");
                                expLore.add(ChatColor.YELLOW + "Spawned On: " + ChatColor.GREEN + format.format(now));
                                expMeta.setLore(expLore);
                                exp.setItemMeta(expMeta);
                                chest.getBlockInventory().setItem(13, exp);
                            }
                        }
                    }
                }
            }
        }
    }
}