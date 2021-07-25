package me.allink.NukkitExtras.modules.player;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import me.allink.NukkitExtras.Main;

import java.io.File;
import java.util.UUID;

public class PlayerChat implements Listener {
    @EventHandler
    void onPlayerChat(final PlayerChatEvent event) {
        final Player player = event.getPlayer();
        final UUID playerUuid = event.getPlayer().getUniqueId();

        if (PlayerCommand.getCommandMillisList().get(playerUuid) != null) {
            final long millisDifference = System.currentTimeMillis() - PlayerCommand.getCommandMillisList().get(playerUuid);

            if (millisDifference < 50) {
                event.setCancelled(true);
            }
        }

        PlayerCommand.getCommandMillisList().put(playerUuid, System.currentTimeMillis());

        if (event.isCancelled()) {
            return;
        }

        final File configFile = new File(Main.getInstance().getDataFolder(), "prefixes.yml");
        final Config prefixConfig = new Config(configFile);
        final String prefix;
        final String name = player.getDisplayName().toString();
        if (!prefixConfig.getString(player.getUniqueId().toString()).isEmpty()) {
            prefix = TextFormat.colorize(
                    '&',
                    prefixConfig.getString(player.getUniqueId().toString()) +  " " + TextFormat.RESET + TextFormat.RED
            );
        } else if (event.getPlayer().isOp()) {
            prefix = Main.getInstance().getConfig().getString("opTag");
        } else {
            prefix = Main.getInstance().getConfig().getString("deOpTag");
        }

        event.setFormat(prefix + "{%0}" + TextFormat.RESET + ": " + TextFormat.RESET + "{%1}");
        event.setMessage(
                TextFormat.colorize('&', event.getMessage())
        );
    }
}
