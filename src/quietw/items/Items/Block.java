package quietw.items.Items;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class Block extends Item{

    public Block() {
        ItemsRegister.registerBlock(this);
    }

    public abstract void onPlace(BlockPlaceEvent event);
    public abstract void onBreak(BlockBreakEvent event);
    public abstract void onRightClick(PlayerInteractEvent event);

}
