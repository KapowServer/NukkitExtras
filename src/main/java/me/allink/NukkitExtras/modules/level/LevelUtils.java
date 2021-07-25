package me.allink.NukkitExtras.modules.level;

import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

import java.util.ArrayList;
import java.util.List;

public class LevelUtils {
    public static List<Entity> getEntitiesByClass(Class clazz, Level level) {
        List<Entity> entityList = new ArrayList<>();

        for(Entity entity: level.getEntities()) {
            if(entity.getClass().equals(clazz)) {
                entityList.add(entity);
            }
        }

        return entityList;
    }

    public static void createExplosion(Location location) {
        Level level = location.getValidLevel();

        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", location.getX()))
                        .add(new DoubleTag("", location.getY()))
                        .add(new DoubleTag("", location.getZ())))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0D))
                        .add(new DoubleTag("", 0D))
                        .add(new DoubleTag("", 0D)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0F))
                        .add(new FloatTag("", 0F))
                        .add(new FloatTag("", 0F)))
                .putShort("Fuse", (short) 0);

        Entity explosion = Entity.createEntity("PrimedTnt", level.getChunk(location.getFloorX() >> 4, location.getFloorZ() >> 4), nbt);

        explosion.spawnToAll();
    }

}
