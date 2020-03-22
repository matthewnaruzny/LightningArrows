/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow;

import org.bukkit.plugin.java.JavaPlugin;

public class LightningArrowPlugin extends JavaPlugin {

    @Override
    public void onEnable(){
        // Register Commands
        this.getCommand("lightningArrow").setExecutor(new LightningArrow(this));

        // Register Listeners
        getServer().getPluginManager().registerEvents(new ArrowEventListener(), this);
    }
}
