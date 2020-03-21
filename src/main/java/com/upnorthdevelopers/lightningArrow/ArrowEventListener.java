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

    ArrayList<AliveArrow> aliveArrowList;

    ArrowEventListener(){
        aliveArrowList = new ArrayList<AliveArrow>();
    }

    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event){
        Player player = (Player) event.getEntity().getShooter();
        if(player.hasPermission("lightningArrow.shoot") || player.isOp()){
            if(LightningArrow.hasSpecialBow(player.getInventory())){
                if(player.getInventory().getItemInMainHand().equals(LightningArrow.getLightningBow())){
                    aliveArrowList.add(new AliveArrow(event.getEntity().getUniqueId(), BowType.LIGHTNING));
                }
                if(player.getInventory().getItemInMainHand().equals(LightningArrow.getExplosionBow())){
                    aliveArrowList.add(new AliveArrow(event.getEntity().getUniqueId(), BowType.EXPLOSION));
                }
            }
        }
    }

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent event){
        for(AliveArrow arrow : aliveArrowList){
            if(event.getEntity().getUniqueId() == arrow.getArrowUUID()){
                aliveArrowList.remove(arrow);
                if(arrow.getArrowAction() == BowType.LIGHTNING){
                    //Lightning Arrow
                    try{
                        event.getHitEntity().getWorld().strikeLightning(event.getHitEntity().getLocation());
                    } catch (NullPointerException ex){
                        try{
                            event.getHitBlock().getWorld().strikeLightning(event.getHitBlock().getLocation());
                        } catch (NullPointerException ignored){

                        }
                    }
                } else if(arrow.getArrowAction() == BowType.EXPLOSION){
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
                }
            }
        }
    }
}
