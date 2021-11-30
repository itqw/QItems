package quietw.items.Items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Tea extends Potion {
    @Override
    public String getId() {
        return "tea";
    }

    @Override
    public void createItem() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§6Чёрный чай");
        meta.addEnchant(Enchantment.THORNS, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
        List<String> lore = new ArrayList<>();
        lore.add("§5§oОн ещё горячий...");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PotionMeta potionMeta = (PotionMeta) meta;
        potionMeta.setColor(Color.BLACK);
        item.setItemMeta(potionMeta);
        Tea.item = item;
    }

    @Override
    public void registerCraft() {
        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("tea"), item);
        shapedRecipe.shape("0C0", "0P0", "0W0");
        shapedRecipe.setIngredient('W', Material.WATER_BUCKET);
        shapedRecipe.setIngredient('P', Material.PAPER);
        shapedRecipe.setIngredient('C', Material.COCOA_BEANS);
        registerRecipe(shapedRecipe);
    }

    @Override
    public ItemStack getItemStack() {
        return item;
    }

    @Override
    public void onUse(PlayerItemConsumeEvent event) {
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 1, true, true, true));
    }
}
