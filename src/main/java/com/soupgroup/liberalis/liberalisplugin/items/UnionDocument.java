package com.soupgroup.liberalis.liberalisplugin.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class UnionDocument {
    public static final String CUSTOM_ID = "union_document";
    public static final Integer CUSTOM_MODEL_VALUE = 1;
    public static final Material ITEM_MATERIAL = Material.KNOWLEDGE_BOOK;
    private static UnionDocument singleton = null;
    private final Plugin plugin;

    public static UnionDocument getInstance(Plugin plugin){
        if (singleton == null) singleton = new UnionDocument(plugin);
        return singleton;
    }

    private UnionDocument(Plugin plugin) {
        this.plugin = plugin;
//        Registering the recipe into the server
        this.plugin.getServer().addRecipe(this.getItemRecipe());
//        Registering the listener that will serve as a logic for the item.
        this.plugin.getServer().getPluginManager().registerEvents(new ItemLogic(this), plugin);
    }

    /**
     * The class that will listen to different event and be registered in main item.
     * As all logic for an item is done through listening to the events, this class encapsulates the item logics.
     */
        private record ItemLogic(UnionDocument item) implements Listener {

        @EventHandler
        public void openUnionDocument(PlayerInteractEvent event) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (this.item.itemIsUnionDocument(event.getItem())) {
                    event.getPlayer().sendMessage("You tried to open the union document interface.");
                }
            }
        }
    }

    public boolean itemIsUnionDocument(ItemStack item) {
        if (item == null) return false;
        if (!Objects.equals(item.getType(), ITEM_MATERIAL)) return false;
        return Objects.equals(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "custom_item"), PersistentDataType.STRING), CUSTOM_ID);
    }

    public ItemStack getItemStack(){
//        Creating the stack object
        ItemStack item = new ItemStack(ITEM_MATERIAL, 1);
        ItemMeta item_meta = item.getItemMeta();
        item_meta.setCustomModelData(1);
//        Adding the NBT tag for custom item
        item_meta.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "custom_item"), PersistentDataType.STRING, CUSTOM_ID);
//        Adding the name
        item_meta.displayName(Component.text("Union document"));

//        Finishing the process
        item.setItemMeta(item_meta);
        return item;
    }

    private @NotNull ShapelessRecipe getItemRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(this.plugin, CUSTOM_ID), this.getItemStack());
        recipe.addIngredient(Material.PAPER);
        recipe.addIngredient(Material.FEATHER);
        recipe.addIngredient(Material.INK_SAC);
        return recipe;
    }


}
