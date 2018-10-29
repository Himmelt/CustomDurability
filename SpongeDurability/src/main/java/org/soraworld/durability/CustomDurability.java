package org.soraworld.durability;

import org.soraworld.durability.command.CommandDurability;
import org.soraworld.durability.manager.DurabilityManager;
import org.soraworld.violet.Violet;
import org.soraworld.violet.command.SpongeBaseSubs;
import org.soraworld.violet.command.SpongeCommand;
import org.soraworld.violet.manager.SpongeManager;
import org.soraworld.violet.plugin.SpongePlugin;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import java.nio.file.Path;
import java.util.List;

@Plugin(
        id = CustomDurability.PLUGIN_ID,
        name = CustomDurability.PLUGIN_NAME,
        version = CustomDurability.PLUGIN_VERSION,
        description = "CustomDurability Plugin for sponge.",
        dependencies = {
                @Dependency(
                        id = Violet.PLUGIN_ID,
                        version = Violet.PLUGIN_VERSION
                )
        }
)
public class CustomDurability extends SpongePlugin {

    public static final String PLUGIN_ID = "customdurability";
    public static final String PLUGIN_NAME = "CustomDurability";
    public static final String PLUGIN_VERSION = "1.0.0";

    protected SpongeManager registerManager(Path path) {
        return new DurabilityManager(this, path);
    }

    protected List<Object> registerListeners() {
        return null;
    }

    protected void registerCommands() {
        SpongeCommand command = new SpongeCommand(getId(), manager.defAdminPerm(), false, manager);
        command.extractSub(SpongeBaseSubs.class);
        command.extractSub(CommandDurability.class);
        register(this, command);
    }
}
