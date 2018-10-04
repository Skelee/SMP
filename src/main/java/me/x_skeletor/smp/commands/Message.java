package me.x_skeletor.smp.commands;

import me.x_skeletor.smp.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Message implements CommandExecutor {

    public Message(Core main) {
    }

    public static HashMap<Player, Player> REPLY = new HashMap();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("msg")) {
            if ((sender instanceof Player)) {
                Player p = (Player) sender;
                if (args.length >= 2) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player p2 = Bukkit.getPlayer(args[0]);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            sb.append(" ").append(args[i]);
                        }
                        String message = sb.toString().substring(1);
                        p.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + p.getName() + " > " + p2.getName()
                                + ChatColor.LIGHT_PURPLE + " " + ChatColor.BOLD + message);
                        p2.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + sender.getName() + " > "
                                + p2.getName() + ChatColor.LIGHT_PURPLE + " " + ChatColor.BOLD + message);
                        p2.playSound(p2.getLocation(), Sound.NOTE_PIANO, 1, 0);
                        if (REPLY.containsKey(p)) {
                            REPLY.remove(p);
                        }
                        REPLY.put(p, p2);

                    } else {
                        p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "Could not find player ["
                                + ChatColor.YELLOW + args[0] + ChatColor.GRAY + "].");
                    }
                } else {
                    p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "Usage: " + ChatColor.YELLOW
                            + "/message <player> <message>");
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("reply")) {
            if ((sender instanceof Player)) {
                Player p = (Player) sender;
                if (args.length == 0) {
                    p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.GRAY + "Usage: " + ChatColor.YELLOW
                            + "/reply <message>");
                } else if (REPLY.containsKey(p)) {
                    String p2 = ((Player) REPLY.get(p)).getName();
                    if (Bukkit.getPlayer(p2) != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            sb.append(" ").append(args[i]);
                        }
                        String message = sb.toString().substring(1);

                        p.performCommand("msg " + p2 + " " + message);
                    } else {
                        p.sendMessage(ChatColor.DARK_PURPLE + "SMP> " + ChatColor.YELLOW + "0 " + ChatColor.GRAY
                                + "matches for [" + ChatColor.YELLOW + args[0] + ChatColor.GRAY + "].");
                    }
                } else {
                    p.sendMessage(ChatColor.DARK_PURPLE + "Message> " + ChatColor.GRAY
                            + "You have not messaged anyone recently.");
                }
            }
        }
        return true;
    }

}
