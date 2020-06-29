package org.soraworld.durability;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.soraworld.durability.proxy.CommonProxy;

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
            serverSide = "org.soraworld.durability.proxy.CommonProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void onInit(FMLPreInitializationEvent event) {
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        proxy.onLoadComplete(event);
    }
}
