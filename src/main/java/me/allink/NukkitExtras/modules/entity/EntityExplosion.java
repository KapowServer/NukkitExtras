package me.allink.NukkitExtras.modules.entity;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.ExplosionPrimeEvent;

public class EntityExplosion implements Listener {
    @EventHandler
    void onExplosionPrime(final ExplosionPrimeEvent event) {
        final int maxRadius = 20;

        if (event.getForce() > maxRadius) {
            event.setForce(maxRadius);
        }
    }
}
