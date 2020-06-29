package org.soraworld.durability.proxy;

import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Himmelt
 */
public class CommonProxy {

    private final HashMap<Integer, Integer> maxDurability = new HashMap<>();

    public void onPreInit(FMLPreInitializationEvent event) {
        // TODO 从文件加载配置
        File cfgFile = event.getSuggestedConfigurationFile();
        if (cfgFile.exists()) {
            Configuration config = new Configuration(cfgFile);
            config.load();
            String[] list = config.getStringList("durabilities", "general", new String[]{}, "自定义耐久度");
            if (list != null && list.length > 0) {
                for (String line : list) {
                    String[] ss = line.split(",");
                    if (ss.length == 2) {
                        try {
                            maxDurability.put(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]));
                        } catch (Throwable ignored) {
                        }
                    }
                }
            }
        } else {
            try {
                cfgFile.createNewFile();
                Configuration config = new Configuration(cfgFile);
                config.save();
            } catch (IOException ignored) {
            }
        }
    }

    public void onLoadComplete(FMLLoadCompleteEvent event) {
        maxDurability.forEach((id, damage) -> Item.getItemById(id).setMaxDamage(damage));
    }

    @SubscribeEvent
    public void onClientConnect(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            // TODO send maxDurability
        }
    }
}
