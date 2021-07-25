package me.allink.NukkitExtras.commands;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

/**
 * @author xtypr
 * @since 2015/11/12
 */
public class CommandOP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(TextFormat.RED + "Usage: /op <player>");
            return false;
        }

        String name = strings[0];
        IPlayer player = commandSender.getServer().getOfflinePlayer(name);

        Command.broadcastCommandMessage(commandSender, new TranslationContainer("commands.op.success", player.getName()));
        if (player instanceof Player) {
            ((Player) player).sendMessage(new TranslationContainer(TextFormat.GRAY + "%commands.op.message"));
        }

        player.setOp(true);

        return true;
    }
}
