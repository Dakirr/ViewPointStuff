package org.dakirr.testPlugin.database;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

        private final JavaPlugin plugin;
        private Connection connection;

        public DatabaseManager(JavaPlugin plugin) {
            this.plugin = plugin;
            String host = plugin.getConfig().getString("database.host");
            int port = plugin.getConfig().getInt("database.port");
            String dbName = plugin.getConfig().getString("database.name");
            String user = plugin.getConfig().getString("database.user");
            String password = plugin.getConfig().getString("database.password");

            String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                plugin.getLogger().severe("PostgreSQL driver not found in plugin JAR!");
                e.printStackTrace();
                plugin.getServer().getPluginManager().disablePlugin(plugin);
                return;
            }

            try {
                connection = DriverManager.getConnection(url, user, password);
                plugin.getLogger().info("DKR: Database connected successfully!");
            } catch (SQLException e) {
                plugin.getLogger().severe("DKR: Failed to connect to database!");
                e.printStackTrace();
                plugin.getServer().getPluginManager().disablePlugin(plugin);
            }


        }

        public Connection getConnection() {
            return connection;
        }

        public void insertEntity(String type, String world, double x, double y, double z) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                String sql = "INSERT INTO removed_entities(entity_type, world, x, y, z) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, type);
                    ps.setString(2, world);
                    ps.setDouble(3, x);
                    ps.setDouble(4, y);
                    ps.setDouble(5, z);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    plugin.getLogger().severe("DKR: Error while inserting entity into DB!");
                    e.printStackTrace();
                }
            });
        }

        public void insertEntity(Entity entity) {
            insertEntity(
                    entity.getType().name(),
                    entity.getWorld().getName(),
                    entity.getLocation().getX(),
                    entity.getLocation().getY(),
                    entity.getLocation().getZ()
            );
        }
}

