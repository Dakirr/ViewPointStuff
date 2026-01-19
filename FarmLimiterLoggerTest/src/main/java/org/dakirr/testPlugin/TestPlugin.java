package org.dakirr.testPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.dakirr.testPlugin.database.DatabaseManager;
import org.dakirr.testPlugin.listener.EntityDeletionListener;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class TestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        DatabaseManager dm = new DatabaseManager(this);
        Bukkit.getPluginManager().registerEvents(
                new EntityDeletionListener(dm),
                this
        );

        getLogger().info("DKR: Plugin Initialized!");

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
