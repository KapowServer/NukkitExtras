package me.allink.NukkitExtras;

import cn.nukkit.Server;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import me.allink.NukkitExtras.commands.*;
import me.allink.NukkitExtras.modules.entity.EntityExplosion;
import me.allink.NukkitExtras.modules.entity.EntityKnockback;
import me.allink.NukkitExtras.modules.player.PlayerChat;
import me.allink.NukkitExtras.modules.player.PlayerCommand;
import me.allink.NukkitExtras.modules.player.PlayerConnection;
import me.allink.NukkitExtras.modules.player.PlayerDamage;

import javax.annotation.Resources;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends PluginBase {
    private static Main INSTANCE = null;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        ((PluginCommand<?>) this.getCommand("broadcastvanilla")).setExecutor(new CommandBroadcastVanilla());
        ((PluginCommand<?>) this.getCommand("clearchat")).setExecutor(new CommandClearChat());
        ((PluginCommand<?>) this.getCommand("console")).setExecutor(new CommandConsole());
        ((PluginCommand<?>) this.getCommand("destroyentities")).setExecutor(new CommandDestroyEntities());
        ((PluginCommand<?>) this.getCommand("enchantall")).setExecutor(new CommandEnchantAll());
        ((PluginCommand<?>) this.getCommand("jumpscare")).setExecutor(new CommandJumpscare());
        ((PluginCommand<?>) this.getCommand("kaboom")).setExecutor(new CommandKaboom());
        ((PluginCommand<?>) this.getCommand("ping")).setExecutor(new CommandPing());
        ((PluginCommand<?>) this.getCommand("op")).setExecutor(new CommandOP());
        ((PluginCommand<?>) this.getCommand("prefix")).setExecutor(new CommandPrefix());
        ((PluginCommand<?>) this.getCommand("pumpkin")).setExecutor(new CommandPumpkin());
        ((PluginCommand<?>) this.getCommand("spawn")).setExecutor(new CommandSpawn());
        ((PluginCommand<?>) this.getCommand("tellraw")).setExecutor(new CommandTellraw());

        Server.getInstance().getPluginManager().registerEvents(new PlayerChat(), this);
        Server.getInstance().getPluginManager().registerEvents(new PlayerCommand(), this);
        Server.getInstance().getPluginManager().registerEvents(new PlayerConnection(), this);
        Server.getInstance().getPluginManager().registerEvents(new PlayerDamage(), this);
        Server.getInstance().getPluginManager().registerEvents(new EntityExplosion(), this);
        Server.getInstance().getPluginManager().registerEvents(new EntityKnockback(), this);

        //Configuration check
        File configFile = new File(this.getDataFolder(), "config.yml");

        try {
            if(configFile.exists()) {
                if((Files.readAllBytes(Paths.get(configFile.getPath())).length < 10)) {
                    configFile.delete();
                    saveDefaultConfig();
                    reloadConfig();
                }
            } else {
                saveDefaultConfig();
                reloadConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
            saveDefaultConfig();
            reloadConfig();
        }
    }

    public static Main getInstance() {
        return INSTANCE;
    }
}
