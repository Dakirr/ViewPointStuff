package org.dakirr.testPlugin.listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.md_5.bungee.api.chat.TextComponent;

import me.filoghost.farmlimiter.api.FarmLimitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.dakirr.testPlugin.database.DatabaseManager;

import java.util.Collection;
import java.util.Iterator;

public class EntityDeletionListener implements Listener {
    private final DatabaseManager databaseManager;

    public EntityDeletionListener(DatabaseManager dm) {
        databaseManager = dm;
    }


    @EventHandler
    public void onFarmLimit(FarmLimitEvent event) {
        Collection<Entity> entitiesToRemove;
        entitiesToRemove = event.getEntitiesToRemove();
        Iterator<Entity> entityIterator = entitiesToRemove.iterator();

        while (entityIterator.hasNext()) {
            Entity entityToRemove = entityIterator.next();
            databaseManager.insertEntity(entityToRemove);
        }

    }
}