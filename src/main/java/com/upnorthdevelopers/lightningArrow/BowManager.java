/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BowManager {

    private LightningArrowPlugin plugin;

    public BowManager(LightningArrowPlugin plugin){
        this.plugin = plugin;
    }

    public ItemStack getBow(BowType bowType){
        switch (bowType){
            default:
            case LIGHTNING:
                return getLightningBow();
            case EXPLOSION:
                return getExplosionBow();
        }
    }

    public ItemStack getLightningBow(){
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Lightning Bow");
        List<String> bowLore = new ArrayList<String>();
        bowLore.add("Strike lightning where lands");
        bowMeta.setLore(bowLore);
        bow.setItemMeta(bowMeta);
        return bow;

    }

    public ItemStack getExplosionBow(){
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Explosion Bow");
        List<String> bowLore = new ArrayList<String>();
        bowLore.add("Cause explosion where lands");
        bowMeta.setLore(bowLore);
        bow.setItemMeta(bowMeta);
        return bow;

    }

    public boolean hasSpecialBow(Inventory inv){
        for(BowType bowType : BowType.values()){
            if(inv.contains(getBow(bowType))){
                return true;
            }
        }
        return false;
    }
}
