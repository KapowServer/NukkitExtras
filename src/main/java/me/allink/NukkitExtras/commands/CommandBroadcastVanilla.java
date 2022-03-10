package me.allink.NukkitExtras.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class CommandBroadcastVanilla implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(TextFormat.RED + "Usage: /" + s + " <message ..>");
        } else {
            Command.broadcastCommandMessage(commandSender, TextFormat.colorize('&', String.join(" ", strings)));
        }
        return true;
    }
}
