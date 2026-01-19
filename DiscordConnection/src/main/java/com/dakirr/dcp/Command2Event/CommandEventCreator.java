package com.dakirr.dcp.Command2Event;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandEventCreator implements CommandExecutor {
    private Plugin plugin;

    public CommandEventCreator(Plugin p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String S = ((Player) sender).getPlayerListName();
            CommandEvent ce = new CommandEvent(plugin, S, command.getName(), args);
        } else if (sender instanceof ConsoleCommandSender) {
            CommandEvent ce = new CommandEvent(plugin, "CONSOLE", command.getName(), args);
        } else if (sender instanceof BlockCommandSender) {
            CommandEvent ce = new CommandEvent(plugin, "CMD_BLOCK", command.getName(), args);
        } else if (sender instanceof ProxiedCommandSender) {
            CommandEvent ce = new CommandEvent(plugin, "PROXY", command.getName(), args);
        }
        return true;
    }
}
