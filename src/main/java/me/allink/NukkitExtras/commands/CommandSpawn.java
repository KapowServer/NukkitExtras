package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;

public class CommandSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Command has to be run by a player");
        } else {
            final Player player = (Player) sender;
            final Level level = sender.getServer().getLevelByName("world");
            final Location spawnLocation = level.getSafeSpawn().getLocation();
            final int maxWorldHeight = 256;

            player.teleport(spawnLocation);
            player.sendMessage("Successfully moved to spawn");
        }
        return true;
    }
}
