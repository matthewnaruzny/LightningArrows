/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow.bow;

import com.upnorthdevelopers.lightningArrow.BowType;
import com.upnorthdevelopers.lightningArrow.LightningArrowPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class BowManager {

    private LightningArrowPlugin plugin;

    private NamespacedKey pluginBow;
    private NamespacedKey bowType;

    public BowManager(LightningArrowPlugin plugin){
        this.plugin = plugin;
        pluginBow = new NamespacedKey(this.plugin, "plugin-bow");
        bowType = new NamespacedKey(this.plugin, "bow-type");
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
        bowMeta.getPersistentDataContainer().set(pluginBow, PersistentDataType.STRING, "true");
        bowMeta.getPersistentDataContainer().set(bowType, PersistentDataType.STRING, "lightning");
        bow.setItemMeta(bowMeta);
        return bow;
    }

    public ItemStack getExplosionBow(int power){
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Explosion Bow");
        List<String> bowLore = new ArrayList<String>();
        bowLore.add("Cause explosion where lands");
        bowMeta.setLore(bowLore);
        bowMeta.getPersistentDataContainer().set(pluginBow, PersistentDataType.STRING, "true");
        bowMeta.getPersistentDataContainer().set(bowType, PersistentDataType.STRING, "explosion");
        bow.setItemMeta(bowMeta);
        return bow;
    }

    public ItemStack getExplosionBow(){
        return getExplosionBow(5);
    }

    public boolean hasSpecialBow(Inventory inv){
        for(ItemStack itemStack : inv.getContents()){
            if(itemStack.getItemMeta().getPersistentDataContainer().has(pluginBow, PersistentDataType.STRING)){
                if(itemStack.getItemMeta().getPersistentDataContainer().get(pluginBow, PersistentDataType.STRING).equalsIgnoreCase("true")){
                    return true;
                }
            }
        }
        return false;
    }

    public BowType getBowType(ItemStack itemStack){
        if(itemStack.getItemMeta().getPersistentDataContainer().get(pluginBow, PersistentDataType.STRING).equalsIgnoreCase("true")){
            return(BowType.valueOf(itemStack.getItemMeta().getPersistentDataContainer().get(bowType, PersistentDataType.STRING)));
        }
        return null;
    }
}
