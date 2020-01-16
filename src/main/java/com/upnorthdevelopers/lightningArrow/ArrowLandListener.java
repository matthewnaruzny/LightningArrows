/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.ArrayList;
import java.util.UUID;

public class ArrowLandListener implements Listener {

    ArrayList<UUID> flyingArrowsList;

    ArrowLandListener(){
        flyingArrowsList = new ArrayList<UUID>();
    }

    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event){
        Player player = (Player) event.getEntity().getShooter();
        if(player.hasPermission("lightningArrow.shoot") || player.isOp()){
           if(player.getInventory().contains(LightningArrow.getLightningBow())){
               flyingArrowsList.add(event.getEntity().getUniqueId());
           }
        }
    }

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent event){
        if(flyingArrowsList.contains(event.getEntity().getUniqueId())){
            flyingArrowsList.remove(event.getEntity().getUniqueId());
            try{
                event.getHitEntity().getWorld().strikeLightning(event.getHitEntity().getLocation());
            } catch (NullPointerException ex){
                event.getHitBlock().getWorld().strikeLightning(event.getHitBlock().getLocation());
            }
        }
    }
}
