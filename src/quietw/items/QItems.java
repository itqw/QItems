package quietw.items;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import quietw.items.Database.DatabaseEditor;
import quietw.items.Events.EventListener;
import quietw.items.Items.BlockTest;
import quietw.items.Items.ItemTest;
import quietw.items.Items.Tea;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QItems extends JavaPlugin {

    private static QItems instance;
    Connection connection;
    File config = new File(getDataFolder(), "config.yml");

    @Override
    public void onEnable() {
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
        new ItemTest();
        new BlockTest();
        new Tea();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + (getDataFolder() + File.separator + "data.db"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance = this;
        new DatabaseEditor(connection);
    }

    public static QItems getInstance() {
        return instance;
    }
}
