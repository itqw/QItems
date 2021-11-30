package quietw.items.Database;

import org.bukkit.Location;
import quietw.items.Items.Block;
import quietw.items.Items.ItemsRegister;
import quietw.items.QItems;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseEditor {

    private static DatabaseEditor instance;
    private static Connection connection;

    public DatabaseEditor(Connection con) {
        connection = con;
        execUpdate("CREATE TABLE IF NOT EXISTS 'blocks' ('id' TEXT, 'posX' INTEGER, 'posY' INTEGER, 'posZ' INTEGER)");
        QItems.getInstance().getLogger().info("DB Connected. Table created");
    }

    public static void execUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Block getBlock(Location loc) {
        int locX = loc.getBlockX();
        int locY = loc.getBlockY();
        int locZ = loc.getBlockZ();
        String id = "null";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT COUNT(id) FROM blocks WHERE posX=%s AND posY=%s AND posZ=%s", locX, locY, locZ));
            if(rs.getInt("COUNT(id)") == 1) {
                ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM blocks WHERE posX=%s AND posY=%s AND posZ=%s", locX, locY, locZ));
                id = resultSet.getString("id");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(id.equalsIgnoreCase("null")) {
            return null;
        }
        for(Block block : ItemsRegister.getBlocks()) {
            if(block.getId().equalsIgnoreCase(id)) {
                return block;
            }
        }
        return null;
    }

    public static void saveBlock(Block block, Location loc) {
        String id = block.getId();
        int locX = loc.getBlockX();
        int locY = loc.getBlockY();
        int locZ = loc.getBlockZ();
        execUpdate(String.format("INSERT INTO blocks VALUES('%s', %s, %s, %s)", id, locX, locY, locZ));
    }

    public static void removeBlock(Block block, Location loc) {
        String id = block.getId();
        int locX = loc.getBlockX();
        int locY = loc.getBlockY();
        int locZ = loc.getBlockZ();
        execUpdate(String.format("DELETE FROM blocks WHERE posX=%s AND posY=%s AND posZ=%s", locX, locY, locZ));
    }

    public static DatabaseEditor getInstance() {
        return instance;
    }
}
