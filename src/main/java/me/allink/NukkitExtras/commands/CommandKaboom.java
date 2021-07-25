package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import me.allink.NukkitExtras.modules.level.LevelUtils;

import java.util.Random;

public class CommandKaboom implements CommandExecutor {
    private double getRandom(final int min, final int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final Player player = (Player) commandSender;
        int random = new Random().nextBoolean() ? 0 : 1;

        if (random == 0) {
            final Location location = player.getLocation();
            final Level level = player.getLevel();
            final int explosionCount = 20;

            for (int i = 0; i < explosionCount; i++) {
                final double posX = location.getX() + getRandom(-15, 15);
                final double posY = location.getY() + getRandom(-6, 6);
                final double posZ = location.getZ() + getRandom(-15, 15);

                final Location explodeLocation = new Location(posX, posY, posZ, level);

                LevelUtils.createExplosion(explodeLocation);

                level.setBlock(explodeLocation, Block.get(BlockID.LAVA));
            }

            player.sendMessage("Forgive me :c");
        } else {
            player.getInventory().setItemInHand(new Item(Item.CAKE));
            player.sendMessage("Have a nice day :)");
        }
        return true;
    }
}
