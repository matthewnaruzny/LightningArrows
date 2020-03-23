/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow.arrows;

import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

public class LightningArrowEntity implements SpecialArrow {

    private UUID uuid;

    public LightningArrowEntity(UUID uuid){
        this.uuid = uuid;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void impact(ProjectileHitEvent event) {
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
}
