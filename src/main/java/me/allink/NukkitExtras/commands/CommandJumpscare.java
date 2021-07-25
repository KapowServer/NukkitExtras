package me.allink.NukkitExtras.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.mob.EntityCreeper;
import cn.nukkit.entity.weather.EntityLightning;
import cn.nukkit.level.Location;
import cn.nukkit.level.Sound;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.MathHelper;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.TextFormat;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;

public class CommandJumpscare implements CommandExecutor {
    private void createJumpscare(final Player player) {


        Location loc = player.getTargetBlock(3).getLocation();

        double dx = player.getLocation().getX() - loc.getX();
        double dy = 0;
        double dz = player.getLocation().getZ() - loc.getZ();
        double r = MathHelper.sqrt((float) ( dx*dx + dy*dy +  dz*dz));
        float yaw = (float) (-atan2(dx,dz)/PI*180);
        if (yaw < 0) yaw = 360 + yaw;
        float pitch = (float) (-Math.asin(dy/r)/PI*180);

        CompoundTag nbt = new CompoundTag();
        nbt.putList(new ListTag<>("Pos")
                .add(new DoubleTag("", loc.getX()))
                .add(new DoubleTag("", player.getLocation().getY()))
                .add(new DoubleTag("", loc.getZ())));
        nbt.putList(new ListTag<DoubleTag>("Motion")
                .add(new DoubleTag("", 0))
                .add(new DoubleTag("", 0))
                .add(new DoubleTag("", 0)));
        nbt.putList(new ListTag<FloatTag>("Rotation")
                .add(new FloatTag("", yaw))
                .add(new FloatTag("", pitch)));
        nbt.putBoolean("NoAI", true);

        Entity creeper = Entity.createEntity("Creeper", loc.getChunk(), nbt);
        creeper.spawnTo(player);

        Effect effect = Effect.getEffect(Effect.BLINDNESS);
        effect.setDuration(40);
        effect.setAmbient(true);
        effect.setVisible(false);
        effect.setAmplifier(1);
        player.addEffect(effect);
        //player.sendPotionEffects(player);
        final int maxIterator = 10;
        for (int i = 0; i <= maxIterator; i++) {
            player.sendMovementSpeed(0);
            player.getLevel().addSound(player.getLocation(), Sound.MOB_ELDERGUARDIAN_CURSE, 1, 1, player);
        }
        player.sendMovementSpeed(0.1F);
        //player.removeEffect(Effect.BLINDNESS);

        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                creeper.despawnFromAll();
                creeper.getLevel().removeEntity(creeper);
                cancel();
            }
        }, 100);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(TextFormat.RED + "Usage: /" + s + " <player>");
        } else {
            if (strings[0].equals("*") || strings[0].equals("**")) {
                for (Player onlinePlayer : commandSender.getServer().getOnlinePlayers().values()) {
                    createJumpscare(onlinePlayer);
                }
                commandSender.sendMessage("Successfully created jumpscare for every player");
            } else {
                final Player target = commandSender.getServer().getPlayer(strings[0]);
                if (target != null) {
                    createJumpscare(target);
                    commandSender.sendMessage("Successfully created jumpscare for player \"" + target.getName() + "\"");
                } else {
                    commandSender.sendMessage("Player \"" + strings[0] + "\" not found");
                }
            }
        }
        return true;
    }
}
