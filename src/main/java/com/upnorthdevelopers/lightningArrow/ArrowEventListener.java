/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow;

import com.upnorthdevelopers.lightningArrow.arrows.ExplosionArrow;
import com.upnorthdevelopers.lightningArrow.arrows.LightningArrowEntity;
import com.upnorthdevelopers.lightningArrow.arrows.SpecialArrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArrowEventListener implements Listener {

    Map<UUID, SpecialArrow> aliveArrowList;

    ArrowEventListener(){
        aliveArrowList = new HashMap<>();
    }

    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if(player.hasPermission("lightningArrow.shoot") || player.isOp()){
                if(LightningArrow.hasSpecialBow(player.getInventory())){
                    if(player.getInventory().getItemInMainHand().equals(LightningArrow.getLightningBow())){
                        aliveArrowList.put(event.getEntity().getUniqueId(), new LightningArrowEntity(event.getEntity().getUniqueId()));
                    }
                    if(player.getInventory().getItemInMainHand().equals(LightningArrow.getExplosionBow())){
                        aliveArrowList.put(event.getEntity().getUniqueId(), new ExplosionArrow(event.getEntity().getUniqueId()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent event){

        if(aliveArrowList.containsKey(event.getEntity().getUniqueId())){
            aliveArrowList.get(event.getEntity().getUniqueId()).impact(event);
            aliveArrowList.remove(event.getEntity().getUniqueId());
        }
    }
}
