package org.soraworld.durability;

import org.bukkit.event.Listener;
import org.soraworld.durability.command.CommandDurability;
import org.soraworld.durability.manager.DurabilityManager;
import org.soraworld.violet.command.SpigotBaseSubs;
import org.soraworld.violet.command.SpigotCommand;
import org.soraworld.violet.manager.SpigotManager;
import org.soraworld.violet.plugin.SpigotPlugin;

import java.nio.file.Path;
import java.util.List;

public class CustomDurability extends SpigotPlugin {

    protected SpigotManager registerManager(Path path) {
        return new DurabilityManager(this, path);
    }

    protected List<Listener> registerListeners() {
        return null;
    }

    protected void registerCommands() {
        SpigotCommand command = new SpigotCommand(getId(), manager.defAdminPerm(), false, manager, "durability", "damage");
        command.extractSub(SpigotBaseSubs.class);
        command.extractSub(CommandDurability.class);
        register(this, command);
    }
}
