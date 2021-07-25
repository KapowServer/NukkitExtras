package me.allink.NukkitExtras.commands;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.SetWorldSpawnCommand;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.EntityHumanType;
import cn.nukkit.level.Level;

public class CommandDestroyEntities implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        int entityCount = 0;
        int levelCount = 0;

        for(Level level: commandSender.getServer().getLevels().values()) {
            for(Entity entity: level.getEntities()) {
                if(!(entity instanceof EntityHuman)) {
                    entity.despawnFromAll();
                    level.removeEntity(entity);
                    entityCount++;
                }
            }
            levelCount++;

        }


        commandSender.sendMessage(String.format("Successfully destroyed %d entities in %d levels", entityCount, levelCount));
        return true;
    }
}
