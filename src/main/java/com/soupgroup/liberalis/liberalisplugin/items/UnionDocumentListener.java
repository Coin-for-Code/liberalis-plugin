package com.soupgroup.liberalis.liberalisplugin.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class UnionDocumentListener implements Listener {

    private final Plugin root;

    public UnionDocumentListener(Plugin plugin) {
        this.root = plugin;
    }

    @EventHandler
    public void openUnionDocument(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack document = event.getItem();
            if (document != null &&
                    document.getType().equals(Material.PAPER) &&
                    Objects.equals(document.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(root, "customItem"), PersistentDataType.STRING), "union_document")) {
                event.getPlayer().sendMessage("You tried to open the union document interface.");
            }
        }
    }
}
