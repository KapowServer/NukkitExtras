package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;

public class CommandEnchantAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("Command has to be run by a player");
        } else {
            final Player player = (Player) commandSender;
            final Item item = player.getInventory().getItemInHand();

            if(Block.AIR == item.getBlockId()) {
                player.sendMessage("Please hold an item in your hand to enchant it");
            } else {
                for(Enchantment enchantment: Enchantment.getRegisteredEnchantments()) {
                    enchantment.setLevel(Short.MAX_VALUE, false);
                    item.addEnchantment(enchantment);
                }

                player.getInventory().setItemInHand(item);
                player.sendMessage("I killed Mathias.");
            }
        }

        return true;
    }
}
