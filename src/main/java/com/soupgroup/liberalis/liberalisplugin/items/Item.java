package com.soupgroup.liberalis.liberalisplugin.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Item {

//    The name is used for customItem key
    private final String id_name;
//    The custom model value for resource packs
    private final Integer custom_model_value;
//    The material of the item
    private final Material item_material;

    private final Plugin plugin;
    private final Logger log;

    private Item singleton;

    private Item(String name, Integer custom_model_value, Material material, Plugin plugin){
        this.log = Logger.getLogger("org.soupgroup.liberalis.plugin.items");
        log.finer(String.format("Creating an %s, with a texture id: %d", name, custom_model_value));
        this.id_name = name;
        this.custom_model_value = custom_model_value;
        this.item_material = material;
        this.plugin = plugin;
    }

//    public Item getInstance() {
//        return new Item()
//    }

    public String getIdName() {
        return this.id_name;
    }

    public Integer getCustomModelId() {
        return this.custom_model_value;
    }



    public boolean equal(@NotNull Item other){
        return Objects.equals(other.getIdName(), this.getIdName());

    }

    public ItemStack createItemStack(){
        ItemStack item = new ItemStack(this.item_material, 1);
        ItemMeta item_metadata = item.getItemMeta();
        item_metadata.setCustomModelData(this.custom_model_value);
        item_metadata.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "custom_item"), PersistentDataType.STRING, this.id_name);
        item_metadata.displayName(Component.text("Union document"));
        item.setItemMeta(item_metadata);
        return item;
    }
}
