package org.soraworld.durability.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import org.soraworld.durability.proxy.CommonProxy;

import java.util.HashMap;

/**
 * @author Himmelt
 */
public class ClientProxy extends CommonProxy {

    private final HashMap<Integer, Integer> defaultMaxDurability = new HashMap<>();

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        super.onPreInit(event);
    }

    @Override
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        defaultMaxDurability.clear();
        for (Object o : Item.itemRegistry) {
            if (o instanceof Item) {
                defaultMaxDurability.put(Item.getIdFromItem((Item) o), ((Item) o).getMaxDamage());
            }
        }
        super.onLoadComplete(event);
    }

    @SubscribeEvent
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        defaultMaxDurability.forEach((id, damage) -> Item.getItemById(id).setMaxDamage(damage));
    }
}
