package org.soraworld.durability;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;

/**
 * @author Himmelt
 */
@Mod(
        modid = "customdurability",
        name = "CustomDurability",
        version = "1.0.0",
        acceptableRemoteVersions = "*",
        useMetadata = true
)
public final class CustomDurability {

    @SidedProxy(
            clientSide = "org.soraworld.durability.client.ClientProxy",
            serverSide = "org.soraworld.durability.CommonProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        proxy.onLoadComplete(event);
    }
}
