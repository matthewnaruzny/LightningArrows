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

public class ArrowEventListener implements Listener {

    ArrayList<UUID> lightningArrowList;
    ArrayList<UUID> explosionArrowList;

    ArrowEventListener(){
        lightningArrowList = new ArrayList<UUID>();
        explosionArrowList = new ArrayList<UUID>();
    }

    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event){
        Player player = (Player) event.getEntity().getShooter();
        if(player.hasPermission("lightningArrow.shoot") || player.isOp()){
            if(LightningArrow.hasSpecialBow(player.getInventory())){
                if(player.getInventory().getItemInMainHand().equals(LightningArrow.getLightningBow())){
                    lightningArrowList.add(event.getEntity().getUniqueId());
                }
                if(player.getInventory().getItemInMainHand().equals(LightningArrow.getExplosionBow())){
                    explosionArrowList.add(event.getEntity().getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent event){
        if(lightningArrowList.contains(event.getEntity().getUniqueId())){
            lightningArrowList.remove(event.getEntity().getUniqueId());
            try{
                event.getHitEntity().getWorld().strikeLightning(event.getHitEntity().getLocation());
            } catch (NullPointerException ex){
               try{
                   event.getHitBlock().getWorld().strikeLightning(event.getHitBlock().getLocation());
               } catch (NullPointerException ignored){

               }
            }
            event.getEntity().remove();
        }
        if(explosionArrowList.contains(event.getEntity().getUniqueId())){
            explosionArrowList.remove(event.getEntity().getUniqueId());
            try{
                event.getHitEntity().getWorld().createExplosion(event.getHitEntity().getLocation(), 5f);
            } catch (NullPointerException ex){
                try{
                    event.getHitBlock().getWorld().createExplosion(event.getHitBlock().getLocation(), 5f);
                } catch (NullPointerException ignored){

                }
            }
            event.getEntity().remove();
        }
    }
}
