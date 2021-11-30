package quietw.items.Items;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public abstract class Item {

    public static ItemStack item;

    public Item() {
        this.createItem();
        this.registerCraft();
        item = this.getItemStack();
        ItemsRegister.registerItem(this);
    }

    public abstract String getId();
    public abstract void createItem();
    public abstract void registerCraft();
    public abstract ItemStack getItemStack();
    public static void registerRecipe(ShapedRecipe shapedRecipe) {
        Bukkit.getServer().addRecipe(shapedRecipe);
    }
    public static void registerRecipe(ShapelessRecipe shapelessRecipe) {
        Bukkit.getServer().addRecipe(shapelessRecipe);
    }
    public static ItemStack getItem() {
        return item;
    }

}
