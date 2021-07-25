package me.allink.NukkitExtras.modules.player;

import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerCommand implements Listener {
    private static HashMap<UUID, Long> commandMillisList = new HashMap<UUID, Long>();

    @EventHandler
    void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        final UUID playerUuid = event.getPlayer().getUniqueId();

        if (getCommandMillisList().get(playerUuid) != null) {
            final long millisDifference = System.currentTimeMillis() - getCommandMillisList().get(playerUuid);

            if (millisDifference < 75) {
                event.setCancelled(true);
            }
        }

        getCommandMillisList().put(playerUuid, System.currentTimeMillis());

        if (event.isCancelled()) {
            return;
        }

        final CommandSender sender = event.getPlayer();
        final String command = event.getMessage();
        final boolean isConsoleCommand = false;
        final String checkedCommand = event.getMessage();

        if (checkedCommand != null) {
            if ("cancel".equals(checkedCommand)) {
                event.setCancelled(true);
            } else {
                event.setMessage(checkedCommand);
            }
        }

		/*final MessageInterceptingCommandRunner cmdRunner = new MessageInterceptingCommandRunner(Bukkit.getConsoleSender());
		Bukkit.dispatchCommand(cmdRunner, event.getMessage().substring(1));
        event.setCancelled(true);*/
    }

    public static HashMap<UUID, Long> getCommandMillisList() {
        return commandMillisList;
    }
}
