package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

public class CommandPumpkin implements CommandExecutor {
    private void placePumpkin(final Player player) {
        player.getInventory().setHelmet(new Item(BlockID.PUMPKIN, 1, 1));
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 0) {
            sender.sendMessage(TextFormat.RED + "Usage: /" + label + " <player>");
        } else {
            if (args[0].equals("*") || args[0].equals("**")) {
                for (Player onlinePlayer : sender.getServer().getOnlinePlayers().values()) {
                    placePumpkin(onlinePlayer);
                }
                sender.sendMessage("Everyone is now a pumpkin");
            } else {
                final Player target = sender.getServer().getPlayer(args[0]);
                if (target != null) {
                    placePumpkin(target);
                    sender.sendMessage("Player \"" + target.getName() + "\" is now a pumpkin");
                } else {
                    sender.sendMessage("Player \"" + args[0] + "\" not found");
                }
            }
        }
        return true;
    }
}
