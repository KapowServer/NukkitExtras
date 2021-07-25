package me.allink.NukkitExtras.modules.player;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityRegainHealthEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;
import cn.nukkit.item.Item;

public class PlayerDamage implements Listener {
    @EventHandler
    void onEntityDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof EntityHuman) {
            if (EntityDamageEvent.DamageCause.VOID.equals(event.getCause())
                    && event.getDamage() == Float.MAX_VALUE) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onEntityRegainHealth(final EntityRegainHealthEvent event) {
        if (event.getAmount() < 0) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    void onFoodLevelChange(final PlayerFoodLevelChangeEvent event) {
        final EntityHuman player = event.getPlayer();

        if (player.getMaxHealth() <= 0) {
            player.setMaxHealth(Integer.MAX_VALUE);
            player.setHealth(20);
            player.setMaxHealth(20);
        }
    }

    @EventHandler
    void onPlayerDeath(final PlayerDeathEvent event) {
        final Player player = event.getEntity();

        for (Player onlinePlayer : event.getEntity().getServer().getOnlinePlayers().values()) {
            onlinePlayer.sendMessage(event.getDeathMessage());
        }

        try {
            if (!event.getKeepInventory()) {
                player.getInventory().clearAll();

                for (Item item : event.getDrops()) {
                    player.getLevel().dropItem(player.getLocation(), item);
                }
            }

            player.setMaxHealth(20);
            player.setHealth(20);
        } catch (Exception exception) {
            player.setMaxHealth(Integer.MAX_VALUE);
            player.setHealth(20);
            player.setMaxHealth(20);
        }

        player.setExperience(event.getExperience());
        player.setLevel(event.getEntity().level);
        player.getFoodData().setLevel(20);
        player.setOnFire(0);
        player.setAirTicks(0);
        player.removeAllEffects();
        player.teleport(event.getEntity().getLevel().getSafeSpawn());

        event.setCancelled(true);
    }
}
