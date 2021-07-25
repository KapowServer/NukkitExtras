package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class CommandClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final int maxMessages = 100;

        for(int i = 0; i < maxMessages; i++) {
            commandSender.getServer().broadcastMessage("");
        }
        commandSender.getServer().broadcastMessage(TextFormat.DARK_GREEN + "The chat has been cleared");

        return true;
    }
}
