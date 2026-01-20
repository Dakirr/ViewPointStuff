package com.dakirr.dcp.Command2Event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CommandEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public Plugin plugin;
    public String sender;
    public String command;
    public List<String> args;

    public CommandEvent(Plugin p, String s, String c, String[] a) {
        sender = s;
        command = c;
        args = a.length == 0 ? new ArrayList<>() : new ArrayList<>(Arrays.asList(a));
        plugin = p;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getArgs() {
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