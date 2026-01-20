package com.dakirr.dcp.Command2Event;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class CommandEventCreator implements CommandExecutor {
    private Plugin plugin;

    public CommandEventCreator(Plugin p) {
        plugin = p;
        plugin.getLogger().info("CommandEventCreator initialized successfully");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            plugin.getLogger().warning("CommandEvent not created: No function name is given");
            return false;
        }
        if (sender instanceof Player) {
            String S = ((Player) sender).getPlayerListName();
            CommandEvent ce = new CommandEvent(plugin, S, args[0], Arrays.copyOfRange(args, 1, args.length));
            Bukkit.getPluginManager().callEvent(ce);
        } else if (sender instanceof ConsoleCommandSender) {
            CommandEvent ce = new CommandEvent(plugin, "CONSOLE", args[0], Arrays.copyOfRange(args, 1, args.length));
            Bukkit.getPluginManager().callEvent(ce);
        } else if (sender instanceof BlockCommandSender) {
            CommandEvent ce = new CommandEvent(plugin, "CMD_BLOCK", args[0], Arrays.copyOfRange(args, 1, args.length));
            Bukkit.getPluginManager().callEvent(ce);
        } else if (sender instanceof ProxiedCommandSender) {
            CommandEvent ce = new CommandEvent(plugin, "PROXY", args[0], Arrays.copyOfRange(args, 1, args.length));
            Bukkit.getPluginManager().callEvent(ce);
        }

        return true;
    }
}
