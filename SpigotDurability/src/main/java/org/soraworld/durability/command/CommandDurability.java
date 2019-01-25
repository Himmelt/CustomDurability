package org.soraworld.durability.command;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.soraworld.durability.manager.DurabilityManager;
import org.soraworld.violet.command.Args;
import org.soraworld.violet.command.SpigotCommand;
import org.soraworld.violet.command.Sub;

public final class CommandDurability {

    @Sub(perm = "admin", onlyPlayer = true)
    public static void set(SpigotCommand self, CommandSender sender, Args args) {
        DurabilityManager manager = (DurabilityManager) self.manager;
        Player player = (Player) sender;
        if (args.notEmpty()) {
            ItemStack stack = player.getItemInHand();
            if (stack != null && stack.getType() != Material.AIR) {
                try {
                    manager.setMaxDamage(stack, Integer.valueOf(args.first()));
                    manager.sendKey(player, "setMaxDamage");
                } catch (Throwable e) {
                    manager.sendKey(player, "invalidInt");
                }
            } else manager.sendKey(player, "emptyHand");
        } else manager.sendKey(player, "emptyArgs");
    }
}
