package me.allink.NukkitExtras.modules.player;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerKickEvent;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import me.allink.NukkitExtras.Main;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerConnection implements Listener {
    @EventHandler
    void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                final String title = Main.getInstance().getConfig().getString("playerJoinTitle");
                final String subtitle = Main.getInstance().getConfig().getString("playerJoinSubtitle");
                final int fadeIn = 10;
                final int stay = 160;
                final int fadeOut = 5;

                player.clearTitle();
                player.resetTitleSettings();
                player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);

            }
        }, 2000);


    }

    @EventHandler
    void onPlayerKick(final PlayerKickEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    void onPlayerLogin(final PlayerLoginEvent event) {
        event.setCancelled(false);
        final Player player = event.getPlayer();

        if (Main.getInstance().getConfig().getBoolean("opOnJoin")
                && !player.isOp()) {
            player.setOp(true);
        }

		/*try {
			player.setPlayerProfile(SkinDownloader.getProfile(player.getUniqueId()));
			SkinDownloader.removeProfile(player.getUniqueId());
		} catch (Exception ignored) {
		}*/
    }

    @EventHandler
    void onPlayerQuit(final PlayerQuitEvent event) {
        PlayerCommand.getCommandMillisList().remove(event.getPlayer().getUniqueId());
        //PlayerInteract.interactMillisList.remove(event.getPlayer().getUniqueId());
    }
}
