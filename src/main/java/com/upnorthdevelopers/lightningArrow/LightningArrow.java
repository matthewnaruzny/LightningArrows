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

    private BowManager bowManager;

    public LightningArrow(BowManager bowManager) {
        this.bowManager = bowManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            BowType bowType;
            try{
                bowType = BowType.valueOf(strings[0].toUpperCase());
                player.getInventory().addItem(bowManager.getBow(bowType));
            } catch (ArrayIndexOutOfBoundsException ex){
                player.getInventory().addItem(bowManager.getBow(BowType.LIGHTNING));
            }

        }
        return true;
    }

}