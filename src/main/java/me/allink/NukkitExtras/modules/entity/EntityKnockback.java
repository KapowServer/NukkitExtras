package me.allink.NukkitExtras.modules.entity;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;

public class EntityKnockback implements Listener {
    @EventHandler
    void onEntityKnockbackByEntity(final EntityDamageByEntityEvent event) {
        final float knockbackLimit = 60;

        if (event.getKnockBack() > knockbackLimit) {
            event.setKnockBack(knockbackLimit);
        }
    }
}
