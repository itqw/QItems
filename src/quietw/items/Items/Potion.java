package quietw.items.Items;

import org.bukkit.event.player.PlayerItemConsumeEvent;

public abstract class Potion extends Item {

    public Potion() {
        ItemsRegister.registerPotion(this);
    }

    public abstract void onUse(PlayerItemConsumeEvent event);

}
