package com.dakirr.dcp;

import com.dakirr.dcp.Command2Event.CommandEvent;
import com.dakirr.dcp.Command2Event.CommandEventCreator;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordConnectionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandEventCreator commandEventCreator = new CommandEventCreator(this);
        for (String cmd : getDescription().getCommands().keySet()) {
            getCommand(cmd).setExecutor(commandEventCreator);
        }
        saveDefaultConfig();
        getLogger().info("DKR: DiscordConnection plugin initialized!");
    }

    @Override
    public void onDisable() {
    }
}
