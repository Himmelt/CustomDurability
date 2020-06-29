package org.soraworld.durability.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

/**
 * @author Himmelt
 */
public class CommandDurability extends CommandBase {
    @Override
    public String getCommandName() {
        return "durability";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "durability <value>";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender.canCommandSenderUseCommand(2, "gamemode");
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("cnj");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

    }
}
