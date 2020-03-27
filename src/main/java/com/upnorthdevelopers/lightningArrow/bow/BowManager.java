/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow.bow;

import com.upnorthdevelopers.lightningArrow.LightningArrowPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowManager {

    private LightningArrowPlugin plugin;

    public NamespacedKey pluginBow;
    public NamespacedKey bowType;

    public BowManager(LightningArrowPlugin plugin){
        this.plugin = plugin;
        pluginBow = new NamespacedKey(this.plugin, "plugin-bow");
        bowType = new NamespacedKey(this.plugin, "bow-type");
    }

    public ItemStack getBow(BowType bowType, String[] args){
        switch (bowType){
            default:
            case LIGHTNING:
                return getLightningBow();
            case EXPLOSION:
                if(args.length >= 2){
                    System.out.println("Bow Arg Power" + Integer.parseInt(args[1]));
                    return getExplosionBow(Integer.parseInt(args[1]));
                } else {
                    return getExplosionBow();
                }
        }
    }

    public ItemStack getLightningBow(){
        return LightningBow.getBow(this);
    }

    public ItemStack getExplosionBow(int power){
        return new ExplosionBow(plugin).getBow(this, power);
    }

    public ItemStack getExplosionBow(){
        return getExplosionBow(5);
    }

    public boolean hasSpecialBow(Inventory inv){
        for(ItemStack itemStack : inv.getContents()){
            if(Objects.requireNonNull(itemStack.getItemMeta()).getPersistentDataContainer().has(pluginBow, PersistentDataType.STRING)){
                if(itemStack.getItemMeta().getPersistentDataContainer().get(pluginBow, PersistentDataType.STRING).equalsIgnoreCase("true")){
                    return true;
                }
            }
        }
        return false;
    }

    public BowType getBowType(ItemStack itemStack){
        if(itemStack.getItemMeta().getPersistentDataContainer().get(pluginBow, PersistentDataType.STRING).equalsIgnoreCase("true")){
            return(BowType.valueOf(itemStack.getItemMeta().getPersistentDataContainer().get(bowType, PersistentDataType.STRING).toUpperCase()));
        }
        return null;
    }
}
