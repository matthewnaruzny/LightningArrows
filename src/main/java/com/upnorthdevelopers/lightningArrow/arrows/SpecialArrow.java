/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow.arrows;

import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

public interface SpecialArrow {

    UUID getUUID();
    void impact(ProjectileHitEvent event);
}
