package quietw.items.Items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import quietw.items.Database.DatabaseEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockTest extends Block {

    @Override
    public String getId() {
        return "magic_block";
    }

    @Override
    public void createItem() {
        ItemStack item = new ItemStack(Material.DIRT, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§kW §r§dULTRA MEGA SUPER MAGIC BLOCK §r§k");
        meta.addEnchant(Enchantment.THORNS, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<>();
        lore.add("§5§oYou've just found very rare block...");
        meta.setLore(lore);
        item.setItemMeta(meta);
        BlockTest.item = item;
    }

    @Override
    public void registerCraft() {
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(NamespacedKey.minecraft(getId()), item);
        shapelessRecipe.addIngredient((RecipeChoice) ItemTest.getItem());
        registerRecipe(shapelessRecipe);
    }

    @Override
    public ItemStack getItemStack() {
        return item;
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        Location blockLocation = event.getBlockPlaced().getLocation();
        blockLocation.setY(blockLocation.getY() + 1);
        blockLocation.getBlock().setType(Material.CREEPER_HEAD);
        event.getPlayer().sendMessage("You felt something magic...");
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        event.setDropItems(false);
        assert getItemStack() != null;
        Objects.requireNonNull(event.getBlock().getLocation().getWorld()).dropItem(
                event.getBlock().getLocation(),
                getItemStack()
        );
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        Location blockLocation = Objects.requireNonNull(event.getClickedBlock()).getLocation();
        Objects.requireNonNull(blockLocation.getWorld()).getBlockAt(blockLocation).setType(Material.AIR);
        DatabaseEditor.removeBlock(this, blockLocation);
        blockLocation.setY(blockLocation.getY() + 1);
        Objects.requireNonNull(blockLocation.getWorld()).getBlockAt(blockLocation).setType(Material.AIR);
        Objects.requireNonNull(blockLocation.getWorld()).dropItem(blockLocation, new ItemStack(Material.DIAMOND, 1));
    }
}
