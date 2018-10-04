package me.x_skeletor.smp.listeners;

import me.x_skeletor.smp.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    public PlayerChat(Core main) {
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setFormat(ChatColor.YELLOW + "%s " + ChatColor.WHITE + "%s");
    }
}