package me.x_skeletor.smp;

import me.x_skeletor.smp.commands.EnchantCommands;
import me.x_skeletor.smp.commands.Message;
import me.x_skeletor.smp.consumables.Regen;
import me.x_skeletor.smp.enchantments.Forge;
import me.x_skeletor.smp.enchantments.InvisibilityEnch;
import me.x_skeletor.smp.listeners.PlayerChat;
import me.x_skeletor.smp.listeners.PlayerJoin;
import me.x_skeletor.smp.listeners.PlayerLeave;
import me.x_skeletor.smp.supplydrop.Drop;
import me.x_skeletor.smp.utils.Crafting;
import me.x_skeletor.smp.utils.Shards;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Core extends JavaPlugin {

    private static Plugin plugin;

    public static InvisibilityEnch enchInvisibility = new InvisibilityEnch(103);

    @Override
    public void onEnable() {
        plugin = this;
        LoadEnchantments();
        this.getServer().getPluginManager().registerEvents(enchInvisibility, this);
        regCommands();
        regListeners();
        Drop.callStart();
    }

    @Override
    public void onDisable() {
        disableEnchantments();
    }

    public void disableEnchantments() {
        try {
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            @SuppressWarnings("unchecked")
            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);
            if (byId.containsKey(enchInvisibility.getId())) {
                byId.remove(enchInvisibility.getId());
            }
        } catch (Exception ignored) {
        }
    }


    private void LoadEnchantments() {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(enchInvisibility);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void regListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerLeave(this), this);
        pm.registerEvents(new PlayerChat(this), this);
        pm.registerEvents(new Regen(this), this);
        pm.registerEvents(new Crafting(this), this);
        pm.registerEvents(new Forge(this), this);
        pm.registerEvents(new Shards(this), this);
        pm.registerEvents(new EnchantCommands(this), this);

    }

    private void regCommands() {
        getCommand("consumable").setExecutor(new Regen(this));
        getCommand("forge").setExecutor(new EnchantCommands(this));
        getCommand("msg").setExecutor(new Message(this));
        getCommand("reply").setExecutor(new Message(this));
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
