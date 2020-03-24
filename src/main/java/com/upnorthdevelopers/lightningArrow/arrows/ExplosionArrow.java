/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow.arrows;

import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

public class ExplosionArrow implements SpecialArrow {

    private UUID uuid;
    private Float power;

    public ExplosionArrow(UUID uuid){
        this(uuid, 5f);
    }

    public ExplosionArrow(UUID uuid, Float power){
        this.uuid = uuid;
        this.power = power;
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void impact(ProjectileHitEvent event) {
        try{
            event.getHitEntity().getWorld().createExplosion(event.getHitEntity().getLocation(), power);
        } catch (NullPointerException ex){
            try{
                event.getHitBlock().getWorld().createExplosion(event.getHitBlock().getLocation(), power);
            } catch (NullPointerException ignored){

            }
        }
        event.getEntity().remove();
    }
}
