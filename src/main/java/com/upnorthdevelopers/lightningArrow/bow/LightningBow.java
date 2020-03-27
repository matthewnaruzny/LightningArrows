package com.upnorthdevelopers.lightningArrow.bow;

import com.upnorthdevelopers.lightningArrow.arrows.SpecialArrow;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class LightningBow {

    public static ItemStack getBow(BowManager bowManager) {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Lightning Bow");
        List<String> bowLore = new ArrayList<String>();
        bowLore.add("Strike lightning where lands");
        bowMeta.setLore(bowLore);
        bowMeta.getPersistentDataContainer().set(bowManager.pluginBow, PersistentDataType.STRING, "true");
        bowMeta.getPersistentDataContainer().set(bowManager.bowType, PersistentDataType.STRING, "lightning");
        bow.setItemMeta(bowMeta);
        return bow;
    }
}
