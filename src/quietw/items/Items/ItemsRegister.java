package quietw.items.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemsRegister {

    private static final List<Item> items = new ArrayList<>();
    private static final List<Block> blocks = new ArrayList<>();
    private static final List<Potion> potions = new ArrayList<>();

    public static void registerItem(Item item) {
        if(!items.contains(item)) {
            items.add(item);
        }
    }

    public static void registerBlock(Block block) {
        if(!blocks.contains(block)) {
            blocks.add(block);
        }
    }

    public static void registerPotion(Potion potion) {
        if(!potions.contains(potion)) {
            potions.add(potion);
        }
    }

    public static List<Item> getItems() {
        return items;
    }

    public static List<Block> getBlocks() {
        return blocks;
    }

    public static List<Potion> getPotions() {
        return potions;
    }

}
