package quietw.items.Items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemTest extends Item {

    @Override
    public String getId() {
        return "magic_stick";
    }

    @Override
    public void createItem() {
        ItemStack item = new ItemStack(Material.WOODEN_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§6MAGIC STICK");
        meta.addEnchant(Enchantment.THORNS, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        ItemTest.item = item;
    }

    @Override
    public void registerCraft() {
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(NamespacedKey.minecraft(getId()), item);
        shapelessRecipe.addIngredient(4, Material.STICK);
        registerRecipe(shapelessRecipe);
    }

    @Override
    public ItemStack getItemStack() {
        return item;
    }


}
