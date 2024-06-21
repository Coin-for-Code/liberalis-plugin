package com.soupgroup.liberalis.liberalisplugin;

import com.soupgroup.liberalis.liberalisplugin.items.UnionDocument;
import com.soupgroup.liberalis.liberalisplugin.items.UnionDocumentListener;
import com.soupgroup.liberalis.liberalisplugin.items.UnionDocumentRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.logging.Logger;

public final class LiberalisPlugin extends JavaPlugin {

    private Connection ServerDatabaseConnection;
    private static final Logger log = Logger.getLogger("com.soupgroup.Liberalis");

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        log.info("The plugin's name is " + this.getName());
//        this.getServer().addRecipe(UnionDocumentRecipe.getUnionRecipe(this));
//
//        this.getServer().getPluginManager().registerEvents(new UnionDocumentListener(this), this);
        UnionDocument unionDocument = UnionDocument.getInstance(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
