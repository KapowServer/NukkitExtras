package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class CommandPing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final Player player = (Player) commandSender;
        final int ping = player.getPing();
        final int d = (int) Math.floor(ping / 100);
        TextFormat highlighting = TextFormat.WHITE;

        switch (d) {
            case 0:
                highlighting = TextFormat.GREEN;
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                highlighting = TextFormat.YELLOW;
                break;
            case 5:
                highlighting = TextFormat.RED;
                break;
            default:
                if (d > 5) {
                    highlighting = TextFormat.DARK_RED;
                }
                break;
        }

        player.sendMessage("Your ping is " + highlighting + ping + "ms.");

        return true;
    }
}
