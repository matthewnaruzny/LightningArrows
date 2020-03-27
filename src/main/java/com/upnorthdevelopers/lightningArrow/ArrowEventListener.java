/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow;

import com.upnorthdevelopers.lightningArrow.arrows.ExplosionArrow;
import com.upnorthdevelopers.lightningArrow.arrows.LightningArrowEntity;
import com.upnorthdevelopers.lightningArrow.arrows.SpecialArrow;
import com.upnorthdevelopers.lightningArrow.bow.BowManager;
import com.upnorthdevelopers.lightningArrow.bow.BowType;
import com.upnorthdevelopers.lightningArrow.bow.ExplosionBow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ArrowEventListener implements Listener {

    private Map<UUID, SpecialArrow> aliveArrowList;
    private BowManager bowManager;
    private LightningArrowPlugin plugin;

    public ArrowEventListener(LightningArrowPlugin plugin, BowManager bowManager){
        aliveArrowList = new HashMap<>();
        this.plugin = plugin;
        this.bowManager = bowManager;
    }

    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event){
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if(player.hasPermission("lightningArrow.shoot") || player.isOp()){
                if(bowManager.hasSpecialBow(player.getInventory())){
                    if(player.getInventory().getItemInMainHand().equals(bowManager.getLightningBow())){
                        aliveArrowList.put(event.getEntity().getUniqueId(), new LightningArrowEntity(event.getEntity().getUniqueId()));
                    }
                    if(bowManager.getBowType(player.getInventory().getItemInMainHand()).equals(BowType.EXPLOSION)){
                        int power = new ExplosionBow(plugin).getBowPower(Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()));
                        aliveArrowList.put(event.getEntity().getUniqueId(), new ExplosionArrow(event.getEntity().getUniqueId(), (float) power));
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
