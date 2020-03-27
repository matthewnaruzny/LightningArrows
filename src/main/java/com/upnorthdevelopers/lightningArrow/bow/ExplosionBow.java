package com.upnorthdevelopers.lightningArrow.bow;

import com.upnorthdevelopers.lightningArrow.LightningArrowPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ExplosionBow {

    private LightningArrowPlugin plugin;
    private NamespacedKey bowPower;

    public ExplosionBow(LightningArrowPlugin plugin){
        this.plugin = plugin;
        bowPower = new NamespacedKey(this.plugin, "bow-power");
    }

    public ItemStack getBow(BowManager bowManager, int power) {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Explosion Bow");
        List<String> bowLore = new ArrayList<String>();
        bowLore.add("Cause explosion where lands");
        bowMeta.setLore(bowLore);
        bowMeta.getPersistentDataContainer().set(bowManager.pluginBow, PersistentDataType.STRING, "true");
        bowMeta.getPersistentDataContainer().set(bowManager.bowType, PersistentDataType.STRING, "explosion");
        bowMeta.getPersistentDataContainer().set(bowPower, PersistentDataType.INTEGER, power);
        bow.setItemMeta(bowMeta);
        return bow;
    }

    public int getBowPower(ItemMeta explosionBow){
        if(explosionBow.getPersistentDataContainer().has(bowPower, PersistentDataType.INTEGER)){
            return explosionBow.getPersistentDataContainer().get(bowPower, PersistentDataType.INTEGER);
        }
        System.out.println("No Power");
        return 0;
    }
}
