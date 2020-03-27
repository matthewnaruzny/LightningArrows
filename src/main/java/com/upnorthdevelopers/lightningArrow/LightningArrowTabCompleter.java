package com.upnorthdevelopers.lightningArrow;

import com.upnorthdevelopers.lightningArrow.bow.BowType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LightningArrowTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("lightningArrow") && commandSender instanceof Player){
            Player p = (Player) commandSender;

            List<String> bows = new ArrayList<>();
            BowType[] bowTypes = BowType.values();
            for(BowType bowType : bowTypes){
                bows.add(bowType.toString().toLowerCase());
            }

            return bows;
        }
        return null;
    }
}
