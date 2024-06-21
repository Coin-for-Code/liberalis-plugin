package com.soupgroup.liberalis.liberalisplugin.items;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.Material;

public class UnionDocumentRecipe {
    public static ShapelessRecipe getUnionRecipe(Plugin mainPlugin) {
        NamespacedKey recipe_key = new NamespacedKey(mainPlugin, "union_recipe");
        NamespacedKey custom_items_key = new NamespacedKey(mainPlugin, "customItem");

        ItemStack union_document = new ItemStack(Material.PAPER, 1);
        ItemMeta document_meta = union_document.getItemMeta();
        document_meta.setCustomModelData(1);
        document_meta.getPersistentDataContainer().set(custom_items_key, PersistentDataType.STRING, "union_document");
        document_meta.displayName(Component.text("Union document"));
        union_document.setItemMeta(document_meta);


        ShapelessRecipe recipe = new ShapelessRecipe(recipe_key, union_document);
        recipe.addIngredient(Material.PAPER);
        recipe.addIngredient(Material.FEATHER);
        recipe.addIngredient(Material.INK_SAC);

        return recipe;
    }
}
