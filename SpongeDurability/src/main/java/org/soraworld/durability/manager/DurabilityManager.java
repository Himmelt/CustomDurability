package org.soraworld.durability.manager;

import org.soraworld.violet.manager.SpongeManager;
import org.soraworld.violet.plugin.SpongePlugin;
import org.soraworld.violet.util.ChatColor;

import java.nio.file.Path;

public class DurabilityManager extends SpongeManager {

    public DurabilityManager(SpongePlugin plugin, Path path) {
        super(plugin, path);
    }

    public ChatColor defChatColor() {
        return ChatColor.YELLOW;
    }
}
