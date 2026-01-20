package com.dakirr.dcp.Command2Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class CommandEventListener implements Listener {
    Plugin plugin;

    public CommandEventListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getLogger().info("CommandEventListener initialized successfully");
    }

    @EventHandler
    public void onCommandEvent(CommandEvent event) {
        plugin.getLogger().info(String.format("CommandEvent catched: Executed /%s %s by %s",
                event.getCommand(), event.getArgsString(), event.getSender()));
    }
}