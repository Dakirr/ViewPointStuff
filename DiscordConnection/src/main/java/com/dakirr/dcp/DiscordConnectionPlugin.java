package com.dakirr.dcp;

import com.dakirr.dcp.Command2Event.CommandEvent;
import com.dakirr.dcp.Command2Event.CommandEventCreator;
import com.dakirr.dcp.Command2Event.CommandEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordConnectionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandEventCreator commandEventCreator = new CommandEventCreator(this);
        for (String cmd : getDescription().getCommands().keySet()) {
            getCommand(cmd).setExecutor(commandEventCreator);
        }

        boolean debugMode = getConfig().getBoolean("DebugMode", false);
        if (debugMode) { // This part is for the tests only
            Bukkit.getPluginManager().registerEvents(
                    new CommandEventListener(this),
                    this
            );
        }

        saveDefaultConfig();
        getLogger().info("DiscordConnection plugin initialized successfully!");
    }

    @Override
    public void onDisable() {
    }
}
