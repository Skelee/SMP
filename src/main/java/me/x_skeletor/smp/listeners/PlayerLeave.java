package me.x_skeletor.smp.listeners;

import me.x_skeletor.smp.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    public PlayerLeave(Core main) {
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.DARK_GRAY + "Quit> " + ChatColor.GRAY + p.getName());
    }
}
