package quietw.items.Events;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import quietw.items.Database.DatabaseEditor;
import quietw.items.Items.*;
import quietw.items.QItems;

import java.util.Objects;

public class EventListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        ItemStack handItem = event.getItemInHand();
        for(Block block : ItemsRegister.getBlocks()) {
            if(handItem.isSimilar(block.getItemStack())) {
                block.onPlace(event);
                DatabaseEditor.saveBlock(block, event.getBlock().getLocation());
                return;
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = DatabaseEditor.getBlock(event.getBlock().getLocation());
        if(block != null) {
            block.onBreak(event);
            DatabaseEditor.removeBlock(block, event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = DatabaseEditor.getBlock(Objects.requireNonNull(event.getClickedBlock()).getLocation());
            if(block == null) return;
            block.onRightClick(event);
        }
    }

    @EventHandler
    public void onUse(PlayerItemConsumeEvent event) {
        if(event.getItem().getType() == Material.POTION) {
            ItemStack consumedItem = event.getItem();
            for(Potion potion : ItemsRegister.getPotions()) {
                if(consumedItem.isSimilar(potion.getItemStack())) {
                    potion.onUse(event);
                    return;
                }
            }
        }
    }

}
