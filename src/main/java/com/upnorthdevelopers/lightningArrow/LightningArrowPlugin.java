/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow;

import com.upnorthdevelopers.lightningArrow.bow.BowManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LightningArrowPlugin extends JavaPlugin {

    @Override
    public void onEnable(){

        BowManager bowManager = new BowManager(this);

        // Register Commands
        this.getCommand("lightningArrow").setExecutor(new LightningArrowCommand(bowManager));

        // Register TabCompleters
        this.getCommand("lightningArrow").setTabCompleter(new LightningArrowTabCompleter());

        // Register Listeners
        getServer().getPluginManager().registerEvents(new ArrowEventListener(this, bowManager), this);
    }
}
