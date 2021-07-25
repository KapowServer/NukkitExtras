package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import me.allink.NukkitExtras.Main;

import java.io.File;

public class CommandPrefix implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("Command has to be run by a player");
        } else {
            final Player player = (Player) commandSender;
            final File configFile = new File(Main.getInstance().getDataFolder(), "prefixes.yml");
            final Config prefixConfig = new Config(configFile);

            try {
                if (strings.length == 0) {
                    player.sendMessage(TextFormat.RED + "Usage: /" + s + " <prefix|off>");
                } else if ("off".equalsIgnoreCase(strings[0])) {
                    prefixConfig.remove(player.getUniqueId().toString());
                    prefixConfig.save(configFile);
                    player.sendMessage("You no longer have a tag");
                } else {
                    prefixConfig.set(player.getUniqueId().toString(), String.join(" ", strings));
                    prefixConfig.save(configFile);
                    player.sendMessage("You now have the tag: " + TextFormat.colorize('&', String.join(" ", strings)));
                }
            } catch (Exception exception) {
                player.sendMessage("Something went wrong while saving the prefix. Please check console.");
                exception.printStackTrace();
            }
        }
        return true;
    }
}
