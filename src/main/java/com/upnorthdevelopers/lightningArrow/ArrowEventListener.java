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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArrowEventListener implements Listener {

    Map<UUID, BowType> aliveArrowList;

    ArrowEventListener(){
        aliveArrowList = new HashMap<>();
    }

    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event){
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if(player.hasPermission("lightningArrow.shoot") || player.isOp()){
                if(LightningArrow.hasSpecialBow(player.getInventory())){
                    if(player.getInventory().getItemInMainHand().equals(LightningArrow.getLightningBow())){
                        aliveArrowList.put(event.getEntity().getUniqueId(), BowType.LIGHTNING);
                    }
                    if(player.getInventory().getItemInMainHand().equals(LightningArrow.getExplosionBow())){
                        aliveArrowList.put(event.getEntity().getUniqueId(), BowType.EXPLOSION);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent event){

        if(aliveArrowList.containsKey(event.getEntity().getUniqueId())){
            BowType action = aliveArrowList.get(event.getEntity().getUniqueId());
            if(action == BowType.LIGHTNING){
                //Lightning Arrow
                try{
                    event.getHitEntity().getWorld().strikeLightning(event.getHitEntity().getLocation());
                } catch (NullPointerException ex){
                    try{
                        event.getHitBlock().getWorld().strikeLightning(event.getHitBlock().getLocation());
                    } catch (NullPointerException ignored){

                    }
                }
            } else if(action == BowType.EXPLOSION){
                //Explosion Arrow
                try{
                    event.getHitEntity().getWorld().createExplosion(event.getHitEntity().getLocation(), 5f);
                } catch (NullPointerException ex){
                    try{
                        event.getHitBlock().getWorld().createExplosion(event.getHitBlock().getLocation(), 5f);
                    } catch (NullPointerException ignored){

                    }
                }
                event.getEntity().remove();
                aliveArrowList.remove(event.getEntity().getUniqueId());
            }
        }
    }
}
