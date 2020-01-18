/*
 * Copyright (c) 2020 Matthew Naruzny
 */

package com.upnorthdevelopers.lightningArrow;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LightningArrow implements CommandExecutor {

    LightningArrowPlugin plugin;

    public LightningArrow(LightningArrowPlugin plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            BowType bowType;
            try{
                bowType = BowType.valueOf(strings[0].toUpperCase());
                player.getInventory().addItem(getBow(bowType));
            } catch (ArrayIndexOutOfBoundsException ex){
                player.getInventory().addItem(getBow(BowType.LIGHTNING));
            }

        }
        return true;
    }

    public static ItemStack getBow(BowType bowType){
        switch (bowType){
            default:
            case LIGHTNING:
                return getLightningBow();
            case EXPLOSION:
                return getExplosionBow();
        }
    }

    public static ItemStack getLightningBow(){
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Lightning Bow");
        List<String> bowLore = new ArrayList<String>();
        bowLore.add("Strike lightning where lands");
        bowMeta.setLore(bowLore);
        bow.setItemMeta(bowMeta);
        return bow;

    }

    public static ItemStack getExplosionBow(){
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Explosion Bow");
        List<String> bowLore = new ArrayList<String>();
        bowLore.add("Cause explosion where lands");
        bowMeta.setLore(bowLore);
        bow.setItemMeta(bowMeta);
        return bow;

    }

    public static boolean hasSpecialBow(Inventory inv){
        for(BowType bowType : BowType.values()){
            if(inv.contains(getBow(bowType))){
                return true;
            }
        }
        return false;
    }

}

enum BowType {
    LIGHTNING,
    EXPLOSION;
}