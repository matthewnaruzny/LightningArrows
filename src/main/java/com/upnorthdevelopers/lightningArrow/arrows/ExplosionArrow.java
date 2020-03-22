/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow.arrows;

import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

public class ExplosionArrow implements SpecialArrow {

    private UUID uuid;

    public ExplosionArrow(UUID uuid){
        this.uuid = uuid;
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void impact(ProjectileHitEvent event) {
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
