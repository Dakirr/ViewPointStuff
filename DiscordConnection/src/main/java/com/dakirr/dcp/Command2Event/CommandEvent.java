package com.dakirr.dcp.Command2Event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public final class CommandEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Plugin plugin;
    private String sender;
    private String command;
    private String[] args;

    public CommandEvent(Plugin p, String s, String c, String[] a) {
        sender = s;
        command = c;
        args = a;
        plugin = p;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public String getArgsString() {
        return String.join(" ", args);
    }

    public String getSender() {
        return sender;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}